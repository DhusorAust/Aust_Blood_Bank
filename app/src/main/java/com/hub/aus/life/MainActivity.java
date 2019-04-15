package com.hub.aus.life;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonSignup;
    ProgressBar progressBar;
    EditText editTextLoginEmail,editTextLoginPassword;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.mAuth =FirebaseAuth.getInstance();
        this.editTextLoginEmail = this.findViewById(R.id.LoginEmail);
        this.editTextLoginPassword = this.findViewById(R.id.LoginPassword);
        this.progressBar = this.findViewById(R.id.LoginProgress);

        this.findViewById(R.id.buttonLogin).setOnClickListener(this);


        buttonSignup =(Button)findViewById(R.id.signup);
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });


    }
    public void  openActivity(){
        Intent registerIntent = new Intent(this,RegisterActivity.class);
        startActivity(registerIntent);
    }

    public void  LoginUser(){
       /* Intent loginIntent = new Intent(this,LoginActivity.class);
        startActivity(loginIntent);*/

            String email = this.editTextLoginEmail.getText().toString().trim();
            String password = this.editTextLoginPassword.getText().toString().trim();

            if (email.isEmpty()) {
                this.editTextLoginEmail.setError("Email is required");
                this.editTextLoginEmail.requestFocus();
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                this.editTextLoginEmail.setError("Enter a valid email");
                this.editTextLoginEmail.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                this.editTextLoginPassword.setError("Password is required");
                this.editTextLoginPassword.requestFocus();
                return;
            }
            if (password.length() < 6) {
                this.editTextLoginPassword.setError("Minimum password length is 6");
                this.editTextLoginPassword.requestFocus();
                return;
            }

            this.progressBar.setVisibility(View.VISIBLE);
            this.mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressBar.setVisibility(View.GONE);
                    if(task.isSuccessful()){
                        /*Intent userintent =new Intent(MainActivity.this,UserActivity.class);
                        userintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        MainActivity.this.startActivity(userintent);*/

                        Intent userintent =new Intent(MainActivity.this,ProActivity.class);
                        userintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        MainActivity.this.startActivity(userintent);
                    }
                    else{
                        Toast.makeText(MainActivity.this.getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonLogin:
                this.LoginUser();
                break;
        }
    }


}
