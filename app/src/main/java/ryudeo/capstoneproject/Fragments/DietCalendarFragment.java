package ryudeo.capstoneproject.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
        View rootView = inflater.inflate(R.layout.fragment_diet_calendar, container, false);
        setUpCalendar(rootView);


        return rootView;
    }

    private void setUpCalendar(View rootView) {

        AgendaCalendarView agendaCalendarView = (AgendaCalendarView) rootView.findViewById(R.id.agenda_calendar_view);

        Calendar minDate = Calendar.getInstance();
        Calendar maxDate = Calendar.getInstance();

        minDate.add(Calendar.MONTH, -2);
        minDate.set(Calendar.DAY_OF_MONTH, 1);
        maxDate.add(Calendar.YEAR, 1);

        List<CalendarEvent> eventList = new ArrayList<>();

        agendaCalendarView.init(eventList, minDate, maxDate, Locale.getDefault(), new CalendarPickerController() {
            @Override
            public void onDaySelected(DayItem dayItem) {

            }

            @Override
            public void onEventSelected(CalendarEvent event) {

            }

            @Override
            public void onScrollToDate(Calendar calendar) {

            }
        });
    }

}
