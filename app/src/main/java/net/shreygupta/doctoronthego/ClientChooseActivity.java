package net.shreygupta.doctoronthego;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClientChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_choose);

        Button patient_activity = (Button) findViewById(R.id.patient_activity);
        Button doctor_activity = (Button) findViewById(R.id.doctor_activity);
        Button admin_activity = (Button) findViewById(R.id.admin_activity);

        patient_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ClientChooseActivity.this,PatientActivity.class));
            }
        });

        doctor_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ClientChooseActivity.this,DoctorActivity.class));
            }
        });

        admin_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ClientChooseActivity.this,AdminActivity.class));
            }
        });
    }
}
