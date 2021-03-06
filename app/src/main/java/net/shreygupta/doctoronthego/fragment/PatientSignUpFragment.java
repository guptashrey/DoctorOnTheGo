package net.shreygupta.doctoronthego.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.shreygupta.doctoronthego.DatabaseHelper;
import net.shreygupta.doctoronthego.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PatientSignUpFragment extends Fragment {

    private EditText first_name;
    private EditText last_name;
    private EditText email;
    private EditText password;
    private EditText con_password;
    private Button patient_signup_button;

    public PatientSignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_patient_sign_up, container, false);

        first_name = v.findViewById(R.id.patient_reg_first_name);
        last_name = v.findViewById(R.id.patient_reg_last_name);
        email = v.findViewById(R.id.patient_reg_email);
        password = v.findViewById(R.id.patient_reg_password);
        con_password = v.findViewById(R.id.patient_reg_con_pass);

        patient_signup_button = v.findViewById(R.id.patient_reg_sign_up);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        patient_signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                patient_signup();
            }
        });

        super.onActivityCreated(savedInstanceState);
    }

    private void patient_signup() {

        String a = first_name.getText().toString();
        String b = last_name.getText().toString();
        String c = email.getText().toString();
        String d = password.getText().toString();
        String e = con_password.getText().toString();

        if (d.equals(e)) {
            DatabaseHelper db_h = new DatabaseHelper(getActivity());
            Long id = db_h.patient_insert_Data(a, b, c, d);

            if (id <= 0) {

                Toast.makeText(getActivity(), "Signup Unsuccessful.", Toast.LENGTH_SHORT).show();

                first_name.setText("");
                last_name.setText("");
                email.setText("");
                password.setText("");
                con_password.setText("");

            } else {

                Toast.makeText(getActivity(), "Signup Successful.", Toast.LENGTH_SHORT).show();

                first_name.setText("");
                last_name.setText("");
                email.setText("");
                password.setText("");
                con_password.setText("");
            }
        } else {
            Toast.makeText(getActivity(), "Password Mismatch!", Toast.LENGTH_SHORT).show();
        }
    }
}