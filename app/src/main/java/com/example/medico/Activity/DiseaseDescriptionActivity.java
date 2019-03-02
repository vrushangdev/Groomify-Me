package com.example.medico.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.medico.R;

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
        if (getIntent().hasExtra("image_url") && getIntent().hasExtra("image_name") && getIntent().hasExtra("image_discription_urlEn") && getIntent().hasExtra("image_discription_urlHi") ) {

            String imageUrl=getIntent().getStringExtra("image_url");
            String imageName=getIntent().getStringExtra("image_name");
            String imageDiscriptionEn=getIntent().getStringExtra("image_discription_urlEn");
            String imageDiscriptionHi=getIntent().getStringExtra("image_discription_urlHi");
            setImage(imageUrl,imageName,imageDiscriptionEn,imageDiscriptionHi);
        }
    }

    private void setImage(String imageUrl,String imageName,String imageDiscriptionEn,String imageDiscriptionHi){

        TextView name=findViewById(R.id.disease_title);
        SharedPreferences prefs = DiseaseDescriptionActivity.this.getSharedPreferences("pref",MODE_PRIVATE);
        String lang = prefs.getString("lang","en");
        name.setText(imageName);
        ImageView image=findViewById(R.id.disease_image);
        Glide.with(this).asBitmap().load(imageUrl).into(image);
        ImageView image2=findViewById(R.id.disease_discription_image);
        if(lang.equals("en")){
            Glide.with(this).asBitmap().load(imageDiscriptionEn).into(image2);
        }else if(lang.equals("hi")){
            Glide.with(this).asBitmap().load(imageDiscriptionHi).into(image2);
        }




    }

}
