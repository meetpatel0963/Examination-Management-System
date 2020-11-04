import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.regex.*;

public class Main {
	static BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
	static Scanner sc = new Scanner(System.in);
	static void printData() {
		try {
			FileReader fr = new FileReader("E:\\Main.txt");
			int i;
			System.out.println();
			while((i=fr.read())!=-1) {
				System.out.print((char)i);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Account l;
		ArrayList <String> u=new ArrayList<String>();
		ArrayList <Integer> m=new ArrayList<Integer>();
		int choice,flag=0;
		String Achoice="";
		System.out.println("\n                                                                                 ---------------------------");
		System.out.println("                                                                                  WELCOME TO THE OOP TEST : ");
		System.out.println("                                                                                 ---------------------------");
		System.out.print("\n          1.Sign Up\n          2.Login\n          3.Exit\n\n          Enter Your Choice:");
		choice = sc.nextInt();
		while(choice!=3) {
		switch(choice) {
			case 1: Sign_up s=new Sign_up();
					s.sign_up();
					System.out.println("          --------------------");
					System.out.println("          Sign Up Successful!");
					System.out.println("          --------------------\n");
					break;
			case 2: 
					Login a=new Login();
					do
					{
						a.login();
						//System.out.println(a.username);
						u.add(a.username);
						if(!a.loginsuccess)
							System.out.print("\n    	  LOGIN Unsuccessful : ");
					}while(!a.loginsuccess);
					if(a.loginsuccess)
					{
					do {
						System.out.println("\n\n          -----------------");
						System.out.println("            YOUR ACCOUNT:");
						System.out.println("          -----------------");
						try{
						do {
						System.out.print("\n          1.Attempt Test\n          2.Review Test\n          3.LeaderBoard\n          4.Logout\n\n          Enter Your Choice : ");
						Achoice = sc.next();
						if(Integer.parseInt(Achoice)<48 && Integer.parseInt(Achoice)>57)
							System.out.println("Please Enter Valid Choice!");
						}while(Integer.parseInt(Achoice)<48 && Integer.parseInt(Achoice)>57);
						}
						catch(Exception e)
						{
							System.out.println("\n          Please Enter Valid Choice!");
						}
						switch(Achoice) {
							case "1": if(Account.appear_test )
									{
										System.out.println("          OOPS! YOU CANNOT APPEAR IN THE TEST MORE THAN ONCE! : ");
										break;
									}
									l=new Account();
									//System.out.println(l.marks);
									m.add(l.marks);
									flag=1;
									break;
							case "2": 
									if(Account.appear_test)
									{
										System.out.println('\n');
										System.out.println("             --------------");
										System.out.println("              TEST REVIEW:");
										System.out.println("             --------------\n");
										new ReviewTest();
										//rt.readFile();
										break;
									}
									else
									{
										System.out.println("\n          You can't REVIEW your test without attempting it : ");
										break;
									}
							case "3": new leader();
									break;
							case "4": 
								System.out.println("\n          Logout Successful : ");
								break;
						}
					}while(!Achoice.equals("4"));
					break;
					}	
			case 3: System.exit(0); 
			default: System.out.println("          Invalid Choice!\n          Please Enter Valid Choice!");
			}
			System.out.println("\n                                                                                 ---------------------------");
			System.out.println("                                                                                  WELCOME TO THE OOP TEST : ");
			System.out.println("                                                                                 ---------------------------");
			System.out.print("\n          1.Sign up\n          2.Login\n          3.Exit\n\n          Enter Your Choice:");
			choice = sc.nextInt();
		}
	}
}