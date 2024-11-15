package com.app.chatconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.app.chatconnect.activities.Registration;
import com.app.chatconnect.adapters.ViewPagerScreenContainerAdapter;
import com.app.chatconnect.models.ScreenContainerModel;
import com.app.chatconnect.util.Utils;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ViewPager MainViewPager;
    //private com.google.android.material.tabs.TabLayout MainTabLayout;
    private AppCompatButton MainNextScreenBtn, MainGetStartBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //
        MainViewPager = findViewById(R.id.main_view_pager);
        //MainTabLayout = findViewById(R.id.main_tab_layout);
        MainNextScreenBtn = findViewById(R.id.main_next_screen_btn);
        MainGetStartBtn = findViewById(R.id.main_get_start_btn);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //

        //checkAndUpdateSharedPrefData();
        //Utils.checkSharedPref(getApplicationContext(),"getStartComplete", MODE_PRIVATE, MainActivity.class, Registration.class);
        Utils.checkSharedPref(this, "getStartComplete", MODE_PRIVATE, MainActivity.class, Registration.class);


        List<ScreenContainerModel> screenList = new ArrayList<ScreenContainerModel>();
        screenList.add(new ScreenContainerModel(R.drawable.ic_connection_icon,"Seamless Chat Connection","Connect with friends and colleagues instantly through our reliable chat platform. Start conversations, share moments, and stay connected with ease, no matter where you are."));
        screenList.add(new ScreenContainerModel(R.drawable.ic_group_icon,"Collaborate in Groups","Bring people together in groups to discuss ideas, share updates, and collaborate efficiently. Create or join groups to stay organized and connected with everyone that matters."));
        screenList.add(new ScreenContainerModel(R.drawable.ic_data_transfer_icon,"Secure Data Transfer","Easily transfer files and important information securely within chats or groups. Enjoy peace of mind with high-speed, encrypted data transfer, ensuring your privacy and protection."));

        ViewPagerScreenContainerAdapter adapter = new ViewPagerScreenContainerAdapter(this, screenList);
        MainViewPager.setAdapter(adapter);

        MainNextScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int currentItem = MainViewPager.getCurrentItem();

                if(currentItem <  screenList.size()-1){
                    MainViewPager.setCurrentItem(++currentItem, true);
                }

                loadGetStartPage(MainViewPager, screenList);


            }
        });

        MainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                loadGetStartPage(MainViewPager, screenList);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //
        //MainTabLayout.setupWithViewPager(MainViewPager);

        MainGetStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.updateSharedPrefData(MainActivity.this,"getStartComplete", MODE_PRIVATE, true);

                startActivity(new Intent(MainActivity.this, Registration.class));
            }
        });


    }

    @Override
    public void onBackPressed() {
        int position = MainViewPager.getCurrentItem();

        if(position == 0){
            super.onBackPressed();
        }else{
            MainViewPager.setCurrentItem(position-1, true);
        }

        //super.onBackPressed();
    }

    private void loadGetStartPage(ViewPager viewPager, List list){
        if(viewPager.getCurrentItem() == list.size()-1){
            MainGetStartBtn.setVisibility(View.VISIBLE);
            MainNextScreenBtn.setVisibility(View.INVISIBLE);
        }else {
            MainGetStartBtn.setVisibility(View.INVISIBLE);
            MainNextScreenBtn.setVisibility(View.VISIBLE);
        }
    }





}