

public class Main {

	public static void main(String[] args) {
		Gui gui = new Gui();
        GameLogic gameLogic = new GameLogic(gui);
        Cpu cpu = new Cpu(gui, gameLogic);
        FieldFunctionality fieldFunctionality = new FieldFunctionality(gui, gameLogic, cpu);
        RestartButtonFunctionality restartButtonFunctionality = new RestartButtonFunctionality(gui, gameLogic, cpu, fieldFunctionality);
	}

}
