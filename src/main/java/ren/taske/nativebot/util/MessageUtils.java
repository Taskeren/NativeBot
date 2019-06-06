package ren.taske.nativebot.util;

import cc.moecraft.icq.sender.message.MessageBuilder;
import cc.moecraft.icq.sender.message.components.ComponentAt;
import cc.moecraft.icq.user.User;

public class MessageUtils {

	public static String at(Object object, String defaultVal) {
		if(object instanceof Long) return at((Long) object);
		if(object instanceof User) return at((User) object);
		return defaultVal;
	}
	
	public static String at(Object object) {
		return at(object, null);
	}
	
	public static String at(long userid) {
		return new ComponentAt(userid).toString();
	}
	
	public static String at(User user) {
		return at(user.getId());
	}
	
	public static String retAt(long userid, String...lines) {
		MessageBuilder mb = new MessageBuilder();
		mb.add(at(userid)).newLine();
		for(String line : lines) mb.add(line).newLine();
		return mb.toString().trim();
	}
	
}
