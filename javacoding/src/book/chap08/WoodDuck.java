package book.chap08;

public class WoodDuck extends Duck{

	String type =null;
	WoodDuck(){
		System.out.println("나무오리");
	}
	
	public void fly() {
		System.out.println("나는 날지 못해요");
	}
	

	@Override
	public void swimming() {
		//재정의 라는 의미가 있다
		System.out.println("나는 물 위에 3초정도 떠 있다가 가라 앉습니다");
	}



	
	
	
	
}
