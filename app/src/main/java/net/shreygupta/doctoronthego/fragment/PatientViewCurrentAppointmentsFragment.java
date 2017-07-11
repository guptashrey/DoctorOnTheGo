package net.shreygupta.doctoronthego.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import net.shreygupta.doctoronthego.CustomAdaptorDoctor;
import net.shreygupta.doctoronthego.DatabaseHelper;
import net.shreygupta.doctoronthego.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PatientViewCurrentAppointmentsFragment extends Fragment {

    // private TextView curr_appt;
    private ListView lv;

    public PatientViewCurrentAppointmentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_patient_view_current_appointments, container, false);
        //curr_appt = v.findViewById(R.id.curr_appt_tv);
        lv = v.findViewById(R.id.appointment_list);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final SharedPreferences sp = this.getActivity().getSharedPreferences("my_sp1", Context.MODE_PRIVATE);
        String patient_email = sp.getString("Patient_Email", null);

        DatabaseHelper db_h = new DatabaseHelper(getActivity());

        String[] name = db_h.getDoctorName(patient_email);
        String[] date = db_h.getDoctorDate(patient_email);
        String[] time = db_h.getDoctorTime(patient_email);

        CustomAdaptorDoctor adptrr = new CustomAdaptorDoctor(getActivity(), name, date, time);
        lv.setAdapter(adptrr);


        //curr_appt.setText(db_h.currAppointments_forpatient(db_h.getPatientId(patient_email)));
    }
}