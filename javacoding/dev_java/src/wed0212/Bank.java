package wed0212;

public class Bank {

	String accountName="자바사랑"; //예금주
	String accountNumber="123-456-789"; //계좌번호
	int money=1000; //잔액
	
	public int deposit(int inputMoney) {		// 입금

		money+=inputMoney;
		
		System.out.println("입금성공,현재 잔액은 :" + money +"입니다");
		
		return money;
	}
	
	public int withDraw(int outputMoney) { //출금
		
		if(money<outputMoney) {
			System.out.println("출금실패,잔액이 부족합니다 ." +"현재 잔액 " +money+"원");
			return money;
		}
		money-=outputMoney;
		System.out.println("출금성공, 현재 잔액은: " + money + "원 입니다");
		
		return money;
	}
	/*모든 클래스의 상위 클래스가 Object인데 이 클래스에는 toString메소드가 정의되어 있다.
	 * 이 메소드 자식 클래스가 재정의해서 사용할 수 있는데 이를 오버라이딩 이라고 한다.
	 * 리턴값이 String인데 이것을 format형식을 지정하여 출력할 수 있도록 지원하고 있다.
	 * 메소드이름은 format이다.
	 * %s는 문자열 형식을 지원하는 예약어이다.
	 * %n은 개행처리를 지원한다.
	 * %s가 세번 나왔으므로 파라미터도 3개가 되어야 한다.
	 */
		
			public String toString() {
				return String.format("예금주 : %s%n계좌번호 : %s%n잔액 : %s%n",accountName,accountNumber,money);
			}
	
	
	public static void main(String[] args) {
		
		Bank bank = new Bank();
		bank.deposit(15000);
		bank.withDraw(30000);
		System.out.println(bank.toString());
		
	}

}
