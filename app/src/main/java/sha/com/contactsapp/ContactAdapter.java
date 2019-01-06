package sha.com.contactsapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Shahul Hameed Shaik
 * Email: android.shahul@gmail.com
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    private List<Contact> contactList;
    private ItemClickListener onItemClickListener;

    public ContactAdapter(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_contact, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Contact contact = contactList.get(position);
        holder.fullName.setText(contact.getFirstName() + " " + contact.getMiddleName() + " " + contact.getLastName());
        holder.phoneNumber.setText(contact.getMobileNumber());
        holder.rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.onItemClickListener = itemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public View rowView;
        public TextView fullName, phoneNumber;

        public MyViewHolder(View view) {
            super(view);
            view.setTag(this);
            rowView = (View) view.findViewById(R.id.row_view);
            fullName = (TextView) view.findViewById(R.id.tvFullName);
            phoneNumber = (TextView) view.findViewById(R.id.tvPhoneNumber);
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
