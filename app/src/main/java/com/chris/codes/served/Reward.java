package com.chris.codes.served;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

// keywords starting with @ are annotations
// they make this class meaningful to a Room DB
// tell Room that Reward is an entity to be stored in "reward_table"
@Entity(tableName = "reward_table")
public class Reward {

    private String name; // set by joining with reward_info_table
    private String itemName;
    private String condition;
    private String instructions;
    private int numDaysToRedeem;

    private Date purchaseDate;
    private Date expirationDate; // created based on date of purchase

    @PrimaryKey @NonNull
    private String redeemCode;

    @NonNull private int companyId;

    public Reward(@NonNull int companyId, @NonNull String redeemCode, Date purchaseDate) {

        this.purchaseDate = purchaseDate;
        this.redeemCode = redeemCode;
        this.companyId = companyId;
    }

    public Date getExpirationDate() {

        return DateUtil.addDays(purchaseDate, numDaysToRedeem);
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

    int getCompanyId() {
        return companyId;
    }
    void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCondition() {
        return condition;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getInstructions() {
        return instructions;
    }
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getNumDaysToRedeem() {
        return numDaysToRedeem;
    }
    public void setNumDaysToRedeem(int numDaysToRedeem) {
        this.numDaysToRedeem = numDaysToRedeem;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}

