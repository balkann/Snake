import enigma.console.Console;
import enigma.core.Enigma;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class Snake {
	Console cn = Enigma.getConsole("Game", 30, 30, 15, 0);
	private int velocityX,velocityY,x,y,highestPoint;
	public KeyListener klis;
	private DoubleNode head;
	private DoubleNode tail;
	private int size;
	private Boolean flag,flag2;
	// ------ Standard variables for mouse and keyboard ------
	public int mousepr;          // mouse pressed?
	public int mousex, mousey;   // mouse text coords.
	public int keypr;   // key pressed?
	public int rkey;    // key   (for press/release)
	// ----------------------------------------------------

	
	public Snake() {
		int x=0, y=0;
		flag=false;
		size=0;
		flag2=true;
		add(3,3);
		add(4,3);
		add(5,3);
		this.velocityX=-1;
		this.velocityY=0;						
		klis=new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				if(keypr==0) {
					keypr=1;
					rkey=e.getKeyCode();
				}
			}
			public void keyReleased(KeyEvent e) {}
		};
		cn.getTextWindow().addKeyListener(klis);
	}
	public DoubleNode getHead() {
		return head;
	}
	

	public void setHead(DoubleNode head) {
		this.head = head;
	}

	public DoubleNode getTail() {
		return tail;
	}

	public void setTail(DoubleNode tail) {
		this.tail = tail;
	}
	public int getHighestPoint() {
		return highestPoint;
	}
	public void setHighestPoint(int highestPoint) {
		this.highestPoint = highestPoint;
	}
	public void add(int x,int y) {
		DoubleNode dn=new DoubleNode(x,y);
		if(head==null&&tail==null) {
			head=dn;
			tail=dn;
			size++;
		}
		else {
			tail.setNext(dn);
			dn.setPrev(tail);
			tail=dn;
			size++;
		}		
	}
	public void highestScore() throws IOException  {
		BufferedReader bufferedReader = null;
		 String line;
		 File file=new File("highest.txt");
		 FileReader fileReader =new FileReader(file);
		 bufferedReader=new BufferedReader(fileReader);				//YUKSEK PUAN OKUMA
		 line = bufferedReader.readLine();
	     this.highestPoint=Integer.parseInt(line);
	     cn.getTextWindow().setCursorPosition(8, 16);
		 System.out.println("Highest Point: "+highestPoint);
		 fileReader.close();
		 bufferedReader.close();
		 flag2=true;
	}
	public void reset()throws InterruptedException, IOException {
		boolean flag4=true;
		while(flag4==true) {
			//Scanner scn=new Scanner(System.in);
			cn.getTextWindow().setCursorPosition(9, 9);
			System.out.println("Press R to Restart");
			
			if(keypr==1) {
				if(rkey==KeyEvent.VK_R)
					flag4=false;
				
				keypr=0;
			}
			//String str=scn.next();	
			//if(str.equals("r")) {
			//flag4=false;
			//scn.close();
		//}
		}
	}
	public void move() throws InterruptedException, IOException {
		flag2=true;
		while(this.flag2==true) {
			clear();
			if(keypr==1) {    // IF KEYBOARD BUTTON PRESSED
				if(rkey==KeyEvent.VK_LEFT && this.velocityY!=1 ) {//sol
					this.velocityY=-1;
					this.velocityX=0;
				}
				else if(rkey==KeyEvent.VK_RIGHT && this.velocityY!=-1) {
					this.velocityY=1;
					this.velocityX=0;
				}
				else if(rkey==KeyEvent.VK_UP&&this.velocityX!=1 ) {   //KLAVYE YONETIMI
					this.velocityX=-1;
					this.velocityY=0;
				}
				else if(rkey==KeyEvent.VK_DOWN&&this.velocityX!=-1) {
					this.velocityX=1;
					this.velocityY=0;
				}
				keypr=0;    // LAST ACTION
			}
			DoubleNode temp =head;
				if(head.getX()==0) {
					head.setX(13);
				}
				else if(head.getX()==14) {
					head.setX(1);
				}													//KENARLARA CARPINCA DIGER TARAFTAN CIKMA
				else if(head.getY()==28) {
					head.setY(1);
				}

				else if(head.getY()==0) {
					head.setY(27);
				}
				apple();
			temp=tail;
			while(temp.getPrev()!=null) {
				temp.setX(temp.getPrev().getX());temp.setY(temp.getPrev().getY());temp=temp.getPrev();					//HAREKET
			}			
			head.setX(head.getX()+velocityX);
			head.setY(head.getY()+velocityY);
			
			update();
			if(velocityY!=0)
			{
				Thread.sleep(150);
				}
			else {																//ZAMANLAMA
				Thread.sleep(220);	
			}
		}
		
	}
	public void apple() {
		Random rnd=new Random();
		while(flag==false) {
			this.flag=true;
			 x=rnd.nextInt(13)+1;
			 y =rnd.nextInt(26)+1;
			 DoubleNode temp=head;
			 while(temp!=null) {
				 if(temp.getX()==x&&temp.getY()==y) {												//ELMA URETME
					 flag=false;
				 }
				 temp=temp.getNext();
			 }
		}
		if(head.getX()==x && head.getY()==y) {
			flag=false;																//YEDIYSE			
			add(tail.getX(),tail.getY());
		}
		cn.getTextWindow().setCursorPosition(y, x);
		System.out.print("+");
	}
	
	
	public void update() throws IOException {
		//map();
		highestScore();
		DoubleNode temp2=head;
		while(temp2!=null) {
			cn.getTextWindow().setCursorPosition(temp2.getY(), temp2.getX());
			System.out.println("#");												//YILANI YAZDIRMA				
			temp2=temp2.getNext();
		}
		cn.getTextWindow().setCursorPosition(8, 15);
		System.out.println("Score: "+(size-3));
		for(int i=0;i<14;i++) {
			cn.getTextWindow().setCursorPosition(0, i);
			System.out.print(0);
		}
		for(int i=0;i<14;i++) {
			cn.getTextWindow().setCursorPosition(28, i);
			System.out.print(0);
		}																			//MAP OLUSTURMA
		
		for(int i=0;i<28;i++) {
			cn.getTextWindow().setCursorPosition(i, 0);
			System.out.print(0);
		}
		for(int i=0;i<28;i++) {
			cn.getTextWindow().setCursorPosition(i,14 );
			System.out.print(0);
		}
		DoubleNode temp=head.getNext();
		while(temp!=null) {
			if(head.getX()==temp.getX()&&head.getY()==temp.getY()) {   //KENDINI YEDIYSEN OYUNU BITIR
				clear();
				if(size-3>highestPoint) {
					List<String> lines = new ArrayList<String>();
					File file=new File("highest.txt");
					FileWriter fw = new FileWriter(file);				//HIGHEST SCORE GUNCELLEME
		            BufferedWriter out = new BufferedWriter(fw);
		            String str=Integer.toString(size-3);
		            for(String s:lines)
		                 out.write(s);
		            out.write(str);
		            out.flush();
		            out.close();
		            
				}
				this.flag2=false;			
				cn.getTextWindow().setCursorPosition(8,8);
				System.out.print("YOU LOST SCORE= "+(size-3));
					
			}
			temp=temp.getNext();
		}
	}
	public void clear() {
		for(int i=0;i<40;i++) {
			for(int k=0;k<21;k++) {
				cn.getTextWindow().setCursorPosition(i, k);
				System.out.print(" ");
			}
		}
	}

	
	
	

}
