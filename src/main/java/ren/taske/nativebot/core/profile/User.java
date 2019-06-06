package ren.taske.nativebot.core.profile;

import ren.taske.data.SimpleDataStorage;

public abstract class User {

	protected final String userid;
	
	public User(String userid) {
		this.userid = userid;
	}
	
	public String getUserId() {
		return this.userid;
	}
	
	public abstract SimpleDataStorage getData();
	public abstract void reload();
	public abstract void onUnload();
	
}
