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
		
		gp.s[1] = new Skeleton(this.gp);
		gp.s[1].speed = 1;
		gp.s[1].direction = "down";
		gp.s[1].worldx = gp.tileSize * 50;
		gp.s[1].worldy = gp.tileSize * 71;
		
		gp.s[2] = new Skeleton(this.gp);
		gp.s[2].speed = 1;
		gp.s[2].direction = "down";
		gp.s[2].worldx = gp.tileSize * 75;
		gp.s[2].worldy = gp.tileSize * 71;
		
		gp.s[3] = new Skeleton(this.gp);
		gp.s[3].speed = 1;
		gp.s[3].direction = "down";
		gp.s[3].worldx = gp.tileSize * 15;
		gp.s[3].worldy = gp.tileSize * 85;
		
		gp.s[4] = new Skeleton(this.gp);
		gp.s[4].speed = 1;
		gp.s[4].direction = "down";
		gp.s[4].worldx = gp.tileSize * 19;
		gp.s[4].worldy = gp.tileSize * 71;
		
		gp.s[5] = new Skeleton(this.gp);
		gp.s[5].speed = 1;
		gp.s[5].direction = "down";
		gp.s[5].worldx = gp.tileSize * 28;
		gp.s[5].worldy = gp.tileSize * 24;
		
		gp.s[6] = new Skeleton(this.gp);
		gp.s[6].speed = 1;
		gp.s[6].direction = "down";
		gp.s[6].worldx = gp.tileSize * 31;
		gp.s[6].worldy = gp.tileSize * 26;
		
		gp.s[7] = new Skeleton(this.gp);
		gp.s[7].speed = 1;
		gp.s[7].direction = "down";
		gp.s[7].worldx = gp.tileSize * 20;
		gp.s[7].worldy = gp.tileSize * 24;
		
	}
}
