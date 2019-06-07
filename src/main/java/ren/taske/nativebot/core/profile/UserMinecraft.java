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
		data.save();
	}
	
	public boolean hasPermission(String node) {
		return hasPermission(node, false);
	}
	
	public boolean hasPermission(String node, boolean defaultVal) {
		if(node != null) {
			data.setDefault(node, defaultVal);
			data.save();
		}
		return data.getBoolean(node, false);
	}
	
	public void setPermission(String node, boolean val) {
		data.setBoolean(node, val);
		data.save();
	}
	
	public static final String _TENCENT_UID = "tencent.uid";
	
	public void setTencentId(long uid) {
		data.setLong(_TENCENT_UID, uid);
		data.save();
	}
	
	public long getTencentId() {
		data.setDefault(_TENCENT_UID, -1L);
		data.save();
		return data.getLong(_TENCENT_UID, -1L);
	}
	
}
