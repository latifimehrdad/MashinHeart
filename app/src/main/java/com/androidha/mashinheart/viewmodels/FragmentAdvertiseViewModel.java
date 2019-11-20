package com.androidha.mashinheart.viewmodels;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.androidha.mashinheart.R;
import com.androidha.mashinheart.dagger.retrofit.RetrofitComponent;
import com.androidha.mashinheart.models.ModelAdvertise;
import com.androidha.mashinheart.models.ModelAdvertiseList;
import com.androidha.mashinheart.views.application.MachinHeartApplication;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAdvertiseViewModel {

    private Context context;
    private Boolean isCancel;
    public PublishSubject<String> MessageType = PublishSubject.create();
    private ArrayList<ModelAdvertiseList> modelAdvertiseLists;

    public FragmentAdvertiseViewModel(Context context) {//__________________________________________ Start FragmentAdvertiseViewModel
        this.context = context;
    }//_____________________________________________________________________________________________ End FragmentAdvertiseViewModel


    public void GetAdvertise(double lat, double lng, String Text) {//_____________________________________________ Start GetAdvertise

        RetrofitComponent retrofitComponent =
                MachinHeartApplication
                        .getMachinHeartApplication(context)
                        .getRetrofitComponent();

        retrofitComponent.getRetrofitApiInterface()
                .getAdvertise(String.valueOf(lat), String.valueOf(lng))
                .enqueue(new Callback<ModelAdvertise>() {
                    @Override
                    public void onResponse(Call<ModelAdvertise> call, Response<ModelAdvertise> response) {
                        if(isCancel)
                            return;
                        if(response.body().getStatue() == 1) {
                            setModelAdvertiseLists(response.body().getAdvertiseList());
                            MessageType.onNext(context.getResources().getString(R.string.GetOk));
                            //SetSearchFilter(Text);

                        }
                        else{
                            MessageType.onNext(response.body().getMessage());
                        }
//                        setAdvertiseListModels(response.body().getAdvertiseList());
//                        status.setValue(true);
                    }

                    @Override
                    public void onFailure(Call<ModelAdvertise> call, Throwable t) {
                        if(isCancel)
                            return;
                        MessageType.onNext(context.getResources().getString(R.string.GetError));
                    }
                });


    }//_____________________________________________________________________________________________ End GetAdvertise




    private void SetSearchFilter(String Text){//____________________________________________________ Start SetSearchFilter

        ArrayList<ModelAdvertiseList> temp = new ArrayList<>();
        for (int i = 0; i < modelAdvertiseLists.size() ; i++) {
            ModelAdvertiseList item = modelAdvertiseLists.get(i);
                Boolean ret = false;
                if((item.getTitle() != null) && (!item.getTitle().equalsIgnoreCase("")))
                    ret = item.getTitle().contains(Text);
            if((item.getTxt2() != null) && (!item.getTxt2().equalsIgnoreCase("")))
                if(!ret)
                    ret = item.getTxt2().contains(Text);
                if(ret)
                    temp.add(modelAdvertiseLists.get(i));
        }


//        modelAdvertiseLists.removeIf(new Predicate<ModelAdvertiseList>() {
//            @Override
//            public boolean test(ModelAdvertiseList list) {
//                Boolean ret = list.getTitle().contains(Text);
//                if(!ret)
//                    ret = list.getTxt2().contains(Text);
//                return ret;
//            }
//        });
//        Observable<ArrayList<ModelAdvertiseList>> observable = Observable.just(getModelAdvertiseLists());
//        observable
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .observeOn(Schedulers.io())
//                .filter(new Predicate<ArrayList<ModelAdvertiseList>>() {
//                    @Override
//                    public boolean test(ArrayList<ModelAdvertiseList> modelAdvertiseLists) throws Exception {
//                        modelAdvertiseLists.
//                        return false;
//                    }
//                })
//                .subscribe();

        modelAdvertiseLists = temp;
        MessageType.onNext(context.getResources().getString(R.string.GetOk));
    }//_____________________________________________________________________________________________ End SetSearchFilter




    public Boolean getCancel() {
        return isCancel;
    }

    public void setCancel(Boolean cancel) {
        isCancel = cancel;
    }


    public ArrayList<ModelAdvertiseList> getModelAdvertiseLists() {
        return modelAdvertiseLists;
    }

    public void setModelAdvertiseLists(ArrayList<ModelAdvertiseList> modelAdvertiseLists) {
        this.modelAdvertiseLists = modelAdvertiseLists;
    }
}
