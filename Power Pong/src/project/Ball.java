package project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Random;

public class Ball
{
	public Random random ;   // This Facilitates in generating random values of any data type we want.
	private Pong pong ;   //  Creation of Reference Variable which is same as an object.
	Graphics g ;
	public int x , y , width = 15 , height = 15 ;  // This x and y can be referred like coordinates
	public int motionX , motionY ;
	public int amountOfHits ;
	public int speed = 5 ;
	
	public static int hitScore1; // in public variable used in different method their copies are made but when declared static they all share the same int 
	public static int hitScore2;
	public static int repeaterBonus = 1 ;
	public static int speedIncrease = 1 ;
	public static int ran = 0 ;   // This variable is used to start the start of the Score bonus. 
	public static int rand = 0 ;   // This variable is used to start the start of the Life bonus.
	public static int ran1 = -5 ; 
	public static int ran2 = -5 ;
	
	/* we have put this -5 because due to some unKnown reason As the collision expresion is executed for the collision of the 
	 * bonus with the paddle the expression is continuously executed 6 times and the hitScore gets increased by 6 point
	 * So in order to minimize it i have initialized the value by -5 
	 */
	
	public Ball(Pong pong)
	{
		this.pong = pong ;
		this.random = new Random();	
		ballSpawn();	
	}
	
	public void ballupdate1(Paddle paddle1 , Paddle paddle2 , Hurdles hurdle1 , Hurdles hurdle2 )  // This is in my guess for the collision detection.
	{
		
		this.x += motionX * speed ;   // these lines
		this.y += motionY * speed ;   //              Determine the speed of ball 
		
		if(this.y + height - motionY + 40  > pong.height || this.y + motionY - 45 < 0)    // these lines defines the collision with the walls
		{
			
			if (this.motionY  < 0)
			{
				this.y = 45 ;
				this.motionY = 1 ;                   //random.nextInt(4);  // this declares 4 as a maximum value and random values are generated smaller than it
			}
			else
			{	
				this.motionY = -1 ;                  //   -random.nextInt(4);
				this.y = pong.height - height - 40  ;  
			}
		}
														// The following lines describe the collision with the paddles
		if (checkCollision(paddle1) == 1)
		{
			Toolkit.getDefaultToolkit().beep();     // For the beep
			this.motionX = speedIncrease + (amountOfHits / 6 );   // This increases the speed of the ball by every 4 hits.
			this.motionY = - 2 + random.nextInt(4);    // This determines the orientation of the ball as it hits the paddle
			
			if(motionY == 0)
			{
				motionY = 1 ; 
			}
			amountOfHits++ ;
			hitScore1++;
			repeaterBonus ++ ;
		}
		
		else if (checkCollision(paddle2) == 1)
		{
			Toolkit.getDefaultToolkit().beep();     // For the beep
			this.motionX = - speedIncrease - (amountOfHits / 6);   // This increases the speed of the ball by every 4 hits.
			this.motionY = - 2 + random.nextInt(4);    // This determines the orientation of the ball as it hits the paddle
			
			if(motionY == 0)
			{
				motionY = 1 ; 
			}
			amountOfHits++ ;
			hitScore2 ++ ;
			repeaterBonus ++ ;
		}
		
		if (x + width > hurdle1.x && x < hurdle1.x + hurdle1.width)
		{
			if (y + height > hurdle1.y && y< hurdle1.y + hurdle1.height)
			{
				reverseDirection();
			}
		}
	
		if (x + width > hurdle2.x && x  < hurdle2.x + hurdle2.width)
		{
			if (y + height > hurdle2.y && y < hurdle2.y + hurdle2.height)
			{
				reverseDirection();
			}
		}
		
		if (checkCollision(paddle1) == 2)
		{
			paddle1.life -- ;
			ballSpawn();
		}	
		
		else if (checkCollision(paddle2) == 2)
		{
			paddle2.life -- ;
			ballSpawn();
		}
		
		if (repeaterBonus == 6) // Controls the starting of the bonus
		{
			ran = 1 ;
		}
		if (repeaterBonus == 15) // Controls the starting of the bonus
		{
			rand = 1 ;
		}
	}
	
