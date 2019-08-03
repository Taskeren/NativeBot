package ren.taske.nativebot;

import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.logger.HyLogger;
import cc.moecraft.logger.LoggerInstanceManager;
import cn.glycol.t18n.I18n;
import ren.taske.nativebot.bot.chatting.Chatting;
import ren.taske.nativebot.bot.chatting.MinecraftMessage;
import ren.taske.nativebot.bot.chatting.TencentMessage;
import ren.taske.nativebot.bot.listener.ServerLoadEventListener;
import ren.taske.nativebot.bot.permission.PermissionManager;
import ren.taske.nativebot.core.NativeBot;
import ren.taske.nativebot.i18n.I18nInit;
import ren.taske.nativebot.minecraft.command.CommandQQ;
import ren.taske.nativebot.minecraft.command.CommandQQAdmin;

public class NativeBotPlugin extends JavaPlugin {
	
	protected final NativeBot nativebot = new NativeBot(this);
	protected final Chatting chatting = new Chatting(this);
	
	protected final MinecraftMessage mcevent = new MinecraftMessage(this);
	protected final TencentMessage tencentevent = new TencentMessage(this);
	
	protected final ServerLoadEventListener botstartevent = new ServerLoadEventListener();
	
	protected final CommandQQ cmdqq = new CommandQQ(this);
	protected final CommandQQAdmin cmdqqadmin = new CommandQQAdmin();
	
	protected final HyLogger logger = new LoggerInstanceManager().getLoggerInstance("NaB", false);
	
	@Override
	public void onEnable() {
		nativebotJavaPlugin = this;
		
		I18nInit.init();
		PermissionManager.init();
		
		getLogger().info(I18n.format("message.common.welcome"));
		
		nativebot.onEnable();
		
		// 绑定QQ号指令
		getCommand("qq").setExecutor(cmdqq);
		getCommand("qq-admin").setExecutor(cmdqqadmin);
		
		// 注册消息事件监听器
		nativebot.getBot().addEventListenr(tencentevent);
		getServer().getPluginManager().registerEvents(mcevent, this);
		
		// 注册服务器载入事件监听器
		getServer().getPluginManager().registerEvents(botstartevent, this);
		
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		nativebot.onDisable();
	}
	
	public NativeBot getBot() {
		return this.nativebot;
	}
	
	public void onTencentMessage(EventMessage evt) {
		chatting.onTencentMessage(evt);
	}
	
	public void onMinecraftMessage(AsyncPlayerChatEvent evt) {
		chatting.onMinecraftMessage(evt);
	}
	
	private static NativeBotPlugin nativebotJavaPlugin;
	
	public static NativeBotPlugin getPlugin() {
		return nativebotJavaPlugin;
	}
	
	public static HyLogger logger() {
		return nativebotJavaPlugin.logger;
	}
	
}
