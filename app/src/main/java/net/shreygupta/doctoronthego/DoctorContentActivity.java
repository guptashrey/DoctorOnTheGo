package net.shreygupta.doctoronthego;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import net.shreygupta.doctoronthego.fragment.DoctorProfileFragment;
import net.shreygupta.doctoronthego.fragment.DoctorViewCurrentAppointmentsFragment;

public class DoctorContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_content);

        TabLayout t = (TabLayout) findViewById(R.id.tab_doctor_content);

        TabLayout.Tab profile = t.newTab();
        profile.setText("PROFILE");
        t.addTab(profile);

        TabLayout.Tab view_appt = t.newTab();
        view_appt.setText("VIEW APPOINTMENTS");
        t.addTab(view_appt);

        loadFragment(new DoctorProfileFragment(), "Profile");

        t.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == 0){
                    loadFragment(new DoctorProfileFragment(), "Profile");
                }


                if (tab.getPosition() == 1) {
                    loadFragment(new DoctorViewCurrentAppointmentsFragment(), "View_appt");
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

    private void loadFragment(Fragment fragment, String tag){

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_doctor_content, fragment, tag);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }
}