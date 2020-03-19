package book.chap05;

public class Student {

	int studentID;
	String studentName;
	int grade;
	String address;
	
	public String getStudent() {
		
		return studentName;
	}
	
	public static void main(String[] args) {
		
		Student st = new Student();
		st.studentName="이순신"; 				
		
		System.out.println(st.studentName);
		System.out.println(st.getStudent());
		

	}

}
