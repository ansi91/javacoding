package thread.talk2;

import java.util.StringTokenizer;
import java.util.Vector;

public class TalkClientThread extends Thread {
	TalkClient tc = null;
	TalkServer ts = null;
	LoginForm lf = null;
	public TalkClientThread(TalkClient tc) { //생성자를 통해서 TalkClient의 주소원본을 초기화 해준다
		this.tc = tc;
	}
	/*
	 * 서버에서 말한 내용을 들어봅시다.
	 */
	public TalkClientThread(LoginForm lf) {
		this.lf = lf;
	}

	public void run() {
		boolean isStop = false; // 상수 true대신 변수를 넣기 위해 boolean인 isStop을 쓴다 
		try {
		while(!isStop) {
			
				String msg = "";//100#apple
				msg = (String)tc.ois.readObject(); //오브젝트 격인 ois를 String으로 변환 시켜서 대입해준다
				StringTokenizer st = null;
				int protocol = 0;//100|200|201|202|500
				if(msg !=null) {
					st = new StringTokenizer(msg,"#");  //#을 구분해서 문자를 썰어준다.
					protocol = Integer.parseInt(st.nextToken());//100 -  StringTokenizer를 통해 변한 String타입을 다시 int형으로 변환한다.
				}
				//채팅이 많아 졌을때 스크롤바가 같이 따라 내려가주도록 한다 
				//입력 안하면 스크롤바가 맨 위에 붙어 있음
				 tc.jta_display.setCaretPosition(tc.jta_display.getDocument().getLength());
				switch(protocol) {
					
					case Protocol.LOGIN: {//100#apple
						String nickName = st.nextToken();
						tc.jta_display.append(nickName+"님이 입장하였습니다.\n");
						Vector<String> v = new Vector<>();
						v.add(nickName);
						tc.dtm.addRow(v);
					}break;
				
					case Protocol.ONE: {//1:1 개인대화방
						//보내는 사람
						//받는 사람
						//보내는 메세지
						//클라이언트로 전송하기
						//스레드 중에서 상대 스레드에게만 전송할 것
						//그리고 나 자신에게도 전송해보자
						
						String nickName = st.nextToken();
						String name = st.nextToken();
						String message = st.nextToken();
						
						
						

						tc.jta_display.append("["+nickName+"]"+message+"\n");
						tc.jta_display.setCaretPosition(tc.jta_display.getDocument().getLength());
					}break;
					case Protocol.MULTI:{//단톡방
						String nickName = st.nextToken();
						String message = st.nextToken();
						tc.jta_display.append("["+nickName+"]"+message+"\n");
					}break;
					case Protocol.CHANGE:{//대화명 변경
						String nickName = st.nextToken();
						String afterName = st.nextToken();
						String message =  st.nextToken();
						//테이블의 대화명 변경하기
						for(int i=0;i<tc.dtm.getRowCount();i++) { //대화명의 갯수만큼 반복문을 실행
							String imsi = (String)tc.dtm.getValueAt(i, 0); //row i번째 imsi에 담는다
							if(nickName.equals(imsi)) { 				 //StringTokenizer에 잘라온 문자와 imsi에 담긴 닉네임이 같냐?
								tc.dtm.setValueAt(afterName, i, 0); //값,row,column -> 그러면 바뀐 닉네임으로 변경한다
								break;
							}
							
						}
						//채팅창에 title에도 대화명을 변경처리한다.
						if(nickName.equals(tc.lf.nickName)) {  		//현재 닉네임하고 TimeClient의 닉네임하고 같냐?
							tc.setTitle(afterName+"님의 대화창");  //바뀐 닉네임으로 타이틀 제목 설정
							tc.lf.nickName = afterName;					//바뀐 닉네임을 TimeClient의 전역변수 nickName에다가 초기화 해준다
						}
						tc.jta_display.append(message+"\n");  //바뀐 닉네임을 대화창에 출력
					}break;
					
					case 203:{
						
					}break;
					
					
					case Protocol.EXIT:{
					  String nickName = st.nextToken();
					  tc.jta_display.append(nickName+"님이 퇴장 하였습니다.\n");
					 
					  for(int i=0;i<tc.dtm.getRowCount();i++) {
						  String n = (String)tc.dtm.getValueAt(i, 0);
						  if(n.equals(nickName)) {
							  tc.dtm.removeRow(i); //나가기 버튼을 누른 사람을 대화창에서 제거한다
						  }
					  }
					
					}break;
				}////////////end of switch
		}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}////////////////////end of while
	}////////////////////////end of run













