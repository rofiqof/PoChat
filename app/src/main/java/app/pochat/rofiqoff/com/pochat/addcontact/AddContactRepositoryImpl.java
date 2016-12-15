package app.pochat.rofiqoff.com.pochat.addcontact;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import app.pochat.rofiqoff.com.pochat.addcontact.event.AddContactEvent;
import app.pochat.rofiqoff.com.pochat.contactlist.entities.User;
import app.pochat.rofiqoff.com.pochat.domain.FirebaseHelper;
import app.pochat.rofiqoff.com.pochat.lib.Eventbus;
import app.pochat.rofiqoff.com.pochat.lib.GreenRobotEventbus;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public class AddContactRepositoryImpl implements AddContactRepository{
	@Override
	public void addContact(final String email) {
		final String key = email.replace(".","_");

		FirebaseHelper helper = FirebaseHelper.getInstance();
		final DatabaseReference userReference = helper.getUserReference(email);

		userReference.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				User user = dataSnapshot.getValue(User.class);
				AddContactEvent event = new AddContactEvent();
				if (user != null){
					boolean online = user.isOnline();
					FirebaseHelper helper = FirebaseHelper.getInstance();

					DatabaseReference userContactReference = helper.getMyContactReference();
					userContactReference.child(key).setValue(online);

					String currentUserEmailKey = helper.getAuthUserEmail();
					currentUserEmailKey = currentUserEmailKey.replace(".", "_");

					DatabaseReference reserveUserContactReference = helper.getContactReference(email);
					reserveUserContactReference.child(currentUserEmailKey).setValue(true);
				} else {
					event.setError(true);
				}
				Eventbus eventbus = GreenRobotEventbus.getInstance();
				eventbus.post(event);
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});
	}
}
