

package com.jolly.creations.dequeue;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class doc_list extends AppCompatActivity {


    FirebaseDatabase database;
    DatabaseReference myRef;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "kootam" ;
    public static final String hosname = "hosname";
    public static final String docname = "docname";

    List<doc_model> list = new ArrayList<>();

    RecyclerView recyclerView;


    RecyclerView.Adapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);

        database = FirebaseDatabase.getInstance();


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        recyclerView = (RecyclerView) findViewById(R.id.prin_log_data_recycler);


        myRef = database.getReference(sharedpreferences.getString(hosname,"")+"/doctors");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    doc_model usersdetials = dataSnapshot.getValue(doc_model.class);
                    list.add(usersdetials);
                }
                adapter = new doc_name_adapter(doc_list.this, list);

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(doc_list.this));

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {

                final Button viewlog =(Button)view.findViewById(R.id.user_name);

                final SharedPreferences.Editor editor = sharedpreferences.edit();

                viewlog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String temp=viewlog.getText().toString();
                        Toast.makeText(doc_list.this, viewlog.getText().toString(),Toast.LENGTH_LONG).show();
                        editor.putString(docname,temp);
                        editor.commit();
                        startActivity(new Intent(doc_list.this,register.class));
                    }
                });




            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(doc_list.this, "Long press on position :"+position,
                        Toast.LENGTH_LONG).show();
            }
        }));



    }
}
