package app.pochat.rofiqoff.com.pochat.contactlist;

import app.pochat.rofiqoff.com.pochat.contactlist.event.ContactListEvent;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public interface ContactListPresenter {
	void onPause();
	void onResume();
	void onCreate();
	void onDestroy();

	void signOff();
	String getCurrentUserEmail();
	void removeContact(String email);
	void onEventMainThread(ContactListEvent event);
}
