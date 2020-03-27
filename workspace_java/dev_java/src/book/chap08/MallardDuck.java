package book.chap08;

public class MallardDuck extends Duck{

	public MallardDuck() {
		System.out.println("청둥오리");
}
	
	public void fly() {
		System.out.println("나는 하늘을 날고 있어요");
	}
	
	
	@Override
	public void swimming() {
		System.out.println("청둥 오리는 물위에 뜨기도 하고 잠수도 가능합니다");
	}
	
}
