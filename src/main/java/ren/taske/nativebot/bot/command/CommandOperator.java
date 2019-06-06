package ren.taske.nativebot.bot.command;

import java.util.ArrayList;

import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;
import ren.taske.data.util.ParseUtil;
import ren.taske.nativebot.core.profile.UserTencent;
import ren.taske.nativebot.util.MessageLib;
import ren.taske.nativebot.util.MessageUtils;

public class CommandOperator extends CommandBase {

	public CommandOperator() {
		super("op", null, "operator");
	}
	
	public static final String OP_PERM_NODE = "op";
	
	@Override
	public String execute(EventMessage evt, User user, long userid, String command, ArrayList<String> args) {
		UserTencent u = UserTencent.of(userid);
		String message = "";
		
		/* Mode: check */
		if(args.size() == 0) {
			boolean perm = u.hasPermission(OP_PERM_NODE);
			message = "You're "+(perm?"an operator!":"not an operator");
		}
		
		/* Mode: set */
		if(args.size() == 1) {
			if(u.hasPermission(OP_PERM_NODE)) {
				// Authorized
				Long uid = ParseUtil.parseLong(args.get(0));
				if(uid != null) {
					UserTencent u2 = UserTencent.of(uid);
					// Check if has OP_PERM_NODE
					if(u2.hasPermission(OP_PERM_NODE)) {
						u2.setPermission(OP_PERM_NODE, false);
						message = "["+uid+"] now is NOT operator!";
					} else {
						u2.setPermission(OP_PERM_NODE, true);
						message = "["+uid+"] now is operator!";
					}
				} else {
					message = "NumberFormatException!";
				}
			} else {
				// Unauthorized
				return MessageLib.getUnauthorizedMessage(userid);
			}
		}
		
		return MessageUtils.retAt(userid, message);
	}
	
}
