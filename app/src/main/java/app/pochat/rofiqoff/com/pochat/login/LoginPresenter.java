package app.pochat.rofiqoff.com.pochat.login;

import app.pochat.rofiqoff.com.pochat.login.event.LoginEvent;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public interface LoginPresenter {
	void onCreate();
	void onDestroy();
	void checkForAuthenticatedUser();
	void onEventMainThread(LoginEvent event);
	void validateLogin(String email, String password);
	void registerNewUser(String email, String password);
}
