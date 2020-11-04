import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.regex.*;

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