package sha.com.contactsapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


/**
 * Created by Shahul Hameed Shaik
 * Email: android.shahul@gmail.com
 */
@Database(entities = {Contact.class}, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactDao contactDao();
}
