package test;

import java.util.Calendar;

public class GetAgeYear {


	int birth_year=1991; //태어난 연도
	int age=0;				// 나이를 저장할 변수
	
	public int getAge(int birthYear, int birthMonth, int birthDay) //매개변수로 연,달, 일 을 받는다
	{
	        Calendar current = Calendar.getInstance(); //캘린더의 객체를 생성
	        int currentYear  = current.get(Calendar.YEAR);          //현재의 연도 구하기
	        int currentMonth = current.get(Calendar.MONTH) + 1;  
	        int currentDay   = current.get(Calendar.DAY_OF_MONTH);
	       
	        int age = currentYear - birthYear; //현재 연도에서 태어난 연도를 빼준다
	        // 생일 안 지난 경우 -1
	        if (birthMonth * 100 + birthDay > currentMonth * 100 + currentDay)  
	            age--;
	        this.age = age;
	        return age;

	}
	
	public static void main(String[] args) {

		GetAgeYear agy = new GetAgeYear();
		int result = agy.getAge(agy.birth_year, 1, 8);
		
		System.out.println("age :"+result+" "+"year :"+agy.birth_year);
		
	}

}
