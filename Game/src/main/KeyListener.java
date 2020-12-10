package main;

import com.asecave.Console;

public class KeyListener {

	Player player;
	Console console;
	Menu menu;
	Screen screen;
	boolean listen = true;
	String whatIsRunning = null;

// Setter for Listen
	public void setListen(boolean pListen) {
		listen = pListen;
		if (pListen) {
			keyListener();
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

// new Reference to the class Screen
	public void newScreenReference(Screen pScreen) {
		screen = pScreen;
	}

// Method delay to create a delay
	public void delay(int pDelay) {
		try {
			Thread.sleep(pDelay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

// listens to the received Keypresses from the imported class Console and
// executes doAction() or moveCursor() if the player pressed something
	public void keyListener() {
		char inputChar;
		while (listen) {
			inputChar = console.getInputChar();
			if (inputChar != 0 && whatIsRunning.equals("gameRunning")) {
				screen.switchConsoleSettings(false);
				player.doAction(inputChar);
			}
			if (whatIsRunning.equals("gameRunning")) {
				player.trailTimer();
				delay(player.gameDelay);
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
}
