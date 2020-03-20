package book.chap15;
/*
 * 상황에 따라 분리된 스레드로 백그라운드 작업을 해야 하는 경우가 있다.
 * JVM안에 가비지 컬렉션과 같은 작업이 대표적이다.
 * 이런 백그라운드 작업이 일반 스레드로 설정되어 있다면 
 * 전원이 종료되거나 사용자가 강제 종료 하지 않는 한 어플리케이션은 영원히 정지 하지 않을것이다.
 * 
 * 
 * 
 */
public class DaemonThreadTest {

	public static void main(String[] args) {

		Thread th = new Thread() {
			public void run() {
				try {
					//5초가 될때까지 NormalThreadTest객체는 종료 되지 않는다.
					Thread.sleep(5000); // 5초 정지
					System.out.println("Inner Thread finish...");
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
			}
		};
		th.setDaemon(true);
		th.start();
		System.out.println("메인 종료");
	}

}
