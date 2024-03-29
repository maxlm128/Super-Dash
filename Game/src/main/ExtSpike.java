package main;

public class ExtSpike extends Entity {
	Entity extSpikeNeck;
	int colorHead;
	int coordsXSpikeHead;
	int coordsYSpikeHead;
	boolean isExtending = true;
	boolean extend = true;
	int extentionTime = 100;
	int spikeTimer;

//initializes the references and the color
	public ExtSpike(char pRotation, Screen pScreen, Menu pMenu) {
		rotation = pRotation;
		screen = pScreen;
		menu = pMenu;
		color = 1;
		colorHead = 1;
		extSpikeNeck = new ExtSpikeNeck(colorHead,rotation);
		switch(rotation) {
		case'd':
			content = '>';
			break;
		case'a':
			content = '<';
			break;
		case'w':
			content = '^';
			break;
		case's':
			content = 'v';
			break;
		}
	}

//checks for every case if something is in front of the spike
	private boolean isSomethingInFront(char pContent) {
		switch (rotation) {
		case 'd':
			return Map.map[coordsXSpikeHead + 1][coordsYSpikeHead] == pContent;
		case 's':
			return Map.map[coordsXSpikeHead][coordsYSpikeHead + 1] == pContent;
		case 'a':
			return Map.map[coordsXSpikeHead - 1][coordsYSpikeHead] == pContent;
		case 'w':
			return Map.map[coordsXSpikeHead][coordsYSpikeHead - 1] == pContent;
		}
		return true;
	}

//checks what entity is in front and return the entity
	private Entity whatEntityIsInFront() {
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
		if (whatEntityIsInFront() instanceof Player) {
			menu.executeDeathAnimation();
		}
		if (isExtending && isSomethingInFront(' ') && !(whatEntityIsInFront() instanceof MovableBox) && extend) {
			extendSpike();
		} else if (isExtending && isSomethingInFront('X') && extend && spikeTimer < extentionTime) {
			extendSpike();
			extend = false;
			spikeTimer++;
		} else if (isExtending && spikeTimer < extentionTime) {
			extend = false;
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
			extend = true;
			isExtending = true;
			spikeTimer = 0;
		}
	}

//moves the current head location by one away to the entity position depentend on the rotation
	private void extendSpike() {
		switch (rotation) {
		case 'd':
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = extSpikeNeck.content;
			screen.print(coordsXSpikeHead, coordsYSpikeHead, color);
			coordsXSpikeHead++;
			if (!(Map.entityMap[coordsXSpikeHead][coordsYSpikeHead] instanceof ExtSpike)) {
				Map.entityMap[coordsXSpikeHead][coordsYSpikeHead] = extSpikeNeck;
			}
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = content;
			screen.print(coordsXSpikeHead, coordsYSpikeHead, colorHead);
			break;
		case 's':
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = extSpikeNeck.content;
			screen.print(coordsXSpikeHead, coordsYSpikeHead, color);
			coordsYSpikeHead++;
			if (!(Map.entityMap[coordsXSpikeHead][coordsYSpikeHead] instanceof ExtSpike)) {
				Map.entityMap[coordsXSpikeHead][coordsYSpikeHead] = extSpikeNeck;
			}
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = content;
			screen.print(coordsXSpikeHead, coordsYSpikeHead, colorHead);
			break;
		case 'a':
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = extSpikeNeck.content;
			screen.print(coordsXSpikeHead, coordsYSpikeHead, color);
			coordsXSpikeHead--;
			if (!(Map.entityMap[coordsXSpikeHead][coordsYSpikeHead] instanceof ExtSpike)) {
				Map.entityMap[coordsXSpikeHead][coordsYSpikeHead] = extSpikeNeck;
			}
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = content;
			screen.print(coordsXSpikeHead, coordsYSpikeHead, colorHead);
			break;
		case 'w':
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = extSpikeNeck.content;
			screen.print(coordsXSpikeHead, coordsYSpikeHead, color);
			coordsYSpikeHead--;
			if (!(Map.entityMap[coordsXSpikeHead][coordsYSpikeHead] instanceof ExtSpike)) {
				Map.entityMap[coordsXSpikeHead][coordsYSpikeHead] = extSpikeNeck;
			}
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = content;
			screen.print(coordsXSpikeHead, coordsYSpikeHead, colorHead);
			break;
		}
	}

//moves the current head location by one to the entity position depentend on the rotation
	private void retractSpike() {
		switch (rotation) {
		case 'd':
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = ' ';
			Map.entityMap[coordsXSpikeHead][coordsYSpikeHead] = null;
			screen.print(coordsXSpikeHead, coordsYSpikeHead, color);
			coordsXSpikeHead--;
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = content;
			screen.print(coordsXSpikeHead, coordsYSpikeHead, colorHead);
			break;
		case 's':
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = ' ';
			Map.entityMap[coordsXSpikeHead][coordsYSpikeHead] = null;
			screen.print(coordsXSpikeHead, coordsYSpikeHead, color);
			coordsYSpikeHead--;
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = content;
			screen.print(coordsXSpikeHead, coordsYSpikeHead, colorHead);
			break;
		case 'a':
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = ' ';
			Map.entityMap[coordsXSpikeHead][coordsYSpikeHead] = null;
			screen.print(coordsXSpikeHead, coordsYSpikeHead, color);
			coordsXSpikeHead++;
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = content;
			screen.print(coordsXSpikeHead, coordsYSpikeHead, colorHead);
			break;
		case 'w':
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = ' ';
			Map.entityMap[coordsXSpikeHead][coordsYSpikeHead] = null;
			screen.print(coordsXSpikeHead, coordsYSpikeHead, color);
			coordsYSpikeHead++;
			Map.map[coordsXSpikeHead][coordsYSpikeHead] = content;
			screen.print(coordsXSpikeHead, coordsYSpikeHead, colorHead);
			break;
		}
	}

//inizializes the coordinates of the head 
	public void inizializeCoordsSpikeHead() {
		coordsXSpikeHead = coordsXEntity;
		coordsYSpikeHead = coordsYEntity;
	}
}
