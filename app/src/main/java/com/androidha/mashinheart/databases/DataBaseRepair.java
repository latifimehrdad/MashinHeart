package com.androidha.mashinheart.databases;

import com.androidha.mashinheart.models.ModelRepair;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DataBaseRepair extends RealmObject {
    private Boolean AddServer;
    private String Brand;
    private Integer CarId;
    @PrimaryKey
    private Integer ID;
    private Integer Money;
    private Integer RepairDate;
    private Integer RepairKm;
    private String RepairWhy;
    private String Title;


    public void DataBaseRepair(ModelRepair model) {
        setCarId(model.getCarId());
        setRepairKm(model.getRepairKm());
        setRepairWhy(model.getRepairWhy());
        setMoney(model.getMoney());
        setBrand(model.getBrand());
        setTitle(model.getTitle());
        setRepairDate(model.getRepairDate());
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

    public Integer getRepairDate() {
        return RepairDate;
    }

    public void setRepairDate(Integer repairDate) {
        RepairDate = repairDate;
    }

    public Integer getRepairKm() {
        return RepairKm;
    }

    public void setRepairKm(Integer repairKm) {
        RepairKm = repairKm;
    }

    public String getRepairWhy() {
        return RepairWhy;
    }

    public void setRepairWhy(String repairWhy) {
        RepairWhy = repairWhy;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
