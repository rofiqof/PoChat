package app.pochat.rofiqoff.com.pochat.contactlist;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import app.pochat.rofiqoff.com.pochat.contactlist.entities.User;
import app.pochat.rofiqoff.com.pochat.contactlist.event.ContactListEvent;
import app.pochat.rofiqoff.com.pochat.domain.FirebaseHelper;
import app.pochat.rofiqoff.com.pochat.lib.Eventbus;
import app.pochat.rofiqoff.com.pochat.lib.GreenRobotEventbus;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public class ContactListRepositoryImpl implements ContactListRepository {
	private FirebaseHelper helper;

	private ChildEventListener contactListEventListener;

	public ContactListRepositoryImpl() {
		helper = FirebaseHelper.getInstance();
	}

	@Override
	public void subscribeForContactListUpdates() {
		if (contactListEventListener == null){
			contactListEventListener = new ChildEventListener() {
				@Override
				public void onChildAdded(DataSnapshot dataSnapshot, String s) {
					String email = dataSnapshot.getKey();
					email = email.replace(".", "_");
					boolean online = ((Boolean)dataSnapshot.getValue()).booleanValue();
					User user = new User(email, online, null);
					postEvent(ContactListEvent.onContactAdded, user);
				}

				@Override
				public void onChildChanged(DataSnapshot dataSnapshot, String s) {
					String email = dataSnapshot.getKey();
					email = email.replace("_",".");
					boolean online = ((Boolean)dataSnapshot.getValue()).booleanValue();
					User user = new User(email, online, null);
					postEvent(ContactListEvent.onContactChanged, user);
				}

				@Override
				public void onChildRemoved(DataSnapshot dataSnapshot) {
					String email = dataSnapshot.getKey();
					email = email.replace("_",".");
					boolean online = ((Boolean)dataSnapshot.getValue()).booleanValue();
					User user = new User(email, online, null);
					postEvent(ContactListEvent.onContactRemoved, user);
				}

				@Override
				public void onChildMoved(DataSnapshot dataSnapshot, String s) {

				}

				@Override
				public void onCancelled(DatabaseError databaseError) {

				}
			};
		}

		helper.getMyContactReference().addChildEventListener(contactListEventListener);
	}

	@Override
	public void destroyContactListListener() {
		contactListEventListener = null;
	}

	@Override
	public void unSubscribeForContactListUpdates() {
		if (contactListEventListener != null){
			helper.getMyContactReference().removeEventListener(contactListEventListener);
		}
	}

	@Override
	public void removeContact(String email) {
		String currentUserEmail = helper.getAuthUserEmail();
		helper.getOneContactReference(currentUserEmail, email).removeValue();
		helper.getOneContactReference(email, currentUserEmail).removeValue();
	}

	@Override
	public String getCurrentEmail() {
		return helper.getAuthUserEmail();
	}

	@Override
	public void signOff() {
		helper.signOff();
	}

	@Override
	public void changeUserConnectionStatus(boolean online) {
		helper.changeUserConnectionStatus(online);
	}

	private void postEvent(int type, User user) {
		ContactListEvent contactListEvent = new ContactListEvent();
		contactListEvent.setEventType(type);
		contactListEvent.setUser(user);
		Eventbus eventBus = GreenRobotEventbus.getInstance();
		eventBus.post(contactListEvent);
	}
}
