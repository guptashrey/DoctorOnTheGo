package net.shreygupta.doctoronthego.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.shreygupta.doctoronthego.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PatientViewCurrentAppointmentsFragment extends Fragment {


    public PatientViewCurrentAppointmentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_patient_view_current_appointments, container, false);
    }

}
