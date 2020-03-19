package book.chap15;

public class StopThreadTest2 {
	public void process() {
		StopThread2 st = new StopThread2();
		Thread th = new Thread(st); //자동차->네비게이션
		th.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			}
			th.interrupt();
		}
	
	public static void main(String[] args) {
		StopThreadTest2 stt = new StopThreadTest2();
		stt.process();
		

	}

}
