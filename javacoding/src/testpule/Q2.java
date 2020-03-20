package testpule;

public class Q2 {

	public static void main(String[] args) {
		int dap1=0, dap2=0;
		int i=0;
		for(i=1;i<1000;i++) {
			if(i%2==0 && i%5==0) {
				continue;
			}else if(i%2==0||i%5==0) {
				dap1=dap1+i;
			}
		}
		i=1;
		while(i<=1000) {
			if(i%2==0 && i%5==0) {
				continue;
			}else if(i%2==0||i%5==0) {
				dap2=dap2+i;
			}
			i++;
		}
		
	}

}
