package main;

import com.asecave.Console;

public class Menu {

	Screen screen;
	Console console;
	Player player;
	KeyListener keyListener;
	int coordsXCursor = 1;
	int maxCoords;
	int localTailTime = 20;
	int localGameSpeed = 20;
	String[] mainMenu;
	String[] settingsMenu;
	String[] credits;

// initializing the three Arrays and the variables
	public void startMenu() {
		mainMenu[0] = "SuperDash - Main Menu";
		mainMenu[1] = "   Play       ";
		mainMenu[2] = " Settings     ";
		mainMenu[3] = " Credits       ";
		settingsMenu[0] = " Settings ";
		settingsMenu[1] = "TrailTime   " + localTailTime;
		settingsMenu[2] = "GameSpeed   " + localGameSpeed;
		settingsMenu[3] = "          ";
		credits[0] = "Credits";
		credits[1] = " Asecave";
		credits[2] = " maxlm128";
		credits[3] = "        ";
		keyListener.whatIsRunning = "mainMenuRunning";
		maxCoords = 2;
		player.setGameDelay(localGameSpeed);
		player.setTailTime(localTailTime);
		screen.printMenu(mainMenu);
	}

//creating the Arrays for the Menus main ,settings and credits
	public Menu() {
		mainMenu = new String[4];
		settingsMenu = new String[4];
		credits = new String[4];
	}

// new Reference to the Class Map
	public void newPlayerReference(Player pPlayer) {
		player = pPlayer;
	}

// new Reference to the class Screen
	public void newScreenReference(Screen pScreen) {
		screen = pScreen;
	}

// new Reference to the class Console
	public void newConsoleReference(Console pConsole) {
		console = pConsole;
	}

// new Reference to the class KeyListener
	public void newKeyListenerReference(KeyListener pKeyListener) {
		keyListener = pKeyListener;
	}

//changes the coords of the Cursor and prints it with the method print()
	public void printChangedCoords(boolean isUp) {
		screen.print(3, 10 + (coordsXCursor * 2), ' ');
		screen.print(14, 10 + (coordsXCursor * 2), ' ');
		if (isUp) {
			coordsXCursor--;
		} else {
			coordsXCursor++;
		}
		screen.print(3, 10 + (coordsXCursor * 2), '<');
		screen.print(14, 10 + (coordsXCursor * 2), '>');
	}

//changes the maximum coords for the cursor and prints it with the method printChangedCoords()
	public void changeMaxCoords(int pMaxCoords) {
		maxCoords = pMaxCoords;
		for (int i = maxCoords; i < coordsXCursor; i++) {
			printChangedCoords(true);
		}
	}

//executes the required methods to move the cursor and print it onto the screen
	public void moveCursor(char pInputChar) {
		switch (pInputChar) {
		case 'w':
			if (coordsXCursor == 0) {
				break;
			}
			printChangedCoords(true);
			break;
		case 's':
			if (coordsXCursor == maxCoords) {
				break;
			}
			printChangedCoords(false);
			break;
		case 'd':
			if (coordsXCursor == 0 && keyListener.whatIsRunning.equals("settingsMenuRunning")) {
				localTailTime++;
				player.setTailTime(localTailTime);
				settingsMenu[1] = "TrailTime   " + localTailTime;
				screen.printMenu(settingsMenu);
				break;
			} else if (coordsXCursor == 1 && keyListener.whatIsRunning.equals("settingsMenuRunning")) {
				localGameSpeed++;
				player.setGameDelay(localGameSpeed);
				settingsMenu[2] = "GameSpeed   " + localGameSpeed;
				screen.printMenu(settingsMenu);
				break;
			}
		case 'a':
			if (coordsXCursor == 0 && keyListener.whatIsRunning.equals("settingsMenuRunning")) {
				localTailTime--;
				player.setTailTime(localTailTime);
				settingsMenu[1] = "TrailTime   " + localTailTime;
				screen.printMenu(settingsMenu);
				break;
			} else if (coordsXCursor == 1 && keyListener.whatIsRunning.equals("settingsMenuRunning")) {
				localGameSpeed--;
				player.setGameDelay(localGameSpeed);
				settingsMenu[2] = "GameSpeed   " + localGameSpeed;
				screen.printMenu(settingsMenu);
				break;
			}
		case 13:
			if (coordsXCursor == 0 && keyListener.whatIsRunning.equals("mainMenuRunning")) {
				keyListener.whatIsRunning = "gameRunning";
				screen.switchConsoleSettings(false);
				screen.printMap();
			} else if (coordsXCursor == 1 && keyListener.whatIsRunning.equals("mainMenuRunning")) {
				changeMaxCoords(1);
				screen.printMenu(settingsMenu);
				keyListener.whatIsRunning = "settingsMenuRunning";
			} else if (coordsXCursor == 2 && keyListener.whatIsRunning.equals("mainMenuRunning")) {
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