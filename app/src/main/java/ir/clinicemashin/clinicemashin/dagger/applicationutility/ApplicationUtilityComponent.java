package ir.clinicemashin.clinicemashin.dagger.applicationutility;

import ir.clinicemashin.clinicemashin.utility.ApplicationUtility;

import dagger.Component;

@Component(modules = {ApplicationUtilityModul.class})
public interface ApplicationUtilityComponent {
    ApplicationUtility getApplicationUtility();
}
