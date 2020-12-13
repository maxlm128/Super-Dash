package main;

import com.asecave.Console;

class Map {
	String currentMap;
	int width;
	int height;
	static char[][] map;
	static int[][] trailMap;
	Console console;
	Player player;

// creates the new array and saves the borders of the map into the new array
	public void newMapArray(String pFile,boolean pReload) {
		MapLoader.loadAll(pFile);
		map = MapLoader.charsgetMap();
		width = map.length;
		height = map[0].length;
		trailMap = new int[width][height];
		map[player.coordsXPlayer][player.coordsYPlayer] = '<';
		currentMap = pFile;
	}

//new reference to the class Player
	public void newPlayerReference(Player pPlayer) {
		player = pPlayer;
	}
}
