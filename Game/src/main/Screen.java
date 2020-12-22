package main;

import com.asecave.Console;
import com.asecave.Console.Color;
import com.asecave.Console.FontSize;

public class Screen {

	Map map;
	Console console;
	Menu menu;
	Player player;
	boolean flipFlop = true;

// new Reference to the Class Map
	public void newMapReference(Map pMap) {
		map = pMap;
	}

// new Reference to the Class Menu
	public void newMenuReference(Menu pMenu) {
		menu = pMenu;
	}

// new Reference to the Class map
	public void newPlayerReference(Player pPlayer) {
		player = pPlayer;
	}

//new reference to the class Console
	public void newConsoleReference(Console pConsole) {
		console = pConsole;
	}

//prints the map onto the screen
	public void printMap() {
		console.fill(' ');
		console.setFontSize(FontSize.NORMAL);
		console.setBGColor(Color.BLACK);
		console.setFGColor(Color.WHITE);
		console.setCursorVisible(false);
		for (int y = 0; y < map.height; y++) {
			method:
			for (int x = 0; x < map.width; x++) {
				if(Map.trailMap[x][y] > 0) {
					console.setBGColor(Color.WHITE);
				}
				if (Map.map[x][y] == ('X')) {
					console.setFGColor(8);
				} else if (Map.entityMap[x][y] != null) {
					console.setFGColor(Map.entityMap[x][y].color);
					console.print(Map.entityMap[x][y].content);
					console.setBGColor(Color.BLACK);
					break method;
				} else {
					console.setFGColor(Color.WHITE);
				}
				console.print(Map.map[x][y]);
				console.setBGColor(Color.BLACK);
			}
			console.print("\n");
		}
	}

// prints the content of the array Map in the class with the requested content
	public void print(int pCoordsX, int pCoordsY, char pContent) {
		console.setCursorVisible(false);
		console.setCursor(pCoordsX, pCoordsY);
		console.print(pContent);
	}

	public void reprintPlayer(int pColor) {
		console.setFGColor(pColor);
		console.setCursorVisible(false);
		console.setCursor(player.coordsXEntity, player.coordsYEntity);
		console.print(Map.map[player.coordsXEntity][player.coordsYEntity]);
		console.setFGColor(Color.WHITE);
	}

// prints the content of the array Map in the class with the requested color
	public void print(int pCoordsX, int pCoordsY, int pColor) {
		console.setFGColor(pColor);
		console.setCursorVisible(false);
		console.setCursor(pCoordsX, pCoordsY);
		console.print(Map.map[pCoordsX][pCoordsY]);
		console.setFGColor(Color.WHITE);
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
		console.setBGColor(Color.BLACK);
		console.setFGColor(Color.RED);
		console.fill(' ');
		console.setCursor(0, 1);
		console.setCursorVisible(false);
		console.print("                           " + pInhalt[0] + "                                    ");
		console.setFGColor(Color.WHITE);
		for (int i = 1; i < pInhalt.length; i++) {
			console.setCursor(3, 8 + i * 2);
			console.print(" " + pInhalt[i]);
		}
		if (menu.maxCoords > 0) {
			print(3, 10 + (menu.coordsXCursor * 2), '<');
			print(14, 10 + (menu.coordsXCursor * 2), '>');
		}
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