	public void ballupdate2(Paddle paddle1 , Paddle paddle2 , Hurdles hurdle1 , Hurdles hurdle2 , Hurdles hurdle3 , Hurdles hurdle4 )  // This is in my guess for the collision detection.
	{
		
		this.x += motionX * speed ;   // these lines
		this.y += motionY * speed ;   //              Determine the speed of ball 
		
		if(this.y + height - motionY + 40  > pong.height || this.y + motionY - 45 < 0)    // these lines defines the collision with the walls
		{
			
			if (this.motionY  < 0)
			{
				this.y = 45 ;
				this.motionY = 1 ;                   //random.nextInt(4);  // this declares 4 as a maximum value and random values are generated smaller than it
			}
			else
			{	
				this.motionY = -1 ;                  //   -random.nextInt(4);
				this.y = pong.height - height - 40  ;  
			}
		}
														// The following lines describe the collision with the paddles
		if (checkCollision(paddle1) == 1)
		{
			Toolkit.getDefaultToolkit().beep();     // For the beep
			this.motionX = speedIncrease + (amountOfHits / 6 );   // This increases the speed of the ball by every 4 hits.
			this.motionY = - 2 + random.nextInt(4);    // This determines the orientation of the ball as it hits the paddle
			
			if(motionY == 0)
			{
				motionY = 1 ; 
			}
			amountOfHits++ ;
			hitScore1++;
			repeaterBonus ++ ;
		}
		
		else if (checkCollision(paddle2) == 1)
		{
			Toolkit.getDefaultToolkit().beep();     // For the beep
			this.motionX = - speedIncrease - (amountOfHits / 6);   // This increases the speed of the ball by every 4 hits.
			this.motionY = - 2 + random.nextInt(4);    // This determines the orientation of the ball as it hits the paddle
			
			if(motionY == 0)
			{
				motionY = 1 ; 
			}
			amountOfHits++ ;
			hitScore2 ++ ;
			repeaterBonus ++ ;
		}
		
		if (x + width > hurdle1.x && x < hurdle1.x + hurdle1.width)
		{
			if (y + height > hurdle1.y && y < hurdle1.y + hurdle1.height)
			{
				reverseDirection();
			}
		}
	
		if (x  + width > hurdle2.x && x < hurdle2.x + hurdle2.width)
		{
			if (y + height > hurdle2.y && y < hurdle2.y + hurdle2.height)
			{
				reverseDirection();
			}
		}
		
		if (x + width > hurdle3.x && x < hurdle3.x + hurdle3.width)
		{
			if (y + height > hurdle3.y && y < hurdle3.y + hurdle3.height)
			{
				reverseDirection();
			}
		}
		if (x + width > hurdle4.x && x < hurdle4.x + hurdle4.width)
		{
			if (y + height > hurdle4.y && y < hurdle4.y + hurdle4.height)
			{
				reverseDirection();
			}
		}
		
		if (checkCollision(paddle1) == 2)
		{
			paddle1.life -- ;
			ballSpawn();
		}	
		
		else if (checkCollision(paddle2) == 2)
		{
			paddle2.life -- ;
			ballSpawn();
		}
		
		if (repeaterBonus == 6) // Controls the starting of the bonus
		{
			ran = 1 ;
		}
		if (repeaterBonus == 15) // Controls the starting of the bonus
		{
			rand = 1 ;
		}
	}
	
