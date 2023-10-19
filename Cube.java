import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;

public class Cube implements Comparable<Cube> {

	HashMap<Integer, Integer> Colours = new HashMap<Integer, Integer>(); // used for filling cube with colours

	private double[] current = new double[6]; // stores target colour of each of the 6 sides

	private int m; // move that was made that resulted in this state 
	
	public int wrong_moves; // number of tiles out of place

	private int[][][] Cube = new int[3][3][6]; // cube array 3d

	Random rand = new Random(System.currentTimeMillis()); // random seed

	private int r; // used in constructor
	private Cube father; // previous state of cube
	private Cube Child; // next state of cube

	public Cube() { // makes a perfect cube , all colours at the right place
		for (int z = 0; z < 6; z++) {
			for (int x = 0; x < 3; x++) {
				for (int y = 0; y < 3; y++) {
					Cube[x][y][z] = z+1;
				}
			}
		}

		for (int z = 0; z < 6; z++) {
			current[z] = Cube[1][1][z]; // store current colours
		}
		wrong_moves = num_of_wrong_titles();
	}

	public Cube(Cube other) { // copy constructor
		for (int z = 0; z < 6; z++) {
			for (int x = 0; x < 3; x++) {
				for (int y = 0; y < 3; y++) {
					this.Cube[x][y][z] = other.GetColour(x, y, z);
				}
			}
		}

		for (int z = 0; z < 5; z++) {
			current[z] = Cube[1][1][z]; // store current colours
		}
	}

	public void set_m(int m) { 
		this.m = m;
	}

	public int get_m() {
		return this.m;
	}

	public void perform_move(int i) { // performs a move based on the input
		if (i == 0) {
			vertical_left("up");
		} else if (i == 1) {
			vertical_left("down");
		} else if (i == 2) {
			vertical_middle("up");
		} else if (i == 3) {
			vertical_middle("down");
		} else if (i == 4) {
			vertical_right("up");
		} else if (i == 5) {
			vertical_right("down");
		} else if (i == 6) {
			horizontal_bottom("right");
		} else if (i == 7) {
			horizontal_bottom("left");
		} else if (i == 8) {
			horizontal_middle("right");
		} else if (i == 9) {
			horizontal_middle("left");
		} else if (i == 10) {
			horizontal_top("right");
		} else if (i == 11) {
			horizontal_top("left");
		} else if (i == 12) {
			behind_behind("right");
		} else if (i == 13) {
			behind_behind("left");
		} else if (i == 14) {
			behind_middle("right");
		} else if (i == 15) {
			behind_middle("left");
		} else if (i == 16) {
			behind_front("right");
		} else if (i == 17) {
			behind_front("left");
		}

	}

	public void set_father(Cube cb) { // set previous state
		this.father = cb;
	}

	public Cube get_father() { // get previous state
		return this.father;
	}

	public void set_Child(Cube child) { // set next state
		this.Child = child;
	}

	public Cube get_child() { // get next state
		return Child;
	}

	public void scramble_cube() { // perform random moves to scramble the cube
		int max = 18;
		for (int moves = 0; moves < 5; moves++) {
			perform_move(rand.nextInt(max));
		}
	}

	public boolean check_one(int k) { // check if condition is met
										// for example if k==2 , counter should be 2 (two sides are done)
		int counter = 0;
		if (check__blue()) {
			counter++;
		}

		if (check__green()) {
			counter++;
		}

		if (check__orange()) {
			counter++;
		}

		if (check__red()) {
			counter++;
		}

		if (check__white()) {
			counter++;
		}

		if (check__yellow()) {
			counter++;
		}

		return counter >= k;

	}

	int num_of_wrong_titles() { // heuristic ,  number of misplaced tiles
		int counter = 0; 
		for (int z = 0; z < 6; z++) {
			for (int x = 0; x < 3; x++) {
				for (int y = 0; y < 3; y++) {
					if (Cube[x][y][z] != current[z]) {
						counter++;
					}
				}
			}
		}

		return counter / 3;
	}

