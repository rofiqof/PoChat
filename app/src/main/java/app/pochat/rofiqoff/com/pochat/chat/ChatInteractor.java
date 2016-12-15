package app.pochat.rofiqoff.com.pochat.chat;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public interface ChatInteractor {
	void sendMessage(String msg);
	void setRecipient(String recipient);

	void destroyChatListener();
	void subscribeForChatUpate();
	void unSubscribeForChatUpate();
}
