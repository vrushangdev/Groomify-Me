package com.example.medico.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.medico.Activity.DiseaseDescriptionActivity;
import com.example.medico.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG="RecyclerViewAdapter";

    private ArrayList<String> mImageNames=new ArrayList<>();
    private ArrayList<String> mImages=new ArrayList<>();
    private ArrayList<String> mImageDiscriptionEn=new ArrayList<>();
    private ArrayList<String> mImageDiscriptionHi=new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context mContext,ArrayList<String> mImageNames,ArrayList<String> mImages , ArrayList<String> mImageDiscriptionEn,ArrayList<String> mImageDiscriptionHi) {
        this.mImageNames = mImageNames;
        this.mImages=mImages;
        this.mContext = mContext;
        this.mImageDiscriptionEn=mImageDiscriptionEn;
        this.mImageDiscriptionHi=mImageDiscriptionHi;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater
                .from(parent.getContext()).inflate(R.layout.layout_diseaseslist,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG,"onBindViewHolder: called.");
        Log.d("saumil", String.valueOf(position));

       Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.diseaseimage);
        holder.diseasesname.setText(mImageNames.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick:click:"+ mImageNames.get(position));

                Toast.makeText(mContext,mImageNames.get(position),Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(mContext, DiseaseDescriptionActivity.class);
                intent.putExtra("image_url",mImages.get(position));
                intent.putExtra("image_name",mImageNames.get(position));
                intent.putExtra("image_discription_urlEn",mImageDiscriptionEn.get(position));
                intent.putExtra("image_discription_urlHi",mImageDiscriptionHi.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageNames.size();

    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView diseaseimage;
        TextView diseasesname;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            diseaseimage=itemView.findViewById(R.id.diseaseimage);
            diseasesname=itemView.findViewById(R.id.diseasesname);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