	public void ballupdate3(Paddle paddle1 , Paddle paddle2 , Hurdles hurdle1 , Hurdles hurdle2 , Hurdles hurdle3 , Hurdles hurdle4 , Hurdles hurdle5 , Hurdles hurdle6)  // This is in my guess for the collision detection.
	{
		this.x += motionX * speed ;   // these lines
		this.y += motionY * speed ;   //              Determine the speed of ball 
		
		if(this.y + height - motionY + 40  > pong.height || this.y + motionY - 45 < 0)    // these lines defines the collision with the walls
		{
			
			if (this.motionY  < 0)
			{
				this.y = 45 ;
				this.motionY = 1 ;                   //random.nextInt(4);  // this declares 4 as a maximum value and random values are generated smaller than it
			}
			else
			{	
				this.motionY = -1 ;                  //   -random.nextInt(4);
				this.y = pong.height - height - 40  ;  
			}
		}
														// The following lines describe the collision with the paddles
		if (checkCollision(paddle1) == 1)
		{
			Toolkit.getDefaultToolkit().beep();     // For the beep
			this.motionX = speedIncrease + (amountOfHits / 6 );   // This increases the speed of the ball by every 4 hits.
			this.motionY = - 2 + random.nextInt(4);    // This determines the orientation of the ball as it hits the paddle
			
			if(motionY == 0)
			{
				motionY = 1 ; 
			}
			amountOfHits++ ;
			hitScore1++;
			repeaterBonus ++ ;
		}
		
		else if (checkCollision(paddle2) == 1)
		{
			Toolkit.getDefaultToolkit().beep();     // For the beep
			this.motionX = - speedIncrease - (amountOfHits / 6);   // This increases the speed of the ball by every 4 hits.
			this.motionY = - 2 + random.nextInt(4);    // This determines the orientation of the ball as it hits the paddle
			
			if(motionY == 0)
			{
				motionY = 1 ; 
			}
			amountOfHits++ ;
			hitScore2 ++ ;
			repeaterBonus ++ ;
		}
		
		if (x + width > hurdle1.x && x < hurdle1.x + hurdle1.width)
		{
			if (y + height > hurdle1.y && y < hurdle1.y + hurdle1.height)
			{
				reverseDirection();
			}
		}
	
		if (x + width > hurdle2.x && x < hurdle2.x + hurdle2.width)
		{
			if (y + height > hurdle2.y && y < hurdle2.y + hurdle2.height)
			{
				reverseDirection();
			}
		}
		if (x + width > hurdle3.x && x < hurdle3.x + hurdle3.width)
		{
			if (y + height > hurdle3.y && y < hurdle3.y + hurdle3.height)
			{
				reverseDirection();
			}
		}
		if (x + width > hurdle4.x && x < hurdle4.x + hurdle4.width)
		{
			if (y + height > hurdle4.y && y < hurdle4.y + hurdle4.height)
			{
				reverseDirection();
			}
		}
		if (x + width > hurdle5.x && x < hurdle5.x + hurdle5.width)
		{
			if (y + height > hurdle5.y && y < hurdle5.y + hurdle5.height)
			{
				reverseDirection();
			}
		}
		if (x + width > hurdle6.x && x < hurdle6.x + hurdle6.width)
		{
			if (y + height > hurdle6.y && y < hurdle6.y + hurdle6.height)
			{
				reverseDirection();
			}
		}
		
		if (checkCollision(paddle1) == 2)
		{
			paddle1.life -- ;
			ballSpawn();
		}	
		
		else if (checkCollision(paddle2) == 2)
		{
			paddle2.life -- ;
			ballSpawn();
		}
		
		if (repeaterBonus == 6) // Controls the starting of the bonus
		{
			ran = 1 ;
		}
		if (repeaterBonus == 15) // Controls the starting of the bonus
		{
			rand = 1 ;
		}
	}
	
	
	public void ballupdate4(Paddle paddle1, Paddle paddle2, Paddle paddle3, Paddle paddle4, Hurdles hurdle1, Hurdles hurdle2) 
	{	
		this.x += motionX * speed ;   // these lines
		this.y += motionY * speed ;   //              Determine the speed of ball 
		
		
		if(this.y + height - motionY + 40  > pong.height || this.y + motionY - 45 < 0)    // these lines defines the collision with the walls
		{
			
			if (this.motionY  < 0)
			{
				this.y = 45 ;
				paddle1.life--;
				ballSpawn();
			}
			else
			{	
				this.y = pong.height - height - 40  ;
				paddle2.life--;
				ballSpawn();
			}
		}
														// The following lines describe the collision with the paddles
		if (checkCollision(paddle1) == 1)
		{
			Toolkit.getDefaultToolkit().beep();     // For the beep
			this.motionX = speedIncrease + (amountOfHits / 6 );   // This increases the speed of the ball by every 4 hits.
			this.motionY = - 2 + random.nextInt(4);    // This determines the orientation of the ball as it hits the paddle
			
			if(motionY == 0)
			{
				motionY = 1 ; 
			}
			amountOfHits++ ;
			hitScore1++;
			repeaterBonus ++ ;
		}
		
		else if (checkCollision(paddle2) == 1)
		{
			Toolkit.getDefaultToolkit().beep();     // For the beep
			this.motionX = - speedIncrease - (amountOfHits / 6);   // This increases the speed of the ball by every 4 hits.
			this.motionY = - 2 + random.nextInt(4);    // This determines the orientation of the ball as it hits the paddle
			
			if(motionY == 0)
			{
				motionY = 1 ; 
			}
			amountOfHits++ ;
			hitScore2 ++ ;
			repeaterBonus ++ ;
		}
		else if (checkCollision2(paddle3) == 1)
		{
			Toolkit.getDefaultToolkit().beep();     // For the beep
			this.motionX =  - 2 + random.nextInt(4) ;   // This increases the speed of the ball by every 4 hits.
			this.motionY = + speedIncrease + (amountOfHits / 6); ;    // This determines the orientation of the ball as it hits the paddle
			
			if(motionX == 0)
			{
				motionX = 1 ; 
			}
			hitScore2++;
			repeaterBonus ++ ;
		}
		else if (checkCollision2(paddle4) == 1)
		{
			Toolkit.getDefaultToolkit().beep();     // For the beep
			this.motionY = - speedIncrease - (amountOfHits / 6);   // This increases the speed of the ball by every 4 hits.
			this.motionX = - 2 + random.nextInt(4);    // This determines the orientation of the ball as it hits the paddle
			
			if(motionX == 0)
			{
				motionX = 1 ; 
			}
			amountOfHits++ ;
			hitScore1++;
			repeaterBonus ++ ;
		}
		
		if (x + width > hurdle1.x && x < hurdle1.x + hurdle1.width)
		{
			if (y + height > hurdle1.y && y < hurdle1.y + hurdle1.height)
			{
				reverseDirection();
			}
		}
	
		if (x + width > hurdle2.x && x < hurdle2.x + hurdle2.width)
		{
			if (y + height > hurdle2.y && y < hurdle2.y + hurdle2.height)
			{
				reverseDirection();
			}
		}
		
		if (checkCollision(paddle1) == 2)
		{
			paddle2.life -- ;
			ballSpawn();
		}	
		
		else if (checkCollision(paddle2) == 2)
		{
			paddle1.life -- ;
			ballSpawn();
		}
		
		if (repeaterBonus == 6) // Controls the starting of the bonus
		{
			ran = 1 ;
		}
		if (repeaterBonus == 15) // Controls the starting of the bonus
		{
			rand = 1 ;
		}
	}

