package net.shreygupta.doctoronthego;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class PatientSignInFragment extends Fragment {

    EditText email, password;

    Button patient_signin_button;
    DatabaseHelper db_h;

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

    public void patient_signin() {

        String a = email.getText().toString();
        String b = email.getText().toString();
        db_h = new DatabaseHelper(getActivity());
        String data = db_h.getData();
        Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();

    }
}