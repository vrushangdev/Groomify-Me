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

public class LungDiseases extends AppCompatActivity {

    private static final String TAG="MainActivity";

    private ArrayList<String> mNames=new ArrayList<>();
    private ArrayList<String> mImages=new ArrayList<>();
    private ArrayList<String> mImageDiscriptionEn=new ArrayList<>();
    private ArrayList<String> mImageDiscriptionHi=new ArrayList<>();
    @Override
    public void onBackPressed() {
        startActivity(new Intent(LungDiseases.this, HomeActivity.class));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lung_diseases);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Lung Diseases");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.d(TAG,"onCreate: started.");
        initImageBitmaps();
    }

    private void initImageBitmaps(){
        Log.d(TAG,"initImageBitmaps: preparing bitmaps.");

        mNames.add("Bronahnopulmonary Dysplasia");
        mImages.add("https://firebasestorage.googleapis.com/v0/b/medico-5e7ec.appspot.com/o/diseasesimages%2FBronchnopulmonary.png?alt=media&token=ff1d6b33-097f-4fac-8819-8e790822587a");
        mImageDiscriptionEn.add("https://firebasestorage.googleapis.com/v0/b/medico-5e7ec.appspot.com/o/DiseasesDiscription%2FBPD%20en.png?alt=media&token=0e18a0a3-70f9-4fc5-9849-1c6e31584400");
        mImageDiscriptionHi.add("https://firebasestorage.googleapis.com/v0/b/medico-5e7ec.appspot.com/o/DiseasesDiscription%2FBPD%20hi.png?alt=media&token=ed9db3bf-a37a-4420-84b1-e4f695f91438");
        mNames.add("Respiratory Distress Syndrome");
        mImages.add("https://firebasestorage.googleapis.com/v0/b/medico-5e7ec.appspot.com/o/diseasesimages%2FRDS%20in%20lung%20disease%20column.jpg?alt=media&token=1ce6f7dc-c347-42ae-b0ff-d50b0aab38bc");
        mImageDiscriptionEn.add("https://firebasestorage.googleapis.com/v0/b/medico-5e7ec.appspot.com/o/DiseasesDiscription%2FRDS%20en.png?alt=media&token=9dc5b083-49eb-4da1-9ce4-e91520ba4e22");
        mImageDiscriptionHi.add("https://firebasestorage.googleapis.com/v0/b/medico-5e7ec.appspot.com/o/DiseasesDiscription%2FRDS%20hi.png?alt=media&token=53ea7a02-27d7-465b-88f3-02d389bbb16c");

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