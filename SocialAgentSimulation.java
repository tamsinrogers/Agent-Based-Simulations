/**
 * File: SocialAgentSimulation.java
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

public class SocialAgentSimulation
{
	private Landscape scape;
	private LinkedList<SocialAgent> agentList;																// initialize the linkedlist of agents

	public static void main(String[] args) throws InterruptedException 
	{
    	System.out.println("Enter the width of the Landscape: ");
    	Scanner scan1 = new Scanner(System.in);
		int width = scan1.nextInt();																		// set width to the user input value
		
		System.out.println("Enter the height of the Landscape: ");
    	Scanner scan2 = new Scanner(System.in);
		int height = scan2.nextInt();																		// set height to the user input value
		
		System.out.println("Enter the number of agents: ");
    	Scanner scan3 = new Scanner(System.in);
		int N = scan3.nextInt();																			// set the number of agents (N) to the user input value
		
		System.out.println("Enter the radius of sensitivity: ");
    	Scanner scan4 = new Scanner(System.in);
		int rs = scan4.nextInt();
		
		System.out.println("Enter the number of simulation iterations: ");
    	Scanner scan5 = new Scanner(System.in);
		int iterations = scan5.nextInt();																	// set radius to the user input value
		
		Scanner inputTime = new Scanner(System.in);						
        System.out.print("Enter the number of milliseconds to pause for between frames: ");
        int time = inputTime.nextInt();																		// set time to the user input value
        inputTime.close();
		
		Landscape scape = new Landscape(width, height);
		LandscapeDisplay display = new LandscapeDisplay(scape);
		
		Random ran = new Random();
		
		LinkedList<SocialAgent> agentList = new LinkedList<SocialAgent>();
		
		// create the agents and add them to the landscape
		for(int i=0; i<(N+1); i++)																			// loop N times
		{
			SocialAgent agent = new SocialAgent((ran.nextInt(width+1)), (ran.nextInt(height+1)), 20);		// generate an agent
			agent.setRadius(rs);
			scape.addAgent(agent);	
			agent.updateState(scape);	
		}
		
		for(int i=0; i<(iterations+1); i++)																			
        {						
        	System.out.println("iteration: " + i);																	
			scape.updateAgents();
			display.repaint();
			Thread.sleep(time);
		}
	}
}