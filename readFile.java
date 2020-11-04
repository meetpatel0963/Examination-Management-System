import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.regex.*;

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