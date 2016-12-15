package app.pochat.rofiqoff.com.pochat.contactlist.entities;

import java.util.Map;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public class User {
	String email;
	boolean online;
	Map<String, Boolean> contacts;
	public static final boolean ONLINE = true;
	public static final boolean OFFLINE = false;

	public User() {
	}

	public User(String email, boolean online, Map<String, Boolean> contacts) {
		this.email = email;
		this.online = online;
		this.contacts = contacts;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public Map<String, Boolean> getContacts() {
		return contacts;
	}

	public void setContacts(Map<String, Boolean> contacts) {
		this.contacts = contacts;
	}

	public static boolean isONLINE() {
		return ONLINE;
	}

	public static boolean isOFFLINE() {
		return OFFLINE;
	}
}
