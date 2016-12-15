package app.pochat.rofiqoff.com.pochat.chat.ui;

import app.pochat.rofiqoff.com.pochat.chat.entities.ChatMessage;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public interface ChatView {
	void sendMessage();
	void onMessageReceived(ChatMessage message);

}
