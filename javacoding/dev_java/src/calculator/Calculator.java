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
	String names[] = {"C","/","*","-","7","8","9","+","4","5","6","=","1","2","3","0"};
	int number, total , oper;
	public Calculator () {
		initDisplay();
	}
	
	public void initDisplay() {
		BorderLayout layout = new BorderLayout(2,2);
		setLayout(layout);
		jtf_input = new JTextField("",SwingConstants.CENTER); //
		jtf_input.setHorizontalAlignment(JTextField.RIGHT); //입력창에 보이는 숫자는 오른쪽 정렬
		jtf_input.setFont(new Font(Font.SERIF,Font.BOLD,40));
		jtf_input.setBounds(0,0,450,50);
		
		JPanel jpad = new JPanel(new GridLayout(4,4));
		JButton buttons[] = new JButton[names.length];
		
		
		for(int i=0; i<names.length; i++) {
			buttons[i] = new JButton(names[i]);
			buttons[i].setFont(new Font(Font.SERIF,Font.BOLD,20));
			jpad.add(buttons[i]);
			PadInput handler = new PadInput();
			buttons[i].addActionListener(handler);
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
			  String eventText = e.getActionCommand();

			   if(eventText.equals("C"))
			   {
			    jtf_input.setText("");
			    number = 0;
			    total = 0;
			   
			   } 
			   else if(eventText.equals("+")){
			    jtf_input.setText("");
			    total += number;
			    oper=0;   
			   }
			   else if(eventText.equals("-")){
			     jtf_input.setText("");
			     total += number;
			     oper = 1;
			   } 

			   else if(eventText.equals("*")){
			    jtf_input.setText("");
			    total += number;
			    oper=2;   
			   }
			   else if(eventText.equals("/")){
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
	


