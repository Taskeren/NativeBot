package ren.taske.nativebot.bot.command;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;
import ren.taske.nativebot.MinecraftPlugin;
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
			return "Exception!";
		}
		if(flag) {
			message = "Done!";
		} else {
			message = "Fail!";
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
			return Bukkit.getScheduler().callSyncMethod(MinecraftPlugin.nativebotJavaPlugin, new Callable<Boolean>() {
				@Override
				public Boolean call() throws Exception {
					return Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), command);
				}
			}).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
