package Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.GamePanel;

public class Entity {

	public int worldx , worldy;
	public int speed;
	public int health;
	
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
		
		count++;
		//System.out.println(count);
		gp.c.checkEnt(this, gp.p);
		//System.out.println(gp.s[0].collOn);
		if (count == 60) {
			  Random r = new Random();
			  int i = r.nextInt(100) + 1; // 1-100
			  count = 0;
			  // Check if i is between 1-25
			  if (i >= 1 && i <= 25) {
			    direction = "up";
			    
			  }

			  // Check if i is between 25-50
			  else if (i >= 26 && i <= 50) {
				  direction = "down";
			  }

			  // Check if i is between 50-75
			  else if (i >= 51 && i <= 75) {
				  direction = "left";
			  }

			  // Check if i is between 75-100
			  else {
				  direction = "right";
			  }
		}
		collOn = false;
		gp.c.checkTile(this);
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
}
