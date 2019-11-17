package com.androidha.mashinheart.dagger.retrofit;

import android.database.Observable;

import com.androidha.mashinheart.models.ModelAdvertise;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitApiInterface {
    @FormUrlEncoded
    @POST("api/AdvertiseList")
    Call<ModelAdvertise> getAdvertise
            (
                    @Field("Lat") String Lat,
                    @Field("Long") String Long
            );

}