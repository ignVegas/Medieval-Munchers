package Entity;

import java.awt.image.BufferedImage;

public class Entity {

	public int x,y;
	public int speed;
	public int health;
	
	public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1,right2,right3;
	public String direction;
	
	public int stepCount = 0;
	public int step = 1;
}
