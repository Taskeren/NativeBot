package ren.taske.nativebot.bot.command;

import java.util.ArrayList;

import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;
import cn.glycol.t18n.I18n;
import ren.taske.data.util.ParseUtil;
import ren.taske.nativebot.bot.permission.PermissionManager;
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
			message = I18n.format("command.common.argument");
		}
		if(args.size() >= 2) {
			String username = args.get(0);
			String nodename = args.get(1);
			
			Long uid;
			
			if(username.equals("*")) {
				uid = userid;
			} else {
				uid = ParseUtil.parseLong(username);
			}
			
			if(uid != null) {
				
				UserTencent u = UserTencent.of(uid);
				boolean exists = PermissionManager.has(nodename);
				
				if(exists) {
					if(args.size() == 2) {
						message = I18n.format("command.permission.query", u.getUserId(), nodename, u.hasPermission(nodename));
					}
					
					if(args.size() > 2) {
						boolean value = ParseUtil.parseBoolean(args.get(2));
						u.setPermission(nodename, value);
						message = I18n.format("command.permission.set", u.getUserId(), nodename, value);
					}
				} else {
					message = I18n.format("command.permission.unregistered");
				}
				
			} else {
				message = I18n.format("command.common.exception.math");
			}
			
		}
		return MessageUtils.retAt(userid, message);
	}
	
}
