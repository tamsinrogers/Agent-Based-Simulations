/**
 * File: SocialAgent.java
 * Author: Tamsin Rogers
 * Date: 3/10/20
 */
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;

public class SocialAgent extends Agent 
{
	boolean moved;																				// indicates whether the agent moved during the last updateState
	int r;																						// the radius r between neighboring agents
	
	public SocialAgent(double x0, double y0, int radius) 
	{
		super(x0, y0);																			// call the super class constructor
		r = radius;																				// set the radius field
	}
	public void setRadius(int radius)
	{
		r = radius;																				// set the cell's radius of sensitivity to the value of the given radius
	}		
	public int getRadius()
	{
		return r;																				// return the cell's radius of sensitivity
	}
	public void draw(Graphics g)
	{
		int sX = (int)(x+0.5);
		int sY = (int)(y+0.5);
		
		if(moved)																				// if the agent moved during the last updateState
		{
			g.setColor(Color.cyan);																// set the color of the oval to light blue
		}
		else																					// if the agent did not move during the last updateState
		{
			g.setColor(Color.blue);																// set the color of the oval to dark blue
		}
		g.fillOval(sX, sY, 20,20);																// fill the circle in
		g.drawOval(sX, sY, 20,20);																// draw the circle
	}
	
	public void updateState(Landscape scape)
	{
		moved = false;
		Random ran = new Random();
		int neighbors = scape.getNeighbors(this.getX(), this.getY(), this.getRadius()).size();	// the number of neighbors that the social agent has

		if(neighbors > 3)																		// if the social agent has more than 3 neighbors
		{
			moved = false;
			if(ran.nextInt(100)<1)																// execute with a 1% chance
			{												
				this.x += ran.nextDouble()*20-10;												// randomly change the x position
				this.y += ran.nextDouble()*20-10;												// randomly change the y position
				moved = true;	
			}
		}
		else
		{
			this.x += ran.nextDouble()*20-10;													// randomly change the x position
			this.y += ran.nextDouble()*20-10;													// randomly change the y position
			moved = true;
		}
	}
	
	public static void main(String[] args) 
	{
		SocialAgent social = new SocialAgent(5,10,5);											// create a new social agent at 5,10 with a radius of 5
		System.out.println("getX: " + social.getX());											// print the social agent's current x position
		System.out.println("getY: " + social.getY());											// print the social agent's current y position
		social.setX(7);																			// set the social agent's x position to 7
		System.out.println("set x to 7: " + social.getX());										// get the social agent's current x position
		social.setY(3);																			// set the social agent's y position to 3
		System.out.println("set y to 3: " + social.getY());										// get the social agent's current y position
		System.out.println(social.toString());													// print the social agent's location using the toString method
		System.out.println("radius: " + social.getRadius());									// print the social agent's radius
		social.setRadius(4);																	// set the social agent's radius to 4
		System.out.println("new radius: " + social.getRadius());								// print the social agent's radius
		social.setRadius(5);																	// set the social agent's radius back to 5
		System.out.println("radius: " + social.getRadius());									// print the social agent's radius
	
		Landscape scape = new Landscape(100,50);												// new landscape of width 100, height 50
		SocialAgent agent1 = new SocialAgent(5,5,25);											// create a new agent at this location
		scape.addAgent(agent1);																	// add the agent to the landscape
		agent1.updateState(scape);
		
		SocialAgent agent2 = new SocialAgent(10,10,25);											// create a new agent at this location
		scape.addAgent(agent2);																	// add the agent to the landscape
		SocialAgent agent3 = new SocialAgent(15,15,25);											// create a new agent at this location
		scape.addAgent(agent3);																	// add the agent to the landscape
		SocialAgent agent4 = new SocialAgent(20,20,20);											// create a new agent at this location
		scape.addAgent(agent4);																	// add the agent to the landscape
		
		System.out.println("neighbors within a radius of 10 (should be 1): " + scape.getNeighbors(5,5,10));
		System.out.println("neighbors within a radius of 15 (should be 2): " + scape.getNeighbors(5,5,15));
		System.out.println("neighbors within a radius of 25 (should be 3): " + scape.getNeighbors(5,5,25));
	
	}
}