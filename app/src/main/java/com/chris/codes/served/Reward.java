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

    private Date expirationDate; // created based on date of completion

    @NonNull
    private String redeemCode;

    @NonNull
    @PrimaryKey
    private String companyId;

    public Reward(@NonNull String companyId, @NonNull String redeemCode) {

        // the number of days the user has to redeem
        // hard coded now, will come from other table
        int numDaysToRedeem = 30;
        Date today = new Date();
        expirationDate = DateUtil.addDays(today, numDaysToRedeem); // the last day to redeem reward

        this.redeemCode = redeemCode;
        this.companyId = companyId;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @NonNull
    String getRedeemCode() {
        return redeemCode;
    }
    public void setRedeemCode(@NonNull String redeemCode) {
        this.redeemCode = redeemCode;
    }

    String getCompanyId() {
        return companyId;
    }
    void setCompanyId(String companyId) {
        this.companyId = companyId;
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
