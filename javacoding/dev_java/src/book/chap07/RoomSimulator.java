package book.chap07;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RoomSimulator extends JFrame {
//JTable헤더에 들어갈 정보들의 이름을 1차배열로 선언하기
	String cols[] = {"톡방명", "총인원수","참여인원"};
	//JTable에 들어갈 데이터 영역 생성하기
	//데이터는 존재하지 않으므로 0을 주었고 컬럼의 수는 톡방명, 총인원수, 현재 참여자 명단
	//이렇게 3이므로 3을 주었음
	String data[][] = new String[0][3];
	//실제로 데이터를 담을 수 있는 자바에서 제공되는 클래스임
	//화면에 붙일때 헤더정보들은 2차배열로 포함시키지 않음.
	DefaultTableModel dtm_room = new DefaultTableModel(data,cols);
	//JTable은 양식을 제공할 뿐 데이터는 DefaultTableModel에 초기화 되어야함
	JTable jtb_room = new JTable(dtm_room);
	//JScrollPane은 일종의 속지로 스크롤되는 화살표를 지원해줌
	JScrollPane jsp_room = new JScrollPane(jtb_room);
	
	public RoomSimulator() {
		//최종적으로 화면에 붙일 때 JTable이 아닌 JScrollPane이 붙음.
		this.add("Center",jsp_room);
		//창닫기시 자원반납처리
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400,500);
		this.setVisible(true);
		
		ArrayList<Room> room = new ArrayList<Room>();
		Room r = new Room();
		r.gisu="코스모 57기 수업";
		
		room.add(r);
		
		Vector<String> v = new Vector<String>();
		v.add(room.get(0).gisu);
		v.add(room.get(0).t_inwon);
		v.add(room.get(0).c_inwon);
	}
	
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		new RoomSimulator();
	}

}
