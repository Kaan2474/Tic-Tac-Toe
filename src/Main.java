

public class Main {

	public static void main(String[] args) {
		Gui gui = new Gui();
        GameLogic gameLogic = new GameLogic(gui);
        Cpu cpu = new Cpu(gui, gameLogic);
        ButtonFunctionality buttonHandler = new ButtonFunctionality(gui, gameLogic, cpu);
	}

}