	public ArrayList<Cube> get_children() { // expand children states

		ArrayList<Cube> children = new ArrayList<>();
		
		Cube child = new Cube(this); // create a state just like the current one
		child.perform_move(0); // perform the move on the child state
		child.set_m(0); // move that was executed
		child.wrong_moves = this.wrong_moves + child.num_of_wrong_titles(); // calculate misplaced tiles
		children.add(child); 
		child.set_father(this);
		
		child = null;

		child = new Cube(this); // create a state just like the current one
		child.perform_move(1);
		child.set_m(1);

		child.wrong_moves = this.wrong_moves + child.num_of_wrong_titles(); // calculate misplaced tiles
		children.add(child);
		child.set_father(this);
		child = null;

		child = new Cube(this); // create a state just like the current one
		child.perform_move(2);
		child.set_m(2);

		child.wrong_moves = this.wrong_moves + child.num_of_wrong_titles(); // calculate misplaced tiles
		children.add(child);
		child.set_father(this);
		child = null;

		child = new Cube(this); // create a state just like the current one
		child.perform_move(3);
		child.set_m(3);

		child.wrong_moves = this.wrong_moves + child.num_of_wrong_titles(); // calculate misplaced tiles
		children.add(child);
		child.set_father(this);
		child = null;

		child = new Cube(this); // create a state just like the current one
		child.perform_move(4);
		child.set_m(4);

		child.wrong_moves = this.wrong_moves + child.num_of_wrong_titles(); // calculate misplaced tiles
		children.add(child);
		child.set_father(this);
		child = null;

		child = new Cube(this); // create a state just like the current one
		child.perform_move(5);
		child.set_m(5);

		child.wrong_moves = this.wrong_moves + child.num_of_wrong_titles(); // calculate misplaced tiles
		children.add(child);
		child.set_father(this);
		child = null;

		child = new Cube(this); // create a state just like the current one
		child.perform_move(6);
		child.set_m(6);

		child.wrong_moves = this.wrong_moves + child.num_of_wrong_titles(); // calculate misplaced tiles
		children.add(child);
		child.set_father(this);
		child = null;

		child = new Cube(this); // create a state just like the current one
		child.perform_move(7);
		child.set_m(7);

		child.wrong_moves = this.wrong_moves + child.num_of_wrong_titles(); // calculate misplaced tiles
		children.add(child);
		child.set_father(this);
		child = null;

		child = new Cube(this); // create a state just like the current one
		child.perform_move(8);
		child.set_m(8);

		child.wrong_moves = this.wrong_moves + child.num_of_wrong_titles(); // calculate misplaced tiles
		children.add(child);
		child.set_father(this);
		child = null;

		child = new Cube(this); // create a state just like the current one
		child.perform_move(9);
		child.set_m(9);

		child.wrong_moves = this.wrong_moves + child.num_of_wrong_titles(); // calculate misplaced tiles
		children.add(child);
		child.set_father(this);
		child = null;

		child = new Cube(this); // create a state just like the current one
		child.perform_move(10);
		child.set_m(10);

		child.wrong_moves = this.wrong_moves + child.num_of_wrong_titles(); // calculate misplaced tiles
		children.add(child);
		child.set_father(this);
		child = null;

		child = new Cube(this); // create a state just like the current one
		child.perform_move(11);
		child.set_m(11);

		child.wrong_moves = this.wrong_moves + child.num_of_wrong_titles(); // calculate misplaced tiles
		children.add(child);
		child.set_father(this);
		child = null;

		child = new Cube(this); // create a state just like the current one
		child.perform_move(12);
		child.set_m(12);

		child.wrong_moves = this.wrong_moves + child.num_of_wrong_titles(); // calculate misplaced tiles
		children.add(child);
		child.set_father(this);
		child = null;

		child = new Cube(this); // create a state just like the current one
		child.perform_move(13);
		child.set_m(13);

		child.wrong_moves = this.wrong_moves + child.num_of_wrong_titles(); // calculate misplaced tiles
		children.add(child);
		child.set_father(this);
		child = null;

		child = new Cube(this); // create a state just like the current one
		child.perform_move(14);
		child.set_m(14);

		child.wrong_moves = this.wrong_moves + child.num_of_wrong_titles(); // calculate misplaced tiles
		children.add(child);
		child.set_father(this);
		child = null;

		child = new Cube(this); // create a state just like the current one
		child.perform_move(15);
		child.set_m(15);

		child.wrong_moves = this.wrong_moves + child.num_of_wrong_titles(); // calculate misplaced tiles
		children.add(child);
		child.set_father(this);
		child = null;

		child = new Cube(this); // create a state just like the current one
		child.perform_move(16);
		child.set_m(16);

		child.wrong_moves = this.wrong_moves + child.num_of_wrong_titles(); // calculate misplaced tiles
		children.add(child);
		child.set_father(this);
		child = null;

		child = new Cube(this); // create a state just like the current one
		child.perform_move(17);
		child.set_m(17);

		child.wrong_moves = this.wrong_moves + child.num_of_wrong_titles(); // calculate misplaced tiles
		children.add(child);
		child.set_father(this);
		child = null;

		return children;

	}

	public boolean check__red() { // check if target side red is complete
		int counter = 0;
		for (int red = 0; red < 6; red++) {
			if (current[red] == 1) {
				for (int x = 0; x < 3; x++) {
					for (int y = 0; y < 3; y++) {
						if (Cube[x][y][red] == 1) {
							counter++;
						}
					}
				}
			}
		}
		return counter == 9;

	}

