package ren.taske.nativebot.commons;

import java.io.File;

import ren.taske.nativebot.bot.command.CommandOperator;

public class Reference {

	public static final File DATA_FOLDER = new File("NativeBot");
	
	public static final File FILE_CONFIGURATION = new File(DATA_FOLDER+"/config.cfg");
	
	public static final File FILE_I18N = new File(DATA_FOLDER+"/i18n.txt");
	
	public static File getTencentProfile(long userid) {
		return new File(DATA_FOLDER+"/tencent/"+userid+".profile");
	}
	
	public static File getMinecraftProfile(String userid) {
		return new File(DATA_FOLDER+"/minecraft/"+userid+".profile");
	}
	
	public static final String NODE_OP = CommandOperator.OP_PERM_NODE;
	
}
