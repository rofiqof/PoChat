package app.pochat.rofiqoff.com.pochat.addcontact.ui;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public interface AddContactView {
	void showInput();
	void hideInput();
	void showProgress();
	void hideProgress();

	void contactAdded();
	void contactNotAdded();
}
