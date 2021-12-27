/**
 * File: Agent.java
 * Author: Tamsin Rogers
 * Date: 3/10/20
 */
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;

public class Agent
{
	double x;														// the agent's x position
	double y;														// the agent's y position
	
	/* a constructor that sets the position */
	public Agent(double x0, double y0) 
	{
		x = x0;
		y = y0;
	}
	/* returns the x position */
	public double getX() 
	{
		return x;
	}
	/* returns the y position */
	public double getY()
	{
		return y;
	}
	/* sets the x position */
	public void setX(double newX) 
	{
		x = newX;
	}
	/* sets the y position */
	public void setY(double newY)
	{
		y = newY;
	}
	/* returns a String containing the x and y positions */
	public String toString()
	{
		return "(" + Double.toString(x) + ", " + Double.toString(y) + ")";
	}

	public void updateState(Landscape scape)
	{
	
	}

	public void draw(Graphics g)
	{
	
	}
	public static void main(String[] args) 
	{
		Agent agent = new Agent(10,5);							// create a new agent at 10,5
		System.out.println("getX: " + agent.getX());			// print the agent's current x position
		System.out.println("getY: " + agent.getY());			// print the agent's current y position
		agent.setX(3);											// set the agent's x position to 3
		System.out.println("set x to 3: " + agent.getX());		// get the agent's current x position
		agent.setY(7);											// set the agent's y position to 7
		System.out.println("set y to 7: " + agent.getY());		// get the agent's current y position
		System.out.println(agent.toString());					// print the agent's location using the toString method
	}
}