package app.pochat.rofiqoff.com.pochat.contactlist;

import org.greenrobot.eventbus.Subscribe;

import app.pochat.rofiqoff.com.pochat.contactlist.entities.User;
import app.pochat.rofiqoff.com.pochat.contactlist.event.ContactListEvent;
import app.pochat.rofiqoff.com.pochat.contactlist.ui.ContactListView;
import app.pochat.rofiqoff.com.pochat.lib.Eventbus;
import app.pochat.rofiqoff.com.pochat.lib.GreenRobotEventbus;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public class ContactListPresenterImpl implements ContactListPresenter {
	Eventbus eventbus;
	ContactListView contactListView;
	ContactListSessionInteractor contactListSessionInteractor;
	ContactListInteractor contactListInteractor;

	public ContactListPresenterImpl(ContactListView contactListView) {
		this.eventbus = GreenRobotEventbus.getInstance();
		this.contactListView = contactListView;
		this.contactListInteractor = new ContactListInteractorImpl();
		this.contactListSessionInteractor = new ContactListSessionInteractorImpl();
	}

	@Override
	public void signOff() {
		contactListSessionInteractor.changeConnectionStatus(User.OFFLINE);
		contactListInteractor.destroyContactListListener();
		contactListInteractor.unsubscribeForContactEvents();
		contactListSessionInteractor.signOff();
	}

	@Override
	public String getCurrentUserEmail() {
		return contactListSessionInteractor.getCurrentUserEmail();
	}

	@Override
	public void removeContact(String email) {
		contactListInteractor.removeContact(email);
	}

	@Override
	public void onCreate() {
		eventbus.register(this);
	}

	@Override
	public void onPause() {
		contactListSessionInteractor.changeConnectionStatus(User.OFFLINE);
		contactListInteractor.subscribeForContactEvents();
	}

	@Override
	public void onResume() {
		contactListSessionInteractor.changeConnectionStatus(User.ONLINE);
		contactListInteractor.subscribeForContactEvents();
	}

	@Override
	public void onDestroy() {
		eventbus.unregister(this);
		contactListInteractor.destroyContactListListener();
		contactListView = null;
	}

	@Override
	@Subscribe
	public void onEventMainThread(ContactListEvent event) {
		User user = event.getUser();
		switch (event.getEventType()){
			case ContactListEvent.onContactAdded :
				onContactAdded(user);
				break;
			case ContactListEvent.onContactChanged:
				onContactChanged(user);
				break;
			case ContactListEvent.onContactRemoved:
				onContactRemoved(user);
				break;
		}
	}

	private void onContactAdded(User user) {
		if (contactListView != null){
			contactListView.onContactAdded(user);
		}
	}

	private void onContactChanged(User user) {
		if (contactListView != null){
			contactListView.onContactChanged(user);
		}
	}

	private void onContactRemoved(User user) {
		if (contactListView != null){
			contactListView.onContactRemoved(user);
		}
	}
}
