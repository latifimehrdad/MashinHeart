package com.androidha.mashinheart.views.application;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.androidha.mashinheart.R;
import com.androidha.mashinheart.dagger.applicationutility.ApplicationUtilityComponent;
import com.androidha.mashinheart.dagger.applicationutility.ApplicationUtilityModul;
import com.androidha.mashinheart.dagger.applicationutility.DaggerApplicationUtilityComponent;
import com.androidha.mashinheart.dagger.persianpicker.DaggerPersianPickerComponent;
import com.androidha.mashinheart.dagger.persianpicker.PersianPickerComponent;
import com.androidha.mashinheart.dagger.persianpicker.PersianPickerModul;
import com.androidha.mashinheart.dagger.realm.DaggerRealmComponent;
import com.androidha.mashinheart.dagger.realm.RealmComponent;
import com.androidha.mashinheart.dagger.realm.RealmModul;
import com.androidha.mashinheart.dagger.retrofit.DaggerRetrofitComponent;
import com.androidha.mashinheart.dagger.retrofit.RetrofitComponent;
import com.androidha.mashinheart.dagger.retrofit.RetrofitModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig.Builder;

public class MachinHeartApplication extends Application {

    Context context;
    ApplicationUtilityComponent applicationUtilityComponent;
    PersianPickerComponent persianPickerComponent;
    RealmComponent realmComponent;
    RetrofitComponent retrofitComponent;

    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
        ConfigurationCalligraphy();
        ConfigurationRealm();
        ConfigurationDaggerComponent();
        ConfigurationRetrofitComponent();
    }

    private void ConfigurationRetrofitComponent() {
        retrofitComponent = DaggerRetrofitComponent
                .builder()
                .retrofitModule(new RetrofitModule(context))
                .build();
    }


    private void ConfigurationDaggerComponent() {
        applicationUtilityComponent = DaggerApplicationUtilityComponent.builder().applicationUtilityModul(new ApplicationUtilityModul()).build();
        realmComponent = DaggerRealmComponent.builder().realmModul(new RealmModul()).build();
        persianPickerComponent = DaggerPersianPickerComponent.builder().persianPickerModul(new PersianPickerModul()).build();
    }

    public RetrofitComponent getRetrofitComponent() {
        return retrofitComponent;
    }

    public RealmComponent getRealmComponent() {
        return realmComponent;
    }

    public ApplicationUtilityComponent getApplicationUtilityComponent() {
        return applicationUtilityComponent;
    }

    public PersianPickerComponent getPersianPickerComponent() {
        return persianPickerComponent;
    }

    private void ConfigurationCalligraphy() {
        CalligraphyConfig.initDefault(new Builder().setDefaultFontPath("font/iransanslight.ttf").setFontAttrId(R.attr.fontPath).build());
    }

    private void ConfigurationRealm() {
        Realm.init(this);
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder().name("MachinHeartRealm").schemaVersion(1).build());
    }

    public static MachinHeartApplication getMachinHeartApplication(Context context) {
        return (MachinHeartApplication) context.getApplicationContext();
    }

    public void onTerminate() {
        Realm.getDefaultInstance().close();
        super.onTerminate();
    }
}
