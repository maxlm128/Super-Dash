package main;

import com.asecave.Console;

public class Player extends Entity {
	int tailTime;
	int gameDelay;
	char currentInput;
	Screen screen;
	Console console;
	KeyListener keyListener;
	Menu menu;
	Map map;
	Player player;

	public Player() {
		color = 1;
	}

// new reference to the class Console
	public void newConsoleReference(Console pConsole) {
		console = pConsole;
	}

	// new Reference to the Class Map
	public void newMapReference(Map pMap) {
		map = pMap;
	}

// new Reference to the class keyListener
	public void newKeyListenerReference(KeyListener pKeyListener) {
		keyListener = pKeyListener;
	}

// new reference to the class Screen
	public void newScreenReference(Screen pScreen) {
		screen = pScreen;
	}

// new Reference to the class Menu
	public void newMenuReference(Menu pMenu) {
		menu = pMenu;
	}

// new Reference to the Class map
	public void newPlayerReference(Player pPlayer) {
		player = pPlayer;
	}

//setter for tailTime ,but with a short calculation
	public void setTailTime(int pLocalTailLength) {
		tailTime = pLocalTailLength * gameDelay;
	}

//setter for gameDelay ,but with a short calculation
	public void setGameDelay(int pLocalGameSpeed) {
		gameDelay = 100 / pLocalGameSpeed;
	}

// changes the coordinates from player to the new position and prints it out on
// the screen
	private void setCoordsPlayer(int pCoordsXPlayer, int pCoordsYPlayer, char pContent) {
		if (pCoordsXPlayer + 1 < Map.map.length && pCoordsYPlayer + 1 < Map.map[0].length && pCoordsXPlayer > 0
				&& pCoordsYPlayer > 0) {
			Map.map[coordsXEntity][coordsYEntity] = ' ';
			Map.entityMap[coordsXEntity][coordsYEntity] = null;
			screen.print(coordsXEntity, coordsYEntity,color);
			Map.map[pCoordsXPlayer][pCoordsYPlayer] = pContent;
			Map.entityMap[pCoordsXPlayer][pCoordsYPlayer] = player;
			coordsXEntity = pCoordsXPlayer;
			coordsYEntity = pCoordsYPlayer;
			screen.print(coordsXEntity, coordsYEntity,color);
		} else {
			Map.map[coordsXEntity][coordsYEntity] = pContent;
			Map.entityMap[coordsXEntity][coordsYEntity] = player;
			screen.print(coordsXEntity, coordsYEntity,color);
		}
	}

// looks for an integer in the array trailmap and sets every value thats over 
//zero one lower and if the integer reaches zero, the trail is deleted from the 
//screen by executing the method printTrail()
	public void tailTimer() {
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
			return Map.map[coordsXEntity + 1][coordsYEntity] == pContent;
		case 's':
			return Map.map[coordsXEntity][coordsYEntity + 1] == pContent;
		case 'a':
			return Map.map[coordsXEntity - 1][coordsYEntity] == pContent;
		case 'w':
			return Map.map[coordsXEntity][coordsYEntity - 1] == pContent;
		}
		return true;
	}

	private boolean isSpikeInFront(char pRotation) {
		switch (pRotation) {
		case 'd':
			return Map.map[coordsXEntity + 1][coordsYEntity] == 'v' || Map.map[coordsXEntity + 1][coordsYEntity] == '>'
					|| Map.map[coordsXEntity + 1][coordsYEntity] == '^'
					|| Map.map[coordsXEntity + 1][coordsYEntity] == '<'
					|| Map.map[coordsXEntity + 1][coordsYEntity] == '|'
					|| Map.map[coordsXEntity + 1][coordsYEntity] == '-';
		case 's':
			return Map.map[coordsXEntity][coordsYEntity + 1] == 'v' || Map.map[coordsXEntity][coordsYEntity + 1] == '>'
					|| Map.map[coordsXEntity][coordsYEntity + 1] == '^'
					|| Map.map[coordsXEntity][coordsYEntity + 1] == '<'
					|| Map.map[coordsXEntity][coordsYEntity + 1] == '|'
					|| Map.map[coordsXEntity][coordsYEntity + 1] == '-';
		case 'a':
			return Map.map[coordsXEntity - 1][coordsYEntity] == 'v' || Map.map[coordsXEntity - 1][coordsYEntity] == '>'
					|| Map.map[coordsXEntity - 1][coordsYEntity] == '^'
					|| Map.map[coordsXEntity - 1][coordsYEntity] == '<'
					|| Map.map[coordsXEntity - 1][coordsYEntity] == '|'
					|| Map.map[coordsXEntity - 1][coordsYEntity] == '-';
		case 'w':
			return Map.map[coordsXEntity][coordsYEntity - 1] == 'v' || Map.map[coordsXEntity][coordsYEntity - 1] == '>'
					|| Map.map[coordsXEntity][coordsYEntity - 1] == '^'
					|| Map.map[coordsXEntity][coordsYEntity - 1] == '<'
					|| Map.map[coordsXEntity][coordsYEntity - 1] == '|'
					|| Map.map[coordsXEntity][coordsYEntity - 1] == '-';
		}
		return true;
	}

