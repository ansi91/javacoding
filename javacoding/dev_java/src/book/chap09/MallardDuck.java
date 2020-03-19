package book.chap09;

public class MallardDuck extends Duck {

	MallardDuck(){
		flyBehavior = new FlyWithWings();
	}
	
	public void performfly() {
		System.out.println("나는 하늘을 날 수 있어요");
	}

	public void display() {
		
	}
	
}
