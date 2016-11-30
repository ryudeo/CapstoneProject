package ryudeo.capstoneproject.Fragments;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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

import static android.content.ContentValues.TAG;

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

        List<CalendarEvent> eventList = new ArrayList<>(); //이벤트 어레이리스트 생성
        List<Cols> colsList = new ArrayList<>(); //레코드 어레이리스트 생성
        BaseCalendarEvent event; // 캘린더 이벤트 객체 선언

        mCursor = mDbAdapter.open().fetchAll(); //모든 레코드를 커서로
        mCursor.moveToFirst(); // 커서를 맨 앞으로

        while(mCursor.moveToNext()) { //커서를 다음으로 옮기며 모든 레코드 순회

            String type = mCursor.getString(mCursor.getColumnIndex(DbAdapter.COL_TYPE)); //타입 컬럼값
            int quantity = mCursor.getInt(mCursor.getColumnIndex(DbAdapter.COL_QUANTITY));//양 컬럼값
            long timeStamp = mCursor.getLong(mCursor.getColumnIndex(DbAdapter.COL_TIMESTAMP));//타임스탬프 컬럼값

            colsList.add(new Cols(type, quantity,timeStamp)); //레코드 어레이리스트에 추가
            event = Utils.makeBaseCalendarEvent(getActivity(), Utils.EventType.valueOf(type), quantity, timeStamp); //이벤트 생성

            if(!(type == "Weight")){ //Weight type 제외
                eventList.add(event); //이벤트 어레이리스트에 추가
            }
        }
        mDbAdapter.close(); //데이터베이스 닫음

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
