package Tron;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;


/**
 * @author Damien ROBERT
 * This class is in charge of the drawing on the frame. 
 */
public class RenderPanel extends JPanel
{
	public static final Color WP = new Color(0,0,0); 
	private static final long serialVersionUID = 1L;
	
	@Override
	/** 
	 * Before fillRect (draw), we define the color. WP is the wallpaper color.
	 */
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Tron tron = Tron.tron;
		
		char winner = Tron.winner;

		g.setColor(WP);
		
		g.fillRect(0, 0, 600, 400);
		
		
		/*Tron1*/
		g.setColor(Color.BLUE);

		for (Point point : tron.tronBody)
		{
			g.fillRect(point.x * 10, point.y * 10, 10, 10); // 10 represent the size of the rectangle we draw. 
		}
		
		
		g.fillRect(tron.head.x * 10, tron.head.y * 10, 10, 10);
		
		/*Tron2*/
		g.setColor(Color.magenta);
		
		for (Point point2 : tron.tronBody2)
		{
			g.fillRect(point2.x * 10, point2.y * 10, 10, 10);
		}
		
		g.fillRect(tron.head2.x * 10, tron.head2.y * 10, 10, 10);
		
		
		String string ="Temps écoulé: " + tron.time / 12;
		
		g.setColor(Color.white);
		
		g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), 10);

		if (winner == 'R')
		{
			string = "Fin de partie. Le gagnant est le joueur rouge";
		}
		if (winner == 'B')
		{
			string = "Fin de partie. Le gagnant est le joueur bleu";
		}
		
		if (tron.dead)
		{
			g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) tron.dim.getHeight() / 4);
		}

		string = "Jeu en pause";

		if (tron.paused && !tron.dead)
		{
			g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) tron.dim.getHeight() / 4);
		}
		
		
	}
	
	
}
