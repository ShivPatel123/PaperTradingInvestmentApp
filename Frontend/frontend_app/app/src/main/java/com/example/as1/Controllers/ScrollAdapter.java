package com.example.as1.Controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.as1.R;

import java.util.List;

public class ScrollAdapter extends RecyclerView.Adapter<ScrollViewHolder> {

    Context context;
    List<ScrollStockCard> scrollCardList;

    public ScrollAdapter(Context context, List<ScrollStockCard> scrollCardList){
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
        holder.stockName.setText(model.getStockName());
        holder.stockNum.setText(model.getNumPurchased());
        holder.stockPrice.setText(model.getStockPrice());
        holder.imageView.setId(model.getImageView());
    }

    @Override
    public int getItemCount() {
        return scrollCardList.size();
    }
}
