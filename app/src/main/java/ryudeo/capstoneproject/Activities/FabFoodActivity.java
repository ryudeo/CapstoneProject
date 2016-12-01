package ryudeo.capstoneproject.Activities;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ryudeo.capstoneproject.Adapters.FoodSearchItemListAdapter;
import ryudeo.capstoneproject.Database.DbAdapter;
import ryudeo.capstoneproject.Database.FoodInfo;
import ryudeo.capstoneproject.Fragments.SearchStaticRecyclerFragment;
import ryudeo.capstoneproject.HttpClient;
import ryudeo.capstoneproject.R;

import xyz.sahildave.widget.SearchViewLayout;

public class FabFoodActivity extends AppCompatActivity {

    private ArrayList<FoodInfo> mFoodInfos = new ArrayList<>();
    private FoodSearchItemListAdapter mFoodSearchItemListAdapter;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private TextView mEmptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_food);

        setUpLayout();
        setUpViews();
    }

    private void setUpViews() {

        mFoodSearchItemListAdapter = new FoodSearchItemListAdapter(mFoodInfos, this);
        mFoodSearchItemListAdapter.setListItemClickCallback(new FoodSearchItemListAdapter.ListItemClickCallback() {
            @Override
            public void onItemClick(int position) {

                FoodInfo foodInfo = mFoodInfos.get(position);
                new DbAdapter(getApplicationContext()).open().insertData("Food", foodInfo.getName(), foodInfo.getQuantity());
                Toast.makeText(getApplicationContext(), foodInfo.getName() + " 냠냠~~", Toast.LENGTH_SHORT).show();

            }
        });
        mRecyclerView = ((RecyclerView) findViewById(R.id.search_static_recycler));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFoodSearchItemListAdapter);


        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mEmptyTextView = (TextView) findViewById(R.id.textview_empty);


    }

    private void showProgressBar() {

        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void dismissProgressBar(){

        mProgressBar.setVisibility(View.GONE);
    }

    private void requestSearch(String searchQuery) {

        showProgressBar();
        HttpClient httpClient = new HttpClient(this);
        httpClient.requestFoodListWithSearchQuery(searchQuery, new HttpClient.HttpClientCallback() {
            @Override
            public void onSuccess(ArrayList<FoodInfo> foodInfos) {

                dismissProgressBar();
                mFoodInfos = foodInfos;
                mFoodSearchItemListAdapter.setFoodInfoList(mFoodInfos);
                mFoodSearchItemListAdapter.notifyDataSetChanged();

                if (mFoodInfos.isEmpty()) mEmptyTextView.setVisibility(View.VISIBLE);
                else mEmptyTextView.setVisibility(View.GONE);

            }

            @Override
            public void onFail(String errMsg) {

                dismissProgressBar();

            }
        });
    }

    void setUpLayout() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final SearchViewLayout searchViewLayout = (SearchViewLayout) findViewById(R.id.search_view_container);

        searchViewLayout.setExpandedContentSupportFragment(this, new SearchStaticRecyclerFragment());
        searchViewLayout.handleToolbarAnimation(toolbar);
        searchViewLayout.setCollapsedHint("검색어를 입력하세요.");
        searchViewLayout.setExpandedHint("검색어를 입력하세요.");

        ColorDrawable collapsed = new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimary));
        ColorDrawable expanded = new ColorDrawable(ContextCompat.getColor(this, R.color.default_color_expanded));

        searchViewLayout.setTransitionDrawables(collapsed, expanded);
        searchViewLayout.setSearchListener(new SearchViewLayout.SearchListener() {
            @Override
            public void onFinished(String searchKeyword) {
                searchViewLayout.collapse();

                searchViewLayout.setCollapsedHint(searchKeyword);
                searchViewLayout.setExpandedText(searchKeyword);
                requestSearch(searchKeyword);
            }
        });
    }
}
