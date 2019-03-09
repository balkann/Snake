
import enigma.console.Console;
import enigma.core.Enigma;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
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

public class SnakeMain {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Berkant Alkan Snake
		Console cn = Enigma.getConsole("Game", 90, 45, 15, 0);		
		Snake snake=new Snake();	
		while(true) {
			boolean flag3=false;
			snake.move();	
			while(flag3==false) {
				snake=new Snake();
				snake.reset();
				flag3=true;
				}
			}
		}
		
		
	}

	 