	public void ballupdate5(Paddle paddle1, Paddle paddle2, Paddle paddle3, Paddle paddle4, Hurdles hurdle1, Hurdles hurdle2, Hurdles hurdle3, Hurdles hurdle4) 
	{
		this.x += motionX * speed ;   // these lines
		this.y += motionY * speed ;   //              Determine the speed of ball 
		
		
		if(this.y + height - motionY + 40  > pong.height || this.y + motionY - 45 < 0)    // these lines defines the collision with the walls
		{
			
			if (this.motionY  < 0)
			{
				this.y = 45 ;
				paddle1.life--;
				ballSpawn();
			}
			else
			{	
				this.y = pong.height - height - 40  ;
				paddle2.life--;
				ballSpawn();
			}
		}
														// The following lines describe the collision with the paddles
		if (checkCollision(paddle1) == 1)
		{
			Toolkit.getDefaultToolkit().beep();     // For the beep
			this.motionX = speedIncrease + (amountOfHits / 6 );   // This increases the speed of the ball by every 4 hits.
			this.motionY = - 2 + random.nextInt(4);    // This determines the orientation of the ball as it hits the paddle
			
			if(motionY == 0)
			{
				motionY = 1 ; 
			}
			amountOfHits++ ;
			hitScore1++;
			repeaterBonus ++ ;
		}
		
		else if (checkCollision(paddle2) == 1)
		{
			Toolkit.getDefaultToolkit().beep();     // For the beep
			this.motionX = - speedIncrease - (amountOfHits / 6);   // This increases the speed of the ball by every 4 hits.
			this.motionY = - 2 + random.nextInt(4);    // This determines the orientation of the ball as it hits the paddle
			
			if(motionY == 0)
			{
				motionY = 1 ; 
			}
			amountOfHits++ ;
			hitScore2 ++ ;
			repeaterBonus ++ ;
		}
		else if (checkCollision2(paddle3) == 1)
		{
			Toolkit.getDefaultToolkit().beep();     // For the beep
			this.motionX =  - 2 + random.nextInt(4) ;   // This increases the speed of the ball by every 4 hits.
			this.motionY = + speedIncrease + (amountOfHits / 6); ;    // This determines the orientation of the ball as it hits the paddle
			
			if(motionX == 0)
			{
				motionX = 1 ; 
			}
//			motionY = - motionY ;
//			motionX = - motionX ;
			
			hitScore2++;
			repeaterBonus ++ ;
		}
		else if (checkCollision2(paddle4) == 1)
		{
			Toolkit.getDefaultToolkit().beep();     // For the beep
			this.motionY = - speedIncrease - (amountOfHits / 6);   // This increases the speed of the ball by every 4 hits.
			this.motionX = - 2 + random.nextInt(4);    // This determines the orientation of the ball as it hits the paddle
			
			if(motionX == 0)
			{
				motionX = 1 ; 
			}
//			amountOfHits++ ;
//			motionY = - motionY ;
//			motionX = - motionX ;
			hitScore1++;
			repeaterBonus ++ ;
		}
		
		if (x + width > hurdle1.x && x < hurdle1.x + hurdle1.width)
		{
			if (y + height > hurdle1.y && y < hurdle1.y + hurdle1.height)
			{
				reverseDirection();
			}
		}
	
		if (x + width > hurdle2.x && x < hurdle2.x + hurdle2.width)
		{
			if (y + height > hurdle2.y && y < hurdle2.y + hurdle2.height)
			{
				reverseDirection();
			}
		}
		if (x + width > hurdle3.x && x < hurdle3.x + hurdle3.width)
		{
			if (y + height > hurdle3.y && y < hurdle3.y + hurdle3.height)
			{
				reverseDirection();
			}
		}
		if (x + width > hurdle4.x && x < hurdle4.x + hurdle4.width)
		{
			if (y + height > hurdle4.y && y < hurdle4.y + hurdle4.height)
			{
				reverseDirection();
			}
		}
		
		if (checkCollision(paddle1) == 2)
		{
			paddle2.life -- ;
			ballSpawn();
		}	
		
		else if (checkCollision(paddle2) == 2)
		{
			paddle1.life -- ;
			ballSpawn();
		}
		
		if (repeaterBonus == 6) // Controls the starting of the bonus
		{
			ran = 1 ;
		}
		if (repeaterBonus == 15) // Controls the starting of the bonus
		{
			rand = 1 ;
		}
	}
	

