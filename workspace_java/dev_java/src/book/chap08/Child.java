package book.chap08;

//Child에서는 파라미터 두 개짜리 메소드는 재정의하지 않음.
public class Child extends Parent{

	public Child() {
		System.out.println("Child 디폴트생성자");
	}

	public String car = null;
	//메소드 오버라이딩은 부모의 메소드를 재정의하는 것이다.
	//선언부는 반드시 일치해야 한다. (리턴타입과 파라미터가 일치해야한다)
	@Override 
	public void bookRead() {
		System.out.println("1달에 5권씩 책을 읽습니다.");
	}
	//나는 오버라이드가 아니다 - 왜냐면 부모에겐 없는 메소드 이니까
	public void carDrive() {
		System.out.println("파주아울렛으로 쇼핑을 갑니다.");
	}
	
}
