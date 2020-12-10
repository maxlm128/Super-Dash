package main;

import com.asecave.Console;

public class Player {

	int coordsXPlayer;
	int coordsYPlayer;
	int tailTime;
	int gameDelay;
	Map map;
	Screen screen;
	Console console;

	public Player() {
		coordsXPlayer = 1;
		coordsYPlayer = 1;
	}

// new reference to the class Console
	public void newConsoleReference(Console pConsole) {
		console = pConsole;
	}

// new reference to the class Screen
	public void newScreenReference(Screen pScreen) {
		screen = pScreen;
	}

// new reference to the class Map
	public void newMapReference(Map pMap) {
		map = pMap;
	}

// method delay to create a delay
	private void delay(int pDelay) {
		try {
			Thread.sleep(pDelay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void setTailTime(int pLocalTailTime) {
		tailTime = pLocalTailTime * gameDelay;
	}
	
	public void setGameDelay(int pLocalGameSpeed) {
		gameDelay = 100/pLocalGameSpeed;
	}

// changes the coordinates from player to the new position and prints it out on
// the screen
	private void setCoordsPlayer(int pCoordsXPlayer, int pCoordsYPlayer, char pInhalt) {
		if (pCoordsXPlayer + 1 < Map.map.length && pCoordsYPlayer + 1 < Map.map[0].length && pCoordsXPlayer > 0
				&& pCoordsYPlayer > 0) {
			Map.map[coordsXPlayer][coordsYPlayer] = ' ';
			screen.print(coordsXPlayer, coordsYPlayer);
			Map.map[pCoordsXPlayer][pCoordsYPlayer] = pInhalt;
			coordsXPlayer = pCoordsXPlayer;
			coordsYPlayer = pCoordsYPlayer;
			screen.print(coordsXPlayer, coordsYPlayer);
		} else {
			Map.map[coordsXPlayer][coordsYPlayer] = pInhalt;
			screen.print(coordsXPlayer, coordsYPlayer);
		}
	}

// looks for an integer in the array trailmap and sets every value thats over 
//0 one lower and if the integer reaches 0, the trail is deleted from the 
//screen by executing the method printTrail()
	public void trailTimer() {
		for (int x = 0; x < Map.trailMap.length; x++) {
			for (int y = 0; y < Map.trailMap[0].length; y++) {
				if (Map.trailMap[x][y] >= 1) {
					if (Map.trailMap[x][y] == 1) {
						screen.printTrail(x, y, false);
					}
					Map.trailMap[x][y]--;
				}
			}
		}
	}

//checks for every case if something is in front of the player
	private boolean isSomethingInFront(char pRotation, char pContent) {
		switch (pRotation) {
		case 'd':
			return Map.map[coordsXPlayer + 1][coordsYPlayer] == pContent;
		case 's':
			return Map.map[coordsXPlayer][coordsYPlayer + 1] == pContent;
		case 'a':
			return Map.map[coordsXPlayer - 1][coordsYPlayer] == pContent;
		case 'w':
			return Map.map[coordsXPlayer][coordsYPlayer - 1] == pContent;
		}
		return true;
	}

// checks if the keypress is one of the valid keypresses and executes multiple
// associated methods
	public void doAction(char pInputChar) {
		switch (pInputChar) {
		case 'w':
			while (true) {
				if (isSomethingInFront('w', '#')) {
					break;
				}
				setCoordsPlayer(coordsXPlayer, coordsYPlayer - 1, 'v');
				Map.trailMap[coordsXPlayer][coordsYPlayer + 1] = tailTime;
				trailTimer();
				screen.print(coordsXPlayer, coordsYPlayer);
				screen.printTrail(coordsXPlayer, coordsYPlayer + 1, true);
				delay(gameDelay);
				console.getInputChar();
			}
			break;
		case 'a':
			while (true) {
				if (isSomethingInFront('a', '#')) {
					break;
				}
				setCoordsPlayer(coordsXPlayer - 1, coordsYPlayer, '>');
				Map.trailMap[coordsXPlayer + 1][coordsYPlayer] = tailTime;
				trailTimer();
				screen.print(coordsXPlayer, coordsYPlayer);
				screen.printTrail(coordsXPlayer + 1, coordsYPlayer, true);
				delay(gameDelay);
				console.getInputChar();
			}
			break;
		case 's':
			while (true) {
				if (isSomethingInFront('s', '#')) {
					break;
				}
				setCoordsPlayer(coordsXPlayer, coordsYPlayer + 1, '^');
				Map.trailMap[coordsXPlayer][coordsYPlayer - 1] = tailTime;
				trailTimer();
				screen.print(coordsXPlayer, coordsYPlayer);
				screen.printTrail(coordsXPlayer, coordsYPlayer - 1, true);
				delay(gameDelay);
				console.getInputChar();
			}
			break;
		case 'd':
			while (true) {
				if (isSomethingInFront('d', '#')) {
					break;
				}
				setCoordsPlayer(coordsXPlayer + 1, coordsYPlayer, '<');
				Map.trailMap[coordsXPlayer - 1][coordsYPlayer] = tailTime;
				trailTimer();
				screen.print(coordsXPlayer, coordsYPlayer);
				screen.printTrail(coordsXPlayer - 1, coordsYPlayer, true);
				delay(gameDelay);
				console.getInputChar();
			}
			break;
		}
	}
}