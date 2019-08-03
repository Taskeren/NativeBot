package ren.taske.nativebot;

import java.awt.Font;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class Main {

	public static final String[] TITLES = {"𝘕𝘈𝘛𝘐𝘝𝘌𝘉𝘖𝘛 𝘌𝘙𝘙𝘖𝘙", "𝙽𝙰𝚃𝙸𝚅𝙴𝙱𝙾𝚃 𝙴𝚁𝚁𝙾𝚁", "𝐍𝐀𝐓𝐈𝐕𝐄𝐁𝐎𝐓 𝐄𝐑𝐑𝐎𝐑", "𝐍𝐀𝐓𝐈𝐕𝐄𝐁𝐎𝐓 𝐄𝐑𝐑𝐎𝐑"};
	public static final String[] CONTENTS = {
			"𝐔𝐧𝐬𝐮𝐩𝐩𝐨𝐫𝐭𝐞𝐝",
			"𝑼𝒏𝒔𝒖𝒑𝒑𝒐𝒓𝒕𝒆𝒅",
			"𝕌𝕟𝕤𝕦𝕡𝕡𝕠𝕣𝕥𝕖𝕕"
	};
	public static final String MORE = "https://nb.taske.ren";
	
	public static void main(String[] args) {
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(Font.SANS_SERIF, Font.PLAIN, 15)));
		UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("Microsoft YaHei", Font.PLAIN, 12)));
		
		String title = TITLES[new Random().nextInt(TITLES.length)];
		String content = CONTENTS[new Random().nextInt(CONTENTS.length)];
		ImageIcon ii = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("error.png"));
		JOptionPane.showMessageDialog(null, content+"\n"+MORE, title, JOptionPane.ERROR_MESSAGE, ii);
	}
	
}
