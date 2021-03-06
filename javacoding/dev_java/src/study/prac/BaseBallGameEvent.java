package study.prac;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BaseBallGameEvent implements ActionListener {
BaseBallGameView bbView= null; //View에서 이미 생성자가 있다 그안에 initDisplay()호출이 있다 new BaseBallView()를 입력하면 화면이 두번씩 출력된다.

//게임을 진행하는 동안에는 계속 그 숫자를 기억하고 있따가 1씩 증가해야 하니까
//전역변수로 해야함
int cnt=0; //회차를 출력할 변수선언
	public BaseBallGameEvent(BaseBallGameView bbView) { //****
		
		this.bbView = bbView;
	}
	
	public void exitGame() {
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj =e.getSource();
		
		
		if(obj==bbView.jbtn_exit){
		
			exitGame();
		}
		else	if(obj==bbView.jbtn_next){
			cnt =0;
				
			bbView.bbLogic.ranCom();
			//BaseBallGameLogic안에 com배열이 선언되어있음.
			//인스턴스화를 한 상태이므로 접근이 가능함
			for(int coms:bbView.bbLogic.com) {
				System.out.print(coms+"");
			}
			System.out.println();
			bbView.jbtn_dap.setEnabled(true);
		}
		else if(obj==bbView.jtf_input) {
			bbView.jta_display.append(++cnt+"회"+bbView.jtf_input.getText()+" "+bbView.bbLogic.account(bbView.jtf_input.getText())+"\n");
			
			bbView.jtf_input.setText("");
		}
		else if (obj==bbView.jbtn_clear) {
			bbView.jta_display.setText("");
		}
		else if (obj == bbView.jbtn_dap) {
			bbView.jta_display.append(bbView.bbLogic.com[0]+""+bbView.bbLogic.com[1]+""+bbView.bbLogic.com[2]);
			bbView.jbtn_dap.setEnabled(false);
		}
	}

}