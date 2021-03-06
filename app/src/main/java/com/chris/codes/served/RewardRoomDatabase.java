package com.chris.codes.served;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.Date;

// database only holds rewards
@Database(entities = {Reward.class, RewardInfo.class}, version = 1)
@TypeConverters({Converters.class}) // date converters
public abstract class RewardRoomDatabase extends RoomDatabase {

    public abstract RewardDao rewardDao();
    public abstract RewardInfoDao rewardInfoDao();

    // singleton pattern to prevent multiple instances of the database
    private static volatile RewardRoomDatabase INSTANCE;

    private static class PopulateDBAsync extends AsyncTask<Void, Void, Void> {

        private final RewardDao rewardDao;
        private final RewardInfoDao rewardInfoDao;

        PopulateDBAsync(RewardRoomDatabase db) {
            rewardDao = db.rewardDao();
            rewardInfoDao = db.rewardInfoDao();
        }
        @Override
        protected Void doInBackground(final Void... params) {
            int popeyesId = 1;

            rewardDao.deleteAll();              // DEV: using today, should be purchase date
            Reward reward = new Reward(popeyesId, "NLURVA83SBR", new Date());
            rewardDao.insert(reward);


            String exName = "Popeyes";
            String exCond = "Comes with the purchase of a large drink";
            String exInstr = "Write code on receipt, and take a picture of the front and back";
            String exItemName = "2 Piece Chicken";
            int exNumDays = 365;

            rewardInfoDao.deleteAll();
            RewardInfo rewardInfo = new RewardInfo(popeyesId, exName, exCond, exInstr, exItemName, exNumDays);
            rewardInfoDao.insert(rewardInfo);

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
                            .addCallback(sRewardDatabaseCallback)   // uncomment to delete any user
                            .build();                               // entries and add base entries

                }
            }
        }
        return INSTANCE;
    }
}
