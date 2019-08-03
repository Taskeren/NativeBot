package ren.taske.nativebot.bot.event;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import cc.moecraft.icq.command.interfaces.IcqCommand;
import cc.moecraft.icq.event.IcqListener;
import cn.glycol.extrabot.bot.MixinBot;
import ren.taske.nativebot.bot.permission.PermissionManager;

/**
 * 这个事件用于注册于机器人相关的内容。
 * @author Taskeren
 *
 */
public class EventBotRegistration extends Event {

	private static final HandlerList handlers = new HandlerList();
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	protected final MixinBot bot;
	
	public EventBotRegistration(MixinBot bot) {
		this.bot = bot;
	}
	
	/** 获取机器人 */
	public MixinBot getBot() {
		return bot;
	}
	
	/** 注册指令 */
	public void registerCommands(IcqCommand command) {
		bot.addCommand(command);
	}
	
	/** 注册事件监听器 */
	public void registerListeners(IcqListener listener) {
		bot.addEventListenr(listener);
	}
	
	/** 注册权限 */
	public void registerPermission(String node) {
		registerPermission(node, false);
	}
	
	/** 注册权限 */
	public void registerPermission(String node, boolean def) {
		PermissionManager.add(node, def);
	}
	
	/**
	 * 新建一个 EventBotRegistration 并发布它<br>
	 * 千万不要瞎jb调用它！
	 * @return 返回结果
	 */
	public static EventBotRegistration newEventAndCall(MixinBot bot) {
		EventBotRegistration evt = new EventBotRegistration(bot);
		Bukkit.getServer().getPluginManager().callEvent(evt);
		return evt;
	}
	
}
