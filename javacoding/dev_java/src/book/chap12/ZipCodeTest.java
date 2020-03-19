package book.chap12;

import java.util.Vector;

import com.google.gson.Gson;

import oracle.jdbc.ZipCodeSearchApp;
import oracle.jdbc.ZipCodeVO;

public class ZipCodeTest {

	public void gsonFormat(Vector<ZipCodeVO> v) {
		Gson g = new Gson();
		String temp = g.toJson(v);
		System.out.println(temp);
	}
	
	public static void main(String[] args) {
		ZipCodeTest zct = new ZipCodeTest();
		
		
		ZipCodeSearchApp zipApp = new ZipCodeSearchApp();
		Vector<ZipCodeVO> v = zipApp.refreshData(null,"가산동");
		zct.gsonFormat(v);
		System.out.println(v.size());
		for(int i=0; i<v.size();i++) {
			ZipCodeVO zcVO =v.get(i);
			System.out.println(zcVO.getAddress());
			
		}
	}

}