	public boolean check__white() { // check if target side red is complete
		int counter = 0;
		for (int white = 0; white < 6; white++) {
			if (current[white] == 2) {
				for (int x = 0; x < 3; x++) {
					for (int y = 0; y < 3; y++) {
						if (Cube[x][y][white] == 2) {
							counter++;
						}
					}
				}
			}
		}

		return counter == 9;
	}

	public boolean check__blue() {
		int counter = 0;
		for (int blue = 0; blue < 6; blue++) { // check if target side red is complete
			if (current[blue] == 3) {
				for (int x = 0; x < 3; x++) {
					for (int y = 0; y < 3; y++) {
						if (Cube[x][y][blue] == 3) {
							counter++;
						}
					}
				}
			}
		}

		if (counter == 9) {
			return true;
		} else {
			return false;
		}
	}

	public boolean check__yellow() {
		int counter = 0;
		for (int yellow = 0; yellow < 6; yellow++) { // check if target side red is complete
			if (current[yellow] == 4) {
				for (int x = 0; x < 3; x++) {
					for (int y = 0; y < 3; y++) {
						if (Cube[x][y][yellow] == 4) {
							counter++;
						}
					}
				}
			}
		}

		if (counter == 9) {
			return true;
		} else {
			return false;
		}
	}

	public boolean check__orange() {
		int counter = 0;
		for (int orange = 0; orange < 6; orange++) { // check if target side red is complete
			if (current[orange] == 5) {
				for (int x = 0; x < 3; x++) {
					for (int y = 0; y < 3; y++) {
						if (Cube[x][y][orange] == 5) {
							counter++;
						}
					}
				}
			}
		}

		if (counter == 9) {
			return true;
		} else {
			return false;
		}
	}

	public boolean check__green() {
		int counter = 0;
		for (int green = 0; green < 6; green++) { // check if target side red is complete
			if (current[green] == 6) {
				for (int x = 0; x < 3; x++) {
					for (int y = 0; y < 3; y++) {
						if (Cube[x][y][green] == 6) {
							counter++;
						}
					}
				}
			}
		}

		if (counter == 9) {
			return true;
		} else {
			return false;
		}
	}

	public int GetColour(int x, int y, int z) {

		return Cube[x][y][z];
	} // get the colour of a specific square in the cube

	public void SetColour(int x, int y, int z, int colour) {

		Cube[x][y][z] = colour;

	} // set the colour of a specific square in the cube

	public void print_cube() {
		for (int z = 0; z < 6; z++) {
			System.out.println();
			System.out.println("Side" + z);
			for (int x = 0; x < 3; x++) {

				

				for (int y = 0; y < 3; y++) {


					System.out.print(Cube[x][y][z]+" | ");
				}
				System.out.println();
				System.out.println("- - - - - - ");
			}
		}

	} // print the cube on the terminal

	// -------------------Moves-------------------//

	public void vertical_left(String direction) {

		int temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9;

		if (direction == "up") {

			temp1 = Cube[0][0][1];
			temp2 = Cube[1][0][1];
			temp3 = Cube[2][0][1];

			Cube[0][0][1] = Cube[0][0][0];
			Cube[1][0][1] = Cube[1][0][0];
			Cube[2][0][1] = Cube[2][0][0];

			temp4 = Cube[0][0][2];
			temp5 = Cube[1][0][2];
			temp6 = Cube[2][0][2];

			Cube[0][0][2] = temp1;
			Cube[1][0][2] = temp2;
			Cube[2][0][2] = temp3;

			temp7 = Cube[0][0][3];
			temp8 = Cube[1][0][3];
			temp9 = Cube[2][0][3];

			Cube[0][0][3] = temp4;
			Cube[1][0][3] = temp5;
			Cube[2][0][3] = temp6;

			Cube[0][0][0] = temp7;
			Cube[1][0][0] = temp8;
			Cube[2][0][0] = temp9;

			temp1 = Cube[2][0][5];
			Cube[2][0][5] = Cube[0][0][5];
			temp2 = Cube[2][2][5];
			Cube[2][2][5] = temp1;
			temp3 = Cube[0][2][5];
			Cube[0][2][5] = temp2;
			Cube[0][0][5] = temp3;
			temp4 = Cube[2][1][5];
			Cube[2][1][5] = Cube[1][0][5];
			temp5 = Cube[1][2][5];
			Cube[1][2][5] = temp4;
			temp6 = Cube[0][1][5];
			Cube[0][1][5] = temp5;
			Cube[1][0][5] = temp6;

		} else if (direction == "down") {

			temp1 = Cube[0][0][3];
			temp2 = Cube[1][0][3];
			temp3 = Cube[2][0][3];

			Cube[0][0][3] = Cube[0][0][0];
			Cube[1][0][3] = Cube[1][0][0];
			Cube[2][0][3] = Cube[2][0][0];

			temp4 = Cube[0][0][2];
			temp5 = Cube[1][0][2];
			temp6 = Cube[2][0][2];

			Cube[0][0][2] = temp1;
			Cube[1][0][2] = temp2;
			Cube[2][0][2] = temp3;

			temp7 = Cube[0][0][1];
			temp8 = Cube[1][0][1];
			temp9 = Cube[2][0][1];

			/// Cube[0][0][1] = temp4;
			Cube[1][0][1] = temp5;
			Cube[2][0][1] = temp6;

			Cube[0][0][0] = temp7;
			Cube[1][0][0] = temp8;
			Cube[2][0][0] = temp9;

			temp1 = Cube[0][2][5];
			Cube[0][2][5] = Cube[0][0][5];
			temp2 = Cube[2][2][5];
			Cube[2][2][5] = temp1;
			temp3 = Cube[2][0][5];
			Cube[2][0][5] = temp2;
			Cube[0][0][5] = temp3;
			temp4 = Cube[0][1][5];
			Cube[0][1][5] = Cube[1][0][5];
			temp5 = Cube[1][2][5];
			Cube[1][2][5] = temp4;
			temp6 = Cube[2][1][5];
			Cube[2][1][5] = temp5;
			Cube[1][0][5] = temp6;

		}
	}

