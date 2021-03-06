package ir.clinicemashin.clinicemashin.dagger.retrofit;

import android.content.Context;

import ir.clinicemashin.clinicemashin.dagger.DaggerScope;
import ir.clinicemashin.clinicemashin.utility.Parameter;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {
    private Context context;

    public RetrofitModule(Context context) {
        this.context = context;
    }

    @Provides
    @DaggerScope
    public RetrofitApiInterface getRetrofitApiInterface(retrofit2.Retrofit retrofit) {
        return retrofit.create(RetrofitApiInterface.class);
    }

    @Provides
    @DaggerScope
    public retrofit2.Retrofit getRetrofit(OkHttpClient okHttpClient) {
        return new retrofit2.Retrofit.Builder()
                .baseUrl(Parameter.URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @DaggerScope
    public OkHttpClient getOkHttpClient(Cache cache) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @DaggerScope
    public Cache getCache(File file) {
        return new Cache(file, 5 * 1000 * 1000);
    }

    @Provides
    @DaggerScope
    public File getFile() {
        return new File(context.getCacheDir(), "Okhttp_cache");
    }


}