package ren.taske.nativebot.core;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import cc.moecraft.icq.command.interfaces.IcqCommand;
import cn.glycol.extrabot.ExtraBot;
import cn.glycol.extrabot.bot.MixinBot;
import cn.glycol.extrabot.bot.MixinBot.MixinBotConfiguration;
import cn.glycol.extrabot.registration.AutoRegister;
import cn.glycol.extrabot.registration.AutoRegister.Type;
import ren.taske.nativebot.bot.command.CommandAbout;
import ren.taske.nativebot.bot.command.CommandList;
import ren.taske.nativebot.bot.command.CommandOperator;
import ren.taske.nativebot.bot.command.CommandPermission;
import ren.taske.nativebot.bot.event.EventBotRegistration;
import ren.taske.nativebot.commons.Config;

public class NativeBot {

	protected static Logger logger;
	
	@AutoRegister(Type.COMMAND)
	public static final IcqCommand CMD_ABOUT = new CommandAbout();
	
	@AutoRegister(Type.COMMAND)
	public static final IcqCommand CMD_OPERATOR = new CommandOperator();
	
	@AutoRegister(Type.COMMAND)
	public static final IcqCommand CMD_PERMISSION = new CommandPermission();
	
	@AutoRegister(Type.COMMAND)
	public static final IcqCommand CMD_LIST = new CommandList();
	
	protected final JavaPlugin plugin;
	protected final MixinBot bot;
	
	public NativeBot(JavaPlugin plugin) {
		this.plugin = plugin;
		
		bot = new MixinBot(new MixinBotConfiguration(Config.port_in));
		bot.addAccount("Tbot", Config.url_out, Config.port_out);
		bot.enableCommandManager(Config.prefixes);
		
		BotApi.setApi(bot.getAccountManager().getNonAccountSpecifiedApi());
		
		logger = plugin.getLogger();
	}
	
	public static Logger logger() {
		return logger;
	}
	
	public MixinBot getBot() {
		return this.bot;
	}
	
	public void onEnable() {
		ExtraBot.register(NativeBot.class, bot);
		handleRegistrationEvent();
		for(IcqCommand cmd : bot.getCommandManager().getCommandList()) logger.info("[C] "+cmd.properties().getName());
	}
	
	public void onDisable() {
//		bot.stopBot();
	}
	
	/**
	 * 发布一个 EventBotRegistration 事件
	 */
	void handleRegistrationEvent() {
		EventBotRegistration.newEventAndCall(getBot());
	}
	
	/** 启动机器人 */
	public void start() {
		bot.startBot();
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
