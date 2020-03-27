package book.chap15;

public class StopThread2 implements Runnable {
	
	@Override
	public void run() {
		
		while(!Thread.currentThread().isInterrupted()) {
			System.out.println("Thread is alive.....");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
					e.printStackTrace();
			}finally {
				System.out.println("Thread is alive");
			}
		}
		System.out.println("Thread is stop......");
	}

	
}
