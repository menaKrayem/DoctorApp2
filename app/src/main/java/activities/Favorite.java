package activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import DataBase.Notification_Favorite_Appoint_DB;
import adapters.Tips_Adapter;
import models.Tips_Model;

public class Favorite extends AppCompatActivity {
    BottomNavigationView bottom_navigation;
    RecyclerView rv_favorite;
    Notification_Favorite_Appoint_DB db;
    ArrayList<Tips_Model> favList;
    Tips_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        rv_favorite = findViewById(R.id.rv_favorite);
        db = new Notification_Favorite_Appoint_DB(this);
        favList = db.getAllFavorite();
        adapter =new Tips_Adapter(this,favList);
        rv_favorite.setLayoutManager(new LinearLayoutManager(this));
        rv_favorite.setAdapter(adapter);

        //nav bar
        bottom_navigation = findViewById(R.id.bottom_navigation);
        bottom_navigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_fav) {
                    return true;
                } else if (id == R.id.nav_home) {
                    startActivity(new Intent(Favorite.this, home.class));
                    return true;
                } else if (id == R.id.nav_calender) {
                    startActivity(new Intent(Favorite.this, appointement.class));
                    return true;
                } else if (id == R.id.nav_profile) {
                    startActivity(new Intent(Favorite.this, profile.class));
                    return true;
                }
                return false;
            }
        });
    }
}