package GameObj;

import java.awt.Image;

import javax.swing.ImageIcon;
public class Player {
	public Player()
	{
		this.image=new ImageIcon(getClass().getResource("image/Pac1.png")).getImage();
		this.image_speed=new ImageIcon(getClass().getResource("image/Pac2.png")).getImage();
	}
	public static final double PLAYER_SIZE=64;
	private double x;
	private double y;
	private float angle=0f;
	private final Image image;
	private final Image image_speed;
	public void changeAngle()
	{
		
	}
}
