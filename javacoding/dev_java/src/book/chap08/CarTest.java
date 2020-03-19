package book.chap08;

public class CarTest {

	public static void main(String[] args) {

		//myCar로 접근할 수 있는 변수의 갯수는 몇개일까요?        1개
		//myCar로 호출할 수 있는 메소드의 갯수는 몇개 일까요?	  1개
		/*myCar의 타입이 Car타입이어서 Tivoli타입의 변수나 메소드는 한 개도 접근, 호출 할수가 없습니다 
		 * 이것은 new Tivoli()로 인스턴스화 한 경우에도 동일하게 적용됩니다.
		 * 다시 말하자면 타입이 Car타입이어서 생성부의 이름이 Tivoli가 온다 하더라도 
		 * Tivoli타입의 변수나 메소드는 접근, 호출이 불가하다는 것이죠
		 * 이런경우 메소드는 부모와 자식클래스 모두 선언해 놓으면[오버라이드] 호출은 가능합니다
		 * 만일 부모에만 존재하는 메소드 있을경우 자식에게 존재하지 않는 메소드는 부모 메소드가 호출된다
		 * 메소드가 부모와 자식이 둘다 가지고 있다면 자식 클래스가 호출된다
		 * 그러나 변수는...? */
		 
		Car myCar = new Car();
		Tivoli youCar = null;
		myCar.speed=10;
		myCar.stop();
		System.out.println(myCar.speed);
		myCar = null;                               //20번에서 21번으로 진행되는 과정에서 candidate상태
		myCar = new Tivoli();
		
		
		
		//youCar로 접근할 수 있는 변수의 갯수는 몇개일까요? 3개
		//youCar로 호출할 수 있는 메소드의 갯수는 몇개 일까요? 3개
		youCar = new Tivoli();
		youCar.speed=10;
		youCar.carAudio=10;
		youCar.carColor="white";
		
		
		youCar.stop();
		System.out.println(youCar.speed);
		youCar.volumnUp();
		youCar.volumnDown();
		
		
		
		
		
	}

}