	public void ballupdate6(Paddle paddle1, Paddle paddle2, Paddle paddle3, Paddle paddle4, Hurdles hurdle1, Hurdles hurdle2 , Hurdles hurdle3, Hurdles hurdle4, Hurdles hurdle5, Hurdles hurdle6) 
	{
		this.x += motionX * speed ;   // these lines
		this.y += motionY * speed ;   //              Determine the speed of ball 
		
		
		if(this.y + height - motionY + 40  > pong.height || this.y + motionY - 45 < 0)    // these lines defines the collision with the walls
		{
			
			if (this.motionY  < 0)
			{
				this.y = 45 ;
				paddle1.life--;
				ballSpawn();
			}
			else
			{	
				this.y = pong.height - height - 40  ;
				paddle2.life--;
				ballSpawn();
			}
		}
														// The following lines describe the collision with the paddles
		if (checkCollision(paddle1) == 1)
		{
			Toolkit.getDefaultToolkit().beep();     // For the beep
			this.motionX = speedIncrease + (amountOfHits / 6 );   // This increases the speed of the ball by every 4 hits.
			this.motionY = - 2 + random.nextInt(4);    // This determines the orientation of the ball as it hits the paddle
			
			if(motionY == 0)
			{
				motionY = 1 ; 
			}
			amountOfHits++ ;
			hitScore1++;
			repeaterBonus ++ ;
		}
		
		else if (checkCollision(paddle2) == 1)
		{
			Toolkit.getDefaultToolkit().beep();     // For the beep
			this.motionX = - speedIncrease - (amountOfHits / 6);   // This increases the speed of the ball by every 4 hits.
			this.motionY = - 2 + random.nextInt(4);    // This determines the orientation of the ball as it hits the paddle
			
			if(motionY == 0)
			{
				motionY = 1 ; 
			}
			amountOfHits++ ;
			hitScore2 ++ ;
			repeaterBonus ++ ;
		}
		else if (checkCollision2(paddle3) == 1)
		{
			Toolkit.getDefaultToolkit().beep();     // For the beep
			this.motionX =  - 2 + random.nextInt(4) ;   // This increases the speed of the ball by every 4 hits.
			this.motionY = + speedIncrease + (amountOfHits / 6); ;    // This determines the orientation of the ball as it hits the paddle
			
			if(motionX == 0)
			{
				motionX = 1 ; 
			}
			hitScore2++;
			repeaterBonus ++ ;
		}
		else if (checkCollision2(paddle4) == 1)
		{
			Toolkit.getDefaultToolkit().beep();     // For the beep
			this.motionY = - speedIncrease - (amountOfHits / 6);   // This increases the speed of the ball by every 4 hits.
			this.motionX = - 2 + random.nextInt(4);    // This determines the orientation of the ball as it hits the paddle
			
			if(motionX == 0)
			{
				motionX = 1 ; 
			}
			amountOfHits++ ;
			hitScore1++;
			repeaterBonus ++ ;
		}
		
		if (x + width > hurdle1.x && x < hurdle1.x + hurdle1.width)
		{
			if (y + height > hurdle1.y && y < hurdle1.y + hurdle1.height)
			{
				reverseDirection();
			}
		}
	
		if (x + width > hurdle2.x && x < hurdle2.x + hurdle2.width)
		{
			if (y + height > hurdle2.y && y < hurdle2.y + hurdle2.height)
			{
				reverseDirection();
			}
		}
		if (x + width > hurdle3.x && x < hurdle3.x + hurdle3.width)
		{
			if (y + height > hurdle3.y && y < hurdle3.y + hurdle3.height)
			{
				reverseDirection();
			}
		}
		if (x + width > hurdle4.x && x < hurdle4.x + hurdle4.width)
		{
			if (y + height > hurdle4.y && y < hurdle4.y + hurdle4.height)
			{
				reverseDirection();
			}
		}
		if (x + width > hurdle5.x && x < hurdle5.x + hurdle5.width)
		{
			if (y + height > hurdle5.y && y < hurdle5.y + hurdle5.height)
			{
				reverseDirection();
			}
		}
		if (x + width > hurdle6.x && x < hurdle6.x + hurdle6.width)
		{
			if (y + height > hurdle6.y && y < hurdle6.y + hurdle6.height)
			{
				reverseDirection();
			}
		}
		
		if (checkCollision(paddle1) == 2)
		{
			paddle2.life -- ;
			ballSpawn();
		}	
		
		else if (checkCollision(paddle2) == 2)
		{
			paddle1.life -- ;
			ballSpawn();
		}
		
		if (repeaterBonus == 6) // Controls the starting of the bonus
		{
			ran = 1 ;
		}
		if (repeaterBonus == 15) // Controls the starting of the bonus
		{
			rand = 1 ;
		}
	}
	
	public int checkCollision(Paddle paddle)  // Method  // these line determine the detection of hit of the ball with the Paddle and determining of the life 
	{
		if(((this.x < paddle.x + paddle.width) && (this.x + width > paddle.x)) && ((this.y < paddle.y + paddle.height) && (this.y + height > paddle.y)))
		{
			return 1;   // bounce
		}
		
		else if (((paddle.x > x) && (paddle.paddleNumber == 1)) ||  ((paddle.x < x - width )&& (paddle.paddleNumber == 2)))
		{
			return 2 ; // Score
		}
		
		return 0; // Nothing	
	}
	
	public int checkCollision2(Paddle paddle)  // Method  // these line determine the detection of hit of the ball with the Paddle and determining of the life 
	{
		if(((this.y < paddle.y2 + paddle.height2) && (this.y + height > paddle.y2))&&((this.x < paddle.x2 + paddle.width2) && (this.x + width > paddle.x2)))
		{
			return 1;   // bounce
		}
		
		return 0; // Nothing	
	}
	
	
	public void reverseDirection() 
	{
		motionX = - motionX ;    
		motionY = - motionY ;
		
		if (motionY >= 0 && motionY <= 9)
		{
			motionY = motionY + random.nextInt(3);
		}
		
		else  //if (motionY <= 0 && motionY >= -9)
		{
			motionY =  motionY - random.nextInt(3);
		}
	}
		
