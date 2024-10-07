import java.util.Random;
import javax.swing.JButton;

public class Cpu {
	private Gui gui;
	private GameLogic gameLogic;
	
	public Cpu(Gui gui, GameLogic gameLogic) {
		this.gui = gui;
		this.gameLogic = gameLogic;
		doFirstMove();
	}
	
	
	//CPU wählt ein zufälliges Feld(nur wenn CPU anfängt)
	public void doFirstMove() {
		//Wenn die Variable turn = 1 ist, dann fängt die Cpu an
		if(gameLogic.getTurn() == 1) {
			gui.setTurnStatus("Spieler O ist dran!");
			JButton[] fields = gui.getFields();
			Random r = new Random();
			//Wähle ein zufälliges Feld aus mit Verzögerung
			int randomPosition = r.nextInt(9);
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			fields[randomPosition].setText("O");
			gui.setTurnStatus("Wähle ein leeres Feld!");
		}
		else {
			gui.setTurnStatus("Wähle ein leeres Feld!");
		}
	}
	
	
	//Analysiere alle Felder vertikal ob es zwei gleiche, nacheinanderfolgende Zeichen gibt
	//Wenn 2 Felder O sind --> Rating 75
	//Wenn 2 Felder X sind --> Rating 50 
	public int checkVertical(String player, int currentRating, int position) {
		JButton[] fields = gui.getFields();
		
		//Bewerte vertikal von oben
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
		
		//Bewerte vertikal von der Mitte
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
		
		//Bewerte vertikal von unten
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
	
	
	//Analysiere alle Felder horizontal ob es zwei gleiche, nacheinanderfolgende Zeichen gibt
	//Wenn 2 Felder O sind --> Rating 75
	//Wenn 2 Felder X sind --> Rating 50 
	public int checkHorizontal(String player, int currentRating, int position) {
		JButton[] fields = gui.getFields();
		
		//Bewerte horizontal von ganz links
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
		
		//Bewerte horizontal von der Mitte
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
		
		//Bewerte horizontal von ganz rechts
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
	
	
	//Analysiere alle Felder diagonal ob es zwei gleiche, nacheinanderfolgende Zeichen gibt
	//Wenn 2 Felder O sind --> Rating 75
	//Wenn 2 Felder X sind --> Rating 50 
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
		
		//Bewerte diagonal von unten links
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
		
		//Bewerte diagonal von oben rechts
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
	
	
	//Angriff: Analysiere vertikal, horizontal und diagonal ob ein Zug vorliegt, der zum Sieg führt
	public int analyzeMoveToWin(int currentRating, int position) {
		currentRating += checkVertical("O", currentRating, position);
		currentRating += checkHorizontal("O", currentRating, position);
		currentRating += checkDiagonal("O", currentRating, position);
		return currentRating;
	}
	
	
	//Verteidigung: Analysiere vertikal, horizontal und diagonal ob ein Zug vorliegt, der zur Niederlage führt
	public int analyzeMoveToDefendLoss(int currentRating, int position) {
		currentRating += checkVertical("X", currentRating, position);
		currentRating += checkHorizontal("X", currentRating, position);
		currentRating += checkDiagonal("X", currentRating, position);
		return currentRating;
	}
	
	
	//Bewertet das aktuelle Feld anhand des oberen Feldes
	public int checkUp(String player, int currentRating, int position) {
		JButton[] fields = gui.getFields();
		if(fields[position-1].getText().equals(player)) {
			if(player == "O") {
				currentRating += 5;
			}
		}
		return currentRating;
	}
	
	
	//Bewertet das aktuelle Feld anhand des unteren Feldes
	public int checkDown(String player, int currentRating, int position) {
		JButton[] fields = gui.getFields();
		if(fields[position+1].getText().equals(player)) {
			if(player == "O") {
				currentRating += 5;
			}
		}
		return currentRating;
	}
	
	
	//Bewertet das aktuelle Feld anhand des linken Feldes
	public int checkLeft(String player, int currentRating, int position) {
		JButton[] fields = gui.getFields();
		if(fields[position-3].getText().equals(player)) {
			if(player == "O") {
				currentRating += 5;
			}
		}
		return currentRating;
	}
	
	
	//Bewertet das aktuelle Feld anhand des rechten Feldes
	public int checkRight(String player, int currentRating, int position) {
		JButton[] fields = gui.getFields();
		if(fields[position+3].getText().equals(player)) {
			if(player == "O") {
				currentRating += 5;
			}
		}
		return currentRating;
	}
	
	
	//Bewertet ein Feld anhand des diagonalen Felds, welches 4 Schritte vorwärts liegt
	public int checkDiagonalFourStepsForward(String player, int currentRating, int position) {
		JButton[] fields = gui.getFields();
		if(fields[position+4].getText().equals(player)) {
			if(player == "O") {
				currentRating += 5;
			}
		}
		return currentRating;
	}
	
	
	//Bewertet ein Feld anhand des diagonalen Felds, welches 4 Schritte rückwärts liegt
	public int checkDiagonalFourStepsBackward(String player, int currentRating, int position) {
		JButton[] fields = gui.getFields();
		if(fields[position-4].getText().equals(player)) {
			if(player == "O") {
				currentRating += 5;
			}
		}
		return currentRating;
	}
	
	
	//Bewertet ein Feld anhand des diagonalen Felds, welches 2 Schritte vorwärts liegt
	public int checkDiagonalTwoStepsForward(String player, int currentRating, int position) {
		JButton[] fields = gui.getFields();
		if(fields[position+2].getText().equals(player)) {
			if(player == "O") {
				currentRating += 5;
			}
		}
		return currentRating;
	}
	
	
	//Bewertet ein Feld anhand des diagonalen Felds, welches 2 Schritte rückwärts liegt
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
			currentRating += checkRight(player, currentRating, 0);
			currentRating += checkDown(player, currentRating, 0);
			currentRating += checkDiagonalFourStepsForward(player, currentRating, 0);
			break;
		case 1:
			currentRating += checkRight(player, currentRating, 1);
			currentRating += checkUp(player, currentRating, 1);
			currentRating += checkDown(player, currentRating, 1);
			break;
		case 2:
			currentRating += checkUp(player, currentRating, 2);
			currentRating += checkRight(player, currentRating, 2);
			currentRating += checkDiagonalTwoStepsForward(player, currentRating, 2);
			break;
		case 3:
			currentRating += checkRight(player, currentRating, 3);
			currentRating += checkLeft(player, currentRating, 3);
			currentRating += checkDown(player, currentRating, 3);
			break;
		case 4:
			currentRating += checkDiagonalFourStepsForward(player, currentRating, 4);
			currentRating += checkDiagonalFourStepsBackward(player, currentRating, 4);
			currentRating += checkDiagonalTwoStepsForward(player, currentRating, 4);
			currentRating += checkDiagonalTwoStepsBackward(player, currentRating, 4);
			currentRating += checkDown(player, currentRating, 4);
			currentRating += checkUp(player, currentRating, 4);
			currentRating += checkLeft(player, currentRating, 4);
			currentRating += checkRight(player, currentRating, 4);
			break;
		case 5:
			currentRating += checkUp(player, currentRating, 5);
			currentRating += checkLeft(player, currentRating, 5);
			currentRating += checkRight(player, currentRating, 5);
			break;
		case 6:
			currentRating += checkLeft(player, currentRating, 6);
			currentRating += checkDown(player, currentRating, 6);
			currentRating += checkDiagonalTwoStepsBackward(player, currentRating, 6);
			break;
		case 7:
			currentRating += checkLeft(player, currentRating, 7);
			currentRating += checkDown(player, currentRating, 7);
			currentRating += checkUp(player, currentRating, 7);
			break;
		case 8:
			currentRating += checkUp(player, currentRating, 8);
			currentRating += checkLeft(player, currentRating, 8);
			currentRating += checkDiagonalFourStepsBackward(player, currentRating, 8);
			break;
		}
		return currentRating;
	}
	
	
	
