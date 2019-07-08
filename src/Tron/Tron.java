package Tron;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * @author Damien ROBERT
 */
public class Tron implements ActionListener, KeyListener
{

	public static Tron tron;
	
	public JFrame jframe;

	public RenderPanel renderPanel;

	public Timer timer = new Timer(40, this);

	public ArrayList<Point> tronBody = new ArrayList<Point>();
	
	public ArrayList<Point> tronBody2 = new ArrayList <Point>();

	public Point head, head2;
	
	public static final int LEFT2 = 2, RIGHT2 = 3, LEFT = 2, RIGHT = 3;

	public int clock = 0, direction = RIGHT, time, direction2 = RIGHT2;

	public boolean dead = false, paused, keypress = false, keypress2 = false;
	
	public static char winner;

	public Dimension dim;
	
	public char last_direction, last_key, last_direction2, last_key2;
	
	
	
	public static void main(String[] args)
	{
		tron = new Tron();
	}
	
	/**
	 * The most important method. It define the frame, the attributes of the frames and add the render panel.
	 */
	public Tron()
	{
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		jframe = new JFrame("Tron");
		jframe.setVisible(true);
		jframe.setSize(600, 400);
		jframe.setResizable(false);
		jframe.setLocationRelativeTo(null);
		jframe.add(renderPanel = new RenderPanel());
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.addKeyListener(this);
		startGame();
	}
/**
 * Initialisation of the variables.
 */
	public void startGame()
	{
		dead = false;
		paused = false;
		time = 0;
		clock = 0;
		direction = RIGHT;
		direction2 = RIGHT2;
		head = new Point(0, -1);
		head2 = new Point(58, -1);
		tronBody.clear();
		tronBody2.clear();
		timer.start();
		last_direction = 'R';
		last_key = 'R';
		last_direction2 = 'R';
		last_key2 = 'R';
		
	}
/**
 * The method actionPerformed is very important. It defines the logic of displacement.
 * Because we only have left and right, we have to determine in what sense the lightcycle is to move in the right way.
 * For exemple, if the user move to the left while the lightcycle is running to the right, the lightcycle has to turn to the top.
 * 
 * Furthermore the following code define who is the winner, if a lightcycle enter a wall or move ahead the map.
 */
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		renderPanel.repaint();
		clock++;

