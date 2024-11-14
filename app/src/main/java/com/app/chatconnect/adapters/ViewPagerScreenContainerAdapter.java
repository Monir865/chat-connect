package com.app.chatconnect.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.app.chatconnect.R;
import com.app.chatconnect.models.ScreenContainerModel;

import java.util.List;


public class ViewPagerScreenContainerAdapter extends PagerAdapter {

    private Context context;
    private List<ScreenContainerModel> listScreen;

    public ViewPagerScreenContainerAdapter(Context context, List<ScreenContainerModel> listScreen) {
        this.context = context;
        this.listScreen = listScreen;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_screen_container, container, false);

        ImageView image = view.findViewById(R.id.screen_container_image);
        TextView title = view.findViewById(R.id.screen_container_title);
        TextView paragraph = view.findViewById(R.id.screen_container_para);

        ScreenContainerModel currentScreen = listScreen.get(position);

        image.setImageResource(currentScreen.getImage());
        title.setText(currentScreen.getTitle());
        paragraph.setText(currentScreen.getParagraph());

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return listScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
