package activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.doctorapp.R;

import fragments.EditProfile_Fragment;

public class EditProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);

        FrameLayout fm = findViewById(R.id.fm);
        //fragment obj
        EditProfile_Fragment editProfile_fragment=new EditProfile_Fragment();
        //fragment manager & start transaction
        FragmentManager fragmentManager =getSupportFragmentManager();
        //fragment teansaction & begin teansaction
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fm,editProfile_fragment);
        fragmentTransaction.commit();


        //declar element
        ImageView back = findViewById(R.id.iv_back);

        //event in back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfile.this, profile.class);
                startActivity(intent);
            }
        });
    }
}