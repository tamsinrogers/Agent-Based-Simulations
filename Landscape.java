/**
 * File: Landscape.java
 * Author: Tamsin Rogers
 * Date: 3/10/20
 */
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;

public class Landscape
{
	int width;												// initialize the width
	int height;												// initialize the height
	
	LinkedList<Agent> agentList;							// initialize the linkedlist of agents
	
	/* constructor that sets the width and height fields, and initializes the agent list */
	public Landscape(int w, int h)
	{
		width = w;
		height = h;
		agentList = new LinkedList<Agent>();
	}
	
	/* returns the height */
	public int getHeight()
	{
		return height;
	}
	
	/* returns the width */
	public int getWidth()
	{
		return width;
	}
	
	/* inserts an agent at the beginning of its list of agents */
	public void addAgent(Agent a)
	{
		agentList.addFirst(a);								// add agent a to the beginning of the list
	}
	
	/* returns a String representing the Landscape, can be as simple as indicating the number of Agents on the Landscape */
	public String toString() 
	{
		int size = agentList.size();
		String s = Integer.toString(size);
		return s;
	}
	
	/* returns a list of the Agents within radius distance of the location x0, y0 */
	public ArrayList<Agent> getNeighbors(double x0, double y0, double radius)
	{
	
	ArrayList<Agent> neighbors = new ArrayList<Agent>();	// initialize the neighbors list
	
	double ax;												// the current agent's x position
	double ay;												// the current agent's y position
	double distance;										// initialize the distance variable
	
	for(Agent a: this.agentList)
	{
		ax = a.getX();
		ay = a.getY();
		distance = Math.sqrt((ay-y0)*(ay-y0)+(ax-x0)*(ax-x0));
		
		if(distance<radius)									// if the current agent's location is within the radius
		{
			neighbors.add(a);								// add the current agent to the neighbors list
		}
	}
		return neighbors;
	}
	
	/* calls the draw method of all the agents on the Landscape */
	public void draw(Graphics g)
	{
		for(Agent a: this.agentList)
		{
			a.draw(g);
		}
	}
	
	/* updates the state of each agent in a random order */
	public void updateAgents()
	{
		ArrayList<Agent> shuffled;
		shuffled = this.agentList.toShuffledList();			// get the ArrayList of agents in a randomized order
		
		for(Agent a: shuffled)								// for each agent in the shuffled list
		{
			a.updateState(this);							// call each agent's updateState method
		}
	}
	
	public static void main(String[] args) 
	{
		Landscape scape = new Landscape(100,50);			// new landscape of width 100, height 50
		Agent agent1 = new Agent(5,5);						// create a new agent at this location
		scape.addAgent(agent1);								// add the agent to the landscape
		agent1.updateState(scape);
		
		Agent agent2 = new Agent(10,10);					// create a new agent at this location
		scape.addAgent(agent2);								// add the agent to the landscape
		Agent agent3 = new Agent(15,15);					// create a new agent at this location
		scape.addAgent(agent3);								// add the agent to the landscape
		Agent agent4 = new Agent(20,20);					// create a new agent at this location
		scape.addAgent(agent4);								// add the agent to the landscape
		
		System.out.println("neighbors within a radius of 10 (should be 1): " + scape.getNeighbors(5,5,10));
		System.out.println("neighbors within a radius of 15 (should be 2): " + scape.getNeighbors(5,5,15));
		System.out.println("neighbors within a radius of 25 (should be 3): " + scape.getNeighbors(5,5,25));
		}
}