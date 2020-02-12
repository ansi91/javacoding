package book.chap06;

public class Tivoli {
	
	public int speed=0;
	
	
		//디폴트 생성자 구현하기 - 파라미터가 없는 생성자임
		//JVM이 대신 만들어 줄 수 있는 생성자 - 파라미터를 결정하지 않아도 되니까 제공가능함
	
	
	Tivoli(){
		this(70); // 생성자 호출하기
		//this - 자기자신의 주소번지
		//this () - 자기자신 생성자를 호출
	}
	
	Tivoli(int speed){
		this.speed=speed;
	}
	
	
	public static void main(String[] args) {
		
		Tivoli myCar = new Tivoli(); //인스턴스화 하면 디폴트 생성자 호출하기 #12[this70이 파라미터로 전달]->#18(70)
		//myCar.speed = 30;
		System.out.println(myCar.speed);
		
		myCar = new Tivoli();
		myCar.speed=50;
		System.out.println(myCar.speed);
		
		Tivoli herCar = new Tivoli();
		herCar.speed = 100;
		System.out.println(herCar.speed);

	}

}
