package main;

public class MovableBox extends Entity {
	Screen screen;
	Menu menu;
	char content;

	public MovableBox(int pCoordsXEntity, int pCoordsYEntity, Screen pScreen, Menu pMenu) {
		content = '@';
		coordsXEntity = pCoordsXEntity;
		coordsYEntity = pCoordsYEntity;
		screen = pScreen;
		menu = pMenu;
	}

	public boolean moveBox(char pRotation) {
		Entity box;
		if(!isSomethingInFront(pRotation ,' ')) {
			return false;
		}
		switch (pRotation) {
		case 'd':
			screen.print(coordsXEntity, coordsYEntity, ' ');
			box = Map.entityMap[coordsXEntity][coordsYEntity];
			Map.entityMap[coordsXEntity][coordsYEntity] = null;
			coordsXEntity++;
			screen.print(coordsXEntity, coordsYEntity, '@');
			Map.entityMap[coordsXEntity][coordsYEntity] = box;
			return true;
		case 's':
			screen.print(coordsXEntity, coordsYEntity, ' ');
			box = Map.entityMap[coordsXEntity][coordsYEntity];
			Map.entityMap[coordsXEntity][coordsYEntity] = null;
			coordsXEntity++;
			screen.print(coordsXEntity, coordsYEntity, '@');
			Map.entityMap[coordsXEntity][coordsYEntity] = box;
			return true;
		case 'a':
			screen.print(coordsXEntity, coordsYEntity, ' ');
			box = Map.entityMap[coordsXEntity][coordsYEntity];
			Map.entityMap[coordsXEntity][coordsYEntity] = null;
			coordsXEntity++;
			screen.print(coordsXEntity, coordsYEntity, '@');
			Map.entityMap[coordsXEntity][coordsYEntity] = box;
			return true;
		case 'w':
			screen.print(coordsXEntity, coordsYEntity, ' ');
			box = Map.entityMap[coordsXEntity][coordsYEntity];
			Map.entityMap[coordsXEntity][coordsYEntity] = null;
			coordsXEntity++;
			screen.print(coordsXEntity, coordsYEntity, '@');
			Map.entityMap[coordsXEntity][coordsYEntity] = box;
			return true;
		}
		return false;
	}
	
	// checks for every case if something is in front of the spike
		private boolean isSomethingInFront(char pRotation ,char pContent) {
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