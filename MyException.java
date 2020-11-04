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