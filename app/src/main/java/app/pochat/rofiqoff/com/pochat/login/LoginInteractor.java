package app.pochat.rofiqoff.com.pochat.login;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public interface LoginInteractor {
	void checkAlreadyAuthenticated();
	void doSignUp(String email, String password);
	void doSignIn(String email, String passowrd);
}
