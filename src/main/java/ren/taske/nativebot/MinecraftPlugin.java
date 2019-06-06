package ren.taske.nativebot;

import org.bukkit.plugin.java.JavaPlugin;

import ren.taske.nativebot.core.NativeBot;

public class MinecraftPlugin extends JavaPlugin {

	protected final NativeBot core = new NativeBot(this);
	
	@Override
	public void onEnable() {
		core.onEnable();
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		core.onDisable();
		super.onDisable();
	}
	
}
