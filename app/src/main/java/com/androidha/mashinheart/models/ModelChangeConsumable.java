package com.androidha.mashinheart.models;


import com.androidha.mashinheart.viewmodels.FragmentConsumablesViewModel;

public class ModelChangeConsumable {
    private String Brand;
    private Integer CarId;
    private Integer ChangeDate;
    private Integer ChangeKm;
    private Integer ChangeNextKm;
    private Integer ID;
    private Integer Money;
    private String Title;

    public ModelChangeConsumable(FragmentConsumablesViewModel viewModel) {
        this.ID = viewModel.getID();
        this.CarId = viewModel.getCarId();
        this.ChangeKm = viewModel.getChangeKm();
        this.ChangeNextKm = viewModel.getChangeNextKm();
        this.Money = viewModel.getMoney();
        this.Brand = viewModel.getBrand();
        this.Title = viewModel.getTitle();
        this.ChangeDate = viewModel.getChangeDate();
    }

    public Integer getID() {
        return this.ID;
    }

    public void setID(Integer ID2) {
        this.ID = ID2;
    }

    public Integer getCarId() {
        return this.CarId;
    }

    public void setCarId(Integer carId) {
        this.CarId = carId;
    }

    public Integer getChangeKm() {
        return this.ChangeKm;
    }

    public void setChangeKm(Integer changeKm) {
        this.ChangeKm = changeKm;
    }

    public Integer getChangeNextKm() {
        return this.ChangeNextKm;
    }

    public void setChangeNextKm(Integer changeNextKm) {
        this.ChangeNextKm = changeNextKm;
    }

    public Integer getMoney() {
        return this.Money;
    }

    public void setMoney(Integer money) {
        this.Money = money;
    }

    public String getBrand() {
        return this.Brand;
    }

    public void setBrand(String brand) {
        this.Brand = brand;
    }

    public String getTitle() {
        return this.Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public Integer getChangeDate() {
        return this.ChangeDate;
    }

    public void setChangeDate(Integer changeDate) {
        this.ChangeDate = changeDate;
    }
}
