package com.vo;
/*
 * private<protected<friendly[코딩하지 않으면 friendly상태]<public
 * this. 를 붙이면 전역변수를 가르키는거고 없으면 메소드의 파라미터를 가르킨다.
 VO[Value Object]  pattern
 getter메소드 - 읽기
 setter메소드 - 저장, 쓰기, 기억,초기화  (메소드 이름은 카멜표기법 국룰임)
                [매개변수]
                
  인스턴스화를 해야만 사용할 수 있으니까...
  인스턴스화가 초기화 되면 그 값은 사라지니까 변조할 수 없다
 */


public class ZipCodeVO {
	//cnt + shift + y 소문자로 변환 
private int uid_no  =0;//
private int zipcode =0;//
private String zdo     ="";//
private String sigu    ="";//
private String dong    ="";//
private String ri      ="";//
private String bunji   ="";//
private String aptname ="";//
private String upd_date="";//
private String address ="";//
public int getUid_no() {
	return uid_no;
}
//
public void setUid_no(int uid_no) {
	this.uid_no = uid_no;
}
public int getZipcode() {
	return zipcode;
}
public void setZipcode(int zipcode) {
	this.zipcode = zipcode;
}
public String getZdo() {
	return zdo;
}
public void setZdo(String zdo) {
	this.zdo = zdo;
}
public String getSigu() {
	return sigu;
}
public void setSigu(String sigu) {
	this.sigu = sigu;
}
public String getDong() {
	return dong;
}
public void setDong(String dong) {
	this.dong = dong;
}
public String getRi() {
	return ri;
}
public void setRi(String ri) {
	this.ri = ri;
}
public String getBunji() {
	return bunji;
}
public void setBunji(String bunji) {
	this.bunji = bunji;
}
public String getAptname() {
	return aptname;
}
public void setAptname(String aptname) {
	this.aptname = aptname;
}
public String getUpd_date() {
	return upd_date;
}
public void setUpd_date(String upd_date) {
	this.upd_date = upd_date;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}

}
