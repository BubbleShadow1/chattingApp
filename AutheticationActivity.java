package com.androidapp.whatsappproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.androidapp.whatsappproject.databinding.ActivityAutheticationBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AutheticationActivity extends AppCompatActivity {

    ActivityAutheticationBinding binding;
    String name, email,phone, password;
    DatabaseReference databaseReference;

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(AutheticationActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAutheticationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());  // Set the content view using binding.getRoot()
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=binding.nameedittext.getText().toString();
                email = binding.emailedittext.getText().toString();
                phone=binding.phoneedittext.getText().toString();
                password = binding.passwordeditext.getText().toString();
//                Intent i=new Intent();
//                i.putExtra("name",name);
//                i.putExtra("phone",phone);
//                i.putExtra("password",password);

                SharedPreferences sharedPreferences =getSharedPreferences("My", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("phone", phone);
                editor.apply();
                login();
            }
        });
        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = binding.nameedittext.getText().toString();
                phone=binding.emailedittext.getText().toString();
                email = binding.emailedittext.getText().toString();
                password = binding.passwordeditext.getText().toString();
                signup();
            }
        });
    }

    private void login()
    {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email.trim(),password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                startActivity(new Intent(AutheticationActivity.this,MainActivity.class));
                finish();
            }
        });
    }
    private void signup()
    {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.trim(),password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                UserProfileChangeRequest userProfileChangeRequest= new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();

                firebaseUser.updateProfile(userProfileChangeRequest);
                usermodel usermodel=new usermodel(FirebaseAuth.getInstance().getUid(), name,email,password);
                databaseReference.child(FirebaseAuth.getInstance().getUid()).setValue(usermodel);
                startActivity(new Intent(AutheticationActivity.this,MainActivity.class));
                finish();
            }
        });
    }
}