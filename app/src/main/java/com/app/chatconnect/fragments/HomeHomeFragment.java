package com.app.chatconnect.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.app.chatconnect.R;

public class HomeHomeFragment extends Fragment {

    private Toolbar homeHomeFragmentToolbar;

    public HomeHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); // Notify the fragment manager about the menu
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_home, container, false);

        homeHomeFragmentToolbar = view.findViewById(R.id.home_home_fragment_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(homeHomeFragmentToolbar);

        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");
        }


        // Calculate toolbar item's width
        homeHomeFragmentToolbar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                for (int i = 0; i < homeHomeFragmentToolbar.getChildCount(); i++) {
                    View child = homeHomeFragmentToolbar.getChildAt(i);
                    if (child != null) {
                        int width = child.getWidth();
                        int height = child.getHeight();
                        Log.d("ToolbarChild", "Child view width: " + width + ", height: " + height);
                    }
                }
            }
        });









        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.home_home_fragment_toolbar_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_search_container);

        if (searchItem != null && searchItem.getActionView() != null) {
            View actionView = searchItem.getActionView();
            EditText searchEditText = actionView.findViewById(R.id.search_product_edit_text);
            Button searchButton = actionView.findViewById(R.id.search_product_button);

            if (searchButton != null && searchEditText != null) {
                searchButton.setOnClickListener(v -> {
                    String query = searchEditText.getText().toString();
                    Toast.makeText(getContext(), "Searching for: " + query, Toast.LENGTH_SHORT).show();
                });
            }
        }


        // Calculate toolbar item's width
        if (searchItem != null && searchItem.getActionView() != null) {
            View actionView = searchItem.getActionView();
            actionView.post(() -> {
                int width = actionView.getWidth();
                int height = actionView.getHeight();
                Log.d("MenuItemActionView", "ActionView width: " + width + ", height: " + height);
            });
        }



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int currentItem = item.getItemId();

        if (currentItem == R.id.home_home_fragment_menu_item_logout) {
            Toast.makeText(getContext(), "Log out pressed", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
