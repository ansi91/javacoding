package book.chap07;

import java.util.Random;
import java.util.Scanner;

public class BaseBallGame {
	
	int com[] = new int[3];
	int my[] = new int[3];
	public void ranCom() {
		
		Random r = new Random();
		com[0] = r.nextInt(10);//0.0~10.0;
		do {
			
			com[1] = r.nextInt(10);//0.0~10.0;
		}while(com[0]==com[1]);
		
		do {
			com[2] = r.nextInt(10);//0.0~10.0;
			
		}while((com[0]==com[2])||(com[1]==com[2]));
	}
	
	
	//insert here - account메소드 구현//////////////////////////////////////
	public String account(String user) {
		
		int temp = Integer.parseInt(user);
		my[0] = temp/100; //123
		my[1]=(temp%100)/10;
		my[2]= temp%10;
		int ball=0,strike=0;
		for(int i=0;i<com.length;i++) {
			for(int j=0;j<my.length;j++) {
				if(com[i]==my[j]) {//내가 입력한 숫자 중에 컴퓨터에 그 숫자가 있니?
					if(i==j){//혹시 그 숫자가 자리도 일치 하는거야?
						strike++;
						}
						else {
							ball++;
					}//스트라이크 확보
				}// 볼카운트 확보
			}//end of inner for
		}///end of  outter for
		if(strike==3) {
			return "정답입니다. 축하합니다";
		}
		
		
		return strike+"스"+ball+"볼";
	}
	//////////////////////////////////////////////////////////////////////
	
	public void inputMy() {
		
			System.out.println("my배열"+my[0]+""+my[1]+""+my[2]);
	}
	
	
	public static void main(String[] args) {
		
		BaseBallGame bbg = new BaseBallGame();
		int k=0;
		
		

		
		bbg.ranCom();
		System.out.println(bbg.com[0]+""+bbg.com[1]+""+bbg.com[2]);
		System.out.println("게임이 시작되었습니다");
		System.out.println("세자리 숫자를 입력하세요");
		
		Scanner sc = new Scanner(System.in);
		String user = null;
		
		int cnt=1;
		for(int i=1;i<=9;i++) {
			user = sc.nextLine();
		//	System.out.println("입력한 숫자"+user);
		//	String result =bbg.account(user);
		//	System.out.println(result);
			System.out.println(++cnt +"회:"+user+" "+bbg.account(user));
		}
			}
	}


