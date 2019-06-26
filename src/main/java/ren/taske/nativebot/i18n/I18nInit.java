package ren.taske.nativebot.i18n;

import static ren.taske.nativebot.commons.Reference.FILE_I18N;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.google.common.collect.Lists;

import cn.glycol.t18n.I18n;
import cn.glycol.t18n.LanguageMap;
import cn.glycol.t18n.LanguageMapBuilder;
import cn.hutool.core.io.file.FileWriter;
import ren.taske.nativebot.commons.Config;

public class I18nInit {

	private static final Logger LOGGER = LogManager.getLogManager().getLogger("NativeBot-I18nInit");
	
	private static final Charset CHARSET = Charset.forName("utf-8");
	
	public static void init() {
		
		if(!FILE_I18N.exists() || Config.useJarLanguageFile) {
			writeContextToFile(getContextFromJar());
		}
		
		LanguageMap lm = LanguageMapBuilder.fromFile(FILE_I18N);
		I18n.setLanguageMap(lm);
		
	}
	
	/** 从 Jar 里提取文件，文件是根目录的i18n.txt */
	static List<String> getContextFromJar() {
		
		InputStream is = I18nInit.class.getResourceAsStream("/i18n.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is, CHARSET));
		
		String cache = "";
		List<String> context = Lists.newArrayList();
		
		while(true) {
			try {
				if((cache = br.readLine()) == null) {
					break;
				} else {
					context.add(cache);
				}
			} catch(IOException e) {
				
			}
		}
		
		return context;
		
	}
	
	/** 将文件写入到配置文件夹 */
	static void writeContextToFile(List<String> context) {
		
		FileWriter fw = FileWriter.create(FILE_I18N, CHARSET);
		
		try {
			fw.writeLines(context);
		} catch(Exception e) {
			LOGGER.warning("Unable to write i18n to file!");
			LOGGER.warning(e.getMessage());
		}
		
	}
	
}
