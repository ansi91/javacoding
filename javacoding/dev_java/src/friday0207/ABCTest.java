package friday0207;
//클래스 A,B,C에는 main메소드가 없다.
class A{
	String name=null;
	//B b = new B(); //#15 B클래스 기본 생성자 호출  주석제거하면 A랑B왔다갔다 하면서 무한반복 때문에 StackOverFlow error 발생
	
	A(){ //디폴트 생성자
		System.out.println("디폴트 A생성자");
	}
	A(ABCTest abc){
		System.out.println("파라미터가 ABCTest타입인 A생성자");
	}
	A(String name){ 
		this.name = name;
	}
}

class B{
	
	A a = new A();
	
	B(){
		System.out.println("디폴트 B생성자");
	}
}

class C{
	A a = new A();
	B b = new B();
}
public class ABCTest {
//클래스 안에 디폴트 생성자를 생략할 수도 있고 명시적으로 선언 할 수도 있다.
	ABCTest(){
		A a1 = new A(this); //this->ABCTest
	}
	
	//main->#30 ->#15 B디폴트생성자 ->#7A생성자 출력
	public static void main(String[] args) {
	
		A a1 = new A("이순신");//디폴트 A생성자 호출 그리고 heap메모리에 로딩됨
		System.out.println(a1.name);
		
		
	}
}
