import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Study implements ItemListener,ActionListener {

	//선언부
	final int SCISSOR = 0;
	final int ROCK = 1;
	final int PAPER = 2;
	int userPick=0;
	int computerPick=0;
	int userScore =0;
	int computerScore = 0;
	String data [] = {"가위","바위","보"};
	String isfinish [] = {"계속", "종료"};
	JPanel jp_north = new JPanel();
	JPanel jp_center = new JPanel();
	JFrame jf_srp = new JFrame();
	JLabel jlb_user = new JLabel(" 유 저 ");
	JLabel jlb_com = new JLabel(" 컴퓨터 ");
	JLabel jlb_blank = new JLabel("                                                                   ");
	JLabel jlb_userscore = new JLabel("score " + userScore);
	JLabel jlb_comscore = new JLabel("score " + computerScore);
	JButton jbtn_fire 	 = new JButton("결과보기");
	JComboBox jcb_input = new JComboBox(data);
	JComboBox jcb_isFinish = new JComboBox(isfinish);
	//생성자
	public Study() {
		computerPick();
		
		initDisplay();
	}
	
	
	//화면 처리부
	public void initDisplay() {
		jf_srp.setLocationRelativeTo(null);
		jf_srp.setTitle("가위 바위 보");
		jf_srp.setSize(500, 500);
		jp_north.add("North",jlb_user);
		jp_north.add("North",jlb_userscore);
		jp_north.add("North",jlb_blank);
		jp_north.add("North",jlb_com);
		jp_north.add("North",jlb_comscore);
		jp_center.add("Center",jcb_input);
		jp_center.add("Center",jbtn_fire);
		jp_center.add("Center",jcb_isFinish);
		jf_srp.add("North",jp_north);
		jf_srp.add("Center",jp_center);
		
		jf_srp.setVisible(true);
		jcb_input.addItemListener(this);
		jcb_isFinish.addItemListener(this);
		jbtn_fire.addActionListener(this);
	}
	
	public void computerPick() {
		Random r = new Random();
		String result = null;
		 computerPick=r.nextInt(3);
		switch(computerPick) {
		case 0:
			result = "가위";
			computerPick= SCISSOR;
			break;
		
		case 1:
			result ="바위";
			computerPick= ROCK;
			break;
		case 2:
			result = "보";
			computerPick= PAPER;
			break;
			
			
		}
		System.out.println(result);
	}
	
	
	//메인 메소드 
	public static void main(String[] args){

		new Study();
		
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		Object obj = e.getSource();
		if(obj==jcb_input) {
			if(e.getStateChange()==ItemEvent.SELECTED) {
				
				userPick = jcb_input.getSelectedIndex();
				
			}
		}else if(obj==jcb_isFinish) {
			if(e.getStateChange()==ItemEvent.SELECTED) {
				jcb_isFinish.getSelectedIndex();
				if(jcb_isFinish.getSelectedIndex()==0) {
					System.out.println("게임 계속하기");
				}
				else {
					System.exit(0);
				}
			}
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		
		if(computerPick==SCISSOR&&userPick==ROCK) {
			System.out.println("유저 승리");
			userScore++;
			computerPick();
		
		} 
		else if(computerPick==ROCK&&userPick==PAPER){
			System.out.println("유저 승리");
			userScore++;
			computerPick();
		}
		else if(computerPick==PAPER&&userPick==SCISSOR) {				
			System.out.println("유저 승리");
			userScore++;
			computerPick();
	}
		else if(computerPick==userPick) {
			System.out.println("비겼습니다");
			computerPick();
		}else {
			System.out.println("패배");
			computerPick();
			computerScore++;
		}
		jlb_userscore.setText("score " + userScore);
		jlb_comscore.setText("score " + computerScore);
	}
}
