package ren.taske.nativebot.bot.chatting;

import org.bukkit.event.player.AsyncPlayerChatEvent;

import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;
import cc.moecraft.icq.utils.CQUtils;
import ren.taske.nativebot.MinecraftPlugin;
import ren.taske.nativebot.commons.Config;
import ren.taske.nativebot.core.NativeBot;
import ren.taske.nativebot.core.profile.UserMinecraft;
import ren.taske.nativebot.core.profile.UserTencent;
import ren.taske.nativebot.util.MessageLib;

public class Chatting {

	protected final MinecraftPlugin plugin;
	protected final NativeBot nativebot;
	
	public Chatting(MinecraftPlugin plugin) {
		this.plugin = plugin;
		this.nativebot = plugin.getBot();
	}
	
	public static final String NODE_CHATTING_TENCENT = "chatting.tencent";
	public static final String NODE_CHATTING_MINECRAFT = "chatting.minecraft";
	
	/**
	 * To handle messages from Tencent
	 * @param evt PicqBotX message event
	 */
	public void onTencentMessage(EventMessage evt) {
		User user = evt.getSender();
		long userid = user.getId();
		UserTencent ut = UserTencent.of(userid);
		String message = evt.getMessage();
		String username = user.getInfo().getNickname();
		
		if(!isPrefixed(message)) {
			return;
		}
		
		if(!ut.hasPermission(NODE_CHATTING_TENCENT, true)) {
			evt.respond(MessageLib.getUnauthorizedMessage(user));
			return;
		}
		
		if(isPrefixed(message)) {
			
			message = removePrefix(message);
			message = CQUtils.removeCqCode(message);
			
			StringBuffer sb = new StringBuffer();
			sb.append("<").append(username).append("> ").append("\u00a7r");
			sb.append(message);
			
			plugin.getServer().broadcastMessage(sb.toString());
			
		}
		
	}
	
	/**
	 * To handle messages from Minecraft
	 * @param evt Minecraft chatting event
	 */
	public void onMinecraftMessage(AsyncPlayerChatEvent evt) {
		
		String message = evt.getMessage();
		String username = evt.getPlayer().getName();
		UserMinecraft um = UserMinecraft.of(username);
		
		if(!isPrefixed(message)) {
			return;
		}
		
		if(!um.hasPermission(NODE_CHATTING_MINECRAFT, true)) {
			evt.getPlayer().sendMessage("You're Unauthorized!");
			return;
		}
		
		if(isPrefixed(message)) {
			
			message = removePrefix(message);
			
			evt.setMessage(message);
			
			StringBuffer sb = new StringBuffer();
			sb.append("\uff3b").append(username).append("\uff3d ");
			sb.append(message);
			
			plugin.getBot().getBot().sendGroupMessage(Config.group_id, sb.toString());
			
		}
		
	}
	
	public static boolean isPrefixed(String message) {
		for(String prefix : Config.chatting_prefixes) {
			if(message.startsWith(prefix)) return true;
		}
		return false;
	}
	
	public static String removePrefix(String message) {
		return message.length() < 1 ? "" : message.substring(1);
	}
	
}
