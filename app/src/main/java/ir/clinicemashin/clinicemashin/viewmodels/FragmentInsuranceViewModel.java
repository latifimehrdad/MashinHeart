package ir.clinicemashin.clinicemashin.viewmodels;

import android.content.Context;

import ir.clinicemashin.clinicemashin.R;
import ir.clinicemashin.clinicemashin.databases.DataBaseInsurance;
import ir.clinicemashin.clinicemashin.models.ModelInsurance;
import ir.clinicemashin.clinicemashin.views.activitys.MainActivity;
import ir.clinicemashin.clinicemashin.views.application.MachinHeartApplication;

import io.reactivex.subjects.PublishSubject;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class FragmentInsuranceViewModel {
    private Integer CarId;
    private Integer ID;
    private Integer InsuranceDate;
    private int InsuranceType;
    private PublishSubject<String> MessageType = PublishSubject.create();
    private Integer Money;
    private String Title;
    private Context context;


    public FragmentInsuranceViewModel(Context context2) {//_________________________________________ Start FragmentInsuranceViewModel
        this.context = context2;
    }//_____________________________________________________________________________________________ End FragmentInsuranceViewModel


    private void ShowMessage(String Type) {//_______________________________________________________ Start ShowMessage
        this.MessageType.onNext(Type);
    }//_____________________________________________________________________________________________ End ShowMessage


    private Boolean CancelNotification(Integer InsuranceType, Integer Old, Integer ID) {//__________ Start CancelNotification

        Realm realm = MachinHeartApplication
                .getMachinHeartApplication(this.context)
                .getRealmComponent()
                .getRealm();

        if (!(Old == null || Old == InsuranceType)) {
            try {
                DataBaseInsurance dataBaseInsurance = realm
                        .where(DataBaseInsurance.class)
                        .equalTo("CarId", MainActivity.CarId)
                        .and()
                        .equalTo("InsuranceType", Old)
                        .and()
                        .notEqualTo("ID", ID)
                        .sort("InsuranceDate", Sort.DESCENDING)
                        .findFirst();

                if (dataBaseInsurance != null) {
                    realm.beginTransaction();
                    dataBaseInsurance.setNotification(0);
                    realm.commitTransaction();
                }
            } catch (Exception ex) {
                realm.cancelTransaction();
                return false;
            }
        }
        if (InsuranceType != null) {
            try {
                DataBaseInsurance dataBaseInsurance = realm
                        .where(DataBaseInsurance.class)
                        .equalTo("CarId", MainActivity.CarId)
                        .and()
                        .equalTo("InsuranceType", InsuranceType)
                        .sort("InsuranceDate", Sort.DESCENDING)
                        .findFirst();

                if (dataBaseInsurance != null) {
                    realm.beginTransaction();
                    dataBaseInsurance.setNotification(2);
                    realm.commitTransaction();
                }
            } catch (Exception ex) {
                realm.cancelTransaction();
                return false;
            }
        }
        return true;
    }//_____________________________________________________________________________________________ End CancelNotification


    public void SaveToDataBase() {//________________________________________________________________ Start SaveToDataBase

        String message = CheckBeforSave(0);
        switch (message) {
            case "Ok":
                if (CancelNotification(getInsuranceType(), null, 0)) {
                    FinalSave();
                } else {
                    ShowMessage(this.context.getResources().getString(R.string.SaveException));
                }
                break;
            case "Error":
                ShowMessage(this.context.getResources().getString(R.string.SaveException));
                break;
            default:
                String type = this.context.getResources().getStringArray(R.array.InsuranceType)[getInsuranceType()];
                StringBuilder sb = new StringBuilder();
                sb.append(type);
                sb.append(" را در ");
                sb.append(message);
                sb.append(" ");
                sb.append(this.context.getResources().getString(R.string.InsuranceGet));
                ShowMessage(sb.toString());
                break;
        }

    }//_____________________________________________________________________________________________ End SaveToDataBase


    private void FinalSave() {//____________________________________________________________________ Start FinalSave

        Realm realm = MachinHeartApplication
                .getMachinHeartApplication(this.context)
                .getRealmComponent()
                .getRealm();
        try {
            Number currentIdNum = realm.where(DataBaseInsurance.class).max("ID");
            if (currentIdNum == null) {
                this.ID = 1;
            } else {
                this.ID = currentIdNum.intValue() + 1;
            }
            ModelInsurance insert = new ModelInsurance(this);
            realm.beginTransaction();
            realm.createObject(DataBaseInsurance.class, this.ID).DataBaseInsurance(insert);
            realm.commitTransaction();
            ShowMessage(this.context.getResources().getString(R.string.SaveCommit));
        } catch (Exception ex) {
            realm.cancelTransaction();
            ShowMessage(this.context.getResources().getString(R.string.SaveException));
        }
    }//_____________________________________________________________________________________________ End FinalSave


    public void EditDataBase(DataBaseInsurance dataBaseInsurance) {//_______________________________ Start EditDataBase

        String message = CheckBeforSave(dataBaseInsurance.getID());
        switch (message) {
            case "Ok":
                if (CancelNotification(getInsuranceType(), dataBaseInsurance.getInsuranceType(), dataBaseInsurance.getID())) {
                    FinalEdit(dataBaseInsurance);
                } else {
                    ShowMessage(this.context.getResources().getString(R.string.SaveException));
                }
                break;
            case "Error":
                ShowMessage(this.context.getResources().getString(R.string.SaveException));
                break;
            default:
                String type = this.context.getResources().getStringArray(R.array.InsuranceType)[getInsuranceType()];
                StringBuilder sb = new StringBuilder();
                sb.append(type);
                sb.append(" را در ");
                sb.append(message);
                sb.append(" ");
                sb.append(this.context.getResources().getString(R.string.InsuranceGet));
                ShowMessage(sb.toString());
                break;
        }
    }//_____________________________________________________________________________________________ End EditDataBase


    private void FinalEdit(DataBaseInsurance dataBaseInsurance) {//________________________________ Start FinalEdit

        Realm realm = MachinHeartApplication
                .getMachinHeartApplication(this.context)
                .getRealmComponent()
                .getRealm();
        try {
            realm.beginTransaction();
            dataBaseInsurance.setTitle(getTitle());
            dataBaseInsurance.setInsuranceType(getInsuranceType());
            dataBaseInsurance.setMoney(getMoney());
            dataBaseInsurance.setInsuranceDate(getInsuranceDate());
            dataBaseInsurance.setNotification(0);
            realm.commitTransaction();
            ShowMessage(this.context.getResources().getString(R.string.EditCommit));
        } catch (Exception ex) {
            realm.cancelTransaction();
            ShowMessage(this.context.getResources().getString(R.string.SaveException));
        }
    }//_____________________________________________________________________________________________ End FinalEdit


    public void DeleteDataBase(DataBaseInsurance dataBaseInsurance) {//________________________________ Start FinalEdit

        Realm realm = MachinHeartApplication
                .getMachinHeartApplication(this.context)
                .getRealmComponent()
                .getRealm();
        try {
            Integer Id = dataBaseInsurance.getID();
            Integer InsuranceType2 = dataBaseInsurance.getInsuranceType();
            realm.beginTransaction();
            dataBaseInsurance.deleteFromRealm();
            realm.commitTransaction();
            if (CancelNotification(null, InsuranceType2, Id)) {
                ShowMessage(this.context.getResources().getString(R.string.DeleteCommit));
            } else {
                ShowMessage(this.context.getResources().getString(R.string.SaveException));
            }
        } catch (Exception e) {
            ShowMessage(this.context.getResources().getString(R.string.SaveException));
            realm.cancelTransaction();
        }
    }//_____________________________________________________________________________________________ End FinalEdit


    public RealmResults<DataBaseInsurance> GetCarInsurance(Integer CarId) {//______________________ Start GetCarInsurance

        return MachinHeartApplication
                .getMachinHeartApplication(this.context)
                .getRealmComponent()
                .getRealm()
                .where(DataBaseInsurance.class)
                .equalTo("CarId", CarId)
                .sort("InsuranceDate", Sort.DESCENDING)
                .findAll();
    }//_____________________________________________________________________________________________ End GetCarInsurance


    private String CheckBeforSave(Integer ID) {//__________________________________________________ Start CheckBeforSave

        Realm realm = MachinHeartApplication
                .getMachinHeartApplication(this.context)
                .getRealmComponent()
                .getRealm();
        try {
            RealmResults<DataBaseInsurance> checkDate = realm
                    .where(DataBaseInsurance.class)
                    .equalTo("CarId", MainActivity.CarId)
                    .and()
                    .equalTo("InsuranceType", Integer.valueOf(getInsuranceType()))
                    .and()
                    .greaterThanOrEqualTo("InsuranceDate", getInsuranceDate().intValue())
                    .and()
                    .notEqualTo("ID", ID)
                    .sort("InsuranceDate", Sort.ASCENDING)
                    .findAll();

            if (checkDate.size() <= 0) {
                return "Ok";
            }

            String temp = String.valueOf(checkDate.last().getInsuranceDate());
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
        } catch (Exception ex) {
            realm.cancelTransaction();
            ShowMessage(this.context.getResources().getString(R.string.SaveException));
            return "Error";
        }
    }//_____________________________________________________________________________________________ End CheckBeforSave


    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context2) {
        this.context = context2;
    }

    public int getInsuranceType() {
        return this.InsuranceType;
    }

    public void setInsuranceType(int insuranceType) {
        this.InsuranceType = insuranceType;
    }

    public String getTitle() {
        return this.Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public Integer getMoney() {
        return this.Money;
    }

    public void setMoney(Integer money) {
        this.Money = money;
    }

    public Integer getInsuranceDate() {
        return this.InsuranceDate;
    }

    public void setInsuranceDate(Integer insuranceDate) {
        this.InsuranceDate = insuranceDate;
    }

    public Integer getCarId() {
        return this.CarId;
    }

    public void setCarId(Integer carId) {
        this.CarId = carId;
    }

    public PublishSubject<String> getMessageType() {
        return MessageType;
    }
}
