// Working with partner Teddy Vander Wall

import java.util.Scanner;

public class Program3Demo {

	public static void main(String[] args) {
		
		UndirectedGraph graph = new UndirectedGraph();
		
		graph.Load();
		graph.addToList();
		
		Scanner menu = new Scanner(System.in);
		
		int menu_choice = 0;
		
		while(menu_choice != 4) {
			System.out.println("Enter your choice:");
			System.out.println("1: Print out MST Information");
			System.out.println("2: Find Shortest Path from one Actor to another");
			System.out.println("3: Your Custom Method [You will describe your method here]");
			System.out.println("4: Exit");
			//read input
			menu_choice = menu.nextInt();
			
			if(menu_choice == 1) {
				MinimumSpanningTree mst = new MinimumSpanningTree();
				mst.kruskalsAlgorithm(graph);
			}
			else if(menu_choice == 2) {
				System.out.println("Shortest Path");
			}
			else if(menu_choice == 3) {
				System.out.println("Custom method");
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