import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class TicTacToe implements ActionListener {
	JFrame frame;
	JButton fields[];
	JPanel blackPanel, whitePanel;
	JLabel result, move;
	int turn;
	
	
	public TicTacToe() {
		turn = turn();
		frame = new JFrame("Tic Tac Toe");
		frame.setSize(400, 500);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		
		
		//Container für die Felder
		blackPanel = new JPanel();
		blackPanel.setBounds(0, 100, 500, 700);
		blackPanel.setLayout(null);
		blackPanel.setBackground(Color.black);
		
		//Container bzgl. Siege/Unentschieden
		whitePanel = new JPanel();
		whitePanel.setBounds(0,0,500,100);
		whitePanel.setLayout(null);
		whitePanel.setBackground(Color.white);
		
		//Erstellung und Bearbeitung der 9 Felder
		fields = new JButton[9];
		for(int i=0;i<fields.length;i++) {
			fields[i] = new JButton();
			fields[i].setFont(new Font("Arial", Font.BOLD, 50));
			fields[i].addActionListener(this);
		}
		
		//Positionen der Felder
		fields[0].setBounds(50, 50, 100, 100);
		fields[1].setBounds(50, 150, 100, 100);
		fields[2].setBounds(50, 250, 100, 100);
		fields[3].setBounds(150, 50, 100, 100);
		fields[4].setBounds(150, 150, 100, 100);
		fields[5].setBounds(150, 250, 100, 100);
		fields[6].setBounds(250, 50, 100, 100);
		fields[7].setBounds(250, 150, 100, 100);
		fields[8].setBounds(250, 250, 100, 100);
		
		//Felder werden dem schwarzen Container hinzugefügt
		for(int i=0;i<fields.length;i++) {
			blackPanel.add(fields[i]);
		}
		
		
		//Erstellung und Bearbeitung der Benachrichtigungen
		//result = Sieg/Unentschieden
		result = new JLabel();
		result.setBounds(75,0,300,100);
		result.setFont(new Font("Arial", Font.BOLD, 20));
		//move = Aktion
		move = new JLabel();
		move.setBounds(90,0,300,100);
		move.setFont(new Font("Arial", Font.BOLD, 20));
		//Benachrichtigung wird weißem Feld hinzugefügt
		whitePanel.add(result);
		whitePanel.add(move);

		
		//Container dem frame hinzufügen
		frame.add(blackPanel);
		frame.add(whitePanel);

		frame.setVisible(true);
		
		//Falls CPU zuerst dran ist
		if(turn == 1) {
			chooseRandomField();
		}
		move.setText("Wähle ein leeres Feld!");
	}
	
	
	public static void main(String[] args) {
		new TicTacToe();
	}
	
	
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<fields.length;i++) {
			if(e.getSource() == fields[i]) {
				if(fields[i].getText().isEmpty()) {
					fields[i].setText("X");
					fields[i].removeActionListener(this);
				}
			}
		}
		if(winX()) {
			move.setText("");
			result.setText("Spieler X hat gewonnen!");
			deactivateAllButtons();
		}
		if(freeField() && !(winX())) {
			cpuMove(rateFields());
		}
		if(winO()) {
			move.setText("");
			result.setText("Spieler O hat gewonnen!");
			deactivateAllButtons();
		}
		if(draw() && !(winO()) && !(winX())) {
			move.setText("");
			result.setBounds(120,0,300,100);
			result.setText("Unentschieden!");
			deactivateAllButtons();
		}
	}
	
	//0 = Spieler X ist dran, 1 = Spieler O ist dran
	//Gibt zufällig 0 oder 1 aus
	public static int turn() {
		Random r = new Random();
		int number = r.nextInt(2);
		return number;
	}
	
	
	//CPU wählt ein zufälliges Feld(nur bei Spielbeginn)
	public void chooseRandomField() {
		Random r = new Random();
		int number = r.nextInt(9);
		fields[number].setText("O");
		fields[number].removeActionListener(this);
	}
	
	
	//Bewertet Felder für den CPU Spielzug(Offensive)
	public int[] rateFields() {
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
	public void cpuMove(int[] ratings) {
		int biggestRating = 0;
		for(int i=0;i<ratings.length;i++) {
			if(ratings[i] > biggestRating) {
				biggestRating = ratings[i];
			}
		}
		int i = 0;
		while(ratings[i] != biggestRating) {
			i++;
		}
		fields[i].setText("O");
		fields[i].removeActionListener(this);
	}
	
	
	//Prüft ob noch freie Felder existieren
	public boolean freeField() {
		for(int i=0;i<fields.length; i++) {
			if(fields[i].getText().isEmpty()) {
				return true;
			}
		}
		return false;
	}
	
	
	//Deaktiviert alle Knöpfe(wenn Spiel vorbei ist)
	public void deactivateAllButtons() {
		for(int i=0; i<fields.length; i++) {
			fields[i].removeActionListener(this);
		}
	}
	
	
	//Unentschieden
	public boolean draw() {
		for(int i=0;i<fields.length;i++) {
			if(fields[i].getText().isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	
	//Spieler X hat gewonnen
	public boolean winX() {
		if(fields[0].getText().equals("X") && fields[1].getText().equals("X") && fields[2].getText().equals("X")) {
			for(int i=0; i<3; i++) {
				fields[i].setForeground(Color.green);
			}
			return true;
		}
		else if(fields[3].getText().equals("X") && fields[4].getText().equals("X") && fields[5].getText().equals("X")) {
			for(int i=3; i<6; i++) {
				fields[i].setForeground(Color.green);
			}
			return true;
		}
		else if(fields[6].getText().equals("X") && fields[7].getText().equals("X") && fields[8].getText().equals("X")) {
			for(int i=6; i<9; i++) {
				fields[i].setForeground(Color.green);
			}
			return true;
		}
		else if(fields[0].getText().equals("X") && fields[3].getText().equals("X") && fields[6].getText().equals("X")) {
			for(int i=0;i<7;i+=3) {
				fields[i].setForeground(Color.green);
			}
			return true;
		}
		else if(fields[1].getText().equals("X") && fields[4].getText().equals("X") && fields[7].getText().equals("X")) {
			for(int i=1;i<8;i+=3) {
				fields[i].setForeground(Color.green);
			}
			return true;
		}
		else if(fields[2].getText().equals("X") && fields[5].getText().equals("X") && fields[8].getText().equals("X")) {
			for(int i=2;i<9;i+=3) {
				fields[i].setForeground(Color.green);
			}
			return true;
		}
		else if(fields[0].getText().equals("X") && fields[4].getText().equals("X") && fields[8].getText().equals("X")) {
			for(int i=0;i<9;i+=4) {
				fields[i].setForeground(Color.green);
			}
			return true;
		}
		else if(fields[2].getText().equals("X") && fields[4].getText().equals("X") && fields[6].getText().equals("X")) {
			for(int i=2;i<7;i+=2) {
				fields[i].setForeground(Color.green);
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	
	//Spieler O hat gewonnen
	public boolean winO() {
		if(fields[0].getText().equals("O") && fields[1].getText().equals("O") && fields[2].getText().equals("O")) {
			for(int i=0; i<3; i++) {
				fields[i].setForeground(Color.red);
			}
			return true;
		}
		else if(fields[3].getText().equals("O") && fields[4].getText().equals("O") && fields[5].getText().equals("O")) {
			for(int i=3; i<6; i++) {
				fields[i].setForeground(Color.red);
			}
			return true;
		}
		else if(fields[6].getText().equals("O") && fields[7].getText().equals("O") && fields[8].getText().equals("O")) {
			for(int i=6; i<9; i++) {
				fields[i].setForeground(Color.red);
			}
			return true;
		}
		else if(fields[0].getText().equals("O") && fields[3].getText().equals("O") && fields[6].getText().equals("O")) {
			for(int i=0;i<7;i+=3) {
				fields[i].setForeground(Color.red);
			}
			return true;
		}
		else if(fields[1].getText().equals("O") && fields[4].getText().equals("O") && fields[7].getText().equals("O")) {
			for(int i=1;i<8;i+=3) {
				fields[i].setForeground(Color.red);
			}
			return true;
		}
		else if(fields[2].getText().equals("O") && fields[5].getText().equals("O") && fields[8].getText().equals("O")) {
			for(int i=2;i<9;i+=3) {
				fields[i].setForeground(Color.red);
			}
			return true;
		}
		else if(fields[0].getText().equals("O") && fields[4].getText().equals("O") && fields[8].getText().equals("O")) {
			for(int i=0;i<9;i+=4) {
				fields[i].setForeground(Color.red);
			}
			return true;
		}
		else if(fields[2].getText().equals("O") && fields[4].getText().equals("O") && fields[6].getText().equals("O")) {
			for(int i=2;i<7;i+=2) {
				fields[i].setForeground(Color.red);
			}
			return true;
		}
		else {
			return false;
		}
	}
}
