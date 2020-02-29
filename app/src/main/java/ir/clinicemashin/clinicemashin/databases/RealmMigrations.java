package ir.clinicemashin.clinicemashin.databases;

import java.util.Date;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class RealmMigrations implements RealmMigration {
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        if (oldVersion == 1) {
            schema.create("DataBasePositions")
                    .addField("PositionDate", Date.class)
                    .addField("PositonLat", Double.class)
                    .addField("PositionLong", Double.class);
            oldVersion++;
        }
    }

}
