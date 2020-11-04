import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.regex.*;

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