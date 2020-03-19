package book.chap08;

public class CarTest2 {

	public static void main(String[] args) {

		Car myCar = null;
		Tivoli youCar = new Tivoli();
		myCar = youCar;
		
		System.out.println(myCar+" "+youCar);
		myCar.speed=10;
		youCar.speed=100;
		System.out.println(myCar.speed);
		System.out.println(youCar.speed); //둘다 있을경우 자식 객체의 변수나 메소드를 따라간다
		if(myCar instanceof Car) {
			System.out.println("myCar는 Car클래스 타입입니다");
		}else {
			System.out.println("myCar Car클래스 타입이 아닙니다");
		}
		if(youCar instanceof Tivoli) {
			System.out.println("youCar는 Tivoli클래스 타입입니다");
		}else {
			System.out.println("youCar Car클래스 타입이 아닙니다");
		}
		
		
	}

}
