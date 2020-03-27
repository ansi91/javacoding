package test;

import wed0212.Bank;
public class Q3 {
	String accountName=null; //예금주
	String accountNumber=null; //계좌번호
	int money=0	; //잔액
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
			public String toString() {
				return String.format("자바맨 : %s%n계좌번호 : %s%n잔액 : %s%n",accountName,accountNumber,money);
			}
	public static void main(String[] args) {
		Q3 q3 = new Q3();
		q3.accountName="자바맨";
		q3.accountNumber="123-456";
		q3.deposit(10000); //만원 입금
		System.out.println("예금주 :" +q3.accountName+"\n"+"계좌번호 :" +q3.accountNumber+"\n "+"잔액: "+q3.money);
		q3.deposit(15000); //15000원 입금
		q3.withDraw(30000); //30000원 출금 잔액 부족해서 출금 실패
		}

}