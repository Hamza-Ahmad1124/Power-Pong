package project;

import java.awt.Color;
import java.awt.Graphics2D;

public class Paddle 
{
	public int paddleNumber ;
	public int x , y , width = 15 , height = 125 ;   // this value of width and height controls the actual height and width of the paddles
	public int x2 , y2 , width2 = 300 , height2 = 15 ;
	public int life = Pong.pong.lifeLimit ;
	public int speed = 20 ;
	public int speed2 = 30 ;
	
	public Paddle(Pong pong , int paddleNumber)
	{
		this.paddleNumber = paddleNumber ;  //"this"is mainly to refer. as in this case there are two separate integers of same name "paddleNumber" are declared so as we use "this"(it clars the program that the first integer named paddleNumber is being discussed)
		
		if(paddleNumber == 1)
		{
			this.x = 0 ; 
			this.y = pong.height / 2 - this.height / 2 ;
		}
		
		if(paddleNumber == 2)
		{
			this.x = pong.width - width ;  //This line specifies that the second paddle would be at  the right most side of the screen	  
			this.y = pong. height / 2 - this.height / 2 ;
		}
		
		if (paddleNumber == 3)
		{
			this.x2 = Pong.pong.width / 2 - width2 /2 ;
			this.y2 = 46;
		}
		if (paddleNumber == 4)
		{
			this.x2 = Pong.pong.width / 2 - width2 /2;
			this.y2 = pong.height - this.height2 - 40 ;
		}
	}
	
	public void moveUpDown(boolean up) 
	{	
		if(up)
		{
			if(y - speed > 45)         
			{
				y -= speed;   // if i replace speed by 0 than the paddle only moves in the downwards direction.
			}
			else 
			{
				y = 45 ;
			}
		}
		else
		{
			if(y + height + speed + 40 < Pong.pong.height)
			{
				y += speed;   // if i replace speed by 0 than the paddle only moves in the upwards direction.
			}
			else 
			{
				y = Pong.pong.height - height - 40 ;
			}
		}
	}
	
	public void moveRightLeft1(boolean d) 
	{
		if(d)
		{
			if(x2 + width2 + speed2 < Pong.pong.width - width)
			{
				x2 += speed2;   // if i replace speed by 0 than the paddle only moves in the upwards direction.
			}
			else 
			{
				x2 = Pong.pong.width - width2 - width ;
			}
		}
		else
		{
			if(x2 - speed2 > width)         
			{
				x2 -= speed2;   // if i replace speed by 0 than the paddle only moves in the downwards direction.
			}
			else 
			{
				x2 = width ;
			}
		}
	}
	
	public void moveRightLeft2(boolean right) 
	{
		if(right)
		{
			if(x2 + width2 + speed2 < Pong.pong.width - width)
			{
				x2 += speed2;   // if i replace speed by 0 than the paddle only moves in the upwards direction.
			}
			else 
			{
				x2 = Pong.pong.width - width2 - width ;
			}
		}
		else
		{
			if(x2 - speed2 > width)         
			{
				x2 -= speed2;   // if i replace speed by 0 than the paddle only moves in the downwards direction.
			}
			else 
			{
				x2 = width ;
			}
		}
	}
	public void render(Graphics2D g) 
	{
		g.setColor(Color.BLUE);   
		g.fillRect(x, y, width, height );	
	}

	public void renderDouble(Graphics2D g) 
	{
		g.setColor(Color.RED);   
		g.fillRect(x2, y2, width2, height2 );	
	}
}