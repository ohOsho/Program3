// Working with partner Teddy Vander Wall

import java.util.Random;
import java.util.Scanner;

public class Program3Demo {

	public static void main(String[] args) {
		
		UndirectedGraph graph = new UndirectedGraph();
		
		graph.Load();
		graph.addToList();
		
		Scanner menu = new Scanner(System.in);
		Scanner choice = new Scanner(System.in);
		Scanner sc = new Scanner(System.in);
		Scanner scan = new Scanner(System.in);
		
		boolean flag = false;
		Random rand = new Random();
		ShortestPath sp = new ShortestPath();
		MinimumSpanningTree mst = new MinimumSpanningTree();
		
		int menu_choice = 0;
		
		while(menu_choice != 4) {
			System.out.println("Enter your choice:");
			System.out.println("1: Print out MST Information");
			System.out.println("2: Find Shortest Path from one Actor to another");
			System.out.println("3: Guess the hops");
			System.out.println("4: Exit");
			//read input
			menu_choice = menu.nextInt();
			
			if(menu_choice == 1) {
				mst.kruskalsAlgorithm(graph);
			}
			else if (menu_choice == 2) { // Menu option 2
                // user input start/ending actors
                System.out.println("Enter starting actor: ");
                String start = scan.nextLine();
                System.out.println("Enter destination actor: ");
                String end = scan.nextLine();
                sp.computeShortestPath(graph, start); // computes shortest path from users given inputs
                int numOfHops = 0; // declaring # of hops
                if(sp.hasPath(end)) { // ensures that there is a path between 2 given actors in graph
                    numOfHops = sp.getPathTo(end); // prints and returns the number of 'hops' between actors
                    System.out.println("Number of hops: " + numOfHops);
                }else { // if path doesn't exist
                    System.out.println("Path does not exist to " + end);
                }
            }
			else if (menu_choice == 3) { // Menu option 3 (generates 2 random actors from graph and asks user how many
                // hops between the 2 actors)
                flag = false; 
                String actOne = "";
                String actTwo = "";
                while(!flag) { // ensures no matching actors are chosen
                    actOne = graph.numToAct(rand.nextInt(graph.getNumVertices()));
                    actTwo = graph.numToAct(rand.nextInt(graph.getNumVertices()));
                    if(!actOne.equals(actTwo)) {
                        flag = true;
                    }
                }
                System.out.println("Guess how many hops it takes to get from " + actOne +" to " + actTwo + "?");
                System.out.println("Enter answer: ");
                int num = 0; // actual distance
                int answer = sc.nextInt(); // user input
                sp.computeShortestPath(graph, actOne); // computes dijkstra's algorithm
                if(sp.hasPath(actTwo)) {
                    num = sp.getPathTo(actTwo); // prints and returns the number of 'hops' between actors
                    if(num == answer) {
                        System.out.println("\nCongragulations. The correct answer is " + num + " hops.");
                    }else {
                        System.out.println("\nIncorrect. The correct answer is " + num + " hops.");
                    }
                }else {
                    System.out.println("Path does not exist to " + actTwo);
                }
			}
			else if(menu_choice == 4) {
				break;
			}
			else {
				System.out.println("Menu choice invalid, try again");
			}
		}
		menu.close();
	}
}