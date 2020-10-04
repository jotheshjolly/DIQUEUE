package com.jolly.creations.dequeue;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class qrcodescan extends AppCompatActivity {
    String xmldata;

    FirebaseDatabase database;
    DatabaseReference myRef;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "kootam" ;
    public static final String hosname = "hosname";
    public static final String docname = "docname";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcodescan);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null){
            if (intentResult.getContents() != null) {

                xmldata = intentResult.getContents();

                final SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(hosname,xmldata);
                editor.commit();


                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.

                        Toast.makeText(getApplicationContext(), ""+xmldata, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), ""+getLocalIpAddress(), Toast.LENGTH_SHORT).show();


                            Toast.makeText(getApplicationContext(), ""+dataSnapshot.getChildrenCount(), Toast.LENGTH_SHORT).show();
                            myRef.child(xmldata).child("doc_list").child(getLocalIpAddress()).setValue(dataSnapshot.getChildrenCount()+1);

//                        String value = dataSnapshot.getValue(String.class);
                       // Log.d(TAG, "Value is: " + value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        //Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });


                Intent i = new Intent(qrcodescan.this, doc_list.class);
                //i.putExtra("data",xmldata);
                startActivity(i);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void scan(View view) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(qrcodescan.this);
        intentIntegrator.initiateScan();
    }

    public String getLocalIpAddress(){
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
                 en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Exception ex) {
            Log.e("IP Address", ex.toString());
        }
        return null;
    }
}