	public void vertical_middle(String direction) {

		int temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9;

		if (direction == "up") {

			temp1 = Cube[0][1][1];
			temp2 = Cube[1][1][1];
			temp3 = Cube[2][1][1];

			Cube[0][1][1] = Cube[0][1][0];
			Cube[1][1][1] = Cube[1][1][0];
			Cube[2][1][1] = Cube[2][1][0];

			temp4 = Cube[0][1][2];
			temp5 = Cube[1][1][2];
			temp6 = Cube[2][1][2];

			Cube[0][1][2] = temp1;
			Cube[1][1][2] = temp2;
			Cube[2][1][2] = temp3;

			temp7 = Cube[0][1][3];
			temp8 = Cube[1][1][3];
			temp9 = Cube[2][1][3];

			Cube[0][1][3] = temp4;
			Cube[1][1][3] = temp5;
			Cube[2][1][3] = temp6;

			Cube[0][1][0] = temp7;
			Cube[1][1][0] = temp8;
			Cube[2][1][0] = temp9;

		} else if (direction == "down") {

			temp1 = Cube[0][1][3];
			temp2 = Cube[1][1][3];
			temp3 = Cube[2][1][3];

			Cube[0][1][3] = Cube[0][1][0];
			Cube[1][1][3] = Cube[1][1][0];
			Cube[2][1][3] = Cube[2][1][0];

			temp4 = Cube[0][1][2];
			temp5 = Cube[1][1][2];
			temp6 = Cube[2][1][2];

			Cube[0][1][2] = temp1;
			Cube[1][1][2] = temp2;
			Cube[2][1][2] = temp3;

			temp7 = Cube[0][1][1];
			temp8 = Cube[1][1][1];
			temp9 = Cube[2][1][1];

			Cube[0][1][1] = temp4;
			Cube[1][1][1] = temp5;
			Cube[2][1][1] = temp6;

			Cube[0][1][0] = temp7;
			Cube[1][1][0] = temp8;
			Cube[2][1][0] = temp9;

		}
	}

