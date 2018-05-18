package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CoverPage implements ActionListener
{
	public Pong pong;
	public JTextField field1 ;
	public JTextField field2 ;
	public JButton signUp;
	public JFrame frame ;
	
	public static int reference1;
	public static int reference2;
	public static int random ;
	public int width = 1366;
	public int height = 700 ; 
	public static String player1Name ;
	public static String player2Name ;
	public static boolean signup = true ;
	
	public CoverPage()
	{	
		frame = new JFrame(" Sign In Page ");
		field1 = new JTextField();
		field2 = new JTextField();
		signUp = new JButton("Sign Up");
		field1.setEditable(true);
		
		if (pong.X == 1)
		{
			field1 .setBounds(673, 339, 300, 30);
			frame.add(field1);
		}
		
		if (pong.X == 2)
		{
			field1 .setBounds(673, 350, 300, 30); 
			field2 .setBounds(673, 460, 300, 30); 
			frame.add(field1);
			frame.add(field2); 
		}
		
		signUp.setBounds(1100 ,400, 100, 40);
		frame.add(signUp);
			
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		Drawings Draw = new Drawings();
		
		frame.add(Draw);
		frame.setSize(1366 , 738);
		
		signUp.addActionListener(this);
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
	
	public class Drawings extends JPanel 
	{
		private static final long serialVersionUID = 1L;
		public void paintComponent(Graphics g) 
		{
		    super.paintComponent(g);
		    Graphics2D g2d = (Graphics2D) g;
		    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON );
		    g2d.setColor(Color.LIGHT_GRAY);
			g2d.fillRect(0 , 0 , width , height + 38);
			
			g2d.setColor(Color.WHITE);    //this changes the color of the rectangle created in the next line. It works as the background colour.
			g2d.fillRect(0, 45, width,height - 85);  
			
			g.setColor(Color.RED);
			g.setFont(new Font("Arial" , Font.ITALIC , 40));  
			
			if (pong.X == 1)
			{			
				g.drawString("Please Enter Name in Specified Format" ,  width / 2 - 460 , height / 8 + 50);
				g.drawString("Enter Your Name And Your Roll No. " ,  width / 2 - 420 , height / 5 + 50);
				g.drawString("For Example : hamza069 " ,  width / 2 - 340 , height / 3);
				g.setColor(Color.BLUE);
				g.setFont(new Font("Arial" , Font.ITALIC , 30));  
				g.drawString("Enter Player Name : " ,  width / 2 - 400 , height / 2 + 12);
				if (random == 1)
				{
					g.setColor(Color.MAGENTA);
					g.setFont(new Font("Arial" , Font.ITALIC , 30));  
					g.drawString("??? Please Enter in Specified  Format ??? " ,  width / 2 - 400 , height/2 + 80);
				}
				
				if (Filing.random == 1)
				{
					g.setColor(Color.MAGENTA);
					g.setFont(new Font("Arial" , Font.ITALIC , 30));  
					g.drawString("??? Please Try To Sign Up ??? " ,  width / 2 - 400 , height/2 + 80);
					Filing.random = 0 ;
				}
			}
			if (pong.X == 2)
			{
				g.drawString("Please Enter Name in Specified Format" ,  width / 2 - 460 , height / 8 + 50);
				g.drawString("Enter Your Name And Your Roll No. " ,  width / 2 - 420 , height / 5 + 50);
				g.drawString("For Example : hamza069 " ,  width / 2 - 340 , height / 3);
				g.setColor(Color.BLUE);
				g.setFont(new Font("Arial" , Font.ITALIC , 30));  
				g.drawString("Enter Player1 Name : " ,  width / 2 - 400 , height/2 + 20);
				g.drawString("Enter Player2 Name : " ,  width / 2 - 400 , height / 2 + 130);	
				if (random == 2)
				{
					g.setColor(Color.MAGENTA);
					g.setFont(new Font("Arial" , Font.ITALIC , 30));  
					g.drawString("??? Please Enter in Specified  Format ??? " ,  width / 2 - 400 , height/2 + 80);
				}
				
				if (Filing.random == 2)
				{
					g.setColor(Color.MAGENTA);
					g.setFont(new Font("Arial" , Font.ITALIC , 30));  
					g.drawString("??? Please Try To Sign Up ??? " ,  width / 2 - 400 , height/2 + 80);
					Filing.random = 0 ;
				}
			}
		}
	}

	public void actionPerformed(ActionEvent A) 
	{
		if(A.getSource()== signUp)
		{
			if(pong.X == 2)
			{
				pong.gameStatus = 0.1 ;
				player2Name = field2.getText() ;
				player1Name = field1.getText();
				check();
			}
			else if (pong.X == 1)
			{
				player1Name = field1.getText();
				pong.gameStatus = 0.2 ;
				check();
			}
			
			if (signup)
			{
				try 
				{
					Filing.FileMaking();
				}
				catch (Exception e) 
				{	
					e.printStackTrace();
				}
			}
			
			pong.X = 0;
			
			if (pong.X == 0)
			{
				frame.setVisible(false);
				pong.jframe.setVisible(true);
			}
		}
	}

	public void check() 
	{
		try
		{
			if (pong.X == 1)
			{
				for (int i = player1Name.length()-1 ; i >= ( player1Name.length() - 3 ) ; i--)
				{
					char a = player1Name.charAt(i);
					int A = Character.getNumericValue(a);
					
					if (A >= 0 && A <= 9 )
					{
						reference1 = 1;
					}
					else 
					{
						reference1 = 2 ;
					}
				}
				
				char b = player1Name.charAt(player1Name.length() - 4);
				int B = (int) b ;
				
				if (!(((B >= 65)&&(B <= 90))||((B >= 97)&&(B <= 122))))
				{
					reference1 = 2 ;
				}
				
				char c = player1Name.charAt(0);
				int C = (int) c ;
				
				if (!(((C >= 65)&&(C <= 90))||((C >= 97)&&(C <= 122))))
				{
					reference1 = 2;
				}
				
				if (reference1 == 2)
				{
					signup = false ;
					random = 1;
					pong.gameStatus = 0 ;
				}	
			}
			
			if (pong.X == 2)
			{
				for (int i = player1Name.length()-1 ; i >= ( player1Name.length() - 3 ) ; i--)
				{
					char a = player1Name.charAt(i);
					int A = Character.getNumericValue(a);
					
					if (A >= 0 && A <= 9 )
					{
						reference2 = 1;
					}
					else 
					{
						reference2 = 2 ;
					}
				}
				
				for (int i = player2Name.length()-1 ; i >= ( player2Name.length() - 3 ) ; i--)
				{
					char d = player2Name.charAt(i);
					int D = Character.getNumericValue(d);
					
					if (D >= 0 && D <= 9 )
					{
						reference2 = 1;
					}
					else 
					{
						reference2 = 2 ;
					}
				}
				
				char e = player1Name.charAt(player1Name.length() - 4);
				int E = (int) e ;
				
				if (!(((E >= 65)&&(E <= 90))||((E >= 97)&&(E <= 122))))
				{
					reference2 = 2 ;
				}
				
				char f = player1Name.charAt(0);
				int F = (int) f ;
				
				if (!(((F >= 65)&&(F <= 90))||((F >= 97)&&(F <= 122))))
				{
					reference2 = 2 ;
				}
				
				char g = player2Name.charAt(player2Name.length() - 4);
				int G = (int) g ;
				
				if (!(((G >= 65)&&(G <= 90))||((G >= 97)&&(G <= 122))))
				{
					reference2 = 2 ;
				}
				
				char h = player2Name.charAt(0);
				int H = (int) h ;
				
				if (!(((H >= 65)&&(H <= 90))||((H >= 97)&&(H <= 122))))
				{
					reference2 = 2 ;
				}
				
				if (reference2 == 2)
				{
					signup = false ;
					random = 2 ;	
					pong.gameStatus = 0 ;
				}			
			}
		} 
		
		catch (Exception x)
		{
			if(pong.X == 1)
			{
				pong.gameStatus = 0 ;
				random = 1;				
			}
			else if (pong.X == 2)
			{
				pong.gameStatus = 0 ;
				random = 2 ;				
			}
		}
	}
}