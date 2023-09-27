package World;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gp;
	Tile[] tile;
	int mapTileNum[][];
	
	public TileManager(GamePanel gp)
	{
		this.gp = gp;
		
		tile = new Tile[10];
		mapTileNum = new int[gp.screenCol][gp.screenRow];
		
		getTileImage();
		load();
	}
	
	public void getTileImage()
	{
		try {
			
			tile[0] = new Tile();
			tile[0].im = ImageIO.read(getClass().getResourceAsStream("/art/CobbleWall.png"));
			
			tile[1] = new Tile();
			tile[1].im = ImageIO.read(getClass().getResourceAsStream("/art/CobbleWallRight.png"));
			
			tile[2] = new Tile();
			tile[2].im = ImageIO.read(getClass().getResourceAsStream("/art/Grass.png"));
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void load()
	{
		try {
			InputStream is = getClass().getResourceAsStream("/map/map.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			while(col < gp.screenCol && row < gp.screenRow)
			{
				String line = br.readLine();	
				while(col < gp.screenCol)
				{
					String num[] = line.split(" ");
					
					int nums = Integer.parseInt(num[col]);
					
					mapTileNum[col][row] = nums;
					col++;
				}
				if(col == gp.screenCol)
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
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < gp.screenCol && row < gp.screenRow)
		{
			
			int tileNum = mapTileNum[col][row];
			g2.drawImage(tile[tileNum].im, x , y , gp.tileSize, gp.tileSize, null);
			col++;
			x += gp.tileSize;
			
			if(col == gp.screenCol)
			{
				col = 0;
				x = 0;
				row++;
				y += gp.tileSize;
			}
		}
	}
}
