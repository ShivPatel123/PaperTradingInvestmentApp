package com.example.navviewfe.Controllers.RecycleViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navviewfe.R;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ScrollViewHolder>{

    Context context;
    ArrayList<HistoryScrollCard> scrollCardList;

    public HistoryAdapter(Context context, ArrayList<HistoryScrollCard> scrollCardList){
        this.context=context;
        this.scrollCardList = scrollCardList;
    }

    @NonNull
    @Override
    public HistoryAdapter.ScrollViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ScrollViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.history_scroll,parent, false));
    }

    public class ScrollViewHolder extends RecyclerView.ViewHolder {

        TextView date, open, close, high, low, percent;
        CardView card;
        ImageView imageView;

        public ScrollViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.historyCard);
            date = itemView.findViewById(R.id.history_date);
            open = itemView.findViewById(R.id.history_open);
            close = itemView.findViewById(R.id.history_close);
            high = itemView.findViewById(R.id.history_high);
            low = itemView.findViewById(R.id.history_low);
            percent = itemView.findViewById(R.id.history_percent);
            imageView = itemView.findViewById(R.id.history_image);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ScrollViewHolder holder, int position) {
        HistoryScrollCard model = scrollCardList.get(position);
        holder.date.setText("" + model.getDate());
        holder.open.setText("Open:   " + model.getOpen());
        holder.close.setText("Close:   " + model.getClose());
        holder.high.setText("High: " + model.getHigh());
        holder.low.setText("Low: " + model.getLow());

        double closeDouble = Double.parseDouble(model.getClose());
        double openDouble = Double.parseDouble(model.getOpen());

        //set Image
        if(closeDouble >= openDouble){
            holder.imageView.setImageResource(R.drawable.greenarrow);
        }
        else {
            holder.imageView.setImageResource(R.drawable.stockredarrow);
        }

        //set +/-
        double val = closeDouble - openDouble;
        if (val > 0){
            holder.percent.setText("+" + val);
        }
        else {
            holder.percent.setText("-" + val);
        }
    }

    @Override
    public int getItemCount() {
        return scrollCardList.size();
    }
}

