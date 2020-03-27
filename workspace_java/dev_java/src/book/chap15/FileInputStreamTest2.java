package book.chap15;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStreamTest2 {

	public static void main(String[] args) {
		try(FileInputStream fis = new FileInputStream("src\\book\\chap15\\input.txt")){
			int i;
			while((i=fis.read())!=-1) { //-1이면 파일이 끝이라는 뜻
				System.out.println((char)i);
			}
			System.out.println("end");
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}

	}
}
