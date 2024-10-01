package Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.Scale;

public class Entity {

	public int worldx , worldy;
	public int speed;
	public int health = 100;
	
	public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1,right2,right3;
	public String direction;
	
	public int stepCount = 0;
	public int step = 1;
	
	public Rectangle solid;
	public int solidDefX, solidDefY;
	public boolean collOn = false;
	public int count;
	GamePanel gp;
	
	public Entity(GamePanel gp)
	{
		this.gp = gp;
	}
	public void update()
	{
		collOn = true;
		// Calculate distance to player in tiles
		int playerXTile = gp.p.worldx / gp.tileSize;
		int playerYTile = gp.p.worldy / gp.tileSize;
		int entityXTile = worldx / gp.tileSize;
		int entityYTile = worldy / gp.tileSize;
		int distanceX = Math.abs(playerXTile - entityXTile);
		int distanceY = Math.abs(playerYTile - entityYTile);
		
		 // Check if player is within 10 tiles horizontally or vertically
		boolean isPlayerInRangeHorizontally = distanceX <= 6;
		boolean isPlayerInRangeVertically = distanceY <= 6 ;
		
		
		 boolean isInRange = isPlayerInRangeHorizontally && isPlayerInRangeVertically;
		// System.out.println(isInRange);
		 // Determine movement direction
		 if (isInRange) {
		        // Move towards player
		     	if (playerXTile > entityXTile) {
		            direction = "right";
		        } else if (playerXTile < entityXTile) {
		            direction = "left";
		        }

		        if (playerYTile > entityYTile) {
		            direction = "down";
		        } else if (playerYTile < entityYTile) {
		            direction = "up";
		        }
		}
		collOn = false;
		gp.c.checkTile(this);
		gp.c.checkEnt(this, gp.p);
		//System.out.println(collOn);
		if(!collOn)
		{
			switch(direction)
			{
			case "up":
				worldy -= speed;
				break;
			case "down":
				worldy += speed;
				break;
			case "left":
				worldx -= speed;
				break;
			case "right":
				worldx += speed;
				break;
			}
		}
		
		
		stepCount++;
		if(stepCount > 12)
		{
			if(step == 1)
			{
				step = 2;
			}else if(step == 2)
			{
				step = 1;
			}
			stepCount = 0;
		}
	}
	
	public void draw(Graphics2D g2, GamePanel gp)
	{
				
				BufferedImage image = null;
				
				switch(direction)
				{
					case "up":
						if(step == 1)
						{
							image = up1;
						}
						if(step == 2)
						{
							image = up2;
						}
						if(step == 0)
						{
							image = up1;
						}
						break;
					
					case "down":
						if(step == 1)
						{
							image = down2;
						}
						if(step == 2)
						{
							image = down1;
						}
						if(step == 0)
						{
							image = down1;
						}
						break;
					case "left":
						if(step == 1)
						{
							image = left2;
						}
						if(step == 2)
						{
							image = left1;
						}
						if(step == 0)
						{
							image = left1;
						}
						break;
					case "right":
						if(step == 1)
						{
							image = right2;
						}
						if(step == 2)
						{
							image = right1;
						}
						if(step == 0)
						{
							image = right1;
						}
						break;
				}
				

			
				int screenX = worldx - gp.p.worldx + gp.p.screenX;
				int screenY = worldy - gp.p.worldy + gp.p.screenY;
				g2.drawImage(image, screenX , screenY , gp.tileSize, gp.tileSize, null);
				
				g2.fillRect(screenX, screenY, solid.x, solid.y);
	}
	public BufferedImage setup(String path, int width, int height)
	{
		Scale scale = new Scale();
		BufferedImage image = null;
		
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream(path + ".png"));
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		return image;
	}
}
