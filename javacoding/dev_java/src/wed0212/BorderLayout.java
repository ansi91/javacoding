package wed0212;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BorderLayout {

	JFrame jf=							new JFrame();  		//디폴트 레이아웃이 BorderLayout[동,서,남,북,중앙]
	JButton jbtn_north  = 			new JButton("북쪽");
	JButton jbtn_south  = 			new JButton("남쪽");
	JButton jbtn_east    = 			new JButton("동쪽");
	JButton jbtn_west   = 			new JButton("서쪽");
	JButton jbtn_center = 			new JButton("중앙");
	//JPanel											//속지
	public BorderLayout() { 
		jf.add("North",jbtn_north);
		jf.add("South",jbtn_south);
		jf.add("West",jbtn_west);
		jf.add("East",jbtn_east);
		jf.add("Center",jbtn_center);
		
		jf.setSize(500, 400);
		jf.setVisible(true);
	}
	
	public static void main(String[] args) {
		new BorderLayout();

	}

}
