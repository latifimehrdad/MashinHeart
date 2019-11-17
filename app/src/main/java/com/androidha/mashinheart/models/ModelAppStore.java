package com.androidha.mashinheart.models;

import com.google.gson.annotations.SerializedName;

public class ModelAppStore {

    @SerializedName("Id")
    private int Id;
    @SerializedName("Name")
    private String Name;
    @SerializedName("MobileNumber")
    private String MobileNumber;
    @SerializedName("AppUserTypeId")
    private int AppUserTypeId;
    @SerializedName("AppUserType")
    private Object AppUserType;
    @SerializedName("Date")
    private String Date;
    @SerializedName("NameStore")
    private String NameStore;
    @SerializedName("FileNumber")
    private Object FileNumber;
    @SerializedName("Address")
    private String Address;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public int getAppUserTypeId() {
        return AppUserTypeId;
    }

    public void setAppUserTypeId(int appUserTypeId) {
        AppUserTypeId = appUserTypeId;
    }

    public Object getAppUserType() {
        return AppUserType;
    }

    public void setAppUserType(Object appUserType) {
        AppUserType = appUserType;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getNameStore() {
        return NameStore;
    }

    public void setNameStore(String nameStore) {
        NameStore = nameStore;
    }

    public Object getFileNumber() {
        return FileNumber;
    }

    public void setFileNumber(Object fileNumber) {
        FileNumber = fileNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
