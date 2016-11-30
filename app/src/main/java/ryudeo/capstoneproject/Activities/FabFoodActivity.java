package ryudeo.capstoneproject.Activities;

import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Locale;

import ryudeo.capstoneproject.Adapters.SearchItemListAdapter;
import ryudeo.capstoneproject.Database.FoodInfo;
import ryudeo.capstoneproject.Fragments.SearchStaticRecyclerFragment;
import ryudeo.capstoneproject.R;

import xyz.sahildave.widget.SearchViewLayout;

public class FabFoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_food);

        setUpLayout();
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
                Snackbar.make(searchViewLayout, "검색중 :  " + searchKeyword, Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
