package com.androidha.mashinheart.databases;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema.Function;
import io.realm.RealmSchema;

//public class RealmMigrations implements RealmMigration {
//    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
//        RealmSchema schema = realm.getSchema();
//        if (oldVersion == 1) {
//            schema.get(ClassNameHelper.INTERNAL_CLASS_NAME).addField("test", Boolean.class, new FieldAttribute[0]).transform(new Function() {
//                public void apply(DynamicRealmObject obj) {
//                    obj.set("test", Boolean.valueOf(true));
//                }
//            });
//        }
//    }
//}
