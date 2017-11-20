package default_package;
import java.util.Scanner;



public class fordemo1 {

	public static void main(String[] args) {
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print(i+"*"+j+"="+(i*j)+"   ");
			}
			System.out.println();
		}
		System.out.println();
		int sum =0;
		for (int i = 1; i<=9; i++) {
			int i1=(int)Math.pow(10,i)-1;
			System.out.print(i1);
			if (i==9) {
				System.out.print("=");
			}
			else {
				System.out.print("+");
			}
			 sum=i1+sum;
		}
		System.out.println(sum);
		
		
		System.out.println();
		System.out.println("please input a int:");
		Scanner scanner=new Scanner(System.in) ;
		double in=scanner.nextInt();
		double summ=0;
			for (int i = 1; i <=in; i=i+1) {
				System.out.print("1/"+i);
				double lll=1/(double)i;
				summ=summ+lll;
				if (i==in) {
					System.out.print("=");
				}
				else {
					System.out.print("+");
				}
			}
			
		System.out.println(summ);
	}

}
