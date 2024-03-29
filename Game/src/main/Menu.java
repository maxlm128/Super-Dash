package main;

import com.asecave.Console;
import com.asecave.Console.Color;

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
	int currentMap;
	int currentColor = 1;
	String[] allPlayerColors;
	String[] mainMenu;
	String[] settingsMenu;
	String[] creditsMenu;
	String[] mapsMenu;
	String[] pauseMenu;
	String[] deadMenu;
	String[] passedMenu;
	String[] allMaps;

//initializing the different arrays for the menus
	public void startMenu() {
		mainMenu[0] = "SuperDash - Main Menu";
		mainMenu[1] = "   Play       ";
		mainMenu[2] = " Settings     ";
		mainMenu[3] = " Credits       ";
		mainMenu[4] = "  Quit         ";
		settingsMenu[0] = " Settings ";
		settingsMenu[1] = "TailLength  " + localTailLength + " ";
		settingsMenu[2] = "GameSpeed   " + localGameSpeed + " ";
		settingsMenu[4] = "          ";
		creditsMenu[0] = "Credits";
		creditsMenu[1] = " Console Engine by:";
		creditsMenu[2] = " Asecave ";
		creditsMenu[3] = " Game Engine by:";
		creditsMenu[4] = " maxlm128";
		mapsMenu[0] = "Choose Maps";
		mapsMenu[1] = "  Map 1  ";
		mapsMenu[2] = "  Map 2  ";
		mapsMenu[3] = "         ";
		mapsMenu[4] = "         ";
		pauseMenu[0] = "  Pause  ";
		pauseMenu[1] = " To Game ";
		pauseMenu[2] = "Main Menu";
		pauseMenu[3] = "         ";
		pauseMenu[4] = "         ";
		deadMenu[0] = "   DEAD   ";
		deadMenu[1] = "   Quit   ";
		deadMenu[2] = "          ";
		deadMenu[3] = "   GAME   ";
		deadMenu[4] = "   OVER   ";
		passedMenu[0] = " PASSED   ";
		passedMenu[1] = " Next map ";
		passedMenu[2] = " Main Menu";
		passedMenu[3] = "    You   ";
		passedMenu[4] = "   Passed ";
		allMaps[0] = "maps/Map1.txt";
		allMaps[1] = "maps/Map2.txt";
		allPlayerColors[0] = "Red        ";
		allPlayerColors[1] = "Yellow     ";
		allPlayerColors[2] = "Green      ";
		allPlayerColors[3] = "Cyan       ";
		allPlayerColors[4] = "Blue       ";
		allPlayerColors[5] = "Purple     ";
		allPlayerColors[6] = "Dark Red   ";
		allPlayerColors[7] = "Gold       ";
		allPlayerColors[8] = "Dark Green ";
		allPlayerColors[9] = "Dark Cyan  ";
		allPlayerColors[10] = "Dark Blue  ";
		allPlayerColors[11] = "Dark Purple";
		allPlayerColors[12] = "Dark Gray  ";
		allPlayerColors[13] = "Gray       ";
		allPlayerColors[14] = "White      ";
		allPlayerColors[15] = "RGB        ";
		settingsMenu[3] = "P. Color    " + allPlayerColors[currentColor - 1] + " ";
		keyListener.whatIsRunning = "mainMenuRunning";
		maxCoords = 3;
		player.setGameDelay(localGameSpeed);
		player.setTailTime(localTailLength);
		player.setPlayerColor(currentColor);
		screen.printMenu(mainMenu);
		screen.console.setBGColor(Color.BLACK);
	}

