/*
Program: Boyer Moore Bad Heuristic Algorithm
Author: Rahul Yerramsetti
Subject: COSC 5334 - Design and Analysis of Algorithms
*/


package stringmatching;

import java.nio.file.Files;
import java.nio.file.Paths;

public class boyerbad {

	public static String readFileAsString(String fileName) throws Exception {
		String data = "";
		data = new String(Files.readAllBytes(Paths.get(fileName)));
		return data;
	}

	public static int Search(int textind, int patternind, char[] d1, char[] p1, int comp)
			throws Exception {
        //search for next matching of text character in substring
		for (int e1 = patternind; e1 >= 0; e1--) {
			
			//comp=comp+1;
			if (d1[textind] == p1[e1]) {
				return e1;
			}
		}

		return -1;
	}

	public static void main(String[] args) throws Exception {

		String floctation=System.getProperty("user.dir");
		
		//file loading
		String tlin= "\\src\\stringmatching\\test.txt";
		String tfloct= floctation + tlin;
		
		String plin= "\\src\\stringmatching\\pattern.txt";
		String pfloct= floctation + plin;
		
		String data = readFileAsString(tfloct);
		System.out.println("Given Text:"+data);
		data = data.replaceAll("\\s+", "");

		String pattern = readFileAsString(pfloct);
		System.out.println("Given String:"+pattern);
		pattern = pattern.replaceAll("\\s+", "");

		int len = data.length();
		System.out.println("Given Text Length:"+len);

		int len1 = pattern.length();
		System.out.println("Given string length:"+len1);

		char[] d1 = data.toCharArray();
		char[] p1 = pattern.toCharArray();
		
		int comp=0;
		

		int i = len1 - 1;
		int k = len1 - 1;
		int j = k;

		while (i >= 0 && j <=(len-1)) {

			int count = len1 - 1;

			if (d1[j] == p1[i]) {
				
				comp=comp+1;
				if (i == 0) {
					System.out.println("Total number of comparisions:" + comp);
					System.out.println("string found at index:" + j);

				}

				j = j - 1;
				i = i - 1;
				count = count - 1;
				

			}

			else {
				
				comp=comp+1;
				

				int r1 = Search(j, i, d1, p1,comp);
				
				
				//System.out.println("j IS: "+j);
									
				if(r1==-1 && (j + len1) > (len-1))
				{System.out.println("Total number of comparisions:" + (comp+1));
				System.out.println("string not found");
				System.out.println("last location:"+(len-(len%len1)-1));
				//break;
				}
				
				
				
				//System.out.println("submatch:" + r1);

				if (r1 > 0)

				{
					if ((j + (k-r1)) <= (len-1)) {

						j = j + (k-r1);   
						i = k;
						continue;

					} else
						break;

				}

				else if (r1 == 0)

				{
					if ((j + k) <= (len-1)) {

						//System.out.println("J VALUE:" + j);
						j = j + k;
						i = k;
						//System.out.println("J VALUE:" + j);
						continue;

					} else
						break;

				}

				else if (r1 == -1) {
					if ((j + len1) <= (len-1)) {  
						j = j + len1;
						i = k;
						//System.out.println("J VALUE:" + j);
						continue;
					} else
						break;
				}

			}

		}

	}

}
