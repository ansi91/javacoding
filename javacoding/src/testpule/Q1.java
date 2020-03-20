package testpule;

import java.util.Calendar;

public class Q1 {

	
	
	public static void main(String[] args) {
		int year=0;
		year = 1991;
		int age=0;
		int currentyear =0;
		int currentmonth=0;
		Calendar cal = Calendar.getInstance();
		currentyear =cal.get(Calendar.YEAR);
		age = currentyear - year;
		currentmonth = cal.get(Calendar.MONTH);
		System.out.println("age: "+ age);
		System.out.println("year: " + year);
	}

}
