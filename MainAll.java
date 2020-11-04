import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.regex.*;
class MyException extends Exception 
{
	String error;
	MyException(String str) {
		error=str;
	}
	public String toString()
	{
		if(error.equals("Capital") || error.equals("Small"))
			return "          Your Password should contain atleast one "+error+ " ALPHABET : \n";
		if(error.equals("Special"))
			return "          Your Password should contain atleast one "+error+ " Character : \n";
		if(error.equals("Space"))
			return "          Your Password should not contain Space Character : \n";
		if(error.equals("Length")) 
			return "          Length of Your Password must be greater than or equals to 8 : \n";
		return "";
	}
}
class readData {
	private String name,password,gender,e_mail;
	private boolean bool;
	Console console = System.console();
	String readName() {
		do
		{
			name = console.readLine();
			String nm=new String(name);
			name=nm.trim();
		}while(name.equals(""));
		return name;
	}
	void checkCapital() {
		try {
		throw new MyException("Capital");
		} catch(MyException e) {
			System.out.println(e);
		}
	}
	void checkSmall() {
		try {
		throw new MyException("Small");
		} catch(MyException e) {
			System.out.println(e);
		}
	}
	void checkSpecialChar() {
		try {
		throw new MyException("Special");
		} catch(MyException e) {
			System.out.println(e);
		}
	}
	void checkSpace() {
		try {
		throw new MyException("Space");
		} catch(MyException e) {
			System.out.println(e);
		}
	}
	void checkLength() {
		try {
		throw new MyException("Length");
		} catch(MyException e) {
			System.out.println(e);
		}
	}
	String readPassword() {
		do {
				System.out.print("          Enter Password: ");
				password = Password_Masking.readPassword();
				String pass_pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
				Pattern pattern = Pattern.compile(pass_pattern);
				Matcher matcher = pattern.matcher(password);
				bool = matcher.matches();
			if(this instanceof Sign_up) {
				if(!bool)
				{
					System.out.println("1");
					System.out.println();
					boolean fgc=false,fgs=false,fgsp=false;
					String capitals="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
					String smalls="abdcefghijklmnopqrstuvwxyz";
					String specials="@#$%^&+=";
					for(int i=0;i<password.length();i++)
					{
						if(capitals.contains(password.charAt(i)+""))
						{
							fgc=true;
						}	
						else if(smalls.contains(password.charAt(i)+""))
						{
							fgs=true;
						}	
						else if(specials.contains(password.charAt(i)+""))
						{
							fgsp=true;
						}	
					}
					if(!(fgc))
						checkCapital();
					if(!(fgs))
						checkSmall();
					if(!(fgsp))
						checkSpecialChar();
					if(password.contains(" "))
						checkSpace();
					if(password.length()<8)
						checkLength();
				}
			}
			else break;
			}while(!bool);
	return password;
	}
	String readGender() {
		do
			{
				System.out.print("          Gender (1.Male 2.Female) : ");
				gender = console.readLine();
				String G=new String(gender);
				gender=G.trim();
				if(gender.equals("1") || gender.equals("Male")) gender = "Male";
				else if(gender.equals("2") || gender.equals("Female")) gender = "Female";
			}while(gender.equals(""));
		return gender;
	}
	String readEmail() {
		do
			{
				System.out.print("          Enter E-mail: ");
				e_mail = console.readLine();
				String em=new String(e_mail);
				e_mail=em.trim();
			}while(e_mail.equals(""));
		return e_mail;
	}
}
class Sign_up extends readData {
	Console console = System.console();
	BufferedReader br;
	boolean bool,accept=false,check=true;
	FileWriter fw;
	BufferedWriter bw;
	FileWriter fw1;
	FileInputStream fin;
	BufferedWriter bw1;
	File f1=new File("E:\\Username.txt");
	private String first_name,last_name,password,gender,e_mail;
	void sign_up() {
		try {
			//bool = f.createNewFile();
			File f = new File("E:\\Main.txt");
			/*if(f.createNewFile())
				System.out.println("true");
			else
				System.out.println("false");*/
			fw = new FileWriter(f,true);
			fw1 = new FileWriter(f1,true);
			bw = new BufferedWriter(fw);
			bw1 = new BufferedWriter(fw1);
			System.out.println("\n\n          --------------");
			System.out.println("             SIGN UP:");
			System.out.println("          --------------");
			
			readData obj = new Sign_up();
			do {
				accept=false;
				System.out.print("\n          Enter First Name: ");
				first_name = obj.readName();
				System.out.print("          Enter Last Name: ");
				last_name = obj.readName();
				if(check)
				{
				fin = new FileInputStream(f1);
				br = new BufferedReader(new InputStreamReader(fin));
				String data;
				data=br.readLine();
				while(data!=null)
				{
					String u=first_name+" "+last_name;
					if(u.equals(data))
					{
						System.out.println("          This USERNAME is ALREADY been USED : !! Please Enter Other USERNAME : ");
						accept=true;
						break;
					}
					data=br.readLine();
				}
				}
			}while(accept);
			
			password = obj.readPassword();
			gender = obj.readGender();
			e_mail = obj.readEmail();
			
			System.out.println("\n          -------------------------------------------------------------------------");
			System.out.println("          Your Account with USERNAME : "+first_name+" "+last_name+" has been successfully created : ");
			System.out.println("          -------------------------------------------------------------------------");
			fw1.write(first_name+" "+last_name+"\n");
			fw.write(first_name+" "+last_name+"\n");
			fw.write(password+"\n");
			fw.write(gender+"\n");
			fw.write(e_mail+"\n");
			bw.newLine();
			bw.write("----------------------------------------------------------\n");
			check=true;
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				fw.close();
				bw1.close();
				fw1.close();

			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
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
				array = data.split("\n");
				for(String word : array) {
					if(word.equals(username))
						bool = true;
					/*if(bool==true) {	
						if(next_word.equals(password)) {
							flag = 1;
						}
					}*/ 
				}
				if(bool==true) {
					for(String word : array) {
						if(word.equals(password)) 
							flag=1;
					}
				}
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
interface readFile1 {
	public void readFile();
}

class Account extends Login implements readFile1 {
	int random,marks=0;
	static boolean appear_test=false;
	private double accuracy=0.0;
	private String data,ans,correctAns;
	private boolean bool = false;
	private ArrayList<Integer> list = new ArrayList<Integer>();
	private ArrayList<String> userData = new ArrayList<String>();
	Console console = System.console();
	FileInputStream fin;
	BufferedReader br;
	FileWriter fw;
	FileWriter fw1;
	BufferedWriter bw1;
	BufferedWriter bw;
	File ff=new File("E:\\LeaderBoard.txt");
	File f = new File("E:\\Test.txt");
	File Uf = new File("E:\\UserTest"+super.username+".txt");
	Random rand = new Random();
	//Login ll=new Login();
	public void readFile()
	{
		findQuestion(random);
	}
	void findQuestion(int random) {
		try {
			Uf.createNewFile();
			ff.createNewFile();
			fw1=new FileWriter(ff,true);
			bw1=new BufferedWriter(fw1);
			fw = new FileWriter(Uf,true);
			bw = new BufferedWriter(fw);
			fin = new FileInputStream(f);
			br = new BufferedReader(new InputStreamReader(fin));
			do {
					data = br.readLine().trim();
			} while(!data.equals(random+")"));
			data = br.readLine();
			while(!data.equals("Ans:")) {
				while(!data.equals("$")) {
				System.out.print(data);
				userData.add(data);
				data = br.readLine();
				}
				System.out.println();
				System.out.print("		  ");
				data = br.readLine();
			}
			do {
					System.out.print("\n          Enter R to mark this Question for Review!\n          Enter Your Ans: ");
					ans = console.readLine().trim().toUpperCase();
					if(ans.equals("R") || ans.equals("r"))
					{ list.add(random); bool=true; }
					else if(ans.equals("A") || ans.equals("B") || ans.equals("C") || ans.equals("D"))
					{
						userData.add("YOUR ANSWER: "+ans);
						bool=true; 
					}
					else 
					{
						bool=false;
					}
			}while(!bool);
			data = br.readLine();
			correctAns = data;
			userData.add("CORRECT ANSWER: "+correctAns);
			if(!ans.equals("R")) {
				for(int i=0; i<userData.size(); i++) {
					if(userData.get(i).equals("YOUR ANSWER: "+ans))
						fw.write("@\n"+userData.get(i)+"\n$\n");
					else if(userData.get(i).equals("CORRECT ANSWER: "+correctAns))
						fw.write(userData.get(i)+"\n$\n@\n@\n");
					else 
						fw.write(userData.get(i)+"\n$\n");
				}
			}
			userData.clear();
			if(data.equals(ans))
				marks++;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fin.close();
				fw.close();
				bw.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	Account() {
		try {
			appear_test=false;
			System.out.println("\n\n          -----------");
			System.out.println("           OOP TEST:");
			System.out.println("          -----------");
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for(int i=0; i<5; i++) {
				do {
					random = rand.nextInt(5)+1;
				}while(arr.contains(random));
				arr.add(random);
				System.out.print("\n\n"+"          "+(i+1)+") ");
				readFile();
			}
			appear_test=true;
			for(int i=0; i<list.size(); i++) {
				System.out.println("\n\n          REVIEW QUESTIONS :\n");
				System.out.println("          "+"");
				findQuestion(list.get(i));
			} 
			fw1.write(Login.username+"\n");
			fw1.write(marks+"\n");
			//System.out.println("Username is : "+ll.username);
			//mk.add(marks);
			//user.add(ll.username);
			arr.clear();
			list.clear();
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			try{
				fw1.close();
				bw1.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println("\n                -----------------");
		System.out.println("                  RESULT: "+marks+"/5");
		System.out.println("                -----------------");
		System.out.println("                 Accuracy: "+((double)marks/5)*100+"%");
		System.out.println("                -----------------\n");
	}

}
class ReviewTest extends Login implements readFile1 {
	private String data;
	//static delete;
	FileInputStream fin;
	BufferedReader br;
	File f = new File("E:\\UserTest"+super.username+".txt");
	ReviewTest()
	{
		readFile();
	}
	public void readFile() {
		try {
			fin = new FileInputStream(f);
			br = new BufferedReader(new InputStreamReader(fin));
			data = br.readLine();
			while(data!=null)
			{
				System.out.print("          ");
				if(data.equals("$") || data.equals("@"))
					System.out.println();
				else
					System.out.print(data);
				data=br.readLine();
			}
			/*while(data!=null) {
				System.out.print("          ");
				while(data!=null && !data.equals("$")) {
					if(!data.equals("@"))
					System.out.print(data);
					data = br.readLine();
				}
				data = br.readLine();
				if(data!=null && data.equals("@"))
					System.out.println();
				System.out.println();
			}*/
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fin.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	void dd()
	{
		f.delete();
	}
}
class leader implements readFile1
{
	ArrayList <Integer> m=new ArrayList<Integer>();
	ArrayList <String> u=new ArrayList<String>();
	File f=new File("E:\\LeaderBoard.txt");
	FileInputStream fr;
	String data;
	BufferedReader br;
	leader()
	{
		
		readFile();
		int a1[]=new int[m.size()];
		String a2[]=new String[u.size()];
		for(int i=0;i<m.size();i++)
		{
			a1[i]=m.get(i);
			a2[i]=u.get(i);
		}
		for(int i=0;i<m.size()-1;i++)
		{
			for(int j=i+1;j<m.size();j++)
			{
				if(a1[j]>a1[i])
				{
					int mm=a1[i];
					a1[i]=a1[j];
					a1[j]=mm;
					String s=a2[i];
					a2[i]=a2[j];
					a2[j]=s;
				}
			}
		}
		System.out.println("          -----------------------------------------------------");
		System.out.println("              RANK\tUSERNAME\tMARKS");
		System.out.println("          -----------------------------------------------------");
		for(int i=0;i<m.size();i++)
		{
			System.out.printf("\n               %-10d%-10s%10d",(i+1),a2[i],a1[i]);
		}
		m.clear();
		u.clear();
	}
	public void readFile() {
		try {
			fr=new FileInputStream(f);
			br=new BufferedReader(new InputStreamReader(fr));
			data=br.readLine();
			while(data!=null)
			{
				data=data.trim();
				u.add(data);
				//System.out.println(data);
				data=br.readLine();
				m.add(Integer.parseInt(data));
				//System.out.println(data);
				data=br.readLine();
			}
		} catch(Exception e)
			{
				e.printStackTrace();
			}
		finally{
			try{
				fr.close();
				br.close();
			}
			catch(Exception e)
				{
				e.printStackTrace();
			}
		}	
	}
}
public class Main111 {
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
					}while(Achoice!="4");
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