package book.chap05;
/*
 * 오라클 서버에서 관리하고 있는 회원집합을 활용하기 위해 Member클래스를 설계 하였다.
 * 장점:배열을 사용하지 않으면서도 변수 3개를 각각 관리할 수 있게 되었다.
 * 		심지어 서로 타입이 다르더라도 이것은 가능한 일이다. #173페이지,177페이지,189페이지
 * 단점:변수가 배열이 아니다 그래서 여러개(동시에)를 담을 수 없다.
 * 			한번에 한 사람에 대한 정보만 담을 수 있다.
 * 	
 * 여러 사람의 정보를 담을 수 없을까?? -> 객체배열 사용하기 
 * 
 */
public class MemberSimulation2 {

	public static void main(String[] args) {
		//member클래스를 두 개 만들 수 있는 방을 선언해봐요
		Member mems[]= null; //아직은 방이 할당되지 않았다
		//선언한 객체배열을 생성하기 입니다. 크기는 2로 했어요
		mems = new Member[2];//생성하기
		//여기를 지나면 입장할 수 있어요.
		Member mem = new Member();
		mem.mem_name ="김유신";
		mem.mem_id="apple";
		mem.mem_pw="123";
		//앗 mem주소번지를 잘라내려고 해요
		//잘리기 전에 담아두어야 합니다 일단 null이 되고 나면 가비지 컬렉터가
		//넌 쓰레기 값이야 라고 딱지를 붙입니다. 그러면 접근을 못합니다.
		//그래서 반드시 그전에 담아두어야 하는 거군요
		mems[0]=mem;
		mem=null;
		mem= new Member();
		mem.mem_name="이순신";
		mem.mem_id="samsung";
		mem.mem_pw="123";
		mems[1]=mem;
		for(int i=0;i<mems.length;i++) {
			String id = mems[i].mem_id;
			String pw = mems[i].mem_pw;
			String name = mems[i].mem_name;
			
			System.out.println("name====> "+name);
			System.out.println("id====> "+id);
			System.out.println("pw====> "+pw);
		}
		
	}

}
//name ===> 김유신
//name ===> 이순신