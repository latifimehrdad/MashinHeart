package com.androidha.mashinheart.dagger.applicationutility;

import com.androidha.mashinheart.utility.ApplicationUtility;

import dagger.Component;

@Component(modules = {ApplicationUtilityModul.class})
public interface ApplicationUtilityComponent {
    ApplicationUtility getApplicationUtility();
}
