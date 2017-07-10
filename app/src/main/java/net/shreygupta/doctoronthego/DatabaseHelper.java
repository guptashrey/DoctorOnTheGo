package net.shreygupta.doctoronthego;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by shreygupta on 10/07/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Patient DatabaseHelper";
    private static final int VERSION = 1;
    private static final String CREATE_TABLE_PATIENT = "CREATE TABLE PATIENT_INFO(P_ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRST_NAME VARCHAR(20), LAST_NAME VARCHAR(20), EMAIL VARCHAR(50), PASSWORD VARCHAR(20))";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_PATIENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long patient_insert_Data(String a, String b, String c, String d) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FIRST_NAME", a);
        contentValues.put("LAST_NAME", b);
        contentValues.put("EMAIL", c);
        contentValues.put("PASSWORD", d);

        long id = db.insert("PATIENT_INFO", null, contentValues);
        return id;
    }

    public String getData() {
        SQLiteDatabase db = getReadableDatabase();
        String [] columns = {"P_ID", "FIRST_NAME", "LAST_NAME", "EMAIL", "PASSWORD"};

        Cursor cursor = db.query("PATIENT_INFO", columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();

        while (cursor.moveToNext()) {

            int p_id = cursor.getInt(cursor.getColumnIndex("P_ID"));
            String first_name = cursor.getString(cursor.getColumnIndex("FIRST_NAME"));
            String last_name = cursor.getString(cursor.getColumnIndex("LAST_NAME"));
            String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));

            buffer.append(p_id + " " + first_name + " " + last_name + " " + password + "\n");
        }
        return buffer.toString();
    }


    public String getPatientPassword(String email){

        SQLiteDatabase db = getReadableDatabase();
        Cursor result = db.query(
               "PATIENT_INFO",
                new String[] { "PASSWORD" },
                "EMAIL" + "=?",
                new String[] { String.valueOf(email) },
                null, //This parameter deals with grouping results. No need here, hence null.
                null, //Relates to the above. Also null.
                null //Orders results. There should just be one, so it's null here, but can be useful.
        );

        if (result.moveToFirst()){
            return result.getString(result.getColumnIndex("PASSWORD"));
        }
        else {
            return null;
        }

    }
}