package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler key;
	
	public Player(GamePanel gp, KeyHandler key)
	{
		this.gp = gp;
		this.key = key;
		getPlayerImage();
	}
	public void baseSetting()
	{
		x = 100;
		y = 100;
		speed = 3;
		health = 100;
		direction = "down";
	}
	
	public void getPlayerImage()
	{
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/art/BackwardFace.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/art/BackwardFaceStep1.png"));
			up3 = ImageIO.read(getClass().getResourceAsStream("/art/BackwardFaceStep2.png"));
			
			down1 = ImageIO.read(getClass().getResourceAsStream("/art/ForwardStanding.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/art/ForwardStandingStep1.png"));
			down3 = ImageIO.read(getClass().getResourceAsStream("/art/ForwardStandingStep2.png"));
			
			left1 = ImageIO.read(getClass().getResourceAsStream("/art/LeftFaceStanding.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/art/LeftFaceStep1.png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/art/LeftFaceStep2.png"));
			
			right1 = ImageIO.read(getClass().getResourceAsStream("/art/RightFaceStanding.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/art/RightFaceStep1.png"));
			right3 = ImageIO.read(getClass().getResourceAsStream("/art/RightFaceStep2.png"));
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void update()
	{
		
		if(key.up || key.down || key.left || key.right)
		{
			if(key.up)
			{
				
				direction = "up";
				y -= speed;
				
			}
			else if(key.down)
			{
				direction = "down";
				y += speed;
				
			}
			
			if(key.left)
			{
				direction = "left";
				x -= speed;

			}
			else if(key.right)
			{
				direction = "right";
				x += speed;

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
		else if(key.up && key.down && key.left && !key.right)
		{
			step = 0;
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
			g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
			//g2.drawRect(10, 10, 100, 30);
			//g2.setColor(Color.red);
			//g2.fillRect(11, 11, health - 1, 29);
			
			g2.drawString(Integer.toString(health), 115, 30);

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
