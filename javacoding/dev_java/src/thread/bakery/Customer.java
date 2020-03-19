package thread.bakery;

public class Customer extends Thread {

	private BakerStack bs = null;
	
	public Customer(BakerStack bs) { //손님이 빵을 고르기 위해 진열대를 초기화 한다.
		this.bs = bs;
	}
	
	public void run() {
		String bread = null;
		for (int i=0;i<5;i++) {
			
			bread = bs.pop(); // 빵을 꺼내서 가져가는 경우
			System.out.println("손님이 가져가는 빵 " + bread);  
		try {
			sleep(3000); //2초동안 대기.... 빵을 고르는 것이라 생각하면 된다.
		} catch (Exception e) {
			System.out.println("[Exception]" + e.toString());
		}
		
		}
		
	}
	
	
}
