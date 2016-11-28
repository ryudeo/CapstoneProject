package ryudeo.capstoneproject;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.github.tibolte.agendacalendarview.models.BaseCalendarEvent;

import java.util.Calendar;

/**
 * Created by RYU on 2016. 11. 28..
 */

public class Utils {


    public enum  EventType{

        Weight,
        Water,
        Exercise,
        Food,
    }


    public static String getEventTitle(EventType type) {


        switch (type) {

            case Weight:
                return "Weight";
            case Water:
                return "Water";
            case Exercise:
                return "Exercise";
            case Food:
                return "Food";
            default:
                return "Water";
        }
    }

    public static String getEventDescription(EventType type, int quantity) {


        String postfix = "";

        switch (type) {

            case Weight:
                postfix = " kg";
                break;
            case Water:
                postfix = " ml";
                break;
            case Exercise:
                postfix = " kcal";
                break;
            case Food:
                postfix = " kcal";
                break;
            default:
                postfix = " ml";
                break;
        }

        return quantity + postfix;
    }

    public static int getEventColor(Context context, EventType type) {


        int color;

        switch (type) {

            case Weight:
                color = ContextCompat.getColor(context, R.color.colorEventFood);
                break;
            case Water:
                color = ContextCompat.getColor(context, R.color.colorEventWater);
                break;
            case Exercise:
                color = ContextCompat.getColor(context, R.color.colorEventExercise);
                break;
            case Food:
                color = ContextCompat.getColor(context, R.color.colorEventFood);
                break;

            default:
                color = ContextCompat.getColor(context, R.color.colorEventFood);
                break;
        }

        return color;
    }

    public static Calendar getEventCalendarDate(long timeStamp) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);

        return calendar;
    }

    public static BaseCalendarEvent makeBaseCalendarEvent (Context context, EventType eventType, int quantity, long timeStamp) {

        String title = Utils.getEventTitle(eventType);
        String desc = Utils.getEventDescription(eventType, quantity);
        int color = Utils.getEventColor(context, eventType);
        Calendar startTime = Utils.getEventCalendarDate(timeStamp);

        return new BaseCalendarEvent(title, desc, "", color, startTime, startTime, true);
    }

}
