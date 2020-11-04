import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.regex.*;

class Password_Masking implements Runnable { 
	private boolean stop;
	public void run() {
		stop = true;
		while(stop) {
			System.out.print("\010*");
			try {
				Thread.currentThread().sleep(1);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void stopMasking() {
		this.stop = false;
	}
	static String readPassword() {
		Password_Masking pm = new Password_Masking();
		Thread mask = new Thread(pm);
		mask.start();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String pass = "";
		try {
			pass = br.readLine();
		} catch(IOException e) {
			e.printStackTrace();
		} 
		pm.stopMasking();
		return pass;
	}
}