package ren.taske.nativebot.util;

import java.util.List;

import com.google.common.collect.Lists;

import cc.moecraft.icq.command.interfaces.IcqCommand;
import ren.taske.nativebot.core.NativeBot;

public class ClassUtils {

	@SuppressWarnings("unchecked")
	public static List<IcqCommand> instantiate(Class<IcqCommand>...classes) {
		List<IcqCommand> ret = Lists.newArrayList();
		for(Class<IcqCommand> clazz : classes) {
			try {
				IcqCommand cmd = clazz.newInstance();
				ret.add(cmd);
			} catch(Exception e) {
				NativeBot.logger().warning("Error when loading IcqCommand "+clazz.getName());
				NativeBot.logger().warning(e.getMessage());
			}
		}
		return ret;
	}
	
}
