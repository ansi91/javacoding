package book.chap15;

public class StopThread implements Runnable {
	boolean stoped = false;
	@Override
	public void run() {
		
		while(!stoped) {
			System.out.println("Thread is alive.....");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
					e.printStackTrace();
			}
		}
		System.out.println("Thread is stop......");
	}

	public void stop() {
		stoped = true;
	}
}
