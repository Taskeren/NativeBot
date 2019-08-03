package ren.taske.nativebot.bot.command;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;
import cn.glycol.t18n.I18n;
import ren.taske.nativebot.NativeBotPlugin;
import ren.taske.nativebot.commons.Reference;

public class CommandConsole extends CommandBase {

	public CommandConsole() {
		super("console", Reference.NODE_OP, "$");
	}
	
	@Override
	public String execute(EventMessage evt, User user, long userid, String command, ArrayList<String> args) {
		String message = "";
		String cmd = merge(args);
		Boolean flag = callSyncDispatch(Bukkit.getServer().getConsoleSender(), cmd);
		if(flag == null) {
			return I18n.format("command.common.exception");
		}
		if(flag) {
			message = I18n.format("command.common.done");
		} else {
			message = I18n.format("command.common.fail");
		}
		return message;
	}
	
	public static String merge(ArrayList<String> strs) {
		String ret = "";
		for(String str : strs) {
			ret += str + " ";
		}
		return ret.trim();
	}
	
	public static Boolean callSyncDispatch(CommandSender sender, String command) {
		try {
			return Bukkit.getScheduler().callSyncMethod(NativeBotPlugin.getPlugin(), new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					NativeBotPlugin.logger().log("[$] %s", command);
					return Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), command);
				}
			}).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
