package com.chris.codes.served;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

// a repository handles data operations, manages query threads, allows for multiple back ends
// basic use: decides to fetch from a network or use cached local data
public class RewardRepository {

    private RewardDao mRewardDao;
    private LiveData<List<Reward>> mAllRewards;

    RewardRepository(Application application) {

        // get the app's database to use the Reward Dao, and its getAllRewards method
        RewardRoomDatabase db = RewardRoomDatabase.getDatabase(application);
        mRewardDao = db.rewardDao();
        mAllRewards = mRewardDao.getAllRewards();
    }

    // wrapper for RewardDao.getAllRewards()
    LiveData<List<Reward>> getAllRewards() {
        return mAllRewards;
    }

    public void insert(Reward reward) {
        // insert can't be called on a UI thread, run it in the background
        new insertAsyncTask(mRewardDao).execute(reward);
    }

    private static class insertAsyncTask extends AsyncTask<Reward, Void, Void> {

        private RewardDao mAsyncTaskDao;

        insertAsyncTask(RewardDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Reward... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
