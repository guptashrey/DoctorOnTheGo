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

import net.shreygupta.doctoronthego.DatabaseHelper;
import net.shreygupta.doctoronthego.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorViewCurrentAppointmentsFragment extends Fragment {

    TextView curr_app_tv;

    public DoctorViewCurrentAppointmentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_doctor_view_current_appointments, container, false);
        curr_app_tv = v.findViewById(R.id.curr_appt_tv);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SharedPreferences sp = this.getActivity().getSharedPreferences("my_sp1", Context.MODE_PRIVATE);
        String doctor_email = sp.getString("Doctor_Email", null);

        DatabaseHelper db_h = new DatabaseHelper(getActivity());

        curr_app_tv.setText(db_h.currAppointments_fordoctor(db_h.getDoctorId_from_email(doctor_email)));


    }
}