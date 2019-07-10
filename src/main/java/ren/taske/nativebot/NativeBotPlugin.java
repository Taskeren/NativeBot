package ren.taske.nativebot;

import java.util.logging.Logger;

import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import cc.moecraft.icq.event.events.message.EventMessage;
import cn.glycol.t18n.I18n;
import ren.taske.nativebot.bot.chatting.Chatting;
import ren.taske.nativebot.bot.chatting.MinecraftMessage;
import ren.taske.nativebot.bot.chatting.TencentMessage;
import ren.taske.nativebot.core.NativeBot;
import ren.taske.nativebot.i18n.I18nInit;
import ren.taske.nativebot.minecraft.command.CommandQQ;
import ren.taske.nativebot.minecraft.command.CommandQQAdmin;

public class NativeBotPlugin extends JavaPlugin {
	
	protected final NativeBot nativebot = new NativeBot(this);
	protected final Chatting chatting = new Chatting(this);
	
	protected final MinecraftMessage mcevent = new MinecraftMessage(this);
	protected final TencentMessage tencentevent = new TencentMessage(this);
	
	protected final CommandQQ cmdqq = new CommandQQ(this);
	protected final CommandQQAdmin cmdqqadmin = new CommandQQAdmin();
	
	@Override
	public void onEnable() {
		nativebotJavaPlugin = this;
		
		I18nInit.init();
		
		getLogger().info(I18n.format("message.common.welcome"));
		
		nativebot.onEnable();
		
		getCommand("qq").setExecutor(cmdqq);
		getCommand("qq-admin").setExecutor(cmdqqadmin);
		
		nativebot.getBot().register(tencentevent);
		getServer().getPluginManager().registerEvents(mcevent, this);
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		nativebot.onDisable();
		super.onDisable();
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
	
	public static JavaPlugin nativebotJavaPlugin;
	
	public static Logger logger() {
		return nativebotJavaPlugin.getLogger();
	}
	
}
