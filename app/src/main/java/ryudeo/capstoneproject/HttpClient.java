package ryudeo.capstoneproject;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import ryudeo.capstoneproject.Database.FoodInfo;

/**
 * Created by RYU on 2016. 11. 30..
 */

public class HttpClient {

    private static final String LOG_TAG = HttpClient.class.getSimpleName();
    private static final String ROOT_URL = "http://api.dbstore.or.kr:8880/foodinfo";
    private static final String SUB_URL_SEARCH = "/search.do";
    private static final String UID = "KK7ZMV34";

    private static final String HEADER_AUTH_VALUE = "MzI1LTE0ODAzNDg5ODk3NjItNjYzYjU1YTItYTdkYi00NWIwLWJiNTUtYTJhN2RiMzViMGM1";
    private static final String HEADER_AUTH_KEY = "x-waple-authorization";

    private OkHttpClient mClient;
    private Context mContext;

    public HttpClient(Context context) {

        mClient = new OkHttpClient();
        mContext = context;
    }

    public void requestFoodListWithSearchQuery(String searchQuery, final HttpClientCallback callback) {

        String url = Uri.parse(ROOT_URL + SUB_URL_SEARCH).buildUpon()
                .appendQueryParameter("uid", UID)
                .appendQueryParameter("w", searchQuery).build().toString();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                ArrayList<FoodInfo> foodInfos = new ArrayList<>();

                try {
                    JSONArray foodList = response.getJSONArray("food_list");

                    for (int i = 0; i < foodList.length(); i++) {

                        JSONObject jsonObject = foodList.getJSONObject(i);
                        FoodInfo foodInfo = new FoodInfo();
                        String foodName = jsonObject.getString("food_name");
                        foodName = foodName.replaceAll("\\s+", "");

                        foodInfo.setName(foodName);
                        if (jsonObject.has("kcal")) {

                            String kcal = jsonObject.getString("kcal");
                            kcal = kcal.replaceAll("\\s+", "");
                            foodInfo.setKcal(kcal);
                        }

                        String ea = jsonObject.getString("ing_first");
                        foodInfo.setEa(ea);

                        foodInfos.add(foodInfo);

                    }
                    Log.i(LOG_TAG, foodList.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                callback.onSuccess(foodInfos);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(LOG_TAG, "onErrorResponse : " + error.networkResponse.toString());

                callback.onFail(error.networkResponse.toString());
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {


                Map<String, String> headers = new HashMap<>();
                headers.put(HEADER_AUTH_KEY, HEADER_AUTH_VALUE);
                return headers;

            }

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                return super.parseNetworkResponse(response);
            }
        };

        RequestQueueManager.getInstance(mContext).getRequestQueue().add(jsonObjectRequest);

    }

    public interface HttpClientCallback {

        void onSuccess(ArrayList<FoodInfo> foodInfos);
        void onFail(String errMsg);
    }
}
