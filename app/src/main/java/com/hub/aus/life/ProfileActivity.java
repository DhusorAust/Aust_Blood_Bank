package com.hub.aus.life;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileActivity extends AppCompatActivity {

    private Button button1, button2, button3;

    public EditText nameE,passwordE,batchE,mobileE,addressE;

    StringBuilder string1B = new StringBuilder();
    StringBuilder string2B = new StringBuilder();

    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        button1 = (Button) findViewById(R.id.pop);

        button2 = (Button) findViewById(R.id.department);

        db = FirebaseFirestore.getInstance();

        nameE= (EditText)findViewById(R.id.name);
      //  passwordE= (EditText)findViewById(R.id.password);
        batchE= (EditText)findViewById(R.id.batch_no);
        mobileE= (EditText)findViewById(R.id.mobile_no);
        addressE= (EditText)findViewById(R.id.address);

    }

    public void item(View view) {


        PopupMenu popupMenu = new PopupMenu(ProfileActivity.this, button1);

        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                StringBuilder string = new StringBuilder();
                string1B.setLength(0);
                string1B.append(item.getTitle());

                button1.setText(string1B);

                return true;
            }
        });
        popupMenu.show();


    }

    public void deparment(View view) {

        PopupMenu popupMenu = new PopupMenu(ProfileActivity.this, button2);

        popupMenu.getMenuInflater().inflate(R.menu.department, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                string2B.setLength(0);
                string2B.append(item.getTitle());

                button2.setText(string2B);

                return true;
            }
        });
        popupMenu.show();


    }



    public void save(View view) {

        if(string1B.equals("SELECT") && string2B.equals("SELECT"))
        {
            Toast.makeText(ProfileActivity.this, "Fill Blood Group and Department", Toast.LENGTH_LONG).show();
        }
        else {

            Intent main=new Intent(this,MainActivity.class);

            startActivity(main);

            String name = nameE.getText().toString();
           // String password = passwordE.getText().toString().trim();
            String string1 = string1B.toString().trim();
            String string2 = string2B.toString().trim();
            String batch = batchE.getText().toString().trim();
            String mobile = mobileE.getText().toString().trim();
            String address = addressE.getText().toString().trim();

            if (name.isEmpty() && batch.isEmpty() && mobile.isEmpty() && address.isEmpty())
            {
                Toast.makeText(ProfileActivity.this, "Fill Everything", Toast.LENGTH_LONG).show();
            } else {
                CollectionReference add_info = db.collection("Donors_info");


                Info info = new Info(
                        name,
                        //password,
                        string1,
                        string2,
                        batch,
                        mobile,
                        address
                );

                add_info.add(info)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(ProfileActivity.this, "Information Added", Toast.LENGTH_LONG).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


            }

        }
    }
}
