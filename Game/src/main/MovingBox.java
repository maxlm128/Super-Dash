package main;

public class MovingBox extends Entity {
	boolean goingBack;
	
	public MovingBox(Screen pScreen,int pCoordsXEntity,int pCoordsYEntity) {
		coordsXEntity = pCoordsXEntity;
		coordsYEntity = pCoordsYEntity;
		screen = pScreen;
		color = 8;
	}

	// checks for every case if something is in front of the spike
	private boolean isSomethingInFront(char pContent) {
		switch (rotation) {
		case '-':
			if (goingBack) {
				return Map.map[coordsXEntity + 1][coordsYEntity] == pContent;
			} else {
				return Map.map[coordsXEntity - 1][coordsYEntity] == pContent;
			}
		case '|':
			if (goingBack) {
				return Map.map[coordsXEntity][coordsYEntity - 1] == pContent;
			} else {
				return Map.map[coordsXEntity][coordsYEntity + 1] == pContent;
			}
		}
		return true;
	}

	public void moveBlockTimer() {
		if (!goingBack && isSomethingInFront(' ')) {
			moveBlockAway();
		} else if (!goingBack) {
			goingBack = true;
		} else if (goingBack && isSomethingInFront(' ')) {
			moveBlockBack();
		} else if (goingBack) {
			goingBack = false;
		}
	}

	public void moveBlockAway() {
		MovingBox movingBox = (MovingBox) Map.entityMap[coordsXEntity][coordsYEntity];
		switch (rotation) {
		case '-':
			Map.map[coordsXEntity][coordsYEntity] = ' ';
			Map.entityMap[coordsXEntity][coordsYEntity] = null;
			screen.print(coordsXEntity, coordsYEntity, color);
			coordsXEntity--;
			Map.map[coordsXEntity][coordsYEntity] = 'O';
			Map.entityMap[coordsXEntity][coordsYEntity] = movingBox;
			screen.print(coordsXEntity, coordsYEntity, color);
		case '|':
			Map.map[coordsXEntity][coordsYEntity] = ' ';
			Map.entityMap[coordsXEntity][coordsYEntity] = null;
			screen.print(coordsXEntity, coordsYEntity, color);
			coordsYEntity--;
			Map.map[coordsXEntity][coordsYEntity] = 'O';
			Map.entityMap[coordsXEntity][coordsYEntity] = movingBox;
			screen.print(coordsXEntity, coordsYEntity, color);
		}
	}

	public void moveBlockBack() {
		MovingBox movingBox = (MovingBox) Map.entityMap[coordsXEntity][coordsYEntity];
		switch (rotation) {
		case '-':
			Map.map[coordsXEntity][coordsYEntity] = ' ';
			Map.entityMap[coordsXEntity][coordsYEntity] = null;
			screen.print(coordsXEntity, coordsYEntity, color);
			coordsXEntity++;
			Map.map[coordsXEntity][coordsYEntity] = 'O';
			Map.entityMap[coordsXEntity][coordsYEntity] = movingBox;
			screen.print(coordsXEntity, coordsYEntity, color);
		case '|':
			Map.map[coordsXEntity][coordsYEntity] = ' ';
			Map.entityMap[coordsXEntity][coordsYEntity] = null;
			screen.print(coordsXEntity, coordsYEntity, color);
			coordsYEntity++;
			Map.map[coordsXEntity][coordsYEntity] = 'O';
			Map.entityMap[coordsXEntity][coordsYEntity] = movingBox;
			screen.print(coordsXEntity, coordsYEntity, color);
		}
	}
}
