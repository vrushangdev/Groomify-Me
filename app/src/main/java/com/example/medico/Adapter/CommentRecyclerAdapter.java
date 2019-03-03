package com.example.medico.Adapter;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import com.bumptech.glide.Glide;
        import com.example.medico.Model.BlogPostId;
        import com.example.medico.Model.Comment;
        import com.example.medico.R;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.util.List;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;
        import de.hdodenhof.circleimageview.CircleImageView;

public class CommentRecyclerAdapter extends RecyclerView.Adapter<CommentRecyclerAdapter.MyViewHolder> {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Context mContext;
    private FirebaseAuth mAuth;
    private List<Comment> commentList;

    public CommentRecyclerAdapter(Context mContext, List<Comment> commentList) {
        this.commentList = commentList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CommentRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_list_item, parent, false);
        mAuth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Posts");
        return new CommentRecyclerAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final CommentRecyclerAdapter.MyViewHolder holder, int position) {
        BlogPostId blogPostId = new BlogPostId();

        holder.commentUser.setText(commentList.get(position).getComment());
        final String userUploadId = commentList.get(position).getCommentId();

        DatabaseReference myref = firebaseDatabase.getReference("user_data");
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds :dataSnapshot.getChildren()){
                    if(ds.getValue().toString().contains(userUploadId)){
                        String name= ds.child("fName").getValue().toString();
                        String url =ds.child("imageUrl").getValue().toString();
                        holder.commentUserName.setText(name);

                        if(url.contains("default")){
                            Glide.with(mContext).load(R.drawable.medicologo).into(holder.commentUserdp);

                        }
                        else {
                            Glide.with(mContext).load(url).into(holder.commentUserdp);

                        }

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView commentUser;
        TextView commentUserName;
        CircleImageView commentUserdp;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            commentUser = itemView.findViewById(R.id.commentUser);
            commentUserName = itemView.findViewById(R.id.UserNameInComment);
            commentUserdp = itemView.findViewById(R.id.CommentImageView);
        }
    }

}
