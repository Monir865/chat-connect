package com.app.chatconnect;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ActionMode;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreen extends AppCompatActivity {

    private ImageView SplashScreenImage;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);

        // Initialize variables
        SplashScreenImage = findViewById(R.id.splash_screen_image);
        animation = AnimationUtils.loadAnimation(this, R.anim.splash_image_anim);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Work Logic
        SplashScreenImage.startAnimation(animation);
        Intent getStartPageIntent = new Intent(this, MainActivity.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(getStartPageIntent);
                finish();
            }
        }, 4000);




    }





}