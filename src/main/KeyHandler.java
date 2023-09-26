package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	public boolean up, down, left, right;
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		
		if(k == KeyEvent.VK_W || k == KeyEvent.VK_UP)
		{
			up = true;
		}
		
		if(k == KeyEvent.VK_S || k == KeyEvent.VK_DOWN)
		{
			down = true;
		}
		
		if(k == KeyEvent.VK_A || k == KeyEvent.VK_LEFT)
		{
			left = true;
		}
		
		if(k == KeyEvent.VK_D || k == KeyEvent.VK_RIGHT)
		{
			right = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int k = e.getKeyCode();
		
		if(k == KeyEvent.VK_W || k == KeyEvent.VK_UP)
		{
			up = false;
		}
		
		if(k == KeyEvent.VK_S || k == KeyEvent.VK_DOWN)
		{
			down = false;
		}
		
		if(k == KeyEvent.VK_A || k == KeyEvent.VK_LEFT)
		{
			left = false;
		}
		
		if(k == KeyEvent.VK_D || k == KeyEvent.VK_RIGHT)
		{
			right = false;
		}
		
	}

}
