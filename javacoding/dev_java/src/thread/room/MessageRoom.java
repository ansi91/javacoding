package thread.room;

import java.awt.Color;

import javax.swing.JPanel;

public class MessageRoom extends JPanel {

	TalkClientVer2 tc = null;
	
	public MessageRoom(TalkClientVer2 tc) {
		
		this.tc = tc;
		initDisplay();
	}
	
	public void initDisplay() {
		this.setBackground(Color.BLUE);
	}
	
	
}
