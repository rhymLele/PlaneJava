package gamecomponent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class PanelGame extends JComponent{
	//Game FPS
	private Graphics2D g2;
	private BufferedImage image;
	private int width;
	private int heigh;
	private Thread thread;
	private final int FPS=60;
	private boolean start=true;
	private final int TARGET_TIME=1000000000/FPS;
	public void start()
	{
		width=getWidth();
		heigh=getHeight();
		image =new BufferedImage(width,heigh,BufferedImage.TYPE_INT_ARGB);
		g2=image.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		thread=new Thread(new Runnable(){
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
				if(time<TARGET_TIME)	//check neu time < TARGETTIME thi se tri hoan vong lap
				{
					long sleep=(TARGET_TIME-time)/1000000;
					sleep(sleep);
				}
				
			}
			}
		});
		thread.start();
	}
	private void drawBackground()
	{
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, width, heigh);
	}
	private void drawGame()
	{
		
	}
	private void render()
	{
			Graphics g=getGraphics();
			g.drawImage(image, 0, 0,null);
			g.dispose();
	}
	private void sleep(long speed)
	{
		try {
			thread.sleep(speed);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
	}
}
