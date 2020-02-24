package book.chap07;

public class NameList {

	public static void main(String[] args) {

		String nameList[] = { "이정훈", "전진완", "김혜인", "안형재" };
		String nameList2[][] = { 
											{ "이정훈", "전진완", "김혜인", "안형재" }
										   ,{ "25", "26", "27", "28" } 
																				};
		String nameList3[][] = {
											{ "이정훈", "전진완", "김혜인", "안형재" } 
											,{ "25", "26", "27", "28" }
											,{ "바둑", "웨이크보드", "당구", "사이클" } 
																								};

		for (int i = 0; i < nameList3[2].length; i++) {
			System.out.println(nameList3[2][i]);
		}
		System.out.println("================================");

		for(int i=0; i<nameList3.length;i++) {
			for(int j=0; j<nameList3[i].length;j++) {
				if(i==2) {
					System.out.println(nameList3[0][j]+" 친구의 취미 =" +nameList3[i][j]);
				}
			}
		}
		
		
	
	}
}
