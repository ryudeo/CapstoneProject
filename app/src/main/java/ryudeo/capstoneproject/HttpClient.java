package ryudeo.capstoneproject;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ryudeo.capstoneproject.Database.FoodInfo;

/**
 * Created by RYU on 2016. 11. 30..
 */

public class HttpClient {

    private static final String ROOT_URL = "http://api.dbstore.or.kr:8880/foodinfo";
    private static final String SUB_URL_SEARCH = "/search.do";
    private static final String UID = "KK7ZMV34";

    private static final String HEADER_AUTH_VALUE = "MzI1LTE0ODAzNDg5ODk3NjItNjYzYjU1YTItYTdkYi00NWIwLWJiNTUtYTJhN2RiMzViMGM1";
    private static final String HEADER_AUTH_KEY = "x-waple-authorization";

    private OkHttpClient mClient;

    public HttpClient() {

        mClient = new OkHttpClient();
    }

    public void requestFoodListWithSearchQuery(String searchQuery, final HttpClientCallback callback) {


        HttpUrl.Builder urlBuilder = HttpUrl.parse(ROOT_URL + SUB_URL_SEARCH).newBuilder()
                .addQueryParameter("uid", UID)
                .addQueryParameter("w", searchQuery);

        Request request = new Request.Builder()
                .header(HEADER_AUTH_KEY, HEADER_AUTH_VALUE)
                .url(urlBuilder.build())
                .build();

        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                callback.onFail("Error");
                Log.i("Error : ", "error" );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    Log.i("Response : ", response.body().string());

                    ArrayList<FoodInfo> foodInfos = new ArrayList<FoodInfo>();
                    try {
                        JSONObject resJson = new JSONObject(response.body().string());

                        JSONArray foodList = resJson.getJSONArray("food_list");


                        for (int i = 0; i < foodList.length(); i++) {

                            JSONObject foodJson = foodList.getJSONObject(i);
                            FoodInfo foodInfo = new FoodInfo();

                            String name = foodJson.getString("food_name");

                            if (foodJson.has("kcal")) {

                                String kcal = foodJson.getString("kcal");
                                foodInfo.setKcal(kcal);

                            }


                            foodInfo.setName(name);
                            foodInfos.add(foodInfo);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    callback.onSuccess(foodInfos);
                }
                else {

                    callback.onFail("Error");
                }


            }
        });
    }


    public interface HttpClientCallback {

        void onSuccess(ArrayList<FoodInfo> foodInfos);
        void onFail(String errMsg);
    }
}
