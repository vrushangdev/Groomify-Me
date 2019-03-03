package com.example.medico.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.load.data.DataRewinder;
import com.example.medico.R;
import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFrag extends Fragment {
    ImageView generalReasoning , newFacts , healthCare,nutrients;
    //List<String> imageUrls = new ArrayList<>();
    // RecyclerView categoryRecyclerView;
   FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public CategoryFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }
    void callFragment(String data,Fragment fragment , FragmentTransaction fragmentTransaction)
    {
        Bundle args = new Bundle();
        args.putString("uploadCat", data);//
        fragmentTransaction.add(R.id.frmLyt, fragment);
        fragment.setArguments(args);
        fragmentTransaction.replace(R.id.frmLyt, fragment).commit();
        fragmentTransaction.addToBackStack(null);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView);
        //imageMaps();
        generalReasoning = view.findViewById(R.id.generalReasoning);
        newFacts = view.findViewById(R.id.newFacts);
        healthCare = view.findViewById(R.id.healthCare);
        nutrients = view.findViewById(R.id.nutrients);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Posts");

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        final homeFrag fragment = new homeFrag();
        newFacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment("newFacts",fragment,fragmentTransaction);
            }
        });
        generalReasoning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment("generalReasoning",fragment,fragmentTransaction);
            }
        });
        healthCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment("healthCare",fragment,fragmentTransaction);
            }
        });
        nutrients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment("nutrients",fragment,fragmentTransaction);
            }
        });
    }
}

      /*  newFacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        homeFrag fragment = new homeFrag();
                        if (dataSnapshot.hasChildren()) {
                            for (DataSnapshot blogKey : dataSnapshot.getChildren()) {
                                String categoryValue = blogKey.child("uploadCat").getValue().toString();
                                if (categoryValue.equals("newFacts")) {
                                    Log.v("meet", "hey");
                                    Bundle args = new Bundle();
                                    args.putString("uploadCat", categoryValue);
                                    fragmentTransaction.add(R.id.frmLyt, fragment);
                                    fragment.setArguments(args);
                                    // fragmentTransaction.commit();
                                }
                            }
                            fragmentTransaction.replace(R.id.frmLyt, fragment).commit();
                           // fragmentTransaction.addToBackStack(null);
                        }
                        else
                        {
                            Toast.makeText(getActivity(),"No articles",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        nutrients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        homeFrag fragment = new homeFrag();
                        if (dataSnapshot.hasChildren()) {
                            for (DataSnapshot blogKey : dataSnapshot.getChildren()) {
                                String categoryValue = blogKey.child("uploadCat").getValue().toString();
                                if (categoryValue.equals("nutrients")) {
                                    Log.v("meet", "hey");
                                    Bundle args = new Bundle();
                                    args.putString("uploadCat", categoryValue);
                                   fragmentTransaction.add(R.id.frmLyt, fragment);
                                    fragment.setArguments(args);
                                    // fragmentTransaction.commit();
                                }
                            }
                            fragmentTransaction.replace(R.id.frmLyt, fragment).commit();
                            //fragmentTransaction.addToBackStack(null);
                        }
                        else
                        {
                            Toast.makeText(getActivity(),"No articles",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        healthCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        homeFrag fragment = new homeFrag();
                        if (dataSnapshot.hasChildren()) {
                            for (DataSnapshot blogKey : dataSnapshot.getChildren()) {
                                String categoryValue = blogKey.child("uploadCat").getValue().toString();
                                if (categoryValue.equals("healthCare")) {
                                    Log.v("meet", "cool");
                                    Bundle args = new Bundle();
                                    args.putString("uploadCat", categoryValue);
                                   fragmentTransaction.add(R.id.frmLyt, fragment);
                                    fragment.setArguments(args);
                                    // fragmentTransaction.commit();
                                }
                            }
                            fragmentTransaction.replace(R.id.frmLyt, fragment).commit();
                          //  fragmentTransaction.addToBackStack(null);
                        }
                        else
                        {
                            Toast.makeText(getActivity(),"No articles",Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
*/


