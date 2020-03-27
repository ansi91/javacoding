package wed0212;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import com.sun.javafx.tk.Toolkit;
import sun.nio.cs.ext.SJIS;

public class Sungjuk implements ActionListener {

	// 선언부
	JFrame jf_sungjuk = new JFrame();
	JPanel jp_north = new JPanel();
	JPanel jp_south = new JPanel();
	JLabel jlb_su = new JLabel("인원수");
	JLabel jlb_inwon = new JLabel("명");
	JTextField jtf_inwon = new JTextField(15);
	JButton jbtn_data = new JButton("가져오기");
	JButton jbtn_account = new JButton("성적처리");
	JButton jbtn_exit = new JButton("나가기");
	/*사용자가 입력한 인원수를 담을 변수 입니다.
	 * 전역변수로 한 이유는 인원수를 듣기는 jtf_inwon에서 엔터 쳤을때 값이 결정됩니다.
	 * 그 때결정된 3이 jbtn_account에서도 필요합니다.
	 * 왜냐하면 총점을 기준으로 석차를 구하기로 결정되었으므로 석차를 같이 관리합니다
	 * 2차배열을 선언 하였기 때문입니다
	 */
	int inwon =0;
	int tot =0;

	String cols[] = { "이름", "자바", "오라클", "HTML", "총점", "평균", "석차" };
	String data[][] = null;


	DefaultTableModel dtm_sj = null;
	JTable jt_sj = null;
	JScrollPane jsp_sj = null;
	// JTableHeader
	JTableHeader jth_sj = new JTableHeader();

	
	
	// 생성자
	Sungjuk() {
		start();
	
	}

	// 이벤트 소스와 이벤트 처리 클래스를 매핑 --야구 숫자 게임에 rndCom 생각하면 된다
	public void start() {
		// 엔터 쳤을떄 감지하고 콜백메소드를 호출하자
		jtf_inwon.addActionListener(this);
		jbtn_account.addActionListener(this);
		jbtn_data.addActionListener(this);
		jbtn_exit.addActionListener(this);
	}

	/*********** 총점을 구하는 메소드 구현 *************/
	public double total() {
		for(int i=0;i<inwon;i++) {
				tot = 	 Integer.parseInt((String)dtm_sj.getValueAt(i,1))+ //오브젝트를 parseInt하기 위해 String으로 형 변환후 숫자로 바꿔서 tot 넣는다
							 Integer.parseInt((String)dtm_sj.getValueAt(i,2))+
							 Integer.parseInt((String)dtm_sj.getValueAt(i,3));
			
				
				//구한 총점을 DefaultTableModel객체에 담기	
			
				dtm_sj.setValueAt(tot, i, 4);
				
		}
			return tot;
	}

	/*********** 평균을 구하는 메소드 구현 *************/
	public double average() {
		double avg =0;
		for(int i=0;i<inwon;i++) {
			avg = 	 (Integer.parseInt((String)dtm_sj.getValueAt(i,1))+
						 Integer.parseInt((String)dtm_sj.getValueAt(i,2))+
						 Integer.parseInt((String)dtm_sj.getValueAt(i,3)))/3.0;
			dtm_sj.setValueAt(avg, i, 5);
	
		}
		return avg;
	}

	/*********** 석차를 구하는 메소드 구현 *************/
	public int[][] rank() {
		int imsi[][] = new int[inwon][2];


		for(int i=0;i<inwon;i++) {
			tot =  Integer.parseInt((String)dtm_sj.getValueAt(i,1))+
					 Integer.parseInt((String)dtm_sj.getValueAt(i,2))+
					 Integer.parseInt((String)dtm_sj.getValueAt(i,3));
			imsi[i][0]=tot;
			imsi[i][1]=1;
			for(int j=0;j<inwon;j++) {
				if(imsi[i][0] < imsi[j][0]) {
					imsi[i][1]++;
				}
				
			}
			
			//System.out.println(imsi[0][0]+""+imsi[1][0]+""+imsi[2][0]);
		}
		
		
	
		for(int i=0;i<inwon;i++) {
			dtm_sj.setValueAt(imsi[i][1], i, 6);
		
		}
		return imsi;
	}
	// 화면 처리부
	public void initDisplay() {
		jp_north.setLayout(new FlowLayout(FlowLayout.LEFT));
		jf_sungjuk.setTitle("성적처리 프로그램 Ver1.0");
		jf_sungjuk.setSize(500, 500);
		jp_north.add("North", jlb_su);
		jp_north.add("North", jtf_inwon);
		jp_north.add("North", jlb_inwon);
		jp_south.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jp_south.add("South", jbtn_data);
		jp_south.add("South", jbtn_account);
		jp_south.add("South", jbtn_exit);
		jf_sungjuk.add("North", jp_north);
		jf_sungjuk.add("South", jp_south);

		jth_sj.setBackground(new Color(22, 22, 100));
		jth_sj.setForeground(Color.white);
		jth_sj.setFont(new Font("맑은고딕", Font.BOLD, 16));

		// 그리드의 높이 변경하기

		// 컬럼의 너비 조정하기

		// 선택한 로우의 배경색이나 글자색 변경하기

		jf_sungjuk.setVisible(true);
		
	}

	// 메인 메소드
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		Sungjuk sj = new Sungjuk();
		sj.initDisplay();
	}

	

	/*
	 * @Overrid는 어노테이션이라고 읽음 - ActionLister가 가진 추상메소드를 그대로 가져다가 재정의해서 사용하시오 void
	 * methodA(); 이름은 지어져 있는데 기능은 확실하지 않은것 그대로 가져다가 재정의해서 사용하시오
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == jbtn_exit) {
			System.exit(0);	
		}
		else if(obj==jtf_inwon) {
			
				inwon = Integer.parseInt(jtf_inwon.getText());
			    data 	=new String[inwon][7];
			    dtm_sj	= new DefaultTableModel(data,cols);
			    jt_sj 			= new JTable(dtm_sj);
			     jsp_sj		= new JScrollPane(jt_sj);
			     jf_sungjuk.add("Center",jsp_sj);
			     jf_sungjuk.pack(); //프레임 내에 레이아웃들을  윈도우 사이즈에 맞게 맞추는 메소드
			     //사용중인 컴퓨터의 스크린 사이즈 정보 가져오기
			     Dimension di = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			    Dimension di2 = jf_sungjuk.getSize();
			    
			    jf_sungjuk.setLocation((int)(di.getWidth()/2-di2.getWidth()/2),
			    		(int)(di.getHeight()/2-di2.getHeight()/2));
			    
		}
		else if(obj==jbtn_data) {
			String data[][] = 
								{{"이순신","70","85","80"}
							   ,{"김유신","65","75","70"}
							   ,{"이성계","45","50","65"}};
				//초기화 할수 있니?, 2중 for문 사용 할 수 있니?
				for(int i=0;i<3;i++) {
					for(int j=0;j<4;j++) {
						dtm_sj.setValueAt(data[i][j], i, j); //값, row , column

					}
				}
		}else if(obj==jbtn_account) {
			//총점과 석차가 들어갈 공간을 할당하기
			//인원수는 어떻게 가져오지? - 전역변수로 선언하고 사용하는게 좋겠어 왜냐면 다른 이벤트에서도 필요하기 때문이지
			total();
			average();
			rank();
		}
	}			
}			
				
			

		
		



