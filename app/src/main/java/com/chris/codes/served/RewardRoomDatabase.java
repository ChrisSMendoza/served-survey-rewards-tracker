package com.chris.codes.served;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

// database only holds rewards
@Database(entities = {Reward.class}, version = 1)
public abstract class RewardRoomDatabase extends RoomDatabase {

    public abstract RewardDao rewardDao();

    // singleton pattern to prevent multiple instances of the database
    private static volatile RewardRoomDatabase INSTANCE;

    static RewardRoomDatabase getDatabase(final Context context) {

        if(INSTANCE == null) {
            synchronized (RewardRoomDatabase.class) {

                if(INSTANCE == null) {

                    // create database in the app context
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RewardRoomDatabase.class, "reward_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
