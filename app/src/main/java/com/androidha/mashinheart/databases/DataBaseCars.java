package com.androidha.mashinheart.databases;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.androidha.mashinheart.R;
import com.androidha.mashinheart.models.ModelAddCar;

import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.internal.RealmObjectProxy;

public class DataBaseCars extends RealmObject {

    private Boolean AddServer;
    private Integer CarBodyInsurance;
    private int CarBrand;
    private String CarChassisNumber;
    private String CarColor;
    private Integer CarKm;
    private Integer CarPersonInsurance;
    private int CarTagIran;
    private int CarTagLeft;
    private String CarTagLetter;
    private int CarTagRight;
    private String CarType;
    private int CarUseAverage;
    private int CarYearCreated;
    private Boolean Gearbox;
    @PrimaryKey
    private Integer ID;
    private Integer LastChangeDate = 0;


    public void DataBaseCars(ModelAddCar modelAddCar) {
        setCarBrand(modelAddCar.getCarBrand());
        setCarYearCreated(modelAddCar.getCarYearCreated());
        setCarTagLeft(modelAddCar.getCarTagLeft());
        setCarTagRight(modelAddCar.getCarTagRight());
        setCarTagIran(modelAddCar.getCarTagIran());
        setGearbox(modelAddCar.getGearbox());
        setCarUseAverage(modelAddCar.getCarUseAverage());
        setCarType(modelAddCar.getCarType());
        setCarColor(modelAddCar.getCarColor());
        setCarPersonInsurance(modelAddCar.getCarPersonInsurance());
        setCarBodyInsurance(modelAddCar.getCarBodyInsurance());
        setCarChassisNumber(modelAddCar.getCarChassisNumber());
        setCarTagLetter(modelAddCar.getCarTagLetter());
        setCarKm(modelAddCar.getCarKm());
    }

    @BindingAdapter(value = {"CarBrand"})
    public static void setImageBrand(CircleImageView view, int CarBrand) {
        view.setImageResource(view.getResources().obtainTypedArray(R.array.CarLogo).getResourceId(CarBrand, 0));
    }


    @BindingAdapter(value = {"CarBrand","CarType","CarColor"})
    public static void setNameCar(TextView view, int CarBrand, String CarType, String CarColor) {
        String brand = view.getContext().getResources().getStringArray(R.array.CarBrand)[CarBrand];
        StringBuilder sb = new StringBuilder();
        sb.append(brand);
        sb.append(" ");
        sb.append(CarType);
        sb.append(" ");
        sb.append(CarColor);
        view.setText(sb.toString());
    }

    @BindingAdapter(value = {"ChangeDate"})
    public static void setChandeDate(TextView view, Integer ChangeDate) {
        if (String.valueOf(ChangeDate).length() < 8) {
            view.setText(view.getContext().getResources().getString(R.string.NotSaveEnything));
            return;
        }
        String m = String.valueOf(ChangeDate).substring(4, 6);
        switch (Integer.valueOf(m).intValue()) {
            case 1:
                m = "فروردين";
                break;
            case 2:
                m = "ارديبهشت";
                break;
            case 3:
                m = "خرداد";
                break;
            case 4:
                m = "تير";
                break;
            case 5:
                m = "مرداد";
                break;
            case 6:
                m = "شهريور";
                break;
            case 7:
                m = "مهر";
                break;
            case 8:
                m = "آبان";
                break;
            case 9:
                m = "آذر";
                break;
            case 10:
                m = "دي";
                break;
            case 11:
                m = "بهمن";
                break;
            case 12:
                m = "اسفند";
                break;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(ChangeDate).substring(6, 8));
        sb.append(" ");
        sb.append(m);
        sb.append(" ");
        sb.append(String.valueOf(ChangeDate).substring(0, 4));
        view.setText(sb.toString());
    }


    public Boolean getAddServer() {
        return AddServer;
    }

    public void setAddServer(Boolean addServer) {
        AddServer = addServer;
    }

    public Integer getCarBodyInsurance() {
        return CarBodyInsurance;
    }

    public void setCarBodyInsurance(Integer carBodyInsurance) {
        CarBodyInsurance = carBodyInsurance;
    }

    public int getCarBrand() {
        return CarBrand;
    }

    public void setCarBrand(int carBrand) {
        CarBrand = carBrand;
    }

    public String getCarChassisNumber() {
        return CarChassisNumber;
    }

    public void setCarChassisNumber(String carChassisNumber) {
        CarChassisNumber = carChassisNumber;
    }

    public String getCarColor() {
        return CarColor;
    }

    public void setCarColor(String carColor) {
        CarColor = carColor;
    }

    public Integer getCarKm() {
        return CarKm;
    }

    public void setCarKm(Integer carKm) {
        CarKm = carKm;
    }

    public Integer getCarPersonInsurance() {
        return CarPersonInsurance;
    }

    public void setCarPersonInsurance(Integer carPersonInsurance) {
        CarPersonInsurance = carPersonInsurance;
    }

    public int getCarTagIran() {
        return CarTagIran;
    }

    public void setCarTagIran(int carTagIran) {
        CarTagIran = carTagIran;
    }

    public int getCarTagLeft() {
        return CarTagLeft;
    }

    public void setCarTagLeft(int carTagLeft) {
        CarTagLeft = carTagLeft;
    }

    public String getCarTagLetter() {
        return CarTagLetter;
    }

    public void setCarTagLetter(String carTagLetter) {
        CarTagLetter = carTagLetter;
    }

    public int getCarTagRight() {
        return CarTagRight;
    }

    public void setCarTagRight(int carTagRight) {
        CarTagRight = carTagRight;
    }

    public String getCarType() {
        return CarType;
    }

    public void setCarType(String carType) {
        CarType = carType;
    }

    public int getCarUseAverage() {
        return CarUseAverage;
    }

    public void setCarUseAverage(int carUseAverage) {
        CarUseAverage = carUseAverage;
    }

    public int getCarYearCreated() {
        return CarYearCreated;
    }

    public void setCarYearCreated(int carYearCreated) {
        CarYearCreated = carYearCreated;
    }

    public Boolean getGearbox() {
        return Gearbox;
    }

    public void setGearbox(Boolean gearbox) {
        Gearbox = gearbox;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getLastChangeDate() {
        return LastChangeDate;
    }

    public void setLastChangeDate(Integer lastChangeDate) {
        LastChangeDate = lastChangeDate;
    }
}
