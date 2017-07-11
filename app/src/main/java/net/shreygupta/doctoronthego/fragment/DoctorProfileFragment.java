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
public class DoctorProfileFragment extends Fragment {

    private TextView profile;


    public DoctorProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_doctor_profile, container, false);
        profile = v.findViewById(R.id.doctor_profile_tv);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final SharedPreferences sp = this.getActivity().getSharedPreferences("my_sp1", Context.MODE_PRIVATE);
        String doctor_email = sp.getString("Doctor_Email", null);

        DatabaseHelper db_h = new DatabaseHelper(getActivity());
        String data = db_h.getDoctorProfile(doctor_email);

        profile.setText(data);
    }
}