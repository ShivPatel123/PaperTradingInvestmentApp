package com.example.as1.Controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.as1.R;

import java.util.ArrayList;
import java.util.List;

public class ScrollAdapter extends RecyclerView.Adapter<ScrollAdapter.ScrollViewHolder> {

    Context context;
    ArrayList<ScrollStockCard> scrollCardList;

    public ScrollAdapter(Context context, ArrayList<ScrollStockCard> scrollCardList){
        this.context=context;
        this.scrollCardList = scrollCardList;
    }
    @NonNull
    @Override
    public ScrollViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ScrollViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_scroll_view,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ScrollViewHolder holder, int position) {
        ScrollStockCard model = scrollCardList.get(position);
        holder.stockName.setText("" + model.getStockName());
        holder.stockNum.setText("" + model.getNumPurchased());
        holder.stockPrice.setText("" + model.getStockPrice());
    }

    @Override
    public int getItemCount() {
        return scrollCardList.size();
    }

    public class ScrollViewHolder extends RecyclerView.ViewHolder {

        TextView stockPrice, stockNum, stockName;
        CardView card;

        public ScrollViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.stockCard1);
            stockName = itemView.findViewById(R.id.stock1_name);
            stockNum = itemView.findViewById(R.id.stock1_num);
            stockPrice = itemView.findViewById(R.id.stock1_price);
        }
    }

}
