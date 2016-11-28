package ryudeo.capstoneproject.Fragments;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.tibolte.agendacalendarview.AgendaCalendarView;
import com.github.tibolte.agendacalendarview.CalendarPickerController;
import com.github.tibolte.agendacalendarview.models.BaseCalendarEvent;
import com.github.tibolte.agendacalendarview.models.CalendarEvent;
import com.github.tibolte.agendacalendarview.models.DayItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import ryudeo.capstoneproject.Database.Cols;
import ryudeo.capstoneproject.Database.DbAdapter;
import ryudeo.capstoneproject.R;
import ryudeo.capstoneproject.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DietCalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DietCalendarFragment extends Fragment {

    private Cursor mCursor;



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

        DbAdapter mDbAdapter = new DbAdapter(getActivity()); //declare DbAdapter Object and get context in fragment.

        minDate.add(Calendar.MONTH, -2);
        minDate.set(Calendar.DAY_OF_MONTH, 1);
        maxDate.add(Calendar.YEAR, 1);

        List<CalendarEvent> eventList = new ArrayList<>();
        List<Cols> colsList = new ArrayList<>();

        mCursor = mDbAdapter.fetchAll();
        mCursor.moveToFirst();

        while(!mCursor.moveToNext()) {

            String type = mCursor.getString(mCursor.getColumnIndex("type"));
            int quantity = mCursor.getInt(mCursor.getColumnIndex("quantity"));
            long timeStamp = mCursor.getLong(mCursor.getColumnIndex("timeStamp"));

            colsList.add(new Cols(type, quantity,timeStamp));
            BaseCalendarEvent event = Utils.makeBaseCalendarEvent(getActivity(), Utils.EventType.valueOf(type), quantity, timeStamp);
            eventList.add(event);
        }

//        BaseCalendarEvent event1 = Utils.makeBaseCalendarEvent(getActivity(), Utils.EventType.Water, 100, System.currentTimeMillis());
//        BaseCalendarEvent event2 = Utils.makeBaseCalendarEvent(getActivity(), Utils.EventType.Food, 100, System.currentTimeMillis());
//        BaseCalendarEvent event3 = Utils.makeBaseCalendarEvent(getActivity(), Utils.EventType.Exercise, 100, System.currentTimeMillis());
//
//        eventList.add(event1);
//        eventList.add(event2);
//        eventList.add(event3);

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
