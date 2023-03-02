package game.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import game.obj.Bullet;
import game.obj.Player;

public class PanelGame extends JComponent{
	
	private Graphics2D g2;
	private BufferedImage image;
	private int width;
	private int height;
	private Thread thread;
	private boolean start=true;
	private int shortTime;
	//game fps
	private final int FPS=60;
	private final int TARGET_TIME=1000000000/FPS;
	//game obj
	private Player player;
	private List<Bullet> bullets;
	private Key key;
	public void start()
	{
		width=getWidth();
		height=getHeight();
		image=new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		g2=image.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		thread=new Thread(new Runnable() {
				@Override
				public void run()
				{
					while(start)
					{
						long startTime=System.nanoTime();
						drawBackground();
						drawGame();
						render();
						long time=System.nanoTime()-startTime;
						if(time<TARGET_TIME)
						{
							long sleep=(TARGET_TIME-time)/1000000;
							sleep(sleep);
						}
					}
				}
		});
		initObjectGame();
		initKeyboard();
		initBullets();
		thread.start();
	}
	private void initObjectGame()
	{
		player=new Player(); 
		player.changeLocation(150, 150);
	}
	private void initKeyboard()
	{
		key = new Key();
		requestFocus();
		addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode()==KeyEvent.VK_A)
				{
					key.setKey_left(true);
				}else if(e.getKeyCode()==KeyEvent.VK_D)
				{
					key.setKey_right(true);
				}else if(e.getKeyCode()==KeyEvent.VK_SPACE)
				{
					key.setKey_space(true);
				}else if(e.getKeyCode()==KeyEvent.VK_J)
				{
					key.setKey_j(true);
				}else if(e.getKeyCode()==KeyEvent.VK_K)
				{
					key.setKey_k(true);
				}
			}
				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					if(e.getKeyCode()==KeyEvent.VK_A)
					{
						key.setKey_left(false);
					}else if(e.getKeyCode()==KeyEvent.VK_D)
					{
						key.setKey_right(false);
					}else if(e.getKeyCode()==KeyEvent.VK_SPACE)
					{
						key.setKey_space(false);
					}else if(e.getKeyCode()==KeyEvent.VK_J)
					{
						key.setKey_j(false);
					}else if(e.getKeyCode()==KeyEvent.VK_K)
					{
						key.setKey_k(false);
					}
				}
				});
		new Thread(new Runnable()
				{@Override
					public void run() {
					float s=0.5f;
					while(start)
					{
						float angle =player.getAngle();
						if(key.isKey_left())
						{
							angle-=s;
						}
						if(key.isKey_right())
						{
							angle+=s;
						}
						if(key.isKey_j()||key.isKey_k())
						{
							if(shortTime==0)
							{
								if(key.isKey_j())
								{
									bullets.add(0,new Bullet(player.getX(),player.getY(),player.getAngle(),5,3f));
								}else
								{
									bullets.add(0,new Bullet(player.getX(),player.getY(),player.getAngle(),20,3f));
								}
								shortTime++;
								if(shortTime==15)
								{
									shortTime=0;
								}
							}else
							{
								shortTime=0;
							}
						}
						if(key.isKey_space())
						{
							player.speedUp();
						}else
						{
							player.speedDown();
						}
						player.update();
						player.changeAngle(angle);
						sleep(5);
					}
				};
				}		
		).start();
	}
	private void initBullets()
	{
		bullets=new ArrayList<>();
		new Thread(new Runnable()
				{
			@Override
			public void run() {
				while(start)
				{
					for(int i=1;i<=bullets.size();i++)
					{
						Bullet bullet =bullets.get(i);
						if(bullet!=null)
						{
							bullet.update();
							if(!bullet.check(width, height))
							{
								bullets.remove(bullet);
							}
						}else
						{
							bullets.remove(bullet);
						}
					}
					sleep(1);
				}
			};
				}		
				);
	}
	private void drawBackground()
	{
		g2.setColor(new Color(30,30,30));
		g2.fillRect(0, 0, width, height);
	}
	private void drawGame()
	{
		player.draw(g2);
		for(int i=0;i<bullets.size();i++)
		{
			Bullet bullet=bullets.get(i);
			if(bullet!=null)
			{
				bullet.draw(g2);
			}
		}
	}
	private void render()
	{
		Graphics g=getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
	}
	private void sleep(long speed)
	{
		try {
			Thread.sleep(speed);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
	}
}
