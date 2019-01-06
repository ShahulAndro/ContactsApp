package sha.com.contactsapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Shahul Hameed Shaik
 * Email: android.shahul@gmail.com
 */
@Dao
public interface ContactDao {
    @Query("SELECT * FROM contact")
    List<Contact> getAll();

    @Query("SELECT * FROM contact WHERE uid IN (:userIds)")
    List<Contact> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM contact WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    Contact findByName(String first, String last);

    @Insert
    long insert(Contact contact);

    @Update
    int update(Contact contact);

    @Delete
    int delete(Contact contact);
}
