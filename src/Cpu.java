import java.util.Random;

import javax.swing.JButton;

public class Cpu {
	private Gui gui;
	private GameLogic gameLogic;
	private int positionOfFirstMove;
	
	public Cpu(Gui gui, GameLogic gameLogic) {
		this.gui = gui;
		this.gameLogic = gameLogic;
		positionOfFirstMove = doFirstMove();
	}
	
	
	//CPU wählt ein zufälliges Feld(nur bei Spielbeginn)
	public int doFirstMove() {
		if(gameLogic.getTurn() == 1) {
			JButton[] fields = gui.getFields();
			Random r = new Random();
			int randomPosition = r.nextInt(9);
			fields[randomPosition].setText("O");
			return randomPosition;
		}
		return -1;
	}
	
	
	//Bewertet Felder für den CPU Spielzug(Offensive)
	public int[] rateFields() {
		JButton[] fields = gui.getFields();
		int currentRating = 0;
		int[] ratings = new int[9];
		//Bewertung von Feld 0
		if(fields[0].getText().isEmpty()) {
			currentRating++;
			if(fields[1].getText().equals("O") && fields[2].getText().equals("O")) {
				currentRating += 10;
			}
			else if(fields[1].getText().equals("X") && fields[2].getText().equals("X")) {
				currentRating += 20;
			}
			else {
				if(fields[1].getText().equals("O") || fields[2].getText().equals("O")) {
					currentRating += 2;
				}
				else if(fields[1].getText().equals("X") || fields[2].getText().equals("X")) {
					currentRating++;
				}
			}
			if(fields[3].getText().equals("O") && fields[6].getText().equals("O")) {
				currentRating += 10;
			}
			else if(fields[3].getText().equals("X") && fields[6].getText().equals("X")) {
				currentRating += 20;
			}
			else {
				if(fields[3].getText().equals("O") || fields[6].getText().equals("O")) {
					currentRating += 2;
				}
				else if(fields[3].getText().equals("X") || fields[6].getText().equals("X")) {
					currentRating++;
				}
			}
			if(fields[4].getText().equals("O") && fields[8].getText().equals("O")) {
				currentRating += 10;
			}
			else if(fields[4].getText().equals("X") && fields[8].getText().equals("X")) {
				currentRating += 20;
			}
			else {
				if(fields[4].getText().equals("O") || fields[8].getText().equals("O")) {
					currentRating += 2;
				}
				else if(fields[4].getText().equals("X") || fields[8].getText().equals("X")) {
					currentRating++;
				}
			}
		}
		ratings[0] = currentRating;
		currentRating = 0;
		
		//Bewertung von Feld 1
		if(fields[1].getText().isEmpty()) {
			currentRating++;
			if(fields[0].getText().equals("O") && fields[2].getText().equals("O")) {
				currentRating += 10;
			}
			else if(fields[0].getText().equals("X") && fields[2].getText().equals("X")) {
				currentRating += 20;
			}
			else {
				if(fields[0].getText().equals("O") || fields[2].getText().equals("O")) {
					currentRating += 2;
				}
				else if(fields[0].getText().equals("X") || fields[2].getText().equals("X")) {
					currentRating++;
				}
			}
			if(fields[4].getText().equals("O") && fields[7].getText().equals("O")) {
				currentRating += 10;
			}
			else if(fields[4].getText().equals("X") && fields[7].getText().equals("X")) {
				currentRating += 20;
			}
			else {
				if(fields[4].getText().equals("O") || fields[7].getText().equals("O")) {
					currentRating += 2;
				}
				else if(fields[4].getText().equals("X") || fields[7].getText().equals("X")) {
					currentRating++;
				}
			}
		}
		ratings[1] = currentRating;
		currentRating = 0;
		
		//Bewertung von Feld 2
		if(fields[2].getText().isEmpty()) {
			currentRating++;
			if(fields[0].getText().equals("O") && fields[1].getText().equals("O")) {
				currentRating += 10;
			}
			else if(fields[0].getText().equals("X") && fields[1].getText().equals("X")) {
				currentRating += 20;
			}
			else {
				if(fields[0].getText().equals("O") || fields[1].getText().equals("O")) {
					currentRating += 2;
				}
				else if(fields[0].getText().equals("X") || fields[1].getText().equals("X")) {
					currentRating++;
				}
			}
			if(fields[5].getText().equals("O") && fields[8].getText().equals("O")) {
				currentRating += 10;
			}
			else if(fields[5].getText().equals("X") && fields[8].getText().equals("X")) {
				currentRating += 20;
			}
			else {
				if(fields[5].getText().equals("O") || fields[8].getText().equals("O")) {
					currentRating += 2;
				}
				else if(fields[5].getText().equals("X") || fields[8].getText().equals("X")) {
					currentRating++;
				}
			}
			if(fields[4].getText().equals("O") && fields[6].getText().equals("O")) {
				currentRating += 10;
			}
			else if(fields[4].getText().equals("X") && fields[6].getText().equals("X")) {
				currentRating += 20;
			}
			else {
				if(fields[4].getText().equals("O") || fields[6].getText().equals("O")) {
					currentRating += 2;
				}
				else if(fields[4].getText().equals("X") || fields[6].getText().equals("X")) {
					currentRating++;
				}
			}
		}
		ratings[2] = currentRating;
		currentRating = 0;
		
		//Bewertung von Feld 3
		if(fields[3].getText().isEmpty()) {
			currentRating++;
			if(fields[0].getText().equals("O") && fields[6].getText().equals("O")) {
				currentRating += 10;
			}
			else if(fields[0].getText().equals("X") && fields[6].getText().equals("X")) {
				currentRating += 20;
			}
			else {
				if(fields[0].getText().equals("O") || fields[6].getText().equals("O")) {
					currentRating += 2;
				}
				else if(fields[0].getText().equals("X") || fields[6].getText().equals("X")) {
					currentRating++;
				}
			}
			if(fields[4].getText().equals("O") && fields[5].getText().equals("O")) {
				currentRating += 10;
			}
			else if(fields[4].getText().equals("X") && fields[5].getText().equals("X")) {
				currentRating += 20;
			}
			else {
				if(fields[4].getText().equals("O") || fields[5].getText().equals("O")) {
					currentRating += 2;
				}
				else if(fields[4].getText().equals("X") || fields[5].getText().equals("X")) {
					currentRating++;
				}
			}
		}
		ratings[3] = currentRating;
		currentRating = 0;
		
		//Bewertung von Feld 4
		if(fields[4].getText().isEmpty()) {
			currentRating += 5;
			if(fields[3].getText().equals("O") && fields[5].getText().equals("O")) {
				currentRating += 10;
			}
			else if(fields[3].getText().equals("X") && fields[5].getText().equals("X")) {
				currentRating += 20;
			}
			else {
				if(fields[3].getText().equals("O") || fields[5].getText().equals("O")) {
					currentRating += 2;
				}
				else if(fields[3].getText().equals("X") || fields[5].getText().equals("X")) {
					currentRating++;
				}
			}
			if(fields[1].getText().equals("O") && fields[7].getText().equals("O")) {
				currentRating += 10;
			}
			else if(fields[1].getText().equals("X") && fields[7].getText().equals("X")) {
				currentRating += 20;
			}
			else {
				if(fields[1].getText().equals("O") || fields[7].getText().equals("O")) {
					currentRating += 2;
				}
				else if(fields[1].getText().equals("X") || fields[7].getText().equals("X")) {
					currentRating++;
				}
			}
			if(fields[0].getText().equals("O") && fields[8].getText().equals("O")) {
				currentRating += 10;
			}
			else if(fields[0].getText().equals("X") && fields[8].getText().equals("X")) {
				currentRating += 20;
			}
			else {
				if(fields[0].getText().equals("O") || fields[8].getText().equals("O")) {
					currentRating += 2;
				}
				else if(fields[0].getText().equals("X") || fields[8].getText().equals("X")) {
					currentRating++;
				}
			}
			if(fields[2].getText().equals("O") && fields[6].getText().equals("O")) {
				currentRating += 10;
			}
			else if(fields[2].getText().equals("X") && fields[6].getText().equals("X")) {
				currentRating += 20;
			}
			else {
				if(fields[2].getText().equals("O") || fields[6].getText().equals("O")) {
					currentRating += 2;
				}
				if(fields[2].getText().equals("X") || fields[6].getText().equals("X")) {
					currentRating++;
				}
			}
		}
		ratings[4] = currentRating;
		currentRating = 0;
		
		//Bewertung von Feld 5
		if(fields[5].getText().isEmpty()) {
			currentRating++;
			if(fields[2].getText().equals("O") && fields[8].getText().equals("O")) {
				currentRating += 10;
			}
			else if(fields[2].getText().equals("X") && fields[8].getText().equals("X")) {
				currentRating += 20;
			}
			else {
				if(fields[2].getText().equals("O") || fields[8].getText().equals("O")) {
					currentRating += 2;
				}
				else if(fields[2].getText().equals("X") || fields[8].getText().equals("X")) {
					currentRating++;
				}
			}
			if(fields[3].getText().equals("O") && fields[4].getText().equals("O")) {
				currentRating += 10;
			}
			else if(fields[3].getText().equals("X") && fields[4].getText().equals("X")) {
				currentRating += 20;
			}
			else {
				if(fields[3].getText().equals("O") || fields[4].getText().equals("O")) {
					currentRating += 2;
				}
				else if(fields[3].getText().equals("X") || fields[4].getText().equals("X")) {
					currentRating++;
				}
			}
		}
		ratings[5] = currentRating;
		currentRating = 0;
		
		//Bewertung von Feld 6
		if(fields[6].getText().isEmpty()) {
			currentRating++;
			if(fields[0].getText().equals("O") && fields[3].getText().equals("O")) {
				currentRating += 10;
			}
			else if(fields[0].getText().equals("X") && fields[3].getText().equals("X")) {
				currentRating += 20;
			}
			else {
				if(fields[0].getText().equals("O") || fields[3].getText().equals("O")) {
					currentRating += 2;
				}
				if(fields[0].getText().equals("X") || fields[3].getText().equals("X")) {
					currentRating++;
				}
			}
			if(fields[7].getText().equals("O") && fields[8].getText().equals("O")) {
				currentRating += 10;
			}
			else if(fields[7].getText().equals("X") && fields[8].getText().equals("X")) {
				currentRating += 20;
			}
			else {
				if(fields[7].getText().equals("O") || fields[8].getText().equals("O")) {
					currentRating += 2;
				}
				else if(fields[7].getText().equals("X") || fields[8].getText().equals("X")) {
					currentRating++;
				}
			}
			if(fields[2].getText().equals("O") && fields[4].getText().equals("O")) {
				currentRating += 10;
			}
			else if(fields[2].getText().equals("X") && fields[4].getText().equals("X")) {
				currentRating += 20;
			}
			else {
				if(fields[2].getText().equals("O") || fields[4].getText().equals("O")) {
					currentRating += 2;
				}
				else if(fields[2].getText().equals("X") || fields[4].getText().equals("X")) {
					currentRating++;
				}
			}
		}
		ratings[6] = currentRating;
		currentRating = 0;
		
		//Bewertung von Feld 7
		if(fields[7].getText().isEmpty()) {
			currentRating++;
			if(fields[1].getText().equals("O") && fields[4].getText().equals("O")) {
				currentRating += 10;
			}
			else if(fields[1].getText().equals("X") && fields[4].getText().equals("X")) {
				currentRating += 20;
			}
			else {
				if(fields[1].getText().equals("O") || fields[4].getText().equals("O")) {
					currentRating += 2;
				}
				else if(fields[1].getText().equals("X") || fields[4].getText().equals("X")) {
					currentRating++;
				}
			}
			if(fields[6].getText().equals("O") && fields[8].getText().equals("O")) {
				currentRating += 10;
			}
			else if(fields[6].getText().equals("X") && fields[8].getText().equals("X")) {
				currentRating += 20;
			}
			else {
				if(fields[6].getText().equals("O") || fields[8].getText().equals("O")) {
					currentRating += 2;
				}
				else if(fields[6].getText().equals("X") || fields[8].getText().equals("X")) {
					currentRating++;
				}
			}
		}
		ratings[7] = currentRating;
		currentRating = 0;
		
		//Bewertung von Feld 8
		if(fields[8].getText().isEmpty()) {
			currentRating++;
			if(fields[2].getText().equals("O") && fields[5].getText().equals("O")) {
				currentRating += 10;
			}
			else if(fields[2].getText().equals("X") && fields[5].getText().equals("X")) {
				currentRating += 20;
			}
			else {
				if(fields[2].getText().equals("O") || fields[5].getText().equals("O")) {
					currentRating += 2;
				}
				else if(fields[2].getText().equals("X") || fields[5].getText().equals("X")) {
					currentRating++;
				}
			}
			if(fields[6].getText().equals("O") && fields[7].getText().equals("O")) {
				currentRating += 10;
			}
			else if(fields[6].getText().equals("X") && fields[7].getText().equals("X")) {
				currentRating += 20;
			}
			else {
				if(fields[6].getText().equals("O") || fields[7].getText().equals("O")) {
					currentRating += 2;
				}
				else if(fields[6].getText().equals("X") || fields[7].getText().equals("X")) {
					currentRating++;
				}
			}
			if(fields[0].getText().equals("O") && fields[4].getText().equals("O")) {
				currentRating += 10;
			}
			else if(fields[0].getText().equals("X") && fields[4].getText().equals("X")) {
				currentRating += 20;
			}
			else {
				if(fields[0].getText().equals("O") || fields[4].getText().equals("O")) {
					currentRating += 2;
				}
				else if(fields[0].getText().equals("X") || fields[4].getText().equals("X")) {
					currentRating++;
				}
			}
		}
		ratings[8] = currentRating;
		currentRating = 0;
		return ratings;
	}
	
	

	
	//CPU macht anhand der berechneten ratings einen geeigneten Zug
	public int cpuMove(int[] ratings) {
		int biggestRating = 0;
		for(int i=0;i<ratings.length;i++) {
			if(ratings[i] > biggestRating) {
				biggestRating = ratings[i];
			}
		}
		int position = 0;
		while(ratings[position] != biggestRating) {
			position++;
		}
		JButton[] fields = gui.getFields();
		fields[position].setText("O");
		return position;
	}
	
	
	public int getPositionOfFirstMove() {
		return positionOfFirstMove;
	}
}
