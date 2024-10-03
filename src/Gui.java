import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Gui {
	private JFrame frame;
	private JButton fields[];
	private JPanel blackPanel, whitePanel;
	private JLabel result, move;
	
	
	
	public Gui() {
	
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
		
		//Container für Spielstatus
		whitePanel = new JPanel();
		whitePanel.setBounds(0,0,500,100);
		whitePanel.setLayout(null);
		whitePanel.setBackground(Color.white);
		
		//Erstellung und Bearbeitung der 9 Felder
		fields = new JButton[9];
		for(int i=0;i<fields.length;i++) {
			fields[i] = new JButton();
			fields[i].setFont(new Font("Arial", Font.BOLD, 50));
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

	}
	
	
	public void setWinningText(String player) {
		if(player == "X") {
			move.setText("");
			result.setText("Spieler X hat gewonnen!");
		}
		else if(player == "O"){
			move.setText("");
			result.setText("Spieler O hat gewonnen!");
		}
		else {
			move.setText("");
			result.setBounds(120,0,300,100);
			result.setText("Unentschieden!");
		}
	}
	
	
	public JButton[] getFields() {
		return fields;
	}
	
	
	public JLabel getResult() {
		return result;
	}
	
	
	public JLabel getMove() {
		return move;
	}
}
