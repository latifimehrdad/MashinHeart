package com.androidha.mashinheart.databases;

import com.androidha.mashinheart.models.ModelChangeConsumable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DataBaseConsumable extends RealmObject {
    private Boolean AddServer;
    private String Brand;
    private Integer CarId;
    private Integer ChangeDate;
    private Integer ChangeKm;
    private Integer ChangeNextKm;
    @PrimaryKey
    private Integer ID;
    private Integer Money;
    private String Title;
    private int notification = 0;


    public void DataBaseConsumable(ModelChangeConsumable model) {
        setCarId(model.getCarId());
        setChangeKm(model.getChangeKm());
        setChangeNextKm(model.getChangeNextKm());
        setMoney(model.getMoney());
        setBrand(model.getBrand());
        setTitle(model.getTitle());
        setChangeDate(model.getChangeDate());
    }


    public Boolean getAddServer() {
        return AddServer;
    }

    public void setAddServer(Boolean addServer) {
        AddServer = addServer;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public Integer getCarId() {
        return CarId;
    }

    public void setCarId(Integer carId) {
        CarId = carId;
    }

    public Integer getChangeDate() {
        return ChangeDate;
    }

    public void setChangeDate(Integer changeDate) {
        ChangeDate = changeDate;
    }

    public Integer getChangeKm() {
        return ChangeKm;
    }

    public void setChangeKm(Integer changeKm) {
        ChangeKm = changeKm;
    }

    public Integer getChangeNextKm() {
        return ChangeNextKm;
    }

    public void setChangeNextKm(Integer changeNextKm) {
        ChangeNextKm = changeNextKm;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getMoney() {
        return Money;
    }

    public void setMoney(Integer money) {
        Money = money;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getNotification() {
        return notification;
    }

    public void setNotification(int notification) {
        this.notification = notification;
    }
}
