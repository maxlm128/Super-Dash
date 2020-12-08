package main;

import com.asecave.Console;

public class Menu {

	Screen screen;
	Console console;
	KeyListener keyListener;
	int pCoordsXCursor = 1;
	int maxCoords;
	String[] mainMenu;
	String[] settingsMenu;
	String[] credits;

	public Menu() {
		mainMenu = new String[4];
		settingsMenu = new String[4];
		credits = new String[4];
	}

	public void newScreenReference(Screen pScreen) {
		screen = pScreen;
	}

	public void newConsoleReference(Console pConsole) {
		console = pConsole;
	}

	public void newKeyListenerReference(KeyListener pKeyListener) {
		keyListener = pKeyListener;
	}

	public void startMenu() {
		mainMenu[0] = "SuperDash - Main Menu";
		mainMenu[1] = "   Play   ";
		mainMenu[2] = " Settings ";
		mainMenu[3] = " Credits   ";
		settingsMenu[0] = " Settings ";
		settingsMenu[1] = "TrailTime ";
		settingsMenu[2] = "GameSpeed ";
		settingsMenu[3] = "          ";
		credits[0] = "Credits";
		credits[1] = " Asecave";
		credits[2] = " maxlm128";
		credits[3] = "        ";
		keyListener.whatIsRunning = "mainMenuRunning";
		maxCoords = 2;
		screen.printMenu(mainMenu);
	}

	public void printChangedCoords(boolean isUp) {
		screen.print(3, 10 + (pCoordsXCursor * 2), ' ');
		screen.print(14, 10 + (pCoordsXCursor * 2), ' ');
		if (isUp) {
			pCoordsXCursor--;
		}else {
			pCoordsXCursor++;
		}
		screen.print(3, 10 + (pCoordsXCursor * 2), '<');
		screen.print(14, 10 + (pCoordsXCursor * 2), '>');
	}
	
	public void changeMaxCoords(int pMaxCoords) {
		maxCoords = pMaxCoords;
		for(int i = maxCoords ;i < pCoordsXCursor;i++) {
			printChangedCoords(true);
		}
	}

	public void moveCursor(char pInputChar) {
		switch (pInputChar) {
		case 'w':
			if (pCoordsXCursor == 0) {
				break;
			}
			printChangedCoords(true);
			break;
		case 's':
			if (pCoordsXCursor == maxCoords) {
				break;
			}
			printChangedCoords(false);
			break;
		case 13:
			if (pCoordsXCursor == 0 && keyListener.whatIsRunning.equals("mainMenuRunning")) {
				keyListener.whatIsRunning = "gameRunning";
				screen.switchConsoleSettings(false);
				screen.printMap();
			} else if (pCoordsXCursor == 1 && keyListener.whatIsRunning.equals("mainMenuRunning")) {
				changeMaxCoords(1);
				screen.printMenu(settingsMenu);
				keyListener.whatIsRunning = "settingsMenuRunning";
			} else if (pCoordsXCursor == 2 && keyListener.whatIsRunning.equals("mainMenuRunning")) {
				changeMaxCoords(1);
				screen.printMenu(credits);
				keyListener.whatIsRunning = "creditsMenuRunning";
			}
			break;
		case 27:
			if (keyListener.whatIsRunning.equals("settingsMenuRunning")
					|| keyListener.whatIsRunning.equals("creditsMenuRunning")) {
				changeMaxCoords(2);
				screen.printMenu(mainMenu);
				keyListener.whatIsRunning = "mainMenuRunning";
			}
		}
	}
}
