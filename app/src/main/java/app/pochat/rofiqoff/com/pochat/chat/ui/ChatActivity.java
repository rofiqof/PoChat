package app.pochat.rofiqoff.com.pochat.chat.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import app.pochat.rofiqoff.com.pochat.R;
import app.pochat.rofiqoff.com.pochat.chat.ChatPresenter;
import app.pochat.rofiqoff.com.pochat.chat.ChatPresenterImpl;
import app.pochat.rofiqoff.com.pochat.chat.adapter.ChatAdapter;
import app.pochat.rofiqoff.com.pochat.chat.entities.ChatMessage;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity implements ChatView{

	@BindView(R.id.toolbar_chat) Toolbar toolbar;
	@BindView(R.id.txtUser) TextView txtUser;
	@BindView(R.id.txtStatus) TextView txtStatus;
	@BindView(R.id.editTxtMessage) EditText inputMessage;
	@BindView(R.id.messageRecyclerView) RecyclerView recyclerView;

	public final static String EMAIL_KEY = "email";
	public final static String ONLINE_KEY = "online";

	private ChatAdapter adapter;
	private ChatPresenter chatPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		ButterKnife.bind(this);

		chatPresenter = new ChatPresenterImpl(this);
		chatPresenter.onCreate();

		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		Intent intent = getIntent();
		setToolbarData(intent);

		setupAdapter();
		setupRecyclerView();
	}

	@Override
	protected void onResume() {
		super.onResume();
		chatPresenter.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		chatPresenter.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		chatPresenter.onDestroy();
	}

	@Override
	@OnClick(R.id.btnSendMessage)
	public void sendMessage() {
		chatPresenter.sendMessage(inputMessage.getText().toString());
		inputMessage.setText("");
	}

	@Override
	public void onMessageReceived(ChatMessage message) {
		adapter.add(message);
		recyclerView.scrollToPosition(adapter.getItemCount() - 1);
	}

	private void setToolbarData(Intent intent) {
		String recipient = intent.getStringExtra(EMAIL_KEY);
		chatPresenter.setChatRecipient(recipient);

		boolean online = intent.getBooleanExtra(ONLINE_KEY, false);
		String status = online ? "online" : "offline";
		int color = online ? Color.GREEN : Color.RED;

		txtUser.setText(recipient);
		txtStatus.setText(status);
		txtStatus.setTextColor(color);

//		PoChatAplication app = (PoChatAplication) getApplication();

	}

	private void setupAdapter() {
		adapter = new ChatAdapter(this, new ArrayList<ChatMessage>());
	}

	private void setupRecyclerView() {
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);
	}
}
