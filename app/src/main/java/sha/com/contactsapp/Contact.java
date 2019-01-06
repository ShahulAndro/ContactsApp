package sha.com.contactsapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.text.TextUtils;

import java.io.Serializable;

/**
 * Created by Shahul Hameed Shaik
 * Email: android.shahul@gmail.com
 */

@Entity
public class Contact implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    @ColumnInfo(name = "middle_name")
    private String middleName;

    @ColumnInfo(name = "home_number")
    private String homeNumber;

    @ColumnInfo(name = "mobile_number")
    private String mobileNumber;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "notes")
    private String notes;

    public Contact() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        if (TextUtils.isEmpty(firstName)) {
            return "";
        }

        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        if (TextUtils.isEmpty(lastName)) {
            return "";
        }
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        if (TextUtils.isEmpty(middleName)) {
            return "";
        }
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getHomeNumber() {
        if (TextUtils.isEmpty(homeNumber)) {
            return "";
        }
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getMobileNumber() {
        if (TextUtils.isEmpty(mobileNumber)) {
            return "";
        }
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        if (TextUtils.isEmpty(email)) {
            return "";
        }
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotes() {
        if (TextUtils.isEmpty(notes)) {
            return "";
        }
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
