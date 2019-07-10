package ren.taske.nativebot.bot.command;

import java.util.ArrayList;

import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;
import cn.glycol.t18n.I18n;
import ren.taske.nativebot.util.MessageUtils;

public class CommandAbout extends CommandBase {

	public CommandAbout() {
		super("about", null, "bot", "help");
	}
	
	@Override
	public String execute(EventMessage evt, User user, long userid, String command, ArrayList<String> args) {
		return MessageUtils.retAt(userid, I18n.formatList("command.about.message.%s"));
	}

}
