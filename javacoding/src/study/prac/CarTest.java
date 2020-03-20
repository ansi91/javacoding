package study.prac;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CarTest extends JFrame{

	String cols[] = {"차 색깔", "차 이름", "차 가격"};
	String data[][] = new String[0][2];
	DefaultTableModel dtm_car  = new DefaultTableModel(data, cols);
	JTable jtb_car = new JTable(dtm_car);
	JScrollPane jsp_car = new JScrollPane(jtb_car);
	
	
	
	CarTest(){
		ArrayList<Car> garage = new ArrayList<Car>();
		Car car = new Car();
		car.carColor="white";
		car.carName="티볼리";
		car.carPrice="25,000,000";
		garage.add(car);
		
		car = null;
		car = new Car();
		car.carColor="blue";
		car.carName="포르쉐 파라메라";
		car.carPrice="100,000,000";
		garage.add(car);		
		Vector<String> v = new Vector<String>();
		v.add(garage.get(0).carColor);
		v.add(garage.get(0).carName);
		v.add(garage.get(0).carPrice);
		dtm_car.addRow(v);
		v=null;
		v = new Vector<String>();
		v.add(garage.get(1).carColor);
		v.add(garage.get(1).carName);
		v.add(garage.get(1).carPrice);
		dtm_car.addRow(v);
		
		this.add("Center",jsp_car);
		this.setSize(600, 600);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new CarTest();
	}

}
