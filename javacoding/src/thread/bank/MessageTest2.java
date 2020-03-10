package thread.bank;

import java.util.StringTokenizer;

public class MessageTest2 {

	public static void main(String[] args) {

		String msg = "200#apple#test#오늘 스터디 할까?";
		msg = "210#오늘 치킨 먹을까";
		StringTokenizer st = null;
		
		if(msg!=null) {
			st = new StringTokenizer(msg,"#");
		}
		
		int protocol = 0;
		//protocol = Integer.parseInt(st.nextElement().toString());
		protocol = Integer.parseInt(st.nextToken());
		switch(protocol) {
		case 100:
			System.out.println("100때");
			break;
		case 200:
			System.out.println("200때");
			break;
		case 210:
			System.out.println("210때");
			break;
		}
		
	}

}
