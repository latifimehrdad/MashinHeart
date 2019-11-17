package com.androidha.mashinheart.databases;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.androidha.mashinheart.R;
import com.androidha.mashinheart.models.ModelRepair;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;

import java.text.DecimalFormat;
import java.text.NumberFormat;

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


    @BindingAdapter(value = "TextRepair")
    public static void setTextRepair(TextView textView, String title) {
        String tag = textView.getTag().toString();
        if (title != null) {
            switch (tag) {
                case "title":
                    StringBuilder sb = new StringBuilder();
                    sb.append(textView.getContext().getResources().getString(R.string.NewEventRepairTitle));
                    sb.append(" : ");
                    sb.append(title);
                    textView.setText(sb.toString());
                    break;

                case "brand":
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(textView.getContext().getResources().getString(R.string.NewEventChangeBrand));
                    sb2.append(" : ");
                    sb2.append(title);
                    textView.setText(sb2.toString());
                    break;

                case "repairWhy":
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(textView.getContext().getResources().getString(R.string.NewEventRepairWhy));
                    sb3.append(" : ");
                    sb3.append(title);
                    textView.setText(sb3.toString());
                    break;
            }
        } else {
            switch (tag) {
                case "title":
                    StringBuilder sb = new StringBuilder();
                    sb.append(textView.getContext().getResources().getString(R.string.NewEventRepairTitle));
                    sb.append(" : ");
                    sb.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb.toString());
                    break;

                case "brand":
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(textView.getContext().getResources().getString(R.string.NewEventChangeBrand));
                    sb2.append(" : ");
                    sb2.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb2.toString());
                    break;

                case "repairWhy":
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(textView.getContext().getResources().getString(R.string.NewEventRepairWhy));
                    sb3.append(" : ");
                    sb3.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb3.toString());
                    break;
            }
        }
    }


    @BindingAdapter(value = "ValueRepair")
    public static void setValueRepair(TextView textView, Integer value) {
        String tag = textView.getTag().toString();
        NumberFormat formatter = new DecimalFormat("#,###");
        if (value != null) {
            switch (tag) {
                case "repairDate":
                    String temp = String.valueOf(value);
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(temp.substring(0, 4));
                    sb3.append("/");
                    sb3.append(temp.substring(4, 6));
                    sb3.append("/");
                    sb3.append(temp.substring(6, 8));
                    String temp2 = sb3.toString();
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(textView.getContext().getResources().getString(R.string.NewEventRepairDate));
                    sb4.append(" : ");
                    sb4.append(temp2);
                    textView.setText(sb4.toString());
                    break;
                case "money":
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(textView.getContext().getResources().getString(R.string.NewEventChangeMoney));
                    sb2.append(" : ");
                    sb2.append(formatter.format(value));
                    textView.setText(sb2.toString());
                    break;

                case "repairKm":
                    StringBuilder sb = new StringBuilder();
                    sb.append(textView.getContext().getResources().getString(R.string.NewEventRepairKM));
                    sb.append(" : ");
                    sb.append(formatter.format(value));
                    textView.setText(sb.toString());
                    break;
            }
        } else {
            switch (tag) {
                case "repairDate":
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(textView.getContext().getResources().getString(R.string.NewEventRepairDate));
                    sb4.append(" : ");
                    sb4.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb4.toString());
                    break;
                case "money":
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(textView.getContext().getResources().getString(R.string.NewEventChangeMoney));
                    sb2.append(" : ");
                    sb2.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb2.toString());
                    break;

                case "repairKm":
                    StringBuilder sb = new StringBuilder();
                    sb.append(textView.getContext().getResources().getString(R.string.NewEventRepairKM));
                    sb.append(" : ");
                    sb.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb.toString());
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
