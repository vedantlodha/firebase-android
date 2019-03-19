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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity implements View.OnClickListener{
    private Button buttonRegister;
    private EditText editTextEmail, editTextPassword, editTextConfirmPassword, editTextPhoneNumber;
    private TextView textViewSignIn;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        buttonRegister =  findViewById(R.id.buttonRegister);
        editTextEmail =  findViewById(R.id.editTextEmail);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        editTextPhoneNumber = findViewById(R.id.editTextPhone);
        editTextPassword =  findViewById(R.id.editTextPassword);
        textViewSignIn =  findViewById(R.id.textViewSignin);
        progressDialog = new ProgressDialog(this);
        buttonRegister.setOnClickListener(this);
        textViewSignIn.setOnClickListener(this);
    }
    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.toString();
        String phoneNumber = editTextPhoneNumber.toString().trim();
        if(TextUtils.isEmpty(email) ){
            Toast toast = Toast.makeText(this,"Enter your email", Toast.LENGTH_SHORT );
            toast.show();
            return;
        }
        if(TextUtils.isEmpty(password)) {
            Toast toast = Toast.makeText(this, "Enter your password", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if(! password.equals(confirmPassword)){
            Toast.makeText(Register.this, "Passwords donot match!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(! (TextUtils.isDigitsOnly(phoneNumber) && (phoneNumber.length() == 10))){
            Toast.makeText(this, "Invalid Phone", Toast.LENGTH_SHORT).show();
        }
        progressDialog.setMessage("Please Wait, Registering");
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "Success!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    @Override
    public void onClick(View view) {
        if(view == buttonRegister){
            registerUser();
        }
        else{
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        }
    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        FirebaseUser user = firebaseAuth.getCurrentUser();
//        if(user != null) {
//            //Intent intent = new Intent(getApplicationContext(), Welcome.class);
//            //startActivity(intent);
//        }
//    }
}
