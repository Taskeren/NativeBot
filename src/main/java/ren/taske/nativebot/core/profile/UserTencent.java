package ren.taske.nativebot.core.profile;

import java.util.HashMap;

import ren.taske.data.SimpleDataStorage;
import ren.taske.nativebot.commons.Reference;

public class UserTencent extends User {

	protected static final HashMap<Long, UserTencent> PROFILES = new HashMap<>();
	
	protected final SimpleDataStorage data;
	
	private UserTencent(long userid) {
		super(Long.toString(userid));
		data = new SimpleDataStorage(Reference.getTencentProfile(userid));
	}
	
	public static UserTencent of(long userid) {
		if(!PROFILES.containsKey(userid)) {
			UserTencent user = new UserTencent(userid);
			PROFILES.put(userid, user);
		}
		return PROFILES.get(userid);
	}
	
	@Override
	public SimpleDataStorage getData() {
		return data;
	}
	
	@Override
	public void reload() {
		
	}
	
	@Override
	public void onUnload() {
		data.save();
	}
	
	public boolean hasPermission(String node) {
		if(node != null) {
			data.setDefault(node, false);
			data.save();
		}
		return data.getBoolean(node, false);
	}
	
	public void setPermission(String node, boolean val) {
		data.setBoolean(node, val);
		data.save();
	}
	
}
