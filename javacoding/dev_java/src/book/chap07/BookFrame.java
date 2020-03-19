package book.chap07;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BookFrame extends JFrame{

	String cols[]= {"도서명", "저자"};
	String data[][] = new String[0][2];
	DefaultTableModel dtm_book = new DefaultTableModel(data,cols);
	JTable jtb_book  = new JTable(dtm_book);
	JScrollPane jsp_book = new JScrollPane(jtb_book);
	
	public BookFrame() { // 생성자
		ArrayList<Book1> library =  new ArrayList<Book1>();
		Book1 b1 = new Book1();
		b1.b_title="태백산맥";
		b1.b_author="조정래";
		library.add(b1); 
		b1 = null;
		b1 = new Book1();
		b1.b_title="어린왕자";
		b1.b_author="생텍쥐베리";
		library.add(b1);
		System.out.println("size : "+ library.size()); //서랍의 단수2
		
		Vector<String> v = new Vector<String>();
		v.add(library.get(0).b_author);
		v.add(library.get(0).b_title);
		dtm_book.addRow(v);
		//ArrayList<String> 이면 값이 넘어고 al.get(0)
		//ArrayList<Book>이면 주소번지가 넘어온다 al.get(0).b_author
		//벡터를 다시 인스턴스화 한 이유는 서랍에 각각 담기 위해서 했다.
		v = new Vector<String>(); 
		v.add(library.get(1).b_author);
		v.add(library.get(1).b_title);
		dtm_book.addRow(v);
		this.add("Center", jsp_book);
		this.setSize(600, 300);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {

		new BookFrame();
		
	}

}
