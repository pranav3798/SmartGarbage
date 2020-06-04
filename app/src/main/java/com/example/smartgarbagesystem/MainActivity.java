package com.example.smartgarbagesystem;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {
    private TextView email,statusChange,GasUpdate;
    private Button signOut,toi1,toi2,toi3,toi4,toHistory,gast;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private FirebaseDatabase mDB;
    AlertDialog.Builder builder;
    private String statusID,status;
    private MediaPlayer mediaPlayer;
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent( MainActivity.this,MyService.class));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);

        statusChange = findViewById(R.id.statusUpdate);
        gast=findViewById(R.id.GasLevel);
        GasUpdate=findViewById(R.id.GasUpdate);
        mDB = FirebaseDatabase.getInstance();
        //get firebase auth instance
        auth = FirebaseAuth.getInstance();
        //String s = FirebaseInstanceId.getInstance().getToken().toString();
        //Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        //Log.i("String1",s);





        mediaPlayer=MediaPlayer.create(this,R.raw.schoolalarm);

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        statusID = user.getUid();
        DatabaseReference ref = mDB.getReference();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showStatus(dataSnapshot);
                showLevel(dataSnapshot);
                status = statusChange.getText().toString();

                String gas = GasUpdate.getText().toString();
                // Toast.makeText(this, "Status: "+status, Toast.LENGTH_SHORT).show();
                    if (status.equals("Empty")) {
                        statusChange.setBackgroundColor(Color.GRAY);
                        //blink();
                        /*NotificationManager notif = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification notify = new Notification.Builder(getApplicationContext()).setContentTitle("SMGS").setContentText("1234").setContentTitle("1334").setSmallIcon(R.drawable.fui_ic_facebook_white_22dp).build();
                        notify.flags |= Notification.FLAG_AUTO_CANCEL;
                        notif.notify(0, notify);*/
                        if(gas.equals("Safe")) {
                            GasUpdate.setBackgroundColor(Color.GREEN);
                        }
                        else  {
                            mediaPlayer.start();
                            Toast.makeText(getApplicationContext(),"Please press gas level button",Toast.LENGTH_LONG).show();
                            GasUpdate.setBackgroundColor(Color.BLUE);
                        }
                        //Toast.makeText(MainActivity.this, "yuhu", Toast.LENGTH_SHORT).show();
                    }   else if (status.equals("Non-Recylable")) {
                        statusChange.setBackgroundColor(Color.RED);
                        //blink();
                        if(gas.equals("Safe")) {
                            GasUpdate.setBackgroundColor(Color.GREEN);
                        }
                        else  {
                            Toast.makeText(getApplicationContext(),"Please press gas level button",Toast.LENGTH_LONG).show();
                            mediaPlayer.start();
                            GasUpdate.setBackgroundColor(Color.BLUE);
                        }
                        /*NotificationManager notif = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification notify = new Notification.Builder(getApplicationContext()).setContentTitle("SMGS").setContentText("5678").setContentTitle("134443").setSmallIcon(R.drawable.fui_ic_facebook_white_22dp).build();
                        notify.flags |= Notification.FLAG_AUTO_CANCEL;
                        notif.notify(0, notify);*/
                        //Toast.makeText(MainActivity.this, "yuhuuu", Toast.LENGTH_SHORT).show();
                    } else if (status.equals("Recyclable")) {
                        statusChange.setBackgroundColor(Color.GREEN);
                        //oblink();
                        if(gas.equals("Safe")) {
                            GasUpdate.setBackgroundColor(Color.GREEN);
                        }
                        else  {
                            Toast.makeText(getApplicationContext(),"Please press gas level button",Toast.LENGTH_LONG).show();
                            mediaPlayer.start();
                            GasUpdate.setBackgroundColor(Color.BLUE);
                        }
                        /*NotificationManager notif = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification notify = new Notification.Builder(getApplicationContext()).setContentTitle("SMGS").setContentText("5678").setContentTitle("134443").setSmallIcon(R.drawable.fui_ic_facebook_white_22dp).build();
                        notify.flags |= Notification.FLAG_AUTO_CANCEL;
                        notif.notify(0, notify);*/
                        //Toast.makeText(MainActivity.this, "yuhuuu", Toast.LENGTH_SHORT).show();
                    } else if (status.equals("Both Full")) {
                        statusChange.setBackgroundColor(Color.MAGENTA);
                        if(gas.equals("Safe")) {
                            GasUpdate.setBackgroundColor(Color.GREEN);
                        }
                        else  {
                            Toast.makeText(getApplicationContext(),"Please press gas level button",Toast.LENGTH_LONG).show();
                            mediaPlayer.start();
                            GasUpdate.setBackgroundColor(Color.BLUE);
                        }
                       /* NotificationManager notif = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification notify = new Notification.Builder(getApplicationContext()).setContentTitle("SMGS").setContentText("1233edsaa4").setContentTitle("133esdcvesvcfe4").setSmallIcon(R.drawable.fui_ic_facebook_white_22dp).build();
                        notify.flags |= Notification.FLAG_AUTO_CANCEL;
                        notif.notify(0, notify);*/
                    }






            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Error"+databaseError, Toast.LENGTH_SHORT).show();

            }
        });

        signOut = (Button) findViewById(R.id.sign_out);
        toi1 = (Button) findViewById(R.id.toi1);
        toi2 = (Button) findViewById(R.id.toi2);
        toi3 = (Button) findViewById(R.id.toi3);
        toi4 = (Button) findViewById(R.id.toi4);
        toHistory = findViewById(R.id.toHistory);

        email = (TextView) findViewById(R.id.email);

        email.setText(user.getEmail());

        /*signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });*/
        builder = new AlertDialog.Builder(this);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Are You Sure ?");
                alertDialogBuilder.setMessage("Click yes to exit")
                                  .setCancelable(false)
                                  .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                      @Override
                                      public void onClick(DialogInterface dialog, int which) {
                                          signOut();
                                      }
                                  })
                                  .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                      @Override
                                      public void onClick(DialogInterface dialog, int which) {
                                          dialog.cancel();
                                      }
                                  });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        toi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Intent2.class);
                startActivity(intent);
            }
        });
        toi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Intent3.class);
                startActivity(intent);
            }
        });
        toi3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Intent1.class);
                startActivity(intent);
            }
        });

        toi4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PersonalInfo.class);
                startActivity(intent);
            }
        });
        toHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, History.class);
                startActivity(intent);
            }
        });
    }


    public void gasUp(View view)
    {
        mediaPlayer.stop();
    }

    //sign out method
    public void signOut() {
        auth.signOut();
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

    private void showStatus(DataSnapshot dataSnapshot){
        for(DataSnapshot ds: dataSnapshot.getChildren()){
            user_get sget = new user_get();
            int t=Integer.parseInt(ds.child(statusID).getValue(user_get.class).getStatus()+"");
            /*sget.setStatus(t);
            Toast.makeText(getApplicationContext(),t+" ",Toast.LENGTH_LONG).show();*/
            if(t==0)
                statusChange.setText("Empty");
            else if(t==1)
                statusChange.setText("Recyclable");
            else if(t==2)
                statusChange.setText("Non-Recylable");
            else
                statusChange.setText("Both Full");

        }
    }
    private void showLevel(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds: dataSnapshot.getChildren()){
            user_get sget = new user_get();
            int t=Integer.parseInt(ds.child(statusID).getValue(user_get.class).getEnv()+"");
            if(t<150)
            {
                GasUpdate.setText("Safe");
            }
            else
            {
               // Toast.makeText(getApplicationContext(),Integer.toString(sget.getEnv()), Toast.LENGTH_LONG).show();
                GasUpdate.setText("Hazardous");
            }

    }
    }


}
