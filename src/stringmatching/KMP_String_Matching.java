/*
Program: Knuth-Morris-Pratt Algorithm
Author: Rahul Yerramsetti
Subject: COSC 5334 - Design and Analysis of Algorithms
*/



package stringmatching;

import java.nio.file.Files;
import java.nio.file.Paths;


class KMP_String_Matching
{
	
	  public static String readFileAsString(String fileName)throws Exception
	  {
	    String data = "";
	    data = new String(Files.readAllBytes(Paths.get(fileName)));
	    return data;
	  }
	
	
	
	
	
	
	void KMPSearch(String pat, String txt, int len2, int len1)
	{
		int comp=0;
		int M = pat.length();
		int N = txt.length();

		// create lps[] that will hold the longest
		// prefix suffix values for pattern
		int lps[] = new int[M];
		int j = 0; // index for pat[]

		// Preprocess the pattern (calculate lps[]
		// array)
		computeLPSArray(pat,M,lps);

		int i = 0; // index for txt[]
		while (i < N)
		{
			if (pat.charAt(j) == txt.charAt(i))
			{
				comp=comp+1;
				j++;
				
				i++;
			}
			if (j == M)
			{
				System.out.println("Found pattern at index: " + (i-j));
							
				System.out.println("No of comparisions:"+comp);
				j = lps[j-1];
				break; 
			}

			// mismatch after j matches
			else if (i < N && pat.charAt(j) != txt.charAt(i))
			{
				  comp=comp+1;
				  
				  if(i==(len2-len1-1))
				  {   System.out.println("String Not Found");
					  System.out.println("No of comparisions:"+comp);
					  System.out.println("last location:"+(len2-(len2%len1)-1));  
				  }
				// Do not match lps[0..lps[j-1]] characters,
				// they will match anyway
				if (j != 0)
					j = lps[j-1];
				else
					i = i+1;
			}
		}
	}

	void computeLPSArray(String pat, int M, int lps[]) //insert comp into this
	{
		// length of the previous longest prefix suffix
		int len = 0;
		int i = 1;
		lps[0] = 0; // lps[0] is always 0

		// the loop calculates lps[i] for i = 1 to M-1
		while (i < M)
		{
			if (pat.charAt(i) == pat.charAt(len))
			{
				len++;
				lps[i] = len;
				i++;
			}
			else // (pat[i] != pat[len])
			{
				// This is tricky. Consider the example.
				// AAACAAAA and i = 7. The idea is similar 
				// to search step.
				if (len != 0)
				{
					len = lps[len-1];

					// Also, note that we do not increment
					// i here
				}
				else // if (len == 0)
				{
					lps[i] = len;
					i++;
				}
			}
		}
	}

	// Driver program to test above function
	public static void main(String args[]) throws Exception
	{
		
        String floctation=System.getProperty("user.dir");
		
		
		String tlin= "\\src\\stringmatching\\test.txt";
		String tfloct= floctation + tlin;
		
		String plin= "\\src\\stringmatching\\pattern.txt";
		String pfloct= floctation + plin;
			
		
		
		
		String txt = readFileAsString(tfloct);
	    System.out.println("Given Text:"+txt);
	    txt = txt.replaceAll("\\s+","");   
	    
	    String pat = readFileAsString(pfloct);
	    System.out.println("Given String:"+pat);
	    pat = pat.replaceAll("\\s+",""); 
	    
	    int len2=txt.length();
	    System.out.println("Text Length"+len2);
	    
	    
	    int len1=pat.length();
	    System.out.println("String Length:"+len1);
        
	
		
		
		
		
		new KMP_String_Matching().KMPSearch(pat,txt,len2,len1);
	}
}