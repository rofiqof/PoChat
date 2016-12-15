package app.pochat.rofiqoff.com.pochat.addcontact;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public class AddContactInteractorImpl implements AddContactInteractor{
	AddContactRepositoryImpl addContactRepository;

	public AddContactInteractorImpl(AddContactRepositoryImpl addContactRepository) {
		this.addContactRepository = addContactRepository;
	}

	@Override
	public void addContact(String email) {
		addContactRepository.addContact(email);
	}
}
