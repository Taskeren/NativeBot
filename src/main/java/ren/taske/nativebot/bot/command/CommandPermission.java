package ren.taske.nativebot.bot.command;

import java.util.ArrayList;

import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;
import ren.taske.data.util.ParseUtil;
import ren.taske.nativebot.commons.Reference;
import ren.taske.nativebot.core.profile.UserTencent;
import ren.taske.nativebot.util.MessageUtils;

public class CommandPermission extends CommandBase {

	public CommandPermission() {
		super("perm", Reference.NODE_OP, "permission");
	}
	
	@Override
	public String execute(EventMessage evt, User user, long userid, String command, ArrayList<String> args) {
		String message = "";
		if(args.size() < 2) {
			message = "Wrong Arguments!";
		}
		if(args.size() >= 2) {
			String username = args.get(0);
			String nodename = args.get(1);
			
			Long uid = ParseUtil.parseLong(username);
			
			if(uid != null) {
				
				UserTencent u = UserTencent.of(userid);
				
				if(args.size() == 2) {
					message = nodename + " = " + u.hasPermission(nodename);
				}
				
				if(args.size() > 2) {
					boolean value = ParseUtil.parseBoolean(args.get(2));
					u.setPermission(nodename, value);
					
					StringBuffer sb = new StringBuffer();
					sb.append(nodename).append("(").append(uid).append(")");
					sb.append(" set to ").append(value);
					
					message = sb.toString();
				}
				
			} else {
				message = "NumberFormatException!";
			}
			
		}
		return MessageUtils.retAt(userid, message);
	}
	
}
