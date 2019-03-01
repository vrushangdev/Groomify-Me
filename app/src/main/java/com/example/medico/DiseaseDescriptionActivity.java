package com.example.medico;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class  DiseaseDescriptionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Disease");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        if (getIntent().hasExtra("image_url") && getIntent().hasExtra("image_name") && getIntent().hasExtra("image_discription_url") ) {

            String imageUrl=getIntent().getStringExtra("image_url");
            String imageName=getIntent().getStringExtra("image_name");
            String imageDiscription=getIntent().getStringExtra("image_discription_url");
            setImage(imageUrl,imageName,imageDiscription);
        }
    }

    private void setImage(String imageUrl,String imageName,String imageDiscription){

        TextView name=findViewById(R.id.disease_title);
        name.setText(imageName);
        ImageView image=findViewById(R.id.disease_image);
        Glide.with(this).asBitmap().load(imageUrl).into(image);
        ImageView image2=findViewById(R.id.disease_discription_image);
        Glide.with(this).asBitmap().load(imageDiscription).into(image2);

    }

}
