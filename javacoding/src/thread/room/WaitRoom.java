package thread.room;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;



public class WaitRoom extends JPanel implements MouseListener, ActionListener {
	String			   col[]							= {"대화명","위치"};
	String			   data[][]						=new String[5][2];
	DefaultTableModel dtm_wait				=new DefaultTableModel(data,col);
	JTable 				jtb_wait					=new JTable(dtm_wait);
	JScrollPane		jsp_wait					=new JScrollPane(jtb_wait);
	JTableHeader   jth_wait						=jtb_wait.getTableHeader();
	String			   col2[]							= {"단톡명","현재인원"};
	String			   data2[][]						=new String[5][2];
	DefaultTableModel dtm_room				=new DefaultTableModel(data2,col2);
	JTable 				jtb_room					=new JTable(dtm_room);
	JScrollPane		jsp_room					=new JScrollPane(jtb_room);
	JTableHeader   jth_room					=jtb_wait.getTableHeader();
	JPanel 			   jp_first 						=new JPanel();
	JPanel			   jp_second  				=new JPanel();
	JPanel			   jp_second_south 		=new JPanel();
	JLabel 				jlb_banner				=new JLabel();
	JButton jbtn_create = new JButton("단톡방");
	JButton jbtn_in = new JButton("입장");
	JButton jbtn_out = new JButton("나기기");
	JButton jbtn_exit = new JButton("종료");
	
	TalkClientVer2 tc 					  			 = null;
	
	public WaitRoom(TalkClientVer2 tc) {
		this.tc = tc;
		initDisplay();
	}
	
	
	public void initDisplay() {
		this.setLayout(new GridLayout(1,2));
		jp_first.setBorder(BorderFactory.createBevelBorder(10));
		jp_first.setBackground(Color.PINK);
		jp_first.setLayout(new BorderLayout());
		jth_wait.setReorderingAllowed(false); //테이블 헤더 고정 시키기
		jth_wait.setBackground(Color.LIGHT_GRAY);
		jth_wait.setForeground(Color.white);
		jtb_wait.setGridColor(new Color(255,50,200));
		jtb_wait.setSelectionBackground(Color.GREEN);
		
		jp_first.add(jsp_wait);
		jp_second.setBackground(Color.CYAN);
		jp_second.setLayout(new BorderLayout()); //BorderLayOut은 동서남북 방향을 줄 수 있다.
		jp_second.add("Center",jsp_room);
		
		jp_second_south.add(jbtn_create);
		jp_second_south.add(jbtn_in);
		jp_second_south.add(jbtn_out);
		jp_second_south.add(jbtn_exit);
		jp_second.add("South",jp_second_south);
			
		this.add(jp_first);
		this.add(jp_second);
		this.setBackground(Color.GREEN);
		
		jtb_room.addMouseListener(this);
		jbtn_in.addActionListener(this);
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		Object obj = e.getSource();
		JOptionPane.showMessageDialog(tc,"Mouse Pressed");
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if("입장".equals(command)) {
			tc.jtp.setSelectedIndex(1);
		}
		
	}








	


	


	


	

}
