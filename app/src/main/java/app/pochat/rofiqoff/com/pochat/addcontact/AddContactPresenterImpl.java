package app.pochat.rofiqoff.com.pochat.addcontact;

import org.greenrobot.eventbus.Subscribe;

import app.pochat.rofiqoff.com.pochat.addcontact.event.AddContactEvent;
import app.pochat.rofiqoff.com.pochat.addcontact.ui.AddContactView;
import app.pochat.rofiqoff.com.pochat.lib.Eventbus;
import app.pochat.rofiqoff.com.pochat.lib.GreenRobotEventbus;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public class AddContactPresenterImpl implements AddContactPresenter {
	Eventbus eventbus;
	AddContactView addContactView;
	AddContactInteractor addContactInteractor;

	public AddContactPresenterImpl(AddContactView addContactView) {
		this.eventbus = GreenRobotEventbus.getInstance();
		this.addContactView = addContactView;
		this.addContactInteractor = new AddContactInteractorImpl(new AddContactRepositoryImpl());
	}

	@Override
	public void onShow() {
		eventbus.register(this);
	}

	@Override
	public void onDestroy() {
		addContactView = null;
		eventbus.unregister(this);
	}

	@Override
	public void addContact(String email) {
		addContactView.hideInput();
		addContactView.showProgress();
		this.addContactInteractor.addContact(email);
	}

	@Override
	@Subscribe
	public void onEventMainThread(AddContactEvent event) {
		if (addContactView != null){
			addContactView.hideProgress();
			addContactView.showInput();

			if (event.isError()){
				addContactView.contactAdded();
			} else {
				addContactView.contactNotAdded();
			}
		}
	}
}
