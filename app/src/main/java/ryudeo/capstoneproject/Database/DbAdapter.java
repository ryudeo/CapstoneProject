package ryudeo.capstoneproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by RYU on 2016. 11. 28..
 */

public class DbAdapter {

    public static final String COL_ROWID = "_id";
    public static final String COL_TYPE = "type";
    public static final String COL_NAME = "name";
    public static final String COL_QUANTITY = "quantity";
    public static final String COL_TIMESTAMP = "timeStamp";

    private static final String TAG = "DbAdapter"; //log tag
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_CREATE =
            "create table daily (_id integer primary key autoincrement,"+
                    "type text not null, name text, quantity integer not null, timeStamp real not null);";

    private static final String DATABASE_NAME = "data.db";
    private static final String DATABASE_TABLE = "daily";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

// TODO Auto-generated constructor stub
        }

        public void onCreate(SQLiteDatabase db){
            db.execSQL(DATABASE_CREATE);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            Log.w(TAG, "Upgrading db from version" + oldVersion + " to" +
                    newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS data");
            onCreate(db);
        }

    }

    public DbAdapter(Context ctx){
        this.mCtx = ctx;
    }

    public DbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        mDbHelper.close();
    }

    public long insertData(String type, String name, int quantity){
        ContentValues values = new ContentValues();
        values.put(COL_TYPE, type);
        values.put(COL_NAME, name);
        values.put(COL_QUANTITY, quantity);
        values.put(COL_TIMESTAMP, System.currentTimeMillis());

        return mDb.insert(DATABASE_TABLE, null, values);
    }

    public void deleteData(long timeStamp) {
        ContentValues values = new ContentValues();
        mDb.execSQL("DELETE FROM daily WHERE timeStamp = " + timeStamp);
    }

    public Cursor fetchAll(){
        return mDb.query(DATABASE_TABLE, new String[]{COL_ROWID, COL_TYPE, COL_NAME, COL_QUANTITY, COL_TIMESTAMP}, null, null, null, null, null);

    }

    public Cursor fetchData(){
        return mDb.query(DATABASE_TABLE, new String[]{COL_TYPE, COL_QUANTITY, COL_TIMESTAMP}, null, null, null, null, null);

    }

    public Cursor fetchAllFood() {

        String where = COL_TYPE + " = " + "'Food'" + " AND " + COL_TIMESTAMP +" >= " + getStartMills() + " AND " + COL_TIMESTAMP + " <= " + getLastMills();

        return mDb.query(DATABASE_TABLE,
                new String[]{COL_TYPE, COL_QUANTITY, COL_NAME, COL_TIMESTAMP},
                where,
                null,
                null,
                null,
                null);
    }

    public Cursor fetchAllWater() {

        String where = COL_TYPE + " = " + "'Water'" + " AND " + COL_TIMESTAMP +" >= " + getStartMills() + " AND " + COL_TIMESTAMP + " <= " + getLastMills();



        return mDb.query(DATABASE_TABLE,
                new String[]{COL_TYPE, COL_QUANTITY, COL_NAME, COL_TIMESTAMP},
                where,
                null,
                null,
                null,
                null);
    }

    public Cursor fetchAllWeight() {

        String where = COL_TYPE + " = " + "'Weight'" + " AND " + COL_TIMESTAMP +" >= " + getStartMills() + " AND " + COL_TIMESTAMP + " <= " + getLastMills();

        return mDb.query(DATABASE_TABLE,
                new String[]{COL_TYPE, COL_QUANTITY, COL_NAME, COL_TIMESTAMP},
                where,
                null,
                null,
                null,
                null);
    }

    public Cursor fetchAllExercise() {

        String where = COL_TYPE + " = " + "'Exercise'" + " AND " + COL_TIMESTAMP +" >= " + getStartMills() + " AND " + COL_TIMESTAMP + " <= " + getLastMills();

        return mDb.query(DATABASE_TABLE,
                new String[]{COL_TYPE, COL_QUANTITY, COL_NAME, COL_TIMESTAMP},
                where,
                null,
                null,
                null,
                null);
    }

    public static long getStartMills() {

        long curMills = System.currentTimeMillis();

        long week = 1000 * 60 * 60 * 24 * 7;

        return curMills - week;
    }

    public static long getLastMills() {

        long curMills = System.currentTimeMillis();
        Calendar todayLast = Calendar.getInstance();
        todayLast.setTimeInMillis(curMills);
        todayLast.set(Calendar.HOUR, 23);
        todayLast.set(Calendar.MINUTE, 59);
        todayLast.set(Calendar.SECOND, 59);

        return todayLast.getTimeInMillis();
    }
}

