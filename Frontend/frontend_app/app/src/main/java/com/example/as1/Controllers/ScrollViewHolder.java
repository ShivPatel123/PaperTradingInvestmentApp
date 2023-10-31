package com.example.as1.Controllers;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.as1.R;

public class ScrollViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    EditText stockPrice, stockNum, stockName;
    CardView card;

    public ScrollViewHolder(@NonNull View itemView) {
        super(itemView);
        card = itemView.findViewById(R.id.stockCard1);
        imageView = itemView.findViewById(R.id.imageView);
        stockName = itemView.findViewById(R.id.stock1_name);
        stockNum = itemView.findViewById(R.id.stock1_num);
        stockPrice = itemView.findViewById(R.id.stock1_price);
    }
}
