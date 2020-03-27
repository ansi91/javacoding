package study.prac;
import java.util.ArrayList;
import java.util.List;

public class ListTest {


	
	
	public static void main(String[] args) {
		List<String> li = new ArrayList<String>();
		
		li.add("가나다");
		li.add("홍길동");
		
		String element = li.get(0).toString();
		
		for (int i=0; i<=1;i++) {
			element = li.get(i).toString();
			System.out.println(element);
		}
		
		
		
		
	}

}