	public void vertical_right(String direction) {

		int temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9;

		if (direction == "up") {

			temp1 = Cube[0][2][1];
			temp2 = Cube[1][2][1];
			temp3 = Cube[2][2][1];

			Cube[0][2][1] = Cube[0][2][0];
			Cube[1][2][1] = Cube[1][2][0];
			Cube[2][2][1] = Cube[2][2][0];

			temp4 = Cube[0][2][2];
			temp5 = Cube[1][2][2];
			temp6 = Cube[2][2][2];

			Cube[0][2][2] = temp1;
			Cube[1][2][2] = temp2;
			Cube[2][2][2] = temp3;

			temp7 = Cube[0][2][3];
			temp8 = Cube[1][2][3];
			temp9 = Cube[2][2][3];

			Cube[0][2][3] = temp4;
			Cube[1][2][3] = temp5;
			Cube[2][2][3] = temp6;

			Cube[0][2][0] = temp7;
			Cube[1][2][0] = temp8;
			Cube[2][2][0] = temp9;

			temp1 = Cube[0][2][4];
			Cube[0][2][4] = Cube[0][0][4];
			temp2 = Cube[2][2][4];
			Cube[2][2][4] = temp1;
			temp3 = Cube[2][0][4];
			Cube[2][0][4] = temp2;
			Cube[0][0][4] = temp3;
			temp4 = Cube[0][1][4];
			Cube[0][1][4] = Cube[1][0][4];
			temp5 = Cube[1][2][4];
			Cube[1][2][4] = temp4;
			temp6 = Cube[2][1][4];
			Cube[2][1][4] = temp5;
			Cube[1][0][4] = temp6;

		} else if (direction == "down") {

			temp1 = Cube[0][2][3];
			temp2 = Cube[1][2][3];
			temp3 = Cube[2][2][3];

			Cube[0][2][3] = Cube[0][2][0];
			Cube[1][2][3] = Cube[1][2][0];
			Cube[2][2][3] = Cube[2][2][0];

			temp4 = Cube[0][2][2];
			temp5 = Cube[1][2][2];
			temp6 = Cube[2][2][2];

			Cube[0][2][2] = temp1;
			Cube[1][2][2] = temp2;
			Cube[2][2][2] = temp3;

			temp7 = Cube[0][2][1];
			temp8 = Cube[1][2][1];
			temp9 = Cube[2][2][1];

			Cube[0][2][1] = temp4;
			Cube[1][2][1] = temp5;
			Cube[2][2][1] = temp6;

			Cube[0][2][0] = temp7;
			Cube[1][2][0] = temp8;
			Cube[2][2][0] = temp9;

			temp1 = Cube[2][0][4];
			Cube[2][0][4] = Cube[0][0][4];
			temp2 = Cube[2][2][4];
			Cube[2][2][4] = temp1;
			temp3 = Cube[0][2][4];
			Cube[0][2][4] = temp2;
			Cube[0][0][4] = temp3;
			temp4 = Cube[2][1][4];
			Cube[2][1][4] = Cube[1][0][4];
			temp5 = Cube[1][2][4];
			Cube[1][2][4] = temp4;
			temp6 = Cube[0][1][4];
			Cube[0][1][4] = temp5;
			Cube[1][0][4] = temp6;

		}

	}

	public void horizontal_bottom(String direction) {
		int temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp11, temp12, temp10;
		if (direction.equals("right")) {
			temp1 = Cube[0][0][3];
			temp2 = Cube[0][1][3];
			temp3 = Cube[0][2][3];

			temp4 = Cube[1][2][3];
			temp5 = Cube[2][2][3];

			temp6 = Cube[2][1][3];
			temp7 = Cube[2][0][3];

			temp8 = Cube[1][0][3];

			//

			Cube[0][0][3] = temp7;
			Cube[0][2][3] = temp1;
			Cube[2][2][3] = temp3;
			Cube[2][0][3] = temp5;

			Cube[0][1][3] = temp8;
			Cube[1][2][3] = temp2;
			Cube[2][1][3] = temp4;
			Cube[1][0][3] = temp6;

			//////////////

			temp1 = Cube[2][0][0];
			temp2 = Cube[2][1][0];
			temp3 = Cube[2][2][0];

			temp4 = Cube[2][0][4];
			temp5 = Cube[2][1][4];
			temp6 = Cube[2][2][4];

			temp7 = Cube[0][2][2];
			temp8 = Cube[0][1][2];
			temp9 = Cube[0][0][2];

			temp10 = Cube[2][0][5];
			temp11 = Cube[2][1][5];
			temp12 = Cube[2][2][5];

			//

			Cube[2][0][4] = temp1;
			Cube[2][1][4] = temp2;
			Cube[2][2][4] = temp3;

			Cube[0][2][2] = temp4;
			Cube[0][1][2] = temp5;
			Cube[0][0][2] = temp6;

			Cube[2][0][5] = temp7;
			Cube[2][1][5] = temp8;
			Cube[2][2][5] = temp9;

			Cube[2][0][0] = temp10;
			Cube[2][1][0] = temp11;
			Cube[2][2][0] = temp12;

		} else if ((direction.equals("left"))) {

			temp1 = Cube[0][0][3];
			temp2 = Cube[0][1][3];
			temp3 = Cube[0][2][3];

			temp4 = Cube[1][2][3];
			temp5 = Cube[2][2][3];

			temp6 = Cube[2][1][3];
			temp7 = Cube[2][0][3];

			temp8 = Cube[1][0][3];

			//

			Cube[0][0][3] = temp3;
			Cube[0][2][3] = temp5;
			Cube[2][2][3] = temp7;
			Cube[2][0][3] = temp1;

			Cube[0][1][3] = temp4;
			Cube[1][2][3] = temp6;
			Cube[2][1][3] = temp8;
			Cube[1][0][3] = temp2;

			//////////

			temp1 = Cube[2][0][0];
			temp2 = Cube[2][1][0];
			temp3 = Cube[2][2][0];

			temp4 = Cube[2][0][4];
			temp5 = Cube[2][1][4];
			temp6 = Cube[2][2][4];

			temp7 = Cube[0][2][2];
			temp8 = Cube[0][1][2];
			temp9 = Cube[0][0][2];

			temp10 = Cube[2][0][5];
			temp11 = Cube[2][1][5];
			temp12 = Cube[2][2][5];

			//

			Cube[2][0][0] = temp4;
			Cube[2][1][0] = temp5;
			Cube[2][2][0] = temp6;

			Cube[2][0][5] = temp1;
			Cube[2][1][5] = temp2;
			Cube[2][2][5] = temp3;

			Cube[0][2][2] = temp10;
			Cube[0][1][2] = temp11;
			Cube[0][0][2] = temp12;

			Cube[2][0][4] = temp7;
			Cube[2][1][4] = temp8;
			Cube[2][2][4] = temp9;

		}
	}

