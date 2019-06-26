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
		return MessageUtils.retAt(userid, I18n.format("command.about.message").replace("$", "\n"));
	}
	
/*

Yes, sir!
(command with * requires OP_PERMISSION_NODE)
/about[/bot|/help] - show this notice
/op - query if you're operator
/op* [uid] - set user as operator
/perm* [uid] [node] - get the value of the node
/perm* [uid] [node] [true/false] - set the value of the node
/console* [cmd] - to execute command as Console

*/

}
