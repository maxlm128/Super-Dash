package main;

import com.asecave.Console;

public class Menu {

	Screen screen;
	Console console;
	Player player;
	KeyListener keyListener;
	Map map;
	int coordsXCursor = 1;
	int maxCoords;
	int localTailLength = 20;
	int localGameSpeed = 20;
	String[] mainMenu;
	String[] settingsMenu;
	String[] creditsMenu;
	String[] mapsMenu;
	String[] pauseMenu;

// initializing the three Arrays and the variables
	public void startMenu() {
		mainMenu[0] = "SuperDash - Main Menu";
		mainMenu[1] = "   Play       ";
		mainMenu[2] = " Settings     ";
		mainMenu[3] = " Credits       ";
		settingsMenu[0] = " Settings ";
		settingsMenu[1] = "TailLength  " + localTailLength + " ";
		settingsMenu[2] = "GameSpeed   " + localGameSpeed + " ";
		settingsMenu[3] = "          ";
		creditsMenu[0] = "Credits";
		creditsMenu[1] = " Asecave";
		creditsMenu[2] = " maxlm128";
		creditsMenu[3] = "        ";
		mapsMenu[0] = "Choose Maps";
		mapsMenu[1] = "  Map 1  ";
		mapsMenu[2] = "  Map 2  ";
		mapsMenu[3] = "         ";
		pauseMenu[0] = "  Pause  ";
		pauseMenu[1] = " To Game ";
		pauseMenu[2] = "Main Menu";
		pauseMenu[3] = "         ";
		keyListener.whatIsRunning = "mainMenuRunning";
		maxCoords = 2;
		player.setGameDelay(localGameSpeed);
		player.setTailTime(localTailLength);
		screen.printMenu(mainMenu);
	}

//creating the Arrays for the Menus main ,settings and credits
	public Menu() {
		mainMenu = new String[4];
		settingsMenu = new String[4];
		creditsMenu = new String[4];
		mapsMenu = new String[4];
		pauseMenu = new String[4];
	}

// new Reference to the Class map
	public void newPlayerReference(Player pPlayer) {
		player = pPlayer;
	}

// new Reference to the class screen
	public void newScreenReference(Screen pScreen) {
		screen = pScreen;
	}

// new Reference to the class console
	public void newConsoleReference(Console pConsole) {
		console = pConsole;
	}

// new Reference to the class keyListener
	public void newKeyListenerReference(KeyListener pKeyListener) {
		keyListener = pKeyListener;
	}

// new Reference to the class menu
	public void newMapReference(Map pMap) {
		map = pMap;
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
			if (coordsXCursor == 0 && keyListener.whatIsRunning.equals("settingsMenuRunning") && localTailLength < 99) {
				localTailLength++;
				player.setTailTime(localTailLength);
				settingsMenu[1] = "TailLength  " + localTailLength + " ";
				screen.printMenu(settingsMenu);
				break;
			} else if (coordsXCursor == 1 && keyListener.whatIsRunning.equals("settingsMenuRunning")
					&& localGameSpeed < 99) {
				localGameSpeed++;
				player.setGameDelay(localGameSpeed);
				settingsMenu[2] = "GameSpeed   " + localGameSpeed + " ";
				screen.printMenu(settingsMenu);
				break;
			}
			break;
		case 'a':
			if (coordsXCursor == 0 && keyListener.whatIsRunning.equals("settingsMenuRunning") && localTailLength > 1) {
				localTailLength--;
				player.setTailTime(localTailLength);
				settingsMenu[1] = "TailLength  " + localTailLength + " ";
				screen.printMenu(settingsMenu);
				break;
			} else if (coordsXCursor == 1 && keyListener.whatIsRunning.equals("settingsMenuRunning")
					&& localGameSpeed > 1) {
				localGameSpeed--;
				player.setGameDelay(localGameSpeed);
				settingsMenu[2] = "GameSpeed   " + localGameSpeed + " ";
				screen.printMenu(settingsMenu);
				break;
			}
			break;
		case 13:
			if (coordsXCursor == 0 && keyListener.whatIsRunning.equals("mainMenuRunning")) {
				changeMaxCoords(1);
				screen.printMenu(mapsMenu);
				keyListener.whatIsRunning = "mapsMenuRunning";
			} else if (coordsXCursor == 1 && keyListener.whatIsRunning.equals("mainMenuRunning")) {
				changeMaxCoords(1);
				screen.printMenu(settingsMenu);
				keyListener.whatIsRunning = "settingsMenuRunning";
			} else if (coordsXCursor == 2 && keyListener.whatIsRunning.equals("mainMenuRunning")) {
				changeMaxCoords(1);
				screen.printMenu(creditsMenu);
				keyListener.whatIsRunning = "creditsMenuRunning";
			} else if (coordsXCursor == 0 && keyListener.whatIsRunning.equals("mapsMenuRunning")) {
				keyListener.whatIsRunning = "gameRunning";
				screen.switchConsoleSettings(false);
				coordsXCursor = 1;
				map.newMapArray("maps/Map1.txt",false);
				screen.printMap();
			} else if (coordsXCursor == 1 && keyListener.whatIsRunning.equals("mapsMenuRunning")) {
				keyListener.whatIsRunning = "gameRunning";
				screen.switchConsoleSettings(false);
				coordsXCursor = 1;
				map.newMapArray("maps/Map2.txt",false);
				screen.printMap();
			} else if(coordsXCursor == 0 && keyListener.whatIsRunning.equals("pauseMenuRunning")) {
				keyListener.whatIsRunning = "gameRunning";
				screen.switchConsoleSettings(false);
				coordsXCursor = 1;
				screen.printMap();
			} else if(coordsXCursor == 1 && keyListener.whatIsRunning.equals("pauseMenuRunning")) {
				coordsXCursor = 0;
				changeMaxCoords(2);
				screen.printMenu(mainMenu);
				player.coordsXPlayer = 1;
				player.coordsYPlayer = 1;
				keyListener.whatIsRunning = "mainMenuRunning";
			}
			break;
		case 27:
			if (keyListener.whatIsRunning.equals("settingsMenuRunning")
					|| keyListener.whatIsRunning.equals("creditsMenuRunning")
					|| keyListener.whatIsRunning.equals("mapsMenuRunning")) {
				changeMaxCoords(2);
				screen.printMenu(mainMenu);
				keyListener.whatIsRunning = "mainMenuRunning";
			}
			break;
		}
	}
}
