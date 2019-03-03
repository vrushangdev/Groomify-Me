package com.example.medico.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

import com.example.medico.Model.User;
import com.example.medico.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class doctorDetails extends AppCompatActivity {

    ImageView imageView;
    EditText ClinicContactNo;
    EditText Degree;
    Uri docURI ;
    String user_id;
    StorageReference storageReference;
    FirebaseAuth mAuth;
    boolean isChanged = false;
    DatabaseReference databaseuserdoctor;
    String mid;
    String email;
    String fullName;
    String status;
    String imageURL;
    String id;
    String cat,lang;
    boolean verified;
    DatabaseReference databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        ClinicContactNo = findViewById(R.id.ClinicContactNo);
        Degree = findViewById(R.id.Degree);
        storageReference = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        user_id = mAuth.getCurrentUser().getUid();
        databaseuserdoctor = FirebaseDatabase.getInstance().getReference("user_data");
        Intent intent=getIntent();
        mid=intent.getStringExtra("phoneNumber");
        email = intent.getStringExtra("email");
        fullName = intent.getStringExtra("fullName");
        id = intent.getStringExtra("id");
        imageURL= intent.getStringExtra("imageURL");
        status = intent.getStringExtra("status");
        verified = intent.getBooleanExtra("isverified",true);
        cat = intent.getStringExtra("category");


        ClinicContactNo = (EditText)findViewById(R.id.ClinicContactNo);
        Degree = (EditText)findViewById(R.id.Degree);


        imageView = (ImageView) findViewById(R.id.verify_doc);     //Camera Acess code

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    if (ContextCompat.checkSelfPermission(doctorDetails.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                        Toast.makeText(doctorDetails.this, "Permission Denied", Toast.LENGTH_LONG).show();
                        ActivityCompat.requestPermissions(doctorDetails.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                    } else {
                        BringImagePicker();

                    }

                }
            }

        });

    }

    private void BringImagePicker() {

        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMinCropResultSize(512, 512)
                .setAspectRatio(1, 1)
                .start(doctorDetails.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                if(ClinicContactNo.getText().toString().isEmpty())
                {
                    Toast.makeText(doctorDetails.this," contactField missing",Toast.LENGTH_LONG).show();
                }
                else if(Degree.getText().toString().isEmpty())
                {
                    Toast.makeText(doctorDetails.this," Degree Field missing",Toast.LENGTH_LONG).show();
                }
                else
                {
                    final String degree =Degree.getText().toString().trim();
                    final String ClinicNo =ClinicContactNo.getText().toString().trim();
                    databaseUser = FirebaseDatabase.getInstance().getReference("user_data");
                    DatabaseReference mRef= databaseUser.child(id);
                    User user_db = new User(fullName,mid ,email,id,"default","offline", degree, ClinicNo,cat,verified);
                    mRef.setValue(user_db);
                    docURI = result.getUri();
                    imageView.setImageURI(docURI);
                    isChanged = true;
                    StorageReference filepath=storageReference.child("photos").child(docURI.getLastPathSegment());
                    filepath.putFile(docURI).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(doctorDetails.this,"Upload done",Toast.LENGTH_LONG).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

                    Intent intent1=new Intent(doctorDetails.this, verifyotp.class);
                    intent1.putExtra("phoneNumber",mid);
                    startActivity(intent1);
                }


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();

            }
        }

    }
}