// checks if the keypress is one of the valid keypresses and executes multiple
// associated methods
	public void doAction(char pInputChar) {
		if(currentInput != 0) {
			pInputChar = currentInput;
		} else {
			currentInput = pInputChar;
		}
		switch (pInputChar) {
		case 'w':
				if (isSpikeInFront('w')) {
					currentInput = 0;
					menu.executeDeathAnimation();
					break;
				}
				if (!isSomethingInFront('w', ' ')) {
					currentInput = 0;
					break;
				}
				setCoordsPlayer(coordsXEntity, coordsYEntity - 1, 'v');
				Map.trailMap[coordsXEntity][coordsYEntity + 1] = tailTime;
				screen.print(coordsXEntity, coordsYEntity, color);
				screen.printTrail(coordsXEntity, coordsYEntity + 1, true);
			break;
		case 'a':
				if (isSpikeInFront('a')) {
					currentInput = 0;
					menu.executeDeathAnimation();
					break;
				}
				if (!isSomethingInFront('a', ' ')) {
					currentInput = 0;
					break;
				}
				setCoordsPlayer(coordsXEntity - 1, coordsYEntity, '>');
				Map.trailMap[coordsXEntity + 1][coordsYEntity] = tailTime;
				screen.print(coordsXEntity, coordsYEntity, color);
				screen.printTrail(coordsXEntity + 1, coordsYEntity, true);
			break;
		case 's':
				if (isSpikeInFront('s')) {
					currentInput = 0;
					menu.executeDeathAnimation();
					break;
				}
				if (!isSomethingInFront('s', ' ')) {
					currentInput = 0;
					break;
				}
				setCoordsPlayer(coordsXEntity, coordsYEntity + 1, '^');
				Map.trailMap[coordsXEntity][coordsYEntity - 1] = tailTime;
				screen.print(coordsXEntity, coordsYEntity, color);
				screen.printTrail(coordsXEntity, coordsYEntity - 1, true);
			break;
		case 'd':
				if (isSpikeInFront('d')) {
					currentInput = 0;
					menu.executeDeathAnimation();
					break;
				}
				if (!isSomethingInFront('d', ' ')) {
					currentInput = 0;
					break;
				}
				setCoordsPlayer(coordsXEntity + 1, coordsYEntity, '<');
				Map.trailMap[coordsXEntity - 1][coordsYEntity] = tailTime;
				screen.print(coordsXEntity, coordsYEntity, color);
				screen.printTrail(coordsXEntity - 1, coordsYEntity, true);
			break;
		case 27:
			currentInput = 0;
			screen.switchConsoleSettings(true);
			menu.changeMaxCoords(1);
			screen.printMenu(menu.pauseMenu);
			keyListener.whatIsRunning = "pauseMenuRunning";
			break;
		}
	}
}