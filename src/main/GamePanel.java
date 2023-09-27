package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Entity.Player;
import World.TileManager;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable {
	
	public final int defTileSize = 16;
	public final int scale = 3;
	
	public final int tileSize = defTileSize * scale;
	public final int screenCol = 16;
	public final int screenRow = 12;
	
	public final int screenWidth = screenCol * tileSize;
	public final int screenHeight = screenRow * tileSize;
	
	int FPS = 60;
	
	public boolean end = false;
	
	KeyHandler key = new KeyHandler();
	Thread gThread;
	
	Player p = new Player(this, key);
	TileManager tileM = new TileManager(this);
	
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
		
		p.baseSetting();
		
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
		load();
		
	}
	
	public void base()
	{
		if(p.health <= 0)
		{
			end = true;
		}
	}
	
	public void load()
	{
		p.update();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		tileM.draw(g2);
		p.draw(g2);
		
		g2.dispose();
	}

}