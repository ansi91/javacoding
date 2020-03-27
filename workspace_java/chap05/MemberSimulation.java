package book.chap05;

public class MemberSimulation {

	public static void main(String[] args) {

		Member member = new Member();
		//before
		
		System.out.println("before "+member.mem_name +member);
		member.mem_name="김유신"; //초기화 null==> 김유신
		//after
		member = null; //풍선줄이 날라가서 접근 불가능하다는걸 생각하면 됨 
		//System.out.println("after "+member.mem_name+member); //김유신 [에러 발생]
		
		member = new Member();
		member.mem_name="이순신";
		System.out.println("after "+member.mem_name ); 
	}

}
