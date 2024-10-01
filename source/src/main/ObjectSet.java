package main;

import Enemy.Skeleton;
import obj.Key_Ice;

public class ObjectSet {

	GamePanel gp;
	
	public ObjectSet(GamePanel gp)
	{
		this.gp = gp;
	}
	
	public void placeObj()
	{
		gp.obj[0] = new Key_Ice();
		gp.obj[0].worldX = 16 * gp.tileSize;
		gp.obj[0].worldY = 71 * gp.tileSize;
	}
	
	public void placeEnt()
	{
		gp.s[0] = new Skeleton(this.gp);
		gp.s[0].speed = 1;
		gp.s[0].direction = "down";
		gp.s[0].worldx = gp.tileSize * 34;
		gp.s[0].worldy = gp.tileSize * 71;
		
		gp.s[0] = new Skeleton(this.gp);
		gp.s[0].speed = 1;
		gp.s[0].direction = "down";
		gp.s[0].worldx = gp.tileSize * 50;
		gp.s[0].worldy = gp.tileSize * 71;
		
		gp.s[0] = new Skeleton(this.gp);
		gp.s[0].speed = 1;
		gp.s[0].direction = "down";
		gp.s[0].worldx = gp.tileSize * 76;
		gp.s[0].worldy = gp.tileSize * 71;
		
		gp.s[1] = new Skeleton(this.gp);
		gp.s[1].speed = 1;
		gp.s[1].direction = "down";
		gp.s[1].worldx = gp.tileSize * 16;
		gp.s[1].worldy = gp.tileSize * 71;
		
		gp.s[1] = new Skeleton(this.gp);
		gp.s[1].speed = 1;
		gp.s[1].direction = "down";
		gp.s[1].worldx = gp.tileSize * 19;
		gp.s[1].worldy = gp.tileSize * 71;
		
	}
}
