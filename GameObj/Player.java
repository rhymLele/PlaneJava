package game.obj;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

public class Player {
	
	public Player(){
		this.image = new ImageIcon(getClass().getResource("/game/image/plane.png")).getImage();
		this.image_speed=new ImageIcon(getClass().getResource("/game/image/plane_speed.png")).getImage();
	}
	
	
	public static final double PLAYER_SIZE=64;
	private double x;
	private double y;
	private final float MAX_SPEED=1f;
	private float speed=0f;
	private float angle=0f;
	private final Image image;
	private final Image image_speed;
	private boolean speedUp;
	
	public void changeLocation(double x, double y) {
		this.x = x;
		this.y =y;
	}
	
	public void changeAngle(float angle) {
		if(angle<0) {
			angle = 359;
		}else if(angle >359) {
			angle = 0;
		}						//chinh toa do sao cho ko nho hon 0 va lon hon 360
		this.angle=angle;
		
	}
	
	public void draw(Graphics2D g2) {
		AffineTransform oldFrameform = g2.getTransform();
		g2.translate(x, y);
		AffineTransform tran = new AffineTransform();
		tran.rotate(Math.toRadians(angle+90), PLAYER_SIZE/2, PLAYER_SIZE/2);
		g2.drawImage(speedUp?image_speed:image, tran, null);
		
		
		g2.setTransform(oldFrameform);
	}
	
	public double getX() {
		return x;	
	}
	
	public double getY() {
		return y;
	}
	
	public float getAngle(){
		return angle;
	}
	public void speedUp() {
		speedUp=true;
	}
	public void speedDown() {
		speedUp=false;
	}
}
	
