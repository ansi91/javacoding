package book.chap09;

public class WoodDuck extends Duck {

	public WoodDuck() {
		flyBehavior = new FlyNoWay(); //다형성을 누릴 수 있다.
	}
	
	public void performFly() {
		System.out.println("나는 날지 못합니다.");
	}
	
	@Override
	public void display() { //템플릿 메소드 -- 상속받은 Duck에도 있는 메소드
		
		

	}

}
