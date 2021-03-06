package com.chris.codes.served;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RewardListAdapter extends RecyclerView.Adapter<RewardListAdapter.RewardViewHolder> {

    private final Context context;

    class RewardViewHolder extends RecyclerView.ViewHolder {

        private final LinearLayout linearLayout; // holds list item's subviews
        private final TextView itemNameText;
        private final TextView conditionText;
        private final TextView expDateText;

        private RewardViewHolder(View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.reward_linear_layout);
            itemNameText = itemView.findViewById(R.id.item_name_text);
            conditionText = itemView.findViewById(R.id.reward_condition_text);
            expDateText = itemView.findViewById(R.id.exp_date_text);
        }
    }


    private final LayoutInflater mInflater;
    private List<Reward> mRewards;

    RewardListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override @NonNull
    public RewardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new RewardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RewardViewHolder holder, int position) {

        if(mRewards != null) {

            final Reward currentReward = mRewards.get(position);

            holder.itemNameText.setText(currentReward.getItemName());
            holder.conditionText.setText(currentReward.getCondition());
            holder.expDateText.setText(currentReward.getExpirationDate().toString());

            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String message = currentReward.getItemName() + "\n@" + currentReward.getCompanyId();
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                }
            });

        }
        // data isn't ready yet
        else {
            holder.itemNameText.setText("No rewards yet");
        }
    }

    void setRewards(List<Reward> rewards) {
        mRewards = rewards;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        if(mRewards != null)
            return mRewards.size();
        else
            return 0;
    }
}
