package com.chris.codes.served;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

// database only holds rewards
@Database(entities = {Reward.class}, version = 1)
public abstract class RewardRoomDatabase extends RoomDatabase {

    public abstract RewardDao rewardDao();

    // singleton pattern to prevent multiple instances of the database
    private static volatile RewardRoomDatabase INSTANCE;

    private static class PopulateDBAsync extends AsyncTask<Void, Void, Void> {

        private final RewardDao mDao;

        PopulateDBAsync(RewardRoomDatabase db) {
            mDao = db.rewardDao();
        }
        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();

            Reward reward = new Reward("7eleven-code");
            mDao.insert(reward);
            reward = new Reward("popeyes-code");
            mDao.insert(reward);

            return null;
        }

    }

    private static RoomDatabase.Callback sRewardDatabaseCallback =
            new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDBAsync(INSTANCE).execute();
        }
    };

    static RewardRoomDatabase getDatabase(final Context context) {

        if(INSTANCE == null) {
            synchronized (RewardRoomDatabase.class) {

                if(INSTANCE == null) {

                    // create database in the app context
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RewardRoomDatabase.class, "reward_database")
                            .addCallback(sRewardDatabaseCallback) // uncomment to delete any user
                            .build();                               // entries and add base entries

                }
            }
        }
        return INSTANCE;
    }
}
