package app.pochat.rofiqoff.com.pochat.contactlist;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public interface ContactListRepository {
	void signOff();
	String getCurrentEmail();
	void removeContact(String email);
	void destroyContactListListener();
	void subscribeForContactListUpdates();
	void unSubscribeForContactListUpdates();
	void changeUserConnectionStatus(boolean online);
}
