package main;

public class ExtSpike extends Entity {

	Screen screen;
	int length;
	int coordsXSpikeHead;
	int coordsYSpikeHead;
	boolean isExtending;
	boolean canDestroy;
	int extentionTime;
	int spikeTimer;

	public ExtSpike(char pRotation, Screen pScreen) {
		rotation = pRotation;
		screen = pScreen;
		color = 1;
	}

	// checks for every case if something is in front of the spike
	private boolean isSomethingInFront(char pContent) {
		switch (rotation) {
		case 'd':
			return Map.map[coordsXSpikeHead + 1][coordsYSpikeHead] == pContent;
		case 's':
			return Map.map[coordsXSpikeHead][coordsYSpikeHead + 1] == pContent;
		case 'a':
			return Map.map[coordsXSpikeHead - 1][coordsYSpikeHead] == pContent;
		case 'w':
			return Map.map[coordsXSpikeHead][coordsYSpikeHead - 1] == pContent;
		}
		return true;
	}
	
	public void spikeTimer() {
		if (isExtending && !isSomethingInFront('#')) {
			extendSpike();
		} else if (isExtending && spikeTimer < extentionTime) {
			spikeTimer++;
		} else if (isExtending) {
			isExtending = false;
			spikeTimer = 0;
		}
		if (!isExtending && coordsXSpikeHead != coordsXEntity && coordsYSpikeHead != coordsYEntity) {
			retractSpike();
		} else if (!isExtending && spikeTimer < extentionTime) {
			spikeTimer++;
		} else if (!isExtending) {
			isExtending = true;
			spikeTimer = 0;
		}
	}

	private void extendSpike() {
		if (rotation == 'd') {
			screen.print(coordsXSpikeHead, coordsYSpikeHead, '-');
			coordsXSpikeHead++;
			screen.print(coordsXSpikeHead, coordsYSpikeHead, '>');
		} else if (rotation == 's') {
			screen.print(coordsXSpikeHead, coordsYSpikeHead, '|');
			coordsYSpikeHead++;
			screen.print(coordsXSpikeHead, coordsYSpikeHead, 'v');
		} else if (rotation == 'a') {
			screen.print(coordsXSpikeHead, coordsYSpikeHead, '-');
			coordsXSpikeHead--;
			screen.print(coordsXSpikeHead, coordsYSpikeHead, '<');
		} else if (rotation == 'w') {
			screen.print(coordsXSpikeHead, coordsYSpikeHead, '-');
			coordsYSpikeHead--;
			screen.print(coordsXSpikeHead, coordsYSpikeHead, '^');
		}
	}

	private void retractSpike() {
		if (rotation == 'd') {
			screen.print(coordsXSpikeHead, coordsYSpikeHead, ' ');
			coordsXSpikeHead--;
			screen.print(coordsXSpikeHead, coordsYSpikeHead, '>');
		} else if (rotation == 's') {
			screen.print(coordsXSpikeHead, coordsYSpikeHead, ' ');
			coordsYSpikeHead--;
			screen.print(coordsXSpikeHead, coordsYSpikeHead, 'v');
		} else if (rotation == 'a') {
			screen.print(coordsXSpikeHead, coordsYSpikeHead, ' ');
			coordsXSpikeHead++;
			screen.print(coordsXSpikeHead, coordsYSpikeHead, '<');
		} else if (rotation == 'w') {
			screen.print(coordsXSpikeHead, coordsYSpikeHead, ' ');
			coordsYSpikeHead++;
			screen.print(coordsXSpikeHead, coordsYSpikeHead, '^');
		}
	}
}
