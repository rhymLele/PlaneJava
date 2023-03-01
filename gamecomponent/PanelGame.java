package game.component;

import javax.swing.JComponent;

import game.obj.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.lang.*;

public class PanelGame extends JComponent{
	
	
	
	private Graphics2D g2;
	private BufferedImage image;
	private int width, height;
	private Thread thread;
	private boolean start=true;
	
	
	//Game FPS
	private final int FPS = 60;
	private final int TARGET_TIME = 1000000000/FPS;
	//Game Object
	private Player player;
	private Key key;
	
	
	public void start() {
		width =getWidth();
		height = getHeight();
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g2 = image.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		thread = new Thread(new Runnable() {
			public void run() {
				while (start) {
					
					long startTime = System.nanoTime();
					drawBackground();
					drawGame();
					render();
					long time = System.nanoTime() - startTime;
					if (time < TARGET_TIME) {
						long sleep = (TARGET_TIME - time)/1000000;
						sleep(sleep);

					}
				}
				
			}
		});
		initObjectGame();
		initKeyboard();
		thread.start();
	}
	
	private void initObjectGame() {
		player = new Player();
	}
	
	private void initKeyboard() {
		key = new Key();
		requestFocus();
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()==KeyEvent.VK_A) {
					key.setKey_left(true);
				}
				else if(e.getKeyCode()==KeyEvent.VK_D) {
					key.setKey_right(true);
				}
				else if(e.getKeyCode()==KeyEvent.VK_SPACE) {
					key.setKey_space(true);
				}
				else if(e.getKeyCode()==KeyEvent.VK_J) {
					key.setKey_j(true);
				}
				else if(e.getKeyCode()==KeyEvent.VK_K) {
					key.setKey_k(true);
				}
			}
			
			public void KeyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_A) {
					key.setKey_left(false);
				}
				else if(e.getKeyCode()==KeyEvent.VK_D) {
					key.setKey_right(false);
				}
				else if(e.getKeyCode()==KeyEvent.VK_SPACE) {
					key.setKey_space(false);
				}
				else if(e.getKeyCode()==KeyEvent.VK_J) {
					key.setKey_j(false);
				}
				else if(e.getKeyCode()==KeyEvent.VK_K) {
					key.setKey_k(false);
				}
			}
			
		});
		new Thread(new Runnable() {
			public void run() {
				float s = 0.5f;
				while(start){
					float angle = player.getAngle();
					if(key.isKey_left()) {
						angle -= s;
					}
					if(key.isKey_right()) {
						angle += s;
					}
					if(key.isKey_space()) {
						player.speedUp();
					} else {
						player.speedDown();
					}
					player.changeAngle(angle);
					sleep(5);
				}
			
			}
		}).start();
	}
	
	
	private void drawBackground() {
		g2.setColor(new Color(0,0,0));
		g2.fillRect(0, 0, width, height);
		
	}
	
	private void drawGame() {
		player.draw(g2);
		player.changeLocation(600, 350);
	}
	
	private void render() {
		Graphics g = getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
	}
	
	
	private void sleep(long speed) {
		try {
			Thread.sleep(speed);
		} catch (InterruptedException ex) {
			System.err.println(ex);
		}
	}
	

}
