package sha.com.contactsapp;

import android.app.Application;
import android.arch.persistence.room.Room;

/**
 * Created by Shahul Hameed Shaik
 * Email: android.shahul@gmail.com
 */
public class MyApplication extends Application {

    private String DB_NAME = "db_contact";
    private AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, DB_NAME).build();
    }

    public ContactDao contactDao() {
        return appDatabase.contactDao();
    }
}
