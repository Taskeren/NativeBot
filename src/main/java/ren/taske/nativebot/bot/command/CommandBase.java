package ren.taske.nativebot.bot.command;

import java.util.ArrayList;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;
import ren.taske.nativebot.commons.Reference;
import ren.taske.nativebot.core.profile.UserTencent;
import ren.taske.nativebot.util.MessageLib;

public abstract class CommandBase implements EverywhereCommand {

	final CommandProperties properties;
	
	final String node;
	
	public CommandBase(String name, String node, String...alias) {
		this.properties = new CommandProperties(name, alias);
		this.node = node;
	}
	
	@Override
	public CommandProperties properties() {
		return properties;
	}
	
	@Override
	public String run(EventMessage event, User sender, String command, ArrayList<String> args) {
		long userid = sender.getId();
		if(node == null || node.equals("") || UserTencent.of(userid).hasPermission(Reference.NODE_OP) || UserTencent.of(userid).hasPermission(node)) {
			return execute(event, sender, userid, command, args);
		} else {
			return MessageLib.getUnauthorizedMessage(sender)+"CB";
		}
	}
	
	public abstract String execute(EventMessage evt, User user, long userid, String command, ArrayList<String> args);
	
}
