package ryudeo.capstoneproject.Activities;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ryudeo.capstoneproject.Adapters.ExerSearchItemListAdapter;
import ryudeo.capstoneproject.Database.DbAdapter;
import ryudeo.capstoneproject.Database.ExerInfo;
import ryudeo.capstoneproject.Fragments.SearchStaticRecyclerFragment;
import ryudeo.capstoneproject.R;
import xyz.sahildave.widget.SearchViewLayout;

public class FabExerciseActivity extends AppCompatActivity {

    private ArrayList<ExerInfo> mExerInfosAll = new ArrayList<>();
    private ArrayList<ExerInfo> mExerInfosResult = new ArrayList<>();
    private ExerSearchItemListAdapter mExerSearchItemListAdapter;
    private RecyclerView mRecyclerView;
    private TextView mEmptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_exercise);

        setUpLayout();
        setUpViews();
        setUpExerList();
    }

    public void setUpViews() {

        mExerSearchItemListAdapter = new ExerSearchItemListAdapter(mExerInfosResult, this);
        mExerSearchItemListAdapter.setListItemClickCallback(new ExerSearchItemListAdapter.ListItemClickCallback() {
            @Override
            public void onItemClick(int position) {

                ExerInfo exerInfo = mExerInfosResult.get(position);
                new DbAdapter(getApplicationContext()).open().insertData("Exercise", exerInfo.getName(), exerInfo.getQuantity());
                Toast.makeText(getApplicationContext(), exerInfo.getName() + " 힘들어 헉헉!", Toast.LENGTH_SHORT).show();

            }
        });
        mRecyclerView = ((RecyclerView) findViewById(R.id.search_static_recycler));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mExerSearchItemListAdapter);

        mEmptyTextView = (TextView) findViewById(R.id.textview_empty);

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
                filter(searchKeyword);
            }
        });
    }

    public void filter(String searchKeyword) {
        mExerInfosResult.clear();
        for(ExerInfo exerInfo : mExerInfosAll) {
            String name  = exerInfo.getName();
            if(name.contains(searchKeyword)) {
                mExerInfosResult.add(exerInfo);
            }
        }

        mExerSearchItemListAdapter.setExerInfoList(mExerInfosResult);
        mExerSearchItemListAdapter.notifyDataSetChanged();

        if(mExerInfosResult.isEmpty()) mEmptyTextView.setVisibility(View.VISIBLE);
        else mEmptyTextView.setVisibility(View.GONE);

    }

    public void setUpExerList() {
        mExerInfosAll.add(new ExerInfo("산책", 66));
        mExerInfosAll.add(new ExerInfo("자전거타기(보통속도)", 93));
        mExerInfosAll.add(new ExerInfo("자전거타기(빠른속도)", 111));
        mExerInfosAll.add(new ExerInfo("스트레칭", 63));
        mExerInfosAll.add(new ExerInfo("춤추기", 102));
        mExerInfosAll.add(new ExerInfo("볼링", 75));
        mExerInfosAll.add(new ExerInfo("요가", 63));
        mExerInfosAll.add(new ExerInfo("골프", 102));
        mExerInfosAll.add(new ExerInfo("에어로빅", 126));
        mExerInfosAll.add(new ExerInfo("계단오르내리기", 48*3));
        mExerInfosAll.add(new ExerInfo("팔굽혀펴기", 32*3));
        mExerInfosAll.add(new ExerInfo("스키", 59*3));
        mExerInfosAll.add(new ExerInfo("탁구", 50*3));
        mExerInfosAll.add(new ExerInfo("테니스", 60*3));
        mExerInfosAll.add(new ExerInfo("배드민턴", 59*3));
        mExerInfosAll.add(new ExerInfo("배구", 59*3));
        mExerInfosAll.add(new ExerInfo("수영(자유형)", 145*3));
        mExerInfosAll.add(new ExerInfo("수영(접형)", 184*3));
        mExerInfosAll.add(new ExerInfo("조깅", 79*3));
        mExerInfosAll.add(new ExerInfo("농구", 67*3));
        mExerInfosAll.add(new ExerInfo("윗몸일으키기", 72*3));
        mExerInfosAll.add(new ExerInfo("줄넘기", 75*3));
        mExerInfosAll.add(new ExerInfo("걷기", 90));
        mExerInfosAll.add(new ExerInfo("맨손체조", 210));
        mExerInfosAll.add(new ExerInfo("가벼운달리기", 240));
        mExerInfosAll.add(new ExerInfo("스케이트타기", 240));
        mExerInfosAll.add(new ExerInfo("축구", 270));
        mExerInfosAll.add(new ExerInfo("핸드볼", 300));
        mExerInfosAll.add(new ExerInfo("빠르게달리기", 315));
        mExerInfosAll.add(new ExerInfo("빠른걷기", 150));
        mExerInfosAll.add(new ExerInfo("승마", 173));
        mExerInfosAll.add(new ExerInfo("미식축구", 270));
        mExerInfosAll.add(new ExerInfo("등산", 196/2));
        mExerInfosAll.add(new ExerInfo("수영(평형)", 273/2));
        mExerInfosAll.add(new ExerInfo("야구", 180/2));
        mExerInfosAll.add(new ExerInfo("수상스키", 200/2));
        mExerInfosAll.add(new ExerInfo("파도타기(서핑)", 176/2));
        mExerInfosAll.add(new ExerInfo("소프트볼", 90/2));
        mExerInfosAll.add(new ExerInfo("탁구", 200/2));
        mExerInfosAll.add(new ExerInfo("사이클링", 111/2));
        mExerInfosAll.add(new ExerInfo("피구", 102/2));
        mExerInfosAll.add(new ExerInfo("아이스하키", 322/2));
        mExerInfosAll.add(new ExerInfo("라켓볼", 432/2));
        mExerInfosAll.add(new ExerInfo("스쿼시", 431/2));
        mExerInfosAll.add(new ExerInfo("당구", 136/2));
        mExerInfosAll.add(new ExerInfo("유도", 638/2));
        mExerInfosAll.add(new ExerInfo("스쿠버다이빙", 269/2));
        mExerInfosAll.add(new ExerInfo("헬스", 603/2));
        mExerInfosAll.add(new ExerInfo("덤벨", 756/2));
        mExerInfosAll.add(new ExerInfo("바벨", 486/2));
    }
}
