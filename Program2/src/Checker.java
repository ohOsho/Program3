//Contributers: Logan Racz, Oscar Oropeza

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;


public class Checker<Word> {
	
	public Checker(){
		loadHashTable();
	}
	
	HashMap<String, Integer> checker = new HashMap<String, Integer>(); 
	
	HashMap<Integer, HashSet<String>> frequency = new HashMap<Integer, HashSet<String>>(); 
	
	HashSet<String> EnglishWords = new HashSet<String>();
	
	TreeMap<String, Integer> treemap = new TreeMap<String, Integer>(checker);
	
	HashSet<String> WordsUsed = new HashSet<String>();
	
	
	public HashMap <String, Integer > loadHashTable() {
		
		try {
		      File myObj = new File("input.txt");
		      Scanner myReader = new Scanner(myObj);
		      
		      while(myReader.hasNextLine()) {
		    	  String data = myReader.nextLine().toLowerCase();
		    	  String[] SplitString = data.split(" |\\.|\\,|\\?|\\!");
		    	  for(int i = 0; i < SplitString.length; i++) {
		    		  if(SplitString[i] != "") {
		    			  if(!checker.containsKey(SplitString[i])) {
		    				  checker.put(SplitString[i], 1);
		    			  }
		    			  else {
		    				  int frequency = checker.get(SplitString[i]);
		    				  checker.put(SplitString[i], frequency+1);
		    			  }
		    		  }
		    	  }
		      }
		      myReader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
		    e.printStackTrace();
		}
		return checker;
	}

	public HashSet <String> loadHashSet() {
		try {
			File myObj = new File("words.txt");
			Scanner myReader = new Scanner(myObj);
			while(myReader.hasNextLine()) {
				String data = myReader.nextLine().toLowerCase();
				EnglishWords.add(data);
			}
			myReader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
	    }
		return EnglishWords;	
	}
	
	public void SpellCheck() {
		try {
			loadHashSet();
			File myObj = new File("input.txt");
		    Scanner myReader = new Scanner(myObj);
		    String checked = "";
		    while (myReader.hasNextLine()) {
		    	String data = myReader.nextLine();
		        String[] SplitString2 = data.split(" ");  
		        for(int i = 0; i < SplitString2.length; i++) {
		        	if(SplitString2[i].contains(".")) {
		        		SplitString2[i] = SplitString2[i].replace(".","");
		        		String wild = ". ";
		        		if(!EnglishWords.contains(SplitString2[i].toLowerCase())) {
			        		checked += "<"+SplitString2[i]+">" + wild;
			        	}
		        		else {
		        			checked += SplitString2[i] + wild;
		        		}
		        	}
		        	else if(SplitString2[i].contains(",")) {
		        		SplitString2[i] = SplitString2[i].replace(",","");
		        		String wild = ", ";
		        		if(!EnglishWords.contains(SplitString2[i].toLowerCase())) {
			        		checked += "<"+SplitString2[i]+">" + wild;
			        	}
		        		else {
		        			checked += SplitString2[i] + wild;
		        		}
		        	}
		        	else if(SplitString2[i].contains("?")) {
		        		SplitString2[i] = SplitString2[i].replace("?","");
		        		String wild = "? ";
		        		if(!EnglishWords.contains(SplitString2[i].toLowerCase())) {
			        		checked += "<"+SplitString2[i]+">" + wild;
			        	}
		        		else {
		        			checked += SplitString2[i] + wild;
		        		}
		        	}
		        	else if(SplitString2[i].contains("!")) {
		        		SplitString2[i] = SplitString2[i].replace("!","");
		        		String wild = "! ";
		        		if(!EnglishWords.contains(SplitString2[i].toLowerCase())) {
			        		checked += "<"+SplitString2[i]+">" + wild;
			        	}
		        		else {
		        			checked += SplitString2[i] + wild;
		        		}
		        	}
		        	else if(!EnglishWords.contains(SplitString2[i].toLowerCase())) {
		        		checked += "<"+SplitString2[i]+"> ";
		        	}
		        	else {
		        		checked+= SplitString2[i]+" ";
		        	}
		        }
		    }
		    myReader.close();
		    System.out.println(checked);
		    }
		    catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		
	}
	
	public void Alphabetical() {
		TreeMap<String, Integer> treemap = new TreeMap<String, Integer>(checker);
		for(String keyword :treemap.keySet()) {
			System.out.println(keyword + ": "+ treemap.get(keyword));
		}
	}
	
	public void Frequency() {
		loadHashTable();
		for(String word: checker.keySet()) {
			if(!frequency.containsKey(checker.get(word))) {
				frequency.put(checker.get(word), new HashSet<>());
			}
			frequency.get(checker.get(word)).add(word);
		}
		Set<Integer> sameFrequency = frequency.keySet();
		List<Integer> sorted = new ArrayList<>(sameFrequency);
		Collections.sort(sorted, Collections.reverseOrder());
		
		for(int occurance: sorted) {
			System.out.println(occurance/2 + ": " + String.join(", ", frequency.get(occurance)));
		}
	}
}

	

		
	
	
	
	
	