		if (clock % 2 == 0 && head != null && !dead && !paused)
		{
			time++;

			tronBody.add(new Point(head.x, head.y));
			tronBody2.add(new Point(head2.x, head2.y));
			/*Tron1*/

			if (direction == LEFT)
			{
				if (last_direction == 'D')
				{
					if (head.x + 1 < 60 && noBodyAt(head.x + 1, head.y) && noBodyAt2(head.x + 1, head.y))
					{
					head = new Point(head.x + 1, head.y);
					if (keypress == true)
				{	last_direction = 'R';
						keypress = false;
				}
					}
					else
					{
						dead = true;
						winner = 'R';
					}
				}
				
				else if (last_direction == 'R')
				{
					if (head.y - 1 >= 0 && noBodyAt(head.x, head.y -1) && noBodyAt2(head.x, head.y - 1))
					{
						head = new Point(head.x, head.y -1);
					if (keypress == true)
					{last_direction = 'U';
					keypress = false;
					}
					}
					else
					{
						dead = true;
						winner = 'R';
					}
				}
				else if (last_direction == 'U')
					{
					if (head.x - 1 >= 0 && noBodyAt(head.x - 1, head.y) && noBodyAt2(head.x - 1, head.y))
					{
					head = new Point(head.x -1, head.y);
					if (keypress == true) {
						last_direction = 'L';
						keypress = false;
					}
					}
					else
					{
						dead = true;
						winner = 'R';
					}
					}
				else if (last_direction == 'L')
				{
					if (head.y + 1 < 38 && noBodyAt(head.x, head.y + 1) && noBodyAt2(head.x, head.y + 1))
					{
					head = new Point(head.x, head.y +1);
					if (keypress == true) {
					last_direction = 'D';
					keypress = false;
					}
					}
					else
					{
						dead = true;
						winner = 'R';
					}
				}
			}

			if (direction == RIGHT)
			{
				if (last_direction == 'D')
				{
					if (head.x - 1 >= 0 && noBodyAt(head.x - 1, head.y) && noBodyAt2(head.x - 1, head.y))
					
				{
					head = new Point(head.x - 1, head.y);
					if (keypress == true)
					{	last_direction = 'L';
						keypress = false;
				}
				}
				
					else
					{
						dead = true;
						winner = 'R';
					}
				}
			
				else if (last_direction == 'R')
				{
					if (head.y + 1 < 38 && noBodyAt(head.x, head.y + 1) && noBodyAt2(head.x, head.y + 1))
					{
					head = new Point(head.x, head.y +1);
					if (keypress == true)
					{last_direction = 'D';
					keypress = false;
					}
					}
					else
					{
						dead = true;
						winner = 'R';
					}
				}
				else if (last_direction == 'U')
					{
					if (head.x + 1 < 60 && noBodyAt(head.x + 1, head.y) && noBodyAt2(head.x + 1, head.y))
					{
					head = new Point(head.x +1, head.y);
					if (keypress == true) {
						last_direction = 'R';
						keypress = false;
					}
					}
					else
					{
						dead = true;
						winner = 'R';
					}
					}
				else if (last_direction == 'L')
				{
					if (head.y - 1 >= 0 && noBodyAt(head.x - 1, head.y) && noBodyAt2(head.x - 1, head.y))
					{
					head = new Point(head.x, head.y -1);
					if (keypress == true) {
					last_direction = 'U';
					keypress = false;
					}
				}
					else
					{
						dead = true;
						winner = 'R';
					}
				}
			}
		
			
			/*Tron2*/
			if (direction2 == LEFT)
			{
				if (last_direction2 == 'D')
				{
					if (head2.x + 1 < 60 && noBodyAt(head2.x + 1, head2.y) && noBodyAt2(head2.x + 1, head2.y))
						
				{
					head2 = new Point(head2.x + 1, head2.y);
					if (keypress2 == true)
					{	last_direction2 = 'R';
						keypress2 = false;
				}
				}
					else
					{
						dead = true;
						winner = 'B';
					}
				}
				else if (last_direction2 == 'R')
				{
					if (head2.y - 1 >= 0 && noBodyAt(head2.x - 1, head2.y) && noBodyAt2(head2.x - 1, head2.y))
					{
					head2 = new Point(head2.x, head2.y -1);
					if (keypress2 == true)
					{last_direction2 = 'U';
					keypress2 = false;
					}
				}
					else
					{
						dead = true;
						winner = 'B';
					}
				}
				else if (last_direction2 == 'U')
					{
					if (head2.x - 1 >= 0 && noBodyAt(head2.x - 1, head2.y) && noBodyAt2(head2.x - 1, head2.y))
					{
					head2 = new Point(head2.x -1, head2.y);
					if (keypress2 == true) {
						last_direction2 = 'L';
						keypress2 = false;
					}
					}
					else
					{
						dead = true;
						winner = 'B';
					}
					}
				else if (last_direction2 == 'L')
				{
					if (head2.y + 1 < 38 && noBodyAt(head2.x, head2.y + 1) && noBodyAt2(head2.x, head2.y + 1))
					{
					head2 = new Point(head2.x, head2.y +1);
					if (keypress2 == true) {
					last_direction2 = 'D';
					keypress2 = false;
					}
				}
					else
					{
						dead = true;
						winner = 'B';
					}
			}
			}

			if (direction2 == RIGHT)
			{
				if (last_direction2 == 'D')
				{
					if (head2.x - 1 >= 0 && noBodyAt(head2.x - 1, head2.y) && noBodyAt2(head2.x - 1, head2.y))
					{
					head2 = new Point(head2.x - 1, head2.y);
					if (keypress2 == true)
					{	last_direction2 = 'L';
						keypress2 = false;
				}
					}
					else
					{
						dead = true;
						winner = 'B';
					}
				}
				else if (last_direction2 == 'R')
				{
					if (head2.y + 1 < 38 && noBodyAt(head2.x, head2.y + 1) && noBodyAt2(head2.x, head2.y + 1))
					{
					head2 = new Point(head2.x, head2.y +1);
					if (keypress2 == true)
					{last_direction2 = 'D';
					keypress2 = false;
					}
					}
					else
					{
						dead = true;
						winner = 'B';
					}
				}
				else if (last_direction2 == 'U')
					{
					if (head2.x + 1 < 60 && noBodyAt(head2.x + 1, head2.y) && noBodyAt2(head2.x + 1, head2.y))
					{
					head2 = new Point(head2.x +1, head2.y);
					if (keypress2 == true) {
						last_direction2 = 'R';
						keypress2 = false;
					}
					}
					else
					{
						dead = true;
						winner = 'B';
					}
					}
				else if (last_direction2 == 'L')
				{
					if (head2.y - 1 >= 0 && noBodyAt(head2.x - 1, head2.y) && noBodyAt2(head2.x - 1, head2.y))
					{
					head2 = new Point(head2.x, head2.y -1);
					if (keypress2 == true) {
					last_direction2 = 'U';
					keypress2 = false;
					}
				}
					else
					{
						dead = true;
						winner = 'B';
					}
				}
			}
			
		}
	}
