package com.example.medico;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class DiseasesType extends AppCompatActivity {

    Button btn_brain;
    Button btn_intestinal;
    Button btn_mental;
    Button btn_vision;
    Button btn_lung;
    Button btn_infectous;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseases_type);

        btn_brain=findViewById(R.id.btn_brain);
        btn_intestinal=findViewById(R.id.btn_intestinal);
        btn_mental=findViewById(R.id.btn_mental);
        btn_vision=findViewById(R.id.btn_vision);
        btn_lung=findViewById(R.id.btn_lung);
        btn_infectous=findViewById(R.id.btn_infectous);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Types Of Diseases");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_brain.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
            startActivity(new Intent(DiseasesType.this,BrainDisease.class));
            }
        });

        btn_intestinal.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(DiseasesType.this,IntestinalDiseases.class));
            }
        });

        btn_mental.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(DiseasesType.this,MentalDisorder.class));
            }
        });

        btn_vision.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(DiseasesType.this,VisionDiseases.class));
            }
        });


        btn_lung.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(DiseasesType.this,LungDiseases.class));
            }
        });

        btn_infectous.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(DiseasesType.this,InfectousDiseases.class));
            }
        });

    }




}
