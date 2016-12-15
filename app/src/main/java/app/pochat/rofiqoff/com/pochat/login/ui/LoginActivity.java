package app.pochat.rofiqoff.com.pochat.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import app.pochat.rofiqoff.com.pochat.R;
import app.pochat.rofiqoff.com.pochat.activity.MainActivity;
import app.pochat.rofiqoff.com.pochat.login.LoginPresenter;
import app.pochat.rofiqoff.com.pochat.login.LoginPresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView{

	@BindView(R.id.button_login) Button btnSignIn;
	@BindView(R.id.button_signUp) Button btnSignUp;
	@BindView(R.id.editText_usernameLogin) EditText inputEmail;
	@BindView(R.id.editText_passwordLogin) EditText inputPassword;
	@BindView(R.id.progressBar) ProgressBar progressBar;
	@BindView(R.id.activity_main) LinearLayout container;

	private LoginPresenter loginPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ButterKnife.bind(this);

		loginPresenter = new LoginPresenterImpl(this);
		loginPresenter.onCreate();
		loginPresenter.checkForAuthenticatedUser();
	}

	@Override
	protected void onDestroy() {
		loginPresenter.onDestroy();
		super.onDestroy();
	}


	@Override
	@OnClick(R.id.button_signUp)
	public void handleSignUp() {
		loginPresenter.registerNewUser(inputEmail.getText().toString(), inputPassword.getText().toString());
	}

	@Override
	@OnClick(R.id.button_login)
	public void handleSignIn() {
		loginPresenter.validateLogin(inputEmail.getText().toString(), inputPassword.getText().toString());
	}

	@Override
	public void showProgress() {
		progressBar.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideProgress() {
		progressBar.setVisibility(View.GONE);
	}

	@Override
	public void enableInput() {
		setInput(true);
	}

	@Override
	public void disableInput() {
		setInput(false);
	}

	@Override
	public void loginError(String error) {
		inputPassword.setText("");
		String msgError = String.format(getString(R.string.login_error_message_signin), error);
		inputPassword.setError(msgError);
	}

	@Override
	public void navigateToMainScreen() {
		startActivity(new Intent(this, MainActivity.class));
	}

	@Override
	public void newUserSuccess() {
		Toast.makeText(this, R.string.login_notice_message_useradded, Toast.LENGTH_LONG).show();
	}

	@Override
	public void newUserError(String error) {
		inputPassword.setText("");
		String msgError = String.format(getString(R.string.login_error_message_signup), error);
		inputPassword.setError(msgError);
	}

	private void setInput(boolean enable) {
		btnSignIn.setEnabled(true);
		btnSignUp.setEnabled(true);
		inputEmail.setEnabled(true);
		inputPassword.setEnabled(true);
	}
}
