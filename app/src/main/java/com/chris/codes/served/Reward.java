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
    private String mCompanyId;
    private Date mExpiresOnDate; // created based on date of completion

    public Reward(@NonNull String companyId, @NonNull String redeemCode) {

        // the number of days the user has to redeem
        // hard coded now, will come from other table
        int numDaysToRedeem = 30;
        Date today = new Date();
        mExpiresOnDate = DateUtil.addDays(today, numDaysToRedeem); // the last day to redeem reward

        this.mRedeemCode = redeemCode;
        this.mCompanyId = companyId;
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

    String getCompanyId() {
        return mCompanyId;
    }

    void setCompanyId(String companyId) {
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
