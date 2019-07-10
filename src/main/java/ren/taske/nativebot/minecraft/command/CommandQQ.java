package ren.taske.nativebot.minecraft.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import cn.glycol.t18n.I18n;
import ren.taske.data.util.ParseUtil;
import ren.taske.nativebot.NativeBotPlugin;
import ren.taske.nativebot.core.profile.UserMinecraft;

public class CommandQQ implements CommandExecutor {

	protected final NativeBotPlugin plugin;
	
	public CommandQQ(NativeBotPlugin plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(args.length == 1) {
			UserMinecraft um = UserMinecraft.of(sender.getName());
			Long qqid = ParseUtil.parseLong(args[0]);
			if(qqid != null) {
				if(um.getTencentId() == -1L) {
					um.setTencentId(qqid);
					sender.sendMessage(I18n.format("command.qq.done", qqid));
				} else {
					sender.sendMessage(I18n.format("command.qq.undone", um.getTencentId()));
				}
				return true;
			}
		}
		
		return false;
	}
	
}
