package ren.taske.nativebot.core;

import cc.moecraft.icq.sender.IcqHttpApi;

public class BotApi {

	private static IcqHttpApi api;
	
	/* 初始化机器人时设定API */
	public static void setApi(IcqHttpApi api) {
		BotApi.api = api;
	}
	
	public static void sendPrivateMessage(long dest, String message) {
		api.sendPrivateMsg(dest, message);
	}
	
	public static void sendGroupMessage(long dest, String message) {
		api.sendGroupMsg(dest, message);
	}
	
	public static void sendDiscussMessage(long dest, String message) {
		api.sendDiscussMsg(dest, message);
	}
	
}
