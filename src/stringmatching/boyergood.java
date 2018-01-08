/*
Program: Boyer Moore Good Heuristic Algorithm
Author: Rahul Yerramsetti
Subject: COSC 5334 - Design and Analysis of Algorithms
*/





package stringmatching;

import java.nio.file.Files;
import java.nio.file.Paths;


public class boyergood {

	
	public static String readFileAsString(String fileName) throws Exception {
		String data = "";
		data = new String(Files.readAllBytes(Paths.get(fileName)));
		return data;
	}


	// preprocessing for strong good suffix rule
	void preprocess_strong_suffix(int[] shift, int[] bpos, char[] pat, int m, int comp)
	{
		// m is the length of pattern 
		int i=m, j=m+1;
		bpos[i]=j;
		

		while(i>0)
		{
			/*if character at position i-1 is not equivalent to
			character at j-1, then continue searching to right
			of the pattern for border */
			while(j<=m && pat[i-1] != pat[j-1])
			{
				
				/* the character preceding the occurence of t in 
				pattern P is different than mismatching character in P, 
				we stop skipping the occurences and shift the pattern
				from i to j */
				if (shift[j]==0)
					shift[j] = j-i;

				//Update the position of next border 
				j = bpos[j];
			}
			/* p[i-1] matched with p[j-1], border is found.
			store the beginning position of border */
			i--;j--;
			bpos[i] = j; 
		}
	}

	//Preprocessing for case 2
	void preprocess_case2(int[] shift, int[] bpos,char[] pat, int m, int comp)
						
	{
		int i, j;
		j = bpos[0];
		for(i=0; i<=m; i++)
		{
			/* set the border postion of first character of pattern
			to all indices in array shift having shift[i] = 0 */
			if(shift[i]==0)
				shift[i] = j;

			/* suffix become shorter than bpos[0], use the position of 
			next widest border as value of j */
			if (i==j)
				j = bpos[j];
		}
	}

	/*Search for a pattern in given text using
	Boyer Moore algorithm with Good suffix rule */
	void search(char[] txt, char[] pat, int len, int len1)
	{
		// s is shift of the pattern with respect to text
		int s=0, j;
		int m = len1;
		int n = len;
		int comp=0;

		int bpos[]=new int[m+1];
		int shift[]=new int[m+1];
		
		

		//initialize all occurence of shift to 0
		for(int i=0;i<m+1;i++) {shift[i]=0;}

		//do preprocessing
		preprocess_strong_suffix(shift, bpos, pat, m,comp);
		preprocess_case2(shift, bpos, pat, m,comp);

		while(s <= n-m)
		{

			j = m-1;

			/* Keep reducing index j of pattern while characters of
				pattern and text are matching at this shift s*/
			while(j >= 0 && pat[j] == txt[s+j])
				{comp=comp+1;
				j--;}

			/* If the pattern is present at current shift, then index j
				will become -1 after the above loop */
			if (j<0)
			{
				System.out.println("No of comparisions: "+(comp+len1-1));
				System.out.println("pattern FOUND at shift: "+s);
				//s += shift[0];
				break;
			}
			else
				/*pat[i] != pat[s+j] so shift the pattern
				shift[j+1] times */
				{comp=comp+1;
				
				if(s==(len-len1))
						{
					System.out.println("No of comparisions: "+comp);
					System.out.println("pattern last index: "+s);
					System.out.println("Pattern Not Found");
					
						}
				
				s += shift[j+1];}
			
		}
		

	}
	

	
	public static void main(String args[]) throws Exception
	{
        String floctation=System.getProperty("user.dir");
		
		
		String tlin= "\\src\\stringmatching\\test.txt";
		String tfloct= floctation + tlin;
		
		String plin= "\\src\\stringmatching\\pattern.txt";
		String pfloct= floctation + plin;
		
		
				
		
		String t1 = readFileAsString(tfloct);
	    System.out.println("Given text:  "+t1);
	    t1 = t1.replaceAll("\\s+","");   
	    
	    String p1 = readFileAsString(pfloct);
	    System.out.println("Given string:  "+p1);
	    p1 = p1.replaceAll("\\s+",""); 
	    
	    int len=t1.length();
	    System.out.println("Text Length:  "+len);
	    
	    
	    int len1=p1.length();
	    System.out.println("String Length:  "+len1);
        
	    char[] pat = p1.toCharArray();
	    char[] txt = t1.toCharArray();
		
		
		
		
		new boyergood().search(txt,pat,len,len1);//access from here
	}	
	

	
}
