/*
Program: Rabin Karp Algorithm
Author: Rahul Yerramsetti
Subject: COSC 5334 - Design and Analysis of Algorithms
*/



package stringmatching;

import java.nio.file.Files;
import java.nio.file.Paths;

//Following program is a Java implementation 
//of Rabin Karp Algorithm

public class rabinkarp 
{
	// d is the number of characters in input alphabet
	public final static int d = 256;
	
		
	
	public static String readFileAsString(String fileName)throws Exception
	  {
	    String data = "";
	    data = new String(Files.readAllBytes(Paths.get(fileName)));
	    return data;
	  }
	
	
	
	
	
	
	
	static void search(String pat, String txt, int q, int len, int len1)
	{
		int M = pat.length();
		int N = txt.length();
		int i, j;
		int p = 0; // hash value for pattern
		int t = 0; // hash value for txt
		int h = 1;
		int comp=0;
	
		// The value of h would be "pow(d, M-1)%q"
		for (i = 0; i < M-1; i++)
			h = (h*d)%q;
	
		// Calculate the hash value of pattern and first
		// window of text
		for (i = 0; i < M; i++)
		{
			p = (d*p + pat.charAt(i))%q;
			t = (d*t + txt.charAt(i))%q;
		}
	
		// Slide the pattern over text one by one
		for (i = 0; i <= N - M; i++)
		{
	
			// Check the hash values of current window of text
			// and pattern. If the hash values match then only
			// check for characters on by one
			if ( p == t )
			{
				comp=comp+1;
				/* Check for characters one by one */
				for (j = 0; j < M; j++)
				{
					if (txt.charAt(i+j) != pat.charAt(j))
						break; 
				}
	
				// if p == t and pat[0...M-1] = txt[i, i+1, ...i+M-1]
				if (j == M)
					{System.out.println("Pattern found at index: " + i);
					System.out.println("Total number of Comparisions: " + comp);
					break; //only first occurence can be found
					}
				
				
			}
	
			// Calculate hash value for next window of text: Remove
			// leading digit, add trailing digit
			if ( i <= (N-M) )
			{
				if(i==(N-M))
				{
					System.out.println("Patern not found " );
					System.out.println("Total number of Comparisions " + (comp+1));
					System.out.println("last location:"+(len-(len%len1)-1));
					break;
				}
				comp=comp+1;
				t = (d*(t - txt.charAt(i)*h) + txt.charAt(i+M))%q;
	
				// We might get negative value of t, converting it
				// to positive
				if (t < 0)
				t = (t + q);
			}
		}
	}
	
	/* Driver program to test above function */
	public static void main(String[] args) throws Exception
	{   
		
        String floctation=System.getProperty("user.dir");
		
		
		String tlin= "\\src\\stringmatching\\test.txt";
		String tfloct= floctation + tlin;
		
		String plin= "\\src\\stringmatching\\pattern.txt";
		String pfloct= floctation + plin;
		
	

		
		String txt = readFileAsString(tfloct);
	    System.out.println("Given Text:  "+txt);
	    txt = txt.replaceAll("\\s+","");   
	    
	    String pat = readFileAsString(pfloct);
	    System.out.println("Given Pattern:  "+pat);
	    pat = pat.replaceAll("\\s+",""); 
	    
	    int len=txt.length();
	    System.out.println("Given Text Length: "+len);
	    
	    
	    int len1=pat.length();
	    System.out.println("Given string Length: "+len1);
		
	
		
		int q = 101; // A prime number
		search(pat, txt, q,len,len1);
	}
}

