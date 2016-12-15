package app.pochat.rofiqoff.com.pochat.contactlist;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public interface ContactListSessionInteractor {
	void signOff();
	String getCurrentUserEmail();
	void changeConnectionStatus(boolean online);
}
