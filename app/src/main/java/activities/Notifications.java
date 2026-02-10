package activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import DataBase.Notification_Favorite_Appoint_DB;
import adapters.Notification_Adapter;
import models.Notification_Model;

public class Notifications extends AppCompatActivity {
    BottomNavigationView bottom_navigation;
    RecyclerView rv_notifications;
    Notification_Favorite_Appoint_DB db;
    ArrayList<Notification_Model> notifList;
    Notification_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        rv_notifications = findViewById(R.id.rv_notifications);
        db = new Notification_Favorite_Appoint_DB(this);
        notifList = db.getAllNotification();
        adapter = new Notification_Adapter(this, notifList);
        rv_notifications.setLayoutManager(new LinearLayoutManager(this));
        rv_notifications.setAdapter(adapter);

        Swipe();

            bottom_navigation = findViewById(R.id.bottom_navigation);
            bottom_navigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int id = item.getItemId();

                    if (id == R.id.nav_home) {
                        startActivity(new Intent(Notifications.this, home.class));

                        return true;
                    } else if (id == R.id.nav_fav) {
                        startActivity(new Intent(Notifications.this, Favorite.class));
                        return true;
                    } else if (id == R.id.nav_calender) {
                        startActivity(new Intent(Notifications.this, appointement.class));
                        return true;
                    } else if (id == R.id.nav_profile) {
                        startActivity(new Intent(Notifications.this, profile.class));
                        return true;
                    }
                    return false;
                }
            });
        }

    private void Swipe() {
        ItemTouchHelper.SimpleCallback swipeHandler = new ItemTouchHelper.SimpleCallback
                (0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,@NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                 new AlertDialog.Builder(Notifications.this)
                         .setTitle("Confirm Delete?")
                         .setMessage("Are you sure you want to delete this notification?")
                         .setPositiveButton("delete", (dialog, which) -> {
                             Notification_Model itemToDelete = notifList.get(position);
                             boolean isSuccess = db.deleteNotification(itemToDelete.getId());
                             if (isSuccess) {
                                 notifList.remove(position);
                                 adapter.notifyItemRemoved(position);
                                 Toast.makeText(Notifications.this, "تم الحذف بنجاح", Toast.LENGTH_SHORT).show();
                             }
                         })
                         .setNegativeButton("cancle", (dialog, which) -> {
                             adapter.notifyItemChanged(position);
                         })
                         .setOnCancelListener(dialog -> {
                             adapter.notifyItemChanged(position);
                         })
                         .show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeHandler);
        itemTouchHelper.attachToRecyclerView(rv_notifications);
    }
    }