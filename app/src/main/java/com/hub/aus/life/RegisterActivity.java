package com.hub.aus.life;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;


public class RegisterActivity extends AppCompatActivity implements OnClickListener{
    ProgressBar progressBar;
    EditText editTextEmailSign1,editTextPasswordSign1;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextEmailSign1=(EditText) findViewById(R.id.editTextEmailSign1);
        editTextPasswordSign1 =(EditText) findViewById(R.id.editTextPasswordSign1);

        progressBar=(ProgressBar)findViewById(R.id.progressbar1);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.buttonSignUp).setOnClickListener(this);
    }

    private void registerUser(){
        String email = editTextEmailSign1.getText().toString().trim();
        String password =editTextPasswordSign1.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmailSign1.setError("Email is required");
            editTextEmailSign1.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmailSign1.setError("Enter a valid email");
            editTextEmailSign1.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPasswordSign1.setError("Password is required");
            editTextPasswordSign1.requestFocus();
            return;
        }
        if(password.length()<6){
            editTextPasswordSign1.setError("Minimum password length is 6");
            editTextPasswordSign1.requestFocus();
            return;
        }
        this.progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    //Toast.makeText(getApplicationContext(), "User Registered Successfully",Toast.LENGTH_SHORT).show();

                    Intent profileintent =new Intent(RegisterActivity.this,ProfileActivity.class);
                    profileintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    RegisterActivity.this.startActivity(profileintent);
                }
                else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"This Email is already registered",Toast.LENGTH_SHORT).show();
                    }
                    else{

                    Toast.makeText(getApplicationContext(),"Opps,Some Error Occured",Toast.LENGTH_SHORT).show();}
                }
            }
        });
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.buttonSignUp:
                registerUser();
                break;
        }
    }
}

