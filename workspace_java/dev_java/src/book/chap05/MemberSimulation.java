package book.chap05;

public class MemberSimulation {

	public static void main(String[] args) {

		Member member = new Member();
		//before
		
		System.out.println("before "+member.mem_name +member);
		member.mem_name="κΉ?? ? "; //μ΄κΈ°? null==> κΉ?? ? 
		//after
		member = null; //?? μ€μ΄ ? ?Όκ°?? ? κ·? λΆκ??₯??€?κ±? ?κ°νλ©? ?¨ 
		//System.out.println("after "+member.mem_name+member); //κΉ?? ?  [??¬ λ°μ]
		
		member = new Member();
		member.mem_name="?΄?? ";
		System.out.println("after "+member.mem_name ); 
	}

}
