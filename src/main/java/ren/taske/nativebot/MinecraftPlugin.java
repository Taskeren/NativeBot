package ren.taske.nativebot;

import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import cc.moecraft.icq.event.events.message.EventMessage;
import ren.taske.nativebot.bot.chatting.Chatting;
import ren.taske.nativebot.bot.chatting.MinecraftMessage;
import ren.taske.nativebot.bot.chatting.TencentMessage;
import ren.taske.nativebot.core.NativeBot;
import ren.taske.nativebot.minecraft.command.CommandQQ;

public class MinecraftPlugin extends JavaPlugin {

	protected final NativeBot nativebot = new NativeBot(this);
	protected final Chatting chatting = new Chatting(this);
	
	protected final MinecraftMessage mcevent = new MinecraftMessage(this);
	protected final TencentMessage tencentevent = new TencentMessage(this);
	
	protected final CommandQQ cmdqq = new CommandQQ(this);
	
	@Override
	public void onEnable() {
		nativebotJavaPlugin = this;
		
		nativebot.onEnable();
		
		getCommand("qq").setExecutor(cmdqq);
		
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
	
}
