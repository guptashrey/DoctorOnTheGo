package net.shreygupta.doctoronthego.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import net.shreygupta.doctoronthego.DatabaseHelper;
import net.shreygupta.doctoronthego.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class PatientProfileFragment extends Fragment {

    DatabaseHelper db_h;

    TextView email;
    TextView fname;
    TextView lname;
    String patient_email;

    public PatientProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_patient_profile, container, false);
        email = v.findViewById(R.id.email1);
        fname = v.findViewById(R.id.fname1);
        lname = v.findViewById(R.id.lname1);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final SharedPreferences sp = this.getActivity().getSharedPreferences("my_sp1", Context.MODE_PRIVATE);
        patient_email = sp.getString("Patient_Email", null);

        db_h = new DatabaseHelper(getActivity());
        String Fname = db_h.getFname(patient_email);
        String Lname = db_h.getLname(patient_email);

        fname.setText(Fname);
        lname.setText(Lname);
        email.setText(patient_email);
    }
}
