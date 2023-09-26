package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	
	final int defTileSize = 16;
	final int scale = 3;
	
	final int tileSize = defTileSize * scale;
	final int screenCol = 16;
	final int screenRow = 12;
	
	final int screenWidth = screenCol * tileSize;
	final int screenHeight = screenRow * tileSize;
	
	final int FPS = 60;
	
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
		
		if(key.up)
		{
			p.y -= p.speed; 
		}
		else if(key.down)
		{
			p.y += p.speed;
		}
		
		if(key.left)
		{
			p.x -= p.speed;
		}
		else if(key.right)
		{
			p.x += p.speed;
		}
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		
		g2.setColor(Color.white);
		
		g2.fillRect(p.x, p.y, tileSize, tileSize);
		g2.dispose();
	}

}
