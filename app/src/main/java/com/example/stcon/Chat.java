package com.example.stcon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class Chat extends AppCompatActivity implements View.OnClickListener{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView imageView;
    private TextView user;
    private DatabaseReference mUsersDatabase;
    private FirebaseUser mCurrent_user;
    private String mCurrent_state;
    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout)findViewById(R.id.titles);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        user = (TextView)findViewById(R.id.user);
        mCurrent_user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = mCurrent_user.getUid();
        String name = mCurrent_user.getDisplayName();
       // mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("User").child(mCurrent_user.getDisplayName());
        user.setText(name);
        imageView = (ImageView)findViewById(R.id.pic);
        imageView.setOnClickListener(this);
//        tabLayout = (TabLayout)findViewById(R.id.container);
//        viewPager = (ViewPager)findViewById(R.id.viewpage);
//        tabLayout.addTab(tabLayout.newTab().setText("Available"));
//        tabLayout.addTab(tabLayout.newTab().setText("Applied"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//

        //final switchadapter adapter = new switchadapter(getSupportFragmentManager(),this,tabLayout.getTabCount());
        //viewPager.setAdapter(adapter);
        //viewPager.addOnAdapterChangeListener((ViewPager.OnAdapterChangeListener) new TabLayoutOnPageChangeListener(tabLayout));
    }
    public void profile()
    {
        Intent profile = new Intent (Chat.this,Profile.class);
        startActivity(profile);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.pic:
                profile();
                break;
        }

    }
}