package com.chris.codes.served;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

/*
    ViewModel must never reference a view, Lifecycle, or any class that may hold a reference
    to the activity context.

    ViewModel is not a replacement for the onSaveInstanceState() method, because the ViewModel does
    not survive a process shutdown.

    it takes the responsibility of holding and processing all the data needed by the UI
*/
public class RewardViewModel extends AndroidViewModel {

    private RewardRepository mRewardRepository;
    private LiveData<List<Reward>> mAllRewards; // to cache the list of rewards

    // set the reference to the Rewards repository and all of its Rewards
    public RewardViewModel(Application application) {
        super(application);

        mRewardRepository = new RewardRepository(application);
        mAllRewards = mRewardRepository.getAllRewards();
    }

    // getAllRewards(), insert() hide implementation from UI

    LiveData<List<Reward>> getAllRewards() { return mAllRewards; }

    public void insert(Reward reward) { mRewardRepository.insert(reward); }
}
