package ren.taske.nativebot.bot;

import java.util.Collection;
import java.util.List;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.PicqConfig;
import cc.moecraft.icq.command.CommandManager;
import cc.moecraft.icq.command.interfaces.IcqCommand;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.icq.sender.IcqHttpApi;
import cc.moecraft.logger.environments.ColorSupportLevel;
import ren.taske.nativebot.commons.Config;

public class Bot extends Thread {

	protected final PicqBotX bot;
	
	public Bot() {
		this(Config.port_in, Config.port_out, Config.url_out, Config.prefixes);
	}
	
	/**
	 * new bot!
	 * @param portIn   the port to receive messages from HttpApi
	 * @param portOut  the port to send messages to HttpApi
	 * @param urlOut   the url to send messages to HttpApi
	 * @param prefixes the prefixes of commands in Tencent
	 */
	public Bot(int portIn, int portOut, String urlOut, String...prefixes) {
		PicqConfig botconfig = new PicqConfig(portIn).setColorSupportLevel(ColorSupportLevel.DISABLED);
		bot = new PicqBotX(botconfig);
		bot.addAccount("NightBot", urlOut, portOut);
		bot.enableCommandManager(prefixes);
	}
	
	/**
	 * Add commands for bot
	 * @param cmds the commands
	 */
	public void register(IcqCommand...cmds) {
		bot.getCommandManager().registerCommands(cmds);
	}
	
	/**
	 * Add commands for bot
	 * @param cmds the commands
	 */
	public void register(Collection<IcqCommand> cmds) {
		register(cmds.toArray(new IcqCommand[0]));
	}
	
	
	/**
	 * Add event listener for bot
	 * @param listeners listeners
	 */
	public void register(IcqListener...listeners) {
		bot.getEventManager().registerListeners(listeners);
	}
	
	/**
	 * To start the bot. <br>
	 * But wait! Did you {@code register} the commands?
	 */
	public void run() {
		bot.startBot();
	}
	
	public CommandManager getCommandManager() {
		return bot.getCommandManager();
	}
	
	public List<String> getCommands(){
		return getCommandManager().getCommandNameList();
	}
	
	public IcqHttpApi getApi() {
		return bot.getAccountManager().getNonAccountSpecifiedApi();
	}
	
	public void sendGroupMessage(long gid, String msg) {
		getApi().sendGroupMsg(gid, msg);
	}
	
	public void sendPrivateMessage(long uid, String msg) {
		getApi().sendPrivateMsg(uid, msg);
	}
	
}
