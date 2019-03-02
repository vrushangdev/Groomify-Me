package com.example.medico.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.medico.Model.MyPostModel;
import com.example.medico.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Parsania Hardik on 26-Jun-17.
 */
public class MyPostsAdapter extends RecyclerView.Adapter<MyPostsAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<MyPostModel> imageModelArrayList;


    public MyPostsAdapter(Context ctx, ArrayList<MyPostModel> imageModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.imageModelArrayList = imageModelArrayList;
    }

    public void removeItem(int position) {
        imageModelArrayList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, imageModelArrayList.size());
    }
    public void restoreItem(MyPostModel model, int position) {
        imageModelArrayList.add(position, model);
        // notify item added by position
        notifyItemInserted(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_item_myposts, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.time.setText(imageModelArrayList.get(position).getUploadTitle());
    }

    @Override
    public int getItemCount() {
        return imageModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView time;


        public MyViewHolder(View itemView) {
            super(itemView);

            time = (TextView) itemView.findViewById(R.id.tvh);

        }

    }
}