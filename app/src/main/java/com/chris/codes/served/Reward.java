package com.chris.codes.served;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

// keywords starting with @ are annotations
// they make this class meaningful to a Room DB
// tell Room that Reward is an entity to be stored in "reward_table"
@Entity(tableName = "reward_table")
public class Reward {

    @PrimaryKey // every entity needs one
    @NonNull
    @ColumnInfo(name = "redeemCode") // column should be labeled with this instead of 'mRedeemCode'
    private String mRedeemCode;
    private Date mExpiresOnDate; // created based on date of completion
    private int mCompanyId;


    public Reward(@NonNull String redeemCode) {
        this.mRedeemCode = redeemCode;
    }

    @NonNull
    String getRedeemCode() {
        return mRedeemCode;
    }

    public void setRedeemCode(@NonNull String redeemCode) {
        this.mRedeemCode = redeemCode;
    }

    Date getExpiresOnDate() {
        return mExpiresOnDate;
    }

    void setExpiresOnDate(Date expiresOnDate) {
        this.mExpiresOnDate = expiresOnDate;
    }

    int getCompanyId() {
        return mCompanyId;
    }

    void setCompanyId(int companyId) {
        this.mCompanyId = companyId;
    }
}
/*
Example:
Popeyes
Unlimited
Never
Please write the validation code on your receipt. You will need to bring the receipt
with the validation code back to the restaurant to redeem the offer.

With the purchase of a large drink
*/
