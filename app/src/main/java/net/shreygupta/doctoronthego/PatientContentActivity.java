package net.shreygupta.doctoronthego;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import net.shreygupta.doctoronthego.fragment.PatientBookAppointmentFragment;
import net.shreygupta.doctoronthego.fragment.PatientProfileFragment;
import net.shreygupta.doctoronthego.fragment.PatientViewCurrentAppointmentsFragment;

public class PatientContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_content);

        //final DatabaseHelper db_h = new DatabaseHelper(this);

        TabLayout t = (TabLayout) findViewById(R.id.tab_patient_content);

        TabLayout.Tab profile = t.newTab();
        profile.setText("PROFILE");
        t.addTab(profile);

        TabLayout.Tab book_appt = t.newTab();
        book_appt.setText("BOOK AN APPOINTMENT");
        t.addTab(book_appt);

        TabLayout.Tab current_appt = t.newTab();
        current_appt.setText("VIEW APPOINTMENTS");
        t.addTab(current_appt);


        loadFragment(new PatientProfileFragment(), "Profile");

        t.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == 0){
                    loadFragment(new PatientProfileFragment(), "Profile");
                }

                if (tab.getPosition() == 1) {
                    loadFragment(new PatientBookAppointmentFragment(), "Book_appt");
                }

                if (tab.getPosition() == 2) {

                    //  db_h.updateAppointments();

                    loadFragment(new PatientViewCurrentAppointmentsFragment(), "View_appt");
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
        ft.replace(R.id.frame_patient_content, fragment, tag);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }
}