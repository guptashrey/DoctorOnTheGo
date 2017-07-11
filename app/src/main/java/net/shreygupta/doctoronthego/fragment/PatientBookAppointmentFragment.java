package net.shreygupta.doctoronthego.fragment;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
    private int p_id;

    private Spinner selectspeciality;
    private Spinner selectdoctor;
    private String selected_doctor;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_patient_book_appointment, container, false);

        email = v.findViewById(R.id.email);
        fname = v.findViewById(R.id.fname);
        lname = v.findViewById(R.id.lname);
        datepick = v.findViewById(R.id.datepicker);
        timepicker = v.findViewById(R.id.timepicker);
        submit = v.findViewById(R.id.submit);

        selectdoctor = v.findViewById(R.id.selectdoctor);
        selectspeciality = v.findViewById(R.id.selectspeciality);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        final DatabaseHelper db_h = new DatabaseHelper(getActivity());
//        Resources res = getResources();
//        String[] doctors = res.getStringArray(R.array.doctors);
//        ArrayAdapter<String> adptr = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, doctors);
//        doclist.setAdapter(adptr);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.specialities, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectspeciality.setAdapter(adapter);

        selectspeciality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv = (TextView) view;
                String spc = tv.getText().toString();
                String[] Doclist = db_h.selectdoctorlist(spc);


                ArrayAdapter<String> adptr1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, Doclist);
                selectdoctor.setAdapter(adptr1);

                selectdoctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        TextView tv1 = (TextView) view;
                        selected_doctor = tv1.getText().toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        String[] arr = db_h.selectdoctorlist("Dermatologist");
//        Toast.makeText(getActivity(), arr[0], Toast.LENGTH_SHORT).show();

        final SharedPreferences sp = this.getActivity().getSharedPreferences("my_sp1", Context.MODE_PRIVATE);
        String patient_email = sp.getString("Patient_Email", null);


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
                int hh = c.get(Calendar.HOUR_OF_DAY);
                int mi = c.get(Calendar.MINUTE);

                TimePickerDialog tp = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = hourOfDay + ":" + minute;
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
                        String date = year + "-" + (month + 1) + "-" + dayOfMonth;
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
                int d_id = db_h.getDoctorId(selected_doctor);

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