	public void horizontal_middle(String direction) {

		int temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp11, temp12, temp10;
		if (direction.equals("right")) {

			temp1 = Cube[1][0][0];
			temp2 = Cube[1][1][0];
			temp3 = Cube[1][2][0];

			temp4 = Cube[1][0][4];
			temp5 = Cube[1][1][4];
			temp6 = Cube[1][2][4];

			temp7 = Cube[1][2][2];
			temp8 = Cube[1][1][2];
			temp9 = Cube[1][0][2];

			temp10 = Cube[1][0][5];
			temp11 = Cube[1][1][5];
			temp12 = Cube[1][2][5];

			//

			Cube[1][0][4] = temp1;
			Cube[1][1][4] = temp2;
			Cube[1][2][4] = temp3;

			Cube[1][2][2] = temp4;
			Cube[1][1][2] = temp5;
			Cube[1][0][2] = temp6;

			Cube[1][0][5] = temp7;
			Cube[1][1][5] = temp8;
			Cube[1][2][5] = temp9;

			Cube[1][0][0] = temp10;
			Cube[1][1][0] = temp11;
			Cube[1][2][0] = temp12;

		} else if (direction.equals("left")) {
			temp1 = Cube[1][0][0];
			temp2 = Cube[1][1][0];
			temp3 = Cube[1][2][0];

			temp4 = Cube[1][0][4];
			temp5 = Cube[1][1][4];
			temp6 = Cube[1][2][4];

			temp7 = Cube[1][2][2];
			temp8 = Cube[1][1][2];
			temp9 = Cube[1][0][2];

			temp10 = Cube[1][0][5];
			temp11 = Cube[1][1][5];
			temp12 = Cube[1][2][5];

			//

			Cube[1][2][2] = temp10;
			Cube[1][1][2] = temp11;
			Cube[1][0][2] = temp12;

			Cube[1][0][4] = temp7;
			Cube[1][1][4] = temp8;
			Cube[1][2][4] = temp9;

			Cube[1][0][0] = temp4;
			Cube[1][1][0] = temp5;
			Cube[1][2][0] = temp6;

			Cube[1][0][5] = temp1;
			Cube[1][1][5] = temp2;
			Cube[1][2][5] = temp3;

		}
	}

	public void horizontal_top(String direction) {
		int temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp11, temp12, temp10;
		if (direction.equals("right")) {

			temp1 = Cube[0][0][1];
			temp2 = Cube[0][1][1];
			temp3 = Cube[0][2][1];

			temp4 = Cube[1][2][1];
			temp5 = Cube[2][2][1];

			temp6 = Cube[2][1][1];
			temp7 = Cube[2][0][1];

			temp8 = Cube[1][0][1];

			//

			Cube[0][0][1] = temp3;
			Cube[0][2][1] = temp5;
			Cube[2][2][1] = temp7;
			Cube[2][0][1] = temp1;

			Cube[0][1][1] = temp4;
			Cube[1][2][1] = temp6;
			Cube[2][1][1] = temp8;
			Cube[1][0][1] = temp2;

			/////////////

			temp1 = Cube[0][0][0];
			temp2 = Cube[0][1][0];
			temp3 = Cube[0][2][0];

			temp4 = Cube[0][0][4];
			temp5 = Cube[0][1][4];
			temp6 = Cube[0][2][4];

			temp7 = Cube[2][2][2];
			temp8 = Cube[2][1][2];
			temp9 = Cube[2][0][2];

			temp10 = Cube[0][0][5];
			temp11 = Cube[0][1][5];
			temp12 = Cube[0][2][5];

			//

			Cube[0][0][4] = temp1;
			Cube[0][1][4] = temp2;
			Cube[0][2][4] = temp3;

			Cube[2][2][2] = temp4;
			Cube[2][1][2] = temp5;
			Cube[2][0][2] = temp6;

			Cube[0][0][5] = temp7;
			Cube[0][1][5] = temp8;
			Cube[0][2][5] = temp9;

			Cube[0][0][0] = temp10;
			Cube[0][1][0] = temp11;
			Cube[0][2][0] = temp12;

		} else if (direction.equals("left")) {

			temp1 = Cube[0][0][1];
			temp2 = Cube[0][1][1];
			temp3 = Cube[0][2][1];

			temp4 = Cube[1][2][1];
			temp5 = Cube[2][2][1];

			temp6 = Cube[2][1][1];
			temp7 = Cube[2][0][1];

			temp8 = Cube[1][0][1];

			//

			Cube[0][0][1] = temp7;
			Cube[0][2][1] = temp1;
			Cube[2][2][1] = temp3;
			Cube[2][0][1] = temp5;

			Cube[0][1][1] = temp8;
			Cube[1][2][1] = temp2;
			Cube[2][1][1] = temp4;
			Cube[1][0][1] = temp6;

			///////////////

			temp1 = Cube[0][0][0];
			temp2 = Cube[0][1][0];
			temp3 = Cube[0][2][0];

			temp4 = Cube[0][0][4];
			temp5 = Cube[0][1][4];
			temp6 = Cube[0][2][4];

			temp7 = Cube[2][2][2];
			temp8 = Cube[2][1][2];
			temp9 = Cube[2][0][2];

			temp10 = Cube[0][0][5];
			temp11 = Cube[0][1][5];
			temp12 = Cube[0][2][5];

			//

			Cube[0][0][0] = temp4;
			Cube[0][1][0] = temp5;
			Cube[0][2][0] = temp6;

			Cube[0][0][5] = temp1;
			Cube[0][1][5] = temp2;
			Cube[0][2][5] = temp3;

			Cube[2][2][2] = temp10;
			Cube[2][1][2] = temp11;
			Cube[2][0][2] = temp12;

			Cube[0][0][4] = temp7;
			Cube[0][1][4] = temp8;
			Cube[0][2][4] = temp9;

		}
	}

