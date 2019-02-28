package com.example.medico.Activity;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.medico.Model.UploadPosts;
import com.example.medico.R;
import com.example.medico.Translate;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.sql.Timestamp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class newPost extends AppCompatActivity {

    private ImageView postImage;
    private EditText postTitle;
    private EditText postSubject;
    private FloatingActionButton floatingPost;
    private Uri postImageUri = null;
    private StorageReference storageReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    private Toolbar newPostToolbar;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        newPostToolbar=findViewById(R.id.newPostToolbar);
        setSupportActionBar(newPostToolbar);
       setSupportActionBar(newPostToolbar);
        getSupportActionBar().setTitle(R.string.createnewpost);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        storageReference = FirebaseStorage.getInstance().getReference().child("postImages");
        firebaseDatabase = FirebaseDatabase.getInstance();

        mAuth = FirebaseAuth.getInstance();
        final String userId = mAuth.getCurrentUser().getUid();
        postImage = findViewById(R.id.blogImage);
        postTitle = findViewById(R.id.postTitle);
        postSubject = findViewById(R.id.postSubject);
        floatingPost = findViewById(R.id.floatingPost);
        postImage= findViewById(R.id.postCertificateImage);
        //progressBarImage=findViewById(R.id.progressBarImage);

        postImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    if (ContextCompat.checkSelfPermission(newPost.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                        Toast.makeText(newPost.this, "Permission Denied", Toast.LENGTH_LONG).show();
                        ActivityCompat.requestPermissions(newPost.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                    } else {
                        BringImagePicker();

                    }

                } else {

                    BringImagePicker();

                }
            }

            private void BringImagePicker() {

                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setMinCropResultSize(512, 512)
                        .setAspectRatio(1, 1)
                        .start(newPost.this);
            }

        });
        floatingPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = postTitle.getText().toString();
                String subject = postSubject.getText().toString();
                if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(title) && postImageUri != null) {
                    // String randomName =

                    final StorageReference filePath = storageReference.child(postImageUri.getLastPathSegment());
                    filePath.putFile(postImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String imageDownloadLink = uri.toString();
                                    String title = postTitle.getText().toString();
                                    String summary = postSubject.getText().toString();

                                    UploadPosts uploadPosts = new UploadPosts();
                                    uploadPosts.setUploadImageUrl(imageDownloadLink);
                                    uploadPosts.setUploadTitle(title);
                                    uploadPosts.setUploadSubject(summary);
                                    uploadPosts.setUploadId(userId);
                                    int time = (int) (System.currentTimeMillis());
                                    Timestamp tsTemp = new Timestamp(time);
                                    uploadPosts.setTimeStamp(tsTemp);

                                    translateService trans = new translateService();
                                    trans.execute(uploadPosts);



                                }
                            });
                        }
                        private void addPost(UploadPosts uploadPosts) {

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("Posts").push();

                            // get post unique ID and upadte post key
                            String key = myRef.getKey();
                            uploadPosts.setPostKey(key);
                            uploadPosts.getTimeStamp().toString();

                            // add post data to firebase database

                            myRef.setValue(uploadPosts).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(newPost.this,"Post Added Succesfully",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(newPost.this, HomeActivity.class));
                                    //popupClickProgress.setVisibility(View.INVISIBLE);
                                    // floatingPostBtn.set(View.VISIBLE);
                                }
                            });





                        }

                    });

                }

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                postImageUri = result.getUri();
                postImage.setImageURI(postImageUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();

            }
        }

    }

    private class translateService extends AsyncTask<UploadPosts,UploadPosts,UploadPosts> {
        String toText;
        String title;
        String htitle;
        String hsummary;
        String summary;
        String translatedTitle;

        UploadPosts uploadPosts = new UploadPosts();
        private ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(newPost.this);
            this.dialog.setMessage("Posting ...");
            this.dialog.show();

        }



        @Override
        protected UploadPosts doInBackground(UploadPosts... uploadPosts) {
            translatedTitle = "Welcome To Medico";
            UploadPosts post = new UploadPosts();

            for(UploadPosts t:uploadPosts){
                title=t.getUploadTitle();
                summary=t.getUploadSubject();
                post=t;
            }
            try{



                Translate translateRequest = new Translate();
                String response = Translate.prettify(translateRequest.Post(title+","+summary));
                String content=Translate.getTranslatedText(response);
                String[] contents=content.split(",");
                htitle=contents[0];
                hsummary=contents[1];
                post.setUploadTitleHindi(htitle);
                post.setUploadSubjectHindi(hsummary);




                Log.d("newPost",response);
                Log.d("newPost",content);


            } catch (Exception e) {
                e.printStackTrace();
            }


            return post;
        }


        @Override
        protected void onPostExecute(UploadPosts post) {
            super.onPostExecute(post);
            addPost(post);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }






        }


    }

    public void addPost(UploadPosts uploadPosts) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Posts").push();


        // get post unique ID and upadte post key
        String key = myRef.getKey();
        uploadPosts.setPostKey(key);
        uploadPosts.getTimeStamp().toString();

        // add post data to firebase database

        myRef.setValue(uploadPosts).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(newPost.this, "Post Added Succesfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(newPost.this, HomeActivity.class));
                //popupClickProgress.setVisibility(View.INVISIBLE);
                // floatingPostBtn.set(View.VISIBLE);
            }
        });


    }

}
