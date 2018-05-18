package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.Scanner;

public class Filing 
{
	public static File file ;
	public static File file1 ;
	public static File file2 ;
	
	public static PrintWriter writer ;
	public static FileWriter fw ;
	public static Formatter Fom ;
	public static FileReader reader ;
	public static BufferedReader buffer ;
	public static CoverPage CP ;
	
	public static String High = "High Score" ;
	public static int random = 0 ;
	public static int highScore ;
	
	public static void FileMaking() throws FileNotFoundException
	{
		file = new File(High);
		file.mkdirs();
		Fom = new Formatter(High + "//" + High + ".txt");
		
		if (CP.reference1 == 1)
		{
			file1 = new File(CP.player1Name);
			file1.mkdirs();
			Fom = new Formatter(file1 + "//" + "Score.txt");
			Fom = new Formatter(file1 + "//" + "gameStatus.txt");
			Fom = new Formatter(file1 + "//" + "botDifficulty.txt");
			Fom = new Formatter(file1 + "//" + "bot.txt");
		}
		if (CP.reference2 == 1)
		{
			file1 = new File(CP.player1Name);
			file2 = new File(CP.player2Name);
			
			file1.mkdirs();
			
			Fom = new Formatter(file1 + "//" + "Score.txt");
			Fom = new Formatter(file1 + "//" + "gameStatus.txt");
			Fom = new Formatter(file1 + "//" + "botDifficulty.txt");
			Fom = new Formatter(file1 + "//" + "bot.txt");
			
			file2.mkdirs();
			
			Fom = new Formatter(file2 + "//" + "Score.txt");
			Fom = new Formatter(file2 + "//" + "gameStatus.txt");
			Fom = new Formatter(file2 + "//" + "botDifficulty.txt");
			Fom = new Formatter(file2 + "//" + "bot.txt");
		}
	}
	
	public static void FileUpdating() throws FileNotFoundException
	{
		if (CP.reference1 == 1)
		{
			File file1 = new File(CP.player1Name + "//" + "Score.txt");
			File file2 = new File(CP.player1Name + "//" + "gameStatus.txt");
			File file3 = new File(CP.player1Name + "//" + "botDifficulty.txt");
			File file4 = new File(CP.player1Name + "//" + "bot.txt");
			
			writer = new PrintWriter(file1);
			writer.print(Ball.hitScore1);
			writer.close();
			
			writer = new PrintWriter(file2);
			writer.print(Pong.reference);
			writer.close();
			
			writer = new PrintWriter(file3);
			writer.print(Pong.botDifficulty);
			writer.close();
			
			writer = new PrintWriter(file4);
			writer.print(Pong.bot);
			writer.close();
			
		}
		
		if (CP.reference2 == 1)
		{
			File file11 = new File(CP.player1Name + "//" + "Score.txt");
			File file12 = new File(CP.player1Name + "//" + "gameStatus.txt");
			File file13 = new File(CP.player1Name + "//" + "botDifficulty.txt");
			File file14 = new File(CP.player1Name + "//" + "bot.txt");
			
			writer = new PrintWriter(file11);
			writer.print(Ball.hitScore1);
			writer.close();
			
			writer = new PrintWriter(file12);
			writer.print(Pong.reference);
			writer.close();
			
			writer = new PrintWriter(file13);
			writer.print(Pong.botDifficulty);
			writer.close();
			
			writer = new PrintWriter(file14);
			writer.print(Pong.bot);
			writer.close();
			
			File file21 = new File(CP.player2Name + "//" + "Score.txt");
			File file22 = new File(CP.player2Name + "//" + "gameStatus.txt");
			File file23 = new File(CP.player2Name + "//" + "botDifficulty.txt");
			File file24 = new File(CP.player2Name + "//" + "bot.txt");
			
			writer = new PrintWriter(file21);
			writer.print(Ball.hitScore1);
			writer.close();
			
			writer = new PrintWriter(file22);
			writer.print(Pong.reference);
			writer.close();
			
			writer = new PrintWriter(file23);
			writer.print(Pong.botDifficulty);
			writer.close();
			
			writer = new PrintWriter(file24);
			writer.print(Pong.bot);
			writer.close();	
		}
	}
}