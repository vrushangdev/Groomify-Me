package com.example.medico;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

public class AwarenessActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AwarenessActivity.this,HomeActivity.class));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awareness);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.awareness);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
