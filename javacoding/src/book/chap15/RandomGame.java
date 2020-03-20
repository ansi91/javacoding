package book.chap15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RandomGame extends JFrame {

	public static void main(String[] args) {
		RandomGame rg = new RandomGame();
		Random r = new Random();
		String user = "-1";
		int result  = r.nextInt(10); // 0~9
		JOptionPane.showMessageDialog(rg, "0~9", "랜덤숫자입력", JOptionPane.INFORMATION_MESSAGE);
		InputStreamReader in = new InputStreamReader(System.in);
		//버퍼링 기능이 추가되어 있는 보조 스트림이다
		//단독으로 읽기 불가함. 기반스트림과 연결해서 써야 한다.
		BufferedReader br = new BufferedReader(in); // --> 보조스트림 안에 기반 스트림을 파라미터로 넣었다.
		try {
//			user = in.read();
//			System.out.println(user);
			while(((user=br.readLine())!=null)){
				System.out.println("유저가 입력한 숫자:" + user);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

}