	public void bonus1update(Paddle paddle1, Paddle paddle2, Hurdles hurdle1, Hurdles hurdle2) 
	{
		
		this.x += motionX * speed ;   // these lines
		this.y += motionY * speed ;   //              Determine the speed of ball 
		
		if(this.y + height - motionY + 40  > pong.height || this.y + motionY - 45 < 0)    // these lines defines the collision with the walls
		{
			if (this.motionY  < 0)
			{
				this.y = 45 ;
				//this.motionY = -1 ;                   //random.nextInt(4);  // this declares 4 as a maximum value and random values are generated smaller than it
			}
			
			else
			{	
				//this.motionY = 1 ;                  //   -random.nextInt(4);
				this.y = pong.height - height - 40  ;  
			}
			
			motionX = 1 ;
			
			if (motionY > 0 )
			{
				motionY = - 1 - random.nextInt(2) ;
			}
			
			else
			{				
				motionY = 1 + random.nextInt(2) ;
			}
		}
														// The following lines describe the collision of bonus with the paddles
		if(((this.x < paddle1.x + paddle1.width) && (this.x + width > paddle1.x)) && ((this.y < paddle1.y + paddle1.height) && (this.y + height > paddle1.y)))//if (checkCollision(paddle1) == 1)
		{
			ran1 = ran1 + 1 ;
		}
		
		if(((this.x < paddle2.x + paddle2.width) && (this.x + width > paddle2.x)) && ((this.y < paddle2.y + paddle2.height) && (this.y + height > paddle2.y)))  //if (checkCollision(paddle2) == 1)
		{	
			ran2 = ran2 + 1 ;
		}
		
		if (ran1 == 1)
		{
			hitScore1 ++ ;
			ran1 = -5 ;
		}
		
		if (ran2 == 1)
		{
			hitScore2 ++ ;
			ran2 = -5 ;	
		}
		scoreSpawn1();
	}
	
	public void bonus2update(Paddle paddle1, Paddle paddle2, Hurdles hurdle1, Hurdles hurdle2) 
	{
		
		this.x += motionX * speed ;   // these lines
		this.y += motionY * speed ;   //              Determine the speed of ball 
		
		if(this.y + height - motionY + 40  > pong.height || this.y + motionY - 45 < 0)    // these lines defines the collision with the walls
		{
			if (this.motionY  < 0)
			{
				this.y = 45 ;
				//this.motionY = 1 ;                   //random.nextInt(4);  // this declares 4 as a maximum value and random values are generated smaller than it
			}
			
			else
			{	
				//this.motionY = -1 ;                  //   -random.nextInt(4);
				this.y = pong.height - height - 40  ;  
			}
			motionX = -1 ;
			
			if (motionY > 0 )
			{
				motionY = - 1 -random.nextInt(2);
			}
			else
			{
				motionY = 1 + random.nextInt(2) ;				
			}		
		}
														// The following lines describe the collision of the bonus with the paddles
		if(((this.x < paddle1.x + paddle1.width) && (this.x + width > paddle1.x)) && ((this.y < paddle1.y + paddle1.height) && (this.y + height > paddle1.y)))//if (checkCollision(paddle1) == 1)
		{
			ran1 = ran1 + 1 ;
		}
		
		if(((this.x < paddle2.x + paddle2.width) && (this.x + width > paddle2.x)) && ((this.y < paddle2.y + paddle2.height) && (this.y + height > paddle2.y)))  //if (checkCollision(paddle2) == 1)
		{	
			ran2 = ran2 + 1 ;
		}
	
		if (ran1 == 1)
		{
			hitScore1 ++ ;
			ran1 = -5 ;
		}
		
		if (ran2 == 1)
		{
			hitScore2 ++ ;
			ran2 = -5 ;
		}
		
		scoreSpawn2();
	}
	
	public void bonus1update2(Paddle paddle1, Paddle paddle2, Paddle paddle3, Paddle paddle4, Hurdles hurdle1, Hurdles hurdle2) 
	{
		this.x += motionX * speed ;   // these lines
		this.y += motionY * speed ;   //              Determine the speed of ball 
		
		motionX = 1 ;
		motionY = 0;
														// The following lines describe the collision of bonus with the paddles
//		if((((this.x < paddle1.x + paddle1.width) && (this.x + width > paddle1.x)) && ((this.y < paddle1.y + paddle1.height) && (this.y + height > paddle1.y))))//||(((this.x < paddle4.x2 + paddle4.width2) && (this.x + width > paddle4.x2)) && ((this.y < paddle4.y2 + paddle4.height2) && (this.y + height > paddle4.y2))))
//		{
//			ran1 = ran1 + 1 ;
//		}
		
		if((((this.x < paddle2.x + paddle2.width) && (this.x + width > paddle2.x)) && ((this.y < paddle2.y + paddle2.height) && (this.y + height > paddle2.y))))//||(((this.x < paddle3.x2 + paddle3.width2) && (this.x + width > paddle3.x2)) && ((this.y < paddle3.y2 + paddle3.height2) && (this.y + height > paddle3.y2))))
		{	
			 ran2 = ran2 + 1 ;
		}
		 
//		if (ran1 == 1)
//		{
//			hitScore1 ++ ;
//			ran1 = -5 ;
//		}
		
		if (ran2 == 1)
		{
			hitScore2 ++ ;
			ran2 = -5 ;	
		}
		
		scoreSpawn2();
	}
	
