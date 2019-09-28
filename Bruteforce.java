//Andrew Loveless KSU Hackathon 2019 - 27/09/19

import java.util.Iterator;

class Bruteforce implements Iterator<String> {

	//Possible choices
    private static char[] charSet = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    private static int maximum = charSet.length - 1; //Last choice is 'z'
    private int[] indexes; //Tumblers
    private int minLength, maxLength; //Minimum and maximum characters
    
    public Bruteforce(int minLength, int maxLength) {
    	this.minLength = minLength;
    	this.maxLength = maxLength;
        indexes = new int[minLength]; //Start at minimum characters
        initIndexes(); //Set all indexes to 0 ('a')
    }

    private void initIndexes() {
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = 0;
        }
    }

    @Override
    public boolean hasNext() {
    	//Can we increment any of the indexes further?
        for (int i = 0; i < indexes.length; i++) 
            if (indexes[i] < maximum) return true;

        //If we can continue to a larger length, reset the indexes and start over
        if (indexes.length < maxLength)
        {
        	indexes = new int[indexes.length + 1];
            initIndexes();
            return true;
        } else return false; //Else return false

        
    }

    @Override
    public String next() {
    	//If we have no more room, return null
        if (!hasNext()) {
            return null;
        }

        String next = produceString(); //Next will be the next possible string
        adjustIndexes(); //Increment indexes by 1 where possible

        return next;
    }

    
    //Create a string based on index values
    //Ex. {0,1,2} -> "abc"
    private String produceString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indexes.length; i++) {
            sb.append(charSet[indexes[i]]);
        }

        return sb.toString();
    }

    //Increment the lowest possible index that is not at the maximum ('z')
    private void adjustIndexes() {
        int i;
        for(i = 0 ; i < indexes.length ; i++) {
            if(indexes[i] < maximum) {
                indexes[i] = indexes[i] + 1;
                break;
            }
        }

        //Overflow to start counting with a new combination
        for(int j=0; j < i; j++) {
            indexes[j] = 0;
        }
    }
}