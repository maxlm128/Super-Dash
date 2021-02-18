package main;

public class MovingBall extends Entity {
	boolean goingBack;
	int movingDelay = 1;
	int movingTimer = movingDelay;

	public MovingBall(Screen pScreen, int pCoordsXEntity, int pCoordsYEntity, char pRotation) {
		coordsXEntity = pCoordsXEntity;
		coordsYEntity = pCoordsYEntity;
		screen = pScreen;
		color = 8;
		rotation = pRotation;
		content = 'O';
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
				return Map.map[coordsXEntity][coordsYEntity + 1] == pContent;
			} else {
				return Map.map[coordsXEntity][coordsYEntity - 1] == pContent;
			}
		}
		return true;
	}

//a method which controls whether the ball should move forward or back
	public void ballTimer() {
		if (0 < movingTimer && movingTimer < movingDelay) {
			movingTimer--;
		} else if (movingTimer < 1) {
			movingTimer = movingDelay;
		} else if (!goingBack && isSomethingInFront(' ')) {
			moveBlockAway();
			movingTimer--;
		} else if (!goingBack) {
			goingBack = true;
		} else if (goingBack && isSomethingInFront(' ')) {
			moveBlockBack();
			movingTimer--;
		} else if (goingBack) {
			goingBack = false;
		}
	}

//moves the ball one away by adding one to the x or y coordinate and prints the ball onto the screen
	public void moveBlockAway() {
		MovingBall movingBox = (MovingBall) Map.entityMap[coordsXEntity][coordsYEntity];
		switch (rotation) {
		case '-':
			Map.map[coordsXEntity][coordsYEntity] = ' ';
			Map.entityMap[coordsXEntity][coordsYEntity] = null;
			screen.print(coordsXEntity, coordsYEntity, color);
			coordsXEntity--;
			Map.map[coordsXEntity][coordsYEntity] = content;
			Map.entityMap[coordsXEntity][coordsYEntity] = movingBox;
			screen.print(coordsXEntity, coordsYEntity, color);
			break;
		case '|':
			Map.map[coordsXEntity][coordsYEntity] = ' ';
			Map.entityMap[coordsXEntity][coordsYEntity] = null;
			screen.print(coordsXEntity, coordsYEntity, color);
			coordsYEntity--;
			Map.map[coordsXEntity][coordsYEntity] = content;
			Map.entityMap[coordsXEntity][coordsYEntity] = movingBox;
			screen.print(coordsXEntity, coordsYEntity, color);
			break;
		}
	}

//moves the ball one back by subtracting one from the x or y coordinate and prints the ball onto the screen
	public void moveBlockBack() {
		MovingBall movingBox = (MovingBall) Map.entityMap[coordsXEntity][coordsYEntity];
		switch (rotation) {
		case '-':
			Map.map[coordsXEntity][coordsYEntity] = ' ';
			Map.entityMap[coordsXEntity][coordsYEntity] = null;
			screen.print(coordsXEntity, coordsYEntity, color);
			coordsXEntity++;
			Map.map[coordsXEntity][coordsYEntity] = content;
			Map.entityMap[coordsXEntity][coordsYEntity] = movingBox;
			screen.print(coordsXEntity, coordsYEntity, color);
			break;
		case '|':
			Map.map[coordsXEntity][coordsYEntity] = ' ';
			Map.entityMap[coordsXEntity][coordsYEntity] = null;
			screen.print(coordsXEntity, coordsYEntity, color);
			coordsYEntity++;
			Map.map[coordsXEntity][coordsYEntity] = content;
			Map.entityMap[coordsXEntity][coordsYEntity] = movingBox;
			screen.print(coordsXEntity, coordsYEntity, color);
			break;
		}
	}
}