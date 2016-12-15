package app.pochat.rofiqoff.com.pochat.chat;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public class ChatInteractorImpl implements ChatInteractor {
	ChatRepository chatRepository;

	public ChatInteractorImpl() {
		this.chatRepository = new ChatRepositoryImpl();
	}

	@Override
	public void sendMessage(String msg) {
		chatRepository.sendMessage(msg);
	}

	@Override
	public void setRecipient(String recipient) {
		chatRepository.setReceiver(recipient);
	}

	@Override
	public void destroyChatListener() {
		chatRepository.destroyChatLisener();
	}

	@Override
	public void subscribeForChatUpate() {
		chatRepository.subscribeForChatUpdate();
	}

	@Override
	public void unSubscribeForChatUpate() {
		chatRepository.unsubscribeForChatUpdate();
	}
}
