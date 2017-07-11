package net.shreygupta.doctoronthego.fragment;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import net.shreygupta.doctoronthego.DatabaseHelper;
import net.shreygupta.doctoronthego.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PatientBookAppointmentFragment extends Fragment {

    private TextView email;
    private TextView fname;
    private TextView lname;
    private Button submit;

    private EditText datepick;
    private EditText timepicker;
    private ListView doclist;
    private int p_id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_patient_book_appointment, container, false);

        email = v.findViewById(R.id.email);
        fname = v.findViewById(R.id.fname);
        lname = v.findViewById(R.id.lname);
        datepick = v.findViewById(R.id.datepicker);
        doclist = v.findViewById(R.id.doclist);
        timepicker = v.findViewById(R.id.timepicker);
        submit = v.findViewById(R.id.submit);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Resources res = getResources();
        String[] doctors = res.getStringArray(R.array.doctors);
        ArrayAdapter<String> adptr = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, doctors);
        doclist.setAdapter(adptr);

        final SharedPreferences sp = this.getActivity().getSharedPreferences("my_sp1", Context.MODE_PRIVATE);
        String patient_email = sp.getString("Patient_Email", null);

        DatabaseHelper db_h = new DatabaseHelper(getActivity());
        String Fname = db_h.getFname(patient_email);
        String Lname = db_h.getLname(patient_email);
        p_id = db_h.getPatientId(patient_email);
        fname.setText(Fname);
        lname.setText(Lname);
        email.setText(patient_email);

        timepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int dd = c.get(Calendar.DAY_OF_MONTH);
                int mm = c.get(Calendar.MONTH);
                int yy = c.get(Calendar.YEAR);
                int hh = c.get(Calendar.HOUR_OF_DAY);
                int mi = c.get(Calendar.MINUTE);

                TimePickerDialog tp = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = hourOfDay + " : "+ minute;
                        timepicker.setText(time);
                    }
                }, hh, mi, false);
                tp.show();
            }
        });

        datepick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int dd = c.get(Calendar.DAY_OF_MONTH);
                int mm = c.get(Calendar.MONTH);
                int yy = c.get(Calendar.YEAR);

                DatePickerDialog dp = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        String date = dayOfMonth+"/"+(month+1)+"/"+year;
                        datepick.setText(date);

                    }
                }, yy, mm, dd);
                dp.show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String email_data;
                String fname_data;
                String lname_data;
                String date_data;
                String time_data;
                int d_id = 1;

                email_data = email.getText().toString();
                fname_data = fname.getText().toString();
                lname_data = lname.getText().toString();
                date_data = datepick.getText().toString();
                time_data = timepicker.getText().toString();

                DatabaseHelper db_h = new DatabaseHelper(getActivity());
                db_h.Appointment_submit(date_data,time_data,p_id,d_id);
                String res = db_h.getAppointmentData();

                Toast.makeText(getActivity(),res, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
