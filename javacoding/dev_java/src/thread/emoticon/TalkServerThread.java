package thread.emoticon;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.StringTokenizer;



public class TalkServerThread extends Thread {
	public TalkServer 			ts = null;
	Socket							client = null;
	ObjectOutputStream 	oos = null;
	ObjectInputStream 		ois = null;
	String 							chatName = null;//현재 서버에 입장한 클라이언트 스레드 닉네임 저장
	public TalkServerThread(TalkServer ts) {
		this.ts = ts;
		this.client = ts.socket; // □ -> △
		try {
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
			String msg = (String)ois.readObject(); //말한걸 듣는다 [듣기]
			ts.jta_log.append(msg+"\n"); //들은걸 그대로 말해준다.
			StringTokenizer st = new StringTokenizer(msg,"#");
			st.nextToken();//100
			chatName = st.nextToken();
			ts.jta_log.append(chatName+"님이 입장하였습니다.\n");
			for(TalkServerThread tst:ts.globalList) {
			//이전에 입장해 있는 친구들 정보 받아내기
				//String currentName = tst.chatName;
				this.send(Protocol.LOGIN
							 +Protocol.SEPERATOR
							 +tst.chatName);
			}
			//현재 서버에 입장한 클라이언트 스레드 추가하기
			ts.globalList.add(this); //this는 지금 막 들어온 사람
			this.broadCasting(msg); //들어온 사람의 정보를 전파한다
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	//현재 입장해 있는 친구들 모두에게 메시지 전송하기 구현
	public void broadCasting(String msg) { 
		for(TalkServerThread tst:ts.globalList) {
			tst.send(msg);
		}
	}
	//클라이언트에게 말하기 구현
	public void send(String msg) {
		try {
			oos.writeObject(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void run() {
		String msg = null;
		boolean isStop = false;
		try {
			//while(true) {//무한루프에 빠질 수 있다.
			run_start:
			while(!isStop) {
				
					
				msg = (String)ois.readObject(); //[듣기]
				ts.jta_log.append(msg+"\n");
				TalkClient tc = new TalkClient();
//				tc.sd_display.setCaretPosition(tc.sd_display.getLength());
				tc.jtp_display.setCaretPosition(tc.sd_display.getLength());
				StringTokenizer st = null;
				int protocol = 0;//100|200|201|202|500
				if(msg !=null) {
					st = new StringTokenizer(msg,"#");
					protocol = Integer.parseInt(st.nextToken());//100
				}
				switch(protocol) {
					case Protocol.ONE:{
						String nickName = st.nextToken(); //자신의 닉네임 
						String name		=  st.nextToken(); //상대방 닉네임
						String message = st.nextToken();  //보낼 메세지
						
						for(TalkServerThread tst:ts.globalList) { 
							if(name.equals(tst.chatName))
						tst.send(Protocol.ONE+"#"+nickName+"#"+name+"#"+message);
						}
						
						//나 자신에게 보내기
						this.send(Protocol.ONE + "#"+nickName+"#"+name+"#"+message);
						
					}break;
					case Protocol.MULTI:{
						String nickName = st.nextToken();
						String message = st.nextToken();
						String fontColor = st.nextToken();
						String imgChoice = "";
						while(st.hasMoreTokens()) {
							imgChoice = st.nextToken();
						}
						broadCasting(Protocol.MULTI
								   +"#"+nickName
								   +"#"+message
								   +"#"+fontColor
								   +"#"+imgChoice);
								   
					}break;
					
					case Protocol.CHANGE:{
						String nickName = st.nextToken();
						String afterName = st.nextToken();
						String message =  st.nextToken();
						this.chatName=afterName;
						broadCasting(Protocol.CHANGE+"#"+nickName+"#"+afterName+"#"+message); //대화명 변경할때 상대방 채팅창도 변경해야 한다
					}
					
					case Protocol.EXIT:{
						String nickName = st.nextToken();
						ts.globalList.remove(this);
						broadCasting(500 +"#"+nickName);
								  
								   
					}break run_start;
					
					
				}/////////////end of switch
			}/////////////////end of while			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}/////////////////////////end of run
}
