//Andrew Loveless KSU Hackathon 2019 - 28/09/19

import java.util.Random;

public class Lock 
{
    private static char[] charSet = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	private char[][] configuration;
	private int tumblers, letters, matches;
	
	public Lock(int tumblers, int letters)
	{
		this.tumblers = tumblers;
		this.letters = letters;
		this.configuration = new char[letters][tumblers];
		this.matches = 0;
		
		for(int i=0;i<letters;i++)
			for(int j=0;j<tumblers;j++)
				configuration[i][j] = (char) (new Random().nextInt(charSet.length) + 97);
			
		
	}
	
	public Lock(char[][] configuration)
	{
		this.tumblers = configuration[0].length;
		this.letters = configuration.length;
		this.configuration = configuration;
		this.matches = 0;
	}

	
	public void match()
	{
		this.matches++;
	}
	
	public int getTumblers()
	{
		return this.tumblers;
	}
	
	public int getLetters()
	{
		return this.letters;
	}
	
	public int getMatches()
	{
		return this.matches;
	}
	
	public char getLetter(int tumbler, int index)
	{
		return this.configuration[index][tumbler];
	}
	@Override
	public String toString()
	{	
		String output = "";
		for(int i=0;i<letters;i++)
		{
			output += "\n";
			for(int j=0;j<tumblers;j++)
				output += configuration[i][j] + " ";
		}
		return output;
			
		
	
	}

}