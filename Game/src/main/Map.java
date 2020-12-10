package main;

import com.asecave.Console;

class Map {
	int width;
	int height;
	static char[][] map;
	static int[][] trailMap;
	Console console;
	Screen screen;
	Player player;

// creates the new array and saves the borders of the map into the new array
	public void newMapArray() {
		map = MapLoader.charsgetMap();
		width = map.length;
		height = map[0].length;
		trailMap = new int[width][height];
		map[player.coordsXPlayer][player.coordsYPlayer] = '<';
	}

//new reference to the class Player
	public void newPlayerReference(Player pPlayer) {
		player = pPlayer;
	}
}