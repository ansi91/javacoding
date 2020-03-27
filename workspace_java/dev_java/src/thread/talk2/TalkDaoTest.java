package thread.talk2;

public class TalkDaoTest {

	public static void main(String[] args) {

			TalkDao td = new TalkDao();
		String result=	td.login("apple", "123");
		
		System.out.println(result);
		
	}

}
