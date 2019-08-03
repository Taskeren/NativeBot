package ren.taske.nativebot.bot.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.event.server.ServerLoadEvent.LoadType;

import ren.taske.nativebot.NativeBotPlugin;

public class ServerLoadEventListener implements Listener {
	
	@EventHandler
	public void onServerLoad(ServerLoadEvent evt) {
		
		LoadType type = evt.getType();
		
		if(type == LoadType.STARTUP) {
			NativeBotPlugin.getPlugin().getBot().start();
		}
		
		if(type == LoadType.RELOAD) {
			// server has already started. look up!
		}
		
	}
	
}
