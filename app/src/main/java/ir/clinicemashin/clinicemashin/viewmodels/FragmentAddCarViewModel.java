package ir.clinicemashin.clinicemashin.viewmodels;

import android.content.Context;

import androidx.databinding.BaseObservable;

import ir.clinicemashin.clinicemashin.R;
import ir.clinicemashin.clinicemashin.databases.DataBaseCars;
import ir.clinicemashin.clinicemashin.models.ModelAddCar;
import ir.clinicemashin.clinicemashin.views.application.MachinHeartApplication;

import io.reactivex.subjects.PublishSubject;
import io.realm.Realm;

public class FragmentAddCarViewModel extends BaseObservable {
    private Integer CarBodyInsurance;
    public int CarBrand;
    private String CarChassisNumber;
    private String CarColor;
    private int CarKm;
    private Integer CarPersonInsurance;
    private int CarTagIran;
    private int CarTagLeft;
    private String CarTagLetter;
    private int CarTagRight;
    private String CarType;
    private int CarUseAverage;
    private int CarYearCreated;
    private Boolean Gearbox;
    private Integer ID;
    private Integer LastChangeDate;
    public PublishSubject<String> MessageType = PublishSubject.create();
    private Context context;


    public void SaveToDataBase() {//________________________________________________________________ Start SaveToDataBase
        try {
            Realm realm = MachinHeartApplication.getMachinHeartApplication(this.context).getRealmComponent().getRealm();
            Number currentIdNum = realm.where(DataBaseCars.class).max("ID");
            if (currentIdNum == null) {
                ID = Integer.valueOf(1);
            } else {
                ID = Integer.valueOf(currentIdNum.intValue() + 1);
            }
            ModelAddCar insert = new ModelAddCar(this);
            realm.beginTransaction();
            realm.createObject(DataBaseCars.class, this.ID).DataBaseCars(insert);
            realm.commitTransaction();
            ShowMessage(this.context.getResources().getString(R.string.SaveCommit));
        } catch (Exception ex) {
            ShowMessage(this.context.getResources().getString(R.string.SaveException));
        }
    }//_____________________________________________________________________________________________ End SaveToDataBase


    private void ShowMessage(String Type) {//_______________________________________________________ Start ShowMessage
        MessageType.onNext(Type);
    }//_____________________________________________________________________________________________ End ShowMessage


    public FragmentAddCarViewModel(Context context2) {//____________________________________________ Start FragmentAddCarViewModel
        this.context = context2;
    }//_____________________________________________________________________________________________ End FragmentAddCarViewModel


    public void setContext(Context context2) {
        this.context = context2;
    }


    public int getCarBrand() {
        return this.CarBrand;
    }


    public void setCarBrand(int carBrand) {
        this.CarBrand = carBrand;
    }


    public int getCarYearCreated() {
        return this.CarYearCreated;
    }


    public void setCarYearCreated(int carYearCreated) {
        this.CarYearCreated = carYearCreated;
    }


    public int getCarUseAverage() {
        return this.CarUseAverage;
    }


    public void setCarUseAverage(int carUseAverage) {
        this.CarUseAverage = carUseAverage;
    }


    public int getCarTagLeft() {
        return this.CarTagLeft;
    }

    public void setCarTagLeft(int carTagLeft) {
        this.CarTagLeft = carTagLeft;
    }

    public int getCarTagRight() {
        return this.CarTagRight;
    }

    public void setCarTagRight(int carTagRight) {
        this.CarTagRight = carTagRight;
    }

    public int getCarTagIran() {
        return this.CarTagIran;
    }

    public void setCarTagIran(int carTagIran) {
        this.CarTagIran = carTagIran;
    }

    public Boolean getGearbox() {
        return this.Gearbox;
    }

    public void setGearbox(Boolean gearbox) {
        this.Gearbox = gearbox;
    }

    public String getCarType() {
        return this.CarType;
    }

    public void setCarType(String carType) {
        this.CarType = carType;
    }

    public String getCarColor() {
        return this.CarColor;
    }

    public void setCarColor(String carColor) {
        this.CarColor = carColor;
    }

    public Integer getCarPersonInsurance() {
        return this.CarPersonInsurance;
    }

    public void setCarPersonInsurance(Integer carPersonInsurance) {
        this.CarPersonInsurance = carPersonInsurance;
    }

    public Integer getCarBodyInsurance() {
        return this.CarBodyInsurance;
    }

    public void setCarBodyInsurance(Integer carBodyInsurance) {
        this.CarBodyInsurance = carBodyInsurance;
    }

    public String getCarChassisNumber() {
        return this.CarChassisNumber;
    }

    public void setCarChassisNumber(String carChassisNumber) {
        this.CarChassisNumber = carChassisNumber;
    }

    public String getCarTagLetter() {
        return this.CarTagLetter;
    }

    public void setCarTagLetter(String carTagLetter) {
        this.CarTagLetter = carTagLetter;
    }

    public Integer getID() {
        return this.ID;
    }

    public void setID(Integer ID2) {
        this.ID = ID2;
    }

    public int getCarKm() {
        return this.CarKm;
    }

    public void setCarKm(int carKm) {
        this.CarKm = carKm;
    }

    public Integer getLastChangeDate() {
        return this.LastChangeDate;
    }

    public void setLastChangeDate(Integer lastChangeDate) {
        this.LastChangeDate = lastChangeDate;
    }
}
