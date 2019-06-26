package ren.taske.nativebot.bot.permission;

import java.util.HashMap;

import ren.taske.nativebot.core.NativeBot;

public class Permission {

	private static final HashMap<String, Permission> PERMS = new HashMap<>();
	
	private static boolean lock;
	
	protected final String name;
	protected final boolean def;
	
	private Permission(String name) {
		this(name, false);
	}
	
	private Permission(String name, boolean def) {
		this.name = name;
		this.def  = def;
	}
	
	/**
	 * <code>null</code>, if locked and un-registered
	 * @param name
	 * @return
	 */
	public static Permission of(String name) {
		return of(name, false);
	}
	
	public static Permission of(String name, boolean def) {
		if(!PERMS.containsKey(name)) {
			if(lock) return null;
			PERMS.put(name, new Permission(name, def));
			NativeBot.logger().info("Register Permission Node "+name + " (def: " + def + ")");
		}
		return PERMS.get(name);
	}
	
	public static void lock() {
		lock = true;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean getDefault() {
		return this.def;
	}
	
}
