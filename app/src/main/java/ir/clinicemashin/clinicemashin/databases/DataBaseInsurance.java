package ir.clinicemashin.clinicemashin.databases;

import ir.clinicemashin.clinicemashin.models.ModelInsurance;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class DataBaseInsurance extends RealmObject {
    private Boolean AddServer;
    private Integer CarId;
    @PrimaryKey
    private Integer ID;
    private Integer InsuranceDate;
    private int InsuranceType;
    private Integer Money;
    private String Title;
    private int notification = 0;


    public void DataBaseInsurance(ModelInsurance modelInsurance) {
        setCarId(modelInsurance.getCarId());
        setInsuranceType(modelInsurance.getInsuranceType());
        setTitle(modelInsurance.getTitle());
        setMoney(modelInsurance.getMoney());
        setInsuranceDate(modelInsurance.getInsuranceDate());
    }


    public Boolean getAddServer() {
        return AddServer;
    }

    public void setAddServer(Boolean addServer) {
        AddServer = addServer;
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

    public Integer getInsuranceDate() {
        return InsuranceDate;
    }

    public void setInsuranceDate(Integer insuranceDate) {
        InsuranceDate = insuranceDate;
    }

    public int getInsuranceType() {
        return InsuranceType;
    }

    public void setInsuranceType(int insuranceType) {
        InsuranceType = insuranceType;
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
