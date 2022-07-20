class CHashtable{
    private CData [] table;
    private int capacity;
    private int contains = 0;
    private int numProbes = 0;
    private int numPutOperations = 0;
	
    /*
     * initialsises the hash table to the passed in size
     */
    CHashtable(int capacity){
	if(capacity > 0){ // only allow a valid sized table to be created
	    this.capacity = capacity;
	    table = new CData[capacity];
	}
	else{
	    System.err.println("You cannot create a Hashtable with less than 1 elements");
	}
    }
	
    /*
     * adds the CData into the hash table at the location determined by the hashCode of the CKey or uses linear probing to index further 
     * if the index is already occupied with another CData object
     */
    public void put(CKey k, CData d){	
        numPutOperations++; //increase the number of put operations by 1	
	//if the passed in key or cdata is null or if adding the item will cause the hashtable to be full return
	if(k == null || d == null || contains + 1 >= capacity)
	    return; 
	
	numProbes++; //increase the number of probes performed by 1		
	
	//get the hashcode of the CKey
	int hc = k.hashCode();
		
	//use the hashcode to calculate the index in the table
	int index = hc % capacity;
		
	//if the calculated index is negative then turn it into a positive number
	if(index < 0)
	    index = index * -1;
								
	//loop through the hashtable until we find an empty slot to put our CData object in
	while(table[index] != null){			
	    //if we are at the end of the hash table change the index so that we loop back up to the beginning of it to continue searching
	    if(index == capacity -1)
		index = 0;
	    else //otherwise increase the index to the next position in the hash table
		index++;			
	    
	    numProbes++; //increase the number of probes performed by 1
	}				
	table[index] = d; //insert the CData object at the index 
	contains++; //increase the number of items that exist in the hash table by 1		
	return;
    }
	
    /*
     * returns a reference to the CData stored in the table with the passed in key, or null if the item is not found in the hash table
     */
    public CData get(CKey k){
	//if the passed in key is null or the hashtable has no elements return
	if(k == null || capacity < 2)
	    return null;
	
	//get the hashcode of the CKey
	int hc = k.hashCode();
	//calculate the index of the key
	int index = hc % capacity;
		
	//if the calculated index is negative then turn it into a positive number
	if(index < 1)
	    index = index * -1;
		
	//increase the number of probes by 1
	numProbes++;
	
	//check if the CData object is at the index of the hashcode of the key if so return it
	if(table[index].getKey().getKeystring().equals(k.getKeystring())){
	    return table[index];		
	}

	int originalIndex = index; //copy of index to use as a flag that we have looped all the way around the hashtable		
	
	//set up to begin linear probing
	if(index == capacity - 1) //if we are at the end of the hash table change the index so that we loop back up to the beginning of it to continue searching
	    index = 0;
	else
	    index++; //move to the next slot
				
	numProbes++; //increase the number of probes performed by 1
		
	//loop through the hashtable to check if the item has been place in another index using linear probing
	//while we are not back at the original index AND we have not reached an empty slot in the hashtable
	while(index != originalIndex && table[index] != null){
	    //check if the item at the current index contains the CData object with the key we are looking for if so return the CData object at that index
	    if(table[index].getKey().getKeystring().equals(k.getKeystring()))
		return table[index];					
		
	    //if we are at the end of the hash table change the index so that we loop back up to the beginning of it to continue searching
	    if(index == capacity - 1)
		index = 0;
	    else //otherwise increase the index to the next position in the hash table
		index++;
	    //increase the number of probes performed by 1
	    numProbes++;					
	}		
	//we have not found the CData item with the passed in key so return null
	return null;
    }
	
    /*
     * returns how many probes have been performed on the hashtable
     */
    public int getProbes(){
	return numProbes;		
    }
	
    /*
     * returns how many put operations have been performed on the hashtable
     */
    public int getPutOperations(){
	return numPutOperations;		
    }
	
    /*
     * returns the load factor - the fraction of array locations occupied with items
     */
    public double getLoad(){
	//if the table is empty return 0
	if(contains == 0) 
	    return 0;
	return (double)contains/capacity; //otherwise calculate the fraction of the occupied array locations
    }

}
