package app.pochat.rofiqoff.com.pochat.chat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import app.pochat.rofiqoff.com.pochat.R;
import app.pochat.rofiqoff.com.pochat.chat.entities.ChatMessage;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
	private Context context;
	private List<ChatMessage> chatMessages;

	public ChatAdapter(Context context, List<ChatMessage> chatMessages) {
		this.context = context;
		this.chatMessages = chatMessages;
	}

	@Override
	public ChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_chat, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		ChatMessage chatMessage = chatMessages.get(position);

		String message = chatMessage.getMessage();
		holder.textMessage.setText(message);

//		int color = fetchColor(R.attr.colorPrimary);
		int gravity = Gravity.RIGHT;

		if (!chatMessage.isSentByMe()){
			gravity = Gravity.LEFT;
//			color = fetchColor(R.attr.colorPrimaryDark);
		}

//		holder.textMessage.setBackgroundColor();
		LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.textMessage.getLayoutParams();
		params.gravity = gravity;
		holder.textMessage.setLayoutParams(params);
	}

//	private int fetchColor(int color) {
//		TypedValue typedValue = new TypedValue();
//		TypedArray typedArray = context.obtainStyledAttributes(typedValue.data, new int[]{color});
//
//		int returnColor = typedArray.getColor(0, 0);
//		typedArray.recycle();
//		return returnColor;
//	}

	@Override
	public int getItemCount() {
		return chatMessages.size();
	}

	private boolean alreadyInAdapter(ChatMessage newMessage){
		boolean alreadyInAdapter = false;
		for (ChatMessage message : this.chatMessages){
			if (message.getMessage().equals(newMessage.getMessage()) && message.getSender().equals(newMessage.getSender())){
				alreadyInAdapter = true;
				break;
			}
		}
		return alreadyInAdapter;
	}

	public void add(ChatMessage message){
		if (!alreadyInAdapter(message)){
			this.chatMessages.add(message);
			this.notifyDataSetChanged();
		}
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		@BindView(R.id.txtMessage) TextView textMessage;

		public ViewHolder(View view) {
			super(view);
			ButterKnife.bind(this, view);
		}
	}
}
