package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class KeyHandler implements KeyListener {

	public boolean up, down, left, right, attack, use;
	long lastAttackTime = 0;
	static final long ATTACK_DELAY = 500; 
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
			attack = false;
		}
		
		if(k == KeyEvent.VK_S || k == KeyEvent.VK_DOWN)
		{
			down = true;
			attack = false;
		}
		
		if(k == KeyEvent.VK_A || k == KeyEvent.VK_LEFT)
		{
			left = true;
			attack = false;
		}
		
		if(k == KeyEvent.VK_D || k == KeyEvent.VK_RIGHT)
		{
			right = true;
			attack = false;
		}
		
		if(k == KeyEvent.VK_E)
		{
			use = true;
		}
		
		if(k == KeyEvent.VK_SPACE || k == KeyEvent.VK_ENTER)
		{
		    long currentTime = System.currentTimeMillis();

		    // Only attack if at least ATTACK_DELAY milliseconds have passed since the last attack
		    if (currentTime - lastAttackTime >= ATTACK_DELAY) {
		        attack = true;
		        lastAttackTime = currentTime;

		        // Schedule a task to set attack back to false after 300 milliseconds
		        new Timer().schedule(new TimerTask() {
		            @Override
		            public void run() {
		                attack = false;
		            }
		        }, 300);
		    }
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
		if(k == KeyEvent.VK_SPACE || k == KeyEvent.VK_ENTER)
		{
			attack = false;
		}
		if(k == KeyEvent.VK_E)
		{
			use = false;
		}
	}

}
