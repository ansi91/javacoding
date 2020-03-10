package thread.talk;

public class TalkServerThread extends Thread {
	TalkServer ts = null;
	public TalkServerThread(TalkServer ts) {
		this.ts = ts;
	}
	
	public void run() {
		boolean isStop = false;
		//while(true){//무한루프에 빠질 수 있따.
		
		while(!isStop) {
			int protocol = 0;
			switch(protocol) {
			case Protocol._LOGIN:{
			}break;
			case  Protocol._EXIT:{
			}break;
			
			}//end of switch
		}// end of while
	}
}
