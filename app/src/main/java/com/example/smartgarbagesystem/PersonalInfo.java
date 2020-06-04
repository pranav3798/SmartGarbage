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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class PersonalInfo extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Toolbar toolbar;
    private ActionBar a;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private TextView uName,uAddress,uAge,uPhone,uPinCode;
    private Button uEdit;
    private FirebaseDatabase mFirebaseDatabase;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        a = getSupportActionBar();
        assert  a!=null;
        a.setDisplayHomeAsUpEnabled(true);


        uName = findViewById(R.id.uName);
        uAddress = findViewById(R.id.uAddress);
        uAge = findViewById(R.id.uAge);
        uPhone = findViewById(R.id.uPhone);
        uPinCode = findViewById(R.id.uPinCode);
        uEdit = findViewById(R.id.uEdit);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        final FirebaseUser user = mAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        DatabaseReference mRef = mFirebaseDatabase.getReference();


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){

                }
                else{

                }
            }
        };
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(PersonalInfo.this,"Error:"+databaseError, Toast.LENGTH_SHORT).show();
            }
        });

        uEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonalInfo.this, NewInfo.class));
                finish();
            }
        });

    }

    private void showData(DataSnapshot dataSnapshot){
        //Toast.makeText(this, "nam"+uName.getText().toString(), Toast.LENGTH_SHORT).show();
        for(DataSnapshot ds : dataSnapshot.getChildren()){

            user_get uGet = new user_get();
            uGet.setName(ds.child(userID).getValue(user_get.class).getName());
            uGet.setAddress(ds.child(userID).getValue(user_get.class).getAddress());
            uGet.setPin(ds.child(userID).getValue(user_get.class).getPin());
            uGet.setPhone(ds.child(userID).getValue(user_get.class).getPhone());
            uGet.setAge(ds.child(userID).getValue(user_get.class).getAge());

            uName.setText("Name: "+uGet.getName());
            uAddress.setText("Address: "+uGet.getAddress());
            uAge.setText("Age: "+uGet.getAge());
            uPinCode.setText("Pin Code: "+uGet.getPin());
            uPhone.setText("Contact Number: "+uGet.getPhone());

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
