package com.example.stcon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Log_sign extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_sign);
        getSupportActionBar().hide();
        Button register = (Button)findViewById(R.id.sign);
    }
    public void login()
    {
        Button login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Log_sign.this,Chat.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void signupform()
    {
        Button signup = (Button)findViewById(R.id.sign);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Log_sign.this,login.class);
                startActivity(intent);
//                Fragments mf = new Fragments();
//                FragmentManager manager = getSupportFragmentManager();
//                FragmentTransaction trn = manager.beginTransaction();
//                trn.add(R.id.sign,mf);
//                trn.commit();
//                getFragmentManager().popBackStackImmediate();
            }
        });


    }

    @Override
    public void onClick(View v) {

    }
}