package com.example.myapplication;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

public class MainActivity2 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    //private int[][] dataList; // Your nXn data array

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.rv_main);

        int[][] dataList = new int[][]{
                {0, 0, 1, 1, 1, 3, 2, 2, 2, 6, 6},
                {0, 3, 1, 3, 3, 3, 2, 6, 6, 6, 6},
                {0, 3, 1, 1, 1, 3, 2, 2, 2, 6, 6},
                {0, 3, 3, 3, 1, 3, 2, 6, 6, 6, 6},
                {5, 3, 1, 1, 1, 3, 2, 2, 2, 6, 6},
                {5, 3, 3, 3, 3, 3, 3, 3, 3, 3, 6},
                {5, 3, 8, 8, 8, 3, 7, 7, 7, 3, 6},
                {5, 5, 8, 4, 8, 9, 9, 7, 3, 3, 6},
                {5, 5, 8, 8, 8, 9, 10, 7, 3, 3, 6},
                {5, 5, 8, 10, 10, 9, 10, 7, 3, 3, 3},
                {5, 5, 8, 10, 10, 10, 10, 7, 3, 3, 3}};

        int n = dataList.length;
        final int[] colorPalette = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4),
                getResources().getColor(R.color.color5),
                getResources().getColor(R.color.color6),
                getResources().getColor(R.color.color7),
                getResources().getColor(R.color.color8),
                getResources().getColor(R.color.color9),
                getResources().getColor(R.color.color10),
                getResources().getColor(R.color.color11)};
        // Get the screen width to calculate item sizes
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels - (int) (16 * displayMetrics.density * 2); // Subtracting margin

        // Set GridLayoutManager with n columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, n);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Initialize the adapter and set it to RecyclerView
        adapter = new MyAdapter(dataList, n, screenWidth, colorPalette);
        recyclerView.setAdapter(adapter);
    }
}
