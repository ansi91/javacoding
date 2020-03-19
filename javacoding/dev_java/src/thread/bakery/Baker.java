package thread.bakery;

public class Baker extends Thread  {
	//빵을 제공해 주는 메소드 구현
	private BakerStack bs= null; //진열대
	
	public Baker(BakerStack bs) { //빵을 진열대에서 가져가거나 진열하기 위해 생성자를 통해 초기화 한다 
		this.bs = bs;
		
	}
	
	public void run() {  
		String bread=null;
		for(int i=0;i<5;i++) {
			bread = getBread();
			bs.push(bread);
			System.out.println("제빵사가 만든 빵 " + bread);
			try {
				sleep(1000);
			} catch (InterruptedException ie) {
				System.out.println(ie.toString());
			}
		}
		 
	}
	
	public String getBread() { 
		
		String bread = null;
		
		switch((int)(Math.random()*3)) { //난수를 발생시켜 임의에 빵을 굽는다.
		case 0:
			bread = "생크림빵";
		break;
		case 1:
			bread = "단팥빵";
		break;
		case 2:
			bread = "마늘빵";
			break;
		}
		
		
		return bread;
	}
	
}