	public void bonus2update2(Paddle paddle1, Paddle paddle2, Paddle paddle3, Paddle paddle4, Hurdles hurdle1, Hurdles hurdle2) 
	{

		this.x += motionX * speed ;   // these lines
		this.y += motionY * speed ;   //              Determine the speed of ball 
		
		motionX = -1 ;
		motionY = 0;
		
		if((((this.x < paddle1.x + paddle1.width) && (this.x + width > paddle1.x)) && ((this.y < paddle1.y + paddle1.height) && (this.y + height > paddle1.y))))//||(((this.x < paddle4.x2 + paddle4.width2) && (this.x + width > paddle4.x2)) && ((this.y < paddle4.y2 + paddle4.height2) && (this.y + height > paddle4.y2))))
		{
			ran1 = ran1 + 1 ;
		}
		
//		if((((this.x < paddle2.x + paddle2.width) && (this.x + width > paddle2.x)) && ((this.y < paddle2.y + paddle2.height) && (this.y + height > paddle2.y))))//||(((this.x < paddle3.x2 + paddle3.width2) && (this.x + width > paddle3.x2)) && ((this.y < paddle3.y2 + paddle3.height2) && (this.y + height > paddle3.y2))))
//		{	
//			ran2 = ran2 + 1 ;
//		}
		
		if (ran1 == 1)
		{
			hitScore1 ++ ;
			ran1 = -5 ;
		}
			
//		if (ran2 == 1)
//		{
//			hitScore2 ++ ;
//			ran2 = -5 ;	
//		}
		
		scoreSpawn1();
	}
	
	public void lifebonus1update1(Paddle paddle1, Paddle paddle2, Hurdles hurdle1, Hurdles hurdle2) 
	{
		this.x += motionX * speed ;   // these lines
		this.y += motionY * speed ;   //              Determine the speed of ball 
		
		if(this.y + height - motionY + 40  > pong.height || this.y + motionY - 45 < 0)    // these lines defines the collision with the walls
		{
			if (this.motionY  < 0)
			{
				this.y = 45 ;
				//this.motionY = 1 ;                   //random.nextInt(4);  // this declares 4 as a maximum value and random values are generated smaller than it
			}
			
			else
			{	
				//this.motionY = -1 ;                  //   -random.nextInt(4);
				this.y = pong.height - height - 40  ;  
			}
			motionX = -1 ;
			
			if (motionY > 0 )
			{
				motionY = - 1 - random.nextInt(2);
			}
			else
			{
				motionY = 1 + random.nextInt(2) ;				
			}		
		}
														// The following lines describe the collision of bonus with the paddles
		if(((this.x < paddle1.x + paddle1.width) && (this.x + width > paddle1.x)) && ((this.y < paddle1.y + paddle1.height) && (this.y + height > paddle1.y)))//if (checkCollision(paddle1) == 1)
		{
			ran1 = ran1 + 1 ;
		}
		
		if ((ran1 == 1))
		{
			paddle1.life ++ ;
			ran1 = -5 ;
		}
		
		lifeSpawn1();
	}
	
	public void lifebonus2update1(Paddle paddle1, Paddle paddle2, Hurdles hurdle1, Hurdles hurdle2) 
	{
		this.x += motionX * speed ;   // these lines
		this.y += motionY * speed ;   //              Determine the speed of ball 
		
		if(this.y + height - motionY + 40  > pong.height || this.y + motionY - 45 < 0)    // these lines defines the collision with the walls
		{
			if (this.motionY  < 0)
			{
				this.y = 45 ;
				//this.motionY = -1 ;                   //random.nextInt(4);  // this declares 4 as a maximum value and random values are generated smaller than it
			}
			
			else
			{	
				//this.motionY = 1 ;                  //   -random.nextInt(4);
				this.y = pong.height - height - 40  ;  
			}
			
			motionX = 1 ;
			
			if (motionY > 0 )
			{
				motionY = - 1 - random.nextInt(2) ;
			}
			
			else
			{				
				motionY = 1 + random.nextInt(2) ;
			}
		}
														// The following lines describe the collision of the bonus with the paddles
		if(((this.x < paddle2.x + paddle2.width) && (this.x + width > paddle2.x)) && ((this.y < paddle2.y + paddle2.height) && (this.y + height > paddle2.y)))  //if (checkCollision(paddle2) == 1)
		{	
			ran2 = ran2 + 1 ;
		}
		
		if ((ran2 == 1))
		{
			paddle2.life ++ ;
			ran2 = -5 ;
		}
		
		lifeSpawn2();
	}
	
