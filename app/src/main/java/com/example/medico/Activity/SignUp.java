package com.example.medico.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ProgressBar;

import java.util.Locale;


import com.example.medico.Model.User;
import com.example.medico.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    Button BtnSuSignUp;
    EditText FullName, LName,ContactNo, Password, Email, CPassword;
    TextView TvLogin,SignUp;
    boolean twice;
    boolean verified = false;
    Spinner language,category;
    String spinnerValue,cat;
    Locale myLocale;
    String currentLanguage, currentLang;
    final String TAG=getClass().getName();
    private FirebaseAuth mAuth;
    private String Degree, ClinicNo;
    DatabaseReference databaseUser;
    ProgressBar progressBar2;
    ProgressDialog dialog;
    Animation frombottom,fromtop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        BtnSuSignUp = (Button) findViewById(R.id.BtnSuSignUp);
        FullName = (EditText) findViewById(R.id.FullName);
        //LName = (EditText) findViewById(R.id.LName);
       // CPassword = (EditText) findViewById(R.id.CPassword);
        ContactNo = (EditText) findViewById(R.id.ContactNo);
        Password = (EditText) findViewById(R.id.Password);
        Email = (EditText) findViewById(R.id.Email);
        TvLogin= (TextView) findViewById(R.id.TvLogin);
        SignUp= (TextView) findViewById(R.id.SignUp);
        category=(Spinner)findViewById(R.id.category);
        language=(Spinner)findViewById(R.id.language);
        mAuth = FirebaseAuth.getInstance();

        //Animation
        TvLogin.startAnimation(frombottom);
        BtnSuSignUp.startAnimation(frombottom);
        SignUp.startAnimation(fromtop);
        FullName.startAnimation(fromtop);
       // LName.startAnimation(fromtop);
        ContactNo.startAnimation(fromtop);
        Email.startAnimation(fromtop);
        Password.startAnimation(fromtop);
       // CPassword.startAnimation(fromtop);
        category.startAnimation(fromtop);
        language.startAnimation(fromtop);

        ArrayAdapter<String> newadapter = new ArrayAdapter<String>(
                SignUp.this, R.layout.spinner_layout_test, getResources().getStringArray(R.array.lang));
        language.setAdapter(newadapter);

        ArrayAdapter<String> newadapter2 = new ArrayAdapter<String>(
                SignUp.this, R.layout.spinner_layout_test, getResources().getStringArray(R.array.category));
        category.setAdapter(newadapter2);

        // databaseUser = FirebaseDatabase.getInstance().getReference("user_data");
        //progressBar2.setVisibility(View.INVISIBLE);

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                spinnerValue = adapterView.getItemAtPosition(position).toString();
                if(position == 1)
                {
                    cat = "Patient";
                    verified = false;
                }
                else if(position == 2)
                {
                    cat = "Doctor";
                    verified = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        BtnSuSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new ProgressDialog(SignUp.this);
                dialog.setMessage("Loading");
                dialog.show();
                if (FullName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "FirstName Required..!!", Toast.LENGTH_SHORT).show();
                    FullName.setError("FirstName Required");
                    dialog.dismiss();
                    return;
                } /*else if (LName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "LastName Required..!!", Toast.LENGTH_SHORT).show();
                    LName.setError("LastName Required");
                    dialog.dismiss();
                    return;
                }*/ else if (ContactNo.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Contact Required..!!", Toast.LENGTH_SHORT).show();
                    ContactNo.setError("Contact Required");
                    dialog.dismiss();
                    return;
                }
                 else if (Email.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Email Required..!!", Toast.LENGTH_SHORT).show();
                    Email.setError("Email Required");
                    dialog.dismiss();
                    return;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(Email.getText().toString().trim()).matches()) {
                    Toast.makeText(getApplicationContext(), "Email Invaild..!!", Toast.LENGTH_SHORT).show();
                    Email.setError("Email Invalid");
                    dialog.dismiss();
                    return;
                } else if (Password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password Required..!!", Toast.LENGTH_SHORT).show();
                    Password.setError("Password Required");
                    dialog.dismiss();
                    return;
                } /*else if (CPassword.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password Required..!!", Toast.LENGTH_SHORT).show();
                    Password.setError("Password Required");
                    dialog.dismiss();
                    return;
                }*/ /*else if (!(Password.getText().toString()).equals(CPassword.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Password not match", Toast.LENGTH_SHORT).show();
                    CPassword.setError("Password not match");
                    dialog.dismiss();
                    return;
                }*/ else {
                    // progressBar2.setVisibility(View.VISIBLE);
                    final String email = Email.getText().toString().trim();
                    final String password = Password.getText().toString().trim();
                    final String fullName = FullName.getText().toString().trim();
                    //final String lName = LName.getText().toString().trim();
                    final String mid = ContactNo.getText().toString();
                    final String status = "offline";
                    final String imageURL = "default";

                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        BtnSuSignUp.setEnabled(false);
                                        Log.d(String.valueOf(SignUp.this), "createUserWithEmail:success");
                                        String id = mAuth.getCurrentUser().getUid();
                                        mAuth = FirebaseAuth.getInstance();

                                        databaseUser = FirebaseDatabase.getInstance().getReference("user_data");
                                        if (spinnerValue.equals("Patient") || spinnerValue.equals("मरीज")) {
                                            databaseUser = FirebaseDatabase.getInstance().getReference("user_data");
                                            DatabaseReference mRef = databaseUser.child(id);
                                            User user_db = new User(fullName, mid, email, id, "default", "offline", Degree, ClinicNo, cat, verified);
                                            mRef.setValue(user_db);
                                            Toast.makeText(SignUp.this, "Patient Successfully Registered", Toast.LENGTH_SHORT).show();
                                            Intent intent;
                                            intent = new Intent(SignUp.this, verifyotp.class);
                                            intent.putExtra("phoneNumber", mid);
                                            startActivity(intent);
                                        } else if (spinnerValue.equals("Doctor") || spinnerValue.equals("चिकित्सक")) {
                                            Toast.makeText(SignUp.this, "Doctor Successfully Registered", Toast.LENGTH_SHORT).show();
                                            Intent intent;
                                            intent = new Intent(SignUp.this, doctorDetails.class);
                                            intent.putExtra("phoneNumber", mid);
                                            intent.putExtra("fullName", fullName);
                                            intent.putExtra("email", email);
                                            intent.putExtra("id", id);
                                            intent.putExtra("imageURL", imageURL);
                                            intent.putExtra("status", status);
                                            intent.putExtra("isverified", verified);
                                            intent.putExtra("category", cat);
                                            startActivity(intent);
                                        } else if (spinnerValue.equals("Select Category")) {
                                            Toast.makeText(SignUp.this, "Please Select a Category!", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(String.valueOf(SignUp.this), "createUserWithEmail:failure");
                                        Toast.makeText(SignUp.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }

                                }

                            });

                }
            }
        });
        TvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this, LogIn.class));
            }
        });
       /* public static boolean isContactNoValid(String ConnNo)
        {
            String regExpn="\\d{10}";//regEx for contact no.

            CharSequence inputStr=ConnNo;//to convert string into character sequence.
            Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
            Matcher matcher= pattern.matcher(inputStr);
            if(matcher.matches())

                return true;

            else
                return false;
        }*/
       /*//* BtnSuCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CleanEditText();
            }
        });*//*

        language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        setLocale("en");
                        break;
                    case 2:
                        setLocale("hi");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void setLocale(String localeName) {
        if (!localeName.equals(currentLanguage)) {
            myLocale = new Locale(localeName);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, SignUp.class);
            refresh.putExtra(currentLang, localeName);
            startActivity(refresh);
        } else {
            Toast.makeText(SignUp.this, "Language already selected!", Toast.LENGTH_SHORT).show();
        }
    }
    //String user= mAuth.getCurrentUser().getUid();
    //String user= mAuth.getCurrentUser().getUid();
  *//*  public void insert_db(String fName,String lName,String mid,String email){
        String id= mAuth.getCurrentUser().getUid();
        User user_db = new User(fName,lName,mid,email,id,"default","offline");
        databaseUser.child(id).setValue(user_db);
    }*//*


    public void CleanEditText()
    {
        FullName.setText("");
        LName.setText("");
        ContactNo.setText("");
        Email.setText("");
        Password.setText("");
    }
    *//* @Override
     protected void onStart(){

         SignUp.super.onStart();
         databaseUser.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                 for(DataSnapshot ds : dataSnapshot.getChildren()){
 //                    System.out.println(ds);
 //                    System.out.println(ds.getValue());

                     ds.getValue();
                     try {
                         JSONObject reader = new JSONObject(ds.getValue().toString());
                         System.out.println(reader.getString("mid"));

                     } catch (JSONException e) {
                         e.printStackTrace();
                     }
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });
     }*//*
    @Override
    public void onBackPressed() {
        Log.d(TAG,"click");
        if(twice==true){
            Intent i=new Intent(Intent.ACTION_MAIN);
            i.addCategory(Intent.CATEGORY_HOME);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
            System.exit(0);
        }
        //super.onBackPressed();
        twice=true;
        Log.d(TAG,"twice:"+twice);
        Toast.makeText(SignUp.this,"Press Back Again to Exit.",Toast.LENGTH_SHORT).show();
        Handler h= new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                twice=false;
                Log.d(TAG,"twice:"+ twice);
            }
        },3000);
    }
}






*/}}