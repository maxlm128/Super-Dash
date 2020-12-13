package main;

import com.asecave.Console;

public class Launcher {
	static Map map;
	static Screen screen;
	static Console console;
	static Launcher launcher;
	static Player player;
	static KeyListener keyListener;
	static Menu menu;

//creates all classes and saves them on the variables
	public static void main(String Args[]) {
		map = new Map();
		screen = new Screen();
		console = new Console();
		player = new Player();
		keyListener = new KeyListener();
		menu = new Menu();
		launcher = new Launcher();
	}

//executes the methods which create references between the classes and starts the program
	public Launcher() {
		map.newPlayerReference(player);
		screen.newMapReference(map);
		screen.newConsoleReference(console);
		screen.newMenuReference(menu);
		player.newConsoleReference(console);
		player.newScreenReference(screen);
		player.newKeyListenerReference(keyListener);
		player.newMenuReference(menu);
		keyListener.newConsoleReference(console);
		keyListener.newPlayerReference(player);
		keyListener.newMenuReference(menu);
		keyListener.newScreenReference(screen);
		menu.newConsoleReference(console);
		menu.newScreenReference(screen);
		menu.newKeyListenerReference(keyListener);
		menu.newPlayerReference(player);
		menu.newMapReference(map);
		menu.startMenu();
		menu.moveCursor('w');
		keyListener.gameLoop();
	}
}
