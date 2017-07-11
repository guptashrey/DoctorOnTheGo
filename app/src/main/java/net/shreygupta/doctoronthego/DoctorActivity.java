package net.shreygupta.doctoronthego;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import net.shreygupta.doctoronthego.fragment.DoctorSignInFragment;
import net.shreygupta.doctoronthego.fragment.DoctorSignUpFragment;

public class DoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        TabLayout t1 = (TabLayout) findViewById(R.id.tab_doctor);

        TabLayout.Tab signup_doctor = t1.newTab();
        signup_doctor.setText("SIGN UP");
        t1.addTab(signup_doctor);

        TabLayout.Tab signin_doctor = t1.newTab();
        signin_doctor.setText("SIGN IN");
        t1.addTab(signin_doctor);

        signin_doctor.select();

        loadFragment(new DoctorSignInFragment(), "SignUp");

        t1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == 0) {
                    loadFragment(new DoctorSignUpFragment(), "SignUp");
                }

                if (tab.getPosition() == 1) {
                    loadFragment(new DoctorSignInFragment(), "SignIn");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void loadFragment(Fragment fragment, String tag) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_doctor, fragment, tag);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }
}
