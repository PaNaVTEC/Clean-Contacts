package me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.persistors;

import me.panavtec.cleancontacts.data.DatabaseHelper;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.BddContact;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.BddLocation;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.BddName;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.BddPicture;

import java.sql.SQLException;

public class ContactPersistor implements Persistor<BddContact> {

    private DatabaseHelper helper;

    public ContactPersistor(DatabaseHelper helper) {
        this.helper = helper;
    }
    
    @Override public void persist(BddContact data) throws SQLException {
        if (data != null) {
            BddLocation location = data.getLocation();
            if (location != null) {
                helper.getLocationDao().create(location);
            }
            BddName name = data.getName();
            if (name != null) {
                helper.getNameDao().create(name);
            }
            BddPicture picture = data.getPicture();
            if (picture != null) {
                helper.getPictureDao().create(picture);
            }
            helper.getContactDao().create(data);
        }
    }
}
