package com.androidha.mashinheart.dagger.applicationutility;

import com.androidha.mashinheart.utility.ApplicationUtility;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationUtilityModul {
    @Provides
    public ApplicationUtility getApplicationUtility() {
        return new ApplicationUtility();
    }
}
