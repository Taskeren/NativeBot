package ren.taske.nativebot.core.profile;

import java.util.HashMap;

import ren.taske.data.SimpleDataStorage;
import ren.taske.nativebot.commons.Reference;

public class UserMinecraft extends User {

	protected static final HashMap<String, UserMinecraft> PROFILES = new HashMap<>();
	
	protected final SimpleDataStorage data;
	
	private UserMinecraft(String userid) {
		super(userid);
		data = new SimpleDataStorage(Reference.getMinecraftProfile(userid));
	}
	
	public static UserMinecraft of(String userid) {
		if(!PROFILES.containsKey(userid)) {
			UserMinecraft user = new UserMinecraft(userid);
			PROFILES.put(userid, user);
		}
		return PROFILES.get(userid);	
	}
	
	@Override
	public SimpleDataStorage getData() {
		return data;
	}
	
	@Override
	public void onUnload() {
		data.save();
	}
	
	@Override
	public void reload() {
		
	}
	
}
