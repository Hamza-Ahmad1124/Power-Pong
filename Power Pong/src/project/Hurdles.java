package project;

import java.awt.Color;
import java.awt.Graphics;

public class Hurdles 
{
	public int x , y , width = 50 , height = 50 ;
	public int hurdleNumber ;
	
	
	public Hurdles(Pong pong , int hurdleNumber)
	{
		this.hurdleNumber = hurdleNumber ;  //"this"is mainly to refer. as in this case there are two separate integers of same name "paddleNumber" are declared so as we use "this"(it clears the program that the first integer named paddleNumber is being discussed)
		
		if(hurdleNumber == 1)
		{
			if ((pong.gameStatus == 2) || (pong.gameStatus == 3)||(pong.gameStatus == 5)||(pong.gameStatus == 6)||(pong.gameStatus == 7)||(pong.gameStatus == 8))
			{
				this.y = pong.height/2 - 10;
				this.x = (pong.width/2) - 300;
			}
			else 
			{
				this.x = (pong.width/2) - 300;
				this.y = pong.height/2 + 130;
			}
		}
				
		if(hurdleNumber == 2)
		{
			if ((pong.gameStatus == 2) || (pong.gameStatus == 3)||(pong.gameStatus == 5)||(pong.gameStatus == 6)||(pong.gameStatus == 7)||(pong.gameStatus == 8))
			{
				this.x = pong.width/2 + 250;  
				this.y = pong.height/2 -10 ;
			}
			
			else
			{
				this.x = pong.width/2 + 250;  
				this.y = pong.height/2 + 130 ;
			}
		}
		
		if(hurdleNumber == 3)
		{
			this.x = pong.width/2 + 250;  
			this.y = pong.height/2 - 170 ;
		}
		
		if(hurdleNumber == 4)
		{
			this.x = (pong.width/2) - 300;
			this.y = pong.height/2 - 170;
		}
		
		if(hurdleNumber == 5)
		{
			this.x = pong.width/2 + 85;  
			this.y = pong.height/2 - 20 ;
			
		}
		
		if(hurdleNumber == 6)
		{
			this.y = pong.height/2 - 20;
			this.x = (pong.width/2) - 130;
		}
	}
	
	public void render(Graphics g) 
	{
		g.setColor(Color.CYAN);         
		g.fillRect(x, y, width, height );		
	}
}
