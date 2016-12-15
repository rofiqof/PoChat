package app.pochat.rofiqoff.com.pochat;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public class PoChatAplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		setupFirebase();
	}

	private void setupFirebase() {
		FirebaseDatabase.getInstance().setPersistenceEnabled(true);
	}
}
