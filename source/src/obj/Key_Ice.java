package obj;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Key_Ice extends mainObject {
	
	public Key_Ice()
	{
		name = "Key_Ice";
		try
		{
			im = ImageIO.read(getClass().getResourceAsStream("/item/IceKey.png"));
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		touch = true;
	}

}
