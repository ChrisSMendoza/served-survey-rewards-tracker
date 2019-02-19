package com.chris.codes.served;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface RewardInfoDao {

    @Insert
    void insert(RewardInfo rewardInfo);

    @Update
    void update(RewardInfo... rewardInfos);

    @Delete
    void delete(RewardInfo... rewardInfos);

    @Query("SELECT * FROM reward_info_table WHERE id=:companyId")
    RewardInfo findRewardInfo(int companyId);

    @Query("DELETE FROM reward_info_table")
    void deleteAll();
}
