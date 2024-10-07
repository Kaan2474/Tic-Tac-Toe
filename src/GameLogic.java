import java.awt.Color;
import java.util.Random;
import javax.swing.JButton;

public class GameLogic {
	private Gui gui;
	private int turn;
	
	public GameLogic(Gui gui) {
		turn = createRandomTurn();
		this.gui = gui;
	}
	
	
	//0 = Spieler X ist dran, 1 = Spieler O ist dran
	//Gibt zufällig 0 oder 1 aus
	public int createRandomTurn() {
		Random r = new Random();
		int number = r.nextInt(2);
		return number;
	}
	
	
	//Prüft ob noch freie Felder existieren
	public boolean freeField() {
		JButton[] fields = gui.getFields();
		for(int i=0;i<fields.length; i++) {
			if(fields[i].getText().isEmpty()) {
				return true;
			}
		}
		return false;
	}
	
	
	
	//Prüft ob das Spiel unentschieden ist
	public boolean draw() {
		JButton[] fields = gui.getFields();
		for(int i=0;i<fields.length;i++) {
			if(fields[i].getText().isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	
	//Prüft ob ein Spieler gewonnen hat
	public boolean checkWin(String player) {
		JButton[] fields = gui.getFields();
	    int[][] winConditions = {
	        {0, 1, 2}, {3, 4, 5}, {6, 7, 8},  // Reihen
	        {0, 3, 6}, {1, 4, 7}, {2, 5, 8},  // Spalten
	        {0, 4, 8}, {2, 4, 6}              // Diagonalen
	    };
	    
	    for (int[] condition : winConditions) {
	        if (fields[condition[0]].getText().equals(player) &&
	            fields[condition[1]].getText().equals(player) &&
	            fields[condition[2]].getText().equals(player)) {
	            
	            // Farbänderung für die Gewinnfelder
	            for (int i : condition) {
	                fields[i].setForeground(player.equals("X") ? Color.green : Color.red);
	            }
	            return true;
	        }
	    }
	    return false;
	}
	
	
	//Getter und Setter Methoden
	public int getTurn() {
		return turn;
	}
	
	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	
}
