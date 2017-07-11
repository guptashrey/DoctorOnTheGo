package net.shreygupta.doctoronthego.fragment;


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
import android.widget.Toast;

import net.shreygupta.doctoronthego.DatabaseHelper;
import net.shreygupta.doctoronthego.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorSignUpFragment extends Fragment {

    private final String[] d = new String[1];
    private final String[] e = new String[1];
    private EditText first_name;
    private EditText last_name;
    private EditText email;
    private EditText password;
    private EditText con_password;
    private Button doctor_signup_button;
    private Spinner speciality;
    private Spinner experience;

    public DoctorSignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_doctor_sign_up, container, false);

        first_name = v.findViewById(R.id.doctor_reg_first_name);
        last_name = v.findViewById(R.id.doctor_reg_last_name);
        email = v.findViewById(R.id.doctor_reg_email);
        speciality = v.findViewById(R.id.speciality);
        experience = v.findViewById(R.id.experience);
        password = v.findViewById(R.id.doctor_reg_password);
        con_password = v.findViewById(R.id.doctor_reg_con_password);


        doctor_signup_button = v.findViewById(R.id.doctor_reg_sign_up);


        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.specialities, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        speciality.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(),
                R.array.experience, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        experience.setAdapter(adapter1);

        speciality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv = (TextView) view;
                d[0] = tv.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        experience.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv = (TextView) view;
                e[0] = tv.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        doctor_signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doctor_signup();
            }
        });

        super.onActivityCreated(savedInstanceState);
    }

    private void doctor_signup() {

        String a = first_name.getText().toString();
        String b = last_name.getText().toString();
        String c = email.getText().toString();
        String f = password.getText().toString();
        String g = con_password.getText().toString();

        DatabaseHelper db_h = new DatabaseHelper(getActivity());
        long id = db_h.doctor_insert_Data(a, b, c, d[0], e[0], f);

        if (id <= 0) {

            Toast.makeText(getActivity(), "Signup Unsuccessful.", Toast.LENGTH_SHORT).show();

            first_name.setText("");
            last_name.setText("");
            email.setText("");
            password.setText("");
            con_password.setText("");

        }

        else {

            Toast.makeText(getActivity(), "Signup Successful.", Toast.LENGTH_SHORT).show();

            first_name.setText("");
            last_name.setText("");
            email.setText("");
            password.setText("");
            con_password.setText("");
        }
    }
}