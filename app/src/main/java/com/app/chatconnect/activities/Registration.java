package com.app.chatconnect.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.chatconnect.R;

public class Registration extends AppCompatActivity {

    private EditText regFirstName, regLastName, regEmail, regAddress, regContact, regPassword;
    private AppCompatButton RegRegisterBtn;

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


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        RegRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });


    }
}
