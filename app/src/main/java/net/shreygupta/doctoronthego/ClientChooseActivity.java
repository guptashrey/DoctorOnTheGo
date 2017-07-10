package net.shreygupta.doctoronthego;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class ClientChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_choose);

        ImageButton patient_activity = (ImageButton) findViewById(R.id.patient_activity);
        ImageButton doctor_activity = (ImageButton) findViewById(R.id.doctor_activity);

        patient_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ClientChooseActivity.this, PatientActivity.class));
            }
        });

        doctor_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ClientChooseActivity.this, DoctorActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.client_choose_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.admin_activity) {
            startActivity(new Intent(ClientChooseActivity.this, AdminActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}