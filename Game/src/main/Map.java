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

	// new reference to the class Player
	public void newPlayerReference(Player pPlayer) {
		player = pPlayer;
	}

	// new reference to the class Screen
	public void newScreenReference(Screen pScreen) {
		screen = pScreen;
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
				if (map[x][y] == 'p') {
					player.coordsXPlayer = x;
					player.coordsYPlayer = y;
					map[x][y] = '<';
				}
				if (map[x][y] == '<') {
					entityMap[x][y] = new ExtSpike('a', screen);
				}
				if (map[x][y] == '^') {
					entityMap[x][y] = new ExtSpike('w', screen);
				}
				if (map[x][y] == '>') {
					entityMap[x][y] = new ExtSpike('d', screen);
				}
				if (map[x][y] == 'v') {
					entityMap[x][y] = new ExtSpike('s', screen);
				}
			}
		}
	}

	public Entity searchForEntity(int pNumber) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if(entityMap[x][y] == null) {
					pNumber--;
					if(pNumber == 0) {
					return entityMap[x][y];
					}
				}
			}
		}
		return null;
	}
}
