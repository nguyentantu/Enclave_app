package com.bracesmedia.androidmaterialdashboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView txtUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        addControls();
    }

    private void addControls() {

        txtUsername = findViewById(R.id.txt_username);

        Intent intent = getIntent();
        String username = intent.getStringExtra("usernameset");


        txtUsername.setText(username);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
