package app.pochat.rofiqoff.com.pochat.contactlist.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import app.pochat.rofiqoff.com.pochat.R;
import app.pochat.rofiqoff.com.pochat.addcontact.ui.AddContactFragment;
import app.pochat.rofiqoff.com.pochat.chat.ui.ChatActivity;
import app.pochat.rofiqoff.com.pochat.contactlist.ContactListPresenter;
import app.pochat.rofiqoff.com.pochat.contactlist.ContactListPresenterImpl;
import app.pochat.rofiqoff.com.pochat.contactlist.adapter.ContactListAdapter;
import app.pochat.rofiqoff.com.pochat.contactlist.entities.User;
import app.pochat.rofiqoff.com.pochat.contactlist.ui.ContactListView;
import app.pochat.rofiqoff.com.pochat.contactlist.ui.OnItemClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactListFragment extends Fragment implements ContactListView, OnItemClickListener{
	@BindView(R.id.recyclerView_contact_list) RecyclerView recyclerView;

	private ContactListAdapter adapter;
	private ContactListPresenter contactListPresenter;

	public ContactListFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_contact_list, container, false);
		ButterKnife.bind(this, view);

		contactListPresenter = new ContactListPresenterImpl(this);
		contactListPresenter.onCreate();

		setUpAdapter();
		setUpRecyclerView();
		return view;
	}

	@OnClick(R.id.fab)
	public void addContact(){
		AddContactFragment fragment = new AddContactFragment();
		fragment.show(getFragmentManager(), "");
	}

	@Override
	public void onResume() {
		super.onResume();
		contactListPresenter.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		contactListPresenter.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		contactListPresenter.onDestroy();
	}

	private void setUpAdapter() {
		adapter = new ContactListAdapter(new ArrayList<User>(), (OnItemClickListener) this);
	}

	private void setUpRecyclerView() {
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerView.setAdapter(adapter);
	}

	@Override
	public void onContactAdded(User user) {
		adapter.add(user);
	}

	@Override
	public void onContactChanged(User user) {
		adapter.update(user);
	}

	@Override
	public void onContactRemoved(User user) {
		adapter.remove(user);
	}


	@Override
	public void onItemClick(User user) {
		Intent intent = new Intent(getContext(), ChatActivity.class);
		intent.putExtra(ChatActivity.EMAIL_KEY, user.getEmail());
		intent.putExtra(ChatActivity.ONLINE_KEY, user.isOnline());
		startActivity(intent);
	}

	@Override
	public void onItemLongClick(User user) {
		contactListPresenter.removeContact(user.getEmail());
	}
}