	public void lifebonus1update2(Paddle paddle1, Paddle paddle2, Paddle paddle3, Paddle paddle4, Hurdles hurdle1, Hurdles hurdle2) 
	{
		this.x += motionX * speed ;   // these lines
		this.y += motionY * speed ;   //              Determine the speed of ball 
		
		motionX = 1 ;
		motionY = 0;
														// The following lines describe the collision of bonus with the paddles
//		if((((this.x < paddle1.x + paddle1.width) && (this.x + width > paddle1.x)) && ((this.y < paddle1.y + paddle1.height) && (this.y + height > paddle1.y))))//||(((this.x < paddle4.x2 + paddle4.width2) && (this.x + width > paddle4.x2)) && ((this.y < paddle4.y2 + paddle4.height2) && (this.y + height > paddle4.y2))))
//		{
//			ran1 = ran1 + 1 ;
//		}
		
		 if((((this.x < paddle2.x + paddle2.width) && (this.x + width > paddle2.x)) && ((this.y < paddle2.y + paddle2.height) && (this.y + height > paddle2.y))))//||(((this.x < paddle3.x2 + paddle3.width2) && (this.x + width > paddle3.x2)) && ((this.y < paddle3.y2 + paddle3.height2) && (this.y + height > paddle3.y2))))
		{	
			 ran2 = ran2 + 1 ;
		}
		 
//		if (ran1 == 1)
//		{
//			paddle1.life ++ ;
//			ran1 = -5 ;
//		}
		
		if (ran2 == 1)
		{
			paddle2.life ++ ;
			ran2 = -5 ;	
		}
		
		lifeSpawn2();
	}
	
	public void lifebonus2update2(Paddle paddle1, Paddle paddle2, Paddle paddle3, Paddle paddle4, Hurdles hurdle1, Hurdles hurdle2) 
	{
		this.x += motionX * speed ;   // these lines
		this.y += motionY * speed ;   //              Determine the speed of ball 
		
		motionX = -1 ;
		motionY = 0;
		
		if((((this.x < paddle1.x + paddle1.width) && (this.x + width > paddle1.x)) && ((this.y < paddle1.y + paddle1.height) && (this.y + height > paddle1.y))))
		{
			ran1 = ran1 + 1 ;
		}
		
//		if((((this.x < paddle2.x + paddle2.width) && (this.x + width > paddle2.x)) && ((this.y < paddle2.y + paddle2.height) && (this.y + height > paddle2.y))))
//		{	
//			ran2 = ran2 + 1 ;
//		}
		 
		 if (ran1 == 1)
		{
			paddle1.life ++ ;
			ran1 = -5 ;
		}
			
//		if (ran2 == 1)
//		{
//			hitScore2 ++ ;
//			ran2 = -5 ;	
//		}

		lifeSpawn1();
	}
	
	public void ballSpawn()
	{
		this.amountOfHits = 0 ;
		
		this.x = pong.width / 2 - this.width / 2 ;   // This line and the next Line Clearly puts the ball in the very middle of the Screen
		this.y = pong.height / 2 - this.height / 2 ;
		
		this.motionY = -2 + random.nextInt(4);
		
		if(motionY == 0)
		{
			motionY = 1 ; 
		}
		
		if(random.nextBoolean())  // This condition randomly to move from its initial point to the right or left side of the frame depending upon the random boolean value
		{
			motionX = 1 ;  // in this condition the ball will start moving from its initial point to the left direction
		} 
		else
		{
			motionX = -1 ;  // in this condition the ball will start moving from its initial point to the right direction
		}
	}
	
	public void scoreSpawn1()
	{	
		if (repeaterBonus % 6 == 0)//((hitScore1 % 5 == 0)&&(hitScore2 % 5 == 0))
		{
			this.x = pong.width / 2 - this.width / 2 ;   // This line and the next Line Clearly puts the ball in the very middle of the Screen
			this.y = pong.height / 2 - this.height / 2 ;
		}
	}
	
	public void scoreSpawn2()
	{
		if  (repeaterBonus % 6 == 0)//((hitScore1 % 5 == 0)&&(hitScore2 % 5 == 0))
		{
			this.x = pong.width / 2 - this.width / 2 ;   // This line and the next Line Clearly puts the ball in the very middle of the Screen
			this.y = pong.height / 2 - this.height / 2 ;
		}
	}
	
	public void lifeSpawn1()
	{	
		if (repeaterBonus % 15 == 0)//((hitScore1 % 5 == 0)&&(hitScore2 % 5 == 0))
		{
			this.x = pong.width / 2 - this.width / 2 ;   // This line and the next Line Clearly puts the ball in the very middle of the Screen
			this.y = pong.height / 2 - this.height / 2 ;
		}
	}
		
	public void lifeSpawn2()
	{
		if  (repeaterBonus % 15 == 0)//((hitScore1 % 5 == 0)&&(hitScore2 % 5 == 0))
		{
			this.x = pong.width / 2 - this.width / 2 ;   // This line and the next Line Clearly puts the ball in the very middle of the Screen
			this.y = pong.height / 2 - this.height / 2 ;
		}
	}

	public void renderer(Graphics g)
	{
		g.setColor(Color.MAGENTA);  // this specifies a color to be filled in the  ball
		g.fillOval(x , y , width , height); // This line creates an filled oval in given sizes. The oval is filled by the color declared above. 
	}
	
	public void bonusrenderer(Graphics g)
	{
		g.setColor(Color.ORANGE);  // this specifies a color to be filled in the  ball
		g.fillOval(x , y , width +10 , height); // This line creates an filled oval in given sizes. The oval is filled by the color declared above. 
	}
	
	public void lifebonusrenderer(Graphics g)
	{
		g.setColor(Color.GREEN);  // this specifies a color to be filled in the  ball
		g.fillOval(x , y , width +10 , height); // This line creates an filled oval in given sizes. The oval is filled by the color declared above. 
	}
}