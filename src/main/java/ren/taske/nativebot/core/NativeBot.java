package ren.taske.nativebot.core;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import ren.taske.nativebot.bot.Bot;
import ren.taske.nativebot.bot.event.EventBotRegistration;
import ren.taske.nativebot.util.ClassUtils;

public class NativeBot {

	protected static Logger logger;
	
	private static final String[] COMMANDS = new String[] {
			"CommandAbout",
			"CommandOperator",
			"CommandPermission",
			"CommandConsole"
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
		bot.register(ClassUtils.instantiate(COMMANDS));
		handleRegistrationEvent();
		bot.start();
		for(String cmd : bot.getCommands()) logger.info("[C] "+cmd);
	}
	
	/**
	 * 发布一个 EventBotRegistration 事件
	 */
	void handleRegistrationEvent() {
		EventBotRegistration.newEventAndCall(getBot());
	}
	
	@SuppressWarnings("deprecation")
	public void onDisable() {
		try {
			bot.stop();
		} catch(Exception e) {
			logger.warning(e.getMessage());
		}
	}
	
	public static void bigWarning(String str, Object...format) {
		logger.warning("***********************************************");
		logger.warning(String.format(str, format));
		logger.warning("***********************************************");
	}
	
	public static void bigWarning(String[] strs, Object...format) {
		String s = "";
		for(String str : strs) {
			s += str + "\n";
		}
		bigWarning(s, format);
	}
	
}
