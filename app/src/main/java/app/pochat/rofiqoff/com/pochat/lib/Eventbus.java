package app.pochat.rofiqoff.com.pochat.lib;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public interface Eventbus {
	void register(Object subscriber);
	void unregister(Object subscriber);
	void post(Object event);
}
