package com.example.medico;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;

import com.example.medico.Activity.EditPostActivity;
import com.example.medico.Activity.HomeActivity;
import com.example.medico.Adapter.MyPostsAdapter;
import com.example.medico.Model.BlogPostId;
import com.example.medico.Model.MyPostModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class myposts extends AppCompatActivity {
    private RecyclerView recyclerView;
    public ArrayList<MyPostModel> mypostArrayList;
    private MyPostsAdapter adapter;
    private Paint p = new Paint();
    public FirebaseDatabase database = FirebaseDatabase.getInstance();
    public DatabaseReference myRef = database.getReference().child("Posts");
    final FirebaseAuth mAuth = FirebaseAuth.getInstance();



    private String[] mypostdemolist = new String[]{};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myposts);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mypostArrayList = new ArrayList<MyPostModel>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));

        //get data from firebase and add it to list myPostDemoList
        final String currentUserId = mAuth.getCurrentUser().getUid();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for(DataSnapshot postSnapshot :  dataSnapshot.getChildren()){

                    String key =postSnapshot.child("postKey").getValue().toString();
                    String uploadId = postSnapshot.child("uploadId").getValue().toString();
                    String uploadTitle = postSnapshot.child("uploadTitle").getValue().toString();
                    String url = postSnapshot.child("uploadImageUrl").getValue().toString();

                    Log.d("Myposts", "Value is: " +currentUserId+"\t\t"+uploadId );
                    if(currentUserId.equals(uploadId)){
                        MyPostModel model = new MyPostModel();
                        model.setUploadTitle(uploadTitle);
                        model.setPostKey(key);
                        model.setUploadId(uploadId);



                        mypostArrayList.add(model);

                    }


                    adapter = new MyPostsAdapter(myposts.this, mypostArrayList);

                    recyclerView.setAdapter(adapter);

                    enableSwipe();
                }



                MyPostModel value = dataSnapshot.getValue(MyPostModel.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Myposts", "Failed to read value.", error.toException());
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    private void enableSwipe(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT){
                    final MyPostModel deletedModel = mypostArrayList.get(position);
                    final String blogid = deletedModel.getPostKey();
                    final int deletedPosition = position;
                    adapter.removeItem(position);
                    // showing snack bar with Undo option
                    Snackbar snackbar = Snackbar.make(getWindow().getDecorView().getRootView(), " removed from Recyclerview!", Snackbar.LENGTH_LONG);
                    snackbar.setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // undo is selected, restore the deleted item
                            adapter.restoreItem(deletedModel, deletedPosition);
                        }
                    });
                    snackbar.setActionTextColor(Color.YELLOW);
                    snackbar.show();
                    deletePost(blogid);


                }
                else if(direction == ItemTouchHelper.RIGHT){
                    final MyPostModel deletedModel = mypostArrayList.get(position);
                    final String blogid = deletedModel.getPostKey();
                    Intent i = new Intent(myposts.this, EditPostActivity.class);
                    String strName = blogid;
                    i.putExtra("BLOG_POST_ID", strName);
                    startActivity(i);

                }
                else {
                    final MyPostModel deletedModel = mypostArrayList.get(position);
                    final int deletedPosition = position;
                    adapter.removeItem(position);
                    // showing snack bar with Undo option
                    Snackbar snackbar = Snackbar.make(getWindow().getDecorView().getRootView(), " removed from Recyclerview!", Snackbar.LENGTH_LONG);
                    snackbar.setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            // undo is selected, restore the deleted item
                            adapter.restoreItem(deletedModel, deletedPosition);
                        }
                    });
                    snackbar.setActionTextColor(Color.YELLOW);
                    snackbar.show();
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Bitmap icon;
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if(dX > 0){
                        p.setColor(Color.parseColor("#388E3C"));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX,(float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.btnsend);
                        RectF icon_dest = new RectF((float) itemView.getLeft() + 2*width ,(float) itemView.getTop() + width,(float) itemView.getLeft()+ width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    } else {
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.deleteicon);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private ArrayList<MyPostModel> populateList(ArrayList<MyPostModel> postList){



        return postList;
    }
    private void deletePost(final String blogId){
                myRef.child(blogId).removeValue();


    }

}
