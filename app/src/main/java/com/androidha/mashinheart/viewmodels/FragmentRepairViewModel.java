package com.androidha.mashinheart.viewmodels;

import android.content.Context;

import com.androidha.mashinheart.R;
import com.androidha.mashinheart.databases.DataBaseCars;
import com.androidha.mashinheart.databases.DataBaseRepair;
import com.androidha.mashinheart.models.ModelRepair;
import com.androidha.mashinheart.views.activitys.MainActivity;
import com.androidha.mashinheart.views.application.MachinHeartApplication;

import io.reactivex.subjects.PublishSubject;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class FragmentRepairViewModel {
    private String Brand;
    private Integer CarId;
    private Integer ID;
    public PublishSubject<String> MessageType = PublishSubject.create();
    private Integer Money;
    private Integer RepairDate;
    private Integer RepairKm;
    private String RepairWhy;
    private String Title;
    private Context context;

    public FragmentRepairViewModel(Context context) {//____________________________________________ Start FragmentRepairViewModel
        this.context = context;
    }//_____________________________________________________________________________________________ End FragmentRepairViewModel


    private void ShowMessage(String Type) {//_______________________________________________________ Start ShowMessage
        this.MessageType.onNext(Type);
    }//_____________________________________________________________________________________________ End ShowMessage


    public void SaveToDataBase() {//________________________________________________________________ Start ShowMessage

        String message = CheckBeforSave(0);
        switch (message) {
            case "Ok":
                FinalSave();
                break;
            case "Error":
                ShowMessage(this.context.getResources().getString(R.string.SaveException));
                break;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append(getTitle());
                sb.append(" را در ");
                sb.append(message);
                sb.append(" ");
                sb.append(this.context.getResources().getString(R.string.RepairChange));
                ShowMessage(sb.toString());
                break;
        }

    }//_____________________________________________________________________________________________ End ShowMessage


    private void FinalSave() {//____________________________________________________________________ Start FinalSave
        try {
            Realm realm = MachinHeartApplication
                    .getMachinHeartApplication(this.context)
                    .getRealmComponent()
                    .getRealm();

            Number currentIdNum = realm.where(DataBaseRepair.class).max("ID");
            if (currentIdNum == null) {
                this.ID = 1;
            } else {
                this.ID = currentIdNum.intValue() + 1;
            }
            ModelRepair insert = new ModelRepair(this);
            realm.beginTransaction();
            realm.createObject(DataBaseRepair.class, this.ID).DataBaseRepair(insert);
            realm.commitTransaction();

            RealmResults<DataBaseCars> cars = realm
                    .where(DataBaseCars.class)
                    .equalTo("ID", MainActivity.CarId)
                    .findAll();

            realm.beginTransaction();
            if (cars.first().getCarKm() < insert.getRepairKm()) {
                cars.first().setCarKm(insert.getRepairKm());
            }
            if (cars.first().getLastChangeDate() < insert.getRepairDate()) {
                cars.first().setLastChangeDate(insert.getRepairDate());
            }
            realm.commitTransaction();
            ShowMessage(this.context.getResources().getString(R.string.SaveCommit));
        } catch (Exception ex) {
            ShowMessage(this.context.getResources().getString(R.string.SaveException));
        }
    }//_____________________________________________________________________________________________ End FinalSave


    public void EditDataBase(DataBaseRepair dataBaseRepair) {//_____________________________________ Start EditDataBase

        String message = CheckBeforSave(dataBaseRepair.getID());
        switch (message) {
            case "Ok":
                FinalEdit(dataBaseRepair);
                break;
            case "Error":
                ShowMessage(this.context.getResources().getString(R.string.SaveException));
                break;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append(getTitle());
                sb.append(" را در ");
                sb.append(message);
                sb.append(" ");
                sb.append(this.context.getResources().getString(R.string.RepairChange));
                ShowMessage(sb.toString());
                break;
        }

    }//_____________________________________________________________________________________________ End EditDataBase


    private void FinalEdit(DataBaseRepair dataBaseRepair) {//_______________________________________ Start FinalEdit
        try {
            Realm realm = MachinHeartApplication
                    .getMachinHeartApplication(this.context)
                    .getRealmComponent()
                    .getRealm();

            realm.beginTransaction();
            dataBaseRepair.setTitle(getTitle());
            dataBaseRepair.setRepairKm(getRepairKm());
            dataBaseRepair.setRepairWhy(getRepairWhy());
            dataBaseRepair.setBrand(getBrand());
            dataBaseRepair.setMoney(getMoney());
            dataBaseRepair.setRepairDate(getRepairDate());
            realm.commitTransaction();

            RealmResults<DataBaseCars> cars = realm
                    .where(DataBaseCars.class)
                    .equalTo("ID", MainActivity.CarId)
                    .findAll();

            realm.beginTransaction();
            if (cars.first().getCarKm() < dataBaseRepair.getRepairKm()) {
                cars.first().setCarKm(dataBaseRepair.getRepairKm());
            }
            if (cars.first().getLastChangeDate() < dataBaseRepair.getRepairDate()) {
                cars.first().setLastChangeDate(dataBaseRepair.getRepairDate());
            }
            realm.commitTransaction();
            ShowMessage(this.context.getResources().getString(R.string.EditCommit));
        } catch (Exception ex) {
            ShowMessage(this.context.getResources().getString(R.string.SaveException));
        }
    }//_____________________________________________________________________________________________ End FinalEdit


    public void DeleteDataBase(DataBaseRepair dataBaseRepair) {//___________________________________ Start DeleteDataBase
        try {
            Realm realm = MachinHeartApplication.getMachinHeartApplication(this.context).getRealmComponent().getRealm();
            Integer id = dataBaseRepair.getID();
            String title = dataBaseRepair.getTitle();
            realm.beginTransaction();
            dataBaseRepair.deleteFromRealm();
            realm.commitTransaction();
            ShowMessage(this.context.getResources().getString(R.string.DeleteCommit));
        } catch (Exception e) {
            ShowMessage(this.context.getResources().getString(R.string.SaveException));
        }
    }//_____________________________________________________________________________________________ End DeleteDataBase


    public RealmResults<DataBaseRepair> GetCarRepair(Integer CarId2) {//____________________________ Start GetCarRepair
        return MachinHeartApplication
                .getMachinHeartApplication(this.context)
                .getRealmComponent()
                .getRealm()
                .where(DataBaseRepair.class)
                .equalTo("CarId", CarId2)
                .sort("RepairDate", Sort.DESCENDING)
                .findAll();
    }//_____________________________________________________________________________________________ End GetCarRepair


    public ArrayList<String> GetSuggestionTitle(String text) {//____________________________________ Start GetSuggestionTitle
        ArrayList<String> suggestion = new ArrayList<>();
        RealmResults<DataBaseRepair> temp = MachinHeartApplication
                .getMachinHeartApplication(this.context)
                .getRealmComponent().getRealm()
                .where(DataBaseRepair.class)
                .contains("Title", text)
                .distinct("Title")
                .sort("Title")
                .findAll();

        for (int i = 0; i < temp.size(); i++) {
            suggestion.add(temp.get(i).getTitle());
        }
        return suggestion;
    }//_____________________________________________________________________________________________ End GetSuggestionTitle


    private String CheckBeforSave(Integer ID2) {//__________________________________________________ Start CheckBeforSave
        try {
            Realm realm = MachinHeartApplication
                    .getMachinHeartApplication(this.context)
                    .getRealmComponent()
                    .getRealm();

            RealmResults<DataBaseRepair> checkDate = realm
                    .where(DataBaseRepair.class)
                    .equalTo("CarId", MainActivity.CarId)
                    .and()
                    .equalTo("CarId", getTitle())
                    .and()
                    .greaterThanOrEqualTo("Title", getRepairDate().intValue())
                    .and()
                    .notEqualTo("ID", ID2)
                    .sort("Title", Sort.ASCENDING)
                    .findAll();

            if (checkDate.size() > 0) {
                String temp = String.valueOf(((DataBaseRepair) checkDate.last()).getRepairDate());
                StringBuilder sb = new StringBuilder();
                sb.append(temp.substring(0, 4));
                sb.append("/");
                sb.append(temp.substring(4, 6));
                sb.append("/");
                sb.append(temp.substring(6, 8));
                String temp2 = sb.toString();
                StringBuilder sb2 = new StringBuilder();
                sb2.append("تاریخ ");
                sb2.append(temp2);
                return sb2.toString();
            }
            RealmResults<DataBaseRepair> checkKm = realm
                    .where(DataBaseRepair.class)
                    .equalTo("CarId", MainActivity.CarId)
                    .and()
                    .equalTo("Title", getTitle())
                    .and()
                    .greaterThanOrEqualTo("RepairKm", getRepairKm().intValue())
                    .and()
                    .notEqualTo("ID", ID2)
                    .sort("RepairKm", Sort.ASCENDING)
                    .findAll();

            if (checkKm.size() <= 0) {
                return "Ok";
            }
            NumberFormat formatter = new DecimalFormat("#,###");
            StringBuilder sb3 = new StringBuilder();
            sb3.append("کیلومتر ");
            sb3.append(formatter.format(checkKm.last().getRepairKm()));
            return sb3.toString();
        } catch (Exception ex) {
            ShowMessage(this.context.getResources().getString(R.string.SaveException));
            return "Error";
        }
    }//_____________________________________________________________________________________________ End CheckBeforSave


    public Integer getID() {
        return this.ID;
    }

    public void setID(Integer ID2) {
        this.ID = ID2;
    }

    public Integer getCarId() {
        return this.CarId;
    }

    public void setCarId(Integer carId) {
        this.CarId = carId;
    }

    public Integer getRepairKm() {
        return this.RepairKm;
    }

    public void setRepairKm(Integer repairKm) {
        this.RepairKm = repairKm;
    }

    public String getRepairWhy() {
        return this.RepairWhy;
    }

    public void setRepairWhy(String repairWhy) {
        this.RepairWhy = repairWhy;
    }

    public Integer getMoney() {
        return this.Money;
    }

    public void setMoney(Integer money) {
        this.Money = money;
    }

    public String getBrand() {
        return this.Brand;
    }

    public void setBrand(String brand) {
        this.Brand = brand;
    }

    public String getTitle() {
        return this.Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public Integer getRepairDate() {
        return this.RepairDate;
    }

    public void setRepairDate(Integer repairDate) {
        this.RepairDate = repairDate;
    }
}
