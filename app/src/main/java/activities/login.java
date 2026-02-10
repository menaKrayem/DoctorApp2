package activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.doctorapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    SharedPreferences sh;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        //declear element
        Button btn_start = findViewById(R.id.btn_start);
        TextView signup = findViewById(R.id.tv_signup);
        EditText et_userName = findViewById(R.id.et_userName);
        EditText et_emailAdd = findViewById(R.id.et_emailAdd);
        EditText et_pass = findViewById(R.id.et_pass);

        //event btn
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_emailAdd.getText().toString().isEmpty()) {
                    et_emailAdd.setError("البريد الإلكتروني مطلوب!");
                    return;
                }
                if (et_pass.getText().toString().isEmpty()) {
                    et_pass.setError("كلمة السر مطلوبة!");
                    return;
                }
                mAuth.signInWithEmailAndPassword(
                        et_emailAdd.getText().toString().trim(),
                        et_pass.getText().toString()
                ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            sh = getSharedPreferences("UserPref", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sh.edit();
                            editor.putString("userName",et_userName.getText().toString());

                            editor.putBoolean("isLoggedIn", true);
                            editor.apply();

                            btn_start.setBackgroundColor(Color.LTGRAY);
                            Intent intent = new Intent(login.this, home.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(login.this, "خطأ: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        //event Signup
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup.setTextColor(getColor(R.color.gray));
                Intent intent = new Intent(login.this, signup.class);
                startActivity(intent);
            }
        });
    }
}