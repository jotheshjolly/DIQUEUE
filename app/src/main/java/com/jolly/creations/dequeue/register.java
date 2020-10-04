package com.jolly.creations.dequeue;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "kootam" ;
    public static final String hosname = "hosname";
    public static final String docname = "docname";
    public static final String pname = "pname";

    EditText name,age,gender,address,contatnumber;
    TextView docne;
    Button submit;
    String hname,dnmae;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        name = findViewById(R.id.txtName);
        docne = findViewById(R.id.docname);
        age = findViewById(R.id.age);
        gender = findViewById(R.id.gender);
        address = findViewById(R.id.address);
        contatnumber = findViewById(R.id.contactnumber);
        submit = findViewById(R.id.btnLogin);

        hname=sharedpreferences.getString(hosname,"");
        dnmae=sharedpreferences.getString(docname,"");
        Toast.makeText(getApplicationContext(), hname+"  "+dnmae, Toast.LENGTH_LONG).show();

        docne.setText(dnmae);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(pname,name.getText().toString());
                editor.commit();

                myRef.child(hname).child("doctors").child(dnmae).child("patients").child(name.getText().toString()).child("name").setValue(name.getText().toString());
                myRef.child(hname).child("doctors").child(dnmae).child("patients").child(name.getText().toString()).child("age").setValue(age.getText().toString());
                myRef.child(hname).child("doctors").child(dnmae).child("patients").child(name.getText().toString()).child("gen").setValue(gender.getText().toString());
                myRef.child(hname).child("doctors").child(dnmae).child("patients").child(name.getText().toString()).child("addre").setValue(address.getText().toString());
                myRef.child(hname).child("doctors").child(dnmae).child("patients").child(name.getText().toString()).child("contano").setValue(contatnumber.getText().toString());

                Toast.makeText(getApplicationContext(), "registered", Toast.LENGTH_LONG).show();

                startActivity(new Intent(register.this,recipt.class));
            }
        });





    }

}
