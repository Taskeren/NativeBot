package ren.taske.nativebot.util;

import cn.glycol.t18n.I18n;

public class MessageLib {

	public static final String _NEWLINE = "\n";
	
	/** 自动合成一个无权限回复 */
	public static String getUnauthorizedMessage(Object user) {
		return MessageUtils.at(user)+_NEWLINE+I18n.format("command.common.unauthorized");
	}
	
}
