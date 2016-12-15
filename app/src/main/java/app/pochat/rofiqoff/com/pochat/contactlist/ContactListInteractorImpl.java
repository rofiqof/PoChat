package app.pochat.rofiqoff.com.pochat.contactlist;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public class ContactListInteractorImpl implements ContactListInteractor {
	ContactListRepositoryImpl contactListRepository;

	public ContactListInteractorImpl() {
		contactListRepository = new ContactListRepositoryImpl();
	}

	@Override
	public void subscribeForContactEvents() {
		contactListRepository.subscribeForContactListUpdates();
	}

	@Override
	public void unsubscribeForContactEvents() {
		contactListRepository.unSubscribeForContactListUpdates();
	}

	@Override
	public void destroyContactListListener() {
		contactListRepository.destroyContactListListener();
	}

	@Override
	public void removeContact(String email) {
		contactListRepository.removeContact(email);
	}
}
