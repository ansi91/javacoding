package book.chap05;

public class MemberSimulation {

	public static void main(String[] args) {

		Member member = new Member();
		//before
		
		System.out.println("before "+member.mem_name +member);
		member.mem_name="�??��?��"; //초기?�� null==> �??��?��
		//after
		member = null; //?��?��줄이 ?��?���??�� ?���? 불�??��?��?��?���? ?��각하�? ?�� 
		//System.out.println("after "+member.mem_name+member); //�??��?�� [?��?�� 발생]
		
		member = new Member();
		member.mem_name="?��?��?��";
		System.out.println("after "+member.mem_name ); 
	}

}
