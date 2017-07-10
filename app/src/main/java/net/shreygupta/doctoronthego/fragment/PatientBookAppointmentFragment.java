package net.shreygupta.doctoronthego.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import net.shreygupta.doctoronthego.DatabaseHelper;
import net.shreygupta.doctoronthego.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PatientBookAppointmentFragment extends Fragment {

    DatabaseHelper db_h;
    String []doctors;
    TextView email;
    TextView fname;
    TextView lname;
    String patient_email;
    ListView doclist;
    ArrayAdapter<String> adptr;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_patient_book_appointment, container, false);

        email = v.findViewById(R.id.email);
        fname = v.findViewById(R.id.fname);
        lname = v.findViewById(R.id.lname);

        doclist = v.findViewById(R.id.doclist);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Resources res = getResources();
        doctors = res.getStringArray(R.array.doctors);
        adptr = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,doctors);
        doclist.setAdapter(adptr);

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