//creating the Arrays for the different menus
	public Menu() {
		mainMenu = new String[5];
		settingsMenu = new String[5];
		creditsMenu = new String[5];
		mapsMenu = new String[5];
		pauseMenu = new String[5];
		deadMenu = new String[5];
		passedMenu = new String[5];
		allMaps = new String[2];
		allPlayerColors = new String[16];
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
	private void printChangedCoords(boolean isUp, boolean print) {
		if (print) {
			screen.print(3, 10 + (coordsXCursor * 2), ' ');
			screen.print(14, 10 + (coordsXCursor * 2), ' ');
		}
		if (isUp) {
			coordsXCursor--;
		} else {
			coordsXCursor++;
		}
		if (print) {
			screen.print(3, 10 + (coordsXCursor * 2), '<');
			screen.print(14, 10 + (coordsXCursor * 2), '>');
		}
	}

//executes the death animation
	public void executeDeathAnimation() {
		screen.switchConsoleSettings(true);
		changeMaxCoords(0, false);
		screen.printMenu(deadMenu);
		keyListener.whatIsRunning = "deadMenuRunning";
	}

//changes the maximum coords for the cursor and prints it with the method printChangedCoords()
	public void changeMaxCoords(int pMaxCoords, boolean print) {
		maxCoords = pMaxCoords;
		while (maxCoords < coordsXCursor) {
			printChangedCoords(true, print);
		}
	}

//executes the required methods to move the cursor and print it onto the screen depentend on the keypress
	public void moveCursor(char pInputChar) {
		switch (pInputChar) {
		case 'w':
			if (coordsXCursor == 0) {
				break;
			}
			printChangedCoords(true, true);
			break;
		case 's':
			if (coordsXCursor == maxCoords) {
				break;
			}
			printChangedCoords(false, true);
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
			} else if (coordsXCursor == 2 && keyListener.whatIsRunning.equals("settingsMenuRunning")
					&& 16 > currentColor) {
				currentColor++;
				if (16 > currentColor) {
					player.setPlayerColor(currentColor - 1);
				}
				settingsMenu[3] = "P. Color    " + allPlayerColors[currentColor - 1] + " ";
				screen.printMenu(settingsMenu);
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
			} else if (coordsXCursor == 2 && keyListener.whatIsRunning.equals("settingsMenuRunning")
					&& 1 < currentColor) {
				currentColor--;
				if (currentColor < 15) {
					player.setPlayerColor(currentColor - 1);
				}
				settingsMenu[3] = "P. Color    " + allPlayerColors[currentColor - 1] + " ";
				screen.printMenu(settingsMenu);
			}
			break;
		case 13:
			if (coordsXCursor == 0 && keyListener.whatIsRunning.equals("mainMenuRunning")) {
				changeMaxCoords(1, true);
				screen.printMenu(mapsMenu);
				keyListener.whatIsRunning = "mapsMenuRunning";
			} else if (coordsXCursor == 1 && keyListener.whatIsRunning.equals("mainMenuRunning")) {
				changeMaxCoords(2, true);
				screen.printMenu(settingsMenu);
				keyListener.whatIsRunning = "settingsMenuRunning";
			} else if (coordsXCursor == 2 && keyListener.whatIsRunning.equals("mainMenuRunning")) {
				changeMaxCoords(0, true);
				screen.printMenu(creditsMenu);
				keyListener.whatIsRunning = "creditsMenuRunning";
			} else if (coordsXCursor == 3 && keyListener.whatIsRunning.equals("mainMenuRunning")) {
				keyListener.setRun(false);
			} else if (coordsXCursor == 0 && keyListener.whatIsRunning.equals("mapsMenuRunning")) {
				keyListener.whatIsRunning = "gameRunning";
				screen.switchConsoleSettings(false);
				coordsXCursor = 1;
				map.newMapArray(allMaps[0]);
				currentMap = 0;
				screen.printMap();
			} else if (coordsXCursor == 1 && keyListener.whatIsRunning.equals("mapsMenuRunning")) {
				keyListener.whatIsRunning = "gameRunning";
				screen.switchConsoleSettings(false);
				coordsXCursor = 1;
				map.newMapArray(allMaps[1]);
				currentMap = 1;
				screen.printMap();
			} else if (coordsXCursor == 0 && keyListener.whatIsRunning.equals("pauseMenuRunning")) {
				keyListener.whatIsRunning = "gameRunning";
				screen.switchConsoleSettings(false);
				coordsXCursor = 1;
				screen.printMap();
			} else if (coordsXCursor == 1 && keyListener.whatIsRunning.equals("pauseMenuRunning")
					|| coordsXCursor == 0 && keyListener.whatIsRunning.equals("deadMenuRunning")
					|| coordsXCursor == 1 && keyListener.whatIsRunning.equals("passedMenuRunning")) {
				player.currentInput = 0;
				coordsXCursor = 0;
				changeMaxCoords(3, true);
				screen.printMenu(mainMenu);
				player.coordsXEntity = 1;
				player.coordsYEntity = 1;
				keyListener.whatIsRunning = "mainMenuRunning";
			} else if (coordsXCursor == 0 && keyListener.whatIsRunning.equals("passedMenuRunning")) {
				screen.switchConsoleSettings(false);
				coordsXCursor = 1;
				if (currentMap < allMaps.length) {
					keyListener.whatIsRunning = "gameRunning";
					currentMap++;
					map.newMapArray(allMaps[currentMap]);
					screen.printMap();
				} else {
					coordsXCursor = 0;
					changeMaxCoords(3, true);
					screen.printMenu(mainMenu);
					player.coordsXEntity = 1;
					player.coordsYEntity = 1;
					keyListener.whatIsRunning = "mainMenuRunning";
				}
			}
			break;
		case 27:
			if (keyListener.whatIsRunning.equals("settingsMenuRunning")
					|| keyListener.whatIsRunning.equals("creditsMenuRunning")
					|| keyListener.whatIsRunning.equals("mapsMenuRunning")) {
				changeMaxCoords(3, true);
				screen.printMenu(mainMenu);
				keyListener.whatIsRunning = "mainMenuRunning";
			}
			break;
		}
	}
}
