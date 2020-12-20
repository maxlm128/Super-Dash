package main;

class Map {
	String currentMap;
	int width;
	int height;
	static char[][] map;
	static int[][] trailMap;
	static Entity[][] entityMap;
	Player player;
	Screen screen;
	Menu menu;

// new reference to the class Player
	public void newPlayerReference(Player pPlayer) {
		player = pPlayer;
	}

// new reference to the class Screen
	public void newScreenReference(Screen pScreen) {
		screen = pScreen;
	}

// new Reference to the class Menu
	public void newMenuReference(Menu pMenu) {
		menu = pMenu;
	}

// creates the new array and saves the borders of the map into the new array
	public void newMapArray(String pFile, boolean pReload) {
		MapLoader.loadAll(pFile);
		map = MapLoader.charsgetMap();
		width = map.length;
		height = map[0].length;
		trailMap = new int[width][height];
		entityMap = new Entity[width][height];
		currentMap = pFile;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				ExtSpike extSpike;
				switch(map[x][y]) {
				case'<':
					extSpike = new ExtSpike('a', screen, menu);
					entityMap[x][y] = extSpike;
					extSpike.coordsXEntity = x;
					extSpike.coordsYEntity = y;
					extSpike.inizializeCoordsSpikeHead();
					break;
				case'^':
					extSpike = new ExtSpike('w', screen, menu);
					entityMap[x][y] = extSpike;
					extSpike.coordsXEntity = x;
					extSpike.coordsYEntity = y;
					extSpike.inizializeCoordsSpikeHead();
					break;
				case'>':
					extSpike = new ExtSpike('d', screen, menu);
					entityMap[x][y] = extSpike;
					extSpike.coordsXEntity = x;
					extSpike.coordsYEntity = y;
					extSpike.inizializeCoordsSpikeHead();
					break;
				case'v':
					extSpike = new ExtSpike('s', screen, menu);
					entityMap[x][y] = extSpike;
					extSpike.coordsXEntity = x;
					extSpike.coordsYEntity = y;
					extSpike.inizializeCoordsSpikeHead();
					break;
				case'p':
					player.coordsXEntity = x;
					player.coordsYEntity = y;
					map[x][y] = '<';
					entityMap[x][y] = player;
					break;
				case'@':
					Map.entityMap[x][y] = new MovableBox(x, y, screen, menu);
					break;
				case'O':
					Map.entityMap[x][y] = new FinishBlock(x,y);
					break;
				case'\\':
				case'/':
					Map.entityMap[x][y] = new Bouncer(map[x][y]);
					break;
				}
			}
		}
	}

//searches for entities of the type ExtSpike and returns the (pNumber)st ExtSpike
	public Entity searchForExtSpike(int pNumber) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (entityMap[x][y] != null && entityMap[x][y] instanceof ExtSpike) {
					if (pNumber == 0) {
						return entityMap[x][y];
					}
					pNumber--;
				}
			}
		}
		return null;
	}
}
