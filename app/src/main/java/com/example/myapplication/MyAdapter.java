package com.example.myapplication;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private final int[][] dataList;
    private final int n;
    private int screenWidth; // To hold screen width
    private final int[] colorPalette ;

    public MyAdapter(int[][] dataList, int n,  int screenWidth, int[] colorPalette) {
        Log.d("", "dataList is " + Arrays.deepToString(dataList) + " n is " + n);
        this.dataList = dataList;
        this.n = n;
        this.screenWidth = screenWidth;
        this.colorPalette = colorPalette;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int row = position / n;
        int col = position % n;
        int value = dataList[row][col];

        // Calculate square size
        int itemSize = screenWidth / n; // Divide screen width by number of columns

        // Set the width and height of the item to ensure it's a perfect square
        ViewGroup.LayoutParams params = holder.gridItem.getLayoutParams();
        params.width = itemSize;
        params.height = itemSize;
        holder.gridItem.setLayoutParams(params);

        // Log the dimensions to check if they are equal
        holder.gridItem.post(() -> {
            int width = holder.gridItem.getWidth();
            int height = holder.gridItem.getHeight();
            Log.d("GridItemSize", "Item " + position + ": Width = " + width + ", Height = " + height);
            if (width != height) {
                Log.e("GridItemSizeError", "Item " + position + " is not square! Width: " + width + ", Height: " + height);
            }
        });

        // Set background color based on value (customize color logic here)
        holder.gridItem.setBackgroundColor(colorPalette[value]);
    }

    @Override
    public int getItemCount() {
        return n * n; // Total number of items for an nXn grid
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View gridItem;

        public ViewHolder(View itemView) {
            super(itemView);
            gridItem = itemView.findViewById(R.id.gridItem);
        }
    }

}