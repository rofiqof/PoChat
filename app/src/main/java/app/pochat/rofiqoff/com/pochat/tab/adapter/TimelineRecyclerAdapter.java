package app.pochat.rofiqoff.com.pochat.tab.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.pochat.rofiqoff.com.pochat.R;
import app.pochat.rofiqoff.com.pochat.tab.datatimeline.TimelineList;

/**
 * Created by RofiqoFF on 13/12/2016.
 */
//TimelineRecyclerAdapter

public class TimelineRecyclerAdapter extends RecyclerView.Adapter<TimelineRecyclerAdapter.ViewHolder> {
	private Activity activity;
	private List<TimelineList> timelineLists;

	public TimelineRecyclerAdapter(Activity activity, List<TimelineList> timelineLists){
		this.activity = activity;
		this.timelineLists = timelineLists;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
		LayoutInflater inflater = activity.getLayoutInflater();
		View view = inflater.inflate(R.layout.item_recyler_timeline, viewGroup, false);
		ViewHolder viewHolder = new ViewHolder(view);

		return viewHolder;
	}

	@Override
	public void onBindViewHolder(TimelineRecyclerAdapter.ViewHolder viewHolder, int position) {
		viewHolder.textUser.setText(timelineLists.get(position).getNamaUser());
		viewHolder.imageUser.setImageResource(R.drawable.gambar);
		viewHolder.imagePoster.setImageResource(R.drawable.poseter);
		viewHolder.textDeskripsi.setText(timelineLists.get(position).getDeskripsi());

//        viewHolder.container.setOnClickListener(klikItem());
	}

	@Override
	public int getItemCount() {
		return (null != timelineLists ? timelineLists.size() : 0);
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		private ImageView imageUser;
		private TextView textUser;
		private ImageView imagePoster;
		private TextView textDeskripsi;
		private View container;

		public ViewHolder(View view) {
			super(view);

			imageUser = (ImageView) view.findViewById(R.id.image_user);
			textUser = (TextView) view.findViewById(R.id.text_namaUser);
			imagePoster = (ImageView) view.findViewById(R.id.img_poster);
			textDeskripsi = (TextView) view.findViewById(R.id.textt_deskripsi);
			container = view.findViewById(R.id.cardView_timeline);
		}
	}
}
