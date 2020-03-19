package method.temperature;
/*
 * VO(value object) 생성하기-자바와 오라클 사이에서 인터페이스 역할을 하게 됨.
 * 오라클과 자바는 데이터 타입이 서로 다르다.
 * 그런데 자료는 서로 공유해야 한다.
 * 그럴 때 VO패턴을 통해서 값을 주고 받고자 한다.
 */
public class SeoulTempVO {
	  private String ddate  		=null;// 
	  private int loc     			=0;// 
	  private double avg_tem =0.0;// 
	  private double min_tem =0.0;// 
	  private double max_tem =0.0;// 
	public String getDdate() {
		return ddate;
	}
	public void setDdate(String ddate) {
		this.ddate = ddate;
	}
	public double getLoc() {
		return loc;
	}
	public void setLoc(int loc) {
		this.loc = loc;
	}
	public double getAvg_tem() {
		return avg_tem;
	}
	public void setAvg_tem(double avg_tem) {
		this.avg_tem = avg_tem;
	}
	public double getMin_tem() {
		return min_tem;
	}
	public void setMin_tem(double min_tem) {
		this.min_tem = min_tem;
	}
	public double getMax_tem() {
		return max_tem;
	}
	public void setMax_tem(double max_tem) {
		this.max_tem = max_tem;
	}
	  
}

