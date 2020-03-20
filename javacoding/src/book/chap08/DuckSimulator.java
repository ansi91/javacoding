package book.chap08;

public class DuckSimulator {
	
	/*선언부의 타입과 생성부의 타입이 다를 수 있다.
	 * 결론 : 생성부 타입으로 객체가 로딩된다. (생성된다, heap영역에 올라간다)
	 * 그런데 선언부 타입이 그 메소드를 선언하고 있지 않으면 자식 클래스 안에 그 메소드가 선언되어 있다 하더라도 호출할 수 없다
	 * 완결 : 무조건 부모 타입에 선언된 메소드만 호출할 수 있다.
	 */

	public static void main(String[] args) {

		Duck d = new Duck();
		Duck wd = new WoodDuck(); //권장
		Duck rd = new RubberDuck(); //권장
		Duck md = new MallardDuck(); //권장
	
		System.out.println(d.yell);
		d.swimming();
		d.fly();
		
		wd.quack("꽉꽉");
		wd.swimming();
		wd.fly();
	
		
		rd.quack("삐이익");
		rd.swimming();
		rd.fly();
		
		md.quack("므으으으");
		md.swimming();
		md.fly();
		
	}

}
