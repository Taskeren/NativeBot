package ren.taske.nativebot.util;

import java.util.List;

import com.google.common.collect.Lists;

import cc.moecraft.icq.command.interfaces.IcqCommand;
import ren.taske.nativebot.core.NativeBot;

public class ClassUtils {
	
	public static List<IcqCommand> instantiate(String...names) {
		List<Class<?>> classes = Lists.<Class<?>>newArrayList();
		for(String name : names) {
			 Class<?> cls;
			 try {
				 cls = Class.forName("ren.taske.nativebot.bot.command."+name);
				 classes.add(cls);
			 } catch (Exception e) {
				 NativeBot.logger().warning("Unable to load "+name);
			}
		}
		return instantiate(classes);
	}

	public static List<IcqCommand> instantiate(List<Class<?>> classes) {
		List<IcqCommand> ret = Lists.newArrayList();
		for(Class<?> clazz : classes) {
			try {
				Object obj = clazz.newInstance();
				if(obj instanceof IcqCommand) {
					IcqCommand cmd = (IcqCommand) obj;
					ret.add(cmd); 
				} else {
					NativeBot.logger().warning(String.format("%s is NOT a IcqCommand", clazz.getName()));
				}
			} catch(Exception e) {
				NativeBot.logger().warning("Error when loading IcqCommand "+clazz.getName());
				NativeBot.logger().warning(e.getMessage());
			}
		}
		return ret;
	}
	
}
