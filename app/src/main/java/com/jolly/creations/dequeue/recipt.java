package com.jolly.creations.dequeue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class recipt extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "kootam" ;
    public static final String hosname = "hosname";
    public static final String docname = "docname";
    public static final String pname = "pname";


    TextView pnme,page,pgen,padd,pcon,dnam,date;

    String hname,dnmae,pn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipt);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        pnme=findViewById(R.id.pname);

        page=findViewById(R.id.page);
        pgen=findViewById(R.id.pgen);
        padd=findViewById(R.id.padd);
        pcon=findViewById(R.id.pcn);
        dnam=findViewById(R.id.dname);
        date=findViewById(R.id.tdate);

        String dateStr = "04/05/2010";

        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        Date dateObj = null;
        try {
            dateObj = curFormater.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat postFormater = new SimpleDateFormat("MMMM dd, yyyy");

        final String newDateStr = postFormater.format(dateObj);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        hname=sharedpreferences.getString(hosname,"");
        dnmae=sharedpreferences.getString(docname,"");
        pn=sharedpreferences.getString(pname,"");


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                pnme.setText(dataSnapshot.child(hname).child("doctors").child(dnmae).child("patients").child(pn).child("name").getValue().toString());

                pgen.setText(dataSnapshot.child(hname).child("doctors").child(dnmae).child("patients").child(pn).child("gen").getValue().toString());

                padd.setText(dataSnapshot.child(hname).child("doctors").child(dnmae).child("patients").child(pn).child("addre").getValue().toString());

                pcon.setText(dataSnapshot.child(hname).child("doctors").child(dnmae).child("patients").child(pn).child("contano").getValue().toString());

                dnam.setText(dataSnapshot.child(hname).child("doctors").child(dnmae).child("patients").child(pn).child("name").getValue().toString());

                page.setText(dataSnapshot.child(hname).child("doctors").child(dnmae).child("patients").child(pn).child("age").getValue().toString());

                date.setText(newDateStr);

                // myRef.child(xmldata).child("doc_list").child(getLocalIpAddress()).setValue(dataSnapshot.getChildrenCount()+1);

//                        String value = dataSnapshot.getValue(String.class);
                // Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }

    public void pay(View view) {
        startActivity(new Intent(recipt.this,count.class));
    }
}
