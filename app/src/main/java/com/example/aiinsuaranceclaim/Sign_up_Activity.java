package com.example.aiinsuaranceclaim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aiinsuaranceclaim.databinding.ActivitySignUpBinding;


public class Sign_up_Activity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.signupEmail.getText().toString();
                String password = binding.signupPassword.getText().toString();
                String confirmPassword = binding.signupConfirm.getText().toString();

                if(email.equals("")||password.equals("")||confirmPassword.equals(""))
                    Toast.makeText(Sign_up_Activity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                else{
                    if(password.equals(confirmPassword)){
                        Boolean checkUserEmail = databaseHelper.checkEmail(email);
                        if(checkUserEmail == false){
                            Boolean insert = databaseHelper.insertData(email, password);
                            if(insert == true){
                                Toast.makeText(Sign_up_Activity.this, "Signup Successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Login_Activity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Sign_up_Activity.this, "Signup Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(Sign_up_Activity.this, "User already exists! Please login", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Sign_up_Activity.this, "Invalid Password!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.loginredirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sign_up_Activity.this, Login_Activity.class);
                startActivity(intent);
            }
        });
    }
}

