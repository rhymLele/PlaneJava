package game.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import game.obj.Bullet;
import game.obj.Effect;
import game.obj.Player;
import game.obj.Rocket;

public class PanelGame extends JComponent{
	
	private Graphics2D g2;
	private BufferedImage image;
	private int width;
	private int height;
	private Thread thread;
	private boolean start=true;
	private int shotTime;
	private Key key;
	private int score=0;
//	private ImageIcon image2;
//	private Image pic;
	//game fps
	private final int FPS=60;
	private final int TARGET_TIME=1000000000/FPS;
	//game obj
	private Player player;
	private List<Bullet> bullets;
	private List<Rocket> rockets;
	
	private List <Effect> boomEffects;
	//fps cac thu 
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
	
	private void addRocket()
	{
		Random ran=new Random();
		int locationY=ran.nextInt(height-50)+25;
		Rocket rocket=new Rocket();
		rocket.changeLocation(0, locationY);
		rocket.changeAngle(0);
		rockets.add(rocket);
		int locationY2=ran.nextInt(height-50)+25;
		Rocket rocket2=new Rocket();
		rocket2.changeLocation(width, locationY2);
		rocket2.changeAngle(180);//from right to left
		rockets.add(rocket2);
	}
	
	private void initObjectGame()
	{
		player=new Player(); 
		player.changeLocation(683, 384);
		rockets=new ArrayList<>();
		boomEffects=new ArrayList<>();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(start)
				{
					addRocket();
					sleep(2500);
				}
			}
		}).start();
	}
	
	private void resetGame() {
		score=0;
		rockets.clear();
		bullets.clear();
		player.changeLocation(683, 384);
		player.reset();
	}
	
	private void initKeyboard()
	{
		key = new Key();
		requestFocus();
		addKeyListener(new KeyAdapter()
		{
			@Overridpackage game.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import game.obj.Bullet;
import game.obj.Effect;
import game.obj.Player;
import game.obj.Rocket;

public class PanelGame extends JComponent{
	
	private Graphics2D g2;
	private BufferedImage image;
	private int width;
	private int height;
	private Thread thread;
	private boolean start=true;
	private int shotTime;
	private Key key;
	private int score=0;
//	private Image image2;
//	private Image pic;
	//game fps
	private final int FPS=60;
	private final int TARGET_TIME=1000000000/FPS;
	//game obj
	private Player player;
	private List<Bullet> bullets;
	private List<Rocket> rockets;
	//game state
	public int gameState;
	public int playState=1;
	public int pauseState=2;
	private List <Effect> boomEffects;
	//fps cac thu 
	public void start()
	{
		gameState=1;
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
	
	private void addRocket()
	{
		Random ran=new Random();
		int locationY=ran.nextInt(height-50)+25;
		Rocket rocket=new Rocket();
		rocket.changeLocation(0, locationY);
		rocket.changeAngle(0);
		rockets.add(rocket);
		int locationY2=ran.nextInt(height-50)+25;
		Rocket rocket2=new Rocket();
		rocket2.changeLocation(width, locationY2);
		rocket2.changeAngle(180);//from right to left
		rockets.add(rocket2);
	}
	
	private void initObjectGame()
	{
		player=new Player(); 
		player.changeLocation(683, 384);
		rockets=new ArrayList<>();
		boomEffects=new ArrayList<>();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(start)
				{
					addRocket();
					sleep(2500);
				}
			}
		}).start();
	}
	
	private void resetGame() {
		score=0;
		rockets.clear();
		bullets.clear();
		player.changeLocation(683, 384);
		player.reset();
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
				}else if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
				{
					key.setKey_escape(true);
				}else if(e.getKeyCode()==KeyEvent.VK_P)
				{
					key.setKey_pause(true);
					if(gameState==playState) gameState=pauseState;
					else if(gameState==pauseState) gameState=playState;
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
					}else if(e.getKeyCode()==KeyEvent.VK_ENTER)
					{
						key.setKey_enter(false);
					}else if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
					{
						key.setKey_escape(false);
					}else if(e.getKeyCode()==KeyEvent.VK_P)
					{
						key.setKey_pause(false);
					}
				}
				});
		new Thread(new Runnable()
				{@Override
					public void run() {
					float s=0.5f;
					while(start)
					{
						if(player.isAlive()) {
							float angle =player.getAngle();
							if(key.isKey_left())
							{
								angle-=s;
							}
							if(key.isKey_right())
							{
								angle+=s;
							}
							if(key.isKey_pause())
							{
								if(gameState==playState) gameState=pauseState;
								else if(gameState==pauseState) gameState=playState;
							}
							if(key.isKey_j()||key.isKey_k()) {
								if(shotTime==0) {
									if(key.isKey_j()) {
										bullets.add(0, new Bullet(player.getX(), player.getY(), player.getAngle(), 5, 1f));
									}else {
										bullets.add(0, new Bullet(player.getX(), player.getY(), player.getAngle(), 20, 1f));
									}
								}
								shotTime++;
								if(shotTime==5) {
									shotTime=0;
								}
							}
							
							else {
								shotTime=0;
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
						}
						else if(key.isKey_enter()) {
							resetGame();
						}
						else if(key.isKey_escape()) {
							System.exit(0);
						}
						
						
						for(int i=0;i<rockets.size();i++)
						{
							Rocket rocket=rockets.get(i);
							if(rocket!=null)
							{
								rocket.update();
								if(!rocket.check(width, height))
								{
									rockets.remove(rocket);
								}
								else {
									if(player.isAlive()) {
										checkPlayer(rocket);
									}
								}
							}
						}
						sleep(5);
					}
				};
				}		
		).start();
	}
	
	private void initBullets() {
		bullets=new ArrayList<>();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(start) {
					for (int i=0;i<bullets.size();i++) {
						Bullet bullet=bullets.get(i);
						if(bullet!=null) {
							bullet.update();
							checkBullets(bullet);
							if(!bullet.check(width, height)) {
								bullets.remove(bullet);
							}
						}
						else {
							bullets.remove(bullet);
						}
					}
					for(int i=0;i<boomEffects.size();i++)
					{
						Effect boomEffect =boomEffects.get(i);
						if(boomEffect!=null)
						{
							boomEffect.update();
							if(!boomEffect.check())
							{
								boomEffects.remove(boomEffect);
							}
						}else
						{
							boomEffects.remove(boomEffect);
						}
					}
					sleep(1);
				}
				
			}
		}).start();
	}
	
	private void checkBullets(Bullet bullet)
	{
		for(int i=0;i<rockets.size();i++)
		{
			Rocket rocket=rockets.get(i);
			if(rocket!=null)
			{
				Area area=new Area(bullet.getShape());
				area.intersect(rocket.getShape());
				if(!area.isEmpty())
				{
					if(!rocket.updateHP(bullet.getSize()))
					boomEffects.add(new Effect(bullet.getCenterX(),bullet.getCenterY(),3,5,60,0.5f,new Color(230,207,105)));
					if(true)
					{
						score++;
						rockets.remove(rocket);
						double x=rocket.getX()+Rocket.ROCKET_SIZE/2;
						double y=rocket.getY()+Rocket.ROCKET_SIZE/2;
						boomEffects.add(new Effect(x,y,5,5,75,0.05f,new Color(32,178,169)));
						boomEffects.add(new Effect(x,y,10,10,120,0.5f,new Color(102,216,250)));
						boomEffects.add(new Effect(x,y,10,10,115,0.3f,new Color(230,207,105)));
						boomEffects.add(new Effect(x,y,8,5,100,0.4f,new Color(240,96,117)));
						boomEffects.add(new Effect(x,y,10,5,105,0.5f,new Color(255,70,70)));
						boomEffects.add(new Effect(x,y,10,5,110,0.2f,new Color(121,76,217)));
						
					}
					
					bullets.remove(bullet);
				}
			}
		}
	}
	
	private void checkPlayer(Rocket rocket)
	{
			if(rocket!=null)
			{
				Area area=new Area(player.getShape());
				area.intersect(rocket.getShape());
				if(!area.isEmpty())
				{
					double rocketHp = rocket.getHP();
					if(!rocket.updateHP(player.getHP())) {
					rockets.remove(rocket);
					}
					if(!player.updateHP(rocketHp)) {
						player.setAlive(false);
						}
				}
			}
	}
	
	private void drawBackground()
	{
		g2.setColor(new Color(30,30,30));
//		image2=new ImageIcon(getClass().getResource("/game/image/Background.png")).getImage();
//		super.paintComponent(g2);
//		g2.drawImage(pic,0,0,null);
		g2.fillRect(0, 0, width, height);
	}
	
	private void drawGame()
	{
		if(gameState==playState)
		{
			if(player.isAlive()) {
			player.draw(g2);
		}
		
		for(int i=0;i<bullets.size();i++) {
			Bullet bullet = bullets.get(i);
			if(bullet!=null) {
				bullet.draw(g2);
			}
		}
		for(int i=0;i<rockets.size();i++)
		{
			Rocket rocket=rockets.get(i);
			if(rocket!=null)
			{
				rocket.draw(g2);
			}
		}
		for(int i=0;i<boomEffects.size();i++)
		{
			Effect boomEffect =boomEffects.get(i);
			 if(boomEffect!=null)
			 {
				 boomEffect.draw(g2);
			 }
		}
		
		g2.setColor(Color.white);
		g2.setFont(getFont().deriveFont(Font.BOLD,15f));
		g2.drawString("Score : "+score, 10, 20);
		if(!player.isAlive()) {
			String text ="Game Over!";
			String textKey ="Press enter to continue...";
			String textKey1 ="Press esc to exit...";
			g2.setFont(getFont().deriveFont(Font.BOLD, 50f));
			FontMetrics fm = g2.getFontMetrics();
			Rectangle2D r2 = fm.getStringBounds(text, g2);
			double textWidth = r2.getWidth();
			double textHeight = r2.getHeight();
			double x= (width - textWidth)/2;
			double y= (height - textHeight)/2;
			g2.drawString(text, (int) x, (int) y + fm.getAscent());
			g2.setFont(getFont().deriveFont(Font.BOLD, 20f));
			fm=g2.getFontMetrics();
			r2 = fm.getStringBounds(textKey, g2);
			textWidth = r2.getWidth();
			textHeight = r2.getHeight();
			x= (width - textWidth)/2;
			y= (height - textHeight)/2;
			g2.drawString(textKey, (int) x, (int) y + fm.getAscent()+50);
			g2.setFont(getFont().deriveFont(Font.BOLD, 20f));
			fm=g2.getFontMetrics();
			r2 = fm.getStringBounds(textKey1, g2);
			textWidth = r2.getWidth();
			textHeight = r2.getHeight();
			x= (width - textWidth)/2;
			y= (height - textHeight)/2;
			g2.drawString(textKey1, (int) x, (int) y + fm.getAscent()+100);
			
		}
		}if(gameState==pauseState)
		{
			//nothing to do
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
	
	private void drawPause()
	{
		
	}
}
