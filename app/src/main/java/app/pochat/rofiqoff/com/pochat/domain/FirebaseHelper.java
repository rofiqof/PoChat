package app.pochat.rofiqoff.com.pochat.domain;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import app.pochat.rofiqoff.com.pochat.contactlist.entities.User;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public class FirebaseHelper {
	private DatabaseReference databaseReference;
	private final static String SEPARATOR = "___";
	private final static String CHATS_PATH = "chats";
	private final static String USERS_PATH = "users";
	private final static String CONTACTS_PATH = "contacts";

	private static class SingletoHolder {
		private static final FirebaseHelper INTANCE = new FirebaseHelper();
	}

	public static FirebaseHelper getInstance(){
		return SingletoHolder.INTANCE;
	}

	public FirebaseHelper(){
		databaseReference = FirebaseDatabase.getInstance().getReference();
	}

	public DatabaseReference getDatabaseReference(){
		return databaseReference;
	}

	public String getAuthUserEmail(){
		FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
		String email = null;
		if (user != null){
			email = user.getEmail();
		}
		return email;
	}

	public DatabaseReference getUserReference(String email){
		DatabaseReference userReference = null;
		if (email != null){
			String emailKey = email.replace(".","_");
			userReference = databaseReference.getRoot().child(USERS_PATH).child(emailKey);
		}
		return userReference;
	}

	public DatabaseReference getMyUserReference(){
		return getUserReference(getAuthUserEmail());
	}

	public DatabaseReference getContactReference(String email){
		return getUserReference(email).child(CONTACTS_PATH);
	}

	public DatabaseReference getMyContactReference(){
		return getContactReference(getAuthUserEmail());
	}

	public DatabaseReference getOneContactReference(String mainEmail, String childEMail){
		String childKey = childEMail.replace(".","_");
		return getUserReference(mainEmail).child(CONTACTS_PATH).child(childKey);
	}

	public DatabaseReference getChatReference(String receiver){
		String keySender = getAuthUserEmail().replace(".","_");
		String keyReciver = receiver.replace(".", "_");

		String keyChat = keySender + SEPARATOR + keyReciver;
		if (keySender.compareTo(keyReciver) > 0){
			keyChat = keyReciver + SEPARATOR + keySender;
		}
		return databaseReference.getRoot().child(CHATS_PATH).child(keyChat);
	}

	public void changeUserConnectionStatus(boolean online){
		if (getMyUserReference() != null){
			Map<String, Object> updates = new HashMap<String, Object>();
			updates.put("online", online);
			getMyUserReference().updateChildren(updates);

			notifyContactsOfConnection(online);
		}
	}

	public void notifyContactsOfConnection(final boolean online, final boolean signoff) {
		final String myEmail = getAuthUserEmail();
		getMyContactReference().addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				for (DataSnapshot child : dataSnapshot.getChildren()){
					String email = child.getKey();
					DatabaseReference reference = getOneContactReference(email, myEmail);
					reference.setValue(online);
				}
				if (signoff){
					FirebaseAuth.getInstance().signOut();
				}
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});
	}

	public void notifyContactsOfConnection(boolean online) {
		notifyContactsOfConnection(online, false);
	}

	public void signOff(){
		notifyContactsOfConnection(User.OFFLINE, true);
	}

}