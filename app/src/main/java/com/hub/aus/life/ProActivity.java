package com.hub.aus.life;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ProActivity extends AppCompatActivity implements View.OnClickListener {

    private Button searchbutton;

    private RecyclerView recyclerView;
    private ProfileAdapter adapter;
    private List<Profile> profileList;
    private ProgressBar progressBar;
    private String searchblood;
    EditText Editsearchblood;




    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro);

        progressBar = findViewById(R.id.progressbar);

        recyclerView = findViewById(R.id.recyclerview_products);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        findViewById(R.id.search_button).setOnClickListener(this);
        profileList = new ArrayList<>();
        adapter = new ProfileAdapter(this, profileList);

        recyclerView.setAdapter(adapter);



        db = FirebaseFirestore.getInstance();

        db.collection("Donors_info")

                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        progressBar.setVisibility(View.GONE);

                        if(!queryDocumentSnapshots.isEmpty()){

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                            for(DocumentSnapshot d : list){

                                Profile p = d.toObject(Profile.class);
                                profileList.add(p);

                            }

                            adapter.notifyDataSetChanged();

                        }


                    }
                });

    }

    void search(){



        recyclerView = findViewById(R.id.recyclerview_products);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        profileList = new ArrayList<>();
        adapter = new ProfileAdapter(this, profileList);

        recyclerView.setAdapter(adapter);

        Editsearchblood=(EditText) findViewById(R.id.search_blood);
        searchblood=Editsearchblood.getText().toString().toUpperCase().trim();

        db.collection("Donors_info")
                .whereEqualTo("string1",searchblood)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {



                        if(!queryDocumentSnapshots.isEmpty()){

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                            for(DocumentSnapshot d : list){

                                Profile p = d.toObject(Profile.class);
                                profileList.add(p);

                            }

                            adapter.notifyDataSetChanged();

                        }


                    }
                });
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.search_button:
                search();
                break;
        }
    }
}