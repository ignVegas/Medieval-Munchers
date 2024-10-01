package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler key;
	
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	
	
	public Player(GamePanel gp, KeyHandler key)
	{
		super(gp);
		this.gp = gp;
		this.key = key;
		
		screenX = gp.screenWidth/2 - gp.tileSize/2;
		screenY = gp.screenHeight/2 - gp.tileSize/2;
		
		solid = new Rectangle(16,28, 24,28);
		solidDefX = solid.x;
		solidDefY = solid.y;
		
		getPlayerImage();
	}
	public void baseSetting()
	{
		worldx = 50 * gp.tileSize;
		worldy = 97 * gp.tileSize;
		speed = 3;
		health = 100;
		direction = "down";
	}
	
	public void getPlayerImage()
	{
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/BackwardFace.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/BackwardFaceStep1.png"));
			up3 = ImageIO.read(getClass().getResourceAsStream("/player/BackwardFaceStep2.png"));
			
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/ForwardStanding.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/ForwardStandingStep1.png"));
			down3 = ImageIO.read(getClass().getResourceAsStream("/player/ForwardStandingStep2.png"));
			
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/LeftFaceStanding.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/LeftFaceStep1.png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/player/LeftFaceStep2.png"));
			
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/RightFaceStanding.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/RightFaceStep1.png"));
			right3 = ImageIO.read(getClass().getResourceAsStream("/player/RightFaceStep2.png"));
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void update()
	{
		collOn = false;
		
		if(key.up || key.down || key.left || key.right)
		{
			if(key.up)
			{				
				direction = "up";
							
			}
			else if(key.down)
			{
				direction = "down";
							
			}
			
			if(key.left)
			{
				direction = "left";	

			}
			else if(key.right)
			{
				direction = "right";

			}
			
			int entIndex = gp.c.checkEnt(this, gp.s[0]);
			gp.c.checkTile(this);
			int objIndex = gp.c.checkObj(this, true);
			pickUp(objIndex);
			damage(entIndex);
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
		
	}
	
	int hdelay = 0;
	public void damage(int i)
	{
		if(i != 69)
		{
			hdelay++;
			while(hdelay > 20)
			{
				gp.p.health -= 15;
				gp.p.collOn = false;
				hdelay = 0;
			}
			
		}
	}
	
	public void pickUp(int i)
	{
		if(i != 69)
		{
			String objName = gp.obj[i].name;
			
			switch(objName)
			{
				case "Key_Ice":
					System.out.println("Ice Key!");
					gp.obj[i] = null;
					
					break;
				case "Fire_Key":
					break;
				case "Acid_Key":
					break;
			
			}
				
				
		}
	}
	
	public void draw(Graphics2D g2)
	{
			
			BufferedImage image = null;
			
			switch(direction)
			{
				case "up":
					if(step == 1)
					{
						image = up2;
					}
					if(step == 2)
					{
						image = up3;
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
						image = down3;
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
						image = left3;
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
						image = right3;
					}
					if(step == 0)
					{
						image = right1;
					}
					break;
			}
			BufferedImage sc = new BufferedImage(gp.tileSize,gp.tileSize, image.getType());	
			g2.drawImage(image , screenX , screenY , gp.tileSize , gp.tileSize , null);
			//g2.drawRect(screenX, screenY,gp.p.solid.width, gp.p.solid.height);
			g2.drawRect(10, 10, 100, 30);
			g2.setColor(Color.red);
			g2.fillRect(11, 11, health - 1, 29);
			
			g2.drawString(Integer.toString(health), 115, 30);
			g2.dispose();

			if(gp.end)
			{
				if(health <= 1)
				{
					health = 0;

				}
				g2.drawString("GAME OVER", (gp.screenWidth / 2) - 30, gp.screenHeight / 2);
				speed = 0;
			}
	}
	
}
