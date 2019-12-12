package ir.clinicemashin.clinicemashin.dagger.applicationutility;

import ir.clinicemashin.clinicemashin.utility.ApplicationUtility;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationUtilityModul {
    @Provides
    public ApplicationUtility getApplicationUtility() {
        return new ApplicationUtility();
    }
}
