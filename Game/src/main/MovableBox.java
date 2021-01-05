package main;

public class MovableBox extends Entity {
	char content;

	public MovableBox(int pCoordsXEntity, int pCoordsYEntity, Screen pScreen, Menu pMenu) {
		color = 8;
		content = '@';
		coordsXEntity = pCoordsXEntity;
		coordsYEntity = pCoordsYEntity;
		screen = pScreen;
		menu = pMenu;
	}

//moves the box depentend on the rotation of the player
	public boolean moveBox(char pRotation) {
		Entity box;
		if (!isSomethingInFront(pRotation, ' ')) {
			return false;
		}
		switch (pRotation) {
		case 'd':
			Map.map[coordsXEntity][coordsYEntity] = ' ';
			screen.print(coordsXEntity, coordsYEntity, color);
			box = Map.entityMap[coordsXEntity][coordsYEntity];
			Map.entityMap[coordsXEntity][coordsYEntity] = null;
			coordsXEntity++;
			Map.map[coordsXEntity][coordsYEntity] = content;
			screen.print(coordsXEntity, coordsYEntity, color);
			Map.entityMap[coordsXEntity][coordsYEntity] = box;
			return true;
		case 's':
			Map.map[coordsXEntity][coordsYEntity] = ' ';
			screen.print(coordsXEntity, coordsYEntity, color);
			box = Map.entityMap[coordsXEntity][coordsYEntity];
			Map.entityMap[coordsXEntity][coordsYEntity] = null;
			coordsYEntity++;
			Map.map[coordsXEntity][coordsYEntity] = content;
			screen.print(coordsXEntity, coordsYEntity, color);
			Map.entityMap[coordsXEntity][coordsYEntity] = box;
			return true;
		case 'a':
			Map.map[coordsXEntity][coordsYEntity] = ' ';
			screen.print(coordsXEntity, coordsYEntity, color);
			box = Map.entityMap[coordsXEntity][coordsYEntity];
			Map.entityMap[coordsXEntity][coordsYEntity] = null;
			coordsXEntity--;
			Map.map[coordsXEntity][coordsYEntity] = content;
			screen.print(coordsXEntity, coordsYEntity, color);
			Map.entityMap[coordsXEntity][coordsYEntity] = box;
			return true;
		case 'w':
			Map.map[coordsXEntity][coordsYEntity] = ' ';
			screen.print(coordsXEntity, coordsYEntity, color);
			box = Map.entityMap[coordsXEntity][coordsYEntity];
			Map.entityMap[coordsXEntity][coordsYEntity] = null;
			coordsYEntity--;
			Map.map[coordsXEntity][coordsYEntity] = content;
			screen.print(coordsXEntity, coordsYEntity, color);
			Map.entityMap[coordsXEntity][coordsYEntity] = box;
			return true;
		}
		return false;
	}

// checks for every case if something is in front of the spike
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
}
