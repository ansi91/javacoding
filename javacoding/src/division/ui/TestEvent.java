package division.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TestEvent implements ActionListener {

	TestView tv = null;


	public TestEvent(TestView tv) {
		
		this.tv = tv;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		
		if(obj==tv.ts.jtf_msg) {
			tv.ts.jtf_msg.setText("오늘 스터디 할까?");
		
		}else if(obj == tv.jbtn_change) {//TestView에서 버튼을 누른거니? 그러면 화면을 바꿔준다
			Container cont = tv.getContentPane();
			cont.remove(tv.ts);
			cont.remove(tv.ts.jtf_msg);
			JPanel jp = new JPanel();
			JButton jbtn = new JButton("테스트");
			jp.add(jbtn);
			tv.add("South",jp);
			cont.revalidate();
			
		}
		
	}

}
