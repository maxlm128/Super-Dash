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
				if (map[x][y] == '<') {
					extSpike = new ExtSpike('a', screen, menu);
					entityMap[x][y] = extSpike;
					extSpike.coordsXEntity = x;
					extSpike.coordsYEntity = y;
					extSpike.inizializeCoordsSpikeHead();
				} else if (map[x][y] == '^') {
					extSpike = new ExtSpike('w', screen, menu);
					entityMap[x][y] = extSpike;
					extSpike.coordsXEntity = x;
					extSpike.coordsYEntity = y;
					extSpike.inizializeCoordsSpikeHead();
				} else if (map[x][y] == '>') {
					extSpike = new ExtSpike('d', screen, menu);
					entityMap[x][y] = extSpike;
					extSpike.coordsXEntity = x;
					extSpike.coordsYEntity = y;
					extSpike.inizializeCoordsSpikeHead();
				} else if (map[x][y] == 'v') {
					extSpike = new ExtSpike('s', screen, menu);
					entityMap[x][y] = extSpike;
					extSpike.coordsXEntity = x;
					extSpike.coordsYEntity = y;
					extSpike.inizializeCoordsSpikeHead();
				}
				if (map[x][y] == 'p') {
					player.coordsXEntity = x;
					player.coordsYEntity = y;
					map[x][y] = '<';
				}
			}
		}
	}

	public Entity searchForEntity(int pNumber) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (entityMap[x][y] != null) {
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
