package app.pochat.rofiqoff.com.pochat.contactlist.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.pochat.rofiqoff.com.pochat.R;
import app.pochat.rofiqoff.com.pochat.contactlist.entities.User;
import app.pochat.rofiqoff.com.pochat.contactlist.ui.OnItemClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {
	private List<User> contactList;
	private OnItemClickListener clickListener;

	public ContactListAdapter(List<User> contactList, OnItemClickListener clickListener) {
		this.contactList = contactList;
		this.clickListener = clickListener;
	}

	@Override
	public ContactListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_contact, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ContactListAdapter.ViewHolder holder, int position) {
		User user = contactList.get(position);
		holder.setClikListener(user, clickListener);

		String email = user.getEmail();
		boolean online = user.isOnline();
		String status = online ? "online" : "offline";
		int color = online ? Color.GREEN : Color.RED;

		holder.textUser.setText(email);
		holder.textStatus.setText(status);
		holder.textStatus.setTextColor(color);
	}

	@Override
	public int getItemCount() {
		return contactList.size();
	}


	public int getPositionByUsername(String username){
		int position = 0;
		for (User user : contactList){
			if (user.getEmail().equalsIgnoreCase(username)){
				break;
			}
			position++;
		}

		return position;
	}

	private boolean alreadyInAdapter(User newUser){
		boolean alreadyInAdapter = false;
		for (User user : this.contactList){
			if (user.getEmail().equalsIgnoreCase(newUser.getEmail())){
				alreadyInAdapter = true;
				break;
			}
		}
		return alreadyInAdapter;
	}

	public void add(User user){
		if (!alreadyInAdapter(user)){
			this.contactList.add(user);
			this.notifyDataSetChanged();
		}
	}

	public void update(User user){
		int pos = getPositionByUsername(user.getEmail());
		contactList.set(pos, user);
		this.notifyDataSetChanged();
	}

	public void remove(User user){
		int pos = getPositionByUsername(user.getEmail());
		contactList.remove(pos);
		this.notifyDataSetChanged();
	}


	public class ViewHolder extends RecyclerView.ViewHolder {
		@BindView(R.id.txtStatus) TextView textStatus;
		@BindView(R.id.txtUser) TextView textUser;
		View view;

		public ViewHolder(View view) {
			super(view);
			this.view = view;
			ButterKnife.bind(this, view);
		}

		public void setClikListener(final User user, final OnItemClickListener listener){
			view.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					listener.onItemClick(user);
				}
			});

			view.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					listener.onItemLongClick(user);
					return false;
				}
			});
		}
	}
}
