/*
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

public class DiseasesActivity extends AppCompatActivity {

    private static final String TAG="MainActivity";

    private ArrayList<String> mNames=new ArrayList<>();
    private ArrayList<String> mImages=new ArrayList<>();
    private ArrayList<String> mDiscripion=new ArrayList<>();

    @Override
    public void onBackPressed() {
        startActivity(new Intent(DiseasesActivity.this, HomeActivity.class));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseases);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Diseases");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.d(TAG,"onCreate: started.");
        initImageBitmaps();
    }

    private void initImageBitmaps(){
        Log.d(TAG,"initImageBitmaps: preparing bitmaps.");

        mNames.add("disease1");
        mImages.add("https://firebasestorage.googleapis.com/v0/b/medico-5e7ec.appspot.com/o/uploads%2F1551191297107.jpg?alt=media&token=25bc5662-49eb-4d0b-a3d0-91d9c3e13d83");
        mDiscripion.add("bhai bhai bhai bhai bhai");
        mNames.add("disease2");
        mImages.add("https://firebasestorage.googleapis.com/v0/b/medico-5e7ec.appspot.com/o/uploads%2F1551191297107.jpg?alt=media&token=25bc5662-49eb-4d0b-a3d0-91d9c3e13d83");
        mDiscripion.add("bhai bhai bhai bhai bhai");

        initRecyclerView();
    }


    private void initRecyclerView(){
        Log.d(TAG,"initRecyclerView: init recyclerview.");
        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(DiseasesActivity.this,mNames,mImages,mDiscripionEn,);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
*/
