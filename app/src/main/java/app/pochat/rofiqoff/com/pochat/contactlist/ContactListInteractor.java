package app.pochat.rofiqoff.com.pochat.contactlist;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public interface ContactListInteractor {
	void subscribeForContactEvents();
	void unsubscribeForContactEvents();
	void destroyContactListListener();
	void removeContact(String email);
}
