package app.pochat.rofiqoff.com.pochat.tab.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import app.pochat.rofiqoff.com.pochat.akun.ui.fragment.AkunFragment;
import app.pochat.rofiqoff.com.pochat.chat.ui.fragment.ChatFragment;
import app.pochat.rofiqoff.com.pochat.contactlist.ui.fragment.ContactListFragment;
import app.pochat.rofiqoff.com.pochat.tab.fragment.TimelineFragment;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public class PageAdapterTab extends FragmentPagerAdapter {
	Activity activity;

	public PageAdapterTab(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		Fragment fragment = null;

		switch (position){
			case 0 :
				fragment = new TimelineFragment();
				break;
			case 1 :
				fragment = new ChatFragment();
				break;
			case 2 :
				fragment = new ContactListFragment();
				break;
			case 3 :
				fragment = new AkunFragment();
				break;
			default:
				break;
		}
		return fragment;
	}

	@Override
	public CharSequence getPageTitle(int position) {
//        return title[position];
		return null;
	}

	@Override
	public int getCount() {
		return 4;
	}
}
