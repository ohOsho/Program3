// Working with partner Teddy Vander Wall

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class UndirectedGraph {

	//Beginning by mapping String (Movies) to String (Actors)
	private HashMap<String, LinkedList<String>> connection;
	//This will map the actors name with the actors they have worked with
	private HashMap<String, LinkedList<Edge>> adjList;
	
	public UndirectedGraph() {
		
		adjList = new HashMap<String, LinkedList<Edge>>();
		connection = new HashMap<String, LinkedList<String>>();
	}
	
	//This loads in our input txt file to then store into value
	public void Load() {
		
		try {
			Scanner file = new Scanner(new FileReader("actors.txt"));
			
			while(file.hasNext()) {
				String line = file.nextLine();
				String[] value = line.split("\\|");
				
				//Calling another method in this class
				Insert(value[0], value[1]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Will map the movie and the actors
	public void Insert(String actor, String movie) {
		//If the movie of this actor is not a key
		if(!connection.containsKey(movie)) {
			LinkedList<String> actors = new LinkedList<String>();
			actors.add(actor);
			connection.put(movie, actors);
		}
		else {
			connection.get(movie).add(actor);
		}
	}
	
	//The main function that will add all the edges to our adjList
	public void addToList() {
		
		//for every 'element' in our hashmap of movies and actors
		for(Map.Entry<String, LinkedList<String>> entry: connection.entrySet()) {
			
			String movie = entry.getKey();
			LinkedList<String> actors = entry.getValue();
			String[] act = new String[entry.getValue().size()]; //This will help up create edges between actors
			int i = 0;

			for(String actor: actors) {
				act[i] = actor;
				i++;
			}
			
			for(int j = 0; j < i-1; j++) {
				for(int k = j+1; k < i; k++) {
					//Handles all cases that the program will run into
					if(!adjList.containsKey(act[j])) { //does not exist and is connecting a previous array spot to one further down that array
						Edge edge = new Edge(act[j], act[k], movie, 1.0);
						LinkedList<Edge> edges = new LinkedList<Edge>();
						edges.add(edge);
						adjList.put(act[j], edges);
					}
					if(!adjList.containsKey(act[k])) { //does not exist and is connecting a array spot further down to one prior to it
						Edge edge = new Edge(act[k], act[j], movie, 1.0);
						LinkedList<Edge> edges = new LinkedList<Edge>();
						edges.add(edge);
						adjList.put(act[k], edges);
					}
					if(adjList.containsKey(act[j])){
						Edge edge = new Edge(act[j], act[k], movie, 1.0);
						adjList.get(act[j]).add(edge);
					}
					if(adjList.containsKey(act[k])) {
						Edge edge = new Edge(act[k], act[j], movie, 1.0);
						adjList.get(act[k]).add(edge);
					}
				}
			}
		}
	}
	
	//helps check if our adjList is printing correctly
	public void printAdjList() {
		
		for (Map.Entry<String, LinkedList<Edge>> entry : adjList.entrySet()) {
            String act = entry.getKey();
            LinkedList<Edge> actors = entry.getValue();

            System.out.print(act + ": " + actors);
        
            System.out.println();
        }
	}
}
