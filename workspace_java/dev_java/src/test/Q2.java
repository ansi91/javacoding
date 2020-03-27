package test;
public class Q2 {
	public void forCommonmultiple() {
	int sum=0;
		for(int i=0;i<=1000;i++) {
			if(i%10==0) {
				System.out.println("공배수는 패스");
			}else if(i%2==0||i%5==0) {
				sum+=i;
				System.out.println("i="+i+" "+sum);
			}
		}
	}
	public void whileCommonmultiple() {
		int i=0, sum=0;
		while(i<=1000) {
					if(i%10==0) {
					System.out.println("공배수는 패스");
				}else if(i%2==0||i%5==0) {
					sum+=i;
					System.out.println("i="+i+" "+sum);
				}//end of if
					i++;
		}//end of while
	}//end of whileCommonmultiple
	public static void main(String[] args) {
		Q2 q2 = new Q2();
		q2.forCommonmultiple();
		q2.whileCommonmultiple();
	}
}
