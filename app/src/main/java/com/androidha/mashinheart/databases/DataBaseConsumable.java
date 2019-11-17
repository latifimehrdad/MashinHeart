package com.androidha.mashinheart.databases;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.androidha.mashinheart.R;
import com.androidha.mashinheart.models.ModelChangeConsumable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;
import java.text.DecimalFormat;
import java.text.NumberFormat;

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


    @BindingAdapter(value = "TextConsumable")
    public static void setTextConsumable(TextView textView, String title) {
        String tag = textView.getTag().toString();
        if (title != null) {
            if (tag.equalsIgnoreCase("title")) {
                StringBuilder sb = new StringBuilder();
                sb.append(textView.getContext().getResources().getString(R.string.NewEventChangeTitle));
                sb.append(" : ");
                sb.append(title);
                textView.setText(sb.toString());
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(textView.getContext().getResources().getString(R.string.NewEventChangeBrand));
                sb.append(" : ");
                sb.append(title);
                textView.setText(sb.toString());
            }
        }
        else {
            if (tag.equalsIgnoreCase("title")) {
                StringBuilder sb = new StringBuilder();
                sb.append(textView.getContext().getResources().getString(R.string.NewEventChangeTitle));
                sb.append(" : ");
                sb.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                textView.setText(sb.toString());
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(textView.getContext().getResources().getString(R.string.NewEventChangeBrand));
                sb.append(" : ");
                sb.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                textView.setText(sb.toString());
            }
        }
    }


    @BindingAdapter(value = "ValueConsumable")
    public static void setValueConsumable(TextView textView, Integer value) {
        char c;
        TextView textView2 = textView;
        String tag = textView.getTag().toString();
        NumberFormat formatter = new DecimalFormat("#,###");
        if (value != null) {
            switch (tag) {
                case "changeKm":
                    StringBuilder sb = new StringBuilder();
                    sb.append(textView.getContext().getResources().getString(R.string.NewEventChangeKM));
                    sb.append(" : ");
                    sb.append(formatter.format(value));
                    textView2.setText(sb.toString());
                    break;
                case "money":
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(textView.getContext().getResources().getString(R.string.NewEventChangeMoney));
                    sb3.append(" : ");
                    sb3.append(formatter.format(value));
                    textView2.setText(sb3.toString());
                    break;
                case "changeNextKm":
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(textView.getContext().getResources().getString(R.string.NewEventChangeNextKM));
                    sb2.append(" : ");
                    sb2.append(formatter.format(value));
                    textView2.setText(sb2.toString());
                    break;
                case "changeDate":
                    String temp = String.valueOf(value);
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(temp.substring(0, 4));
                    sb4.append("/");
                    sb4.append(temp.substring(4, 6));
                    sb4.append("/");
                    sb4.append(temp.substring(6, 8));
                    String temp2 = sb4.toString();
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(textView.getContext().getResources().getString(R.string.NewEventChangeDate));
                    sb5.append(" : ");
                    sb5.append(temp2);
                    textView2.setText(sb5.toString());
                    break;

            }
        } else {
            switch (tag) {
                case "changeKm":
                    StringBuilder sb = new StringBuilder();
                    sb.append(textView.getContext().getResources().getString(R.string.NewEventChangeKM));
                    sb.append(" : ");
                    sb.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView2.setText(sb.toString());
                    break;
                case "money":
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(textView.getContext().getResources().getString(R.string.NewEventChangeMoney));
                    sb3.append(" : ");
                    sb3.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView2.setText(sb3.toString());
                    break;
                case "changeNextKm":
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(textView.getContext().getResources().getString(R.string.NewEventChangeNextKM));
                    sb2.append(" : ");
                    sb2.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView2.setText(sb2.toString());
                    break;
                case "changeDate":
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(textView.getContext().getResources().getString(R.string.NewEventChangeDate));
                    sb5.append(" : ");
                    sb5.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView2.setText(sb5.toString());
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
