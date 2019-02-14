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

        private final TextView rewardItemView;

        private RewardViewHolder(View itemView) {
            super(itemView); // what text view is this grabbing?
            rewardItemView = itemView.findViewById(R.id.textView);
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
            holder.rewardItemView.setText(currentReward.getRedeemCode());
        }
        // data isn't ready yet
        else {
            holder.rewardItemView.setText("No rewards yet");
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
