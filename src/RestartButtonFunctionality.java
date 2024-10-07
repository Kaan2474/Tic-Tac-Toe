import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class RestartButtonFunctionality implements ActionListener {
	private Gui gui;
	private GameLogic gameLogic;
	private Cpu cpu;
	private FieldFunctionality fieldFunctionality;
	
	public RestartButtonFunctionality(Gui gui, GameLogic gameLogic, Cpu cpu,  FieldFunctionality fieldFunctionality) {
		this.gui = gui;
		this.gameLogic = gameLogic;
		this.cpu = cpu;
		this.fieldFunctionality = fieldFunctionality;
		
		activateRestartButton();
	}

	public void actionPerformed(ActionEvent e) {
		JButton restartButton = gui.getRestartButton();
		if(e.getSource() == restartButton) {
			//Setze alle Felder zurück
			gui.resetAllFields();
			gui.setGameResult("");
			gui.setTurnStatus("");
			//Kreiere einen neuen Zug
			int newTurn = gameLogic.createRandomTurn();
			//Cpu macht den ersten Zug, wenn die Cpu anfangen darf
			cpu.doFirstMove();
			//Alle Felder erlangen die Funktionalität zurück
			fieldFunctionality.activateFunctionalityOfFreeFields();
		}
		
	}
	
	public void activateRestartButton() {
		JButton restartButton = gui.getRestartButton();
		restartButton.addActionListener(this);
	}

}
