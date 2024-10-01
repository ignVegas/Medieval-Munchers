package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Scale {

	public BufferedImage scaled(BufferedImage og, int h, int w)
	{
		BufferedImage sc = new BufferedImage(w,h, og.getType());
		Graphics2D g2 = sc.createGraphics();
		g2.drawImage(og , 0 , 0 , w , h , null);
		g2.dispose();
		return sc;
	}
}
