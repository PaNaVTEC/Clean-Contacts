package me.panavtec.cleancontacts.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.BddContact;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.BddLocation;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.BddName;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.bdd.entities.BddPicture;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(String databaseName, final Context context) {
        super(context, databaseName, null, DATABASE_VERSION);
    }

    @Override public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, BddContact.class);
            TableUtils.createTable(connectionSource, BddLocation.class);
            TableUtils.createTable(connectionSource, BddName.class);
            TableUtils.createTable(connectionSource, BddPicture.class);
        } catch (SQLException e) {
            Log.e(TAG, "Unable to create tables", e);
        }
    }

    @Override public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, BddContact.class, true);
            TableUtils.dropTable(connectionSource, BddLocation.class, true);
            TableUtils.dropTable(connectionSource, BddName.class, true);
            TableUtils.dropTable(connectionSource, BddPicture.class, true);
        } catch (SQLException e) {
            Log.e(TAG, "Unable to drop tables", e);
        }
    }

    public Dao<BddContact, Integer> getContactDao() {
        try {
            return getDao(BddContact.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Dao<BddLocation, Integer> getLocationDao() {
        try {
            return getDao(BddLocation.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Dao<BddName, Integer> getNameDao() {
        try {
            return getDao(BddName.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Dao<BddPicture, Integer> getPictureDao() {
        try {
            return getDao(BddPicture.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
