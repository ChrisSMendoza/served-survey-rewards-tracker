package com.chris.codes.served;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

// tell Room that Reward is an entity to be stored in "reward_table"
@Entity(tableName = "reward_table")
public class Reward {

    @PrimaryKey // every entity needs one
    @NonNull
    @ColumnInfo(name = "redeem code") // column should be labeled with this instead of 'mRedeemCode'
    private String mRedeemCode;

    // keywords starting with @ are annotations
    // they make this class meaningful to a Room DB
    public Reward(@NonNull String code) {
        this.mRedeemCode = code;
    }

    public String getReward() {
        return this.mRedeemCode;
    }
}
