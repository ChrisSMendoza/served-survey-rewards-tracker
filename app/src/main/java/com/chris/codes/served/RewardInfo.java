package com.chris.codes.served;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "reward_info_table")
public class RewardInfo {

    @NonNull
    @PrimaryKey
    private int id;
    private String name;
    private String itemName;
    private String condition;
    private String instructions;
    private int numDaysToRedeem;

    public RewardInfo(int id, String name, String condition, String instructions, String itemName, int numDaysToRedeem) {

        this.id = id;
        this.name = name;
        this.itemName = itemName;
        this.condition = condition;
        this.instructions = instructions;
        this.numDaysToRedeem = numDaysToRedeem;
    }

    @NonNull
    public int getId() { return id; }

    public void setId(@NonNull int id) {
        this.id = id;
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

    public String getItemName() { return itemName; }

    public void setItemName(String itemName) { this.itemName = itemName; }
}