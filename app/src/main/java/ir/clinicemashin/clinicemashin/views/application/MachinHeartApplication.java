package ir.clinicemashin.clinicemashin.views.application;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import androidx.multidex.MultiDexApplication;

import ir.clinicemashin.clinicemashin.R;
import ir.clinicemashin.clinicemashin.dagger.applicationutility.ApplicationUtilityComponent;
import ir.clinicemashin.clinicemashin.dagger.applicationutility.ApplicationUtilityModul;
import ir.clinicemashin.clinicemashin.dagger.applicationutility.DaggerApplicationUtilityComponent;
import ir.clinicemashin.clinicemashin.dagger.imageloader.DaggerImageLoaderComponent;
import ir.clinicemashin.clinicemashin.dagger.imageloader.ImageLoaderComponent;
import ir.clinicemashin.clinicemashin.dagger.imageloader.ImageLoaderModul;
import ir.clinicemashin.clinicemashin.dagger.persianpicker.DaggerPersianPickerComponent;
import ir.clinicemashin.clinicemashin.dagger.persianpicker.PersianPickerComponent;
import ir.clinicemashin.clinicemashin.dagger.persianpicker.PersianPickerModul;
import ir.clinicemashin.clinicemashin.dagger.realm.DaggerRealmComponent;
import ir.clinicemashin.clinicemashin.dagger.realm.RealmComponent;
import ir.clinicemashin.clinicemashin.dagger.realm.RealmModul;
import ir.clinicemashin.clinicemashin.dagger.retrofit.DaggerRetrofitComponent;
import ir.clinicemashin.clinicemashin.dagger.retrofit.RetrofitComponent;
import ir.clinicemashin.clinicemashin.dagger.retrofit.RetrofitModule;
import com.nostra13.universalimageloader.cache.memory.BaseMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.lang.ref.Reference;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import ir.clinicemashin.clinicemashin.databases.RealmMigrations;

public class MachinHeartApplication extends MultiDexApplication {

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
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("font/iransanslight.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());

//        CalligraphyConfig
//                .initDefault(new Builder()
//                        .setDefaultFontPath("font/iransanslight.ttf").setFontAttrId(R.attr.fontPath).build());
    }

    private void ConfigurationRealm() {
        Realm.init(this);
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder()
                .name("MachinHeartRealm")
                .schemaVersion(2)
                .migration(new RealmMigrations())
                .build());
    }

    public static MachinHeartApplication getMachinHeartApplication(Context context) {
        return (MachinHeartApplication) context.getApplicationContext();
    }

    public void onTerminate() {
        Realm.getDefaultInstance().close();
        super.onTerminate();
    }
}
