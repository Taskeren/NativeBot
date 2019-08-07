package ren.taske.nativebot.bot.chatting;

import org.bukkit.event.player.AsyncPlayerChatEvent;

import cc.moecraft.icq.event.events.message.EventGroupOrDiscussMessage;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;
import cc.moecraft.icq.utils.CQUtils;
import cn.glycol.t18n.I18n;
import ren.taske.nativebot.NativeBotPlugin;
import ren.taske.nativebot.commons.Config;
import ren.taske.nativebot.core.BotApi;
import ren.taske.nativebot.core.NativeBot;
import ren.taske.nativebot.core.profile.UserMinecraft;
import ren.taske.nativebot.core.profile.UserTencent;
import ren.taske.nativebot.util.MessageLib;

public class Chatting {

	protected final NativeBotPlugin plugin;
	protected final NativeBot nativebot;
	
	public Chatting(NativeBotPlugin plugin) {
		this.plugin = plugin;
		this.nativebot = plugin.getBot();
	}
	
	public static final String NODE_CHATTING_TENCENT = "chatting.tencent";
	public static final String NODE_CHATTING_MINECRAFT = "chatting.minecraft";
	
	public static final String FORMAT_CODE = "\u00a7";
	
	/**
	 * To handle messages from Tencent
	 * $param evt PicqBotX message event
	 */
	public void onTencentMessage(EventMessage evt) {
		User user = evt.getSender();
		long userid = user.getId();
		UserTencent ut = UserTencent.of(userid);
		String message = evt.getMessage();
		String username;
		
		if(evt instanceof EventGroupOrDiscussMessage) {
			username = ((EventGroupOrDiscussMessage) evt).getGroupSender().getInfo().getCard();
		} else {
			username = user.getInfo().getNickname();
		}
		
		Message msg = new Message(message);
		
		// 检查消息前缀
		if(!msg.available()) return;
		
		// 检查QQ号权限
		if(!ut.hasPermission(NODE_CHATTING_TENCENT)) {
			evt.respond(MessageLib.getUnauthorizedMessage(user));
			return;
		}
		
		plugin.getServer().broadcastMessage(replaces(Config.chattingFormatMinecraft, username, message));
	}
	
	/**
	 * To handle messages from Minecraft
	 * $param evt Minecraft chatting event
	 */
	public void onMinecraftMessage(AsyncPlayerChatEvent evt) {
		
		String message = evt.getMessage();
		String username = evt.getPlayer().getName();
		UserMinecraft um = UserMinecraft.of(username);
		
		Message msg = new Message(message);
		
		// 检查消息前缀
		if(!msg.available()) return;
		
		// 检查QQ号绑定
		if(um.getTencentId() == -1L) {
			evt.getPlayer().sendMessage(I18n.format("message.using.set-qq-first"));
			return;
		}
		
		// 检查QQ号权限
		UserTencent ut = UserTencent.of(um.getTencentId());
		if(!ut.hasPermission(NODE_CHATTING_MINECRAFT)) {
			evt.getPlayer().sendMessage(I18n.format("message.unauthorized"));
			return;
		}
		
		evt.setMessage(msg.content());
		BotApi.sendGroupMessage(Config.group_id, replaces(Config.chattingFormatTencent, username, message));
	}
	
	public static String replaces(String format, String username, String message) {
		return format.replace("$PLAYER$", username).replace("$MESSAGE$", message).replace("&", FORMAT_CODE);
	}
	
	public static class Message {
		
		protected String message;
		protected boolean available;
		
		public Message(String message) {
			this.message = message;
			check();
		}
		
		/**
		 * 检查，并移除前缀
		 */
		void check() {
			if(Config.require_prefix) {
				String prefix = getPrefix();
				if(prefix != null) {
					available = true;
					message.substring(prefix.length());
				}
			}
			else if(message.startsWith("/")) {
				return;
			}
			else {
				available = true;
			}
		}
		
		public boolean available() {
			return available;
		}
		
		public String content() {
			return CQUtils.removeCqCode(message);
		}
		
		private String getPrefix() {
			for(String p : Config.chatting_prefixes) {
				if(message.startsWith(p)) {
					return p;
				}
			}
			return null;
		}
		
	}
	
}
