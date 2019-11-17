package com.androidha.mashinheart.databases;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.androidha.mashinheart.R;
import com.androidha.mashinheart.models.ModelInsurance;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;

import java.text.DecimalFormat;
import java.text.NumberFormat;

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

    @BindingAdapter(value = "TextInsurance")
    public static void setTextInsurance(TextView textView, String title) {
        if (title != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(textView.getContext().getResources().getString(R.string.InsuranceName));
            sb.append(" : ");
            sb.append(title);
            textView.setText(sb.toString());
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(textView.getContext().getResources().getString(R.string.InsuranceName));
            sb.append(" : ");
            sb.append(textView.getContext().getResources().getString(R.string.IsEmpty));
            textView.setText(sb.toString());
        }
    }


    @BindingAdapter(value = "ValueInsurance")
    public static void setValueInsurance(TextView textView, Integer value) {
        String tag = textView.getTag().toString();
        NumberFormat formatter = new DecimalFormat("#,###");
        if (value != null) {
            switch (tag) {
                case "insuranceType":
                    String type = textView.getContext().getResources().getStringArray(R.array.InsuranceType)[value];
                    StringBuilder sb = new StringBuilder();
                    sb.append(textView.getContext().getResources().getString(R.string.InsuranceType));
                    sb.append(" : ");
                    sb.append(type);
                    textView.setText(sb.toString());
                    break;
                case "insuranceDate":
                    String temp = String.valueOf(value);
                    StringBuilder sb1 = new StringBuilder();
                    sb1.append(temp.substring(0, 4));
                    sb1.append("/");
                    sb1.append(temp.substring(4, 6));
                    sb1.append("/");
                    sb1.append(temp.substring(6, 8));
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(textView.getContext().getResources().getString(R.string.InsuranceDate));
                    sb2.append(" : ");
                    sb2.append(sb1.toString());
                    textView.setText(sb2.toString());
                    break;

                case "money":
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(textView.getContext().getResources().getString(R.string.NewEventChangeMoney));
                    sb3.append(" : ");
                    sb3.append(formatter.format(value));
                    textView.setText(sb3.toString());
                    break;
            }
        } else {
            switch (tag) {
                case "insuranceType":
                    StringBuilder sb = new StringBuilder();
                    sb.append(textView.getContext().getResources().getString(R.string.InsuranceType));
                    sb.append(" : ");
                    sb.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb.toString());
                    break;
                case "insuranceDate":
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(textView.getContext().getResources().getString(R.string.InsuranceDate));
                    sb2.append(" : ");
                    sb2.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb2.toString());
                    break;

                case "money":
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(textView.getContext().getResources().getString(R.string.NewEventChangeMoney));
                    sb3.append(" : ");
                    sb3.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb3.toString());
                    break;
            }
        }
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
