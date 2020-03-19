package book.chap08;

public class RubberDuck extends Duck {

	public RubberDuck() {
		System.out.println("고무 오리");
	}
	
	public void fly() {
		System.out.println("나는 날지 못해요");
	}
	
	
	@Override
	public void swimming() {
		System.out.println("나는 물 위에 뜰 수 있지만 잠수는 못해요");
	}
}
