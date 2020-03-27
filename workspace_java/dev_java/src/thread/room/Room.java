package thread.room;

import java.util.List;
import java.util.Vector;

/***************************************************
 * 생성자 살펴보기 
 *	제공되는 메소드 
 * 제공되는 필드(변수)
 * 관련있는 추상클래스, 인터페이스 같이 생각해 보기
 ***************************************************/


public class Room {
	//단톡에 있는 친구들만 관리하는 리스트
	List<TalkServerThread> userList = new Vector<>();
	List<String> nameList = new Vector<>(); //닉네임 명단을 관리하는 List
	String title = null; //단톡방 이름
	String state = null;//현재 상태 대기 또는 단톡방 이름
	int max = 0; //최대 정원수
	int current = 0; //현재 인원수
	
	
	public Room() {}
	
	public Room(String title, int current) {
		this.title = title;
		this.current = current;
	}
	public Room(String title, int current, String state) {
		this.title 	  = title;
		this.current = current;
		this.state	  = state;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}
}
