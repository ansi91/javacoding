package book.chap09;

public abstract class Duck {
	final int eye = 2;
	public FlayBehavior flyBehavior = null;
	Duck(){
		
	}
	
	public void fly() {
		System.out.println("오리는 날 수 있어요");
	}
	
	public void performFly() {
		System.out.println("오리는 날 수 있어요");
	}
	
	
	public abstract void display(); //추상 메소드
	public void swimming() {//일반 메소드
		System.out.println("모든 오리는 물위에 뜹니다. 가짜 오리도 물위에 뜨죠");
	}
	
}