/**
 * 
 * @param x
 * 			abscissa coordinate (x)
 * @param y
 * 			ordinate coordinate (y)
 * @return
 * 			return a boolean who tells if the lightcycle has already passed by the indicated coordinate (for the blue user)
 */
	public boolean noBodyAt(int x, int y)
	{
		for (Point point : tronBody)
		{
			if (point.equals(new Point(x, y)))
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param x
	 * 			abscissa coordinate (x)
	 * @param y
	 * 			ordinate coordinate (y)
	 * @return
	 * 			return a boolean who tells if the lightcycle has already passed by the indicated coordinate (for the red user)
	 */
	public boolean noBodyAt2(int x, int y)
	{
		for (Point point : tronBody2)
		{
			if (point.equals(new Point(x, y)))
			{
				return false;
			}
		}
		return true;
	}
	

	@Override
	/**
	 * The following code is intended to detect keyboard movements
	 */
	public void keyPressed(KeyEvent e)
	{
		int i = e.getKeyCode();

		/*Tron1*/
		if (i == KeyEvent.VK_LEFT)
		{
			direction = LEFT;
			keypress = true;
			if (last_key == 'R') {
				if (last_direction == 'D') {
					last_direction = 'U';
				}
				else if (last_direction == 'U') {
					last_direction = 'D';
				}
				else if (last_direction == 'L') {
					last_direction = 'R';
				}
				else if (last_direction == 'R') {
					last_direction = 'L';
				}
					
				}
			last_key = 'L';
			}
						
		

		if (i == KeyEvent.VK_RIGHT)
		{
			direction = RIGHT;
			keypress = true;
			if (last_key == 'L') {
				if (last_direction == 'D') {
					last_direction = 'U';
				}
				else if (last_direction == 'U') {
					last_direction = 'D';
				}
				else if (last_direction == 'L') {
					last_direction = 'R';
				}
				else if (last_direction == 'R') {
					last_direction = 'L';
				}
					
				}
			last_key = 'R';
		}

		/*Tron2*/
		if (i == KeyEvent.VK_Q)
		{
			direction2 = LEFT;
			keypress2 = true;
			if (last_key2 == 'R') {
				if (last_direction2 == 'D') {
					last_direction2 = 'U';
				}
				else if (last_direction2 == 'U') {
					last_direction2 = 'D';
				}
				else if (last_direction2 == 'L') {
					last_direction2 = 'R';
				}
				else if (last_direction2 == 'R') {
					last_direction2 = 'L';
				}
					
				}
			last_key2 = 'L';
			}
						
		

		if (i == KeyEvent.VK_D)
		{
			direction2 = RIGHT;
			keypress2 = true;
			if (last_key2 == 'L') {
				if (last_direction2 == 'D') {
					last_direction2 = 'U';
				}
				else if (last_direction2 == 'U') {
					last_direction2 = 'D';
				}
				else if (last_direction2 == 'L') {
					last_direction2 = 'R';
				}
				else if (last_direction2 == 'R') {
					last_direction2 = 'L';
				}
					
				}
			last_key2 = 'R';
		}
		

		if (i == KeyEvent.VK_SPACE)
		{
			paused = !paused;
		}
	}
	

	@Override
	public void keyReleased(KeyEvent e)
	{
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

}
