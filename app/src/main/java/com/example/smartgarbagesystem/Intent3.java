package com.example.smartgarbagesystem;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Intent3 extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private CalendarView slotDate;
    private Toolbar toolbar;
    private ActionBar a;
    private Spinner slotTime;
    private Button submit;
    Session session = null;
    ProgressDialog pDialog = null;
    Context context = null;
    String se,sd,st,msg;
    private FirebaseAuth sAuth;
    private FirebaseAuth.AuthStateListener sAuthListener;
    private FirebaseDatabase sFirebaseDatabase;
    private String userIDs;
    private String sName,sAddress;
    private String code;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent3);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        a = getSupportActionBar();
        assert  a!=null;
        a.setDisplayHomeAsUpEnabled(true);

        slotTime = findViewById(R.id.slotSpinner);
        slotTime.setOnItemSelectedListener(Intent3.this);
        List<String> categories = new ArrayList<String>();
        categories.add("8 A.M. to 9 A.M.");
        categories.add("12 P.M. to 1 P.M.");
        categories.add("4 P.M. to 5 P.M.");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        slotTime.setAdapter(dataAdapter);
        context = this;
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        slotDate = findViewById(R.id.bookDate);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sd = sdf.format(new Date(slotDate.getDate()));
        st = slotTime.getSelectedItem().toString();
        se = user.getEmail();
        code = RandomString();
        msg = "<i>Greetings,</i><br>";
        //msg += sName;
        msg+= "<br>";
        //msg += "<br><br>";
        msg += "<b>Thanks for choosing Smart Garbage Management and becoming a responsible citizen of India.</b><br><br>";
        msg += "This is a confirmation mail of the slot you've booked for garbage pick-up.<br><br>";
        msg += "Please check the details below :<br><br>";
        msg += "Pick-up date: "+sd;
        msg += "<br>";
        msg += "Pick-up time: "+st;
        msg += "<br>";
        //msg += "Pick-up address: "+sAddress;
        //msg += "<br>";
        msg += "<br><br>";
        msg +="Here's the Code to avail the services";
        msg += "<br><br>";
        msg+= "<center><b><font size=\"20\"><font color=\"blue\">"+code+"</font></b></center>";
        msg+= "<br><br>";
        msg += "Thank You,<br>";
        msg += "SMGS Team";
        //Toast.makeText(context, code, Toast.LENGTH_SHORT).show();


        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mayankranjan2018@gmail.com", "mayank7550170131@1");
            }
        });
        pDialog = ProgressDialog.show(context, "", "Booking Your Slot...", true);
        RetrieveFeedTask task = new RetrieveFeedTask();
        task.execute();
    }


    class RetrieveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("mayankranjan2018@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(se));
                message.setSubject("Slot Booking Confirmation");
                message.setContent(msg,"text/html; charset=utf-8");
                Transport.send(message);


            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result){
            pDialog.dismiss();
            Toast.makeText(getApplicationContext(),"Please check your email for confirmation",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent3.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
       // Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public String RandomString(){
        String characters = "abcdefghijklmnopqstuvwxyzABCDEFGHIJKLMNOPQSRTUVWXYZ1234567890@#$%";
        String randomString = "";
        int length = 6;

        Random rand = new Random();
        char[] text = new char[length];
        for(int i=0;i<length;i++){
            text[i] = characters.charAt(rand.nextInt(characters.length()));
        }
        for(int i=0;i<text.length;i++){
            randomString +=text[i];
        }
        return randomString;
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
