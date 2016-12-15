package app.pochat.rofiqoff.com.pochat.akun.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.pochat.rofiqoff.com.pochat.R;
import app.pochat.rofiqoff.com.pochat.contactlist.ContactListPresenter;
import app.pochat.rofiqoff.com.pochat.contactlist.ContactListPresenterImpl;
import app.pochat.rofiqoff.com.pochat.contactlist.entities.User;
import app.pochat.rofiqoff.com.pochat.contactlist.ui.ContactListView;
import app.pochat.rofiqoff.com.pochat.login.ui.LoginActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AkunFragment extends Fragment implements ContactListView {

//	@BindView(R.id.gridView) GridView gridView;

	private ContactListPresenter contactListPresenter;

	public AkunFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_akun, container, false);
		ButterKnife.bind(this, view);

		contactListPresenter = new ContactListPresenterImpl(this);
		contactListPresenter.onCreate();

//		gridView.setAdapter(new ImageGridAdapter(getContext()));
		return view;
	}

//	@Override
//	public void onResume() {
//		super.onResume();
//		contactListPresenter.onResume();
//	}

//	@Override
//	public void onPause() {
//		super.onPause();
//		contactListPresenter.onPause();
//	}

//	@Override
//	public void onDestroy() {
//		contactListPresenter.onDestroy();
//		super.onDestroy();
//	}

	@OnClick(R.id.logOut_akun)
	public void logOut(){
		contactListPresenter.signOff();
		Intent intent = new Intent(getContext(), LoginActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(intent);
	}

	@Override
	public void onContactAdded(User user) {

	}

	@Override
	public void onContactChanged(User user) {

	}

	@Override
	public void onContactRemoved(User user) {

	}
}
