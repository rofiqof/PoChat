package app.pochat.rofiqoff.com.pochat.contactlist;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public class ContactListSessionInteractorImpl implements ContactListSessionInteractor {
	ContactListRepositoryImpl contactListRepository;

	public ContactListSessionInteractorImpl() {
		this.contactListRepository = new ContactListRepositoryImpl();
	}

	@Override
	public void signOff() {
		contactListRepository.signOff();
	}

	@Override
	public String getCurrentUserEmail() {
		return contactListRepository.getCurrentEmail();
	}

	@Override
	public void changeConnectionStatus(boolean online) {
		contactListRepository.changeUserConnectionStatus(online);
	}
}
