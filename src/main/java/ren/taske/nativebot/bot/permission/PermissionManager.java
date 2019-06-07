package ren.taske.nativebot.bot.permission;

import ren.taske.nativebot.bot.chatting.Chatting;
import ren.taske.nativebot.commons.Reference;

public class PermissionManager {

	public static boolean add(String node) {
		return add(node, false);
	}
	
	public static boolean add(String node, boolean def) {
		return !(Permission.of(node, def) == null);
	}
	
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
