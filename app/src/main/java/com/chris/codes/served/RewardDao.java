package com.chris.codes.served;

import android.arch.lifecycle.LiveData;
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
    @Query("SELECT *" +
            "FROM reward_table " +
            "INNER JOIN reward_info_table ON reward_table.companyId=reward_info_table.id "+
            "ORDER BY itemName ASC")
    LiveData<List<Reward>> getAllRewards(); // live data syncs database changes to used data
}
