//Andrew Loveless KSU Hackathon 2019 - 27/09/19

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.stream.Stream;

public class FilterLength 
{
	
    static Pattern p = Pattern.compile("(\\d+)-(\\d+)");   //Search pattern "x-x", ex. 1-10
	public static void main(String[] args)
	{	
		
		String dictionary = ""; 
		String input = "";
		Scanner scan = new Scanner(System.in);
		String basePath = new File("").getAbsolutePath(); //Run path of the java file
	    List<String> filteredWords = new ArrayList<String>(); //ArrayList to hold words of correct length

	    
		System.out.print("" +
				"This program is used to filter out words of a given length and output to a new file.\n\n" +
				"Example inputs:\n" +
				"\"2-10\" will return a file with all words having between 2 and 10 characters\n" + 
				"\"1,3,5,7\" will return a file with all words having 1, 3, 5, and 7 characters\n\n" +
				"Dictionary: " + basePath + "/");
		try 
		{ 	dictionary = scan.nextLine(); //Get name of dictionary file
			System.out.print("Input: "); 
			input = scan.nextLine(); //Get input of what lengths are needed
			scan.close(); 
		} 
		catch (Exception e) { e.printStackTrace(); } //Print stack trace for any scanner errors
	    String absPath = new File(dictionary).getAbsolutePath(); //Get full path for dictionary file
	    Path path = Paths.get(absPath); //Make path into Path object
		Path outputFile = Paths.get(input + ".txt"); //Name the output file


	    Matcher m = p.matcher(input); //Regex matcher
	    while (m.find()) //For any instance of x-x (ex. 1-10) found
	    {
	    	String match = m.group(); // Make it a variable
	    	int lower = Integer.parseInt(match.split("-")[0]); //Get the first number
	    	int upper = Integer.parseInt(match.split("-")[1]); //Get the second number
	    	
	    	//Make sure they are in the right order
	    	if (upper < lower) 
	    	{
	    		int temp = lower; lower = upper; upper = temp;
	    	}
	    	
	    	//Get all values in between lower and upper, seperated by commas
	    	String output = "";
	    	for(int i = lower; i <= upper; i++)
	    	{
	    		output += i;
	    		if (i<upper)  output += ","; 
	    	}
	    	input = input.replace(match, output); //Replace regex match with comma seperated values
	    }
		String[] stringLengths = (input.split(",")); //Create string array of all length values
	    ArrayList<Integer> lengths = new ArrayList<Integer>(stringLengths.length); //ArrayList to fill with integers in stringLengths
	    
	    for (int i=0; i<stringLengths.length; i++)
		{
	    	try {
			lengths.add(Integer.parseInt(stringLengths[i])); //Add numbers to ArrayList
	    	}
	    	catch (Exception e)
	    	{
	    		//If any characters are unexpected, exit
	    		System.out.println("Unrecognized input, exiting..."); 
				System.exit(0);
	    	}
	    	
		}

    try (Stream<String> lines = Files.lines(path)) //Create a stream of lines from the dictionary
    {
        lines.forEach(line -> //for each line
        {
        	
        	if (lengths.contains(line.length())) filteredWords.add(line); //if the length is in the filtered lengths list, add it to the filtered words list

        });
    //If the dictionary can't be found exit
    } catch (IOException e) { System.out.println("Could not find dictionary at " + basePath + "/" + dictionary +  ". Please verify the location and try again."); System.exit(0);}
    
	try {
		//Write new file out
		Files.write(outputFile, filteredWords);
	    System.out.println("Wrote " + filteredWords.size() + " words to " + basePath + "/" + outputFile);

	} catch (IOException e) {
		//Print any io error
		e.printStackTrace();
	}

	}
}
