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

    private AgendaCalendarView mAgenda;


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
    public void onStart() {
        super.onStart();

        if (mAgenda != null) {

            fillEvents(getEvents());
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

        mAgenda = (AgendaCalendarView) rootView.findViewById(R.id.agenda_calendar_view);
        List<CalendarEvent> events = getEvents();
        fillEvents(events);

    }

    private void fillEvents(List<CalendarEvent> events) {

        Calendar minDate = Calendar.getInstance();
        Calendar maxDate = Calendar.getInstance();

        minDate.add(Calendar.MONTH, -1);
        minDate.set(Calendar.DAY_OF_MONTH, 1);
        maxDate.add(Calendar.YEAR, 1);



        mAgenda.init(events, minDate, maxDate, Locale.getDefault(), new CalendarPickerController() {
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

    private List<CalendarEvent> getEvents() {

        DbAdapter mDbAdapter = new DbAdapter(getActivity()); //declare DbAdapter Object and get context in fragment.
        List<CalendarEvent> eventList = new ArrayList<>(); //이벤트 어레이리스트 생성
        List<Cols> colsList = new ArrayList<>(); //레코드 어레이리스트 생성
        BaseCalendarEvent event; // 캘린더 이벤트 객체 선언

        Cursor c = mDbAdapter.open().fetchAll(); //모든 레코드를 커서로
        c.moveToFirst(); // 커서를 맨 앞으로

        while (c.moveToNext()) { //커서를 다음으로 옮기며 모든 레코드 순회

            String type = c.getString(c.getColumnIndex(DbAdapter.COL_TYPE)); //타입 컬럼값
            int quantity = c.getInt(c.getColumnIndex(DbAdapter.COL_QUANTITY));//양 컬럼값
            long timeStamp = c.getLong(c.getColumnIndex(DbAdapter.COL_TIMESTAMP));//타임스탬프 컬럼값
            String name = c.getString(c.getColumnIndex(DbAdapter.COL_NAME));//타임스탬프 음식이름
            colsList.add(new Cols(type, quantity, timeStamp)); //레코드 어레이리스트에 추가
            event = Utils.makeBaseCalendarEvent(getActivity(), Utils.EventType.valueOf(type), name, quantity, timeStamp); //이벤트 생성


            if (!(type == "Weight")) { //Weight type 제외
                eventList.add(event); //이벤트 어레이리스트에 추가
            }
        }
        mDbAdapter.close(); //데이터베이스 닫음

        return eventList;
    }

}
