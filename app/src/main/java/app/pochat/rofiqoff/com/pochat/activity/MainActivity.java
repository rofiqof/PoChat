package app.pochat.rofiqoff.com.pochat.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import app.pochat.rofiqoff.com.pochat.R;
import app.pochat.rofiqoff.com.pochat.contactlist.ContactListPresenter;
import app.pochat.rofiqoff.com.pochat.contactlist.ContactListPresenterImpl;
import app.pochat.rofiqoff.com.pochat.contactlist.entities.User;
import app.pochat.rofiqoff.com.pochat.contactlist.ui.ContactListView;
import app.pochat.rofiqoff.com.pochat.tab.adapter.PageAdapterTab;

public class MainActivity extends AppCompatActivity implements ContactListView{

	private Toolbar toolbar;
	private ViewPager viewPager;
	private TabLayout tabLayout;
	private int[] tabIconWhite = {
			R.drawable.ic_update_white_48px,
			R.drawable.ic_chat_white_48px,
			R.drawable.ic_contacts_white_48px,
			R.drawable.ic_account_circle_white_48px
	};

	private ContactListPresenter contactListPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		toolbar = (Toolbar) findViewById(R.id.toolbar_main);
		setSupportActionBar(toolbar);

		viewPager = (ViewPager) findViewById(R.id.viewPager);
		tabLayout = (TabLayout) findViewById(R.id.tabLayout);

		viewPager.setAdapter(new PageAdapterTab(getSupportFragmentManager()));

		tabLayout.setTabTextColors(getResources().getColor(R.color.colorText), getResources().getColor(R.color.colorText));
		tabLayout.setScrollBarSize(25);
		tabLayout.setupWithViewPager(viewPager);
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

		contactListPresenter = new ContactListPresenterImpl(this);
		setupTabIcon();
	}

	private void setupTabIcon() {
		tabLayout.getTabAt(0).setIcon(tabIconWhite[0]);
		tabLayout.getTabAt(1).setIcon(tabIconWhite[1]);
		tabLayout.getTabAt(2).setIcon(tabIconWhite[2]);
		tabLayout.getTabAt(3).setIcon(tabIconWhite[3]);
	}

	@Override
	protected void onResume() {
		super.onResume();
		contactListPresenter.onResume();
	}

//	@Override
//	protected void onPause() {
//		super.onPause();
//		contactListPresenter.onPause();
//	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		contactListPresenter.onDestroy();
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
