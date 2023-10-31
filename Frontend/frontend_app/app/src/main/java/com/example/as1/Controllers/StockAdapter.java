package com.example.as1.Controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.as1.R;

import java.util.List;

public class StockAdapter extends RecyclerView.Adapter<ScrollViewHolder> {

    Context context;
    List<StockPurchased> stockPurch;

    public StockAdapter(Context context, List<StockPurchased> stockPurch){
        this.stockPurch=stockPurch;
        this.context=context;
    }
    @NonNull
    @Override
    public ScrollViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ScrollViewHolder(LayoutInflater.from(context).inflate(R.layout.stock_scroll_view,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ScrollViewHolder holder, int position) {
        holder.stockName.setText((CharSequence) stockPurch.get(position).getStock());
        holder.stockNum.setText(stockPurch.get(position).getNumPurchased());
        holder.stockPrice.setText((int) stockPurch.get(position).getCostPurchase());
    }

    @Override
    public int getItemCount() {
        return stockPurch.size();
    }
}
