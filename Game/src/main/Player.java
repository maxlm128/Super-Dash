package main;

import com.asecave.Console;

public class Player extends Entity {
	int tailTime;
	int gameDelay;
	int colorCycleTimer;
	char currentInput;
	Console console;
	KeyListener keyListener;
	Map map;
	Player player;

	public Player() {
		rotation = 'd';
		content = '<';
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

//settter for the rotation
	private void setRotation(char pRotation) {
		rotation = pRotation;
		switch (rotation) {
		case 'd':
			content = '<';
			break;
		case 's':
			content = '^';
			break;
		case 'a':
			content = '>';
			break;
		case 'w':
			content = 'v';
			break;
		}
	}

// changes the coordinates from player to the new position and prints it out on
// the screen
	private void setCoordsPlayer(int pCoordsXPlayer, int pCoordsYPlayer, char pContent) {
		if (pCoordsXPlayer + 1 < Map.map.length && pCoordsYPlayer + 1 < Map.map[0].length && pCoordsXPlayer > 0
				&& pCoordsYPlayer > 0) {
			Map.map[coordsXEntity][coordsYEntity] = ' ';
			Map.entityMap[coordsXEntity][coordsYEntity] = null;
			screen.print(coordsXEntity, coordsYEntity, color);
			Map.map[pCoordsXPlayer][pCoordsYPlayer] = pContent;
			Map.entityMap[pCoordsXPlayer][pCoordsYPlayer] = player;
			coordsXEntity = pCoordsXPlayer;
			coordsYEntity = pCoordsYPlayer;
			screen.print(coordsXEntity, coordsYEntity, color);
		} else {
			menu.executeDeathAnimation();
		}
	}

//setter for the player color
	public void setPlayerColor(int pColor) {
		color = pColor;
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

	public void cycleColors() {
		if (color < 14 && colorCycleTimer == 20) {
			color++;
			colorCycleTimer = 0;
		} else if (color < 14) {
			colorCycleTimer++;
		} else {
			color = 1;
		}
	}

//checks for every case if something is in front of the player
	private boolean isSomethingInFront(char pContent) {
		switch (rotation) {
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

//checks which entity is in front and returns it
	private Entity whichEntityInFront() {
		switch (rotation) {
		case 'd':
			return Map.entityMap[coordsXEntity + 1][coordsYEntity];
		case 's':
			return Map.entityMap[coordsXEntity][coordsYEntity + 1];
		case 'a':
			return Map.entityMap[coordsXEntity - 1][coordsYEntity];
		case 'w':
			return Map.entityMap[coordsXEntity][coordsYEntity - 1];
		}
		return null;
	}

//checks if a spike is in front and returns a boolean
	private boolean isSpikeInFront() {
		switch (rotation) {
		case 'd':
			return Map.entityMap[coordsXEntity + 1][coordsYEntity] instanceof ExtSpike
					|| Map.entityMap[coordsXEntity + 1][coordsYEntity] instanceof ExtSpikeNeck;
		case 's':
			return Map.entityMap[coordsXEntity][coordsYEntity + 1] instanceof ExtSpike
					|| Map.entityMap[coordsXEntity][coordsYEntity + 1] instanceof ExtSpikeNeck;
		case 'a':
			return Map.entityMap[coordsXEntity - 1][coordsYEntity] instanceof ExtSpike
					|| Map.entityMap[coordsXEntity - 1][coordsYEntity] instanceof ExtSpikeNeck;
		case 'w':
			return Map.entityMap[coordsXEntity][coordsYEntity - 1] instanceof ExtSpike
					|| Map.entityMap[coordsXEntity][coordsYEntity - 1] instanceof ExtSpikeNeck;
		}
		return true;
	}

//checks if the keypress is one of the valid keypresses and executes a lot of
//associated methods with a lot of conditions ,the methods are for the keypresses a,s,d and w almost the same, but with a lot of rotation based conditions
//the only other keypress is the escape button ,which executes the pause menu

//1: when a movable box is in front of the player, the box is moved when nothing is in front of the box and the player moves also by one
//2: when the finish block is in front of the player, the passedMenu is printed
//3: when a bouncer is in front of the player, the players rotation changes dependent on the rotation of the boucer
//4: when a spike is in front the player, the death animation is executed
//5: when a moving ball is in front of the player, the rotation of the moving ball is changed. In this case, the method does not stop but check the remaining conditions
//6: when no air is in front of the player the player does not move but changes its rotation dependent on the keypress
//7: when all this conditions are not true, the player moves normally
	public void doAction(char pInputChar) {
		if (currentInput != 0 && pInputChar != 27) {
			pInputChar = currentInput;
		} else if (pInputChar != 27) {
			currentInput = pInputChar;
		}
		method: switch (pInputChar) {
		case 'w':
			setRotation('w');
			// 1:
			if (whichEntityInFront() instanceof MovableBox) {
				if (((MovableBox) whichEntityInFront()).moveBox('w')) {
					currentInput = 0;
					setCoordsPlayer(coordsXEntity, coordsYEntity - 1, content);
					Map.trailMap[coordsXEntity][coordsYEntity + 1] = tailTime;
					screen.printTrail(coordsXEntity, coordsYEntity + 1, true);
					break method;
				} else {
					currentInput = 0;
					setCoordsPlayer(coordsXEntity, coordsYEntity, content);
					break method;
				}
			}
			// 2:
			if (whichEntityInFront() instanceof FinishBlock) {
				currentInput = 0;
				screen.switchConsoleSettings(true);
				menu.changeMaxCoords(1, true);
				screen.printMenu(menu.passedMenu);
				keyListener.whatIsRunning = "passedMenuRunning";
				break method;
			}
			// 3:
			if (whichEntityInFront() instanceof Bouncer) {
				if (whichEntityInFront().rotation == '/') {
					setCoordsPlayer(coordsXEntity + 1, coordsYEntity - 1, content);
					Map.trailMap[coordsXEntity - 1][coordsYEntity + 1] = tailTime;
					screen.printTrail(coordsXEntity - 1, coordsYEntity + 1, true);
					setRotation('d');
					currentInput = 'd';
					break method;
				} else if (whichEntityInFront().rotation == '\\') {
					setCoordsPlayer(coordsXEntity - 1, coordsYEntity - 1, content);
					Map.trailMap[coordsXEntity + 1][coordsYEntity + 1] = tailTime;
					screen.printTrail(coordsXEntity + 1, coordsYEntity + 1, true);
					setRotation('a');
					currentInput = 'a';
					break method;
				}
			}
			// 4:
			if (isSpikeInFront()) {
				currentInput = 0;
				menu.executeDeathAnimation();
				break method;
			}
			// 5:
			if (whichEntityInFront() instanceof MovingBall) {
				((MovingBall) whichEntityInFront()).rotation = '|';
			}
			// 6:
			if (!isSomethingInFront(' ')) {
				currentInput = 0;
				setCoordsPlayer(coordsXEntity, coordsYEntity, content);
				break method;
			}
			// 7:
			setCoordsPlayer(coordsXEntity, coordsYEntity - 1, content);
			Map.trailMap[coordsXEntity][coordsYEntity + 1] = tailTime;
			screen.printTrail(coordsXEntity, coordsYEntity + 1, true);
			break;
		case 'a':
			setRotation('a');
			// 1:
			if (whichEntityInFront() instanceof MovableBox) {
				if (((MovableBox) whichEntityInFront()).moveBox('a')) {
					currentInput = 0;
					setCoordsPlayer(coordsXEntity - 1, coordsYEntity, content);
					Map.trailMap[coordsXEntity + 1][coordsYEntity] = tailTime;
					screen.printTrail(coordsXEntity + 1, coordsYEntity, true);
					break method;
				} else {
					currentInput = 0;
					setCoordsPlayer(coordsXEntity, coordsYEntity, content);
					break method;
				}
			}
			// 2:
			if (whichEntityInFront() instanceof FinishBlock) {
				currentInput = 0;
				screen.switchConsoleSettings(true);
				menu.changeMaxCoords(1, true);
				screen.printMenu(menu.passedMenu);
				keyListener.whatIsRunning = "passedMenuRunning";
				break method;
			}
			// 3:
			if (whichEntityInFront() instanceof Bouncer) {
				if (whichEntityInFront().rotation == '/') {
					setCoordsPlayer(coordsXEntity - 1, coordsYEntity + 1, content);
					Map.trailMap[coordsXEntity + 1][coordsYEntity - 1] = tailTime;
					screen.printTrail(coordsXEntity + 1, coordsYEntity - 1, true);
					setRotation('s');
					currentInput = 's';
					break method;
				} else if (whichEntityInFront().rotation == '\\') {
					setCoordsPlayer(coordsXEntity - 1, coordsYEntity - 1, content);
					Map.trailMap[coordsXEntity + 1][coordsYEntity + 1] = tailTime;
					screen.printTrail(coordsXEntity + 1, coordsYEntity + 1, true);
					setRotation('w');
					currentInput = 'w';
					break method;
				}
			}
			// 4:
			if (isSpikeInFront()) {
				currentInput = 0;
				menu.executeDeathAnimation();
				break method;
			}
			// 5:
			if (whichEntityInFront() instanceof MovingBall) {
				((MovingBall) whichEntityInFront()).rotation = '-';
			}
			// 6:
			if (!isSomethingInFront(' ')) {
				currentInput = 0;
				setCoordsPlayer(coordsXEntity, coordsYEntity, content);
				break method;
			}
			// 7:
			setCoordsPlayer(coordsXEntity - 1, coordsYEntity, content);
			Map.trailMap[coordsXEntity + 1][coordsYEntity] = tailTime;
			screen.printTrail(coordsXEntity + 1, coordsYEntity, true);
			break method;
		case 's':
			setRotation('s');
			// 1:
			if (whichEntityInFront() instanceof MovableBox) {
				if (((MovableBox) whichEntityInFront()).moveBox('s')) {
					currentInput = 0;
					setCoordsPlayer(coordsXEntity, coordsYEntity + 1, content);
					Map.trailMap[coordsXEntity][coordsYEntity - 1] = tailTime;
					screen.printTrail(coordsXEntity, coordsYEntity - 1, true);
					break method;
				} else {
					currentInput = 0;
					setCoordsPlayer(coordsXEntity, coordsYEntity, content);
					break method;
				}
			}
			// 2:
			if (whichEntityInFront() instanceof FinishBlock) {
				currentInput = 0;
				screen.switchConsoleSettings(true);
				menu.changeMaxCoords(1, true);
				screen.printMenu(menu.passedMenu);
				keyListener.whatIsRunning = "passedMenuRunning";
				break method;
			}
			// 3:
			if (whichEntityInFront() instanceof Bouncer) {
				if (whichEntityInFront().rotation == '/') {
					setCoordsPlayer(coordsXEntity - 1, coordsYEntity + 1, content);
					Map.trailMap[coordsXEntity + 1][coordsYEntity - 1] = tailTime;
					screen.printTrail(coordsXEntity + 1, coordsYEntity - 1, true);
					setRotation('a');
					currentInput = 'a';
					break method;
				} else if (whichEntityInFront().rotation == '\\') {
					setCoordsPlayer(coordsXEntity + 1, coordsYEntity + 1, content);
					Map.trailMap[coordsXEntity - 1][coordsYEntity - 1] = tailTime;
					screen.printTrail(coordsXEntity - 1, coordsYEntity - 1, true);
					setRotation('d');
					currentInput = 'd';
					break method;
				}
			}
			// 4:
			if (isSpikeInFront()) {
				currentInput = 0;
				menu.executeDeathAnimation();
				break method;
			}
			// 5:
			if (whichEntityInFront() instanceof MovingBall) {
				((MovingBall) whichEntityInFront()).rotation = '|';
			}
			// 6:
			if (!isSomethingInFront(' ')) {
				currentInput = 0;
				setCoordsPlayer(coordsXEntity, coordsYEntity, content);
				break method;
			}
			// 7:
			setCoordsPlayer(coordsXEntity, coordsYEntity + 1, content);
			Map.trailMap[coordsXEntity][coordsYEntity - 1] = tailTime;
			screen.printTrail(coordsXEntity, coordsYEntity - 1, true);
			break method;
		case 'd':
			setRotation('d');
			// 1:
			if (whichEntityInFront() instanceof MovableBox) {
				if (((MovableBox) whichEntityInFront()).moveBox('d')) {
					currentInput = 0;
					setCoordsPlayer(coordsXEntity + 1, coordsYEntity, content);
					Map.trailMap[coordsXEntity - 1][coordsYEntity] = tailTime;
					screen.printTrail(coordsXEntity - 1, coordsYEntity, true);
					break method;
				} else {
					currentInput = 0;
					setCoordsPlayer(coordsXEntity, coordsYEntity, content);
					break method;
				}
			}
			// 2:
			if (whichEntityInFront() instanceof FinishBlock) {
				currentInput = 0;
				screen.switchConsoleSettings(true);
				menu.changeMaxCoords(1, true);
				screen.printMenu(menu.passedMenu);
				keyListener.whatIsRunning = "passedMenuRunning";
				break method;
			}
			// 3:
			if (whichEntityInFront() instanceof Bouncer) {
				if (whichEntityInFront().rotation == '/') {
					setCoordsPlayer(coordsXEntity + 1, coordsYEntity - 1, content);
					Map.trailMap[coordsXEntity - 1][coordsYEntity + 1] = tailTime;
					screen.printTrail(coordsXEntity - 1, coordsYEntity + 1, true);
					setRotation('w');
					currentInput = 'w';
					break method;
				} else if (whichEntityInFront().rotation == '\\') {
					setCoordsPlayer(coordsXEntity + 1, coordsYEntity + 1, content);
					Map.trailMap[coordsXEntity - 1][coordsYEntity - 1] = tailTime;
					screen.printTrail(coordsXEntity - 1, coordsYEntity - 1, true);
					setRotation('s');
					currentInput = 's';
					break method;
				}
			}
			// 4:
			if (isSpikeInFront()) {
				currentInput = 0;
				menu.executeDeathAnimation();
				break method;
			}
			// 5:
			if (whichEntityInFront() instanceof MovingBall) {
				((MovingBall) whichEntityInFront()).rotation = '-';
			}
			// 6:
			if (!isSomethingInFront(' ')) {
				currentInput = 0;
				setCoordsPlayer(coordsXEntity, coordsYEntity, content);
				break method;
			}
			// 7:
			setCoordsPlayer(coordsXEntity + 1, coordsYEntity, content);
			Map.trailMap[coordsXEntity - 1][coordsYEntity] = tailTime;
			screen.printTrail(coordsXEntity - 1, coordsYEntity, true);
			break method;
		case 27:
			screen.switchConsoleSettings(true);
			menu.changeMaxCoords(1, true);
			screen.printMenu(menu.pauseMenu);
			keyListener.whatIsRunning = "pauseMenuRunning";
			break method;
		default:
			currentInput = 0;
			break method;
		}
	}
}