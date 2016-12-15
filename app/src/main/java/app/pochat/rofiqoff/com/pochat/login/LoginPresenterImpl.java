package app.pochat.rofiqoff.com.pochat.login;

import org.greenrobot.eventbus.Subscribe;

import app.pochat.rofiqoff.com.pochat.lib.Eventbus;
import app.pochat.rofiqoff.com.pochat.lib.GreenRobotEventbus;
import app.pochat.rofiqoff.com.pochat.login.event.LoginEvent;
import app.pochat.rofiqoff.com.pochat.login.ui.LoginView;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public class LoginPresenterImpl implements LoginPresenter {

	Eventbus eventbus;
	LoginView loginView;
	LoginInteractor loginInteractor;

	public LoginPresenterImpl(LoginView loginView) {
		this.loginView = loginView;
		this.eventbus = GreenRobotEventbus.getInstance();
		this.loginInteractor = new LoginInteractorImpl();
	}

	@Override
	public void onCreate() {
		eventbus.register(this);
	}

	@Override
	public void onDestroy() {
		loginView = null;
		eventbus.unregister(this);
	}

	@Override
	public void checkForAuthenticatedUser() {
		if (loginView != null){
			loginView.disableInput();
			loginView.showProgress();
		}
		loginInteractor.checkAlreadyAuthenticated();
	}

	@Override
	@Subscribe
	public void onEventMainThread(LoginEvent event) {
		switch (event.getEventType()){
			case LoginEvent.onSignInError:
				onSignInError(event.getErrorMessage());
				break;
			case LoginEvent.onSignInSuccess:
				onSignInSuccess();
				break;
			case LoginEvent.onSignUpError:
				onSignUpError(event.getErrorMessage());
				break;
			case LoginEvent.onSignUpSuccess:
				onSignUpSuccess();
				break;
			case LoginEvent.onFailedToRecoverSession:
				onFailedToRecoverSession();
				break;
		}
	}

	@Override
	public void validateLogin(String email, String password) {
		if (loginView != null){
			loginView.disableInput();
			loginView.showProgress();
		}
		loginInteractor.doSignIn(email, password);
	}

	@Override
	public void registerNewUser(String email, String password) {
		if (loginView != null){
			loginView.disableInput();
			loginView.showProgress();
		}
		loginInteractor.doSignUp(email, password);
	}


	private void onSignInSuccess() {
		if (loginView != null){
			loginView.navigateToMainScreen();
		}
	}

	private void onSignInError(String errorMessage) {
		if (loginView != null){
			loginView.hideProgress();
			loginView.enableInput();
			loginView.loginError(errorMessage);
		}
	}

	private void onSignUpSuccess() {
		if (loginView != null){
			loginView.newUserSuccess();
		}
	}

	private void onSignUpError(String errorMessage) {
		if (loginView != null){
			loginView.hideProgress();
			loginView.enableInput();
			loginView.newUserError(errorMessage);
		}
	}

	private void onFailedToRecoverSession() {
		if (loginView != null){
			loginView.hideProgress();
			loginView.enableInput();
		}
	}
}
