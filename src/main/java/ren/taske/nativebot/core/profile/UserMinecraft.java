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
		return getTencentUser().getData();
	}
	
	@Override
	public void onUnload() {
		getTencentUser().onUnload();
	}
	
	@Override
	public void reload() {
		getTencentUser().reload();
	}
	
	/** 获取玩家对应QQ的权限 */
	public boolean hasPermission(String node) {
		return getTencentUser().hasPermission(node);
	}
	
	/** 设置玩家对应QQ的权限 */
	public void setPermission(String node, boolean val) {
		getTencentUser().setPermission(node, val);
	}
	
	public static final String _TENCENT_UID = "tencent.uid";
	
	/** 设置该用户的QQ号 */
	public void setTencentId(long uid) {
		data.setLong(_TENCENT_UID, uid);
		data.save();
	}
	
	/** 获取该用户的QQ号 */
	public long getTencentId() {
		data.setDefault(_TENCENT_UID, -1L);
		data.save();
		return data.getLong(_TENCENT_UID, -1L);
	}
	
	/** 获取该用户的TencentUser */
	public UserTencent getTencentUser() {
		return UserTencent.of(getTencentId());
	}
	
}
