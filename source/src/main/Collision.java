package main;

import Entity.Entity;

public class Collision {
	
	GamePanel gp;
	
	public Collision(GamePanel gp)
	{
		this.gp = gp;
	}
	
	public void checkTile(Entity ent)
	{
		int entLeft = ent.worldx + ent.solid.x;
		int entRight = ent.worldx + ent.solid.x + ent.solid.width;
		
		int entTop = ent.worldy + ent.solid.y;
		int entBottom = ent.worldy + ent.solid.y + ent.solid.height;
		
		int entLeftCol = entLeft/gp.tileSize;
		int entRightCol = entRight/gp.tileSize;
		
		int entTopRow = entTop/gp.tileSize;
		int entBotRow = entBottom/gp.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(ent.direction)
		{
		case "up":
			entTopRow = (entTop - ent.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entLeftCol][entTopRow];
			tileNum2 = gp.tileM.mapTileNum[entRightCol][entTopRow];
			if(gp.tileM.tile[tileNum1].coll || gp.tileM.tile[tileNum2].coll)
			{
				ent.collOn = true;
			}
			break;
		case "down":
			entBotRow = (entBottom + ent.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entLeftCol][entBotRow];
			tileNum2 = gp.tileM.mapTileNum[entRightCol][entBotRow];
			if(gp.tileM.tile[tileNum1].coll || gp.tileM.tile[tileNum2].coll)
			{
				ent.collOn = true;
			}
			break;
		case "left":
			entLeftCol = (entLeft - ent.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entLeftCol][entTopRow];
			tileNum2 = gp.tileM.mapTileNum[entLeftCol][entTopRow];
			if(gp.tileM.tile[tileNum1].coll || gp.tileM.tile[tileNum2].coll)
			{
				ent.collOn = true;
			}
			break;
		case "right":
			entRightCol = (entRight + ent.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entRightCol][entTopRow];
			tileNum2 = gp.tileM.mapTileNum[entRightCol][entTopRow];
			if(gp.tileM.tile[tileNum1].coll || gp.tileM.tile[tileNum2].coll)
			{
				ent.collOn = true;
			}
			break;
		}
	}
	
	public int checkEnt(Entity ent, Entity ent2)
		{
			int index = 69;
			
			for(int i = 0; i < 3; i++)
			{
				if(ent2 != null)
				{
					//hitbox coll pos
					ent.solid.x = ent.worldx + ent.solid.x;
					ent.solid.y = ent.worldy + ent.solid.y;
					
					//item solid
					ent2.solid.x = ent2.worldx + ent2.solidDefX;
					ent2.solid.y = ent2.worldy + ent2.solidDefY;
					
					
					switch(ent.direction) {
					case "up":
						ent.solid.y -= ent.speed;
						if(ent.solid.intersects(ent2.solid))
						{
								ent.collOn = true;
								ent2.collOn = true;
								index = 1;

						}
						break;
					case "down":
						ent.solid.y += ent.speed;
						if(ent.solid.intersects(ent2.solid))
						{
								ent.collOn = true;
								ent2.collOn = true;
								index = 1;

						}
						break;
					case "left":
						ent.solid.x -= ent.speed;
						if(ent.solid.intersects(ent2.solid))
						{
								ent.collOn = true;
								ent2.collOn = true;
								index = 1;

						}
						break;
					case "right":
						ent.solid.x += ent.speed;
						if(ent.solid.intersects(ent2.solid))
						{
						
								ent.collOn = true;
								ent2.collOn = true;
								index = 1;
						}
						break;
		
					}
					ent.solid.x = ent.solidDefX;
					ent.solid.y = ent.solidDefY;
					ent2.solid.x = ent2.solidDefX;
					ent2.solid.y = ent2.solidDefY;
				}
			}
		
			
			return index;
		}
	
	public int checkObj(Entity ent, boolean player)
	{
		int index = 69;
		
		for(int i = 0; i < gp.obj.length; i++)
		{
			if(gp.obj[i] != null)
			{
				//hitbox coll pos
				ent.solid.x = ent.worldx + ent.solid.x;
				ent.solid.y = ent.worldy + ent.solid.y;
				
				//item solid
				gp.obj[i].sol.x = gp.obj[i].worldX + gp.obj[i].solX;
				gp.obj[i].sol.y = gp.obj[i].worldY + gp.obj[i].solY;
				
				
				switch(ent.direction) {
				case "up":
					ent.solid.y -= ent.speed;
					if(ent.solid.intersects(gp.obj[i].sol))
					{
						if(gp.obj[i].touch)
						{
							ent.collOn = true;
						}
						if(player)
						{
							index = 0;
						}
					}
					break;
				case "down":
					ent.solid.y += ent.speed;
					if(ent.solid.intersects(gp.obj[i].sol))
					{
						if(gp.obj[i].touch)
						{
							ent.collOn = true;
						}
						if(player)
						{
							index = 0;
						}
					}
					break;
				case "left":
					ent.solid.x -= ent.speed;
					if(ent.solid.intersects(gp.obj[i].sol))
					{
						if(gp.obj[i].touch)
						{
							ent.collOn = true;
						}
						if(player)
						{
							index = 0;
						}
					}
					break;
				case "right":
					ent.solid.x += ent.speed;
					if(ent.solid.intersects(gp.obj[i].sol))
					{
						if(gp.obj[i].touch)
						{
							ent.collOn = true;
						}
						if(player)
						{
							index = 0;
						}
					}
					break;
	
				}
				ent.solid.x = ent.solidDefX;
				ent.solid.y = ent.solidDefY;
				gp.obj[i].sol.x = gp.obj[i].solX;
				gp.obj[i].sol.y = gp.obj[i].solY;
			}
		}
	
		
		return index;
	}
}
