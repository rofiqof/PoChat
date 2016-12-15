package app.pochat.rofiqoff.com.pochat.chat;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public interface ChatRepository {
	void sendMessage(String message);
	void setReceiver(String receiver);

	void destroyChatLisener();
	void subscribeForChatUpdate();
	void unsubscribeForChatUpdate();

	void changeUserConnectionStatus(boolean online);
}
