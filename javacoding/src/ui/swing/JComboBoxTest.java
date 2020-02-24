package ui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import com.util.DBConnectionMgr;

public class JComboBoxTest implements ItemListener{
	
	//선언부
	String data[] = {"총무부","인사부","영업부"};
	JComboBox jcb_dept = new JComboBox(data);
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	//생성자
	 public JComboBoxTest() {
			
			jcb_dept.addItemListener(this);
			JFrame jf = new JFrame();
			jf.add("North",jcb_dept);
			jf.setSize(500, 500);
			jf.setVisible(true);
	}
	

	//메인 메소드
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		new JComboBoxTest();

	}
	/*
	 * ItemListener의 공식명칭은 인터페이스 이다.
	 * 인터페이스는 추상메모드를 가지고 있으므로 반드시 구현해 주어야 한다.
	 * 이때 부모가 가진 메소드의 원형을 절대로 훼손해서는 안된다.
	 */

	@Override
	/*
	 * 오라클 서버에서 dept테이블에 있는 정보를 조회 하시오
	 * 조회된 정보를 data 배열에 초기화 하시오.
	 */
	public void itemStateChanged(ItemEvent e) {

		Object obj = e.getSource();
		
		if(obj==jcb_dept) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				System.out.println(jcb_dept.getSelectedIndex());
	
			}
		}
		
	}




}
