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
	
	
	//Bewerte die Felder horizontal
	//Wenn 2 Felder X sind --> Rating = 75
	//Wenn 2 Felder O sind --> Rating = 50
	//Fokus auf Defense
	public int checkHorizontal(JButton[] fields, String player, int currentRating, int position) {
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
	//Wenn 2 Felder X sind --> Rating = 20
	//Wenn 2 Felder O sind --> Rating = 10
	//Fokus auf Defense
	public int checkVertical(JButton[] fields, String player, int currentRating, int position) {
		//Bewerte Vertical von ganz oben
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
	//Wenn 2 Felder X sind --> Rating = 20
	//Wenn 2 Felder O sind --> Rating = 10
	//Fokus auf Defense
	public int checkDiagonal(JButton[] fields, String player, int currentRating, int position) {
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
	public int defendLoss(JButton[] fields, int currentRating, int position) {
		int rating = 0;
		rating += checkHorizontal(fields, "X", rating, position);
		rating += checkVertical(fields, "X", rating, position);
		rating += checkDiagonal(fields, "X", rating, position);
		return rating;
	}
	
	
	//Angriff:Bewerte horizontal, vertikal und diagonal ob zwei Felder nacheinander ein O haben
	public int winningMove(JButton[] fields, int currentRating, int position) {
		int rating = 0;
		rating += checkHorizontal(fields, "O", rating, position);
		rating += checkVertical(fields, "O", rating, position);
		rating += checkDiagonal(fields, "O", rating, position);
		return rating;
	}
	
	
	//Bewertet das aktuelle Feld anhand des unteren Feldes
	public int checkUp(JButton[] fields, String player, int currentRating, int position) {
		if(fields[position-3].getText().equals(player)) {
			if(player == "O") {
				currentRating += 5;
			}
		}
		return currentRating;
	}
	
	
	//Bewertet das aktuelle Feld anhand des oberen Feldes
	public int checkDown(JButton[] fields, String player, int currentRating, int position) {
		if(fields[position+3].getText().equals(player)) {
			if(player == "O") {
				currentRating += 5;
			}
		}
		return currentRating;
	}
	
	//Bewertet das aktuelle Feld anhand des linken Feldes
	public int checkLeft(JButton[] fields, String player, int currentRating, int position) {
		if(fields[position-1].getText().equals(player)) {
			if(player == "O") {
				currentRating += 5;
			}
		}
		return currentRating;
	}
	
	//Bewertet das aktuelle Feld anhand des linken Feldes
	public int checkRight(JButton[] fields, String player, int currentRating, int position) {
		if(fields[position+1].getText().equals(player)) {
			if(player == "O") {
				currentRating += 5;
			}
		}
		return currentRating;
	}
	
	public int a(JButton[] fields, String player, int currentRating, int position) {
		if(fields[position+4].getText().equals(player)) {
			if(player == "O") {
				currentRating += 5;
			}
		}
		return currentRating;
	}
	
	
	public int b(JButton[] fields, String player, int currentRating, int position) {
		if(fields[position-4].getText().equals(player)) {
			if(player == "O") {
				currentRating += 5;
			}
		}
		return currentRating;
	}
	
	
	public int c(JButton[] fields, String player, int currentRating, int position) {
		if(fields[position+2].getText().equals(player)) {
			if(player == "O") {
				currentRating += 5;
			}
		}
		return currentRating;
	}
	
	public int d(JButton[] fields, String player, int currentRating, int position) {
		if(fields[position-2].getText().equals(player)) {
			if(player == "O") {
				currentRating += 5;
			}
		}
		return currentRating;
	}
	
	
	public int checkNeighbour(JButton[] fields, String player, int currentRating, int position) {
		int rating = 0;
		switch(position) {
		case 0:
			rating += checkRight(fields, player, currentRating, position);
			rating += checkDown(fields, player, currentRating, position);
			rating += a(fields, player, currentRating, position);
			break;
		case 1:
			rating += checkRight(fields, player, currentRating, position);
			rating += checkLeft(fields, player, currentRating, position);
			rating += checkDown(fields, player, currentRating, position);
			break;
		case 2:
			rating += checkLeft(fields, player, currentRating, position);
			rating += checkDown(fields, player, currentRating, position);
			rating += c(fields, player, currentRating, position);
			break;
		case 3:
			rating += checkRight(fields, player, currentRating, position);
			rating += checkUp(fields, player, currentRating, position);
			rating += checkDown(fields, player, currentRating, position);
			break;
		case 4:
			rating += a(fields, player, currentRating, position);
			rating += b(fields, player, currentRating, position);
			rating += c(fields, player, currentRating, position);
			rating += d(fields, player, currentRating, position);
			rating += checkDown(fields, player, currentRating, position);
			rating += checkUp(fields, player, currentRating, position);
			break;
		case 5:
			rating += checkUp(fields, player, currentRating, position);
			rating += checkDown(fields, player, currentRating, position);
			rating += checkLeft(fields, player, currentRating, position);
			break;
		case 6:
			rating += checkUp(fields, player, currentRating, position);
			rating += checkRight(fields, player, currentRating, position);
			rating += d(fields, player, currentRating, position);
			break;
		case 7:
			rating += checkLeft(fields, player, currentRating, position);
			rating += checkRight(fields, player, currentRating, position);
			rating += checkUp(fields, player, currentRating, position);
			break;
		case 8:
			rating += checkUp(fields, player, currentRating, position);
			rating += checkLeft(fields, player, currentRating, position);
			rating += b(fields, player, currentRating, position);
			break;
		}
		return rating;
	}
	
	
	
	//Bewertet Felder für den CPU Spielzug(Offensive)
	public int[] rateFields() {
		JButton[] fields = gui.getFields();
		int[] ratings = new int[9];
		int currentRating = 0;
		//Bewertung von Feld 0
		if(fields[0].getText().isEmpty()) {
			currentRating += winningMove(fields, currentRating, 0);
			currentRating += defendLoss(fields, currentRating, 0);
			currentRating += checkNeighbour(fields, "O", currentRating, 0);
		}
		ratings[0] = currentRating;
		currentRating = 0;
		
		//Bewertung von Feld 1
		if(fields[1].getText().isEmpty()) {
			currentRating += winningMove(fields, currentRating, 1);
			currentRating += defendLoss(fields, currentRating, 1);
			currentRating += checkNeighbour(fields, "O", currentRating, 1);
		}
		ratings[1] = currentRating;
		currentRating = 0;
		
		//Bewertung von Feld 2
		if(fields[2].getText().isEmpty()) {
			currentRating += winningMove(fields, currentRating, 2);
			currentRating += defendLoss(fields, currentRating, 2);
			currentRating += checkNeighbour(fields, "O", currentRating, 2);
		}
		ratings[2] = currentRating;
		currentRating = 0;
		
		//Bewertung von Feld 3
		if(fields[3].getText().isEmpty()) {
			currentRating += winningMove(fields, currentRating, 3);
			currentRating += defendLoss(fields, currentRating, 3);
			currentRating += checkNeighbour(fields, "O", currentRating, 3);
		}
		ratings[3] = currentRating;
		currentRating = 0;
		
		//Bewertung von Feld 4
		if(fields[4].getText().isEmpty()) {
			currentRating += winningMove(fields, currentRating, 4);
			currentRating += defendLoss(fields, currentRating, 4);
			currentRating += checkNeighbour(fields, "O", currentRating, 4);
		}
		ratings[4] = currentRating;
		currentRating = 0;
		
		//Bewertung von Feld 5
		if(fields[5].getText().isEmpty()) {
			currentRating += winningMove(fields, currentRating, 5);
			currentRating += defendLoss(fields, currentRating, 5);
			currentRating += checkNeighbour(fields, "O", currentRating, 5);
		}
		ratings[5] = currentRating;
		currentRating = 0;
		
		//Bewertung von Feld 6
		if(fields[6].getText().isEmpty()) {
			currentRating += winningMove(fields, currentRating, 6);
			currentRating += defendLoss(fields, currentRating, 6);
			currentRating += checkNeighbour(fields, "O", currentRating, 6);
		}
		ratings[6] = currentRating;
		currentRating = 0;
		
		//Bewertung von Feld 7
		if(fields[7].getText().isEmpty()) {
			currentRating += winningMove(fields, currentRating, 7);
			currentRating += defendLoss(fields, currentRating, 7);
			currentRating += checkNeighbour(fields, "O", currentRating, 7);
		}
		ratings[7] = currentRating;
		currentRating = 0;
		
		//Bewertung von Feld 8
		if(fields[8].getText().isEmpty()) {
			currentRating += winningMove(fields, currentRating, 8);
			currentRating += defendLoss(fields, currentRating, 8);
			currentRating += checkNeighbour(fields, "O", currentRating, 8);
		}
		ratings[8] = currentRating;
		return ratings;
	}
	
	
	//Gibt das höchste Rating im ratings Array zurück
	public int searchBiggestRating(int[] ratings) {
		int biggestRating = 0;
		for(int i=0;i<ratings.length;i++) {
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
