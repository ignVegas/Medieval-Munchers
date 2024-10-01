package World;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.Scale;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp)
	{
		this.gp = gp;
		
		tile = new Tile[99];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		load();
	}
	
	public void getTileImage()
	{
		display(0,"00", true); // black cube
		display(1,"01", false); // grass
		display(2,"02", false); // cobble tl
		display(3,"03", false); // cobble tr
		display(4,"04", false); // cobble br
		display(5,"05", false); // cobble bl
		display(6,"06", false); // cobble
		display(7,"07", false); // cobble left
		display(8,"08", false); // cobble top
		display(9,"09", false); // cobble bot
		display(10,"10", false); // cobble right
		display(11,"11", false); // icefloor 1
		display(12,"12", false); // icefloor 1
		display(13,"13", false); // icefloor 1
		display(14,"14", false); // icefloor 1
		display(15,"15", false); // icefloor 1
		display(16,"16", false); // icefloor 1
		display(17,"17", false); // icefloor 1
		display(18,"18", false); // icefloor 1
		display(19,"19", false); // icefloor 1
		display(20,"20", false); // floor 2
		display(21,"21", false); // floor 1
		display(22,"22", true); // castle wall
		display(23,"23", false); // banner
		display(24,"24", true); // chest
		display(25,"25", true); // barrel
		display(26,"26", false); // door
		display(27,"27", true); // ice brick
		display(28,"28", false); // ice floor 2
		display(29,"29", false); // ice floor 
		
		
	}
	
	public void display(int i, String path, boolean collision)
	{
		Scale s = new Scale();
		
		try {
			tile[i] = new Tile();
			tile[i].im = ImageIO.read(getClass().getResourceAsStream("/art/" + path + ".png"));
			tile[i].im = s.scaled(tile[i].im, gp.tileSize, gp.tileSize);
			tile[i].coll = collision;
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void load()
	{
		try {
			InputStream is = getClass().getResourceAsStream("/map/world.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			while(col < gp.maxWorldCol && row < gp.maxWorldRow)
			{
				String line = br.readLine();	
				while(col < gp.maxWorldCol)
				{
					String num[] = line.split("\\s+");
					
					int nums = Integer.parseInt(num[col]);
					
					mapTileNum[col][row] = nums;
					col++;
				}
				if(col == gp.maxWorldCol)
				{
					col = 0;
					row++;
				}
			}
			br.close();
			
		}catch(Exception e)
		{
			
		}
	}
	
	public void draw(Graphics2D g2)
	{
		int worldCol = 0;
		int worldRow = 0;

		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow)
		{
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.p.worldx + gp.p.screenX;
			int screenY = worldY - gp.p.worldy + gp.p.screenY;
			
			if(worldX + gp.tileSize > gp.p.worldx - gp.p.screenX && 
					worldX - gp.tileSize < gp.p.worldx + gp.p.screenX &&
					worldY + gp.tileSize > gp.p.worldy - gp.p.screenY && 
					worldY - gp.tileSize < gp.p.worldy + gp.p.screenY)
			{
				g2.drawImage(tile[tileNum].im, screenX , screenY, null);
			}
			
			worldCol++;
			
			
			if(worldCol == gp.maxWorldCol)
			{
				worldCol = 0;
				worldRow++;
			}
			
		}
	}
}
