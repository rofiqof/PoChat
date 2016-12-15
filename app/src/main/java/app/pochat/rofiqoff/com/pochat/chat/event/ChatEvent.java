package app.pochat.rofiqoff.com.pochat.chat.event;

import app.pochat.rofiqoff.com.pochat.chat.entities.ChatMessage;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public class ChatEvent {
	ChatMessage message;

	public ChatEvent(ChatMessage message) {
		this.message = message;
	}

	public ChatMessage getMessage() {
		return message;
	}
}
