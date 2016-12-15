package app.pochat.rofiqoff.com.pochat.login;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import app.pochat.rofiqoff.com.pochat.contactlist.entities.User;
import app.pochat.rofiqoff.com.pochat.domain.FirebaseHelper;
import app.pochat.rofiqoff.com.pochat.lib.Eventbus;
import app.pochat.rofiqoff.com.pochat.lib.GreenRobotEventbus;
import app.pochat.rofiqoff.com.pochat.login.event.LoginEvent;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public class LoginRepositoryImpl implements LoginRepository {

	private FirebaseHelper helper;
	private DatabaseReference databaseReference;
	private DatabaseReference myUserReference;

	public LoginRepositoryImpl() {
		helper = FirebaseHelper.getInstance();
		databaseReference = helper.getDatabaseReference();
		myUserReference = helper.getMyUserReference();
	}

	@Override
	public void signUp(final String email, final String password) {
		FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
				.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
					@Override
					public void onSuccess(AuthResult authResult) {
						postEvent(LoginEvent.onSignUpSuccess);
						signIn(email, password);
					}
				})
				.addOnFailureListener(new OnFailureListener() {
					@Override
					public void onFailure(@NonNull Exception e) {
						postEvent(LoginEvent.onSignUpError, e.getMessage());
					}
				});
	}

	@Override
	public void signIn(String email, String password) {
		try {
			FirebaseAuth auth = FirebaseAuth.getInstance();
			auth.signInWithEmailAndPassword(email, password)
					.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
						@Override
						public void onSuccess(AuthResult authResult) {
							myUserReference = helper.getMyUserReference();
							myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
								@Override
								public void onDataChange(DataSnapshot dataSnapshot) {
									initSignIn(dataSnapshot);
								}

								@Override
								public void onCancelled(DatabaseError databaseError) {
									postEvent(LoginEvent.onSignInError, databaseError.getMessage());
								}
							});
						}
					})
					.addOnFailureListener(new OnFailureListener() {
						@Override
						public void onFailure(@NonNull Exception e) {
							postEvent(LoginEvent.onSignInError, e.getMessage());
						}
					});
		} catch (Exception e){
			postEvent(LoginEvent.onSignInError, e.getMessage());
		}
	}

	@Override
	public void checkAlreadyAuthenticated() {
		if (FirebaseAuth.getInstance().getCurrentUser() != null){
			myUserReference = helper.getMyUserReference();
			myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
				@Override
				public void onDataChange(DataSnapshot dataSnapshot) {
					initSignIn(dataSnapshot);
				}

				@Override
				public void onCancelled(DatabaseError databaseError) {
					postEvent(LoginEvent.onSignInError, databaseError.getMessage());
				}
			});
		} else {
			postEvent(LoginEvent.onFailedToRecoverSession);
		}
	}

	private void postEvent(int type) {
		postEvent(type, null);
	}

	private void postEvent(int type, String errorMessage) {
		LoginEvent loginEvent = new LoginEvent();
		loginEvent.setEventType(type);
		if (errorMessage != null){
			loginEvent.setErrorMessage(errorMessage);
		}

		Eventbus eventbus = GreenRobotEventbus.getInstance();
		eventbus.post(loginEvent);
	}

	private void initSignIn(DataSnapshot dataSnapshot) {
		User currentUser = dataSnapshot.getValue(User.class);
		if (currentUser == null){
			registerNewUser();
		}
		helper.changeUserConnectionStatus(User.ONLINE);
		postEvent(LoginEvent.onSignInSuccess);
	}

	private void registerNewUser() {
		String email = helper.getAuthUserEmail();
		if (email != null){
			User currentUser = new User(email, true, null);
			myUserReference.setValue(currentUser);
		}
	}
}
