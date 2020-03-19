package wed0212;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import com.util.DBConnectionMgr;

public class DeptDao implements ItemListener{
	
	//선언부
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	//JComboBox jcb_dept = new JComboBox(data);
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	StringBuilder sb = new StringBuilder("");
	String [] data = null;
	JComboBox jcb_dept = null; 
	//생성자
	 public DeptDao() {
		 	data= getDeptList();
			 	jcb_dept = new JComboBox(data);// getDeptList가 괄호에 들어간다
		 	jcb_dept.addItemListener(this);
			JFrame jf = new JFrame();
			jf.add("North",jcb_dept);
			jf.setSize(500, 500);
			jf.setVisible(true);
		 
	 }
			

	
	/*
	 * 메소드의 리턴 타입은 배열로 했어요 왜냐하면 그 배열을 JComboBox에 생성자
	 * 파라미터로 넘겨야 하기 때문에 리턴타입이 꼭 필요한거죠
	 */
	
	 /* 출력메소드
	  * 부서명을 담을 배열을 선언했어요 
	  * String 대신에 StringBuilder를 사용해야 합니다
	  * 이 클래스는 원본이 바뀌기 때문에 불필요한 자원낭비를 막을 수 있습니다.
	  * 서버 입장에서는 동시 접속자 수가 많아서 작은 양이지만 큰 문제를 일으킬 수도 있다고 합니다
	  */
	 public String[] getDeptList() {
		
		 String depts[] = null;
		 //물리적으로 떨어져 있는 서버에 ip주소로 접근하니까 예외가 발생할 가능성이 있음
		 //예외가 발생하면 시스템이 멈춰 서 있게 됩니다.
		 //무한히 기다리는 상황이 발생되므로 다음 사람도 이용할 수 없다.
		 	sb.append(" SELECT dname FROM dept "); 
		 try {
			 //쿼리문 작성

			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			Vector<String> v = new Vector<String>();
			while(rs.next()) {
				String dname = rs.getString("dname");
				//조회된 값을 벡터 클래스에 추가한다
				v.add(dname);
			}
			//오라클서버에 조회된 결과만큼 벡터의 크기값만 가진다.
			depts = new String[v.size()];
			v.copyInto(depts); //복사해서 일괄로 배열에 넣는다 
		} catch (Exception e) {
			System.out.println("sql:"+sb.toString());
			System.out.println(e.toString());
	
		}
		 return depts;
	 }
	 
	 
	//메인 메소드
	public static void main(String[] args) {
		DeptDao dd = new DeptDao();
		 
		 String depts[] = dd.getDeptList();
		 
		 for(String dept:depts) {
			 System.out.println(dept);
		 }

		}



	@Override
	public void itemStateChanged(ItemEvent e) {
Object obj = e.getSource();
		
		if(obj==jcb_dept) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				System.out.println(jcb_dept.getSelectedIndex());
	
			}
		
	 }
	}
}

	
		






