package main;

public class ExtSpikeNeck extends Entity{
	public ExtSpikeNeck(int pColor,char rotation) {
		color = pColor;
		switch(rotation) {
		case'd':
		case'a':
			content = '-';
			break;
		case'w':
		case's':
			content = '|';
		}
	}
}
