package ren.taske.nativebot.util;

public class MessageLib {

	public static final String _NEWLINE = "\n";
	
	public static final String _UNAUTHORIZED = "You have no permission!";
	public static String getUnauthorizedMessage(Object user) {
		return MessageUtils.at(user)+_NEWLINE+_UNAUTHORIZED;
	}
	
}
