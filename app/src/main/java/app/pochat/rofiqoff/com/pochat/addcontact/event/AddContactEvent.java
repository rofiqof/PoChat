package app.pochat.rofiqoff.com.pochat.addcontact.event;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public class AddContactEvent {
	boolean error = false;

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}
}
