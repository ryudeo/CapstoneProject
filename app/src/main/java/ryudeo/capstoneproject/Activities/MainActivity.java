package ryudeo.capstoneproject.Activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.github.tibolte.agendacalendarview.models.BaseCalendarEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import ryudeo.capstoneproject.Adapters.TabPagerAdapter;
import ryudeo.capstoneproject.Fragments.DietBriefingFragment;
import ryudeo.capstoneproject.Fragments.DietCalendarFragment;
import ryudeo.capstoneproject.R;
import ryudeo.capstoneproject.Utils;

public class MainActivity extends AppCompatActivity {

    private DietCalendarFragment mDietCalendarFragment;
    private DietBriefingFragment mDietBriefingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpTabLayout();
        setUpFloatingActionButton();



    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void setUpTabLayout() {

        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> tabTitles = new ArrayList<>();

        mDietCalendarFragment = DietCalendarFragment.newInstance();
        mDietBriefingFragment = DietBriefingFragment.newInstance();
        fragments.add(mDietCalendarFragment);
        fragments.add(mDietBriefingFragment);


        String[] titles = new String[]{"캘린더", "브리핑"};
        tabTitles.addAll(Arrays.asList(titles));

        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());
        tabPagerAdapter.setFragments(fragments);
        tabPagerAdapter.setTitles(tabTitles);

        ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setAdapter(tabPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabbar);
        tabLayout.setupWithViewPager(pager);

    }

    private void setUpFloatingActionButton() {

        FloatingActionButton actionWeight = (FloatingActionButton)findViewById(R.id.action_weight);
        FloatingActionButton actionWater = (FloatingActionButton)findViewById(R.id.action_water);
        FloatingActionButton actionExercise = (FloatingActionButton)findViewById(R.id.action_exercise);
        FloatingActionButton actionFood = (FloatingActionButton)findViewById(R.id.action_food);

        actionWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, FabWeightActivity.class);
                startActivity(intent);
            }
        });

        actionWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, FabWaterActivity.class);
                startActivity(intent);

            }
        });

        actionExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, FabExerciseActivity.class);
                startActivity(intent);

            }
        });

        actionFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, FabFoodActivity.class);
                startActivity(intent);

            }
        });
    }
}
