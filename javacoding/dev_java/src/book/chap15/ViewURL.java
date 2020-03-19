package book.chap15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;



public class ViewURL {

	
	public ViewURL() {}
	public ViewURL(String strURL) {
		URL myURL = null;
		URLConnection urlCon = null;
		InputStream is = null;
		BufferedReader br = null;
		String data = null;
		String headerType = null;
		
		try {
			myURL = new URL(strURL);
			urlCon = myURL.openConnection();
			urlCon.connect();
			headerType = urlCon.getContentType();
			is = urlCon.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			java.io.File f = new File("src\\book\\chap15\\google_source.txt");
			FileOutputStream fos = new FileOutputStream(f,true); // true는 기존에 텍스트 파일에 내용을 연결해서 써준다.
			
			while((data=br.readLine())!=null) {
				fos.write(data.getBytes());
			}
			fos.flush();
			fos.close();
		} catch (Exception e) {
			System.out.println("Exception" + e.toString());
		}
	}
	
	public static void main(String[] args) {
		//new ViewURL("http://localhost:8000/index.jsp");
		new ViewURL("http://www.google.com/index.html");
	}

}
