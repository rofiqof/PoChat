package app.pochat.rofiqoff.com.pochat.addcontact;

import app.pochat.rofiqoff.com.pochat.addcontact.event.AddContactEvent;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public interface AddContactPresenter {
	void onShow();
	void onDestroy();

	void addContact(String email);
	void onEventMainThread(AddContactEvent event);
}
