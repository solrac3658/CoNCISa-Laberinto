package ve.ucv.ciens.cicore.concisa;

import lejos.nxt.Button;
import ve.ucv.ciens.cicore.concisa.utils.QuitButtonListener;

public class Main {	
	public static void main(String[] args) {
		Button.ESCAPE.addButtonListener(new QuitButtonListener());
		
		LabyrinthSolver solver = new LabyrinthSolver();
		solver.start();
	}
}
