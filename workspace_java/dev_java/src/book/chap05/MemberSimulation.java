package book.chap05;

public class MemberSimulation {

	public static void main(String[] args) {

		Member member = new Member();
		//before
		
		System.out.println("before "+member.mem_name +member);
		member.mem_name="ê¹??œ ?‹ "; //ì´ˆê¸°?™” null==> ê¹??œ ?‹ 
		//after
		member = null; //?’?„ ì¤„ì´ ?‚ ?¼ê°??„œ ? ‘ê·? ë¶ˆê??Š¥?•˜?‹¤?Š”ê±? ?ƒê°í•˜ë©? ?¨ 
		//System.out.println("after "+member.mem_name+member); //ê¹??œ ?‹  [?—?Ÿ¬ ë°œìƒ]
		
		member = new Member();
		member.mem_name="?´?ˆœ?‹ ";
		System.out.println("after "+member.mem_name ); 
	}

}
