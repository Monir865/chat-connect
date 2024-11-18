package com.app.chatconnect.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.app.chatconnect.R;
import com.app.chatconnect.database.DatabaseManager;

public class Login extends AppCompatActivity {
    private EditText logEmail,logPassword;
    private AppCompatButton logLoginBtn;
    private TextView logRegistrationTxtBtn;
    private CheckBox showPassWordCheckBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        //
        logEmail = findViewById(R.id.log_email);
        logPassword = findViewById(R.id.log_password);
        logLoginBtn = findViewById(R.id.log_login_btn);
        logRegistrationTxtBtn = findViewById(R.id.log_registration_txt_btn);
        showPassWordCheckBox = findViewById(R.id.show_password_check_box);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        logLoginBtn.setOnClickListener(new View.OnClickListener() {
            AlertDialog dialog = null;
            @Override
            public void onClick(View v) {

                DatabaseManager dm = new DatabaseManager(Login.this);
                boolean userCredential = dm.checkCredential(logEmail.getText().toString(), logPassword.getText().toString());

                if(userCredential){
                    dialog = showDialog(Login.this, "Login Successful","User logged in successfully!");

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(Login.this, Home.class));
                            dialog.dismiss();
                            finish();
                        }
                    }, 1000);


                }else {
                    dialog = showDialog(Login.this, "Login Failed", "User not found. Please try again.");
                }

            }
        });

        logRegistrationTxtBtn.setOnClickListener(v->{
            startActivity(new Intent(Login.this, Registration.class));
            finish();
        });
        showPassWordCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = showPassWordCheckBox.isChecked();

                if(isChecked){
                    logPassword.setTransformationMethod(null);
                }else{
                    logPassword.setTransformationMethod(new android.text.method.PasswordTransformationMethod());
                }

            }
        });


    }


    private AlertDialog showDialog(Context context, String title, String message) {

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setTitle(title);
        alertBuilder.setMessage(message);
        alertBuilder.setIcon(R.drawable.round_information_blue_icon);
        alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = alertBuilder.create();
        dialog.show();

        return dialog;
    }


}