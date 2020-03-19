package testpule;

public class Q3 extends Object{

	//변수선언 - 고유명사 - 그 이름으로 클래스가 연상되는 그런아이들 - 전역변수
	String name = null; //예금주
	String account = null;//계좌번호
	int cash = 0;//잔액
	
	//디폴트 생성자
	Q3(){
		
	}
	
	Q3(String name, String account, int cash){
		this.name = name;
		this.account = account;
		this.cash = cash;
	}
		
	/*메소드 선언 - 리턴타입과 파라미터 
	DB연동하기 - select, insert , update ,delete 무조건 먼저 작성해보기 
	쿼리문 안에는 ?가 있다 - 사용자가 입력하는 값을 넣어줄 곳 -파라미터
	처리 결과를 사용자에게 응답해야 한다. 리턴타입, 리턴값
	원래는 부모가 가진 메소드 이므로 별도로 정의하지 않아도 호출할 수 있다
	그러나 추가로 작성하고 싶은 내용이 있다면 언제든지 재정의할 수 있다.
	재정의 할때 반환값을 문자열로 바꾸었으므로 주소번지 대신 
	"나는 테스트 클래스 입니다를 출력한다"
	설계한 클래스의 정보를 출력할 때 많이 활용 한다
		또한 UI/UX 화면단을 구현해주는 외부 클래스에도 적용할 수 있다.
	*/
	@Override
	public String toString() {//아빠가 가진 기능을 재정의 하였다.
		String accountINFO= "예금주는 "+name+ "이고, 계좌번호는 "+account+ "잔액은 "+cash+"입니다";
		return accountINFO;
	}
	
	public void print() {
		System.out.printf("예금주는 %s 이고, 계좌번호는 %s 잔액은 %d 입니다",name,account,cash);
	}
	
	public void deposit(int money) {//입금하기
		cash += money;
		System.out.println("현재 잔액 :" + cash +"원 입니다.");
	}
	public void take(int money) { //얼마를 출금 할거니? 파라미터
		if(cash>=money) {
				cash-=money;
		}else if(cash<money){
			System.out.println("잔액이 부족합니다");
			return;
		}
	}
	
}
