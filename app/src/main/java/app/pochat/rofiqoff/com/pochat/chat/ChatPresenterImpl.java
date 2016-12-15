package app.pochat.rofiqoff.com.pochat.chat;

import org.greenrobot.eventbus.Subscribe;

import app.pochat.rofiqoff.com.pochat.chat.entities.ChatMessage;
import app.pochat.rofiqoff.com.pochat.chat.event.ChatEvent;
import app.pochat.rofiqoff.com.pochat.chat.ui.ChatView;
import app.pochat.rofiqoff.com.pochat.contactlist.entities.User;
import app.pochat.rofiqoff.com.pochat.lib.Eventbus;
import app.pochat.rofiqoff.com.pochat.lib.GreenRobotEventbus;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public class ChatPresenterImpl implements ChatPresenter {
	Eventbus eventbus;
	ChatView chatView;
	ChatInteractor chatInteractor;
	ChatSessionInteractor chatSessionInteractor;

	public ChatPresenterImpl(ChatView chatView) {
		this.chatView = chatView;
		this.eventbus = GreenRobotEventbus.getInstance();
		this.chatInteractor = new ChatInteractorImpl();
		this.chatSessionInteractor = new ChatSessionInteractorImpl();
	}

	@Override
	public void onPause() {
		chatInteractor.unSubscribeForChatUpate();
		chatSessionInteractor.changeConnectionStatus(User.OFFLINE);
	}

	@Override
	public void onResume() {
		chatInteractor.subscribeForChatUpate();
		chatSessionInteractor.changeConnectionStatus(User.ONLINE);
	}

	@Override
	public void onCreate() {
		eventbus.register(this);
	}

	@Override
	public void onDestroy() {
		eventbus.unregister(this);
		chatInteractor.destroyChatListener();
		chatView = null;
	}

	@Override
	public void setChatRecipient(String recipient) {
		this.chatInteractor.setRecipient(recipient);
	}

	@Override
	public void sendMessage(String message) {
		chatInteractor.sendMessage(message);
	}

	@Override
	@Subscribe
	public void onEventMainThread(ChatEvent event) {
		if (chatView != null){
			ChatMessage message = event.getMessage();
			chatView.onMessageReceived(message);
		}
	}
}
