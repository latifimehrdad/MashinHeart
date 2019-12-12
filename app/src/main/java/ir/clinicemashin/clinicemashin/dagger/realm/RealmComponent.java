package ir.clinicemashin.clinicemashin.dagger.realm;

import dagger.Component;
import io.realm.Realm;

@Component(modules = {RealmModul.class})
public interface RealmComponent {
    Realm getRealm();
}
