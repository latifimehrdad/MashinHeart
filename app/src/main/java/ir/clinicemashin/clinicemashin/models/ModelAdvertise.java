package ir.clinicemashin.clinicemashin.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ModelAdvertise {

    @SerializedName("Statue")
    private int Statue;
    @SerializedName("Message")
    private String Message;
    @SerializedName("AdvertiseList")
    private ArrayList<ModelAdvertiseList> AdvertiseList;

    public int getStatue() {
        return Statue;
    }

    public void setStatue(int statue) {
        Statue = statue;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public ArrayList<ModelAdvertiseList> getAdvertiseList() {
        return AdvertiseList;
    }

    public void setAdvertiseList(ArrayList<ModelAdvertiseList> advertiseList) {
        AdvertiseList = advertiseList;
    }

}
