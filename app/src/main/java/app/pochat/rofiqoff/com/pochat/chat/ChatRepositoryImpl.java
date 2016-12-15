package app.pochat.rofiqoff.com.pochat.chat;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import app.pochat.rofiqoff.com.pochat.chat.entities.ChatMessage;
import app.pochat.rofiqoff.com.pochat.chat.event.ChatEvent;
import app.pochat.rofiqoff.com.pochat.domain.FirebaseHelper;
import app.pochat.rofiqoff.com.pochat.lib.Eventbus;
import app.pochat.rofiqoff.com.pochat.lib.GreenRobotEventbus;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public class ChatRepositoryImpl implements ChatRepository {
	private String receiver;
	private FirebaseHelper helper;
	private ChildEventListener chatEventListener;

	public ChatRepositoryImpl() {
		helper = FirebaseHelper.getInstance();
	}

	@Override
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@Override
	public void subscribeForChatUpdate() {
		if (chatEventListener == null){
			chatEventListener = new ChildEventListener() {
				@Override
				public void onChildAdded(DataSnapshot dataSnapshot, String previousChildKey) {
					ChatMessage chatMessage = dataSnapshot.getValue(ChatMessage.class);
					String messageSender = chatMessage.getSender();
					messageSender = messageSender.replace("_", ".");

					String currentUserEmail = helper.getAuthUserEmail();
					chatMessage.setSentByMe(messageSender.equals(currentUserEmail));

					ChatEvent chatEvent = new ChatEvent(chatMessage);
					Eventbus eventbus = GreenRobotEventbus.getInstance();
					eventbus.post(chatEvent);
				}

				@Override
				public void onChildChanged(DataSnapshot dataSnapshot, String s) {

				}

				@Override
				public void onChildRemoved(DataSnapshot dataSnapshot) {

				}

				@Override
				public void onChildMoved(DataSnapshot dataSnapshot, String s) {

				}

				@Override
				public void onCancelled(DatabaseError databaseError) {

				}
			};
			helper.getChatReference(receiver).addChildEventListener(chatEventListener);
		}
	}

	@Override
	public void unsubscribeForChatUpdate() {
		if (chatEventListener != null){
			helper.getChatReference(receiver).removeEventListener(chatEventListener);
		}
	}

	@Override
	public void destroyChatLisener() {
		chatEventListener = null;
	}

	@Override
	public void sendMessage(String message) {
		String keySender = helper.getAuthUserEmail().replace(".", "_");
		ChatMessage chatMessage = new ChatMessage(keySender, message);
		DatabaseReference chatReference = helper.getChatReference(receiver);
		chatReference.push().setValue(chatMessage);
	}

	@Override
	public void changeUserConnectionStatus(boolean online) {
		helper.changeUserConnectionStatus(online);
	}
}
