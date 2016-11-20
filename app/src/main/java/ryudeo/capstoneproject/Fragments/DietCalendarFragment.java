package ryudeo.capstoneproject.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ryudeo.capstoneproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DietCalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DietCalendarFragment extends Fragment {


    public DietCalendarFragment() {
        // Required empty public constructor
    }

    public static DietCalendarFragment newInstance() {
        DietCalendarFragment fragment = new DietCalendarFragment();

        return fragment;
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diet_calendar, container, false);
    }

}
