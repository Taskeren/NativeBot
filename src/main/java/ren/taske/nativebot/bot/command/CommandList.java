package ren.taske.nativebot.bot.command;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.sender.message.MessageBuilder;
import cc.moecraft.icq.user.User;
import cn.glycol.t18n.I18n;

public class CommandList extends CommandBase {

	public CommandList() {
		super("list", null);
	}
	
	@Override
	public String execute(EventMessage evt, User user, long userid, String command, ArrayList<String> args) {
		Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();
		MessageBuilder mb = new MessageBuilder();
		mb.add(I18n.format("command.list.title")).newLine();
		for(Player player : players) {
			mb.add(I18n.format("command.list.prefix", player.getName())).newLine();
		}
		return mb.toString().trim();
	}
	
}
