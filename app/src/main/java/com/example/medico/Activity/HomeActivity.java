package com.example.medico.Activity;

        import android.content.Intent;
        import android.os.Bundle;

        import com.example.medico.Fragments.CategoryFrag;
        import com.example.medico.Fragments.ProfileFragment;
        import com.example.medico.Fragments.homeFrag;
        import com.example.medico.Activity.newPost;
        import com.example.medico.R;
        import com.example.medico.myposts;
        import com.google.android.material.floatingactionbutton.FloatingActionButton;

        import androidx.fragment.app.FragmentManager;
        import androidx.fragment.app.FragmentTransaction;

        import android.os.Handler;
        import android.util.Log;
        import android.view.View;
        import com.google.android.material.navigation.NavigationView;
        import androidx.core.view.GravityCompat;
        import androidx.drawerlayout.widget.DrawerLayout;
        import androidx.appcompat.app.ActionBarDrawerToggle;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;

        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.Toast;

        import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    static String key;

    boolean twice;
    final String TAG=getClass().getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
      ft.replace(R.id.frmLyt,new CategoryFrag());
        ft.commit();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, newPost.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


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
        Toast.makeText(this, "Press Back Again to Exit.",Toast.LENGTH_SHORT).show();
        Handler h= new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                twice=false;
                Log.d(TAG,"twice:"+ twice);
            }
        },3000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        int id = item.getItemId();

        if (id == R.id.nav_home) {
           ft.replace(R.id.frmLyt,new CategoryFrag());
            ft.commit();
        } else if (id == R.id.nav_chat) {
            startActivity(new Intent(HomeActivity.this, ChatActivity.class));

        } else if (id == R.id.nav_preliminaries) {
            startActivity(new Intent(HomeActivity.this, PreliminariesActivity.class));

        } else if (id == R.id.nav_awareness) {
            startActivity(new Intent(HomeActivity.this, AwarenessActivity.class));

        } else if (id == R.id.nav_diseases) {
            startActivity(new Intent(HomeActivity.this, DiseasesType.class));

        }
        else if (id == R.id.nav_equipments) {
            startActivity(new Intent(HomeActivity.this, EquipmentsActivity.class));

        }
        /*else if (id == R.id.nav_upload_history) {
            startActivity(new Intent(HomeActivity.this, UploadHistory.class));
            ft.commit();
        }*/
        else if (id == R.id.nav_profie) {
            ft.replace(R.id.frmLyt,new ProfileFragment());
            ft.commit();
        }
        else if (id == R.id.nav_settings) {
            startActivity(new Intent(HomeActivity.this, settings.class));
            ft.commit();
        } else if (id == R.id.nav_myposts) {
            startActivity(new Intent(HomeActivity.this, myposts.class));
            ft.commit();

        }  else if (id == R.id.nav_about) {
            startActivity(new Intent(HomeActivity.this, myposts.class));
            ft.commit();

        }else if (id == R.id.nav_logut) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(HomeActivity.this, LogIn.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(R.string.post_title);
    }
}
