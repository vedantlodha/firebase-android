package com.example.firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private Button buttonLogin;
    private EditText editTextEmail, editTextPassword;
    private TextView textViewForgotPassword;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView textViewRegisterUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonLogin =  findViewById(R.id.buttonLogin);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword =  findViewById(R.id.editTextPassword);
        textViewRegisterUser = findViewById(R.id.textViewRegister);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            Toast.makeText(this, "Already Logged in", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), Test.class));
        }
        buttonLogin.setOnClickListener(this);
        textViewRegisterUser.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonLogin) {
            Toast.makeText(this, "Logging in", Toast.LENGTH_SHORT).show();
            login();
        }
        else if(v == textViewRegisterUser){
            startActivity(new Intent(getApplicationContext(), Register.class));
        }
    }
    private void login(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString();
        if(TextUtils.isEmpty(email) ){
            Toast.makeText(this,"Enter your email", Toast.LENGTH_SHORT ).show();
            return;
        }
        if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Enter your passowrd", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Logging in, please wait");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    Toast.makeText(Login.this, "Login successful", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Test.class));
                }
                else{
                    Toast.makeText(Login.this, "Login failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
