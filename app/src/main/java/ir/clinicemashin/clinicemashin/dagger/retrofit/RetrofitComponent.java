package ir.clinicemashin.clinicemashin.dagger.retrofit;

import ir.clinicemashin.clinicemashin.dagger.DaggerScope;

import dagger.Component;

@DaggerScope
@Component(modules = RetrofitModule.class)
public interface RetrofitComponent
{
    RetrofitApiInterface getRetrofitApiInterface();

}
