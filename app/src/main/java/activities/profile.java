package activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.doctorapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class profile extends AppCompatActivity {
    SharedPreferences sh;
    private FirebaseAuth mAuth;
    BottomNavigationView bottom_navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();

        //declar element
        Button btn_logout = findViewById(R.id.btn_logout);
        Button editProfile = findViewById(R.id.btn_editprofile);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                sh = getSharedPreferences("UserPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = sh.edit();
                editor.putBoolean("isLoggedIn", false);
                editor.remove("userName");
                editor.apply();
                Intent intent = new Intent(profile.this, login.class);
                startActivity(intent);
                finish();
                Toast.makeText(profile.this, "تم تسجيل الخروج", Toast.LENGTH_SHORT).show();
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, EditProfile.class);
                startActivity(intent);
            }
        });

        bottom_navigation = findViewById(R.id.bottom_navigation);
        bottom_navigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_profile) {
                    return true;
                } else if (id == R.id.nav_fav) {
                    startActivity(new Intent(profile.this, Favorite.class));
                    return true;
                }else if(id== R.id.nav_calender){
                    startActivity(new Intent(profile.this, appointement.class));
                    return true;
                } else if (id == R.id.nav_home) {
                    startActivity(new Intent(profile.this, home.class));
                    return true;
                }
                return false;
            }
        });
    }
}