package project;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Pong implements ActionListener , KeyListener  // We are making our class an ActionListener. ActionListener Is an interface.
{
	public static Pong pong;   //This is called Static best check it.
	
	public Renderer renderer;  //  Created Reference Variable of class Renderer.
	
	public int width = 1350;
	public int height = 700;
	public static int botDifficulty ;
	public int botMoves ;
	public int botCooldown = 0 ;
	public int playerWon ;	
	public static double referenceGameStatus = -1 ;
	public static double gameStatus ;
	public static double reference ;
	
   /* 0 = Menu ,  1 = Paused , 4 = Over/Result , -1 -  Warning
	* 2 - playing with multi player with 2 hurdles and start speed of 1
	* 2.1 - playing with multi player with 4 hurdles and start speed of 2
	* 2.2 - playing with multi player with 6 hurdles and start speed of 3
	* 3 - playing with single player with 2 hurdles and start speed of 1
	* 3.1 - playing with single player with 4 hurdles and start speed of 2
	* 3.2 - playing with single player with 6 hurdles and start speed of 3
	* 5 - playing with multi player with 4 paddle players with 2 hurdles and start speed of 1
	* 5.1 - playing with multi player with 4 paddle players with 4 hurdles and start speed of 2
	* 5.2 - playing with multi player with 4 paddle players with 6 hurdles and start speed of 3
	* 6 - playing with single player with 4 paddle players with 2 hurdles and start speed of 1
	* 6.1 - playing with single player with 4 paddle players with 4 hurdles and start speed of 2
	* 6.2 - playing with single player with 4 paddle players with 6 hurdles and start speed of 3
	* 7 - playing with Complete AI Bot Control with 2 paddle players with 2 hurdles and start speed of 1
	* 7.1 - playing with Complete AI Bot Control with 2 paddle players with 4 hurdles and start speed of 2
	* 7.2 - playing with Complete AI Bot Control with 2 paddle players with 6 hurdles and start speed of 3
	* 8 - playing with Complete AI Bot Control with 4 paddle players with 2 hurdles and start speed of 1
	* 8.1 - playing with Complete AI Bot Control with 4 paddle players with 4 hurdles and start speed of 2
	* 8.2 - playing with Complete AI Bot Control with 4 paddle players with 6 hurdles and start speed of 3
	*/ 
	
	public  int lifeLimit = 1;
	public static int X ;
	public static int ran = 0 ;
	
	public static JFrame jframe;
	public Random random ;
	
	public Paddle player1;
	public Paddle player2;
	public Paddle player3 ;
	public Paddle player4 ;
	public Ball ball ;
	public Ball scoreBonus ;
	public Ball scoreBonus2 ;
	public Ball lifeBonus1;
	public Ball lifeBonus2;
	public Hurdles hurdle1 ;
	public Hurdles hurdle2 ;
	public Hurdles hurdle3 ;
	public Hurdles hurdle4 ;
	public Hurdles hurdle5 ;
	public Hurdles hurdle6 ;
	public CoverPage CP;
	public static boolean bot = false ;
	public boolean w , s , up , down ;
	public boolean a , d , left , right ;
	public boolean selectingDifficulty = false;
	
	
	
	public Pong()  //This is a constructor . //This is a non Static method. Static method can be called directly .non static method needs a object to be created to be called
	{
		Timer timer = new Timer(20 , this);  
		/*The Timer(); constructor needs two Arguments.the first argument is an integer which is used to give value in MILLISECONDS .
		 * The Second argument is the the movement of control when the timer finishes so to deal with this  we need to Use ACTIONLISTENER.
		 * the second argument states "  this  "  As it now refers or in other meanings it is trying to say that after the timer goes out the control should go or remain in Pong() class As this class is an ActionListener.
		 * As the timer goes off it calls the method of ActionListener Named actionPerformed to execute.
		 * And ActionListener needs ActionEvent. Which is the action that needs to be performed as ActionLidtener is needed.
		 * We use timer to make a pause between the game loop before the game again repaints and clears all it components.
		 * timer can also be referred to as the buffer Rate Or the REfresh Rate.
		 */
		
		random = new Random();
		jframe = new JFrame("Pong");   // We dont use extends JFrame After the name of the class in this programe as we initialized the jframe in this line . And if we didnt initialized it in this line then we would have to use extends JFrame after the class.
		
		renderer = new Renderer();   //Creating An Object of Renderer Class
		
		jframe.setSize(width + 16 , height + 38);  // We are adding these values into the width and height in a JFrame there are borderes and the title bar comes between or inside this size so the ball would merge into the title bar and then collide the actual boundary of the frame which is the boundry of the title bar and then reflect back.
		
		jframe.setVisible(true);
		
		
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //we use this to set the default close option on cross button so it stops the execution when cross button is pressed
		jframe.add(renderer);    // This Adds A new component to our JFrame window  Which in this case is the Object named renderer of the class Renderer(); .. 
		jframe.addKeyListener(this);  // The " this " argument specifies is referring to current big class that is public class Pong(), As it also implements Keylistener And after it KeyPressed and key released methods are invoked

		timer.start();  // This is to start the timer // we have to put start before it as timer.start(); calls actionPerformed and it then calls many other things	
	}
	
	public void start()
	{
		System.out.println("3");

		lifeBonus1 = new Ball (this);
		lifeBonus2 = new Ball (this);
		
		scoreBonus = new Ball(this);
		scoreBonus2 = new Ball(this);
		
		player1 = new Paddle(this , 1);
		player2 = new Paddle(this , 2);
		ball = new Ball(this);
		hurdle1 = new Hurdles(this , 1);
		hurdle2 = new Hurdles(this , 2);
		
		if(gameStatus == 2.1 || gameStatus == 3.1 || gameStatus == 7.1)
		{
			hurdle3 = new Hurdles(this , 3);
			hurdle4 = new Hurdles(this , 4);
		}
		if(gameStatus == 2.2 || gameStatus == 3.2 || gameStatus == 7.2)
		{
			hurdle3 = new Hurdles(this , 3);
			hurdle4 = new Hurdles(this , 4);
			hurdle5 = new Hurdles(this , 5);
			hurdle6 = new Hurdles(this , 6);
		}
		
		if((gameStatus == 5)||(gameStatus == 6)||(gameStatus == 8))
		{
			player3 = new Paddle(this , 3);
			player4 = new Paddle(this , 4);
		}
		if((gameStatus == 5.1)||(gameStatus == 6.1)||(gameStatus == 8.1))
		{
			player3 = new Paddle(this , 3);
			player4 = new Paddle(this , 4);
			hurdle3 = new Hurdles(this , 3);
			hurdle4 = new Hurdles(this , 4);
		}
		if((gameStatus == 5.2)||(gameStatus == 6.2)||(gameStatus == 8.2))
		{
			player3 = new Paddle(this , 3);
			player4 = new Paddle(this , 4);
			hurdle3 = new Hurdles(this , 3);
			hurdle4 = new Hurdles(this , 4);
			hurdle5 = new Hurdles(this , 5);
			hurdle6 = new Hurdles(this , 6);
		}
		
	}
	
	public void update()			//These following lines controls the movement of the paddles // This is Just A user defined class
	{		System.out.println("2");

		if (player1.life < 1 && gameStatus == 8.2)
		{
			playerWon = 2 ;
			gameStatus = 1.3 ;
		}
		
		else if (player2.life < 1 && gameStatus == 8.2)
		{
			playerWon = 1 ;
			gameStatus = 1.3 ;
		}
		
		else if (player1.life < 1 && gameStatus == 6.2)
		{
			playerWon = 2 ;
			gameStatus = 1.2 ;
		}
		
		else if (player2.life < 1 && gameStatus == 6.2)
		{
			playerWon = 1 ;
			gameStatus = 1.2 ;
		}
		
		else if (player1.life < 1)
		{
			playerWon = 2 ;
			gameStatus = 4 ;
		}
		
		else if (player2.life < 1)
		{
			playerWon = 1 ;
			gameStatus = 4 ;
		}
		
		if  (!((gameStatus == 7)||(gameStatus == 7.1)||(gameStatus == 7.2)||(gameStatus == 8)||(gameStatus == 8.1)||(gameStatus == 8.2)))
		{
			if (w)   // This defines the functionality of key w when pressed in key listener
			{
				player1.moveUpDown(true);
			}
			
			if (s)
			{
				player1.moveUpDown(false);
			}
			
			if ((gameStatus == 5)||(gameStatus == 5.1)||(gameStatus == 5.2)||(gameStatus == 6)||(gameStatus == 6.1)||(gameStatus == 6.2))
			{
				if(d)
				{
					player4.moveRightLeft1(true);
				}
				if(a)
				{
					player4.moveRightLeft1(false);
				}
			}
			
			if ((gameStatus == 2)||(gameStatus == 2.1)||(gameStatus == 2.2))
			{
				if (up)
				{
					player2.moveUpDown(true);
				}
				
				if (down)
				{
					player2.moveUpDown(false);
				}
			}
			
			if ((gameStatus == 5)||(gameStatus == 5.1)||(gameStatus == 5.2))
			{
				if (up)
				{
					player2.moveUpDown(true);
				}
				
				if (down)
				{
					player2.moveUpDown(false);
				}
				if(right)
				{
					player3.moveRightLeft2(true);
				}
				if(left)
				{
					player3.moveRightLeft2(false);
				}
			}
		}
		
		if (bot)   // these lines controls the movement of the AI Bot
		{
			if (botCooldown > 0)
			{
				botCooldown -- ;
				
				if(botCooldown == 0)
				{
					botMoves = 0 ;
				}
			}
			
			if (botMoves < 100)
			{
				if ((gameStatus == 3)||(gameStatus == 3.1)||(gameStatus == 3.2))
				{
					if (player2.y + player2.height /2 < ball.y)  // This Control the up and down movement of AI BOT with respect to ball
					{
						player2.moveUpDown(false);
						botMoves++ ;
					}
					if (player2.y + player2.height /2 > ball.y)     // This Lines Control the up and down movement of AI BOT with respect to ball
					{
						player2.moveUpDown(true);
						botMoves++ ;
					}
				}
				if ((gameStatus == 6)||(gameStatus == 6.1)||(gameStatus == 6.2))
				{
					if (player2.y + player2.height /2 < ball.y)  // This Control the up and down movement of AI BOT with respect to ball
					{
						player2.moveUpDown(false);
						botMoves++ ;
					}
					if (player2.y + player2.height /2 > ball.y)     // This Lines Control the up and down movement of AI BOT with respect to ball
					{
						player2.moveUpDown(true);
						botMoves++ ;
					}
					if(player3.x2 + player3.width2 /2 < ball.x)
					{
						player3.moveRightLeft2(true);
						botMoves++ ;
					}
					if(player3.x2 + player3.width2 /2 > ball.x)
					{
						player3.moveRightLeft2(false);
						botMoves++ ;
					}
				}
				
				if  ((gameStatus == 7)||(gameStatus == 7.1)||(gameStatus == 7.2))
				{
					if (player2.y + player2.height /2 < ball.y)  // This Control the up and down movement of AI BOT with respect to ball
					{
						player2.moveUpDown(false);
						botMoves++ ;
					}
					if (player2.y + player2.height /2 > ball.y)     // This Lines Control the up and down movement of AI BOT with respect to ball
					{
						player2.moveUpDown(true);
						botMoves++ ;
					}
					
					if (player1.y + player1.height /2 < ball.y)  // This Control the up and down movement of AI BOT with respect to ball
					{
						player1.moveUpDown(false);
						botMoves++ ;
					}
					if (player1.y + player1.height /2 > ball.y)     // This Lines Control the up and down movement of AI BOT with respect to ball
					{
						player1.moveUpDown(true);
						botMoves++ ;
					}
				}
				
				if  ((gameStatus == 8)||(gameStatus == 8.1)||(gameStatus == 8.2))
				{
					if (player1.y + player1.height /2 < ball.y)  // This Control the up and down movement of AI BOT with respect to ball
					{
						player1.moveUpDown(false);
						botMoves++ ;
					}
					if (player1.y + player1.height /2 > ball.y)     // This Lines Control the up and down movement of AI BOT with respect to ball
					{
						player1.moveUpDown(true);
						botMoves++ ;
					}
					if(player4.x2 + player4.width2 /2 < ball.x)
					{
						player4.moveRightLeft2(true);
						botMoves++ ;
					}
					if(player4.x2 + player4.width2 /2 > ball.x)
					{
						player4.moveRightLeft2(false);
						botMoves++ ;
					}	
					if (player2.y + player2.height /2 < ball.y)  // This Control the up and down movement of AI BOT with respect to ball
					{
						player2.moveUpDown(false);
						botMoves++ ;
					}
					if (player2.y + player2.height /2 > ball.y)     // This Lines Control the up and down movement of AI BOT with respect to ball
					{
						player2.moveUpDown(true);
						botMoves++ ;
					}
					if(player3.x2 + player3.width2 /2 < ball.x)
					{
						player3.moveRightLeft2(true);
						botMoves++ ;
					}
					if(player3.x2 + player3.width2 /2 > ball.x)
					{
						player3.moveRightLeft2(false);
						botMoves++ ;
					}
				}
				
				if ((botDifficulty == 0))            // These following lines control the Difficulty of the AI Bot
				{
					botCooldown = 20 ;             // This is for EASY Level
				}
				if (botDifficulty == 1)
				{
					botCooldown = 15 ;             // This is for MEDIUM Level
				}
				if (botDifficulty == 2)
				{
					botCooldown = 5 ;				// This is for HARD Level
				}
				if (botDifficulty == 3)
				{
					botCooldown = 1 ;	          // This is for VERY HARD Level
				}
			}	
		}
		
		if (gameStatus == 2 || gameStatus == 3 || gameStatus == 7)
		{
			ball.ballupdate1(player1 , player2 , hurdle1 , hurdle2);
			
			if (ball.ran == 1)
			{
				scoreBonus.bonus1update(player1 , player2 , hurdle1 , hurdle2);
				scoreBonus2.bonus2update(player1 , player2 , hurdle1 , hurdle2);		
			}
			if (ball.rand == 1)
			{
				lifeBonus1.lifebonus1update1(player1 , player2 , hurdle1 , hurdle2);
				lifeBonus2.lifebonus2update1(player1 , player2 , hurdle1 , hurdle2);
			}
		}
		else if((gameStatus == 2.1)||(gameStatus == 3.1)||( gameStatus == 7.1))
		{
			ball.ballupdate2(player1 , player2 , hurdle1 , hurdle2 , hurdle3 , hurdle4);
			
			if (ball.ran == 1)
			{
				scoreBonus.bonus1update(player1 , player2 , hurdle1 , hurdle2);
				scoreBonus2.bonus2update(player1 , player2 , hurdle1 , hurdle2);	
			}
			if (ball.rand == 1)
			{
				lifeBonus1.lifebonus1update1(player1 , player2 , hurdle1 , hurdle2);
				lifeBonus2.lifebonus2update1(player1 , player2 , hurdle1 , hurdle2);
			}
		}
		else if ((gameStatus == 2.2)||(gameStatus == 3.2)||(gameStatus == 7.2))
		{
			ball.ballupdate3(player1 , player2 , hurdle1 , hurdle2 , hurdle3 , hurdle4 , hurdle5 , hurdle6);
			
			if (ball.ran == 1)
			{ 
				scoreBonus.bonus1update(player1 , player2 , hurdle1 , hurdle2);
				scoreBonus2.bonus2update(player1 , player2 , hurdle1 , hurdle2);
			}
			if (ball.rand == 1)
			{
				lifeBonus1.lifebonus1update1(player1 , player2 , hurdle1 , hurdle2);
				lifeBonus2.lifebonus2update1(player1 , player2 , hurdle1 , hurdle2);
			}
		}
		
		if (gameStatus == 5 || gameStatus == 6 || gameStatus == 8)
		{
			ball.ballupdate4(player1 , player2 ,player3 , player4, hurdle1 , hurdle2);
			
			if (ball.ran == 1)
			{
				scoreBonus.bonus1update2(player1 , player2 ,player3 , player4 , hurdle1 , hurdle2);
				scoreBonus2.bonus2update2(player1 , player2 ,player3 , player4 , hurdle1 , hurdle2);
			}
			if (ball.rand == 1)
			{
				lifeBonus1.lifebonus1update2(player1 , player2 ,player3 , player4 , hurdle1 , hurdle2);
				lifeBonus2.lifebonus2update2(player1 , player2 ,player3 , player4 , hurdle1 , hurdle2);
			}
		}
		
		else if ((gameStatus == 5.1)||(gameStatus == 6.1)|| (gameStatus == 8.1))
		{
			ball.ballupdate5(player1 , player2 ,player3 , player4, hurdle1 , hurdle2 , hurdle3 , hurdle4);
			
			if (ball.ran == 1)
			{
				scoreBonus.bonus1update2(player1 , player2 ,player3 , player4 , hurdle1 , hurdle2);
				scoreBonus2.bonus2update2(player1 , player2 ,player3 , player4 , hurdle1 , hurdle2);
			}
			if (ball.rand == 1)
			{
				lifeBonus1.lifebonus1update2(player1 , player2 ,player3 , player4 , hurdle1 , hurdle2);
				lifeBonus2.lifebonus2update2(player1 , player2 ,player3 , player4 , hurdle1 , hurdle2);
			}
		}
		else if ((gameStatus == 5.2)||(gameStatus == 6.2)||( gameStatus == 8.2))
		{
			ball.ballupdate6(player1 , player2 ,player3 , player4, hurdle1 , hurdle2 , hurdle3 , hurdle4 , hurdle5 , hurdle6);
			
			if (ball.ran == 1)
			{
				scoreBonus.bonus1update2(player1 , player2 ,player3 , player4 , hurdle1 , hurdle2);
				scoreBonus2.bonus2update2(player1 , player2 ,player3 , player4 , hurdle1 , hurdle2);
				
			}
			if (ball.rand == 1)
			{
				lifeBonus1.lifebonus1update2(player1 , player2 ,player3 , player4 , hurdle1 , hurdle2);
				lifeBonus2.lifebonus2update2(player1 , player2 ,player3 , player4 , hurdle1 , hurdle2);		
			}
		}			
	}
	
	public void render(Graphics2D g) 
	{
		System.out.println("1");
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0 , 0 , width , height);
		System.out.println("12");

		g.setColor(Color.WHITE);    //this changes the color of the rectangle created in the next line. It works as the background colour.
		g.fillRect(0, 45, width, height - 85);  
		/* This makes a rectangle of the size of the JFrame. 
		 * THis can be guessed as a panel but it is not actually a panel.
		 * The main purpose of this rectangle is that it removes the trail of the ball by again repainting the frame as the ball moves.
		 */
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON );  // This line is to make the text on the menu card More Stylish
		
		if (gameStatus == 0)
		{
			g.setColor(Color.RED);
			g.setFont(new Font("Arial" , Font.ITALIC , 100));   // this 100 Defines the font size 
			
			g.drawString("Welcome To PONG" ,  width / 2 -430 , height / 4);  // this width / 2 - 100 defines the placement of the text
			g.setFont(new Font("Arial" , 1 , 30));
			g.drawString("Press Shift For Single Player " ,  width / 2 - 330 , height / 2);  // this width / 2 - 100 defines the placement of the text
			g.drawString("Press Space For Multiplayer Player " ,  width / 2 -330 , height/2 + 40);  // this width / 2 - 100 defines the placement of the text
			
			if (CP.random == 1 || CP.random == 2)
			{
				g.setColor(Color.MAGENTA);
				g.setFont(new Font("Arial" , Font.PLAIN , 35));
				g.drawString("!!! WARNING !!!" ,  width / 2 -190 , height / 2 + 150);  // this width / 2 - 100 defines the placement of the text
				g.setColor(Color.MAGENTA);
				g.setFont(new Font("Freestyle Script" , Font.PLAIN , 35));
				g.drawString("  Input Not Accepted. " ,  width / 2 -130 , height / 2 + 200);  // this width / 2 - 100 defines the placement of the text
				g.drawString(" Please Enter Name In Specified Format ??? " ,  width / 2 - 230 , height / 2 + 250);  // this width / 2 - 100 defines the placement of the text
			}
			
			if ((Filing.random == 1) || (Filing.random == 2))
			{
				g.setColor(Color.MAGENTA);
				g.setFont(new Font("Arial" , Font.PLAIN , 35));
				g.drawString("!!! WARNING !!!" ,  width / 2 -190 , height / 2 + 150);  // this width / 2 - 100 defines the placement of the text
				g.setColor(Color.MAGENTA);
				g.setFont(new Font("Freestyle Script" , Font.PLAIN , 35));
				g.drawString(" User Files Not Found " ,  width / 2 -130 , height / 2 + 200);  // this width / 2 - 100 defines the placement of the text
				g.drawString(" Instead Of Loging In Try To Sign Up ??? " ,  width / 2 - 230 , height / 2 + 250);
			}
		}
		
		if (gameStatus == 0.1)   // These Lines is for the Menu Card
		{
			g.setColor(Color.RED);
			g.setFont(new Font("Arial" , Font.ITALIC , 100));   // this 100 Defines the font size 
			
			g.drawString("PONG" ,  width / 2 - 170 , height / 4);  // this width / 2 - 100 defines the placement of the text
			
			g.setFont(new Font("Arial" , 1 , 30));
			g.drawString("Press Space To Play with player" ,  width / 2 - 140 , height / 2 - 25);
			g.drawString("<< Life Limit : " + lifeLimit + " >>" ,  width / 2 - 150 , height / 2 + 75);
				
		}
		if (gameStatus == 0.2)   // These Lines is for the Menu Card
		{
			g.setColor(Color.RED);
			g.setFont(new Font("Arial" , Font.ITALIC , 100));   // this 100 Defines the font size 
			
			g.drawString("PONG" ,  width / 2 - 170 , height / 4);  // this width / 2 - 100 defines the placement of the text
			
			if (! selectingDifficulty)
			{
				g.setFont(new Font("Arial" , 1 , 30));
				g.drawString("Press Shift To Play With Bot" ,  width / 2 - 190 , height / 2 + 25);
				g.drawString("<< Life Limit : " + lifeLimit + " >>" ,  width / 2 - 150 , height / 2 + 75);				
			}
		
			
			if (selectingDifficulty)
			{		
				String string = botDifficulty == 0 ? "Rookie" : (botDifficulty == 1 ? "Competent" :(botDifficulty == 2 ? "Proficient" : "Expert"));
				g.setFont(new Font("Arial" , 1 , 30));
				
				g.drawString("<< Level Difficulty : " + string + " >>" ,  width / 2 - 205 , height / 2 - 25);
				
				g.drawString("Press Space To Play" ,  width / 2 - 140 , height / 2 + 25);
			}
		
		}
		if (gameStatus == 1)   // These Lines is for the Pausing in games
		{
			g.setColor(Color.PINK);
			g.setFont(new Font("Arial" , 1 , 50));
			g.drawString("PAUSED" ,  width / 2 - 103 , height / 2 + 17);
		}
		
		if (gameStatus == -1)   // These Lines is for the Pausing in games
		{
			g.setColor(Color.PINK);
			g.setFont(new Font("Arial" , 1 , 50));
			g.drawString("!!! Warning !!!" ,  width / 2 - 130 , height / 4);
			g.drawString("Press Space To Resume The Game " ,  width / 2 - 430 , height / 2 + 50);		
			g.drawString("Press ESC to LogOut" ,  width / 2 - 300 , height / 2 + 120);
			
			g.setColor(Color.PINK);
			g.setFont(new Font("Arial" , 1 , 25));
			g.drawString("If You Logout then All of Your Score of current level would be lost !  " ,  width / 2 - 500 , height / 3 + 40);
		}
		
		if (gameStatus == 1.2)
		{
			g.setColor(Color.RED);
			g.setFont(new Font("Arial" , Font.ITALIC , 100));
			g.drawString("PONG" ,  width / 2 - 170 , height / 5);
			g.setFont(new Font("Arial" , 1 , 30));
			g.drawString(" The game has practically ended " ,  width / 2 -230 , height / 2);
			g.drawString(" The next levels are non Playable " ,  width / 2 -230 , height / 2 + 50);
			g.drawString("Press Space To continue " ,  width / 2 -230 , height / 2 + 100);			
			
			if (bot)
			{
				if (referenceGameStatus == 3 ||referenceGameStatus == 4 ||referenceGameStatus == 5 ||referenceGameStatus == 9 ||referenceGameStatus == 10 ||referenceGameStatus == 11)
				{
					if (playerWon == 1)
					{
						g.drawString("The AI Bot Wins !!!" ,  width / 2 - 153 , 250);
						g.drawString("Score = " + String.valueOf(ball.hitScore2) , width / 2 - 120 , 300 );
					}
					
					else
					{
						g.drawString("Player Wins !!!" ,  width / 2 - 220 , 250);
						g.drawString("Score = " + String.valueOf(ball.hitScore1) , width / 2 - 120 , 300 );
					}
				}
				else if (playerWon == 1)
				{
					g.drawString("Player Wins !!!" ,  width / 2 - 153 , 250);
					g.drawString("Score = " + String.valueOf(ball.hitScore1) , width / 2 - 120 , 300 );
				}
				
				else
				{
					g.drawString("The AI Bot Wins !!!" ,  width / 2 - 220 , 250);
					g.drawString("Score = " + String.valueOf(ball.hitScore2) , width / 2 - 120 , 300 );
				}
			}
		}
		
		if (gameStatus == 1.3)
		{
			g.setColor(Color.RED);
			
			g.setFont(new Font("Arial" , Font.ITALIC , 100));
			g.drawString("PONG" ,  width / 2 - 170 , height / 5);

			g.setFont(new Font("Arial" , Font.ITALIC , 35));

			if((referenceGameStatus == 6)||(referenceGameStatus == 7)||(referenceGameStatus == 8)||(referenceGameStatus == 9)||(referenceGameStatus == 10)||(referenceGameStatus == 11))
			{
				if (playerWon == 1)
				{
					g.drawString("The AI Bot 1 Wins !!!" ,  width / 2 - 153 , 250);
					g.drawString("Score = " + String.valueOf(ball.hitScore1) , width / 2 - 120 , 300 );
				}
				
				else if (playerWon == 2)
				{
					g.drawString("The AI Bot 2 Wins !!!" ,  width / 2 - 220 , 250);
					g.drawString("Score = " + String.valueOf(ball.hitScore2) , width / 2 - 120 , 300 );
				}
			}
			
			g.drawString("The Game Has Ended" ,  width / 2 - 250 , height /2 + 50);
			g.drawString("Press ESC to Exit" ,  width / 2 - 250 , height /2 + 100);
		
		}	
			
		if ((gameStatus == 1)||(gameStatus == 2)||(gameStatus == 2.1)||(gameStatus == 2.2)||(gameStatus == 3)||(gameStatus == 3.1)||(gameStatus == 3.2)||(gameStatus == 5)||(gameStatus == 5.1)||(gameStatus == 5.2)||(gameStatus == 6)||(gameStatus == 6.1)||(gameStatus == 6.2)||(gameStatus == 7)||(gameStatus == 7.1)||(gameStatus == 7.2)||(gameStatus == 8)||(gameStatus == 8.1)||(gameStatus == 8.2))
		{
			g.setColor(Color.BLACK);                                         //this line  are use to draw the middle line between the jframe on screen.
			
			if ((!bot)&&((gameStatus == 1)||(gameStatus == 2)||(gameStatus == 2.1)||(gameStatus == 2.2)))
			{
				if ((referenceGameStatus == 0)||(referenceGameStatus == 1)||(referenceGameStatus == 2))
				{
					g.setStroke(new BasicStroke(3f));						//this makes the middle line more thick or thin according to choice
					g.drawLine(width / 2 , 0 , width / 2 , height - 41);					//this line  are use to draw the middle line between the jframe on screen.
				}
			}
			
			if ((!bot)&&((gameStatus == 1)||(gameStatus == 5)||(gameStatus == 5.1)||(gameStatus == 5.2)))
			{
				if ((referenceGameStatus == 3)||(referenceGameStatus == 4)||(referenceGameStatus == 5))
				{
					g.setStroke(new BasicStroke(3f));						//this makes the middle line more thick or thin according to choice
					g.drawLine(0 , 45 , width , height - 40);					//this line  are use to draw the middle line between the jframe on screen.	
				}
			}
			
			if ((bot)&&((gameStatus == 3)||(gameStatus == 3.1)||(gameStatus == 3.2)||(gameStatus == 1)||(gameStatus == 7)||(gameStatus == 7.1)||(gameStatus == 7.2)))
			{
				if ((referenceGameStatus == 0)||(referenceGameStatus == 1)||(referenceGameStatus == 2)||(referenceGameStatus == 6)||(referenceGameStatus == 7)||(referenceGameStatus == 8))
				{
					g.setStroke(new BasicStroke(3f));						//this makes the middle line more thick or thin according to choice
					g.drawLine(width / 2 , 0 , width / 2 , height - 41);					//this line  are use to draw the middle line between the jframe on screen.	
				}
			}
			
			if ((bot)&&((gameStatus == 1)||(gameStatus == 5)||(gameStatus == 5.1)||(gameStatus == 5.2)||(gameStatus == 6)||(gameStatus == 6.1)||(gameStatus == 6.2)||(gameStatus == 8)||(gameStatus == 8.1)||(gameStatus == 8.2)))
			{
				if ((referenceGameStatus == 3 )||(referenceGameStatus == 4)||(referenceGameStatus == 5)||(referenceGameStatus == 9)||(referenceGameStatus == 10)||(referenceGameStatus == 11))
				{
					g.setStroke(new BasicStroke(3f));						//this makes the middle line more thick or thin according to choice
					g.drawLine(0 , 45 , width , height - 40);					//this line  are use to draw the middle line between the jframe on screen.					
				}
			}
			
			g.setStroke(new BasicStroke(2f));
			g.drawOval(width / 2 - 150 , height / 2 - 150 , 300 , 300); 
			
			g.setStroke(new BasicStroke(1f));
			g.drawLine(0, 45, width, 45);
			
			g.setStroke(new BasicStroke(1f));
			g.drawLine(0, height - 40, width, height - 40);
			
			
			g.setFont(new Font("Arial" , 1 , 35));
			
			if ((bot)&&((gameStatus == 1)||(gameStatus == 7)||(gameStatus == 7.1)||(gameStatus == 7.2)||(gameStatus == 8)||(gameStatus == 8.1)||(gameStatus == 8.2)))
			{
				if ((referenceGameStatus == 6)||(referenceGameStatus == 7)||(referenceGameStatus == 8)||(referenceGameStatus == 9)||(referenceGameStatus == 10)||(referenceGameStatus == 11))
				{
					g.drawString("AI BOT 1" ,width - 1340 , 35);	
					g.drawString("AI BOT 2" , width - 160 , 35);
				}
			}
			
			if ((bot)&&((gameStatus == 1)||(gameStatus == 3)||(gameStatus == 3.1)||(gameStatus == 3.2)||(gameStatus == 6)||(gameStatus == 6.1)||(gameStatus == 6.2)))
			{
				if ((referenceGameStatus == 0)||(referenceGameStatus == 1)||(referenceGameStatus == 2)||(referenceGameStatus == 3)||(referenceGameStatus == 4)||(referenceGameStatus == 5))
				{
					g.drawString("Player 1" , width - 1340 , 35);	
					g.drawString("AI BOT" , width - 120 , 35);					
				}
			}
			
			if ((!bot)&&((gameStatus == 1)||(gameStatus == 2)||(gameStatus == 2.1)||(gameStatus == 2.2)||(gameStatus == 5)||(gameStatus == 5.1)||(gameStatus == 5.2)))
			{
				g.drawString("Player 1" ,width - 1340 , 35);	
				g.drawString("Player 2" , width - 145 , 35);
			}
			
			g.setFont(new Font("Arial" , 1 , 20));
			
			if(gameStatus == 2|| gameStatus == 2.1|| gameStatus == 2.2 || gameStatus == 3 || gameStatus == 3.1 || gameStatus == 3.2|| gameStatus == 7 || gameStatus == 7.1 || gameStatus == 7.2 )
			{
				g.drawString("Score : " + String.valueOf(ball.hitScore1) , width / 2 - 120 , 20);
				g.drawString("Life : " + String.valueOf(player1.life) , width/2 - 100 , 40);
				
				g.drawString(String.valueOf(ball.hitScore2)+ " : Score",  width / 2 + 40 , 20);
				g.drawString(String.valueOf(player2.life)+ " : Life" , width/2 + 40 , 40);				
			}
			
			else
			{
				g.drawString("Score : " + String.valueOf(ball.hitScore1) , width / 2 - 120 , 20);
				g.drawString("Life : " + String.valueOf(player2.life) , width/2 - 100 , 40);
				
				g.drawString(String.valueOf(ball.hitScore2)+ " : Score",  width / 2 + 40 , 20);
				g.drawString(String.valueOf(player1.life)+ " : Life" , width/2 + 40 , 40);				
			}
			
			g.setFont(new Font("Arial" , 1 , 30));
			g.drawString("Level : " + levelNo() , 10 , height - 10);

			
			ball.renderer(g);	
			hurdle1.render(g);
			hurdle2.render(g);
			
			if ((gameStatus == 1)&&((referenceGameStatus == 1)||(referenceGameStatus == 7)))
			{
				hurdle3.render(g);
				hurdle4.render(g);
			}
			
			if ((gameStatus == 1)&&((referenceGameStatus == 2)||(referenceGameStatus == 8)))
			{
				hurdle3.render(g);
				hurdle4.render(g);
				hurdle5.render(g);
				hurdle6.render(g);
			}
			
			if ((gameStatus == 1)&&((referenceGameStatus == 3)||(referenceGameStatus == 9)))
			{
				player3.renderDouble(g);
				player4.renderDouble(g);
			}
			
			if ((gameStatus == 1)&&((referenceGameStatus == 4)||(referenceGameStatus == 10)))
			{
				player3.renderDouble(g);
				player4.renderDouble(g);
				hurdle3.render(g);
				hurdle4.render(g);
			}
			
			if ((gameStatus == 1)&&((referenceGameStatus == 5)||(referenceGameStatus == 11)))
			{
				player3.renderDouble(g);
				player4.renderDouble(g);
				hurdle3.render(g);
				hurdle4.render(g);
				hurdle5.render(g);
				hurdle6.render(g);
			}
			
			if ((gameStatus == 2)||(gameStatus == 3))
			{
				referenceGameStatus = 0 ;
			}
			
			if ((gameStatus == 2.1)||(gameStatus == 3.1))
			{
				referenceGameStatus = 1;
				ball.speedIncrease = 2 ;
				hurdle3.render(g);
				hurdle4.render(g);
			}
			
			if((gameStatus == 2.2)||(gameStatus == 3.2))
			{
				referenceGameStatus = 2;
				ball.speedIncrease = 3 ;
				hurdle3.render(g);
				hurdle4.render(g);
				hurdle5.render(g);
				hurdle6.render(g);
			}
			
			if ((gameStatus == 5)||(gameStatus == 6))
			{
				referenceGameStatus = 3;
				player3.renderDouble(g);
				player4.renderDouble(g);
			}
			
			if ((gameStatus == 5.1)||(gameStatus == 6.1))
			{
				referenceGameStatus = 4;
				ball.speedIncrease = 2 ;
				player3.renderDouble(g);
				player4.renderDouble(g);	
				hurdle3.render(g);
				hurdle4.render(g);
			}
			
			if((gameStatus == 5.2)||(gameStatus == 6.2))
			{
				referenceGameStatus = 5;
				ball.speedIncrease = 3 ;
				player3.renderDouble(g);
				player4.renderDouble(g);
				hurdle3.render(g);
				hurdle4.render(g);
				hurdle5.render(g);
				hurdle6.render(g);
			}
			
			if ((gameStatus == 7))
			{
				referenceGameStatus = 6;
			}
			if (gameStatus == 7.1)
			{
				referenceGameStatus = 7;
				ball.speedIncrease = 2 ;
				hurdle3.render(g);
				hurdle4.render(g);
			}
			if (gameStatus == 7.2)
			{
				referenceGameStatus = 8;
				ball.speedIncrease = 3 ;
				hurdle3.render(g);
				hurdle4.render(g);
				hurdle5.render(g);
				hurdle6.render(g);
			}
			if (gameStatus == 8)
			{
				referenceGameStatus = 9;
				player3.renderDouble(g);
				player4.renderDouble(g);
			}
			if (gameStatus == 8.1)
			{
				referenceGameStatus = 10;
				ball.speedIncrease = 2 ;
				player3.renderDouble(g);
				player4.renderDouble(g);	
				hurdle3.render(g);
				hurdle4.render(g);
			}
			if (gameStatus == 8.2)
			{
				referenceGameStatus = 11;
				ball.speedIncrease = 3 ;
				player3.renderDouble(g);
				player4.renderDouble(g);
				hurdle3.render(g);
				hurdle4.render(g);
				hurdle5.render(g);
				hurdle6.render(g);
			}
			
			scoreBonus2.bonusrenderer(g);
			
			scoreBonus.bonusrenderer(g);
			
			lifeBonus1.lifebonusrenderer(g);
			
			lifeBonus2.lifebonusrenderer(g);
			player1.render(g);
			player2.render(g);
		}
		
		if (gameStatus == 4)   // These Lines is for the Result Card
		{
			g.setColor(Color.RED);
			
			g.setFont(new Font("Arial" , Font.ITALIC , 100));
			g.drawString("PONG" ,  width / 2 - 170 , height / 5);  // this 50 Defines the font size and this width / 2 - 100 defines the placement of the text
			
			g.setFont(new Font("Arial" , 1 , 50));
			
			if((referenceGameStatus == 6)||(referenceGameStatus == 7)||(referenceGameStatus == 8)||(referenceGameStatus == 9)||(referenceGameStatus == 10)||(referenceGameStatus == 11))
			{
				if (playerWon == 1)
				{
					g.drawString("The AI Bot 1 Wins !!!" ,  width / 2 - 153 , 250);
				}
				
				else if (playerWon == 2)
				{
					g.drawString("The AI Bot 2 Wins !!!" ,  width / 2 - 220 , 250);
				}
			}
			
			else if (bot)
			{
				if (referenceGameStatus == 3 ||referenceGameStatus == 4 ||referenceGameStatus == 5 ||referenceGameStatus == 9 ||referenceGameStatus == 10 ||referenceGameStatus == 11)
				{
					if (playerWon == 1)
					{
						g.drawString("The AI Bot Wins !!!" ,  width / 2 - 153 , 250);
					}
					
					else
					{
						g.drawString("Player Wins !!!" ,  width / 2 - 220 , 250);
					}
				}
				else if (playerWon == 1)
				{
					g.drawString("Player Wins !!!" ,  width / 2 - 153 , 250);
				}
				
				else
				{
					g.drawString("The AI Bot Wins !!!" ,  width / 2 - 220 , 250);
				}
			}
			
			else 
			{
				g.drawString("Player " + playerWon + " Wins !" ,  width / 2 - 165 , 250);
			}
			
			g.setFont(new Font("Arial" , 1 , 30));
			g.drawString("Press ESC For Menu" ,  width / 2 - 190 , height / 2 + 25);
			g.drawString("Press Space To Go To Next Level" ,  width / 2 - 240 , height / 2 + 55);
		}
	}
	
	public static int levelNo() 
	{
		int level = 0 ;
		if (!bot)
		{
			if ((gameStatus == 2)||(referenceGameStatus == 0))
			{
				level = 1 ;
			}
			else if ((gameStatus == 2.1)||(referenceGameStatus == 1))
			{
				level = 2 ;
			}
			else if ((gameStatus == 2.2)||(referenceGameStatus == 2))
			{
				level = 3 ;
			}
			else if ((gameStatus == 5)||(referenceGameStatus == 3))
			{
				level = 4 ;
			}
			else if ((gameStatus == 5.1)||(referenceGameStatus == 4))
			{
				level = 5 ;
			}
			else if ((gameStatus == 5.2)||(referenceGameStatus == 6))
			{
				level = 6 ;
			}
			
		}
		if (bot)
		{
			if ((gameStatus == 3)||(referenceGameStatus == 0))
			{
				level = 1 ;
			}
			else if ((gameStatus == 3.1)||(referenceGameStatus == 1))
			{
				level = 2 ;
			}
			else if ((gameStatus == 3.2)||(referenceGameStatus == 2))
			{
				level = 3 ;
			}
			else if ((gameStatus == 6)||(referenceGameStatus == 3))
			{
				level = 4 ;
			}
			else if ((gameStatus == 6.1)||(referenceGameStatus == 4))
			{
				level = 5 ;
			}
			else if ((gameStatus == 6.2)||(referenceGameStatus == 5))
			{
				level = 6 ;
			}
			else if ((gameStatus == 7)||(referenceGameStatus == 6))
			{
				level = 1 ;
			}
			else if ((gameStatus == 7.1)||(referenceGameStatus == 7))
			{
				level = 2 ;
			}
			else if ((gameStatus == 7.2)||(referenceGameStatus == 8))
			{
				level = 3 ;
			}
			else if ((gameStatus == 8)||(referenceGameStatus == 9))
			{
				level = 4 ;
			}
			else if ((gameStatus == 8.1)||(referenceGameStatus == 10))
			{
				level = 5 ;
			}
			else if ((gameStatus == 8.2)||(referenceGameStatus == 11))
			{
				level = 6 ;
			}
		}
		
		return level;
	}
	
	public void actionPerformed(ActionEvent e)  //  This method will be executed as the Timer runs out. // this metod would be invoked everyTime the timer runs out. This Some how can be referred to as A MAIN COMPONENT OF GAME LOOP. 
	{
		System.out.println("4");

		if ((gameStatus == 2)||(gameStatus == 2.1)||(gameStatus == 2.2)||(gameStatus == 3)||(gameStatus == 3.1)||(gameStatus == 3.2)||(gameStatus == 5)||(gameStatus == 5.1)||(gameStatus == 5.2)||(gameStatus == 6)||(gameStatus == 6.1)||(gameStatus == 6.2)||(gameStatus == 7)||(gameStatus == 7.1)||(gameStatus == 7.2)||(gameStatus == 8)||(gameStatus == 8.1)||(gameStatus == 8.2))
		{
			update();
		}
		
		renderer.repaint();  // We cannot Directly Call paintComponent method so we use repaint();. As the compiler comes to this statement it would know that it needs to call paintComponent() method which is in the Renderer(); class. 
	}
	
	public static void main(String[] args) 
	{
		pong = new Pong();
	}
	
	public void keyPressed(KeyEvent e) 				// i think that this e is in place of arg0 so we can ultimately use any alphabet and just continue using it.
	{   										
		int id = e.getKeyCode();		// the (.getKeyCode())method just returns the ASCII value of the key preesed in keylistener	//e is just an argument
		
		if (id == KeyEvent.VK_W)    // if W is pressed e.getKeyCode would get its ASCII value and compare that value with W value to check if orignally this key is pressed
		{
			w = true;                  // The functinality of this line is set in update() class.
		}
		
		else if (id == KeyEvent.VK_S)  //As many keys are being used in Keylistener So IT is necessary to compare the ASCII value to specify which key is pressed and then perform its specific actions.
		{
			s = true;
		}
		
		else if (id == KeyEvent.VK_UP)   // VK stands for Virtual Key . As this can be understood as Virtual Key Codes
		{
			up = true;
		}
		
		else if (id == KeyEvent.VK_DOWN)
		{
			down = true;
		}
		
		else if (id == KeyEvent.VK_A)
		{
			a = true ;
		}
		
		else if (id == KeyEvent.VK_D)
		{
			d = true ;
		}
		
		else if (id == KeyEvent.VK_RIGHT )
		{
			if((gameStatus == 5)||(gameStatus == 5.1)||(gameStatus == 5.2))
			{
				right = true;
			}
			
			else if (selectingDifficulty)
			{
				if (botDifficulty < 3)
				{
					botDifficulty ++ ;
				}
				
				else
				{
					botDifficulty = 0;
				}
			}
			
			else if (((gameStatus == 0.1)||(gameStatus == 0.2)) && (lifeLimit > 0))
			{
				lifeLimit ++ ;
			}
		}
		else if (id == KeyEvent.VK_LEFT)
		{
			if((gameStatus == 5)||(gameStatus == 5.1)||(gameStatus == 5.2))
			{
				left = true;
			}
			
			else if (selectingDifficulty)
			{
				if (botDifficulty > 0)
				{
					botDifficulty -- ;
				}
				
				else
				{
					botDifficulty = 3;
				}
			}
			else if (((gameStatus == 0.1)||(gameStatus == 0.2)) && (lifeLimit > 1))
			{
				lifeLimit -- ;
			}	
		}
		else if (id == KeyEvent.VK_ESCAPE && ((gameStatus == 1.3)||(gameStatus == -1)||(gameStatus == 4)||(gameStatus == 2)||(gameStatus == 2.1)||(gameStatus == 2.2)||(gameStatus == 3)||(gameStatus == 3.1)||(gameStatus == 3.2)||(gameStatus == 5)||(gameStatus == 5.1)||(gameStatus == 5.2)||(gameStatus == 6)||(gameStatus == 6.1)||(gameStatus == 6.2)||(gameStatus == 7)||(gameStatus == 7.1)||(gameStatus == 7.2)||(gameStatus == 8)||(gameStatus == 8.1)||(gameStatus == 8.2)))
		{
			if(gameStatus == 1.3)
			{
				System.exit(0);
			}
			
			if (gameStatus == 4)
			{
				gameStatus = 0 ;
			}
			
			if ((gameStatus == -1)||(gameStatus == 1.2))
			{
				try 
				{
					Filing.FileUpdating();
				} 
				catch (FileNotFoundException e1) 
				{
					e1.printStackTrace();
				}
				gameStatus = 0 ;
			}
			
			else
			{
				reference = gameStatus ;
				gameStatus = -1 ;				
			}
			
			if (gameStatus == 0)
			{
				ball.repeaterBonus = 0 ;
				ball.ran = 0;
				ball.rand = 0 ;
				selectingDifficulty = false ;
				bot = false;
				botDifficulty = 0 ;
			}
		}
		else if ((id == KeyEvent.VK_SHIFT)&&(gameStatus == 0))   // Defines the Working When SHIFT Bar Is Pressed
		{
			if (gameStatus == 0)
			{
				X = 1 ;
			}
			
			if (X != 1)
			{
				jframe.setVisible(true);
			}
			
			else
			{
				jframe.setVisible(false);
				CP = new CoverPage();
			}
			
			if (X == 0)
			{
				jframe.setVisible(true);				
			}
		}
		
		else if ((id == KeyEvent.VK_SHIFT)&&(gameStatus == 0.2))   // Defines the Working When SHIFT Bar Is Pressed
		{
			ball.repeaterBonus = 0 ;
			ball.ran = 0;
			ball.rand = 0 ;
			selectingDifficulty = true ;
			bot = true ;	
		}
		
		else if (id == KeyEvent.VK_SPACE)      // Defines the Working When SPACE Bar Is Pressed
		{
			if (gameStatus == 1.2)
			{
				try 
				{
					Filing.FileUpdating();
				} 
				catch (FileNotFoundException e1) 
				{
					e1.printStackTrace();
				}
			}
			if (gameStatus == 0)
			{
				X = 2 ;
			}
			
			if (X != 2)
			{
				jframe.setVisible(true);
			}
			
			else
			{
				jframe.setVisible(false);
				CP = new CoverPage();
			}
			
			ball.repeaterBonus = 0 ;
			ball.ran = 0 ;
			ball.rand = 0 ;
			
			if ((gameStatus == 0.1)||(gameStatus == 0.2) ||( gameStatus == 4) || (gameStatus == 1.2))
			{
				if ((gameStatus == 0.1)&&(!selectingDifficulty)&&(referenceGameStatus == -1))
				{
					gameStatus = 2 ;
				}
				
				if ((gameStatus == 4)&&(!selectingDifficulty)&&(referenceGameStatus == 0))
				{
					gameStatus = 2.1 ;
				}
				
				if ((gameStatus == 4)&&(!selectingDifficulty)&&(referenceGameStatus == 1))
				{
					gameStatus = 2.2 ;
				}
				
				if ((gameStatus == 4)&&(!selectingDifficulty)&&(referenceGameStatus == 2))
				{
					gameStatus = 5 ;
				}
				
				if ((gameStatus == 4)&&(!selectingDifficulty)&&(referenceGameStatus == 3))
				{
					gameStatus = 5.1;
				}
				
				if ((gameStatus == 4)&&(!selectingDifficulty)&&(referenceGameStatus == 4))
				{
					gameStatus = 5.2 ;
				}
				
				if ((gameStatus == 0.2)&&(selectingDifficulty)&&(referenceGameStatus == -1))
				{
					gameStatus = 3 ;
				}
				
				if ((gameStatus == 4)&&(selectingDifficulty)&&(referenceGameStatus == 0))
				{
					gameStatus = 3.1 ;
				}
				
				if ((gameStatus == 4)&&(selectingDifficulty)&&(referenceGameStatus == 1))
				{
					gameStatus = 3.2 ;
				}
				
				if ((gameStatus == 4)&&(selectingDifficulty)&&(referenceGameStatus == 2))
				{
					gameStatus = 6 ;
				}
				
				if ((gameStatus == 4)&&(selectingDifficulty)&&(referenceGameStatus == 3))
				{
					gameStatus = 6.1 ;
				}
				
				if ((gameStatus == 4)&&(selectingDifficulty)&&(referenceGameStatus == 4))
				{
					gameStatus = 6.2 ;
				}
				
				if ((gameStatus == 1.2)&&(selectingDifficulty)&&(referenceGameStatus == 5))
				{
					gameStatus = 7 ;
				}
				
				if ((gameStatus == 4)&&(selectingDifficulty)&&(referenceGameStatus == 6))
				{
					gameStatus = 7.1 ;
				}
				
				if ((gameStatus == 4)&&(selectingDifficulty)&&(referenceGameStatus == 7))
				{
					gameStatus = 7.2 ;
				}
				
				if ((gameStatus == 4)&&(selectingDifficulty)&&(referenceGameStatus == 8))
				{
					gameStatus = 8 ;
				}
				
				if ((gameStatus == 4)&&(selectingDifficulty)&&(referenceGameStatus == 9))
				{
					gameStatus = 8.1 ;
				}
				
				if ((gameStatus == 4)&&(selectingDifficulty)&&(referenceGameStatus == 10))
				{
					gameStatus = 8.2 ;
				}
				
				start();
			}
			
			else if ((gameStatus == 2)||(gameStatus == 2.1)||(gameStatus == 2.2)||(gameStatus == 3)||(gameStatus == 3.1)||(gameStatus == 3.2)||(gameStatus == 5)||(gameStatus == 5.1)||(gameStatus == 5.2)||(gameStatus == 6)||(gameStatus == 6.1)||(gameStatus == 6.2)||(gameStatus == 7)||(gameStatus == 7.1)||(gameStatus == 7.2)||(gameStatus == 8)||(gameStatus == 8.1)||(gameStatus == 8.2))
			{
				gameStatus = 1 ;
			}
			
			else if ((gameStatus == 1)||(gameStatus == -1))
			{
				if ((!selectingDifficulty)&&(referenceGameStatus == 0))
				{
					gameStatus = 2 ;
				}
				
				if ((!selectingDifficulty)&&(referenceGameStatus == 1))
				{
					gameStatus = 2.1 ;
				}
				
				if ((!selectingDifficulty)&&(referenceGameStatus == 2))
				{
					gameStatus = 2.2 ;
				}
				
				if ((!selectingDifficulty)&&(referenceGameStatus == 3))
				{
					gameStatus = 5 ;
				}
				
				if ((!selectingDifficulty)&&(referenceGameStatus == 4))
				{
					gameStatus = 5.1;
				}
				
				if ((!selectingDifficulty)&&(referenceGameStatus == 5))
				{
					gameStatus = 5.2 ;
				}
				
				if ((selectingDifficulty)&&(referenceGameStatus == 0))
				{
					gameStatus = 3 ;
				}
				
				if ((selectingDifficulty)&&(referenceGameStatus == 1))
				{
					gameStatus = 3.1 ;
				}
				
				if ((selectingDifficulty)&&(referenceGameStatus == 2))
				{
					gameStatus = 3.2 ;
				}
				
				if ((selectingDifficulty)&&(referenceGameStatus == 3))
				{
					gameStatus = 6 ;
				}
				
				if ((selectingDifficulty)&&(referenceGameStatus == 4))
				{
					gameStatus = 6.1 ;
				}
				
				if ((selectingDifficulty)&&(referenceGameStatus == 5))
				{
					gameStatus = 6.2 ;
				}
				
				if ((selectingDifficulty)&&(referenceGameStatus == 6))
				{
					gameStatus = 7 ;
				}
				
				if ((selectingDifficulty)&&(referenceGameStatus == 7))
				{
					gameStatus = 7.1 ;
				}
				
				if ((selectingDifficulty)&&(referenceGameStatus == 8))
				{
					gameStatus = 7.2 ;
				}
				
				if ((selectingDifficulty)&&(referenceGameStatus == 9))
				{
					gameStatus = 8 ;
				}
				
				if ((selectingDifficulty)&&(referenceGameStatus == 10))
				{
					gameStatus = 8.1 ;
				}
				
				if ((selectingDifficulty)&&(referenceGameStatus == 11))
				{
					gameStatus = 8.2 ;
				}
			}
		}
		
		else if (id == KeyEvent.VK_ENTER)
		{
			
			if (referenceGameStatus == 6 || gameStatus == 7)
			{
				gameStatus = 7.1 ;
				ran = 2 ;
			}
			
			else if (ran == 2)
			{
				gameStatus = 7.2 ;
				ran = 3;
			}
			
			else if (ran == 3)
			{
				gameStatus = 8 ;
				ran = 4 ;
			}
			
			else if (ran == 4)
			{
				gameStatus = 8.1 ;
				ran = 5 ;
			}
			
			else 
			{
				gameStatus = 8.2 ;
			}
			
			ball.ran = 0;
			ball.rand = 0 ;
			
			start();
		}
	}

	public void keyReleased(KeyEvent e)     // we can use different arguments(e) int these 3   public void keyReleased(KeyEvent e)    lines
	{
		int id = e.getKeyCode();			//if we replace the arument e by any other letter we would also have to change from this.
		
		if (id == KeyEvent.VK_W)
		{
			w = false;
		}
		
		else if (id == KeyEvent.VK_S)
		{
			s = false;
		}
		
		else if (id == KeyEvent.VK_UP)
		{
			up = false;
		}
		
		else if (id == KeyEvent.VK_DOWN)
		{
			down = false;
		}
		
		else if (id == KeyEvent.VK_A)
		{
			a = false;
		}
		
		else if (id == KeyEvent.VK_D)
		{
			d = false;
		}
		
		else if (id == KeyEvent.VK_LEFT)
		{
			left = false;
		}
		
		else if (id == KeyEvent.VK_RIGHT)
		{
			right = false;
		}
	}
	
	public void keyTyped(KeyEvent e) 
	{}	
}