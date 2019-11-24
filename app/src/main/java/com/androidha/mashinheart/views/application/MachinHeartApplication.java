package com.androidha.mashinheart.views.application;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.androidha.mashinheart.R;
import com.androidha.mashinheart.dagger.applicationutility.ApplicationUtilityComponent;
import com.androidha.mashinheart.dagger.applicationutility.ApplicationUtilityModul;
import com.androidha.mashinheart.dagger.applicationutility.DaggerApplicationUtilityComponent;
import com.androidha.mashinheart.dagger.imageloader.DaggerImageLoaderComponent;
import com.androidha.mashinheart.dagger.imageloader.ImageLoaderComponent;
import com.androidha.mashinheart.dagger.imageloader.ImageLoaderModul;
import com.androidha.mashinheart.dagger.persianpicker.DaggerPersianPickerComponent;
import com.androidha.mashinheart.dagger.persianpicker.PersianPickerComponent;
import com.androidha.mashinheart.dagger.persianpicker.PersianPickerModul;
import com.androidha.mashinheart.dagger.realm.DaggerRealmComponent;
import com.androidha.mashinheart.dagger.realm.RealmComponent;
import com.androidha.mashinheart.dagger.realm.RealmModul;
import com.androidha.mashinheart.dagger.retrofit.DaggerRetrofitComponent;
import com.androidha.mashinheart.dagger.retrofit.RetrofitComponent;
import com.androidha.mashinheart.dagger.retrofit.RetrofitModule;
import com.nostra13.universalimageloader.cache.memory.BaseMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.lang.ref.Reference;

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
    ImageLoaderComponent imageLoaderComponent;

    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
        ConfigurationCalligraphy();
        ConfigurationRealm();
        ConfigurationImageLoader();
        ConfigurationDaggerComponent();
        ConfigurationRetrofitComponent();

    }



    private void ConfigurationImageLoader() {

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .resetViewBeforeLoading(true)
                .displayer(new FadeInBitmapDisplayer(100))
                .build();


        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new BaseMemoryCache() {
                    @Override
                    protected Reference<Bitmap> createReference(Bitmap value) {
                        return null;
                    }
                })
                .threadPoolSize(3)
                .diskCacheSize(100 * 1024 * 1024)
                .build();
        ImageLoader.getInstance().init(config);
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
        imageLoaderComponent = DaggerImageLoaderComponent.builder().imageLoaderModul(new ImageLoaderModul()).build();
    }

    public ImageLoaderComponent getImageLoaderComponent() {
        return imageLoaderComponent;
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
