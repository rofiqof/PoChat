package app.pochat.rofiqoff.com.pochat.akun.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import app.pochat.rofiqoff.com.pochat.R;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public class ImageGridAdapter extends BaseAdapter{
	private Context context;

	public Integer[] mThumb = {
			R.drawable.ic_add_a_photo_white_48px
	};

	public ImageGridAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		return mThumb.length;
	}

	@Override
	public Object getItem(int position) {
		return mThumb[position];
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView = new ImageView(context);
		imageView.setImageResource(mThumb[position]);
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setLayoutParams(new GridView.LayoutParams(70, 70));
		return imageView;
	}
}
