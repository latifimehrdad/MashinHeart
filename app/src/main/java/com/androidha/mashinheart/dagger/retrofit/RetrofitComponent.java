package com.androidha.mashinheart.dagger.retrofit;

import com.androidha.mashinheart.dagger.DaggerScope;

import dagger.Component;

@DaggerScope
@Component(modules = RetrofitModule.class)
public interface RetrofitComponent
{
    RetrofitApiInterface getRetrofitApiInterface();

}
