//Andrew Loveless KSU Hackathon 2019 - 28/09/19

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;
import java.time.Duration;
import java.time.Instant;

public class Cracker 
{
	static Scanner scan = new Scanner(System.in);
	static String input = "";
	static double bruteSpeed, dictSpeed;

	public static void main(String[] args)
	{	
		String dictionary = ""; 
		String password = "";
		String basePath = new File("").getAbsolutePath(); //Run path of the java file

	    
		System.out.print("" +
				"This program will attempt to crack the password given with various methods\n\n" +
				"Password: ");
		password = scan.nextLine(); //Get the password
		System.out.print("\n\n" + 
				"Select a method:\n" +
				"[0] All\n" + 
				"[1] Bruteforce\n" + 
				"[2] Dictionary\n" +
				"Selection: ");
		input = scan.nextLine(); //Menu selection
		switch(input) 
		{
		case "0": //All
			System.out.println("\nBruteforce attack:");
			bruteforce(password);
			
		break;
		
		case "1": //Bruteforce
			bruteforce(password);
		break;
			
		case "2": //Dictionary
			dictionary(password);
		break;
		default: //Other
			System.out.println("Unrecognized input, exiting...");
			System.exit(0);
		break;
		}
	}
	
	private static void bruteforce(String password)
	{
		Instant startTime = Instant.now(); //Start a timer

        Bruteforce bf = new Bruteforce(1,31); //Call bruteforce for all combinations of 1-31 characters
        bf.forEachRemaining(guess -> { //For each combination that is made
        	System.out.println(guess);
        	if (password.equals(guess)) //If it is correct 
        	{ 
        		Instant endTime = Instant.now(); //Stop the timer
        		bruteSpeed = (double) Duration.between(startTime, endTime).toMillis()/1000; //See time elapsed in seconds
        		System.out.println("\n\nPassword found in " + bruteSpeed + " seconds: " + guess); //Print the correct guess
        		if (input.equals("0")) //If "All was chosen"
        		{
        			//Continue to dictionary attack, where scanner will be closed
        			System.out.println("\n-----------------------\n\nDictionary attack:"); 
        			dictionary(password);
        		} else scan.close(); System.exit(0);//Otherwise close scanner now
        }});
    }

	

	
	private static void dictionary(String password)
	{
		String basePath = new File("").getAbsolutePath(); //Run path of the java file

		System.out.print("\nInput path of dictionary: " + basePath + "/");
		String dictionary = scan.nextLine();
		Instant startTime = Instant.now(); //Start a timer
	    String absPath = new File(dictionary).getAbsolutePath(); //Get full path for dictionary file
	    Path path = Paths.get(absPath); //Make path into Path object
	    try (Stream<String> lines = Files.lines(path)) //Create a stream of lines from the dictionary
	    {
			scan.close(); //Close scanner
	        lines.forEach(guess -> //For each guess
	        {
	        	System.out.println(guess);
	        	if (guess.equals(password)) //If the guess is correct
	        	{	    
	        		Instant endTime = Instant.now(); //Stop timer
	        		dictSpeed = (double) Duration.between(startTime, endTime).toMillis()/1000; //See time elapsed in seconds
	        		System.out.println("\n\nPassword found in " + dictSpeed + " seconds: " + guess); //Print correct guess
	        		if (input.equals("0")) compareSpeed(); //If "All" was selected, compare times
	        		else System.exit(0); //Otherwise exit
	        	}
	        	
	        });
	    } catch (Exception e) { System.out.println("Could not find dictionary at " + basePath + "/" + dictionary +  
	    											". Please verify the location and try again."); dictionary(password);} //Retry for dictionary file
		Instant endTime = Instant.now(); //End timer
		double elapsed = (double) Duration.between(startTime, endTime).toMillis()/1000; //Calculate elapsed
		System.out.println("Went through dictionary in " + elapsed + " seconds and did not find the password."); //Could not find password in dictionary

	}

	private static void compareSpeed() 
	{
		if (dictSpeed < bruteSpeed) //If dictionary was faster
			System.out.println("\n\nDictionary attack was faster by " + String.format("%.2f", (bruteSpeed-dictSpeed)/bruteSpeed*100) + "%"); //Print percent faster
		else System.out.println("\n\nBruteforce attack was faster by " + String.format("%.2f", (dictSpeed-bruteSpeed)/dictSpeed*100)+ "%"); //Otherwise print for bruteforce
		System.exit(0); //Exit the program

	}
}
