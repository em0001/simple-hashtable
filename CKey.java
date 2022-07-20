class CKey{
    private String keystring;

    //intialises the keystring to the passed in value	
    public CKey(String keystring){
    	if(keystring == null)
	    return;
	this.keystring = new String(keystring);
    }

    /*
     * returns a reference to the keystring
     */	
    public String getKeystring(){
	return keystring;
    }
    
    /*
     * overwrites the default hashcode method to create and return a hashcode based on the value in the keystring
     * this is the version Java String uses
     */
    @Override
    public int hashCode(){
	/* provided implementation from Java String's hashCode method  */
	//get the string as a bytes array
    	byte[] bytes = keystring.getBytes();
    	int hashcode = 0;
	//for each element in the array times it by 31^a different value for each element
	//corresponding to it's position in the array and add it to the hashcode
    	for(int i = 0; i < bytes.length; i++){
	    int tmp = (int)Math.pow(31, bytes.length - (i + 1));		 
	    hashcode += (bytes[i] * tmp);
    	}	
    	return hashcode;
    }

    /*
     * overwrites the default hashcode method to create and return a hashcode based on the value in the keystring
     * this is making use of folding - adds the first half and second half of the array into variables and then times them together to produce a hashcode
     */
    //@Override 	
    public int hashCode2(){
	/* folding implementation of hashcode */
	//get the string as a bytes array 
	byte[] bytes = keystring.getBytes();
	int hashcode = 0;
	//if the bytes are only one element use it as the hashcode 
	if(bytes.length == 1){
	    hashcode = bytes[0];
	}
	else{
	    int halfBytesLength = bytes.length/2;
	    int tmp = 0;
	    int tmp2 = 0;
	    //add the first half of the array into a variable
	    for(int i = 0; i < halfBytesLength; i++){
		tmp += bytes[i];
	    }
	    //add the second half of the array into a variable
	    for(int i = halfBytesLength; i < bytes.length; i++){
		tmp2 += bytes[i];
	    }
	    //multiply the two numbers together
	    hashcode = tmp * tmp2;
	    //add the first value in the array to the number
	    hashcode += bytes[0];
	}
	return hashcode;	
    }
    
    /*
     * overwrites the default hashcode method to create and return a hashcode based on the value in the keystring
     * this is making use of rotating the first and last item in the array around
     */
    //@Override 
    public int hashCode1(){
    	/* rotation implementation of hashcode */
	//get the string as a bytes array 
	byte[] bytes = keystring.getBytes();
	int hashcode = 0;
	//if the bytes array has only one element use it as the hashcode 
	if(bytes.length == 1){
	    hashcode = bytes[0];
	}
	else{
	    //rotate the first and last element in the bytes array
	    byte b = bytes[0];
	    bytes[0] = bytes[bytes.length - 1];
	    bytes[bytes.length - 1] = b;
	    //loop through the array and multiply the element by its value to the power of its position and add to the hashcode
	    for(int i = 0; i < bytes.length; i++){
		int tmp = (int)Math.pow(bytes[i], i);
		hashcode += (bytes[i] * tmp);
	    }	    
	}
	return hashcode;
    }
	
    /*
     * overwrites the default equals method to return true or false based on if the contents of the two strings is the same
     */
    public boolean equals(String str){
    	if(keystring.equals(str))
	    return true;
	return false;
    }
    
}
