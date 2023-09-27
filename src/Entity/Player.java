package Entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler key;
	
	public Player(GamePanel gp, KeyHandler key)
	{
		this.gp = gp;
		this.key = key;
	}
	public void baseSetting()
	{
		x = 100;
		y = 100;
		speed = 3;
		health = 100;
	}
	public void update()
	{
		if(key.up)
		{
			y = speed;
			if(y < 0)
			{
				y = 0;
			}
		}
		else if(key.down)
		{
			y += speed;
			if((y + gp.tileSize) > gp.screenHeight)
			{
				y = gp.screenHeight - gp.tileSize;
			}
		}
		
		if(key.left)
		{
			x -= speed;
			if(x < 0)
			{
				x = 0;
			}
		}
		else if(key.right)
		{
			x += speed;
			//health -= 1; health testing
			if((x + gp.tileSize) > gp.screenWidth)
			{
				x = gp.screenWidth - gp.tileSize;
			}
		}
	}
	public void draw(Graphics2D g2)
	{
			g2.setColor(Color.white);
		
		//	g2.fillOval(p.x, p.y, tileSize, tileSize);
			g2.drawRect(x, y, gp.tileSize, gp.tileSize);
			
			g2.drawRect(10, 10, 100, 30);
			g2.setColor(Color.red);
			g2.fillRect(11, 11, health - 1, 29);
			
			if(gp.end)
			{
				g2.drawString("GAME OVER", (gp.screenWidth / 2) - 30, gp.screenHeight / 2);
				speed = 0;
			}
	}
	
}
