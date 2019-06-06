package ren.taske.nativebot.core;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import cc.moecraft.icq.command.interfaces.IcqCommand;
import ren.taske.nativebot.bot.Bot;
import ren.taske.nativebot.bot.command.CommandAbout;
import ren.taske.nativebot.bot.command.CommandOperator;
import ren.taske.nativebot.bot.command.CommandPermission;
import ren.taske.nativebot.util.ClassUtils;

public class NativeBot {

	protected static Logger logger;
	
	@SuppressWarnings("unchecked")
	private static final Class<IcqCommand>[] COMMAND_CLASSES = new Class[] {
		CommandAbout.class,
		CommandOperator.class,
		CommandPermission.class
	};
	
	protected final JavaPlugin plugin;
	protected final Bot bot;
	
	public NativeBot(JavaPlugin plugin) {
		this.plugin = plugin;
		this.bot = new Bot();
		
		logger = plugin.getLogger();
		
	}
	
	public static Logger logger() {
		return logger;
	}
	
	public Bot getBot() {
		return this.bot;
	}
	
	public void onEnable() {
		bot.register(ClassUtils.instantiate(COMMAND_CLASSES));
		bot.start();
		for(String cmd : bot.getCommands()) logger.info("[C] "+cmd);
	}
	
	public void onDisable() {
		
	}
	
}
