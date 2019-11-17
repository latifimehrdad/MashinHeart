package com.androidha.mashinheart.viewmodels;

import android.content.Context;
import android.util.Log;

import com.androidha.mashinheart.R;
import com.androidha.mashinheart.databases.DataBaseCars;
import com.androidha.mashinheart.databases.DataBaseConsumable;
import com.androidha.mashinheart.models.ModelChangeConsumable;
import com.androidha.mashinheart.views.activitys.MainActivity;
import com.androidha.mashinheart.views.application.MachinHeartApplication;

import io.reactivex.subjects.PublishSubject;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class FragmentConsumablesViewModel {
    private String Brand;
    private Integer CarId;
    private Integer ChangeDate;
    private Integer ChangeKm;
    private Integer ChangeNextKm;
    private Integer ID;
    public PublishSubject<String> MessageType = PublishSubject.create();
    private Integer Money;
    private Integer OldChangeDate;
    private Integer OldChangeKm;
    private String Title;
    public Context context;


    public FragmentConsumablesViewModel(Context context2) {//_______________________________________ Start FragmentConsumablesViewModel
        this.context = context2;
    }//_____________________________________________________________________________________________ End FragmentConsumablesViewModel


    private void ShowMessage(String Type) {//_______________________________________________________ Start ShowMessage
        this.MessageType.onNext(Type);
    }//_____________________________________________________________________________________________ End ShowMessage


    public void SaveToDataBase() {//________________________________________________________________ Start SaveToDataBase
        String message = CheckBeforSave(0);
        switch (message) {
            case "Ok":
                if (CancelNotification(getTitle(), null, 0)) {
                    FinalSave();
                } else {
                    ShowMessage(this.context.getResources().getString(R.string.SaveException));
                }
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
                sb.append(this.context.getResources().getString(R.string.ConsumableChange));
                ShowMessage(sb.toString());
                break;
        }

    }//_____________________________________________________________________________________________ End SaveToDataBase


    private void FinalSave() {//____________________________________________________________________ Start FinalSave
        int AverageKm;
        Realm realm = MachinHeartApplication.getMachinHeartApplication(this.context).getRealmComponent().getRealm();
        try {
            Number currentIdNum = realm.where(DataBaseConsumable.class).max("ID");
            if (currentIdNum == null) {
                ID = 1;
            } else {
                ID = currentIdNum.intValue() + 1;
            }
            ModelChangeConsumable insert = new ModelChangeConsumable(this);
            realm.beginTransaction();
            realm.createObject(DataBaseConsumable.class, this.ID).DataBaseConsumable(insert);
            realm.commitTransaction();

            RealmResults<DataBaseCars> cars = realm.where(DataBaseCars.class).equalTo("ID", MainActivity.CarId).findAll();
            int dayBetween = MachinHeartApplication
                    .getMachinHeartApplication(context)
                    .getApplicationUtilityComponent()
                    .getApplicationUtility()
                    .JalaliDatBetween(null, null, getOldChangeDate(), insert.getChangeDate());

            if (getOldChangeKm() == 0)
                setOldChangeKm(cars.first().getCarKm());

            Integer KmBetween = getOldChangeKm() - insert.getChangeKm();
            if (KmBetween < 0) {
                KmBetween = KmBetween * -1;
            }
            if (dayBetween == 0) {
                AverageKm = cars.first().getCarUseAverage();
            } else if (KmBetween == 0) {
                AverageKm = cars.first().getCarUseAverage();
            } else {
                AverageKm = KmBetween / dayBetween;
                if (AverageKm == 0) {
                    AverageKm = 1;
                }
            }

            realm.beginTransaction();
            if (cars.first().getCarKm() < insert.getChangeKm()) {
                cars.first().setCarKm(insert.getChangeKm());
            }
            if (cars.first().getLastChangeDate() < insert.getChangeDate()) {
                cars.first().setLastChangeDate(insert.getChangeDate());
            }

            cars.first().setCarUseAverage(AverageKm);
            realm.commitTransaction();
            ShowMessage(this.context.getResources().getString(R.string.SaveCommit));
        } catch (Exception ex) {
            realm.cancelTransaction();
            ShowMessage(this.context.getResources().getString(R.string.SaveException));
            Log.i("meri","FinalSave : " + ex.toString());
        }
    }//_____________________________________________________________________________________________ End FinalSave


    public void EditDataBase(DataBaseConsumable dataBaseConsumable) {//_____________________________ Start EditDataBase
        String message = CheckBeforSave(dataBaseConsumable.getID());

        switch (message) {
            case "Ok":
                if (CancelNotification(getTitle(), dataBaseConsumable.getTitle(), dataBaseConsumable.getID())) {
                    FinalEdit(dataBaseConsumable);
                } else {
                    ShowMessage(this.context.getResources().getString(R.string.SaveException));
                }
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
                sb.append(this.context.getResources().getString(R.string.ConsumableChange));
                ShowMessage(sb.toString());
                break;
        }
    }//_____________________________________________________________________________________________ End EditDataBase


    private void FinalEdit(DataBaseConsumable dataBaseConsumable) {//_______________________________ Start FinalEdit
        int AverageKm;
        Realm realm = MachinHeartApplication
                .getMachinHeartApplication(this.context)
                .getRealmComponent()
                .getRealm();

        try {
            realm.beginTransaction();
            dataBaseConsumable.setTitle(getTitle());
            dataBaseConsumable.setChangeKm(getChangeKm());
            dataBaseConsumable.setChangeNextKm(getChangeNextKm());
            dataBaseConsumable.setBrand(getBrand());
            dataBaseConsumable.setMoney(getMoney());
            dataBaseConsumable.setChangeDate(getChangeDate());
            dataBaseConsumable.setNotification(0);
            realm.commitTransaction();
            RealmResults<DataBaseCars> cars = realm.where(DataBaseCars.class).equalTo("ID", MainActivity.CarId).findAll();

            int dayBetween = MachinHeartApplication
                    .getMachinHeartApplication(context)
                    .getApplicationUtilityComponent()
                    .getApplicationUtility()
                    .JalaliDatBetween(null, null, getOldChangeDate(), dataBaseConsumable.getChangeDate());

            if (getOldChangeKm() == 0)
                setOldChangeKm(cars.first().getCarKm());

            Integer KmBetween = getOldChangeKm() - dataBaseConsumable.getChangeKm();
            if (KmBetween < 0) {
                KmBetween = KmBetween * -1;
            }

            if (dayBetween == 0) {
                AverageKm = cars.first().getCarUseAverage();
            } else if (KmBetween == 0) {
                AverageKm = cars.first().getCarUseAverage();
            } else {
                AverageKm = KmBetween / dayBetween;
                if (AverageKm == 0) {
                    AverageKm = 1;
                }
            }

            realm.beginTransaction();
            if (cars.first().getCarKm() < dataBaseConsumable.getChangeKm()) {
                cars.first().setCarKm(dataBaseConsumable.getChangeKm());
            }
            if (cars.first().getLastChangeDate() < dataBaseConsumable.getChangeDate()) {
                cars.first().setLastChangeDate(dataBaseConsumable.getChangeDate());
            }

            cars.first().setCarUseAverage(AverageKm);
            realm.commitTransaction();
            ShowMessage(this.context.getResources().getString(R.string.EditCommit));
        } catch (Exception ex) {
            realm.cancelTransaction();
            ShowMessage(this.context.getResources().getString(R.string.SaveException));
            Log.i("meri","FinalEdit : " + ex.toString());
        }
    }//_____________________________________________________________________________________________ End FinalEdit


    private Boolean CancelNotification(String Title, String Old, Integer ID) {//____________________ Start CancelNotification
        Realm realm = MachinHeartApplication.getMachinHeartApplication(this.context).getRealmComponent().getRealm();
        if (Old != null) {
            try {
                if (!Old.equalsIgnoreCase(Title)) {
                    DataBaseConsumable dataBaseConsumable = realm
                            .where(DataBaseConsumable.class)
                            .equalTo("CarId", MainActivity.CarId)
                            .and()
                            .equalTo("Title", Old)
                            .and()
                            .notEqualTo("ID", ID)
                            .sort("ChangeKm", Sort.DESCENDING).findFirst();
                    if (dataBaseConsumable != null) {
                        realm.beginTransaction();
                        dataBaseConsumable.setNotification(0);
                        realm.commitTransaction();
                    }
                }
            } catch (Exception ex) {
                realm.cancelTransaction();
                Log.i("meri","CancelNotification1 : " + ex.toString());
                return false;
            }
        }
        if (Title != null) {
            try {
                DataBaseConsumable dataBaseConsumable2 = realm
                        .where(DataBaseConsumable.class)
                        .equalTo("CarId", MainActivity.CarId)
                        .and()
                        .equalTo("Title", Title)
                        .sort("ChangeKm", Sort.DESCENDING).findFirst();

                setOldChangeDate(Integer.valueOf(0));
                setOldChangeKm(Integer.valueOf(0));
                if (dataBaseConsumable2 != null) {
                    realm.beginTransaction();
                    dataBaseConsumable2.setNotification(2);
                    setOldChangeDate(dataBaseConsumable2.getChangeDate());
                    setOldChangeKm(dataBaseConsumable2.getChangeKm());
                    realm.commitTransaction();
                }
            } catch (Exception ex) {
                realm.cancelTransaction();
                Log.i("meri","CancelNotification2 : " + ex.toString());
                return false;
            }
        }
        return true;
    }//_____________________________________________________________________________________________ End CancelNotification


    public void DeleteDataBase(DataBaseConsumable dataBaseConsumable) {//___________________________ Start DeleteDataBase
        Realm realm = MachinHeartApplication.getMachinHeartApplication(this.context).getRealmComponent().getRealm();
        try {
            Integer Id = dataBaseConsumable.getID();
            String Title2 = dataBaseConsumable.getTitle();
            realm.beginTransaction();
            dataBaseConsumable.deleteFromRealm();
            realm.commitTransaction();
            if (CancelNotification(null, Title2, Id)) {
                ShowMessage(this.context.getResources().getString(R.string.DeleteCommit));
            } else {
                ShowMessage(this.context.getResources().getString(R.string.SaveException));
            }
        } catch (Exception ex) {
            realm.cancelTransaction();
            ShowMessage(this.context.getResources().getString(R.string.SaveException));
            Log.i("meri","DeleteDataBase : "+ex.toString());
        }
    }//_____________________________________________________________________________________________ End DeleteDataBase


    public RealmResults<DataBaseConsumable> GetCarConsumable(Integer CarId2) {//____________________ Start GetCarConsumable
        return MachinHeartApplication
                .getMachinHeartApplication(this.context)
                .getRealmComponent()
                .getRealm()
                .where(DataBaseConsumable.class)
                .equalTo("CarId", CarId2)
                .sort("ChangeDate", Sort.DESCENDING)
                .findAll();
    }//_____________________________________________________________________________________________ End GetCarConsumable


    public ArrayList<String> GetSuggestionTitle(String text) {//____________________________________ Start GetSuggestionTitle
        ArrayList<String> suggestion = new ArrayList<>();
        String str = "Title";
        RealmResults<DataBaseConsumable> temp = MachinHeartApplication.getMachinHeartApplication(this.context).getRealmComponent().getRealm().where(DataBaseConsumable.class).contains(str, text).distinct(str).sort(str).findAll();
        for (int i = 0; i < temp.size(); i++) {
            suggestion.add(temp.get(i).getTitle());
        }
        return suggestion;
    }//_____________________________________________________________________________________________ End GetSuggestionTitle


    private String CheckBeforSave(Integer ID) {//___________________________________________________ Start DeleteDataBase
        Realm realm = MachinHeartApplication
                .getMachinHeartApplication(this.context)
                .getRealmComponent()
                .getRealm();

        try {
            RealmResults<DataBaseConsumable> checkDate = realm
                    .where(DataBaseConsumable.class)
                    .equalTo("CarId", MainActivity.CarId)
                    .and()
                    .equalTo("Title", getTitle())
                    .and()
                    .greaterThanOrEqualTo("ChangeDate", getChangeDate())
                    .and().notEqualTo("ID", ID)
                    .sort("ChangeDate", Sort.ASCENDING)
                    .findAll();

            if (checkDate.size() > 0) {
                String temp = String.valueOf(checkDate.last().getChangeDate());
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
            RealmResults<DataBaseConsumable> checkKm = realm
                    .where(DataBaseConsumable.class)
                    .equalTo("CarId", MainActivity.CarId)
                    .and()
                    .equalTo("Title", getTitle())
                    .and()
                    .greaterThanOrEqualTo("ChangeKm", getChangeKm())
                    .and()
                    .notEqualTo("ID", ID)
                    .sort("ChangeKm", Sort.ASCENDING)
                    .findAll();

            if (checkKm.size() <= 0) {
                return "Ok";
            }
            NumberFormat formatter = new DecimalFormat("#,###");
            StringBuilder sb3 = new StringBuilder();
            sb3.append("کیلومتر ");
            sb3.append(formatter.format(checkKm.last().getChangeKm()));
            return sb3.toString();
        } catch (Exception ex) {
            realm.cancelTransaction();
            ShowMessage(this.context.getResources().getString(R.string.SaveException));
            Log.i("meri","CheckBeforSave : "+ex.toString());
            return "Error";
        }
    }//_____________________________________________________________________________________________ End CheckBeforSave


    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context2) {
        this.context = context2;
    }

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

    public Integer getChangeKm() {
        return this.ChangeKm;
    }

    public void setChangeKm(Integer changeKm) {
        this.ChangeKm = changeKm;
    }

    public Integer getChangeNextKm() {
        return this.ChangeNextKm;
    }

    public void setChangeNextKm(Integer changeNextKm) {
        this.ChangeNextKm = changeNextKm;
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

    public Integer getChangeDate() {
        return this.ChangeDate;
    }

    public void setChangeDate(Integer changeDate) {
        this.ChangeDate = changeDate;
    }

    public Integer getOldChangeKm() {
        return this.OldChangeKm;
    }

    public void setOldChangeKm(Integer oldChangeKm) {
        this.OldChangeKm = oldChangeKm;
    }

    public Integer getOldChangeDate() {
        return this.OldChangeDate;
    }

    public void setOldChangeDate(Integer oldChangeDate) {
        this.OldChangeDate = oldChangeDate;
    }
}
