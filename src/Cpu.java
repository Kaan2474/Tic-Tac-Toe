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
	
	
	//CPU wählt ein zufälliges Feld(nur wenn CPU anfängt)
	public int doFirstMove() {
		if(gameLogic.getTurn() == 1) {
			JButton[] fields = gui.getFields();
			Random r = new Random();
			int randomPosition = r.nextInt(9);
			fields[randomPosition].setText("O");
			return randomPosition;
		}
		//Wenn CPU nicht anfängt gib -1 zurück
		return -1;
	}
	
	
	//Bewerte die Felder horizontal
	//Wenn 2 Felder O sind --> Rating = 75 --> Angriff
	//Wenn 2 Felder X sind --> Rating = 50 --> Defense
	public int checkHorizontal(String player, int currentRating, int position) {
		JButton[] fields = gui.getFields();
		
		//Bewerte Horizontal von links
		if(position == 0 || position == 3 || position == 6) {
			if(fields[position+1].getText().equals(player) && fields[position+2].getText().equals(player)) {
				if(player.equals("O")) {
					currentRating += 75;
				}
				else {
					currentRating += 50;
				}
			}
		}
		
		//Bewerte horizontal von der Mitte
		if(position == 1 || position == 4 || position == 7) {
			if(fields[position-1].getText().equals(player) && fields[position+1].getText().equals(player)) {
				if(player.equals("O")) {
					currentRating += 75;
				}
				else {
					currentRating += 50;
				}
			}
		}
		
		//Bewerte horizontal von rechts
		if(position == 2 || position == 5 || position == 8) {
			if(fields[position-1].getText().equals(player) && fields[position-2].getText().equals(player)) {
				if(player.equals("O")) {
					currentRating += 75;
				}
				else {
					currentRating += 50;
				}
			}
		}
		return currentRating;
	}
	
	
	//Bewerte die Felder vertikal
	//Wenn 2 Felder O sind --> Rating = 75 --> Angriff
	//Wenn 2 Felder X sind --> Rating = 50 --> Defense
	public int checkVertical(String player, int currentRating, int position) {
		JButton[] fields = gui.getFields();
		
		//Bewerte Vertikal von ganz oben
		if(position == 0 || position == 1 || position == 2) {
			if(fields[position+3].getText().equals(player) && fields[position+6].getText().equals(player)) {
				if(player.equals("O")) {
					currentRating += 75;
				}
				else {
					currentRating += 50;
				}
			}
		}
		
		//Bewerte vertikal von der Mitte
		if(position == 3 || position == 4 || position == 5) {
			if(fields[position-3].getText().equals(player) && fields[position+3].getText().equals(player)) {
				if(player.equals("O")) {
					currentRating += 75;
				}
				else {
					currentRating += 50;
				}
			}
		}
		
		//Bewerte vertikal von ganz unten
		if(position == 6 || position == 7 || position == 8) {
			if(fields[position-3].getText().equals(player) && fields[position-6].getText().equals(player)) {
				if(player.equals("O")) {
					currentRating += 75;
				}
				else {
					currentRating += 50;
				}
			}
		}
		return currentRating;
	}
	
	
	//Bewerte die Felder diagonal
	//Wenn 2 Felder O sind --> Rating = 75 --> Angriff
	//Wenn 2 Felder X sind --> Rating = 50 --> Defense
	public int checkDiagonal(String player, int currentRating, int position) {
		JButton[] fields = gui.getFields();
		
		//Bewerte diagonal oben links
		if(position == 0) {
			if(fields[position+4].getText().equals(player) && fields[position+8].getText().equals(player)) {
				if(player.equals("O")) {
					currentRating += 75;
				}
				else {
					currentRating += 50;
				}
			}
		}
		
		//Bewerte diagonal von oben rechts
		if(position == 2) {
			if(fields[position+2].getText().equals(player) && fields[position+4].getText().equals(player)) {
				if(player.equals("O")) {
					currentRating += 75;
				}
				else {
					currentRating += 50;
				}
			}
		}
		
		//Bewerte diagonal von der Mitte
		if(position == 4) {
			if((fields[position-4].getText().equals(player) && fields[position+4].getText().equals(player)) || (fields[position-2].getText().equals(player) && fields[position+2].getText().equals(player))) {
				if(player.equals("O")) {
					currentRating += 75;
				}
				else {
					currentRating += 50;
				}
			}
		}
		
		//Bewerte diagonal von unten links
		if(position == 6) {
			if(fields[position-2].getText().equals(player) && fields[position-4].getText().equals(player)) {
				if(player.equals("O")) {
					currentRating += 75;
				}
				else {
					currentRating += 50;
				}
			}
		}
		
		//Bewerte diagonal von unten rechts
		if(position == 8) {
			if(fields[position-4].getText().equals(player) && fields[position-8].getText().equals(player)) {
				if(player.equals("O")) {
					currentRating += 75;
				}
				else {
					currentRating += 50;
				}
			}
		}
		return currentRating;
	}
	
	
	//Verteidigung: Bewerte horizontal, vertikal und diagonal ob zwei Felder nacheinander ein X haben
	public int defendLoss(int currentRating, int position) {
		currentRating += checkHorizontal("X", currentRating, position);
		currentRating += checkVertical("X", currentRating, position);
		currentRating += checkDiagonal("X", currentRating, position);
		return currentRating;
	}
	
	
	//Angriff:Bewerte horizontal, vertikal und diagonal ob zwei Felder nacheinander ein O haben
	public int winningMove(int currentRating, int position) {
		currentRating += checkHorizontal("O", currentRating, position);
		currentRating += checkVertical("O", currentRating, position);
		currentRating += checkDiagonal("O", currentRating, position);
		return currentRating;
	}
	
	
	//Bewertet das aktuelle Feld anhand des oberen Feldes
	public int checkUp(String player, int currentRating, int position) {
		JButton[] fields = gui.getFields();
		if(fields[position-3].getText().equals(player)) {
			if(player == "O") {
				currentRating += 5;
			}
		}
		return currentRating;
	}
	
	
	//Bewertet das aktuelle Feld anhand des unteren Feldes
	public int checkDown(String player, int currentRating, int position) {
		JButton[] fields = gui.getFields();
		if(fields[position+3].getText().equals(player)) {
			if(player == "O") {
				currentRating += 5;
			}
		}
		return currentRating;
	}
	
	//Bewertet das aktuelle Feld anhand des linken Feldes
	public int checkLeft(String player, int currentRating, int position) {
		JButton[] fields = gui.getFields();
		if(fields[position-1].getText().equals(player)) {
			if(player == "O") {
				currentRating += 5;
			}
		}
		return currentRating;
	}
	
	//Bewertet das aktuelle Feld anhand des rechten Feldes
	public int checkRight(String player, int currentRating, int position) {
		JButton[] fields = gui.getFields();
		if(fields[position+1].getText().equals(player)) {
			if(player == "O") {
				currentRating += 5;
			}
		}
		return currentRating;
	}
	
	
	//Bewertet ein diagonales Feld mit dem diagonalem Feld, welches 4 Schritte vorwärts liegt
	public int checkDiagonalFourStepsForward(String player, int currentRating, int position) {
		JButton[] fields = gui.getFields();
		if(fields[position+4].getText().equals(player)) {
			if(player == "O") {
				currentRating += 5;
			}
		}
		return currentRating;
	}
	
	
	//Bewertet ein diagonales Feld mit dem diagonalem Feld, welches 4 Schritte rückwärts liegt
	public int checkDiagonalFourStepsBackward(String player, int currentRating, int position) {
		JButton[] fields = gui.getFields();
		if(fields[position-4].getText().equals(player)) {
			if(player == "O") {
				currentRating += 5;
			}
		}
		return currentRating;
	}
	
	
	//Bewertet ein diagonales Feld mit dem diagonalem Feld, welches 2 Schritte vorwärts liegt
	public int checkDiagonalTwoStepsForward(String player, int currentRating, int position) {
		JButton[] fields = gui.getFields();
		if(fields[position+2].getText().equals(player)) {
			if(player == "O") {
				currentRating += 5;
			}
		}
		return currentRating;
	}
	
	
	//Bewertet ein diagonales Feld mit dem diagonalem Feld, welches 2 Schritte rückwärts liegt
	public int checkDiagonalTwoStepsBackward(String player, int currentRating, int position) {
		JButton[] fields = gui.getFields();
		if(fields[position-2].getText().equals(player)) {
			if(player == "O") {
				currentRating += 5;
			}
		}
		return currentRating;
	}
	
	
	public int checkNeighbour(String player, int currentRating, int position) {
		switch(position) {
		case 0:
			currentRating += checkRight(player, currentRating, position);
			currentRating += checkDown(player, currentRating, position);
			currentRating += checkDiagonalFourStepsForward(player, currentRating, position);
			break;
		case 1:
			currentRating += checkRight(player, currentRating, position);
			currentRating += checkLeft(player, currentRating, position);
			currentRating += checkDown(player, currentRating, position);
			break;
		case 2:
			currentRating += checkLeft(player, currentRating, position);
			currentRating += checkDown(player, currentRating, position);
			currentRating += checkDiagonalTwoStepsForward(player, currentRating, position);
			break;
		case 3:
			currentRating += checkRight(player, currentRating, position);
			currentRating += checkUp(player, currentRating, position);
			currentRating += checkDown(player, currentRating, position);
			break;
		case 4:
			currentRating += checkDiagonalFourStepsForward(player, currentRating, position);
			currentRating += checkDiagonalFourStepsBackward(player, currentRating, position);
			currentRating += checkDiagonalTwoStepsForward(player, currentRating, position);
			currentRating += checkDiagonalTwoStepsBackward(player, currentRating, position);
			currentRating += checkDown(player, currentRating, position);
			currentRating += checkUp(player, currentRating, position);
			break;
		case 5:
			currentRating += checkUp(player, currentRating, position);
			currentRating += checkDown(player, currentRating, position);
			currentRating += checkLeft(player, currentRating, position);
			break;
		case 6:
			currentRating += checkUp(player, currentRating, position);
			currentRating += checkRight(player, currentRating, position);
			currentRating += checkDiagonalTwoStepsBackward(player, currentRating, position);
			break;
		case 7:
			currentRating += checkLeft(player, currentRating, position);
			currentRating += checkRight(player, currentRating, position);
			currentRating += checkUp(player, currentRating, position);
			break;
		case 8:
			currentRating += checkUp(player, currentRating, position);
			currentRating += checkLeft(player, currentRating, position);
			currentRating += checkDiagonalFourStepsBackward(player, currentRating, position);
			break;
		}
		return currentRating;
	}
	
	
	
	//Bewertet alle Felder für den CPU Spielzug
	public int[] rateFields() {
	    JButton[] fields = gui.getFields();
	    int[] ratings = new int[9];
	    
	    for(int i=0; i<fields.length; i++) {
	        if(fields[i].getText().isEmpty()) {
	            int currentRating = 0;
	            currentRating += winningMove(currentRating, i);
	            currentRating += defendLoss(currentRating, i);
	            currentRating += checkNeighbour("O", currentRating, i);
	            ratings[i] = currentRating;
	        }
	    }
	    return ratings;
	}
	
	
	//Gibt das höchste Rating im ratings Array zurück
	public int searchBiggestRating(int[] ratings) {
		int biggestRating = 0;
		for(int i=0; i<ratings.length; i++) {
			if(ratings[i] > biggestRating) {
				biggestRating = ratings[i];
			}
		}
		return biggestRating;
	}
	
	
	//Gibt die Position im ratings ArrayOzurück in welcher das größte Rating steht
	public int getPositionOfBiggestRating(int[] ratings, int biggestRating) {
		int position = 0;
		while(ratings[position] != biggestRating) {
			position++;
		}
		return position;
	}
	
	
	//CPU macht anhand der berechneten ratings einen geeigneten Zug
	public int cpuMove(int[] ratings) {
		JButton[] fields = gui.getFields();
		int biggestRating = searchBiggestRating(ratings);
		int position = getPositionOfBiggestRating(ratings, biggestRating);
		fields[position].setText("O");
		return position;
	}
	
	
	public int getPositionOfFirstMove() {
		return positionOfFirstMove;
	}
}
