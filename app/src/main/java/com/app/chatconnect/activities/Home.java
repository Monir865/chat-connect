package com.app.chatconnect.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.app.chatconnect.R;
import com.app.chatconnect.fragments.HomeCartFragment;
import com.app.chatconnect.fragments.HomeHomeFragment;
import com.app.chatconnect.fragments.HomeLotarryFragment;
import com.app.chatconnect.fragments.HomeOrderFragment;
import com.app.chatconnect.fragments.HomeProfileFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {
    private FrameLayout homeFrameLayoutContainer;
    private AppBarLayout home_bottom_navigation_container;
    private BottomNavigationView homeBottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);


        //
        homeFrameLayoutContainer = findViewById(R.id.home_frame_layout_container);
        home_bottom_navigation_container = findViewById(R.id.home_bottom_navigation_container);
        homeBottomNavigationView = findViewById(R.id.home_bottom_navigation_view);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //

        // Set AppBarLayout height dynamically
        home_bottom_navigation_container.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                home_bottom_navigation_container.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int currentHeight = home_bottom_navigation_container.getHeight();

                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) homeFrameLayoutContainer.getLayoutParams();
                params.setMargins(0,0,0, currentHeight);
                homeFrameLayoutContainer.setLayoutParams(params);
            }
        });
        homeBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int currentItem = menuItem.getItemId();

                if(currentItem == R.id.home_bot_nevi_view_menu_item_home){
                    loadFragments(new HomeHomeFragment(), true);
                } else if (currentItem == R.id.home_bot_nevi_view_menu_item_cart) {
                    loadFragments(new HomeCartFragment(), false);
                } else if (currentItem == R.id.home_bot_nevi_view_menu_item_orders) {
                    loadFragments(new HomeOrderFragment(), false);
                } else if (currentItem == R.id.home_bot_nevi_view_menu_item_lotary) {
                    loadFragments(new HomeLotarryFragment(), false);
                } else if (currentItem == R.id.home_bot_nevi_view_menu_item_profile) {
                    loadFragments(new HomeProfileFragment(), false);
                }

                return true;

            }
        });

        homeBottomNavigationView.setSelectedItemId(R.id.home_bot_nevi_view_menu_item_home);





    }

    private void loadFragments(Fragment fragment, boolean isHomeFragment) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment currentFragment = fm.findFragmentById(R.id.home_frame_layout_container);

        if (currentFragment != null && currentFragment.getClass().equals(fragment.getClass())) {
            return;
        }

        FragmentTransaction ft = fm.beginTransaction();

        if (isHomeFragment) {
            ft.replace(R.id.home_frame_layout_container, fragment);
        } else {
            ft.replace(R.id.home_frame_layout_container, fragment);
        }

        ft.commit();
    }






}