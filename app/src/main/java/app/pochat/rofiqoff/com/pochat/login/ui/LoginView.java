package app.pochat.rofiqoff.com.pochat.login.ui;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public interface LoginView {
	void enableInput();
	void disableInput();
	void showProgress();
	void hideProgress();

	void handleSignUp();
	void handleSignIn();

	void navigateToMainScreen();
	void loginError(String error);

	void newUserSuccess();
	void newUserError(String error);
}
