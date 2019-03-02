package com.example.medico.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.medico.Adapter.RecyclerViewAdapter;
import com.example.medico.R;

import java.util.ArrayList;

public class VisionDiseases extends AppCompatActivity {

    private static final String TAG="MainActivity";

    private ArrayList<String> mNames=new ArrayList<>();
    private ArrayList<String> mImages=new ArrayList<>();
    private ArrayList<String> mImageDiscriptionEn=new ArrayList<>();
    private ArrayList<String> mImageDiscriptionHi=new ArrayList<>();
    @Override
    public void onBackPressed() {
        startActivity(new Intent(VisionDiseases.this, HomeActivity.class));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vision_diseases);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Vision Diseases");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.d(TAG,"onCreate: started.");
        initImageBitmaps();
    }

    private void initImageBitmaps(){
        Log.d(TAG,"initImageBitmaps: preparing bitmaps.");

        mNames.add("Retinopathy of Prematurity");
        mImages.add("https://firebasestorage.googleapis.com/v0/b/medico-5e7ec.appspot.com/o/diseasesimages%2Fretinopathy%20of%20prematurity.png?alt=media&token=90be815a-9554-4198-8c57-358e7ffdd3d5");
        mImageDiscriptionEn.add("https://firebasestorage.googleapis.com/v0/b/medico-5e7ec.appspot.com/o/DiseasesDiscription%2FROP%20en.png?alt=media&token=aec5fad8-7a45-499d-a04b-7d94e30df4dd");
        mImageDiscriptionHi.add("https://firebasestorage.googleapis.com/v0/b/medico-5e7ec.appspot.com/o/DiseasesDiscription%2FROP%20hi.png?alt=media&token=dc537075-1e57-4fc2-bc4f-95c7f844240a");
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