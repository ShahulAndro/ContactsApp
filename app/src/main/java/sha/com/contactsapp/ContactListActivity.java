package sha.com.contactsapp;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ContactListActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ContactAdapter contactAdapter;
    private RecyclerView recyclerView;
    private List<Contact> contactList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        initToolbbar();
    }

    private void initToolbbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.contact_list);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.contactList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void onResume() {
        super.onResume();
        new GetAllContactsAsync().execute();
    }

    private void showEmptyView() {
        if (contactList.size() < 1) {
            recyclerView.setVisibility(View.GONE);
            findViewById(R.id.empty_view).setVisibility(View.VISIBLE);
            findViewById(R.id.add_contact_textview).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(ContactListActivity.this, ContactInformationActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contact_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.addContact) {
            Intent intent = new Intent();
            intent.setClass(this, ContactInformationActivity.class);
            startActivity(intent);
        }
        return true;
    }

    private class GetAllContactsAsync extends AsyncTask<Void, Void, List<Contact>> {
        @Override
        protected List<Contact> doInBackground(Void... voids) {
            return ((MyApplication)getApplication()).contactDao().getAll();
        }

        @Override
        protected void onPostExecute(final List<Contact> contactList) {
            if (contactList.isEmpty()) {
                showEmptyView();
                return;
            }

            recyclerView.setVisibility(View.VISIBLE);
            findViewById(R.id.empty_view).setVisibility(View.GONE);

            ContactListActivity.this.contactList = contactList;
            contactAdapter = new ContactAdapter(contactList);
            contactAdapter.setItemClickListener(new ContactAdapter.ItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent();
                    intent.setClass(ContactListActivity.this, ContactInformationActivity.class);
                    intent.putExtra("contact_data", contactList.get(position));
                    startActivity(intent);
                }
            });
            recyclerView.setAdapter(contactAdapter);
        }
    }
}
