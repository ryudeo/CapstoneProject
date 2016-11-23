package ryudeo.capstoneproject.Fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.tibolte.agendacalendarview.AgendaCalendarView;
import com.github.tibolte.agendacalendarview.CalendarPickerController;
import com.github.tibolte.agendacalendarview.models.CalendarEvent;
import com.github.tibolte.agendacalendarview.models.DayItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import ryudeo.capstoneproject.Activities.ExerciseBriefActivity;
import ryudeo.capstoneproject.Activities.FoodBriefActivity;
import ryudeo.capstoneproject.Activities.WaterBriefActivity;
import ryudeo.capstoneproject.Activities.WeightBriefActivity;
import ryudeo.capstoneproject.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DietBriefingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DietBriefingFragment extends Fragment{

    public DietBriefingFragment() {
        // Required empty public constructor
    }


    public static DietBriefingFragment newInstance() {

        return new DietBriefingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_diet_briefing,container , false);
        setUpActions(rootView);

        return rootView;
    }



    private void setUpActions(View rootView) {

        ViewGroup weightFrame = (ViewGroup)rootView.findViewById(R.id.weightFrame);
        View waterFrame = rootView.findViewById(R.id.waterFrame);
        View exerciseFrame = rootView.findViewById(R.id.exerciseFrame);
        View foodFrame = rootView.findViewById(R.id.foodFrame);



        weightFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), WeightBriefActivity.class);
                startActivity(intent);
            }
        });

        waterFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), WaterBriefActivity.class);
                startActivity(intent);

            }
        });

        exerciseFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), ExerciseBriefActivity.class);
                startActivity(intent);

            }
        });

        foodFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), FoodBriefActivity.class);
                startActivity(intent);

            }
        });
    }


}
