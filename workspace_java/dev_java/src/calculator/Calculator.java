package calculator;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Calculator extends JFrame{

	JTextField jtf_input = new JTextField();
	String names[] = {"C","/","*","-","7","8","9","+","4","5","6","=","1","2","3","0"}; //각종 버튼을 담을 배열
	/********************************************
	 * @number 숫자를 눌렀을때 담을 변수
	 * @total 	  연산을 할 때 마다 결과를 더하거나 뺄때 사용 하는 사용하는 변수 2자리 수 이상
	 * @oper     연산의 종류를 담는 변수  [0 +],[1 -], [2 *],[3 /]
	 * 
	 * 이 예제를 하면서 배운점
	 * 배열을 통해 버튼을 생성하고 반복문으로 배치 할 수 있다.
	 * 
	 *  
	 ***********************************************/
	int number , total , oper;
	
	//생성자에서 화면을 그리는 메소드 호출
	public Calculator () {
		initDisplay();
	}
	
	public void initDisplay() {
		BorderLayout layout = new BorderLayout(2,2);
		setLayout(layout);
		jtf_input = new JTextField("",SwingConstants.CENTER); //SwingConstants.CENTER는 jtf를 가운데 정렬 하겠다는 뜻
		jtf_input.setHorizontalAlignment(JTextField.RIGHT); //입력창에 보이는 숫자는 오른쪽 정렬
		jtf_input.setFont(new Font(Font.SERIF,Font.BOLD,40));
		jtf_input.setBounds(0,0,450,50);
		
		JPanel jpad = new JPanel(new GridLayout(4,4)); //숫자와 연산 버튼을 배치 하기 위해 GridLayout4,4로 생성
		
		
		JButton buttons[] = new JButton[names.length]; //버튼을 배열의 크기만큼 생성한다 
		for(int i=0; i<names.length; i++) { //반복문을 통해 배열에서 선언한 숫자와 연산을 배치한다 
			buttons[i] = new JButton(names[i]);
			buttons[i].setFont(new Font(Font.SERIF,Font.BOLD,20));
			jpad.add(buttons[i]);
			PadInput handler = new PadInput(); //PadInput이라는 클래스를 누릴수 있게 인스턴스화 한다
			buttons[i].addActionListener(handler); //버튼을 눌렀을때 이벤트가 동작할 수 있도록 addActionListener를 달아준다
		  }  
		  
		  add(jtf_input, BorderLayout.NORTH);
		  add(jpad, BorderLayout.CENTER);

		    number = 0;
		    total = 0;
		    oper = 0; 
		  
		 
		
		
		this.setTitle("자바 계산기");
		this.setSize(600, 500);
		this.setVisible(true);
	}
	
	private class PadInput implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//클릭한 버튼을 감지해서 eventText에 넣어준다.
			  String eventText = e.getActionCommand();

			   if(eventText.equals("C")) //초기화
			   {
			    jtf_input.setText("");
			    number = 0;
			    total = 0;
			   
			   } 
			   else if(eventText.equals("+")){ //+
			    jtf_input.setText("");
			    total += number;
			    oper=0;   
			   }
			   else if(eventText.equals("-")){ //-
			     jtf_input.setText("");
			     total += number;
			     oper = 1;
			   } 

			   else if(eventText.equals("*")){ //*
			    jtf_input.setText("");
			    total += number;
			    oper=2;   
			   }
			   else if(eventText.equals("/")){ // /
			     jtf_input.setText("");
			     total += number;
			     oper = 3;
			   } 
			   else if(eventText.equals("="))
			   {
			      if(oper==0){
			      total += number;
			      jtf_input.setText(""+total);
			      number = 0;
			    
			      }
			      else if(oper==1){
			      total -= number;
			      jtf_input.setText(""+total);
			      number = 0;
			    
			      }
			      else if(oper==2){
			      total *= number;
			      jtf_input.setText(""+total);
			      number = 0;
			    
			      }
			      else if(oper==3){
			      total /= number;
			      jtf_input.setText(""+total);
			      number = 0;
			     
			      }
			   
			   }
			   else 
			   {
				   //클릭한 숫자를 텍스트필드에 보여주기 위한 코드 
			   String  c_num = jtf_input.getText()+e.getActionCommand();
			   jtf_input.setText(c_num);
			   number = Integer.parseInt(c_num);
			  
			   }  
			}
			    
			}


			 public static void main(String args[])
			 {
			  Calculator cal = new Calculator(); 
			  
			  cal.setSize(300, 400); 
			  cal.setResizable(false);
			  cal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			  cal.setVisible(true);
			 }
			} 
	


