package sha.com.contactsapp;

import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ContactInformationActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private EditText firstNameTextView;
    private EditText middleNameTextView;
    private EditText lastNameTextView;
    private EditText mobileNumberTextView;
    private EditText homeNumberTextView;
    private EditText emailTextView;
    private EditText noteTextView;

    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_information);
        initToolbbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firstNameTextView = findViewById(R.id.tvFirstName);
        middleNameTextView = findViewById(R.id.tvMiddleName);
        lastNameTextView = findViewById(R.id.tvLastName);
        mobileNumberTextView = findViewById(R.id.tvMobileNumber);
        homeNumberTextView = findViewById(R.id.tvHomeNumber);
        emailTextView = findViewById(R.id.tvEmail);
        noteTextView = findViewById(R.id.tvNote);

        if (getIntent() != null) {
            contact = (Contact) getIntent().getSerializableExtra("contact_data");
            if (contact != null) {
                displayContact();
            }
        }
    }

    private void displayContact() {
        firstNameTextView.setText(contact.getFirstName());
        middleNameTextView.setText(contact.getMiddleName());
        lastNameTextView.setText(contact.getLastName());
        mobileNumberTextView.setText(contact.getMobileNumber());
        homeNumberTextView.setText(contact.getHomeNumber());
        emailTextView.setText(contact.getEmail());
        noteTextView.setText(contact.getNotes());
    }
    private void initToolbbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.contact_information);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contact_information, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else if (item.getItemId() == R.id.saveContact) {
            save();
        }
        return true;
    }

    private void save() {
        if (contact == null) {
            contact = new Contact();
        }
        contact.setFirstName(firstNameTextView.getText().toString());
        contact.setMiddleName(middleNameTextView.getText().toString());
        contact.setLastName(lastNameTextView.getText().toString());
        contact.setMobileNumber(mobileNumberTextView.getText().toString());
        contact.setHomeNumber(homeNumberTextView.getText().toString());
        contact.setEmail(emailTextView.getText().toString());
        contact.setNotes(noteTextView.getText().toString());

        new ContactInformationAsync().execute(contact);
    }

    private class ContactInformationAsync extends AsyncTask<Contact, Void, Long> {
        @Override
        protected Long doInBackground(Contact... contacts) {
            Contact contact = contacts[0];
            if (contact.getUid() > 0) {
                return (long) ((MyApplication)getApplication()).contactDao().update(contacts[0]);
            }
            return ((MyApplication)getApplication()).contactDao().insert(contacts[0]);
        }

        @Override
        protected void onPostExecute(Long result) {
            if (result > 0) {
                Toast.makeText(ContactInformationActivity.this, "Record updated", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
