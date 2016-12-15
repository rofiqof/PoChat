package app.pochat.rofiqoff.com.pochat.login;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public interface LoginRepository {
	void signUp(final String email, final String password);
	void signIn(String email, String password);
	void checkAlreadyAuthenticated();
}
