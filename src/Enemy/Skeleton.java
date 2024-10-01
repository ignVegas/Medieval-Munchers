package Enemy;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Entity.Entity;
import main.GamePanel;

public class Skeleton extends Entity {
	
	GamePanel gp;
	public int count = 1;
	public Skeleton(GamePanel gp)
	{
		super(gp);

		solid = new Rectangle(0,0, gp.tileSize,gp.tileSize);
		solidDefX = solid.x;
		solidDefY = solid.y;
		
		getSkeleImage();

	}
	
	public void getSkeleImage()
	{
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/monster/sBStep1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/monster/sBStep2.png"));
			
			down1 = ImageIO.read(getClass().getResourceAsStream("/monster/sFStep1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/monster/sFStep2.png"));
			
			left1 = ImageIO.read(getClass().getResourceAsStream("/monster/sLStep1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/monster/sLStep2.png"));
			
			right1 = ImageIO.read(getClass().getResourceAsStream("/monster/sRStep1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/monster/sRStep2.png"));
			
		}catch(IOException e)
		{
			//System.out.println("image");
			e.printStackTrace();
		}
	}
	

	
	
}
