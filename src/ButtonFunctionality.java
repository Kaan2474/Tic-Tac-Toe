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
			gui.setWinningText("X");
			deactivateFunctionalityFromAllFields();
		}
		else {
			int[] ratings = cpu.rateFields();
			int position = cpu.cpuMove(ratings);
			deactivateFunctionalityFromField(position);
			if(gameLogic.checkWin("O")) {
				gui.setWinningText("O");
				deactivateFunctionalityFromAllFields();
			}
			else if(gameLogic.draw()) {
				gui.setWinningText("draw");
				deactivateFunctionalityFromAllFields();
			}
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
	
	//Sorgt dafür, dass ein Feld an der übergebenen Stelle nicht mehr ankilckbar ist
	public void deactivateFunctionalityFromField(int position) {
		//Nur ausführen wenn position eine Zahl von 0-9 ist
		int[] validPosition = {0, 1, 2, 3, 4, 5, 6, 7, 8};
		for(int i = 0; i<validPosition.length; i++) {
			if(validPosition[i] == position) {
				JButton[] fields = gui.getFields();
				fields[position].removeActionListener(this);
			}
		}
	}
	
	
}
