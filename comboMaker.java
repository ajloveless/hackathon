//Andrew Loveless KSU Hackathon 2019 - 28/09/19

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class comboMaker 
{
	static Scanner scan = new Scanner(System.in);
	static String basePath = new File("").getAbsolutePath(); //Run path of the java file
    static List<String> words = new ArrayList<String>(); //ArrayList to hold words of correct length
	static ArrayList<Character> testChars = new ArrayList<Character>();
	static ArrayList<String> outStream = new ArrayList<String>();
	static ArrayList<Lock> topLocks = new ArrayList<Lock>();
	static Path outputFile = Paths.get("locks.txt"); //Name the output file
	static int lockNumber = 10;
	static int nMax = 5, mMax = 26;


	public static void main(String[] args)
	{
		System.out.print("Input number of iterations: ");
		String input = scan.nextLine();

		try
		{
			lockNumber = Integer.parseInt(input);
		} catch (Exception e)
		{
			System.out.println("Unrecognized input. defaulting to " + lockNumber + " iterations.");
		}
		getDictionary();

		
		for(int n = 2; n <= nMax; n++)
			for(int m = 2; m <= mMax; m++)
			{
				ArrayList<Lock> locks = new ArrayList<Lock>();


		for (int i=0; i < lockNumber; i++)
			locks.add( new Lock(n,m));
		
		
		for (int i=0; i < locks.size(); i++)
			checkLock(locks.get(i));
		
		int indexOfMax = 0;
		for (int i=0; i < locks.size(); i++)
			if (locks.get(i).getMatches() > locks.get(indexOfMax).getMatches()) indexOfMax = i;
		topLocks.add(locks.get(indexOfMax));
		String output = "n=" + n + " m=" + m + " " + locks.get(indexOfMax).getMatches() +
				"\n\n" + locks.get(indexOfMax).toString()+ "\n\n";

		outStream.add(output);
		System.out.println(output);
			}
		int indexOfMax = 0;
		for (int i=0; i < topLocks.size(); i++)
			if (topLocks.get(i).getMatches() > topLocks.get(indexOfMax).getMatches()) indexOfMax = i;
		String output = "\n\nMost combinations: n=" + topLocks.get(indexOfMax).getTumblers() + " m=" + topLocks.get(indexOfMax).getLetters() + " " 
			+ topLocks.get(indexOfMax).getMatches() + "\n\n" + topLocks.get(indexOfMax).toString()+ "\n\n";
		outStream.add(output);
		System.out.println(output);
		try {
			//Write new file out
			Files.write(outputFile, outStream);
		    System.out.println("Wrote output to " + basePath + "/" + outputFile);

		} catch (IOException e) {
			//Print any io error
			e.printStackTrace();
		}
	}
		
	public static void checkLock(Lock lock)
	{
		for (int k=0; k<words.size();k++)
		{
			if (words.get(k).length() != lock.getTumblers()) continue;
			//Given that the test word is the correct length

				if (validWord(words.get(k), lock)) lock.match();
				//else System.out.println("Invalid word: " + words.get(k));
		}
	}
	public static boolean validWord(String word, Lock lock)
	{
		for(int j=0;j<lock.getTumblers();j++)
		{
			testChars.removeAll(testChars);
			for(int i=0;i<lock.getLetters();i++)
			{
				testChars.add(lock.getLetter(j, i));
			}
			for(int i=0;i<lock.getLetters();i++)
				//System.out.println(testChars.get(i) + " ");

				if (!testChars.contains(word.charAt(j))) return false;
			
		}
		return true;
	}
	
    public static void getDictionary()
    {
	System.out.print("\nInput path of dictionary: " + basePath + "/");
	String dictionary = scan.nextLine();
    String absPath = new File(dictionary).getAbsolutePath(); //Get full path for dictionary file
    Path path = Paths.get(absPath); //Make path into Path object

    int[] letters = new int[26];
    for (int i=0; i<letters.length; i++)
    	letters[i] = 0;
    

    try (Stream<String> lines = Files.lines(path)) //Create a stream of lines from the dictionary
    {
		scan.close(); //Close scanner

        lines.forEach(word ->{words.add(word);});

    } catch (Exception e) { System.out.println("Could not find dictionary at " + basePath + "/" + dictionary + ". Please verify the location and try again."); getDictionary();} //Retry for dictionary file
    
	}
}
