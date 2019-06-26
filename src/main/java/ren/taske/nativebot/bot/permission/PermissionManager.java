package ren.taske.nativebot.bot.permission;

import ren.taske.nativebot.bot.chatting.Chatting;
import ren.taske.nativebot.commons.Reference;

public class PermissionManager {

	/**
	 * 注册一个权限节点
	 * @return 是否成功
	 */
	public static boolean add(String node) {
		return add(node, false);
	}
	
	/**
	 * 注册一个权限节点
	 * @param def 默认值
	 * @return 是否成功
	 */
	public static boolean add(String node, boolean def) {
		return !(Permission.of(node, def) == null);
	}
	
	/**
	 * 检测权限节点是否存在
	 */
	public static boolean has(String node) {
		return !(Permission.of(node) == null);
	}
	
	// Add defaults
	public static void init() {
		
		add(Reference.NODE_OP);
		
		add(Chatting.NODE_CHATTING_TENCENT, true);
		add(Chatting.NODE_CHATTING_MINECRAFT, true);
		
	}
	
}
