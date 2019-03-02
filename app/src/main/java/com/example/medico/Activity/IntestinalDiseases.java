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

public class IntestinalDiseases extends AppCompatActivity {

    private static final String TAG="MainActivity";

    private ArrayList<String> mNames=new ArrayList<>();
    private ArrayList<String> mImages=new ArrayList<>();
    private ArrayList<String> mImageDiscription=new ArrayList<>();

    @Override
    public void onBackPressed() {
        startActivity(new Intent(IntestinalDiseases.this, HomeActivity.class));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intestinal_diseases);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Intestinal Diseases");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.d(TAG,"onCreate: started.");
        initImageBitmaps();
    }

    private void initImageBitmaps(){
        Log.d(TAG,"initImageBitmaps: preparing bitmaps.");

        mNames.add("Cerebral Palsy");
        mImages.add("https://firebasestorage.googleapis.com/v0/b/medico-5e7ec.appspot.com/o/diseasesimages%2FNecrotizing%20entercolitics.png?alt=media&token=d2b16735-e648-4fa9-a841-340a0b74134d");
        mImageDiscription.add("https://firebasestorage.googleapis.com/v0/b/medico-5e7ec.appspot.com/o/DiseasesDiscription%2FNEC%20en.png?alt=media&token=a5fb94c6-ed99-4b0b-9497-d79e560da597");

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