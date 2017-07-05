package net.shreygupta.doctoronthego;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout t = (TabLayout) findViewById(R.id.tab);

        TabLayout.Tab signup = t.newTab();
        signup.setText("SIGN UP");
        t.addTab(signup);

        TabLayout.Tab signin = t.newTab();
        signin.setText("SIGN IN");
        t.addTab(signin);

        loadFragment(new SignUpFragment(), "SignUp");

        t.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == 0){
                    loadFragment(new SignUpFragment(), "SignUp");
                }

                if (tab.getPosition() == 1) {
                    loadFragment(new SignInFragment(), "SignIn");
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
        ft.replace(R.id.frame, fragment, tag);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }
}