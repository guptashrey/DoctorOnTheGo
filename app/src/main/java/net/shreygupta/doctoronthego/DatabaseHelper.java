package net.shreygupta.doctoronthego;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Patient DatabaseHelper";
    private static final int VERSION = 1;
    private static final String CREATE_TABLE_PATIENT = "CREATE TABLE PATIENT_INFO(P_ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRST_NAME VARCHAR(20), LAST_NAME VARCHAR(20), EMAIL VARCHAR(50), PASSWORD VARCHAR(20));";
    private static final String CREATE_TABLE_DOCTOR = "CREATE TABLE DOCTOR_INFO(D_ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRST_NAME VARCHAR(20), LAST_NAME VARCHAR(20), EMAIL VARCHAR(50), SPECIALITY VARCHAR(20), EXPERIENCE VARCHAR(20), PASSWORD VARCHAR(20));";
    private static final String CREATE_TABLE_APPOINTMENT = "CREATE TABLE APPOINTMENT_INFO(A_ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE DATE, TIME TIME,P_ID INTEGER, D_ID INTEGER ,FOREIGN KEY (P_ID) REFERENCES PATIENT_INFO(P_ID), FOREIGN KEY (D_ID) REFERENCES DOCTOR_INFO(D_ID));";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_PATIENT);
        sqLiteDatabase.execSQL(CREATE_TABLE_DOCTOR);
        sqLiteDatabase.execSQL(CREATE_TABLE_APPOINTMENT);

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

        return db.insert("PATIENT_INFO", null, contentValues);
    }

    public String getData() {
        SQLiteDatabase db = getReadableDatabase();
        String [] columns = {"P_ID", "FIRST_NAME", "LAST_NAME", "EMAIL", "PASSWORD"};

        Cursor cursor = db.query("PATIENT_INFO", columns, null, null, null, null, null);
        StringBuilder buffer = new StringBuilder();

        while (cursor.moveToNext()) {

            int p_id = cursor.getInt(cursor.getColumnIndex("P_ID"));
            String first_name = cursor.getString(cursor.getColumnIndex("FIRST_NAME"));
            String last_name = cursor.getString(cursor.getColumnIndex("LAST_NAME"));
            String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));

            buffer.append(p_id).append(" ").append(first_name).append(" ").append(last_name).append(" ").append(password).append("\n");
        }
        cursor.close();
        return buffer.toString();
    }

    public int getPatientId(String email){

        SQLiteDatabase db = getReadableDatabase();
        Cursor result = db.query(
                "PATIENT_INFO",
                new String[] { "P_ID" },
                "EMAIL" + "=?",
                new String[] { String.valueOf(email) },
                null, //This parameter deals with grouping results. No need here, hence null.
                null, //Relates to the above. Also null.
                null //Orders results. There should just be one, so it's null here, but can be useful.
        );

        if (result.moveToFirst()){
            int s = result.getInt(result.getColumnIndex("P_ID"));
            result.close();
            return s;
        }
        else {
            result.close();
            return -1;
        }

    }

    public String getFname(String email) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor result = db.query(
                "PATIENT_INFO",
                new String[] { "FIRST_NAME" },
                "EMAIL" + "=?",
                new String[] { String.valueOf(email) },
                null, //This parameter deals with grouping results. No need here, hence null.
                null, //Relates to the above. Also null.
                null //Orders results. There should just be one, so it's null here, but can be useful.
        );

        if (result.moveToFirst()){

            String s = result.getString(result.getColumnIndex("FIRST_NAME"));
            result.close();
            return s;
        }
        else {
            result.close();
            return null;
        }
    }

    public String getLname(String email) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor result = db.query(
                "PATIENT_INFO",
                new String[] { "LAST_NAME" },
                "EMAIL" + "=?",
                new String[] { String.valueOf(email) },
                null, //This parameter deals with grouping results. No need here, hence null.
                null, //Relates to the above. Also null.
                null //Orders results. There should just be one, so it's null here, but can be useful.
        );

        if (result.moveToFirst()){
            String s = result.getString(result.getColumnIndex("LAST_NAME"));
            result.close();
            return s;
        }
        else {
            result.close();
            return null;
        }
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
            String s = result.getString(result.getColumnIndex("PASSWORD"));
            result.close();
            return s;
        }
        else {
            result.close();
            return null;
        }

    }

    public long doctor_insert_Data(String a, String b, String c, String d, String e, String f) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("FIRST_NAME", a);
        contentValues.put("LAST_NAME", b);
        contentValues.put("EMAIL", c);
        contentValues.put("SPECIALITY", d);
        contentValues.put("EXPERIENCE", e);
        contentValues.put("PASSWORD", f);

        return db.insert("DOCTOR_INFO", null, contentValues);
    }



    public String getDoctorPassword(String email){

        SQLiteDatabase db = getReadableDatabase();
        Cursor result = db.query(
                "DOCTOR_INFO",
                new String[] { "PASSWORD" },
                "EMAIL" + "=?",
                new String[] { String.valueOf(email) },
                null, //This parameter deals with grouping results. No need here, hence null.
                null, //Relates to the above. Also null.
                null //Orders results. There should just be one, so it's null here, but can be useful.
        );

        if (result.moveToFirst()){
            String s = result.getString(result.getColumnIndex("PASSWORD"));
            result.close();
            return s;
        }
        else {
            result.close();
            return null;
        }
    }

    public int getDoctorId(String name) {

        String[] parts = name.split(" ");
        SQLiteDatabase db = getReadableDatabase();
        Cursor result = db.query(
                "DOCTOR_INFO",
                new String[]{"D_ID"},
                "FIRST_NAME" + "=?",
                new String[]{String.valueOf(parts[0])},
                null, //This parameter deals with grouping results. No need here, hence null.
                null, //Relates to the above. Also null.
                null //Orders results. There should just be one, so it's null here, but can be useful.
        );

        if (result.moveToFirst()) {
            int s = result.getInt(result.getColumnIndex("D_ID"));
            result.close();
            return s;
        } else {
            result.close();
            return -1;
        }

    }


    public int getDoctorId_from_email(String email) {

        SQLiteDatabase db = getReadableDatabase();
        Cursor result = db.query(
                "DOCTOR_INFO",
                new String[]{"D_ID"},
                "EMAIL" + "=?",
                new String[]{String.valueOf(email)},
                null, //This parameter deals with grouping results. No need here, hence null.
                null, //Relates to the above. Also null.
                null //Orders results. There should just be one, so it's null here, but can be useful.
        );

        if (result.moveToFirst()) {
            int s = result.getInt(result.getColumnIndex("D_ID"));
            result.close();
            return s;
        } else {
            result.close();
            return -1;
        }

    }


    public long Appointment_submit(String date, String time, int p_id, int d_id) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("TIME", time);
        contentValues.put("P_ID", p_id);
        contentValues.put("D_ID", d_id);

        return db.insert("APPOINTMENT_INFO", null, contentValues);
    }

    public String getAppointmentData() {
        SQLiteDatabase db = getReadableDatabase();
        String [] columns = {"A_ID", "DATE", "TIME", "P_ID", "D_ID"};

        Cursor cursor = db.query("APPOINTMENT_INFO", columns, null, null, null, null, null);
        StringBuilder buffer = new StringBuilder();

        while (cursor.moveToNext()) {

            int a_id = cursor.getInt(cursor.getColumnIndex("A_ID"));
            String date = cursor.getString(cursor.getColumnIndex("DATE"));
            String time = cursor.getString(cursor.getColumnIndex("TIME"));
            int p_id = cursor.getInt(cursor.getColumnIndex("P_ID"));
            int d_id = cursor.getInt(cursor.getColumnIndex("D_ID"));
            buffer.append(a_id).append(" ").append(date).append(" ").append(time).append(" ").append(p_id).append(" ").append(d_id).append("\n");
        }
        cursor.close();
        return buffer.toString();
    }

    public String currAppointments(int pid) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                "APPOINTMENT_INFO",
                new String[]{"A_ID", "P_ID", "D_ID", "DATE", "TIME"},
                "P_ID" + "=?",
                new String[]{String.valueOf(pid)},
                null, //This parameter deals with grouping results. No need here, hence null.
                null, //Relates to the above. Also null.
                null //Orders results. There should just be one, so it's null here, but can be useful.
        );

        StringBuilder buffer = new StringBuilder();

        while (cursor.moveToNext()) {

            int a_id = cursor.getInt(cursor.getColumnIndex("A_ID"));
            int p_id = cursor.getInt(cursor.getColumnIndex("P_ID"));
            int d_id = cursor.getInt(cursor.getColumnIndex("D_ID"));
            String date = cursor.getString(cursor.getColumnIndex("DATE"));
            String time = cursor.getString(cursor.getColumnIndex("TIME"));

            buffer.append(a_id).append(" ").append(p_id).append(" ").append(d_id).append(" ").append(date).append(" ").append(time).append("\n");
        }
        cursor.close();
        return buffer.toString();
    }

    public String currAppointments_forpatient(int pid) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT DOCTOR_INFO.FIRST_NAME,DOCTOR_INFO.LAST_NAME,DATE,TIME FROM PATIENT_INFO,DOCTOR_INFO,APPOINTMENT_INFO WHERE PATIENT_INFO.P_ID" + "=?" + " AND DOCTOR_INFO.D_ID = APPOINTMENT_INFO.D_ID AND PATIENT_INFO.P_ID = APPOINTMENT_INFO.P_ID;", new String[]{String.valueOf(pid)});

        StringBuilder buffer = new StringBuilder();

        while (cursor.moveToNext()) {

            String fname = cursor.getString(cursor.getColumnIndex("FIRST_NAME"));
            String lname = cursor.getString(cursor.getColumnIndex("LAST_NAME"));
            String date = cursor.getString(cursor.getColumnIndex("DATE"));
            String time = cursor.getString(cursor.getColumnIndex("TIME"));

            buffer.append(fname).append(" ").append(lname).append(" ").append(date).append(" ").append(time).append("\n");
        }
        cursor.close();
        return buffer.toString();
    }


    public String currAppointments_fordoctor(int did) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT PATIENT_INFO.FIRST_NAME,PATIENT_INFO.LAST_NAME,DATE,TIME FROM PATIENT_INFO,DOCTOR_INFO,APPOINTMENT_INFO WHERE DOCTOR_INFO.D_ID" + "=?" + " AND PATIENT_INFO.P_ID = APPOINTMENT_INFO.P_ID AND DOCTOR_INFO.D_ID = APPOINTMENT_INFO.D_ID;", new String[]{String.valueOf(did)});

        StringBuilder buffer = new StringBuilder();

        while (cursor.moveToNext()) {

            String fname = cursor.getString(cursor.getColumnIndex("FIRST_NAME"));
            String lname = cursor.getString(cursor.getColumnIndex("LAST_NAME"));
            String date = cursor.getString(cursor.getColumnIndex("DATE"));
            String time = cursor.getString(cursor.getColumnIndex("TIME"));

            buffer.append(fname).append(" ").append(lname).append(" ").append(date).append(" ").append(time).append("\n");
        }
        cursor.close();
        return buffer.toString();
    }









    public String getDoctorProfile(String email) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                "DOCTOR_INFO",
                new String[]{"D_ID", "FIRST_NAME", "LAST_NAME", "SPECIALITY", "EXPERIENCE"},
                "EMAIL" + "=?",
                new String[]{String.valueOf(email)},
                null, //This parameter deals with grouping results. No need here, hence null.
                null, //Relates to the above. Also null.
                null //Orders results. There should just be one, so it's null here, but can be useful.
        );

        StringBuilder buffer = new StringBuilder();

        if (cursor.moveToFirst()) {
            int d_id = cursor.getInt(cursor.getColumnIndex("D_ID"));
            String fname = cursor.getString(cursor.getColumnIndex("FIRST_NAME"));
            String lname = cursor.getString(cursor.getColumnIndex("LAST_NAME"));
            String speciality = cursor.getString(cursor.getColumnIndex("SPECIALITY"));
            String exp = cursor.getString(cursor.getColumnIndex("EXPERIENCE"));

            buffer.append(d_id).append(" ").append(fname).append(" ").append(lname).append(" ").append(speciality).append(" ").append(exp).append("\n");
            cursor.close();
            return buffer.toString();
        } else {
            cursor.close();
            return null;
        }
    }

    public String[] selectdoctorlist(String speciality) {
        String name;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                "DOCTOR_INFO",
                new String[]{"FIRST_NAME", "LAST_NAME"},
                "SPECIALITY" + "=?",
                new String[]{String.valueOf(speciality)},
                null, //This parameter deals with grouping results. No need here, hence null.
                null, //Relates to the above. Also null.
                null //Orders results. There should just be one, so it's null here, but can be useful.
        );

        int i = cursor.getCount();

        String[] doclist;
        if (i == 0) {
            doclist = new String[]{"Select Another Speciality"};
        } else {
            doclist = new String[i];

            i = 0;

            while (cursor.moveToNext()) {
                String fname = cursor.getString(cursor.getColumnIndex("FIRST_NAME"));
                String lname = cursor.getString(cursor.getColumnIndex("LAST_NAME"));
                name = fname + " " + lname;
                doclist[i] = name;
                i++;
            }
        }

        cursor.close();
        return doclist;
    }

    public void updateAppointments() {
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("DELETE FROM APPOINTMENT_INFO WHERE DATE < DATE();");
    }
}