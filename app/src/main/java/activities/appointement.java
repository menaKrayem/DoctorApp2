package activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorapp.R;

import java.util.ArrayList;

import DataBase.Notification_Favorite_Appoint_DB;
import adapters.Appoint_Adapter;
import models.Appoint_Model;

public class appointement extends AppCompatActivity {
    RecyclerView rv_appoint;
    Appoint_Adapter adapter;
    ArrayList<Appoint_Model> doctorlist;
    Notification_Favorite_Appoint_DB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_appointement);
        // dclare elements
        rv_appoint = findViewById(R.id.rv_appoint);
        TextView tv_upcoming = findViewById(R.id.tv_upcoming);
        TextView tv_cancelled = findViewById(R.id.tv_cancelled);
        ImageView back = findViewById(R.id.iv_back);

        db = new Notification_Favorite_Appoint_DB(this);

        rv_appoint.setLayoutManager(new LinearLayoutManager(this));
        displayData("Upcoming");

        tv_upcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayData("Upcoming");
                tv_upcoming.setTextColor(Color.WHITE);
                tv_upcoming.setBackgroundResource(R.drawable.border);
            }
        });

        tv_cancelled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayData("Cancelled");
                tv_cancelled.setTextColor(Color.WHITE);
                tv_cancelled.setBackgroundResource(R.drawable.border_notif);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(appointement.this, home.class);
                startActivity(intent);
            }
        });
    }
    private void displayData(String status) {
        doctorlist = db.getAppointState(status);
        adapter = new Appoint_Adapter(this, doctorlist);
        rv_appoint.setAdapter(adapter);
    }
}