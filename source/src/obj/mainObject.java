package obj;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class mainObject {
	
	public BufferedImage im;
	public String name;
	public boolean touch = false;
	public int worldX, worldY;
	public Rectangle sol = new Rectangle(0,0,48,48);
	public int solX = 0;
	public int solY = 0;

	public void draw(Graphics2D g2, GamePanel gp)
	{
		int screenX = worldX - gp.p.worldx + gp.p.screenX;
		int screenY = worldY - gp.p.worldy + gp.p.screenY;	
		
			g2.drawImage(im, screenX , screenY , gp.tileSize, gp.tileSize, null);
		
	}
}
