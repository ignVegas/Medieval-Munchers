package Entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler key;
	BufferedImage keyI;
	BufferedImage healthPack;
	Font customFont;
	
	public final int screenX;
	public final int screenY;
	public boolean isAttacking = false;
	public int hasKey = 0;
	public int sizeX = 1;
	public int sizeY = 1;
	public String previousDirection = null;
	public int kCollected = 0;
	public Boolean playerIsHit = false;
	public int itemDrop = 0;
	public boolean item = false;
	public boolean popUp = false;
	final int ATTACK_SIZE_X_MULTIPLIER = 2;
	final int ATTACK_SIZE_Y_MULTIPLIER = 2;
	int attackSizeX;
	int attackSizeY;
	
	int healthNum = 0;
	
	public Player(GamePanel gp, KeyHandler key)
	{
		super(gp);
		this.gp = gp;
		this.key = key;
		
		screenX = gp.screenWidth/2 - gp.tileSize/2;
		screenY = gp.screenHeight/2 - gp.tileSize/2;
		
		solid = new Rectangle(16,32, 16,16);
		solidDefX = solid.x;
		solidDefY = solid.y;
		
		try {
		    //create the font to use. Specify the size!
		    customFont = Font.createFont(Font.TRUETYPE_FONT, new File("Art/font/red.ttf")).deriveFont(16f);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    //register the font
		    ge.registerFont(customFont);
		} catch (IOException e) {
		    e.printStackTrace();
		} catch(FontFormatException e) {
		    e.printStackTrace();
		}
		

		int attackSizeX = solid.width * ATTACK_SIZE_X_MULTIPLIER;
		int attackSizeY = solid.height * ATTACK_SIZE_Y_MULTIPLIER;
		
		getPlayerImage();
		
	}
	public void baseSetting()
	{
		worldx = 50 * gp.tileSize;
		worldy = 97 * gp.tileSize;
		speed = 3;
		health = 100;
		direction = "down";
		keyI = setup("/item/IceKey", gp.tileSize, gp.tileSize);
		healthPack = setup("/item/HealthPack", gp.tileSize, gp.tileSize);
	}
	
	public void getPlayerImage()
	{
		up1 = setup("/player/attackUp", gp.tileSize, gp.tileSize * 2);
		up2 = setup("/player/BackwardFaceStep1", gp.tileSize * 2, gp.tileSize);
		up3 = setup("/player/BackwardFaceStep2",gp.tileSize, gp.tileSize);
		
		down1 = setup("/player/attackDown",gp.tileSize, gp.tileSize * 2);
		down2 = setup("/player/ForwardStandingStep1",gp.tileSize, gp.tileSize);
		down3 = setup("/player/ForwardStandingStep2",gp.tileSize, gp.tileSize);
		
		left1 = setup("/player/attackLeft",gp.tileSize * 2, gp.tileSize);
		left2 = setup("/player/LeftFaceStep1",gp.tileSize, gp.tileSize);
		left3 = setup("/player/LeftFaceStep2",gp.tileSize, gp.tileSize);
		
		right1 = setup("/player/attackRight",gp.tileSize, gp.tileSize);
		right2 = setup("/player/RightFaceStep1",gp.tileSize, gp.tileSize);
		right3 = setup("/player/RightFaceStep2",gp.tileSize, gp.tileSize);
	}
	
	public void update()
	{
		collOn = false;
		isAttacking = key.attack;
		Random random = new Random();
		
		{
			if(key.up)
			{				
				direction = "up";
							
			}
			else if(key.down)
			{
				direction = "down";
							
			}
			
			if(key.left)
			{
				direction = "left";	

			}
			else if(key.right)
			{
				direction = "right";

			}
			
			for(int i = 0; i < gp.s.length; i++)
			{
				int entIndex = gp.c.checkEnt(this, gp.s[i]);
				damage(entIndex, i);

			}
			gp.c.checkTile(this);
			int objIndex = gp.c.checkObj(this, true);
			pickUp(objIndex);
			if(item)
			{
				itemDrop = random.nextInt(5) + 1;
				item = false;
				
			}
			
			switch (itemDrop) {
			  case 1:
				  healthNum++;
				  itemDrop = 0;
				  popUp = true;
			    break;
			  case 2:
				  healthNum++;
				  itemDrop = 0;
				  popUp = true;
			    break;
			  case 3:
				  healthNum++;
				  itemDrop = 0;
				  popUp = true;
			    break;
			  case 4:
				  healthNum++;
				  itemDrop = 0;
				  popUp = true;
			    break;
			  case 5:
			    // Action for item drop 5
			    // ...
			    break;
			  default:
			    // Action if itemDrop is not 1-5
			    // ...
			    break;
			}
			
			
			if(!collOn && !key.attack)
			{
				if(key.up || key.down || key.left || key.right || key.attack)
				{
					switch(direction)
					{
					case "up":
						worldy -= speed;
						break;
					case "down":
						worldy += speed;
						break;
					case "left":
						worldx -= speed;
						break;
					case "right":
						worldx += speed;
						break;
					}
				}
				else
				{
					collOn = true;
				}
				
			}
			
			stepCount++;
		    if (stepCount > 12) {
		        step = (step == 1) ? 2 : 1;
		        stepCount = 0;
		    }
		}
		
	}
	
	int hdelay = 0;
	public void damage(int i, int num) {
	    if (i == 1) {
	        hdelay++;
	        playerIsHit = true; // Set playerIsHit to true when hit

	        while (hdelay > 30) {
		    	Random random = new Random();
		    	int entdmg = random.nextInt(8)+5;
	            gp.p.health -= entdmg;
	            hdelay = 0; // Reset the delay timer
	        }

	    }
	    if(i == 2)
	    {
	    	Random random = new Random();
	    	int attackdmg = random.nextInt(1)+3;
	    	gp.s[num].health -= attackdmg;
	    	gp.s[num].speed = 1;
	    	System.out.println(gp.s[num].health);
	    	
	    }
	}
	
	public void pickUp(int i)
	{
		if(i != 69)
		{
			String objName = gp.obj[i].name;
			
			switch(objName)
			{
				case "Key_Ice":
					System.out.println("Ice Key!");
					gp.obj[i] = null;
					kCollected++;
					break;
				case "Fire_Key":
					break;
				case "Acid_Key":
					break;
			
			}
				
				
		}
	}
	public void draw(Graphics2D g2) {
		
		    
		if(!gp.end)
		{
			 BufferedImage image = null;
			    
			    int tempX = screenX;
			    int tempY = screenY;

			    switch (direction) {
			        case "up":
			            if (step == 1) {
			                image = up2;
			            } else if (step == 2) {
			                image = up3;
			            }

			            if (isAttacking) {
			            	tempY = screenY - gp.tileSize;
			                image = up1;
			                sizeY = 2;
			            }
			            break;

			        case "down":
			            if (step == 1) {
			                image = down2;
			            } else if (step == 2) {
			                image = down3;
			            }

			            if (isAttacking) {
			                image = down1;
			                sizeY = 2;
			            }
			            break;

			        case "left":
			            if (step == 1) {
			                image = left2;
			            } else if (step == 2) {
			                image = left3;
			            }

			            if (isAttacking) {
			            	tempX = screenX - gp.tileSize;
			                image = left1;
			                sizeX = 2;
			            }
			            break;

			        case "right":
			            if (step == 1) {
			                image = right2;
			            } else if (step == 2) {
			                image = right3;
			            }

			            if (isAttacking) {
			                image = right1;
			                sizeX = 2;
			            }
			            break;
			    }
			    
			    if (key.use && healthNum > 0 && !popUp)
			    {
			    	if(health != 100)
			    	{
			    		 healthNum--;
				    	  
				    	  // Check if healing would exceed maximum health
				    	  if (health + 10 > 100) {
				    	    // Set health to maximum
				    	    health = 100;
				    	  } else {
				    	    // Add health normally
				    	    health += 10;
				    	  }
				    	  key.use = false;
			    	}
			    	 
			    }
			    
				// Reset sizeX and sizeY to 1 when key.attack is false or direction changes while attacking
			    if (!key.attack || (direction != previousDirection && isAttacking)) {
			        sizeX = 1;
			        sizeY = 1;       
			    }

			    previousDirection = direction; // Update previous direction
			    
			    int compDelay = 0;
			    if (playerIsHit) {
			    	
			    	
			        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
			        playerIsHit = false;
			    }
			    
			  
			    g2.setFont(customFont);

			    //player image
			    g2.drawImage(image, tempX, tempY, gp.tileSize * sizeX, gp.tileSize * sizeY, null);		   
			    
			    //resetting the RGB ALPHA
			    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.f));	
			    
			    //pop up for items out of chests
			    if (popUp)
			    {
			    	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
			        // Draw the dark outline
			        g2.setColor(Color.BLACK);
			        g2.fillRect(gp.screenWidth / 2 - 105, gp.screenHeight / 2 - 50, 210, 100);

			        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
			        // Draw the pop-up background
			        g2.setColor(Color.DARK_GRAY);
			        g2.fillRect(gp.screenWidth / 2 - 100, gp.screenHeight / 2 - 45, 200, 90);

			        // Set font

			        // Get font metrics
			        FontMetrics metrics = g2.getFontMetrics(customFont);
			       
			        // Calculate text width and height
			        int textWidth = metrics.stringWidth("You found an item!");
			        int textHeight = metrics.getHeight();

			        // Calculate center X coordinate with slight adjustment
			        int textX = (gp.screenWidth / 2) - (textWidth / 2);
			        // Calculate center Y coordinate
			        int textY = (gp.screenHeight / 2) - (textHeight / 2);

			        // Draw text with centered position
			        g2.setColor(Color.WHITE);
			        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.f));
			        g2.setColor(Color.BLACK);
			        g2.drawString("You found an item!", textX + 1, textY + 1);
			        g2.setColor(Color.LIGHT_GRAY);
			        g2.drawString("You found an item!", textX, textY);

			        // Get width of the closing instruction
			        textWidth = metrics.stringWidth("Press E to close");

			        // Calculate and adjust center X coordinate for the instruction
			        textX = (gp.screenWidth / 2) - (textWidth / 2);

			        // Draw closing instruction with centered X position
			        g2.drawString("Press E to close", textX, gp.screenHeight / 2 + 20);

			        if (key.use)
			        {
			            popUp = false;
			            collOn = false;
			        }
			    }

			    	g2.setColor(Color.WHITE);
					g2.drawRect(10, 10, 100, 30);
					g2.setColor(Color.red);
					g2.fillRect(11, 11, health - 1, 29);
					if(healthNum > 0)
					{
						g2.drawImage(healthPack, 10, 50, gp.tileSize - 10,gp.tileSize - 10, null);
						g2.setColor(Color.WHITE);
						g2.drawString("x " + Integer.toString(healthNum) + " [E]", 50, 80);
					}
					
					g2.drawString(Integer.toString(health), 115, 30);
					g2.dispose();
		}
	   


			if (gp.end) {
			    if (health <= 1)
			    {
			        health = 0;
			    }

			    // Fill the screen with black
			    g2.setColor(Color.BLACK);
			    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

			    // Draw the "GAME OVER" text in big red font
			    g2.setColor(Color.RED);
			    g2.setFont(new Font("Arial", Font.BOLD, 50));
			    g2.drawString("GAME OVER", (gp.screenWidth / 2) - 160, gp.screenHeight / 2);
			    if(key.use)
			    {
			    	
			    }
			    speed = 0;
			}
	}
	
}
