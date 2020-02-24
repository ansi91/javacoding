package book.chap08;

public class Tivoli extends Car {

	int carAudio=0;
	String carColor=null;
	//생성자는 전변을 초기화 해준다
	Tivoli(){
		carColor="남색";
	}
	
	public void volumnUp() {
		
		carAudio+=1;
	}
	
	public void volumnDown() {
		
		carAudio-=1;
	}
	
	@Override
	public void stop() {
		System.out.println("youCar stop 호출성공" );
		if(speed>0)
		speed = speed-2;
	}
	
	
}
