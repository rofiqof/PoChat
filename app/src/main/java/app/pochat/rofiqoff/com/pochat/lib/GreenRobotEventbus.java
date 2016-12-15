package app.pochat.rofiqoff.com.pochat.lib;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by RofiqoFF on 12/12/2016.
 */

public class GreenRobotEventbus implements Eventbus {

	EventBus eventbus;

	private static class SingletoHolder {
		private static final GreenRobotEventbus INTANCE = new GreenRobotEventbus();
	}

	public static GreenRobotEventbus getInstance(){
		return SingletoHolder.INTANCE;
	}

	public GreenRobotEventbus(){
		eventbus = EventBus.getDefault();
	}

	@Override
	public void register(Object subscriber) {
		eventbus.register(subscriber);
	}

	@Override
	public void unregister(Object subscriber) {
		eventbus.unregister(subscriber);
	}

	@Override
	public void post(Object event) {
		eventbus.post(event);
	}
}
