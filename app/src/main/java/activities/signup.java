package activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class signup extends AppCompatActivity {
    SharedPreferences sh;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        //declear element
        Button signup = findViewById(R.id.btn_signup);
        EditText et_fullname  = findViewById(R.id.et_fullname);
        EditText et_pass = findViewById(R.id.et_pass);
        EditText et_emailAdd = findViewById(R.id.et_emailAdd);
        EditText et_confpass = findViewById(R.id.et_confpass);
        //event btn
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_emailAdd.getText().toString().isEmpty()) {
                    et_emailAdd.setError("الإيميل مطلوب!");
                    return;
                }
                if (et_pass.getText().toString().isEmpty()) {
                    et_pass.setError("كلمة السر مطلوبة!");
                    return;
                }
                if (!et_pass.getText().toString().equals(et_confpass.getText().toString())) {
                    et_confpass.setError("كلمة السر غير متطابقة!");
                    return;
                }
                mAuth.createUserWithEmailAndPassword(
                        et_emailAdd.getText().toString(),
                        et_pass.getText().toString()
                ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                             sh = getSharedPreferences("UserPref", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sh.edit();
                            editor.putString("userName",et_fullname.getText().toString());
                            editor.putBoolean("isLoggedIn", true);
                            editor.apply();

                            Toast.makeText(signup.this, "تم انشاء حساب بنجاح", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(signup.this, home.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(signup.this, "Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}