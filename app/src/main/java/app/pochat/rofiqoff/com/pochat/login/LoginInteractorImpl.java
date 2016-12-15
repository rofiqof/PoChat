package app.pochat.rofiqoff.com.pochat.login;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public class LoginInteractorImpl implements LoginInteractor {
	private LoginRepository loginRepository;

	public LoginInteractorImpl() {
		this.loginRepository = new LoginRepositoryImpl();
	}

	@Override
	public void checkAlreadyAuthenticated() {
		loginRepository.checkAlreadyAuthenticated();
	}

	@Override
	public void doSignUp(final String email, final String password) {
		loginRepository.signUp(email, password);
	}

	@Override
	public void doSignIn(String email, String passowrd) {
		loginRepository.signIn(email, passowrd);
	}
}
