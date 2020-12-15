package main;

public class ExtSpike extends Entity {

	Screen screen;
	Menu menu;
	int length;
	int coordsXSpikeHead;
	int coordsYSpikeHead;
	boolean isExtending = true;
	boolean canDestroy;
	int extentionTime = 100;
	int spikeTimer;

//initializes the references and the color
	public ExtSpike(char pRotation, Screen pScreen, Menu pMenu) {
		rotation = pRotation;
		screen = pScreen;
		menu = pMenu;
		color = 4;
	}

// checks for every case if something is in front of the spike
	private boolean isSomethingInFront(char pContent) {
		switch (rotation) {
		case 'd':
			return Map.map[coordsXSpikeHead + 1][coordsYSpikeHead] != pContent;
		case 's':
			return Map.map[coordsXSpikeHead][coordsYSpikeHead + 1] == pContent;
		case 'a':
			return Map.map[coordsXSpikeHead - 1][coordsYSpikeHead] == pContent;
		case 'w':
			return Map.map[coordsXSpikeHead][coordsYSpikeHead - 1] == pContent;
		}
		return true;
	}

	private Entity whatIsInFront() {
		switch (rotation) {
		case 'd':
			return Map.entityMap[coordsXSpikeHead + 1][coordsYSpikeHead];
		case 's':
			return Map.entityMap[coordsXSpikeHead][coordsYSpikeHead + 1];
		case 'a':
			return Map.entityMap[coordsXSpikeHead - 1][coordsYSpikeHead];
		case 'w':
			return Map.entityMap[coordsXSpikeHead][coordsYSpikeHead - 1];
		}
		return null;
	}

//a method to let the spike extend and retract alternately with a extention time between it
	public void spikeTimer() {
		if (isExtending && isSomethingInFront(' ')) {
			extendSpike();
		} else if (whatIsInFront() instanceof Player || whatIsInFront() instanceof Player
				|| whatIsInFront() instanceof Player || whatIsInFront() instanceof Player) {
			menu.executeDeathAnimation();
		} else if (isExtending && spikeTimer < extentionTime) {
			spikeTimer++;
		} else if (isExtending) {
			isExtending = false;
			spikeTimer = 0;
		}
		if (!isExtending && (coordsXSpikeHead != coordsXEntity || coordsYSpikeHead != coordsYEntity)) {
			retractSpike();
		} else if (!isExtending && spikeTimer < extentionTime) {
			spikeTimer++;
		} else if (!isExtending) {
			isExtending = true;
			spikeTimer = 0;
		}
	}

//moves the current head location by one away from the entity position
	private void extendSpike() {
		switch (rotation) {
		case 'd':
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = '-';
			screen.print(coordsXSpikeHead, coordsYSpikeHead, color);
			coordsXSpikeHead++;
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = '>';
			screen.print(coordsXSpikeHead, coordsYSpikeHead, color);
			break;
		case 's':
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = '|';
			screen.print(coordsXSpikeHead, coordsYSpikeHead, color);
			coordsYSpikeHead++;
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = 'v';
			screen.print(coordsXSpikeHead, coordsYSpikeHead, color);
			break;
		case 'a':
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = '-';
			screen.print(coordsXSpikeHead, coordsYSpikeHead, color);
			coordsXSpikeHead--;
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = '<';
			screen.print(coordsXSpikeHead, coordsYSpikeHead, color);
			break;
		case 'w':
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = '|';
			screen.print(coordsXSpikeHead, coordsYSpikeHead, color);
			coordsYSpikeHead--;
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = '^';
			screen.print(coordsXSpikeHead, coordsYSpikeHead, color);
			break;
		}
	}

//moves the current head location by one to the entity position
	private void retractSpike() {
		switch (rotation) {
		case 'd':
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = ' ';
			screen.print(coordsXSpikeHead, coordsYSpikeHead, color);
			coordsXSpikeHead--;
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = '>';
			screen.print(coordsXSpikeHead, coordsYSpikeHead, color);
			break;
		case 's':
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = ' ';
			screen.print(coordsXSpikeHead, coordsYSpikeHead, color);
			coordsYSpikeHead--;
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = 'v';
			screen.print(coordsXSpikeHead, coordsYSpikeHead, color);
			break;
		case 'a':
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = ' ';
			screen.print(coordsXSpikeHead, coordsYSpikeHead, color);
			coordsXSpikeHead++;
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = '<';
			screen.print(coordsXSpikeHead, coordsYSpikeHead, color);
			break;
		case 'w':
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = ' ';
			screen.print(coordsXSpikeHead, coordsYSpikeHead, color);
			coordsYSpikeHead++;
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = '^';
			screen.print(coordsXSpikeHead, coordsYSpikeHead, color);
			break;
		}
	}

	public void inizializeCoordsSpikeHead() {
		coordsXSpikeHead = coordsXEntity;
		coordsYSpikeHead = coordsYEntity;
	}
}