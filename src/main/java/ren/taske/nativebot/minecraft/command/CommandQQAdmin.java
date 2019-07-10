package ren.taske.nativebot.minecraft.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import cn.glycol.t18n.I18n;
import ren.taske.data.util.ParseUtil;
import ren.taske.nativebot.core.profile.UserMinecraft;

public class CommandQQAdmin implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(args.length == 2) {
			Long qqid = ParseUtil.parseLong(args[0]);
			
			if(qqid != null) {
				UserMinecraft oped = UserMinecraft.of(args[1]);
				oped.setTencentId(qqid);
				sender.sendMessage(I18n.format("command.qq-admin.done", oped.getUserId(), oped.getTencentId()));
				return true;
			}
			
		}
		
		return false;
	}
	
}
