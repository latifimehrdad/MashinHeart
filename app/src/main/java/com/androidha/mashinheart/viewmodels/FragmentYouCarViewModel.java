package com.androidha.mashinheart.viewmodels;

import android.content.Context;

import com.androidha.mashinheart.databases.DataBaseCars;
import com.androidha.mashinheart.views.application.MachinHeartApplication;
import io.realm.RealmResults;

public class FragmentYouCarViewModel {
    private Context context;

    public FragmentYouCarViewModel(Context context2) {//____________________________________________ Start FragmentYouCarViewModel
        this.context = context2;
    }//_____________________________________________________________________________________________ End FragmentYouCarViewModel



    public RealmResults<DataBaseCars> GetCars() {//_________________________________________________ Start GetCars
        return MachinHeartApplication
                .getMachinHeartApplication(this.context)
                .getRealmComponent()
                .getRealm()
                .where(DataBaseCars.class)
                .findAll();
    }//_____________________________________________________________________________________________ End GetCars
}
