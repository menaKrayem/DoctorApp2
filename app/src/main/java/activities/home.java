package activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.content.Intent;
import android.view.MenuItem;
import androidx.annotation.NonNull;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorapp.R;

import java.util.ArrayList;

import adapters.Specialization_Adapter;
import adapters.Tips_Adapter;
import api.Api_Manager;
import models.Specialization_Model;
import models.Tips_Model;

public class home extends AppCompatActivity {

    RecyclerView rv_sepc;
    RecyclerView rv_tips;
    ArrayList<Specialization_Model> specialization_modelArrayList; //obj
    ArrayList<Tips_Model> tips_modelArrayList; //obj

    Specialization_Adapter specialization_adapter;
    Tips_Adapter tips_adapter;
    SharedPreferences sh;
    String name;
    BottomNavigationView bottom_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        Api_Manager api_managerer = new Api_Manager(this);
        api_managerer.getHealthTips();

        //declare element
        ImageView top4 =findViewById(R.id.iv_top4);
        ImageView iv_notification = findViewById(R.id.iv_notification);
        TextView tv_myname = findViewById(R.id.tv_myname);
        tv_myname.setText(getSharedPreferences("UserPref", MODE_PRIVATE)
                .getString("userName", "Guest"));

        // فصل 2 sepcialize
         rv_sepc = findViewById(R.id.rv_specialize);
         specialization_modelArrayList= new ArrayList<>();
         specialization_modelArrayList.add(new Specialization_Model("Brain",R.drawable.brain));
         specialization_modelArrayList.add(new Specialization_Model("Eyes",R.drawable.eyes));
         specialization_modelArrayList.add(new Specialization_Model("Teeth",R.drawable.teeth));
         specialization_modelArrayList.add(new Specialization_Model("Lungs",R.drawable.lungs));
         specialization_modelArrayList.add(new Specialization_Model("Kidnys",R.drawable.kidnys));
         specialization_modelArrayList.add(new Specialization_Model("Intestine",R.drawable.intestine));

         specialization_adapter = new Specialization_Adapter(home.this, specialization_modelArrayList);
         rv_sepc.setHasFixedSize(true);
         rv_sepc.setLayoutManager(new LinearLayoutManager(home.this,RecyclerView.HORIZONTAL,false));
         rv_sepc.setAdapter(specialization_adapter);

         // tips
        rv_tips = findViewById(R.id.rv_tips);
        tips_modelArrayList= new ArrayList<>();
        tips_modelArrayList.add(new Tips_Model("https://images.pexels.com/photos/3511116/pexels-photo-3511116.jpeg","Smoking"));
        tips_modelArrayList.add(new Tips_Model("https://images.pexels.com/photos/3951628/pexels-photo-3951628.jpeg","Cough"));
        tips_modelArrayList.add(new Tips_Model("https://images.pexels.com/photos/3951883/pexels-photo-3951883.jpeg","Covid 19"));

        tips_adapter = new Tips_Adapter(home.this, tips_modelArrayList);
        rv_tips.setHasFixedSize(true);
        rv_tips.setLayoutManager(new LinearLayoutManager(home.this,RecyclerView.HORIZONTAL,false));
        rv_tips.setAdapter(tips_adapter);

        //event in image doctor
        top4.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View v) {
              Intent intent = new Intent(home.this, detalis_doctor.class);
              startActivity(intent);
            }
        });
        // event to notification
        iv_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, Notifications.class);
                startActivity(intent);
            }
        });
        bottom_navigation = findViewById(R.id.bottom_navigation);
        bottom_navigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_home) {
                    return true;
                } else if (id == R.id.nav_fav) {
                    startActivity(new Intent(home.this, Favorite.class));
                    return true;
                }else if(id== R.id.nav_calender){
                    startActivity(new Intent(home.this, appointement.class));
                    return true;
                } else if (id == R.id.nav_profile) {
                    startActivity(new Intent(home.this, profile.class));
                    return true;
                }
                return false;
            }
        });
        }
    }
