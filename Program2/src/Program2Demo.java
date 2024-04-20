//Contributers: Logan Racz, Oscar Oropeza

import java.util.Scanner;

public class Program2Demo {

	public static void main(String[] args) {
		
		Checker check = new Checker();

		Scanner menu = new Scanner(System.in);
		
		int menu_choice= 0;
		
		while (menu_choice != 4) {
			System.out.println("Select one of the following: ");
			System.out.println("1. Spell Check Your Document");
			System.out.println("2. Print the frequency of each word alphabetically");
			System.out.println("3. Print the frequency of each word from greatest to least");
			System.out.println("4. Exit the program");
			System.out.println("Your choice?");
			menu_choice= menu.nextInt();
		
			if (menu_choice==1) {
				check.SpellCheck();
				}
			else if (menu_choice==2) {
				check.Alphabetical();
				}
			else if (menu_choice==3) {
				//Print word frequency from greatest to least
				check.Frequency();
			}
			else if (menu_choice==4) {
				break;
			}
		
			else {
				System.out.println("Menu choice invalid, try again");
			}
		}	
	}
}
