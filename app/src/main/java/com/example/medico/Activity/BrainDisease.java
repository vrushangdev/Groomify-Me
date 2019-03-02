package com.example.medico.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.medico.R;
import com.example.medico.Adapter.RecyclerViewAdapter;

import java.util.ArrayList;

public class BrainDisease extends AppCompatActivity {

    private static final String TAG="MainActivity";

    private ArrayList<String> mNames=new ArrayList<>();
    private ArrayList<String> mImages=new ArrayList<>();
    private ArrayList<String> mImageDiscriptionEn=new ArrayList<>();
    private ArrayList<String> mImageDiscriptionHi=new ArrayList<>();

    @Override
    public void onBackPressed() {
        startActivity(new Intent(BrainDisease.this, HomeActivity.class));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brain_disease

        );

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Brain Diseases");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.d(TAG,"onCreate: started.");
        initImageBitmaps();
    }

    private void initImageBitmaps(){
        Log.d(TAG,"initImageBitmaps: preparing bitmaps.");

        mNames.add("Cerebral Palsy");
        mImages.add("https://firebasestorage.googleapis.com/v0/b/medico-5e7ec.appspot.com/o/diseasesimages%2FBronchnopulmonary.png?alt=media&token=ff1d6b33-097f-4fac-8819-8e790822587a");
        mImageDiscriptionEn.add("https://firebasestorage.googleapis.com/v0/b/medico-5e7ec.appspot.com/o/DiseasesDiscription%2FCerebral%20palsy%20en.png?alt=media&token=cab13c25-ec2a-41c4-961c-76f7966e7a71");
        mImageDiscriptionHi.add("https://firebasestorage.googleapis.com/v0/b/medico-5e7ec.appspot.com/o/DiseasesDiscription%2FCerebral%20palsy%20hi.png?alt=media&token=3bb12740-7ebf-4517-8c97-1085911e6c46");

        initRecyclerView();
    }


    private void initRecyclerView(){
        Log.d(TAG,"initRecyclerView: init recyclerview.");
        RecyclerView recyclerView=findViewById(R.id.diseases_recycler_view);
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(this,mNames,mImages,mImageDiscriptionEn,mImageDiscriptionHi);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}