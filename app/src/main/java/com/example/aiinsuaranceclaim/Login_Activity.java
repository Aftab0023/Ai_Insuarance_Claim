package com.example.aiinsuaranceclaim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.aiinsuaranceclaim.databinding.ActivityLoginBinding;

public class Login_Activity extends AppCompatActivity {

    ActivityLoginBinding binding;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);
        binding.LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.LoginEmail.getText().toString();
                String password = binding.LoginPassword.getText().toString();

                if(email.equals("")||password.equals(""))
                    Toast.makeText(Login_Activity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkCredentials = databaseHelper.checkEmailPassword(email, password);
                    if(checkCredentials == true){
                        Toast.makeText(Login_Activity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login_Activity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.signupredirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Activity.this, Sign_up_Activity.class);
                startActivity(intent);
            }
        });
    }
}