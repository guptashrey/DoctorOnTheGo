package net.shreygupta.doctoronthego;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdminSignInFragment extends Fragment {

    private EditText et_admin_email;
    private EditText et_admin_pass;
    private Button admin_signin;
    private String admin_email;
    private String admin_pass;

    public AdminSignInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_admin_sign_in, container, false);
        et_admin_email = v.findViewById(R.id.admin_email);
        et_admin_pass = v.findViewById(R.id.admin_pass);
        admin_signin = v.findViewById(R.id.admin_signin_button);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final SharedPreferences sp = this.getActivity().getSharedPreferences("my_sp", Context.MODE_PRIVATE);
        admin_email = sp.getString("Admin_Email", null);
        admin_pass = sp.getString("Admin_Password", null);

        admin_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Objects.equals(admin_email, et_admin_email.getText().toString())) {
                    if (Objects.equals(admin_pass, et_admin_pass.getText().toString())) {
                        loadFragment(new BlankFragment());
                    }

                    else {
                        Toast.makeText(getActivity(), "Invalid Login Credentials", Toast.LENGTH_SHORT).show();
                    }
                }

                else {
                    Toast.makeText(getActivity(), "Invalid Login Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadFragment(Fragment fragment){

        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_admin, fragment, "Blank");
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }
}