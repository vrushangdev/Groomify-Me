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

public class MentalDisorder extends AppCompatActivity {

    private static final String TAG="MainActivity";

    private ArrayList<String> mNames=new ArrayList<>();
    private ArrayList<String> mImages=new ArrayList<>();
    private ArrayList<String> mImageDiscription=new ArrayList<>();

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MentalDisorder.this, HomeActivity.class));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mental_disorder);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Mental Disorder Diseases");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.d(TAG,"onCreate: started.");
        initImageBitmaps();
    }

    private void initImageBitmaps(){
        Log.d(TAG,"initImageBitmaps: preparing bitmaps.");

        mNames.add("Attention Deficit Hyperactivity Disorder");
        mImages.add("https://firebasestorage.googleapis.com/v0/b/medico-5e7ec.appspot.com/o/diseasesimages%2Fadhd.jpg?alt=media&token=c42d0bbc-314d-47b7-939e-da47397d53bb");
        mImageDiscription.add("https://firebasestorage.googleapis.com/v0/b/medico-5e7ec.appspot.com/o/DiseasesDiscription%2FADHD%20en.png?alt=media&token=f0dce4d4-bbb0-43bd-8caf-0efd65d80446");
        initRecyclerView();
    }


    private void initRecyclerView(){
        Log.d(TAG,"initRecyclerView: init recyclerview.");
        RecyclerView recyclerView=findViewById(R.id.diseases_recycler_view);
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(this,mNames,mImages,mImageDiscription);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}