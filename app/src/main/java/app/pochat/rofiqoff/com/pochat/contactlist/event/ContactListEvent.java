package app.pochat.rofiqoff.com.pochat.contactlist.event;

import app.pochat.rofiqoff.com.pochat.contactlist.entities.User;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public class ContactListEvent {
	private User user;
	private int eventType;

	public static final int onContactAdded = 0;
	public static final int onContactChanged = 1;
	public static final int onContactRemoved = 2;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getEventType() {
		return eventType;
	}

	public void setEventType(int eventType) {
		this.eventType = eventType;
	}
}
