package app.pochat.rofiqoff.com.pochat.chat;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public class ChatSessionInteractorImpl implements ChatSessionInteractor {
	ChatRepository chatRepository;

	public ChatSessionInteractorImpl() {
		this.chatRepository = new ChatRepositoryImpl();
	}

	@Override
	public void changeConnectionStatus(boolean online) {
		chatRepository.changeUserConnectionStatus(online);
	}
}
