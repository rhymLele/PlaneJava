package game.main;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import game.component.PanelGame;
public class Main extends JFrame{
	
	public Main()
	{
		init();
	}
	private void init()
	{
		this.setTitle("Plane");
		this.setSize(1366,768);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		PanelGame panelGame=new PanelGame();
		this.add(panelGame);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				panelGame.start();
			}
		});
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main main=new Main();
		main.setVisible(true);
	}

}