	public void behind_behind(String direction) {

		if (direction.equals("left")) {
			int temp1 = Cube[0][0][5];
			int temp2 = Cube[1][0][5];
			int temp3 = Cube[2][0][5];

			Cube[0][0][5] = Cube[0][2][1];
			Cube[1][0][5] = Cube[0][1][1];
			Cube[2][0][5] = Cube[0][0][1];

			int temp4 = Cube[2][0][3];
			int temp5 = Cube[2][1][3];
			int temp6 = Cube[2][2][3];

			Cube[2][0][3] = temp1;
			Cube[2][1][3] = temp2;
			Cube[2][2][3] = temp3;

			int temp7 = Cube[0][2][4];
			int temp8 = Cube[1][2][4];
			int temp9 = Cube[2][2][4];

			Cube[0][2][4] = temp6;
			Cube[1][2][4] = temp5;
			Cube[2][2][4] = temp4;

			Cube[0][0][1] = temp7;
			Cube[0][1][1] = temp8;
			Cube[0][2][1] = temp9;

			// rotate face 2(kitrino)
			rotate(Cube, 2, "right");

		}

		if (direction.equals("right")) {
			int temp1 = Cube[0][0][1];
			int temp2 = Cube[0][1][1];
			int temp3 = Cube[0][2][1];

			Cube[0][0][1] = Cube[2][0][5];
			Cube[0][1][1] = Cube[1][0][5];
			Cube[0][2][1] = Cube[0][0][5];

			int temp4 = Cube[0][2][4];
			int temp5 = Cube[1][2][4];
			int temp6 = Cube[2][2][4];

			Cube[0][2][4] = temp1;
			Cube[1][2][4] = temp2;
			Cube[2][2][4] = temp3;

			int temp7 = Cube[2][0][3];
			int temp8 = Cube[2][1][3];
			int temp9 = Cube[2][2][3];

			Cube[2][0][3] = temp6;
			Cube[2][1][3] = temp5;
			Cube[2][2][3] = temp4;

			Cube[0][0][5] = temp7;
			Cube[1][0][5] = temp8;
			Cube[2][0][5] = temp9;

			// rotate face 3(kitrino)
			rotate(Cube, 2, "left");
		}

	}

