package com.chris.codes.served;


import java.io.Serializable;

public class DevReward implements Serializable {

    public String itemName;
    public String imageName;

    DevReward(String itemName, String imageName) {
        this.itemName = itemName;
        this.imageName = imageName;
    }
}
