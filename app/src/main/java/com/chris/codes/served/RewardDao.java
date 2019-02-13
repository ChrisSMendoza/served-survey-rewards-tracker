package com.chris.codes.served;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

// data access object to create a cleaner API on top of SQL queries
@Dao
public interface RewardDao {

    @Insert // no SQL needed for single insert
    void insert(Reward reward);

    @Query("DELETE FROM reward_table")
    void deleteAll();

    // NOTE: ascending order isn't needed here, but makes testing easier
    @Query("SELECT * FROM reward_table ORDER BY redeemCode ASC")
    List<Reward> getAllRewards();
}
