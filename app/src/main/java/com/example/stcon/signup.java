package com.example.stcon;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;

public class signup extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private DatabaseReference mDatabase;
    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        register = (Button)findViewById(R.id.register);
        register.setOnClickListener(this);
    }

    public void signup(String email,String pass, String username,String dob, String city)
    {
        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(getApplicationContext(),"Email Required",Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(pass))
        {
            Toast.makeText(getApplicationContext(),"Password Required",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering......");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful())
                    {
                        FirebaseUser current_user =FirebaseAuth.getInstance().getCurrentUser();
                        String uid= current_user.getUid();
                        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

                        String device_token = FirebaseInstanceId.getInstance().getToken();

                        HashMap<String, String> userMap = new HashMap<>();
                        userMap.put("name", username);
                        userMap.put("DOB", dob);
                        userMap.put("city", city);
                        userMap.put("image", "default");
                        userMap.put("device_token", device_token);
                        mDatabase.setValue(userMap).addOnCompleteListener(this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"User Registered, Please Login",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent (signup.this,Log_sign.class);
                                startActivity(intent);
                                finish();
                            }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Unable to store data",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Unable to Register",Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();
                });
    }

    @Override
    public void onClick(View v) {

        EditText username = (EditText)findViewById(R.id.username);
        EditText password = (EditText)findViewById(R.id.password);
        EditText emails = (EditText)findViewById(R.id.email);
        EditText DOB = (EditText)findViewById(R.id.DOB);
        EditText city = (EditText)findViewById(R.id.city);

        String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String email = emails.getText().toString().trim();
        String dob = DOB.getText().toString().trim();
        String City = city.getText().toString().trim();
        signup(email,pass,user,dob,City);
    }
}