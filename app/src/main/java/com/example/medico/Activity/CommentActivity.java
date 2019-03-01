package com.example.medico.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.medico.Adapter.CommentRecyclerAdapter;
import com.example.medico.Model.BlogPostId;
import com.example.medico.Model.Comment;
import com.example.medico.Model.UploadPosts;
import com.example.medico.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommentActivity extends AppCompatActivity {
    Button commentSendBtn;
    EditText commentText;
    private RecyclerView commentRecyclerView;
    private List<Comment> commentList;
    TextView likeCount;
    String key;
    private CommentRecyclerAdapter commentRecyclerAdapter;
    ImageView likeBtn;
    Boolean likeChecker = false;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        getIncomingIntent();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Posts");
       // commentSendBtn = findViewById(R.id.commentSendBtn);
        //commentText = findViewById(R.id.commentText);
        likeCount = findViewById(R.id.likeCount);
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        likeBtn = findViewById(R.id.likeBtn);
        setBlogLikeBtn(key);
        setBlogLikeCount(key);
        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String currentUserId = mAuth.getCurrentUser().getUid();
               // UploadPosts uploadPosts = new UploadPosts();
                //uploadPosts.getPostKey();

                likeChecker=true;
                databaseReference.child(key).child("Likes").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (likeChecker) {
                                //dislike
                                if (dataSnapshot.child(currentUserId).exists()) {
                                    databaseReference.child(key).child("Likes").child(currentUserId).removeValue();
                                   // likeBtn.setImageDrawable(getDrawable(R.drawable.upvoteicon0));
                                    /*long count=dataSnapshot.getChildrenCount();
                                    likeCount.setText(count+" UpVotes");*/
                                    likeChecker = false;
                                } else {
                                    //liking post
                                    final HashMap<String, Object> hashmap = new HashMap<>();
                                    hashmap.put("sender", ServerValue.TIMESTAMP);
                                    databaseReference.child(key).child("Likes").child(currentUserId).setValue(hashmap);
                                   /* likeBtn.setImageDrawable(getDrawable(R.drawable.upvoteicon1));
                                    long count=dataSnapshot.getChildrenCount();
                                    likeCount.setText(count+" UpVotes");*/
                                    likeChecker = false;

                                }
                            }
                        }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });

       /* commentSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //final BlogPostId blogPostId = new BlogPostId();
                final String textComent = commentText.getText().toString();
                if (!textComent.isEmpty()) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    final DatabaseReference myRef = database.getReference("Posts");
                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot postchild : dataSnapshot.getChildren()) {
                                String blogPostId = postchild.getKey();
                                //String commentkey = postchild.child(commentId).child("Comments").getKey();
                                Comment comment = new Comment(mAuth.getCurrentUser().getUid(), textComent);
                                String key =  myRef.child(blogPostId).getKey();
                                addComment(comment,key);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                        public void addComment(Comment comment,String key)
                        {
                            myRef.child(key).child("Comments").push().setValue(comment).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(CommentActivity.this,"comment posted",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    });

                }
            }
        });*/

        //showComment();
    }
   /* public void getBlogListId(String blogPostId)
    {   this.blogPostId=blogPostId;}*/
   void setBlogLikeBtn(String key){
       FirebaseAuth mAuth;
       mAuth=FirebaseAuth.getInstance();
       final String currentUserId = mAuth.getCurrentUser().getUid();
       databaseReference.child(key).child("Likes").child(currentUserId).addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               if(dataSnapshot.exists()){
                   likeBtn.setImageDrawable(getDrawable(R.drawable.upvoteicon1));
               }
               else{
                   likeBtn.setImageDrawable(getDrawable(R.drawable.upvoteicon0));
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

   }
    void setBlogLikeCount(String key){

        databaseReference.child(key).child("Likes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long count=dataSnapshot.getChildrenCount();
                likeCount.setText(count+" UpVotes");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void showComment()
    {
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        //commentRecyclerView = findViewById(R.id.commentRecyclerView);
        commentRecyclerView.setLayoutManager(new LinearLayoutManager(CommentActivity.this));
        commentRecyclerView.setHasFixedSize(true);
        if(mAuth.getCurrentUser()!=null)
        {
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference("Posts");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    commentList = new ArrayList<>();
                    for(DataSnapshot comment : dataSnapshot.getChildren())
                    {
                            for(DataSnapshot comment1 : comment.getChildren()){
                                if(comment.child("Comments").getKey().equals(comment1.child("Comments").getKey()))
                                {
                                    for (DataSnapshot comment2 : comment1.child("Comments").getChildren()) {
                                        //String commentKey = comment1.getKey();
                                        Comment commentPost = comment2.getValue(Comment.class);
                                        commentList.add(commentPost);

                                    }
                                }
                            }
                    }

                    commentRecyclerAdapter = new CommentRecyclerAdapter(getApplicationContext(), commentList);
                    commentRecyclerView.setAdapter(commentRecyclerAdapter);
                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    public void getIncomingIntent() {

        if (getIntent().hasExtra("image_url") && getIntent().hasExtra("title") && getIntent().hasExtra("desc") && getIntent().hasExtra("blogPostId")) {
            String imageUrl = getIntent().getStringExtra("image_url");
            String title = getIntent().getStringExtra("title");
            String desc = getIntent().getStringExtra("desc");
            String key = getIntent().getStringExtra("blogPostId");
            setImage(imageUrl, title, desc,key);

        }
    }

    public void setImage(String imageUrl, String title, String desc , String key) {

        ImageView image = findViewById(R.id.commentImage);
        Glide.with(this).load(imageUrl).into(image);
        TextView titleSet = findViewById(R.id.commentTitle);
        titleSet.setText(title);
        TextView descSet = findViewById(R.id.commentSubject);
        descSet.setText(desc);
        this.key=key;
    }

}

