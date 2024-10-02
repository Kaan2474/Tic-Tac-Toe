import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonFunctionality implements ActionListener {
	private Cpu cpu;
	private GameLogic gameLogic;
	private Gui gui;
	
	public ButtonFunctionality(Gui gui, GameLogic gameLogic, Cpu cpu) {
		this.gui = gui;
		this.gameLogic = gameLogic;
		this.cpu = cpu;
		activateFunctionalityForAllFields();
		deactivateFunctionalityFromField(cpu.getPositionOfFirstMove());
	}

	public void actionPerformed(ActionEvent e) {
		JButton[] fields = gui.getFields();
		for(int i=0;i<fields.length;i++) {
			if(e.getSource() == fields[i]) {
				if(fields[i].getText().isEmpty()) {
					fields[i].setText("X");
					fields[i].removeActionListener(this);
				}
			}
		}
		if(gameLogic.checkWin("X")) {
			gui.move.setText("");
			gui.result.setText("Spieler X hat gewonnen!");
			deactivateFunctionalityFromAllFields();
		}
		else if(gameLogic.checkWin("O")) {
			gui.move.setText("");
			gui.result.setText("Spieler O hat gewonnen!");
			deactivateFunctionalityFromAllFields();
		}
		else if(gameLogic.draw()) {
			gui.move.setText("");
			gui.result.setBounds(120,0,300,100);
			gui.result.setText("Unentschieden!");
			deactivateFunctionalityFromAllFields();
		}
		else {
			int position = cpu.cpuMove(cpu.rateFields());
			deactivateFunctionalityFromField(position);
		}
	}
	
	
	//Deaktiviert alle Knöpfe(wenn Spiel vorbei ist)
	public void deactivateFunctionalityFromAllFields() {
		JButton[] fields = gui.getFields();
		for(int i=0; i<fields.length; i++) {
			fields[i].removeActionListener(this);
		}
	}
	
	
	//Aktiviert alle Knöpfe(wenn Spiel anfängt)
	public void activateFunctionalityForAllFields() {
		JButton[] fields = gui.getFields();
		for(int i=0; i<fields.length; i++) {
			fields[i].addActionListener(this);
		}
	}
	
	public void deactivateFunctionalityFromField(int position) {
		if(position != -1) {
			JButton[] fields = gui.getFields();
			fields[position].removeActionListener(this);
		}
	}
	
	
}
