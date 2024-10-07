import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class FieldFunctionality implements ActionListener {
	private Cpu cpu;
	private GameLogic gameLogic;
	private Gui gui;
	
	public FieldFunctionality(Gui gui, GameLogic gameLogic, Cpu cpu) {
		this.gui = gui;
		this.gameLogic = gameLogic;
		this.cpu = cpu;
		activateFunctionalityOfFreeFields();
	}

	public void actionPerformed(ActionEvent e) {
		JButton[] fields = gui.getFields();
		for(int i=0;i<fields.length;i++) {
			if(e.getSource() == fields[i]) {
				if(fields[i].getText().isEmpty()) {
					fields[i].setText("X");
					deactivateFunctionalityFromAllFields();
				}
			}
		}
		if(gameLogic.checkWin("X")) {
			gui.setTurnStatus("");
			gui.setTextAfterGame("X");
		}
		else {
			gui.setTurnStatus("Spieler O ist dran!");
            // CPU-Zug in einem separaten Thread ausführen
            new Thread(() -> {
                try {
                    // Verzögerung nur für den CPU-Zug
                    Thread.sleep(1500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                
                // CPU Zug nach der Verzögerung
                int[] ratedFields = cpu.rateFields();
                cpu.cpuMove(ratedFields);
                
                // Zurück in den Event-Dispatch-Thread wechseln, um die GUI zu aktualisieren
                javax.swing.SwingUtilities.invokeLater(() -> {
                    if (gameLogic.checkWin("O")) {
                    	gui.setTurnStatus("");
                        gui.setTextAfterGame("O");
                    } else if (gameLogic.draw()) {
                    	gui.setTurnStatus("");
                        gui.setTextAfterGame("draw");
                    }
                    else {
                    	gui.setTurnStatus("Wähle ein leeres Feld!");
                    	activateFunctionalityOfFreeFields();
                    }
                });
            }).start();
        }
	}
	
	
	//Deaktiviert alle Knöpfe(wenn Spiel vorbei ist)
	public void deactivateFunctionalityFromAllFields() {
		JButton[] fields = gui.getFields();
		for(int i=0; i<fields.length; i++) {
			fields[i].removeActionListener(this);
		}
	}
	
	
	//Aktiviert die Funktionalität für alle freien Felder
	public void activateFunctionalityOfFreeFields() {
		JButton[] fields = gui.getFields();
		for(int i = 0; i<fields.length; i++) {
			if(fields[i].getText().isEmpty()) {
				fields[i].addActionListener(this);
			}
		}
	}
	
	
}
