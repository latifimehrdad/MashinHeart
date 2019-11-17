package com.androidha.mashinheart.viewmodels;

import android.content.Context;
import android.database.Observable;
import android.util.Log;

import com.androidha.mashinheart.R;
import com.androidha.mashinheart.dagger.retrofit.RetrofitComponent;
import com.androidha.mashinheart.models.ModelAdvertise;
import com.androidha.mashinheart.models.ModelAdvertiseList;
import com.androidha.mashinheart.views.application.MachinHeartApplication;

import java.util.ArrayList;

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
