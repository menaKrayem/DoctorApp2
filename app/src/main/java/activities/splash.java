package activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.doctorapp.R;

public class splash extends AppCompatActivity {
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        //declear element
        ImageView iv = findViewById(R.id.iv_logo);

        // time only in splash عن طريق الدالة بعد 3 ثواني
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //shared pref
                sh  = getSharedPreferences("UserPref",MODE_PRIVATE);
                boolean isFirstTime = sh.getBoolean("isFirstTime", true);
                boolean isLoggedIn = sh.getBoolean("isLoggedIn", false);
                Intent intent;

                if(isFirstTime) {
                    intent = new Intent(splash.this, onbording1.class);
                }else if(isLoggedIn) {
                     intent = new Intent(splash.this,home.class);
                }else {
                     intent = new Intent(splash.this, login.class);
                }
                startActivity(intent);
                finish();
            }
        },3000);
    }
}