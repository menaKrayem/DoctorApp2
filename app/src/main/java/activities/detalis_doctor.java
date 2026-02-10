package activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doctorapp.R;

import DataBase.Notification_Favorite_Appoint_DB;

public class detalis_doctor extends AppCompatActivity {

    Notification_Favorite_Appoint_DB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalis_doctor);

        db = new Notification_Favorite_Appoint_DB(this);
        //declar element
        ImageView back =findViewById(R.id.iv_back);
        Button booknow  = findViewById(R.id.btn_booknow);
        TextView tv_doctorName = findViewById(R.id.tv_doctorName);
        TextView tv_specializeName = findViewById(R.id.tv_specializeName);


        // event back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(detalis_doctor.this, home.class);
                startActivity(intent);
            }
        });

        // event toast
        booknow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String doctorName = tv_doctorName.getText().toString();
                String specName = tv_specializeName.getText().toString();
                SimpleDateFormat todayDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                SimpleDateFormat nowTime = new SimpleDateFormat("hh:mm a", Locale.getDefault());
                Date now = new Date();

                String date = todayDate.format(now);
                String time = nowTime.format(now);


                db.insertNotification(doctorName, "Your appointment has been confirmed. We are waiting for you.", date, time, R.drawable.detalis);
                db.insertAppointment(doctorName,specName, date, time, "Upcoming", R.drawable.detalis);

                    Toast.makeText(detalis_doctor.this, "تم الحجز واضافة اشعار!", Toast.LENGTH_LONG).show();
            }
        });
    }
}