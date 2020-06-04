package com.example.smartgarbagesystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class NewInfo extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Toolbar toolbar;
    private ActionBar a;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText uName,uAddress,uAge,uPhone,uPinCode,uPassword,uEmail;
    private Button uSave;
    private FirebaseDatabase mFirebaseDatabase;
    private String userID;
    private TextView tg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_info);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        a = getSupportActionBar();
        assert  a!=null;
        a.setDisplayHomeAsUpEnabled(true);

        uName = findViewById(R.id.eName);
        uAddress = findViewById(R.id.eAddress);
        uAge = findViewById(R.id.eAge);
        uPhone = findViewById(R.id.ePhone);
        uPinCode = findViewById(R.id.ePinCode);
        uSave = findViewById(R.id.eSave);
        uPassword = findViewById(R.id.ePassword);
        tg = findViewById(R.id.textView2);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        //DatabaseReference mRef = mFirebaseDatabase.getReference(mAuth.getUid());
        final FirebaseUser user = mAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        DatabaseReference mRef = mFirebaseDatabase.getReference();


        uSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = user.getEmail().toString();
                String password = uPassword.getText().toString();

                final String name = uName.getText().toString();
                final String address = uAddress.getText().toString();
                final String age = uAge.getText().toString();
                final String pin = uPinCode.getText().toString();
                final String phone = uPhone.getText().toString();
                final String status = "NA";

                //regex
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Enter name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(address)) {
                    Toast.makeText(getApplicationContext(), "Enter address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(age)) {
                    Toast.makeText(getApplicationContext(), "Enter age!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(getApplicationContext(), "Enter contact number!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pin)) {
                    Toast.makeText(getApplicationContext(), "Enter Pin Code!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(NewInfo.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(NewInfo.this, "Please Check the password entered", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            String user_id = mAuth.getCurrentUser().getUid();
                            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                            Map newPost = new HashMap();
                            newPost.put("name", name);
                            newPost.put("address", address);
                            newPost.put("age", age);
                            newPost.put("pin", pin);
                            newPost.put("phone", phone);
                            newPost.put("email", email);
                            newPost.put("status",status);
                            current_user_db.setValue(newPost);
                            //Toast.makeText(NewInfo.this, "Success!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(NewInfo.this, MainActivity.class));
                            finish();
                        }
                    }
                });
            }
        });

    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        startActivity(new Intent(NewInfo.this, PersonalInfo.class));
        finish();
    }
}
