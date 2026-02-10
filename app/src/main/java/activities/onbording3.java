package activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.doctorapp.R;

public class onbording3 extends AppCompatActivity {

    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_onbording3);

        //declear element
        TextView tv = findViewById(R.id.tv_skip);
        Button btnStart = findViewById(R.id.btn_Getstart);

        sh = getSharedPreferences("UserPref", MODE_PRIVATE);

        //event ابدا الان
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStart.setBackgroundColor(Color.LTGRAY);
                completeOnboarding();
            }
        });
        //event in skip
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setTextColor(getColor( R.color.blue));
                completeOnboarding();
            }
        });
    }

    private void completeOnboarding(){
        SharedPreferences.Editor editor = sh.edit();
        editor.putBoolean("isFirstTime",false);
        editor.apply();
        Intent intent = new Intent(onbording3.this, login.class);
        startActivity(intent);
        finish();
    }
}