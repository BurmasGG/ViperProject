package GameEngine.Util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {
	
	public static int mouseX = -1;
	public static int mouseY = -1;
	public static int mouseB = -1;
	
	public static int getX()
	{
		return mouseX;
	}
	
	public static int getY()
	{
		return mouseY;
	}
	
	public static int getB()
	{
		return mouseB;
	}

	public void mouseClicked(MouseEvent e) 
	{
		
	}

	public void mouseEntered(MouseEvent e) 
	{
		
	}

	public void mouseExited(MouseEvent e) 
	{
		
	}

	public void mousePressed(MouseEvent e) 
	{
		mouseB = e.getButton();
	}

	public void mouseReleased(MouseEvent e) 
	{
		mouseB = -1;
	}

	public void mouseDragged(MouseEvent e) 
	{
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseMoved(MouseEvent e) 
	{
		mouseX = e.getX();
		mouseY = e.getY();
	}

}
