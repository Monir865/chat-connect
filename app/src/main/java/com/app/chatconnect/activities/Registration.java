package com.app.chatconnect.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.chatconnect.R;
import com.app.chatconnect.database.DatabaseManager;
import com.app.chatconnect.models.User;
import com.app.chatconnect.util.Utils;

public class Registration extends AppCompatActivity {

    private EditText regFirstName, regLastName, regEmail, regAddress, regContact, regPassword;
    private AppCompatButton RegRegisterBtn;
    private TextView regLoginTxtBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);

        // Initialize EditTexts
        regFirstName = findViewById(R.id.reg_first_name);
        regLastName = findViewById(R.id.reg_last_name);
        regEmail = findViewById(R.id.reg_email);
        regAddress = findViewById(R.id.reg_address);
        regContact = findViewById(R.id.reg_contact);
        regPassword = findViewById(R.id.reg_password);
        RegRegisterBtn = findViewById(R.id.reg_register_btn);
        regLoginTxtBtn = findViewById(R.id.reg_login_txt_btn);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        RegRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseManager manager = new DatabaseManager(getApplicationContext());

                boolean isInserted = manager.addUser(new User(
                        Utils.generateRandomNumber(8),
                        regFirstName.getText().toString(),
                        regLastName.getText().toString(),
                        regEmail.getText().toString(),
                        regAddress.getText().toString(),
                        regContact.getText().toString(),
                        regPassword.getText().toString()
                ));

                if (isInserted) {
                    Toast.makeText(Registration.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(Registration.this, Login.class));
                            finish();
                        }
                    }, 1000);

                } else {
                    Toast.makeText(Registration.this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
                }

            }
        });


        regLoginTxtBtn.setOnClickListener(v ->{
            startActivity(new Intent(Registration.this, Login.class));
        });


    }




}
