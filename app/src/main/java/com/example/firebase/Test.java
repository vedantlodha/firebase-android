package com.example.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Test extends AppCompatActivity {
private FirebaseAuth firebaseAuth;
private FirebaseUser firebaseUser;
private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String name = firebaseUser.getDisplayName();
        String email = firebaseUser.getEmail();
        String phoneNumber = firebaseUser.getPhoneNumber();
        String uuid = firebaseUser.getUid();
        String provider = firebaseUser.getProviderId();
        Toast.makeText(this,name+"\n"+email+"\n"+phoneNumber+"\n"+uuid+"\n"+provider, Toast.LENGTH_LONG).show();
    }
}
