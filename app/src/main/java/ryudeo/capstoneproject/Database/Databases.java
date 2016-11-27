package ryudeo.capstoneproject.Database;

import android.provider.BaseColumns;

/**
 * Created by RYU on 2016. 11. 28..
 */

public class Databases {
    public static final class CreateDB implements BaseColumns {
        public static final String WEIGHT = "weight";
        public static final String WATER = "water";
        public static final String EXERCISE = "exercise";
        public static final String CALORIES = "calories";
        public static final String _TABLENAME = "daily";
        public static final String _CREATE =
                "create table "+_TABLENAME+"("
                        +_ID+" integer primary key autoincrement, "
                        +WEIGHT+" integer not null , "
                        +WATER+" integer not null , "
                        +EXERCISE+" integer not null , "
                        +CALORIES+" integer not null );";
    }
}
