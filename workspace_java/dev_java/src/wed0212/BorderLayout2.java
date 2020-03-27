package wed0212;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BorderLayout2 implements ActionListener { 
	
	JFrame jf=							new JFrame();  		//디폴트 레이아웃이 BorderLayout[동,서,남,북,중앙]
	JPanel jp_north  = 			new JPanel();
	JLabel jlb_su	   =			new JLabel("인원수");
	JLabel jlb_su2	   =			new JLabel("인원수");
	JTextField jtf_inwon =		new JTextField();
	JTextField jtf_inwon2 =		new JTextField(10);
	JLabel jlb_inwon	   =			new JLabel("명");
	JPanel jp_south  = 			new JPanel();
	JPanel jp_east    = 			new JPanel();
	JPanel jp_west   = 			new JPanel();
	JPanel jp_center = 			new JPanel();//디폴트 레이아웃이 FlowLayout 		//속지	
	JButton jbtn_jumsu   =	new JButton("점수 가져오기");
	JButton jbtn_account   =	new JButton("점수 처리");
	JButton jbtn_exit   =		new JButton("종  료");
	
	int jumsus[][] = {
			{100,80,90} //1row - 강호동
		   ,{60,70,90}  //2row - 유재석
		   ,{80,70,70}
		   ,{90,90,90}
		   ,{80,80,80}
	};
	
	public BorderLayout2(){
		jbtn_exit.addActionListener(this);	
		jp_north.setLayout(new BorderLayout());
		jp_north.setBackground(Color.ORANGE);
		jp_south.setBackground(Color.YELLOW);
		
		jp_south.add(jlb_su2);
		jp_south.add(jtf_inwon2);
		jp_south.add(jlb_inwon);
		jp_south.add("South",jbtn_jumsu);
		jp_south.add("South",jbtn_account);
		jp_south.add("South",jbtn_exit);
		jp_east.setBackground(Color.PINK);
		jp_west.setBackground(Color.BLUE);
		jp_center.setBackground(Color.GREEN);
		jp_north.add("West",jlb_su);
		jp_north.add("Center",jtf_inwon);
		jf.add("North",jp_north);
		jf.add("South",jp_south);
		jf.add("West",jp_west);
		jf.add("East",jp_east);
		jf.add("Center",jp_center);
		
		jf.setSize(500, 400);
		jf.setVisible(true);
		
		
		
		
		
		}
	
	
	public double total() {
		double sum=0;
		for(int i=0;i<jumsus.length;i++) {
			for(int j=0;j<jumsus[i].length;j++) {
				sum+=jumsus[i][0];
			}
		}
		return sum;
	}
	public double average() {
		double avg=0;
		
		System.out.println(total());
		return 0.0;
	}
	
	public static void main(String[] args) {
		new BorderLayout2();
		BorderLayout2 b2 = new BorderLayout2();
		b2.average();
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj =e.getSource();
		if(obj == jbtn_exit) {
			System.exit(0);
		}
		
	}

}
