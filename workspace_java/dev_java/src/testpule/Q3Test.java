package testpule;

public class Q3Test {

	public static void main(String[] args) {

		Q3 q3 = new Q3("김유신","123-456-789",100000);
		
				
		
		q3.take(50000);
		
		System.out.println(q3.toString());
	}

}
