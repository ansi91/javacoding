package book.chap12;

//조인시에 VO클래스 설계는 어떻게 가져가야 할까요?
public class TdeptVO {

private String  DEPT_CODE    =null;
private String  DEPT_NAME    =null;
private String  PARENT_DEPT  =null;
private String  USE_YN       =null;
private String  AREA         =null;
private int  BOSS_ID      =0;

public String getDEPT_CODE() {
	return DEPT_CODE;
}
public void setDEPT_CODE(String dEPT_CODE) {
	DEPT_CODE = dEPT_CODE;
}
public String getDEPT_NAME() {
	return DEPT_NAME;
}
public void setDEPT_NAME(String dEPT_NAME) {
	DEPT_NAME = dEPT_NAME;
}
public String getPARENT_DEPT() {
	return PARENT_DEPT;
}
public void setPARENT_DEPT(String pARENT_DEPT) {
	PARENT_DEPT = pARENT_DEPT;
}
public String getUSE_YN() {
	return USE_YN;
}
public void setUSE_YN(String uSE_YN) {
	USE_YN = uSE_YN;
}
public String getAREA() {
	return AREA;
}
public void setAREA(String aREA) {
	AREA = aREA;
}
public int getBOSS_ID() {
	return BOSS_ID;
}
public void setBOSS_ID(int bOSS_ID) {
	BOSS_ID = bOSS_ID;
}
	
}
