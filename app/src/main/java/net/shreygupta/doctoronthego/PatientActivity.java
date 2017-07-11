package net.shreygupta.doctoronthego;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import net.shreygupta.doctoronthego.fragment.PatientSignInFragment;
import net.shreygupta.doctoronthego.fragment.PatientSignUpFragment;

public class PatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        TabLayout t = (TabLayout) findViewById(R.id.tab);

        TabLayout.Tab signup = t.newTab();
        signup.setText("SIGN UP");
        t.addTab(signup);

        TabLayout.Tab signin = t.newTab();
        signin.setText("SIGN IN");
        t.addTab(signin);

        signin.select();

        loadFragment(new PatientSignInFragment(), "SignUp");

        t.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == 0) {
                    loadFragment(new PatientSignUpFragment(), "SignUp");
                }

                if (tab.getPosition() == 1) {
                    loadFragment(new PatientSignInFragment(), "SignIn");
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
        ft.replace(R.id.frame, fragment, tag);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }
}