package ir.clinicemashin.clinicemashin.viewmodels;

import android.content.Context;

import ir.clinicemashin.clinicemashin.databases.DataBaseCars;
import ir.clinicemashin.clinicemashin.views.application.MachinHeartApplication;
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
