package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable {
	
	final int defTileSize = 16;
	final int scale = 3;
	
	final int tileSize = defTileSize * scale;
	final int screenCol = 16;
	final int screenRow = 12;
	
	final int screenWidth = screenCol * tileSize;
	final int screenHeight = screenRow * tileSize;
	
	int FPS = 60;
	
	boolean end = false;
	
	KeyHandler key = new KeyHandler();
	Thread gThread;
	
	Player p = new Player();
	
	public GamePanel()
	{
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(key);
		this.setFocusable(true);
	}

	public void gameThread()
	{
		gThread = new Thread(this);
		gThread.start();
	}
	
	@Override
	public void run()
	{
		
		double frameInt = 1000000000/FPS;
		double delay = 0;
		long lastTime = System.nanoTime();
		long curTime;
		
		while(gThread != null)
		{
			//System.out.println("GameLoop");
			curTime = System.nanoTime();
			delay += (curTime - lastTime) / frameInt;
			
			lastTime = curTime;
			
			if(delay >= 1)
			{
				//tick to update
				tick();
				
				//display new info
				repaint();
				
				delay--;
			}
			
			
		
		}
		
	}
	
	public void tick()
	{
		base();
		movement();
		
	}
	
	public void base()
	{
		if(p.health <= 0)
		{
			end = true;
		}
	}
	
	public void movement()
	{
		if(key.up)
		{
			p.y -= p.speed;
			if(p.y < 0)
			{
				p.y = 0;
			}
		}
		else if(key.down)
		{
			p.y += p.speed;
			if((p.y + tileSize) > screenHeight)
			{
				p.y = screenHeight - tileSize;
			}
		}
		
		if(key.left)
		{
			p.x -= p.speed;
			if(p.x < 0)
			{
				p.x = 0;
			}
		}
		else if(key.right)
		{
			p.x += p.speed;
			p.health -= 1;
			if((p.x + tileSize) > screenWidth)
			{
				p.x = screenWidth - tileSize;
			}
		}
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		
		g2.setColor(Color.white);
		
	//	g2.fillOval(p.x, p.y, tileSize, tileSize);
		g2.fillRect(p.x, p.y, tileSize, tileSize);
		
		g2.drawRect(10, 10, 100, 30);
		g2.setColor(Color.red);
		g2.fillRect(11, 11, p.health - 1, 29);
		
		if(end)
		{
			g2.drawString("GAME OVER", (screenWidth / 2) - 30, screenHeight / 2);
			p.speed = 0;
		}
		
		g2.dispose();
	}

}
