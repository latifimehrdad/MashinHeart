package com.androidha.mashinheart.models;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.androidha.mashinheart.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ModelPoliceFine {
    private String Bill;
    private String City;
    private String Code;
    private String Date;
    private String Discription;
    private String Location;
    private String Pay;
    private Integer Price;
    private String Serial;
    private String Type;

    public ModelPoliceFine(String type, String discription, String code, Integer price, String location, String city, String date, String serial, String pay, String bill) {
        this.Type = type;
        this.Discription = discription;
        this.Code = code;
        this.Price = price;
        this.Location = location;
        this.City = city;
        this.Date = date;
        this.Serial = serial;
        this.Pay = pay;
        this.Bill = bill;
    }

    @BindingAdapter(value = "IntFine")
    public static void SetIntFine(TextView textView, Integer price) {
        if(price != null) {
            NumberFormat formatter = new DecimalFormat("#,###");
            StringBuilder sb = new StringBuilder();
            sb.append(textView.getContext().getResources().getString(R.string.InfringementPrice));
            sb.append(" : ");
            sb.append(formatter.format(price));
            sb.append(" ");
            sb.append(textView.getContext().getResources().getString(R.string.Rial));
            textView.setText(sb.toString());
        }
        else{
            NumberFormat formatter = new DecimalFormat("#,###");
            StringBuilder sb = new StringBuilder();
            sb.append(textView.getContext().getResources().getString(R.string.InfringementPrice));
            sb.append(" : ");
            sb.append(textView.getContext().getResources().getString(R.string.IsEmpty));
            textView.setText(sb.toString());
        }
    }


    @BindingAdapter(value = "TextFine")
    public static void setTextFine(TextView textView, String Value) {
        String tag = textView.getTag().toString();
        if(Value != null){

            switch (tag){
                case "serial":
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append(textView.getContext().getResources().getString(R.string.InfringementSerial));
                    sb7.append(" : ");
                    sb7.append(Value);
                    textView.setText(sb7.toString());
                    break;

                case "pay":
                    StringBuilder sb8 = new StringBuilder();
                    sb8.append(textView.getContext().getResources().getString(R.string.InfringementPay));
                    sb8.append(" : ");
                    sb8.append(Value);
                    textView.setText(sb8.toString());
                    break;

                case "bill":
                    StringBuilder sb9 = new StringBuilder();
                    sb9.append(textView.getContext().getResources().getString(R.string.InfringementBill));
                    sb9.append(" : ");
                    sb9.append(Value);
                    textView.setText(sb9.toString());
                    break;

                case "city":
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append(textView.getContext().getResources().getString(R.string.InfringementCity));
                    sb6.append(" : ");
                    sb6.append(Value);
                    textView.setText(sb6.toString());
                    break;

                case "code":
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(textView.getContext().getResources().getString(R.string.InfringementCode));
                    sb4.append(" : ");
                    sb4.append(Value);
                    textView.setText(sb4.toString());
                    break;

                case "date":
                    StringBuilder sb = new StringBuilder();
                    sb.append(textView.getContext().getResources().getString(R.string.InfringementDate));
                    sb.append(" : ");
                    sb.append(Value);
                    textView.setText(sb.toString());
                    break;

                case "type":
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(textView.getContext().getResources().getString(R.string.InfringementType));
                    sb2.append(" : ");
                    sb2.append(Value);
                    textView.setText(sb2.toString());
                    break;

                case "description":
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(textView.getContext().getResources().getString(R.string.InfringementDiscription));
                    sb3.append(" : ");
                    sb3.append(Value);
                    textView.setText(sb3.toString());
                    break;

                case "location":
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(textView.getContext().getResources().getString(R.string.InfringementLocation));
                    sb5.append(" : ");
                    sb5.append(Value);
                    textView.setText(sb5.toString());
                    break;
            }
        }
        else{
            switch (tag){
                case "serial":
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append(textView.getContext().getResources().getString(R.string.InfringementSerial));
                    sb7.append(" : ");
                    sb7.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb7.toString());
                    break;

                case "pay":
                    StringBuilder sb8 = new StringBuilder();
                    sb8.append(textView.getContext().getResources().getString(R.string.InfringementPay));
                    sb8.append(" : ");
                    sb8.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb8.toString());
                    break;

                case "bill":
                    StringBuilder sb9 = new StringBuilder();
                    sb9.append(textView.getContext().getResources().getString(R.string.InfringementBill));
                    sb9.append(" : ");
                    sb9.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb9.toString());
                    break;

                case "city":
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append(textView.getContext().getResources().getString(R.string.InfringementCity));
                    sb6.append(" : ");
                    sb6.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb6.toString());
                    break;

                case "code":
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(textView.getContext().getResources().getString(R.string.InfringementCode));
                    sb4.append(" : ");
                    sb4.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb4.toString());
                    break;

                case "date":
                    StringBuilder sb = new StringBuilder();
                    sb.append(textView.getContext().getResources().getString(R.string.InfringementDate));
                    sb.append(" : ");
                    sb.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb.toString());
                    break;

                case "type":
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(textView.getContext().getResources().getString(R.string.InfringementType));
                    sb2.append(" : ");
                    sb2.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb2.toString());
                    break;

                case "description":
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(textView.getContext().getResources().getString(R.string.InfringementDiscription));
                    sb3.append(" : ");
                    sb3.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb3.toString());
                    break;

                case "location":
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(textView.getContext().getResources().getString(R.string.InfringementLocation));
                    sb5.append(" : ");
                    sb5.append(textView.getContext().getResources().getString(R.string.IsEmpty));
                    textView.setText(sb5.toString());
                    break;
            }
        }

    }

    public String getType() {
        return this.Type;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public String getDiscription() {
        return this.Discription;
    }

    public void setDiscription(String discription) {
        this.Discription = discription;
    }

    public String getCode() {
        return this.Code;
    }

    public void setCode(String code) {
        this.Code = code;
    }

    public Integer getPrice() {
        return this.Price;
    }

    public void setPrice(Integer price) {
        this.Price = price;
    }

    public String getLocation() {
        return this.Location;
    }

    public void setLocation(String location) {
        this.Location = location;
    }

    public String getCity() {
        return this.City;
    }

    public void setCity(String city) {
        this.City = city;
    }

    public String getDate() {
        return this.Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public String getSerial() {
        return this.Serial;
    }

    public void setSerial(String serial) {
        this.Serial = serial;
    }

    public String getPay() {
        return this.Pay;
    }

    public void setPay(String pay) {
        this.Pay = pay;
    }

    public String getBill() {
        return this.Bill;
    }

    public void setBill(String bill) {
        this.Bill = bill;
    }
}
