package app.pochat.rofiqoff.com.pochat.chat;

import app.pochat.rofiqoff.com.pochat.chat.event.ChatEvent;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public interface ChatPresenter {
	void onPause();
	void onResume();
	void onCreate();
	void onDestroy();

	void setChatRecipient(String recipient);

	void sendMessage(String message);
	void onEventMainThread(ChatEvent event);
}
