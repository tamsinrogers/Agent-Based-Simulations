/**
 * File: CatSocialAgent.java
 * Author: Tamsin Rogers
 * Date: 3/10/20
 */
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Scanner; 
import java.util.*; 

public class CatSocialAgent extends SocialAgent
{
	private int category;
	
	/* calls the parent constructor and sets the category */
	public CatSocialAgent(double x0, double y0, int cat)
	{
		super(x0, y0, 5);																// call the parent constructor													
		category = cat;																	// set the category
	}
	
	/* returns the category value */
	public int getCategory()
	{
		return category;
	}

	/* returns a single character string indicating the category */
	public String toString()
	{
		return Integer.toString(category);
	}
	
	/* draws a circle of radius 5 at the Agent's location
		different categories have different colors
		each category has a darker color (didn't move) and lighter color (moved) */
	public void draw(Graphics g)
	{
		int cX = (int)(x+0.5);
		int cY = (int)(y+0.5);
		
		if(moved)																		// if the agent moved during the last updateState
		{
			if(this.getCategory() == 1)
			{
				g.setColor(Color.lightGray);											// set the color of the oval to light blue
			}
			else if(this.getCategory() == 2)
			{
				g.setColor(Color.pink);
			}
			else
			{
				return;
			}
		}
		else	
		{																							// if the agent did not move during the last updateState
		if(this.getCategory() == 1)
			{
				g.setColor(Color.black);															// set the color of the oval to light blue
			}
			else if(this.getCategory() == 2)
			{
				g.setColor(Color.red);
			}
			else
			{
				return;
			}
		}
		g.fillOval(cX, cY, 5, 5);																	// fill the circle in
		g.drawOval(cX, cY, 5, 5);																	// draw a circle of radius 5
	}
	
	/* identifies how many neighbors within the cell's radius have the same category 
	and how many have a different category using this.getCategory() */
	public void updateState(Landscape scape)
	{
		moved = false;
		Random ran = new Random();
		int numNeighbors = scape.getNeighbors(this.getX(), this.getY(), this.getRadius()).size();	// the number of neighbors that the social agent has

		ArrayList<Agent> neighbors = scape.getNeighbors(this.getX(),this.getY(),getRadius());

		int sameCategory = 0;
		int differentCategory = 0;
		
		for(Agent neighbor: neighbors)
		{
			if(((CatSocialAgent)neighbor).getCategory() == this.getCategory())
			{
				sameCategory++;
			}
			else
			{
				differentCategory++;
			}
		}

		if(numNeighbors > 3 && sameCategory>differentCategory)										// if the social agent has more than 3 neighbors
		{
			moved = false;
			if(ran.nextInt(100)<1)																	// execute with a 1% chance
			{												
				this.x += ran.nextDouble()*20-10;													// randomly change the x position
				this.y += ran.nextDouble()*20-10;													// randomly change the y position
				moved = true;	
			}
		}
		else
		{
			this.x += ran.nextDouble()*20-10;														// randomly change the x position
			this.y += ran.nextDouble()*20-10;														// randomly change the y position
			moved = true;
		}
	} 

}