package book.chap15;

public class StopThreadTest {
	public void process() {
		StopThread st = new StopThread();
		Thread th = new Thread(st); //자동차->네비게이션
		th.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			}
		st.stop();
		}
		//16-17-18-4-5(Thread)-6-7(호출) -Thread is alive ....
		//[StopThread]11- 13-[StopThread]19 - Thread is stop.....
	public static void main(String[] args) {
		StopThreadTest stt = new StopThreadTest();
		stt.process();
		

	}

}
