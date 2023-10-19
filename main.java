
import java.util.Scanner;

public class main {
	public static void main(String[] args) {

		//
		Scanner sc = new Scanner(System.in);
		System.out.println("How many sides you want to solve? : "); // ask user to pick how many sides is the final state
		int k = sc.nextInt();
		sc.close();
		//

		System.out.println("Number 1 is: red"); // print code of each colour
		System.out.println("Number 2 is: white");
		System.out.println("Number 3 is: blue");
		System.out.println("Number 4 is: yellow");
		System.out.println("Number 5 is: orange");
		System.out.println("Number 6 is: green");


		Cube cb = new Cube(); // create cube (already solved)
		cb.scramble_cube(); // scramble it
		//
		System.out.println("STARTING CUBE ");
		cb.print_cube();

		SpaceSearcher searcher = new SpaceSearcher(); // create object space
		// searcher to use best_fs function

		

		Cube terminalState = searcher.A_Star(cb, k); // begin

		if (terminalState == null) // not found
			System.out.println("Could not find a solution.");
		else {
			
			
			Cube temp = terminalState; // get back to the father
			while (temp!= null) {
				if (temp.get_father() == null) {
					break;
				} else {
					temp = temp.get_father();
				}
				
			}

			int counter = 1;
			int curr_mov;

			while (temp!=null) {    // iterate through every child printing the moves that were made
				if (temp.get_child()== null) {
					break;
				}
				temp = temp.get_child();
				System.out.print(" ");
				System.out.print("Move "+counter+" was : ");
				counter++;
				curr_mov = temp.get_m();
				switch (curr_mov) {
					case 0:
						System.out.println(" Vertical Left Up");
						break;
					case 1:
						System.out.println(" Vertical Left Down");
						break;
					case 2:
						System.out.println(" Vertical Middle Up");
						break;
					case 3:
						System.out.println(" Vertical Middle Down");
						break;
					case 4:
						System.out.println(" Vertical Right Up");
						break;
					case 5:
						System.out.println(" Vertical Right Down");
						break;
					case 6:
						System.out.println(" Horizontal Bottom Right");
						break;
					case 7:
						System.out.println(" Horizontal Bottom Left");
						break;
					case 8:
						System.out.println(" Horizontal Middle Right");
						break;
					case 9:
						System.out.println(" Horizon Middle Left");
						break;
					case 10:
						System.out.println(" Horizontal Top Right");
						break;
					case 11:
						System.out.println(" Horizontal top Left");
						break;
					case 12:
						System.out.println(" Behind Side Right");
						break;
					case 13:
						System.out.println(" Behind Side Left");
						break;
					case 14:
						System.out.println(" Middle Side Right");
						break;
					case 15:
						System.out.println(" Middle Side Left");
						break;
					case 16:
						System.out.println(" Front Side Right");
						break;
					case 17:
						System.out.println(" Front Side Left");
					
				}
			}

			System.out.println("");
			System.out.println("FINAL CUBE\n"); // found
			terminalState.print_cube();
		} 
	}
}