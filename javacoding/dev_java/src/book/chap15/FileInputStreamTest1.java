package book.chap15;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamTest1 {

	
	public static void main(String[] args) {
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream("input.txt");
			System.out.println(fis.read());
			System.out.println(fis.read());
			System.out.println(fis.read());
		} catch (Exception e) {
			System.out.println(e.toString());
		}finally {
			try {
				fis.close();
			} catch (IOException e2) {
				System.out.println(e2);
			}catch (NullPointerException e2) {
				System.out.println(e2);
				
			}
		}
		System.out.println("end");
	}
	
}
