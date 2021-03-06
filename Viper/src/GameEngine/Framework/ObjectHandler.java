package GameEngine.Framework;

import java.awt.Graphics;
import java.util.LinkedList;
import GameEngine.SuperEntities.GameObject;
import GameEngine.SuperEntities.Projectile;

public class ObjectHandler 
{
	public static LinkedList<GameObject> object = new LinkedList<GameObject>();
	public static LinkedList<Projectile> bullet = new LinkedList<Projectile>();
	
	public ObjectHandler()
	{
		 
	}
	
	public void update()
	{
		for(int i = 0; i < object.size(); i++)
		{
			GameObject o = object.get(i);
			o.update();
		}
		
		for(int i = 0; i < bullet.size(); i++)
		{
			Projectile p = bullet.get(i);
			p.update();
		}
	}
	
	public void render(Graphics g)
	{
		for(int i = 0; i < object.size(); i++)
		{
			GameObject o = object.get(i);
			o.render(g);
		}
		
		for(int i = 0; i < bullet.size(); i++)
		{
			Projectile p = bullet.get(i);
			p.render(g);
		}
	}
	
	public static void addObject(GameObject o)
	{
		object.add(o);
	}
	
	public static void removeObject(GameObject o)
	{
		object.remove(o);
	}
	
	public static void addBullet(Projectile p)
	{
		bullet.add(p);
	}
	
	public static void removeBullet(Projectile p)
	{
		bullet.remove(p);
	}
	
	public static LinkedList<GameObject> getObjectList() {
		return object;
	}
	
	public static LinkedList<Projectile> getBulletList() {
		return bullet;
	}
}
