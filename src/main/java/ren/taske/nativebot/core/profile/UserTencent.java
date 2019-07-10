package ren.taske.nativebot.core.profile;

import java.util.HashMap;

import ren.taske.data.SimpleDataStorage;
import ren.taske.nativebot.bot.permission.Permission;
import ren.taske.nativebot.bot.permission.PermissionManager;
import ren.taske.nativebot.commons.Reference;

public class UserTencent extends User {

	protected static final HashMap<Long, UserTencent> PROFILES = new HashMap<>();
	
	protected final SimpleDataStorage data;
	
	/** 错误的用户，用于给 UserMinecraft 提供未绑定的用户 */
	public static final UserTencent NONE = new UserTencent(-1L) {
		@Override
		public boolean hasPermission(String node) {
			return false;
		}
	};
	
	private UserTencent(long userid) {
		super(Long.toString(userid));
		data = new SimpleDataStorage(Reference.getTencentProfile(userid));
	}
	
	static {
		PROFILES.put(-1L, NONE);
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
		Permission perm = Permission.of(node);
		if(perm != null) {
			data.setDefault(node, perm.getDefault());
			data.save();
			return data.getBoolean(node, false);
		}
		return false;
	}
	
	public void setPermission(String node, boolean val) {
		if(PermissionManager.has(node)) {
			data.setBoolean(node, val);
			data.save();
		}
	}
}
