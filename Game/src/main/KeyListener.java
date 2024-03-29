package main;

import com.asecave.Console;

public class KeyListener {

	Player player;
	Console console;
	Menu menu;
	Screen screen;
	Map map;
	boolean run = true;
	String whatIsRunning;

// Setter for Listen
	public void setRun(boolean pRun) {
		run = pRun;
		if (pRun) {
			gameLoop();
		}
	}

// new Reference to the class Player
	public void newPlayerReference(Player pPlayer) {
		player = pPlayer;
	}

// new Reference to the class Menu
	public void newMenuReference(Menu pMenu) {
		menu = pMenu;
	}

// new Reference to the class Console
	public void newConsoleReference(Console pConsole) {
		console = pConsole;
	}

	// new Reference to the Class Map
	public void newMapReference(Map pMap) {
		map = pMap;
	}

// new Reference to the class Screen
	public void newScreenReference(Screen pScreen) {
		screen = pScreen;
	}

// Method delay to create a delay
	private void delay(int pDelay) {
		try {
			Thread.sleep(pDelay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

// listens to the received Keypresses from the imported class Console and
// executes doAction() or moveCursor() if the player pressed something
	public void gameLoop() {
		char inputChar;
		while (run) {
			inputChar = console.getInputChar();
			if ((inputChar != 0 || player.currentInput != 0) && whatIsRunning.equals("gameRunning")) {
				screen.switchConsoleSettings(false);
				player.doAction(inputChar);
			}
			if (whatIsRunning.equals("gameRunning")) {
				executeSpikeTimer();
				executeBallTimer();
				player.tailTimer();
				delay(player.gameDelay);
				if (menu.currentColor == 16) {
					screen.reprintPlayer();
				}
			}
			if (inputChar != 0 && !whatIsRunning.equals("gameRunning")) {
				screen.switchConsoleSettings(true);
				menu.moveCursor(inputChar);
			}
			if (!whatIsRunning.equals("gameRunning")) {
				delay(50);
			}
		}
	}

//searches for the entities of the type ExtSpike and executes the method spikeTimer()
	private void executeSpikeTimer() {
		int number = 0;
		Entity entity;
		while (true) {
			entity = map.searchForExtSpike(number);
			if (entity != null) {
				((ExtSpike) entity).spikeTimer();
			} else {
				break;
			}
			number++;
		}
	}

//searches for the entities of the type MovingBall and executes the method ballTimer()
	private void executeBallTimer() {
		int number = 0;
		Entity entity;
		while (true) {
			entity = map.searchForMovingBall(number);
			if (entity != null) {
				((MovingBall) entity).ballTimer();
			} else {
				break;
			}
			number++;
		}
	}
}