	public void behind_middle(String direction) {
		if (direction.equals("right")) {
			int temp1 = Cube[1][0][1];
			int temp2 = Cube[1][1][1];
			int temp3 = Cube[1][2][1];

			Cube[1][0][1] = Cube[2][1][5];
			Cube[1][1][1] = Cube[1][1][5];
			Cube[1][2][1] = Cube[0][1][5];

			int temp4 = Cube[0][1][4];
			int temp5 = Cube[1][1][4];
			int temp6 = Cube[2][1][4];

			Cube[0][1][4] = temp1;
			Cube[1][1][4] = temp2;
			Cube[2][1][4] = temp3;

			int temp7 = Cube[1][0][3];
			int temp8 = Cube[1][1][3];
			int temp9 = Cube[1][2][3];

			Cube[1][0][3] = temp6;
			Cube[1][1][3] = temp5;
			Cube[1][2][3] = temp4;

			Cube[0][1][5] = temp7;
			Cube[1][1][5] = temp8;
			Cube[2][1][5] = temp9;

		}

		if (direction.equals("left")) {

			int temp1 = Cube[0][1][5];
			int temp2 = Cube[1][1][5];
			int temp3 = Cube[2][1][5];

			Cube[0][1][5] = Cube[1][2][1];
			Cube[1][1][5] = Cube[1][1][1];
			Cube[2][1][5] = Cube[1][0][1];

			int temp4 = Cube[1][0][3];
			int temp5 = Cube[1][1][3];
			int temp6 = Cube[1][2][3];

			Cube[1][0][3] = temp1;
			Cube[1][1][3] = temp2;
			Cube[1][2][3] = temp3;

			int temp7 = Cube[0][1][4];
			int temp8 = Cube[1][1][4];
			int temp9 = Cube[2][1][4];

			Cube[0][1][4] = temp6;
			Cube[1][1][4] = temp5;
			Cube[2][1][4] = temp4;

			Cube[1][0][1] = temp7;
			Cube[1][1][1] = temp8;
			Cube[1][2][1] = temp9;

		}
	}

	public void behind_front(String direction) {

		if (direction.equals("left")) {

			int temp1 = Cube[0][2][5];
			int temp2 = Cube[1][2][5];
			int temp3 = Cube[2][2][5];

			Cube[0][2][5] = Cube[2][2][1];
			Cube[1][2][5] = Cube[2][1][1];
			Cube[2][2][5] = Cube[2][0][1];

			int temp4 = Cube[0][0][3];
			int temp5 = Cube[0][1][3];
			int temp6 = Cube[0][2][3];

			Cube[0][0][3] = temp1;
			Cube[0][1][3] = temp2;
			Cube[0][2][3] = temp3;

			int temp7 = Cube[0][0][4];
			int temp8 = Cube[1][0][4];
			int temp9 = Cube[2][0][4];

			Cube[0][0][4] = temp6;
			Cube[1][0][4] = temp5;
			Cube[2][0][4] = temp4;

			Cube[2][0][1] = temp7;
			Cube[2][1][1] = temp8;
			Cube[2][2][1] = temp9;

			// rotage face 0(aspro)
			rotate(Cube, 0, "left");
		}

		if (direction.equals("right")) {

			int temp1 = Cube[0][0][4];
			int temp2 = Cube[1][0][4];
			int temp3 = Cube[2][0][4];

			Cube[0][0][4] = Cube[2][0][1];
			Cube[1][0][4] = Cube[2][1][1];
			Cube[2][0][4] = Cube[2][2][1];

			int temp4 = Cube[0][0][3];
			int temp5 = Cube[0][1][3];
			int temp6 = Cube[0][2][3];

			Cube[0][0][3] = temp3;
			Cube[0][1][3] = temp2;
			Cube[0][2][3] = temp1;

			int temp7 = Cube[0][2][5];
			int temp8 = Cube[1][2][5];
			int temp9 = Cube[2][2][5];

			Cube[0][2][5] = temp4;
			Cube[1][2][5] = temp5;
			Cube[2][2][5] = temp6;

			Cube[2][0][1] = temp9;
			Cube[2][1][1] = temp8;
			Cube[2][2][1] = temp7;

			// rotate face 0(aspro)
			rotate(Cube, 0, "right");

		}

	}

	public void rotate(int[][][] Cube, int face, String direction) {
		if (direction.equals("right")) {
			for (int i = 0; i < 3; i++) {
				for (int y = i; y < 3; y++) {
					int temp = Cube[i][y][face];
					Cube[i][y][face] = Cube[y][i][face];
					Cube[y][i][face] = temp;
				}
			}
			for (int i = 0; i < 3; i++) {
				int low = 0;
				int high = 2;
				while (low < high) {
					int temp = Cube[i][low][face];
					Cube[i][low][face] = Cube[i][high][face];
					Cube[i][high][face] = temp;
					low++;
					high--;
				}
			}

		}

		if (direction.equals("left")) {
			for (int i = 0; i < 3; i++) {
				for (int y = i; y < 3; y++) {
					int temp = Cube[i][y][face];
					Cube[i][y][face] = Cube[y][i][face];
					Cube[y][i][face] = temp;
				}
			}
			for (int i = 0; i < 3; i++) {
				int low = 0;
				int high = 2;
				while (low < high) {
					int temp = Cube[low][i][face];
					Cube[low][i][face] = Cube[high][i][face];
					Cube[high][i][face] = temp;
					low++;
					high--;
				}
			}
		}

	}

	@Override
	public int compareTo(Cube o) { // used to compare states between each other based on the heuristic
		return Double.compare(this.wrong_moves, o.wrong_moves);
	}

}