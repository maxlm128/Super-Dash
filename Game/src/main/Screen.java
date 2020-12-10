package main;

import com.asecave.Console;
import com.asecave.Console.Color;
import com.asecave.Console.FontSize;

public class Screen {

	Map map;
	Console console;
	Menu menu;
	boolean flipFlop = true;

	public Screen() {

	}

// new Reference to the Class Map
	public void newMapReference(Map pMap) {
		map = pMap;
	}

// new Reference to the Class Menu
	public void newMenuReference(Menu pMenu) {
		menu = pMenu;
	}

//new reference to the class Console
	public void newConsoleReference(Console pConsole) {
		console = pConsole;
	}

// method delay to create a delay
	public void delay(int pDelay) {
		try {
			Thread.sleep(pDelay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

//prints the map onto the screen
	public void printMap() {
		console.fill(' ');
		console.setFontSize(FontSize.NORMAL);
		console.setBGColor(Color.BLACK);
		console.setFGColor(Color.WHITE);
		console.setCursorVisible(false);
		for (int y = 0; y < map.height; y++) {
			for (int x = 0; x < map.width; x++) {
				if (Map.map[x][y] == ('<') || Map.map[x][y] == ('^') || Map.map[x][y] == ('>')
						|| Map.map[x][y] == ('v')) {
					console.setFGColor(Color.RED);
				} else {
					console.setFGColor(Color.WHITE);
				}
				console.print(Map.map[x][y]);
			}
			console.print("\n");
		}
		console.setFGColor(Color.RED);
	}

// prints the content of the array Map in the class
	public void print(int pCoordsX, int pCoordsY) {
		console.setCursorVisible(false);
		console.setCursor(pCoordsX, pCoordsY);
		console.print(Map.map[pCoordsX][pCoordsY]);
	}

	public void print(int pCoordsX, int pCoordsY, char pInhalt) {
		console.setCursorVisible(false);
		console.setCursor(pCoordsX, pCoordsY);
		console.print(pInhalt);
	}

//if the integer on the array TrailMap is greater than 0 ,a trail is printed onto the screen,otherwise,the trail is deleted
	public void printTrail(int pCoordsX, int pCoordsY, boolean isWhite) {
		console.setCursorVisible(false);
		console.setCursor(pCoordsX, pCoordsY);
		if (Map.trailMap[pCoordsX][pCoordsY] > 0) {
			if (isWhite) {
				console.setBGColor(Color.WHITE);
				console.print(" ");
			} else if (Map.map[pCoordsX][pCoordsY] == ' ') {
				console.print(" ");
			}
			console.setBGColor(Color.BLACK);
		}
	}

//requires an array and prints the array onto the screen with some settings in the console
	public void printMenu(String[] pInhalt) {
		console.fill(' ');
		console.setBGColor(Color.BLACK);
		console.setFGColor(Color.RED);
		console.setCursor(0, 1);
		console.setCursorVisible(false);
		console.print("                                     " + pInhalt[0] + "                                    ");
		console.setFGColor(Color.WHITE);
		for (int i = 1; i < pInhalt.length; i++) {
			console.setCursor(3, 8 + i * 2);
			console.print(" " + pInhalt[i]);
		}
		print(3, 10 + (menu.coordsXCursor * 2), '<');
		print(14, 10 + (menu.coordsXCursor * 2), '>');
	}

//switches the settings for the console between the Game and the Menu
	public void switchConsoleSettings(boolean isMenuActive) {
		if (isMenuActive && flipFlop) {
			console.setFontSize(FontSize.LARGE);
			flipFlop = false;
		} else if (!isMenuActive && !flipFlop) {
			console.setFontSize(FontSize.NORMAL);
			flipFlop = true;
		}
	}
}