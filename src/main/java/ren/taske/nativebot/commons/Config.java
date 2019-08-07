package ren.taske.nativebot.commons;

import TConfig.Configuration;

public class Config {

	public static final Configuration cfg;
	
	static {
		cfg = new Configuration(Reference.FILE_CONFIGURATION);
		refresh();
	}
	
	// The Bot Constructor
	public static int port_in;
	public static int port_out;
	public static String url_out;
	public static String[] prefixes;
	
	// The Chatting Settings
	public static long group_id;
	public static String[] chatting_prefixes;
	public static boolean require_prefix;
	
	// I18n Settings
	public static boolean useJarLanguageFile;
	
	public static void refresh() {
		
		port_in  = cfg.getInt("in",  "constructor", 25560, 0, 65535, "The port for receiving messages");
		port_out = cfg.getInt("out", "constructor", 25561, 0, 65535, "The port for sending message to HttpApi");
		url_out  = cfg.getString("url", "constructor", "127.0.0.1",  "The url for sending message to HttpApi");
		prefixes = cfg.getStringList("prefixes", "constructor", new String[] {"/"}, "The prefixes of commands in Tencent");
		
		group_id = cfg.get("chatting", "group", "139971220").getLong(0L);
		chatting_prefixes = cfg.getStringList("prefixes", "chatting", new String[] {"!", "\uff01"}, "The prefixes of chatting");
		require_prefix = cfg.getBoolean("require_prefiex", "chatting", true, "Should the message to re-sent start with prefixes");
		
		useJarLanguageFile = cfg.getBoolean("lang_refresh", "lang", false, "True if want use the language file in jar");
		
		cfg.save();
		
	}
	
}
