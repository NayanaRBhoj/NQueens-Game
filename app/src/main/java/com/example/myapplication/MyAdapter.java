package com.example.myapplication;

import android.content.Context;
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
    private final int[] colorPalette;
    private final Context mContext;
    private int borderThickness; // The thickness of the borders in dp

    public MyAdapter(Context context, int[][] dataList, int n, int screenWidth, int[] colorPalette,int borderThickness) {
        Log.d("", "dataList is " + Arrays.deepToString(dataList) + " n is " + n);
        this.mContext = context;
        this.dataList = dataList;
        this.n = n;
        this.screenWidth = screenWidth;
        this.colorPalette = colorPalette;
        this.borderThickness = borderThickness; // Thickness of the border in dp
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
        int totalBorderSpace = (int) (borderThickness * mContext.getResources().getDisplayMetrics().density * 2);
        int itemSize = (screenWidth / n) - totalBorderSpace;
        // Calculate square size
        //int itemSize = screenWidth / n; // Divide screen width by number of columns

        // Set the width and height of the item to ensure it's a perfect square
        ViewGroup.LayoutParams params = holder.gridItem.getLayoutParams();
        params.width = itemSize;
        params.height = itemSize;
        holder.gridItem.setLayoutParams(params);

        // Log the dimensions to check if they are equal
        holder.gridItem.post(() -> {
            int width = holder.gridItem.getWidth();
            int height = holder.gridItem.getHeight();

            int topBorder_w = holder.topBorder.getWidth();
            int topBorder_h = holder.topBorder.getHeight();

            int bottomBorder_w = holder.bottomBorder.getWidth();
            int bottomBorder_h = holder.bottomBorder.getHeight();

            int leftBorder_w = holder.leftBorder.getWidth();
            int leftBorder_h = holder.leftBorder.getHeight();

            int rightBorder_w = holder.rightBorder.getWidth();
            int rightBorder_h = holder.rightBorder.getHeight();

            Log.d("GridItemSize", "Item " + position + ": Width = " + width + ", Height = " + height + " row " + row + " col " + col);
            Log.d("GridItemSize", "top width = "+topBorder_w+" height ="+topBorder_h+ " visibility "+holder.topBorder.getVisibility());
            Log.d("GridItemSize", "bottom width = "+bottomBorder_w+" height ="+bottomBorder_h+ " visibility "+holder.topBorder.getVisibility());
            Log.d("GridItemSize", "left width = "+leftBorder_w+" height ="+leftBorder_h+ " visibility "+holder.topBorder.getVisibility());
            Log.d("GridItemSize", "right width = "+rightBorder_w+" height ="+rightBorder_h+ " visibility "+holder.topBorder.getVisibility());
            if (width != height) {
                Log.e("GridItemSizeError", "Item " + position + " is not square! Width: " + width + ", Height: " + height);
            }
        });

        //if ((row == 0 && col == 0) || (row == n-1 && col == n-1)){
        if (row == 0 && col == 0){
            holder.topBorder.setVisibility(View.VISIBLE);
            holder.bottomBorder.setVisibility(View.VISIBLE);
            holder.leftBorder.setVisibility(View.VISIBLE);
            holder.rightBorder.setVisibility(View.VISIBLE);
        } else {
            holder.topBorder.setVisibility(View.GONE);
            holder.bottomBorder.setVisibility(View.GONE);
            holder.leftBorder.setVisibility(View.GONE);
            holder.rightBorder.setVisibility(View.GONE);
        }

        // Set background color based on value (customize color logic here)
        holder.gridItem.setBackgroundColor(colorPalette[value]);
    }

    @Override
    public int getItemCount() {
        return n * n; // Total number of items for an nXn grid
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View gridItem, topBorder, bottomBorder, leftBorder, rightBorder;

        public ViewHolder(View itemView) {
            super(itemView);
            gridItem = itemView.findViewById(R.id.gridItem);
            topBorder = itemView.findViewById(R.id.topBorder);
            bottomBorder = itemView.findViewById(R.id.bottomBorder);
            leftBorder = itemView.findViewById(R.id.leftBorder);
            rightBorder = itemView.findViewById(R.id.rightBorder);
        }
    }

}