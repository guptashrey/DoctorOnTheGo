package net.shreygupta.doctoronthego.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import net.shreygupta.doctoronthego.PatientContentActivity;
import net.shreygupta.doctoronthego.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PatientSignInFragment extends Fragment {

    private EditText email;
    private EditText password;

    private Button patient_signin_button;

    public PatientSignInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_patient_sign_in, container, false);

        patient_signin_button = v.findViewById(R.id.patient_sign_in);

        email = v.findViewById(R.id.patient_signin_email);
        password = v.findViewById(R.id.patient_signin_password);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        patient_signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                patient_signin();
            }
        });



        super.onActivityCreated(savedInstanceState);
    }

    private void patient_signin() {

        String a = email.getText().toString();
        String b = password.getText().toString();
        DatabaseHelper db_h = new DatabaseHelper(getActivity());

        String c = db_h.getPatientPassword(a);
        if(c == null){
            Toast.makeText(getActivity(), "User Does Not Exist !", Toast.LENGTH_SHORT).show();
        }
        else{
            if(b.equals(c)){
                SharedPreferences sp = this.getActivity().getSharedPreferences("my_sp1", Context.MODE_PRIVATE);
                SharedPreferences.Editor e = sp.edit();
                e.putString("Patient_Email", a);
                e.apply();

                startActivity(new Intent(this.getActivity(),PatientContentActivity.class));
            }
            else{
                Toast.makeText(getActivity(), "Incorrect password", Toast.LENGTH_SHORT).show();
            }
        }



    }


}