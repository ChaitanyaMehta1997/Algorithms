//package Algo;

/*
 * @Author : Chaitanya Mehta
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;


public class Stable {

	@SuppressWarnings("resource")
	public static void main(String args[]) throws IOException {

		BufferedReader br = null;
		int n = 0;
		int menCountrow = 0;
		int menCountCol = 0;
		
		int womenCountCol = 0;
		int womenCountrow = 0;
		String input = "";
		Scanner sc = new Scanner(System.in);
		
		// input = sc.nextLine();
		// System.out.println(sc.nextLine());
		input+="\n";
		br = new BufferedReader(new InputStreamReader(
				System.in,
				Charset.forName("UTF-8")));
		while (br.ready()) {

			//if (n == 0) {
				n = Integer.parseInt(br.readLine());
				System.out.println(n);
				break;
		}
		//	}
		String Men[][] = new String[n][n];
		String Women[][] = new String[n][n];
		//This part reads the input and stores it in a matrix 
		while (br.ready()) {
			while (menCountrow != (n)) {
				int menCount=1;
				String x = br.readLine();
				while (menCountCol != (n)) {
					String men[] = new String[n];

					men = x.split("\\s");	
					Men[menCountrow][menCountCol] = men[menCount];
					
					menCountCol += 1;
					menCount+=1;
				
				}
				menCountCol=0;
				menCountrow += 1;
			}
			
			//For women
			while (womenCountrow != (n)) {
				int womenCount=1;
				String x = br.readLine();
				while (womenCountCol != (n)) {
					String women[] = new String[n];

					
					
					
					women = x.split("\\s");	
					Women[womenCountrow][womenCountCol] = women[womenCount];
					
					womenCountCol += 1;
					womenCount+=1;
				
				}
				womenCountCol=0;
				womenCountrow += 1;
			}

		}
		
		for(int i=0;i<n;i++) {
			System.out.print("m"+(i+1));
			for(int j=0;j<n;j++) {
				System.out.print(" "+Men[i][j]);
			}
			System.out.println();
		}
		for(int i=0;i<n;i++) {
			System.out.print("w"+(i+1));
			for(int j=0;j<n;j++) {
				System.out.print(" "+Women[i][j]);
			}
			System.out.println();
		}
        //HashMap<Integer, String> WomenSet = new HashMap<>();
        HashMap<String, String> MenHash = new HashMap<>();
        Set<String> WomenSet = new HashSet<>();
        Set<String> MenSet = new HashSet<>();
        HashMap<String, String> WomenHash = new HashMap<>();

        int row=0;
        int col=0;
        int c1=0;
        int c2=0;
        while(MenHash.size() != n) {
        	if(row==n) {
        		row=0;
        		col+=1;
        	}
        	String M = ""+(row+1);
        	
        	//Initiallizing W to the very first woman Man M wants
        	String W = Men[row][col];
        	//Put in WomenSet
        	
        	//To check if woman is free
        	if(!WomenSet.contains(W) && !MenHash.containsKey(M)){
        		MenHash.put(M, W);
        		WomenHash.put(W, M);
        		WomenSet.add(W);
        		row+=1;
        		//col=0;
        	}
        	else if(WomenSet.contains(W) && !MenHash.containsKey(M)) {
        		//String MDash = "" + (Integer.parseInt(WomenHash.get(W))-1);
        		String MDash = (WomenHash.get(W));
        		
        		for(int i=0;i<n;i++) {
        			//System.out.println(Women[ Integer.parseInt(W)-1][i]);
        			if(Women[ Integer.parseInt(W)-1][i].equals(MDash)) {
        				c1=i;
        				
        			}
        			if(Women[ Integer.parseInt(W)-1][i].equals((M))) {
        				//This to be compareed with previouslys store value(this should be lesser)
        				c2=i;
        				
        			}
        			
        		}
        		if(c1>c2) {
        		
        		MenHash.put(M, W);
        		//Man MDash is free
        		MenHash.remove(MDash);
        		WomenHash.put(W, M);
        		WomenSet.add(W);
        		
        		//col=0;
        		}
        		row+=1;
			}
        	else {
        		
        		row+=1;
        		continue;
        		
        	}
        		
		
        	}
        
        for (Entry<String, String> entry : MenHash.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            System.out.println ("m" + key + " w" + value);
        }
        
        PrintStream o = new PrintStream(new File("Stable.out")); 
        
        // Store current System.out before assigning a new value 
        PrintStream console = System.out; 
  
        // Assign o to output stream 
        System.setOut(o); 
        System.out.println("MEN");
        
        for(int i=0;i<n;i++) {
			System.out.print("m"+(i+1));
			for(int j=0;j<n;j++) {
				System.out.print(" "+Men[i][j]);
			}
			System.out.println();
		}
        System.out.println("WOMEN");
		for(int i=0;i<n;i++) {
			System.out.print("w"+(i+1));
			for(int j=0;j<n;j++) {
				System.out.print(" "+Women[i][j]);
			}
			System.out.println();
		}
		
		
        for (Entry<String, String> entry : MenHash.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            System.out.println ("m" + key + " w" + value);
        }
        
       
        System.setOut(console); 
        
        }
}


		
	