	//Bewertet alle Felder für den CPU Spielzug
	public int[] rateFields() {
	    JButton[] fields = gui.getFields();
	    int[] ratings = new int[9];
	    
	    for(int position=0; position<fields.length; position++) {
	        if(fields[position].getText().isEmpty()) {
	            int currentRating = 0;
	            currentRating += analyzeMoveToWin(currentRating, position);
	            currentRating += analyzeMoveToDefendLoss(currentRating, position);
	            currentRating += checkNeighbour("O", currentRating, position);
	            ratings[position] = currentRating;
	        }
	        //Wenn ein Feld bereits belegt ist, gibt es ein negatives Rating -5
	        else {
	        	ratings[position] = -5;
	        }
	    }
	    return ratings;
	}
	
	
	//Gibt das höchste Rating zurück
	public int searchBiggestRating(int[] ratings) {
		int biggestRating = 0;
		for(int i=0; i<ratings.length; i++) {
			if(ratings[i] > biggestRating) {
				biggestRating = ratings[i];
			}
		}
		return biggestRating;
	}
	
	
	//Zählt wie oft das höchste Rating vorkommt
	public int countOccurenceOfRating(int biggestRating, int[] ratings) {
		int occurenceOfRating = 0;
		for(int i = 0; i<ratings.length; i++) {
			if(ratings[i] == biggestRating) {
				occurenceOfRating++;
			}
		}
		return occurenceOfRating;
	}
	
	
	//Gibt die potentiellen Positionen zurück, welche die CPU anhand der Ratings machen kann
	public int[] getPotentialPositionsOfCpu(int occurencesOfBiggestRating, int biggestRating, int[] ratings) { 
		int[] potentialPositions = new int[occurencesOfBiggestRating];
		int i = 0;
		while(i<potentialPositions.length) {
			for(int j = 0; j<ratings.length; j++) {
				if(ratings[j] == biggestRating) {
					potentialPositions[i] = j;
					i++;
				}
			}
		}
		//Debugging 
		for (int j = 0; j<potentialPositions.length; j++) {
			System.out.println("Potentielle Position: " + potentialPositions[j]);
		}
		return potentialPositions;
	}
	
	
	//Wählt die Position für den Cpu Move anhand aller potentiellen Positionen
	public int choosePositionOfCpu(int[] potentialPositions) {
		Random r = new Random();
		int position;
		//Wenn es nur ein höchstes Rating gibt
		if(potentialPositions.length == 1) {
			position = 0;
			//Debugging
			System.out.println("Gewählte Position aus einer Alternative: " + potentialPositions[position]);
			return potentialPositions[position];
		}
		//Wenn es mehrere höchste Ratings gibt
		else {
			position = r.nextInt(potentialPositions.length);
			//Debugging
			System.out.println("Gewählte Position aus mehreren Alternativen: " + potentialPositions[position]);
			return potentialPositions[position];
		}
	}
	
	
	//CPU macht anhand der berechneten ratings einen geeigneten Zug
	public void cpuMove(int[] ratings) {
		JButton[] fields = gui.getFields();
		int biggestRating = searchBiggestRating(ratings);
		int occurenceOfBiggestRating = countOccurenceOfRating(biggestRating, ratings);
		int[] potentialPositions = getPotentialPositionsOfCpu(occurenceOfBiggestRating, biggestRating, ratings);
		int position = choosePositionOfCpu(potentialPositions);
		fields[position].setText("O");
	}
	
}
