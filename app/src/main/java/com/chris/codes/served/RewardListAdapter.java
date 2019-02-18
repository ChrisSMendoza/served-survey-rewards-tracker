package com.chris.codes.served;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RewardListAdapter extends RecyclerView.Adapter<RewardListAdapter.RewardViewHolder> {

    class RewardViewHolder extends RecyclerView.ViewHolder {

        private final TextView itemNameText;
        private final TextView conditionText;

        private RewardViewHolder(View itemView) {
            super(itemView);

            itemNameText = itemView.findViewById(R.id.item_name_text);
            conditionText = itemView.findViewById(R.id.reward_condition_text);
        }
    }


    private final LayoutInflater mInflater;
    private List<Reward> mRewards;

    RewardListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public RewardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new RewardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RewardViewHolder holder, int position) {

        if(mRewards != null) {

            Reward currentReward = mRewards.get(position);
            holder.itemNameText.setText(currentReward.getItemName());
            // DEV: USING COMPANY ID INSTEAD OF CONDITION SINCE CONDITION WILL COME FROM JOINING TABLES
            holder.conditionText.setText(currentReward.getCompanyId());
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
