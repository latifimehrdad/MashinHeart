package ir.clinicemashin.clinicemashin.dagger.realm;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class RealmModul {
    @Provides
    public Realm getRealm() {
        return Realm.getDefaultInstance();
    }
}
