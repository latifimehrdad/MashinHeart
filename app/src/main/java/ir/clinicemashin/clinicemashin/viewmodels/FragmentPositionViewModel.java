package ir.clinicemashin.clinicemashin.viewmodels;

import android.content.Context;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import ir.clinicemashin.clinicemashin.databases.DataBasePositions;
import ir.clinicemashin.clinicemashin.models.ModelPosition;
import ir.clinicemashin.clinicemashin.views.application.MachinHeartApplication;
import ir.clinicemashin.clinicemashin.views.fragments.MapFragment;

public class FragmentPositionViewModel {

    private Context context;

    public FragmentPositionViewModel(Context context) {
        this.context = context;
    }


    public RealmResults<DataBasePositions> GetPositions() {//_______________________________________ Start GetCars
        return MachinHeartApplication
                .getMachinHeartApplication(this.context)
                .getRealmComponent()
                .getRealm()
                .where(DataBasePositions.class)
                .sort("PositionDate", Sort.DESCENDING)
                .findAll();
    }//_____________________________________________________________________________________________ End GetCars



    public void SaveLocation(ModelPosition position, MapFragment mapFragment) {//___________________ Start SaveLocation
        try {
            Realm realm = MachinHeartApplication.getMachinHeartApplication(this.context).getRealmComponent().getRealm();
            realm.beginTransaction();
            realm.createObject(DataBasePositions.class).DataBasePositions(position);
            realm.commitTransaction();
            mapFragment.getActivity().onBackPressed();
        } catch (Exception ex) {
            Log.i("meri", ex.toString());
        }
    }//_____________________________________________________________________________________________ End SaveLocation


}
