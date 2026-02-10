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

public class onbording1 extends AppCompatActivity {

    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_onbording1);

        //declear element
        TextView tv = findViewById(R.id.tv_skip);
        Button btnnext = findViewById(R.id.btn_next);

        //event التالي
        btnnext.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(onbording1.this, onbording2.class);

            @Override
            public void onClick(View v) {
                btnnext.setBackgroundColor(Color.LTGRAY);
                startActivity(intent);
            }
        });
        sh = getSharedPreferences("UserPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sh.edit();
        //event in skip
        tv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tv.setTextColor(getColor( R.color.blue));
                editor.putBoolean("isFirstTime", false);
                editor.apply();
                Intent intent = new Intent(onbording1.this, login.class);
                startActivity(intent);
                finish();

            }
        });
    }
}