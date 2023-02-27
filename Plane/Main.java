package Plane;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import gamecomponent.PanelGame;
public class Main extends JFrame{
	public Main()
	{
		init();
	}
	public void init()
	{
		this.setTitle("Plane");
		this.setSize(1366,768);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		PanelGame pg=new PanelGame();
		this.add(pg);
//		this.addWindowListener(new WindowAdapter()
//				{
//				public void windowOppened(WindowEvent e)
//				{
//					pg.start();
//				}
//			});
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main main=new Main();
		main.setVisible(true);
	}

}
