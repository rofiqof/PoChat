package app.pochat.rofiqoff.com.pochat.contactlist.ui;

import app.pochat.rofiqoff.com.pochat.contactlist.entities.User;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public interface OnItemClickListener {
	void onItemClick(User user);
	void onItemLongClick(User user);
}
