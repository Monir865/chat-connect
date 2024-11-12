package com.app.chatconnect;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.app.chatconnect.adapters.ViewPagerScreenContainerAdapter;
import com.app.chatconnect.model.ScreenContainerModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ViewPager MainViewPager;
   // private TabLayout MainTabLayout;
    private AppCompatButton MainNextScreenBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //
        MainViewPager = findViewById(R.id.main_view_pager);
        //MainTabLayout = findViewById(R.id.main_tab_layout);
        MainNextScreenBtn = findViewById(R.id.main_next_screen_btn);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //

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

            }
        });




    }




}