package com.jolly.creations.dequeue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class count extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "kootam" ;
    public static final String hosname = "hosname";
    public static final String docname = "docname";
    public static final String pname = "pname";

    TextView count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);

        database = FirebaseDatabase.getInstance();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        count=findViewById(R.id.count);

        myRef = database.getReference(sharedpreferences.getString(hosname,"")+"/doctors/"+sharedpreferences.getString(docname,"")+"/patients/");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                count.setText((dataSnapshot.getChildrenCount()+1)+"");
               // pnme.setText(dataSnapshot.child(hname).child("doctors").child(dnmae).child("patients").child(pn).child("name").getValue().toString());



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
}
