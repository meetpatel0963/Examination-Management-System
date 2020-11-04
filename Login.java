
import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.regex.*;

class Login extends readData {
	public boolean loginsuccess=false;
	static String username,password;
	FileReader fr;
	FileInputStream fin;
	Console console = System.console();
	BufferedReader br;
	File f = new File("E:\\Main.txt");
	boolean bool = false;
	FileWriter fw;
	BufferedWriter bw;
	
	void checkValid() {
		try {	
			fin = new FileInputStream(f);
			br = new BufferedReader(new InputStreamReader(fin));
			String data; 
			int flag=0;
			String[] array;
			data = br.readLine();
			while(data!=null) {
				if(data.equals(username)) {
				data = br.readLine();
				if(data.equals(password)) {
					flag=1;
					break;
					}
				}
				/*array = data.split("\n");
				for(String word : array) {
					if(word.equals(username))
						bool = true;
					if(bool==true) {	
						if(next_word.equals(password)) {
							flag = 1;
						}
					}
				}
				if(bool==true) {
					for(String word : array) {
						if(word.equals(password)) 
							flag=1;
						
						System.out.println(password);
						System.out.println(word);
					}
				}*/
				data = br.readLine();
			}
			if(flag==0)
				System.out.println("\n          Invalid Username or Password!! Please Try Again!! : ");
			else {
				//System.out.println(username);
				loginsuccess=true;
				System.out.println("\n          ---------------------");
				System.out.println("            Login Successful!\n            You are Logged in!");
				System.out.println("          ---------------------");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	void login()
	{
		try {
			readData obj = new Login();
			System.out.println("\n\n          --------------");
			System.out.println("              LOGIN:");
			System.out.println("          --------------\n");
			System.out.print("\n          Enter Username: ");
			username = obj.readName();
			do {
			password = obj.readPassword();
			if(password.equals(""))
				System.out.println("\n          ENTER AGAIN : \n");
			}while(password.equals(""));
			checkValid();
		} catch(Exception e) { 
			e.printStackTrace();
		}
	}
}