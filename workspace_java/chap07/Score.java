package book.chap07;
/*
 * 1차배열, 2차배열 - 초기화 하기, 꺼내서 사용하기
 * 배열에 담긴 정보를 꺼낼 수 있다.(반복문과 조건문 활용)
 * 개선된 for문 연습 - 컬렉션 프레임워크
 * 객체배열 따로 정리하기
 * 성적 처리 방법
 * jumsu[i][j]
 * 변수 i는 row수 - 사람구분
 * 변수 j는 column수 -과목 구분
 * 총점 구하기 
 * for문 한개로 가능하다|아니다 [아닐것 같다 2차원 배열이기때문에 [i][j]가 바뀌기 때문에 for문을 2개 써야할거 같다]
 * 만일 아니다 2개일 것이다
 * 만약 강호동의 총점을 구한다면 i가 고정된 상태에서 j가 변해야 한다 | 아니다.  
 * 
 * 총점과 평균 구하기에 배열을 사용할 것인가?
 * 
 * 메소드를 사용할 것인가?
 * 1단계 - main메소드안에서만 코딩
 * 2단계 - 메소드로 꺼내어 보기 : 재사용성과 이식성 높이는 코드를 작성하기
 */

public class Score {
	String[] name = {"김달수","홍길동","이춘향","김갑환","최번개"};
	public static void main(String[] args) {
		Score s = new Score();
		int[][] jumsu = {
				{100, 80, 90},
				{60, 70, 90},
				{80, 70, 70},
				{90, 90, 90},
				{80, 80, 80}
		};
		
		int [] rank=new int[6];
						
		int n=0,total=0,i,j,ktotal=0,etotal=0,mtotal=0,rankNum=1;
		double avg=0;
		System.out.println("     "+"kor "+"eng "+"math " +"total " +"avg  " +"RANK ");
		System.out.println("============================================");
		for(i=0;i<jumsu.length;i++) {
			/*for문에서 증감연산자는 반복문이 진행되는 동안 계속 증감이 일어난다.
			 * for문에 있는 조건문에서 비교할 때 
			 */
			System.out.print(s.name[n]+" ");
			for(j=0;j<jumsu[i].length;j++) {
			
				System.out.print(jumsu[i][j]+" ");
			
				total +=jumsu[i][j];
				avg=(total/3.0);
				}// end of inner 과목 수가 끝나는 위치
			System.out.printf("    "+total+" "+"%.2f  " ,avg);
			rank[n]+=total;
			//String result = (rank[n]>rank[n+1])?"true":"false";
			
		System.out.print(rankNum);
			total=0;
			System.out.println();
			n++; //이름 배열의 인덱스를 증가 0->1
			ktotal+=jumsu[i][0];
			etotal+=jumsu[i][1];
			mtotal+=jumsu[i][2];
			
		}//end of outter
	
		System.out.println("==============================================");
		System.out.print("총점 "+ktotal+" "+etotal+" "+mtotal);
		
	}

}
