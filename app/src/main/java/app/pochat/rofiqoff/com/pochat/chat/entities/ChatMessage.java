package app.pochat.rofiqoff.com.pochat.chat.entities;

import com.google.firebase.database.Exclude;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public class ChatMessage {
	String message;
	String sender;
	@Exclude
	boolean sentByMe;

	public ChatMessage() {
	}

	public ChatMessage(String sender, String message) {
		this.message = message;
		this.sender = sender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public boolean isSentByMe() {
		return sentByMe;
	}

	public void setSentByMe(boolean sentByMe) {
		this.sentByMe = sentByMe;
	}
}
