package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Enemy.Skeleton;
import Entity.Entity;
import Entity.Player;
import World.TileManager;
import obj.mainObject;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable {
	
	public final int defTileSize = 16;
	public final int scale = 3;
	
	public final int tileSize = defTileSize * scale;
	public final int screenCol = 16;
	public final int screenRow = 12;
	
	public final int screenWidth = screenCol * tileSize;
	public final int screenHeight = screenRow * tileSize;
	
	public final int maxWorldCol = 100;
	public final int maxWorldRow = 100;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	int FPS = 60;
	
	public boolean end = false;
	
	KeyHandler key = new KeyHandler();
	Thread gThread;
	TileManager tileM = new TileManager(this);
	public Entity s[] = new Entity[20];
	
	public Collision c = new Collision(this);
	public Player p = new Player(this, key);
	public mainObject obj[] = new mainObject[4];
	public ObjectSet oSet = new ObjectSet(this);
	public GamePanel()
	{
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(key);
		this.setFocusable(true);
	}

	public void setItem()
	{
		p.baseSetting();
		oSet.placeObj();
		oSet.placeEnt();
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
			curTime = System.nanoTime();
			delay += (curTime - lastTime) / frameInt;
			
			lastTime = curTime;
			//System.out.println(lastTime);
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
		for(int i = 0; i < s.length; i++)
		{
			if(s[i] != null)
			{
				if(s[i].health > 10)
				{
					s[i].update();
				}
				else
				{
					s[i] = null;
				}
				
			}
		}
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		//map
		tileM.draw(g2);
		
		//object printing
		for(int i = 0; i < obj.length; i++)
		{
			if(obj[i] != null)
			{
				obj[i].draw(g2, this);
			}
		}
		
		//player
		for(int i = 0; i < s.length; i++)
		{
			if(s[i] != null)
			{
				s[i].draw(g2,this);
			}
		}
		p.draw(g2);

		g2.dispose();
	}

}