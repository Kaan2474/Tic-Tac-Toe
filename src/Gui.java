import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Gui {
	private JFrame frame;
	private JButton fields[];
	private JButton restartButton;
	private JPanel blackPanel, whitePanel;
	private JLabel gameResult, turnStatus;
	
	
	
	public Gui() {
	
		frame = new JFrame("Tic Tac Toe");
		frame.setSize(400, 600);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		
		//Container für die Spielfelder
		blackPanel = new JPanel();
		blackPanel.setBounds(0, 100, 500, 700);
		blackPanel.setLayout(null);
		blackPanel.setBackground(Color.black);
		
		//Container für Spielstatus
		whitePanel = new JPanel();
		whitePanel.setBounds(0,0,500,100);
		whitePanel.setLayout(null);
		whitePanel.setBackground(Color.white);
		
		//Erstellung und Styling der 9 Spielfelder
		createFields();
		styleFields();
		//Setzt die Position der Felder
		setPositionsOfFields();
		
		//Felder werden dem schwarzen Container hinzugefügt
		for(int i=0;i<fields.length;i++) {
			blackPanel.add(fields[i]);
		}
		
		//Erstellung des Buttons für Neustart
		restartButton = new JButton("Neustart");
		restartButton.setBounds(140, 400, 125, 25);
		blackPanel.add(restartButton);
		
		
		//Erstellung und Bearbeitung der Benachrichtigungen
		
		//gameResult -->  Signalisiert ob Sieg, Niederlage oder unentschieden
		gameResult = new JLabel();
		gameResult.setBounds(75,0,300,100);
		gameResult.setFont(new Font("Arial", Font.BOLD, 20));
		
		//turnStatus --> Signalisiert wer am Zug ist
		turnStatus = new JLabel();
		turnStatus.setBounds(90,0,300,100);
		turnStatus.setFont(new Font("Arial", Font.BOLD, 20));
		
		//Benachrichtigungen werden dem weißem Feld hinzugefügt
		whitePanel.add(gameResult);
		whitePanel.add(turnStatus);
		
		//Container dem frame hinzufügen
		frame.add(blackPanel);
		frame.add(whitePanel);
		
		frame.setVisible(true);

	}
	
	
	//Erstellung der 9 Felder
	public void createFields() {
		fields = new JButton[9];
		for(int i=0; i<fields.length; i++) {
			fields[i] = new JButton();
		}
	}
	
	
	//Styling der 9 Felder
	public void styleFields() {
		for(int i=0; i<fields.length; i++) {
			fields[i].setFont(new Font("Arial", Font.BOLD, 50));
		}
	}
	
	
	public void setPositionsOfFields() {
		int width = 100;
		int height = 100;
		fields[0].setBounds(50, 50, width, height);
		fields[1].setBounds(50, 150, width, height);
		fields[2].setBounds(50, 250, width, height);
		fields[3].setBounds(150, 50, width, height);
		fields[4].setBounds(150, 150, width, height);
		fields[5].setBounds(150, 250, width, height);
		fields[6].setBounds(250, 50, width, height);
		fields[7].setBounds(250, 150, width, height);
		fields[8].setBounds(250, 250, width, height);
	}
	
	
	//Löscht die Eingaben aus allen Feldern
	public void resetAllFields() {
		for(int i = 0; i<fields.length; i++) {
			fields[i].setText("");
		}
	}
	
	
	public void setTextAfterGame(String player) {
		if(player == "X") {
			setTurnStatus("");
			gameResult.setText("Spieler X hat gewonnen!");
		}
		else if(player == "O"){
			setTurnStatus("");
			gameResult.setText("Spieler O hat gewonnen!");
		}
		else {
			setTurnStatus("");
			gameResult.setBounds(120,0,300,100);
			gameResult.setText("Unentschieden!");
		}
	}
	
	
	//Getter und Setter Methoden
	
	public JButton[] getFields() {
		return fields;
	}
	
	
	public JButton getRestartButton() {
		return restartButton;
	}
	
	public void setGameResult(String text) {
		gameResult.setText(text);
	}
	
	public void setTurnStatus(String text) {
		turnStatus.setText(text);
	}
	
	public JLabel getTurnStatus() {
		return turnStatus;
	}
	
}
