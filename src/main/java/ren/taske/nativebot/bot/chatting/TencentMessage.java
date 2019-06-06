package ren.taske.nativebot.bot.chatting;

import cc.moecraft.icq.event.EventHandler;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.icq.event.events.message.EventMessage;
import ren.taske.nativebot.MinecraftPlugin;

public class TencentMessage extends IcqListener {

	protected final MinecraftPlugin plugin;
	
	public TencentMessage(MinecraftPlugin plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void message(EventMessage evt) {
		plugin.onTencentMessage(evt);
	}
	
}
