package com.androidha.mashinheart.models;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.androidha.mashinheart.R;
import com.google.gson.annotations.SerializedName;

public class ModelAdvertiseList {

    @SerializedName("Id")
    private int Id;
    @SerializedName("Title")
    private String Title;
    @SerializedName("Txt")
    private String Txt;
    @SerializedName("Txt2")
    private String Txt2;
    @SerializedName("Img")
    private String Img;
    @SerializedName("Seen")
    private int Seen;
    @SerializedName("Date")
    private String Date;
    @SerializedName("AppStoreId")
    private int AppStoreId;
    @SerializedName("AppStore")
    private ModelAppStore AppStore;
    @SerializedName("AdvertiseStatusId")
    private int AdvertiseStatusId;
    @SerializedName("AdvertiseStatus")
    private String AdvertiseStatus;
    @SerializedName("IsAdminAdvertise")
    private boolean IsAdminAdvertise;
    @SerializedName("Name")
    private String Name;
    @SerializedName("MobileNumber")
    private String MobileNumber;
    @SerializedName("Address")
    private String Address;


    @BindingAdapter(value = {"AdvertiseAddress", "StoreAddress"})
    public static void SetAdvertiseText(TextView textView, String AdvertiseAddress, ModelAppStore StoreAddress) {
        if (AdvertiseAddress != null) {
            textView.setText(AdvertiseAddress);
        } else {
            String tag = textView.getTag().toString();
            if (tag.equalsIgnoreCase("address"))
                textView.setText(StoreAddress.getAddress());
            else {
                textView.setVisibility(View.VISIBLE);
                if (StoreAddress != null)
                    textView.setText(textView.getContext().getResources().getString(R.string.Store) + " : " + StoreAddress.getNameStore());
                else
                    textView.setVisibility(View.GONE);
            }
        }
    }

    @BindingAdapter(value = {"Store"})
    public static void SetImageAgaring(ImageView imageView, Boolean IsAdminAdvertise) {
        if (!IsAdminAdvertise)
            imageView.setImageResource(R.drawable.ic_done_all_black_24dp);
        else
            imageView.setImageResource(R.drawable.ic_do_not_disturb_alt_black_24dp);

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTxt() {
        return Txt;
    }

    public void setTxt(String txt) {
        Txt = txt;
    }

    public String getTxt2() {
        return Txt2;
    }

    public void setTxt2(String txt2) {
        Txt2 = txt2;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public int getSeen() {
        return Seen;
    }

    public void setSeen(int seen) {
        Seen = seen;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getAppStoreId() {
        return AppStoreId;
    }

    public void setAppStoreId(int appStoreId) {
        AppStoreId = appStoreId;
    }

    public ModelAppStore getAppStore() {
        return AppStore;
    }

    public void setAppStore(ModelAppStore appStore) {
        AppStore = appStore;
    }

    public int getAdvertiseStatusId() {
        return AdvertiseStatusId;
    }

    public void setAdvertiseStatusId(int advertiseStatusId) {
        AdvertiseStatusId = advertiseStatusId;
    }

    public String getAdvertiseStatus() {
        return AdvertiseStatus;
    }

    public void setAdvertiseStatus(String advertiseStatus) {
        AdvertiseStatus = advertiseStatus;
    }

    public boolean isAdminAdvertise() {
        return IsAdminAdvertise;
    }

    public void setAdminAdvertise(boolean adminAdvertise) {
        IsAdminAdvertise = adminAdvertise;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

}
