package ryudeo.capstoneproject.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import ryudeo.capstoneproject.Adapters.TabPagerAdapter;
import ryudeo.capstoneproject.Database.DbAdapter;
import ryudeo.capstoneproject.Fragments.DietBriefingFragment;
import ryudeo.capstoneproject.Fragments.DietCalendarFragment;
import ryudeo.capstoneproject.R;

public class MainActivity extends AppCompatActivity {

    private DietCalendarFragment mDietCalendarFragment;
    private DietBriefingFragment mDietBriefingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpTabLayout();
        setUpFloatingActionButton();


//        DbAdapter dbAdapter = new DbAdapter(this);
//
//        Cursor foodCursor = dbAdapter.open().fetchAllFood();
//        Cursor waterCursor = dbAdapter.open().fetchAllWater();
//        Cursor weightCursor = dbAdapter.open().fetchAllWeight();
//        Cursor exerciseCursor = dbAdapter.open().fetchAllExercise();
//
//        log(foodCursor);
//        log(waterCursor);
//        log(weightCursor);
//        log(exerciseCursor);

    }

    private void log(Cursor cursor) {

        if (cursor != null) {

            while (cursor.moveToNext()) {

                long timeStamp = cursor.getLong(cursor.getColumnIndex(DbAdapter.COL_TIMESTAMP));
                String name = cursor.getString(cursor.getColumnIndex(DbAdapter.COL_NAME));
                int quantity = cursor.getInt(cursor.getColumnIndex(DbAdapter.COL_QUANTITY));
                String type = cursor.getString(cursor.getColumnIndex(DbAdapter.COL_TYPE));

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(timeStamp);

                int month = calendar.get(Calendar.MONTH) + 1;
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR);
                int seconds = calendar.get(Calendar.MINUTE);

                Log.i("Cursor log : ", "Type : " + type + "\n" +
                        "Name : " + name + "\n" +
                        "Quantity : " + quantity+ "\n" +
                "TimeStamp : " + month+"."+day+","+hour+":"+seconds);
            }
        }
        cursor.close();
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

        final FloatingActionsMenu actionsMenu = (FloatingActionsMenu)findViewById(R.id.multiple_actions);



        FloatingActionButton actionWeight = (FloatingActionButton)findViewById(R.id.action_weight);
        FloatingActionButton actionWater = (FloatingActionButton)findViewById(R.id.action_water);
        FloatingActionButton actionExercise = (FloatingActionButton)findViewById(R.id.action_exercise);
        FloatingActionButton actionFood = (FloatingActionButton)findViewById(R.id.action_food);

        actionWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                actionsMenu.collapse();
                Intent intent = new Intent(MainActivity.this, FabWeightActivity.class);
                startActivity(intent);
            }
        });

        actionWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                actionsMenu.collapse();
                Intent intent = new Intent(MainActivity.this, FabWaterActivity.class);
                startActivity(intent);

            }
        });

        actionExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                actionsMenu.collapse();
                Intent intent = new Intent(MainActivity.this, FabExerciseActivity.class);
                startActivity(intent);

            }
        });

        actionFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                actionsMenu.collapse();
                Intent intent = new Intent(MainActivity.this, FabFoodActivity.class);
                startActivity(intent);

            }
        });
    }
}
