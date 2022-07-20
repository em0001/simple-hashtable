import java.io.*;

class CProcess{
    public static void main(String[] args){
	//check that a text file has been provided
	if(args.length < 3 ){
	    System.out.println("Please provide a file and two integers");
	}
	else{
	    try{
		int size = Integer.parseInt(args[1]);
		//if a valid size is provided to create the hashtable (at least one item can be stored in the hash table)
		if(size > 1){	
		    //create a hashtable with the size specified by the second command-line argument
		    CHashtable hashTable = new CHashtable(size);
				
		    //get the command line argument that tells us what piece of data will be used to create the CKey
		    int keyChoice = Integer.parseInt(args[2]);
			
		    //create a buffered reader object to read the provided file
		    BufferedReader in = new BufferedReader(new FileReader(args[0]));
			
		    //int to count how many lines of the file we have processed
		    int count = 0;
		    //flag so the first line of the file which has headings is included in the processing
		    boolean firstLine = true;
			
		    //read each line in the file
		    while(in.ready() == true){	
			//read a line from the file
			String line = in.readLine();	
			    
			if(firstLine){ //don't include the first line of the file which just contains headings be processed in the file			    	 	
			    firstLine = false;
			    continue;	    	
			}
			count++; //increment how many lines we have processed
			    	
			//split the line of the file into individual words on instances of commas
			String[] tokens = line.split(",");
			//make sure we have the correct number of expected elements (3 - name, phone number, email)
			if(tokens.length == 3){
			    //Create a CData object Key is created based on the CData string field indexed by the last command-line argument
			    CData d = new CData(tokens[0], tokens[1], tokens[2], keyChoice);
			    //adds the CData object to the hashtable
			    hashTable.put(d.getKey(), d);	    
			}	 
			    
			//Display the expected and actual performance values obtained and calculated using getLoad(), getProbes() and a count of how many put operations have been called   	    
			System.out.println("Number of probes performed: " + hashTable.getProbes()); 
			System.out.println("Number of probes expected to have occurred (ideal hashtable): " + count);
			System.out.println("Percentage of the hashtable used: " + hashTable.getLoad());
			System.out.println("Number of put operations performed: " + hashTable.getPutOperations());
		    }
		    //close the file		
		    in.close();
		    System.out.println();
			
		    //display the final num of probes, pecentage of hashtable used and number of put operations performed
		    System.out.println("FILE PROCESSED FINAL VALUES:");
		    System.out.println("FINAL Number of probes performed: " + hashTable.getProbes());
		    System.out.println("FINAL Number probes expected to have occurred (ideal hashtable): " + count);
		    System.out.println("FINAL Percentage of the hashtable used: " + hashTable.getLoad());
		    System.out.println("FINAL Number of put operations performed: " + hashTable.getPutOperations());
		}
		else{ //tell user to give a greater size
		    System.err.println("Please provide a size that is greater than 1 so that at least 1 item can be stored in the hashtable");
		}
	
	    }
	    catch(NumberFormatException e){ //if the integer parsing fails (when user provides two integers in the command line)
		System.err.println("Please provide a file and two valid integers");
	    }
	    catch(Exception e){
		System.err.println("Exception: " + e);
	    }
	}
    }    
}
