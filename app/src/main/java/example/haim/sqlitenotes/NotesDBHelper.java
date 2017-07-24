package example.haim.sqlitenotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DELL e7440 on 24/07/2017.
 *
 * SQLite open Helper class
 */

public class NotesDBHelper extends SQLiteOpenHelper{

    private static final String createTableNotes =
            "\"CREATE TABLE \" +NotesContract.NotesTable.tableName + \"(\" +\n" +
            "                NotesContract.NotesTable.colID + \" INTEGER PRIMARY KEY AUTOINCREMENT, \" +\n" +
            "                NotesContract.NotesTable.colTitle + \" TEXT NOT NULL, \" +\n" +
            "                NotesContract.NotesTable.colContent + \" TEXT\"+\n" +
            "                \");\"";

    public NotesDBHelper(Context context) {
        super(context,
                NotesContract.DB_NAME,
                null /*Don't know what's cursor yet*/,
                NotesContract.DB_VERSION);
    }

    //when? -> once - at the first time uses the DB - when we first use this class
    @Override
    public void onCreate(SQLiteDatabase db) {
        //create your tables here:
        db.execSQL(createTableNotes);

        //can create more tables:
        /*
        db.execSQL("CREATE TABLE Notes("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "colTitle TEXT NOT NULL," +
                "colContent TEXT "+
                ")"
        );
        */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE Notes");
        db.execSQL(createTableNotes);

        /* Guide-lines
        db.execSQL("CREATE TABLE TEMPTABLE");
        //Back Up -> Take all the notes from the old table And put into temp table
        db.execSQL("DROP TABLE Notes");
        db.execSQL("CREATE TABLE Notes (color TEXT...)");
        db.execSQL("INSERT INTO OtherTable");
        */

    }
    //Construct Design Pattern:
    public static class NotesContract{
        public static final String DB_NAME = "NotesDB";
        public static final int DB_VERSION= 1;

        public static class NotesTable{
            public static String tableName = "Notes";

            public static String colID = "_ID";
            public static String colTitle = "colTitle";
            public static String colContent = "colContent";
        }


        //More tables like this:
        /*
        public static class UsersTable{
            public static String tableName = "Users";

            public static String colID = "_ID";
            public static String colFirstName = "firstName";
            public static String colLastName = "lastName";
        }
        */
    }
}
