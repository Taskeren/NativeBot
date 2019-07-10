package ren.taske.nativebot.bot.chatting;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import ren.taske.nativebot.NativeBotPlugin;

public class MinecraftMessage implements Listener {

	protected final NativeBotPlugin plugin;
	
	public MinecraftMessage(NativeBotPlugin plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void message(AsyncPlayerChatEvent evt) {
		plugin.onMinecraftMessage(evt);
	}
	
}
