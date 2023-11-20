package com.example.as1.Controllers.RecycleViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.as1.Controllers.User;
import com.example.as1.R;

import java.util.ArrayList;
public class GroupScrollAdapter extends RecyclerView.Adapter<GroupScrollAdapter.ScrollViewHolder> {

    Context context;
    ArrayList<GroupMemScrollCard> scrollCardList;

    public GroupScrollAdapter(Context context, ArrayList<GroupMemScrollCard> scrollCardList){
        this.context=context;
        this.scrollCardList = scrollCardList;
    }

    @NonNull
    @Override
    public ScrollViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ScrollViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.group_members_scroll,parent, false));
    }

    public class ScrollViewHolder extends RecyclerView.ViewHolder {

        TextView memName,memUsername, memPerm, memID, memMoney, memNumStocks;
        CardView card;
//        Note: add if statement to set visibility based on user priv -- Button ban,unban;
        public ScrollViewHolder(@NonNull View itemView) {
            super(itemView);
              card = itemView.findViewById(R.id.memCard1);
              memID = itemView.findViewById(R.id.mem_id);
              memPerm = itemView.findViewById(R.id.mem_perms);
              memName = itemView.findViewById(R.id.mem_name);
              memUsername = itemView.findViewById(R.id.mem_username);
              memMoney = itemView.findViewById(R.id.mem_money);
              memNumStocks = itemView.findViewById(R.id.mem_numStocks);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ScrollViewHolder holder, int position) {
        GroupMemScrollCard model = scrollCardList.get(position);
        holder.memUsername.setText("" + model.getUsername());
        holder.memName.setText("" + model.getName());
        holder.memID.setText("ID: " + model.getId());
        holder.memPerm.setText("Permission: " + model.getPermission());
        holder.memMoney.setText("" + model.getMoney());
        holder.memNumStocks.setText("" + model.getNumStocks());

        User Global = User.getInstance();
        //Set ID to 1 if its not instantiated yet
//        if(Global.getId() <= 0)        Global.setId(1);
//        int modelID = (int) model.getId();

//        //Ban and Unban buttons
//        holder.ban.setOnClickListener(v -> {
//            holder.permV.setText("Permission: " + "b");
//            postBan(context.getApplicationContext(), Global, modelID);
//        });

    }

    @Override
    public int getItemCount() {
        return scrollCardList.size();
    }
}
