package ren.taske.nativebot.minecraft.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import ren.taske.data.util.ParseUtil;
import ren.taske.nativebot.MinecraftPlugin;
import ren.taske.nativebot.commons.Reference;
import ren.taske.nativebot.core.profile.UserMinecraft;
import ren.taske.nativebot.core.profile.UserTencent;

public class CommandQQ implements CommandExecutor {

	protected final MinecraftPlugin plugin;
	
	public CommandQQ(MinecraftPlugin plugin) {
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
					sender.sendMessage("Your Tencent userid now is " + qqid);
				} else {
					sender.sendMessage("You have set your Tencent userid!");
				}
				return true;
			}
		}
		
		if(args.length == 2) {
			UserMinecraft um = UserMinecraft.of(args[1]);
			UserTencent ut = UserTencent.of(um.getTencentId());
			Long qqid = ParseUtil.parseLong(args[0]);
			
			if(ut.hasPermission(Reference.NODE_OP)) {
				if(qqid != null) {
					um.setTencentId(qqid);
					sender.sendMessage(ChatColor.GOLD+um.getUserId()+ChatColor.RESET+"'s Tencent userid now is "+qqid);
				}
			} else {
				sender.sendMessage("You have no permission!");
			}
			return true;
		}
		
		return false;
	}
	
}
