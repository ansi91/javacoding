package study;

import java.util.Random;

public class BaseBallGameLogic {
		int com[] = new int [3];
		int my[] = new int[3];
	public void ranCom() {

		Random r = new Random();
		com[0] = r.nextInt(10);// 0.0~10.0;
		do {

			com[1] = r.nextInt(10);// 0.0~10.0;
		} while (com[0] == com[1]);

		do {
			com[2] = r.nextInt(10);// 0.0~10.0;

		} while ((com[0] == com[2]) || (com[1] == com[2]));
	}
	
	public String account(String user) {
		
		int k = Integer.parseInt(user);
		int strike=0;
		int ball=0;
		
		
		my[0] = k/100;                 //123
		my[1] = (k%100)/10;
		my[2] = k%10;
		for(int i = 0; i<com.length;i++) {
			for(int j=0; j<my.length;j++) {
				if(com[i]==my[j]) {
					if(i==j) {
						strike++;
					}else {
						ball++;
					}
				}
			}
		}
		if(strike == 3 ) {
			return "축하합니다. 정답입니다";
		}
		return strike +"스트라이크" + ball +"볼";
	}
	
}