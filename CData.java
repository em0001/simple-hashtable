class CData{
    private String name;
    private String phone;
    private String email;
    private CKey key;
	
    //intialises the fields to the values passed in
    public CData(String name, String phone, String email, int keyChoice){
	if(name == null || phone == null || email == null)
	    return;
		
	this.name = name;
	this.phone = phone;
	this.email = email;
	String[] keyOptionsArray = new String[] {this.name, this.phone, this.email};
		
	//if the passed in value is between and including 0 and 2 use the coresponding argument to create the CKey object
	if(keyChoice >= 0 && keyChoice <= 2 ){		
	    key = new CKey(keyOptionsArray[keyChoice]);
	}
	else{ //if the passed in value is not in the range use name as the default argument to create the CKey object
	    key = new CKey(keyOptionsArray[0]);	
	}
    }

    /*
     * returns a reference to the CKey object key
     */
    public CKey getKey(){
	return key;
    }
	
    /*
     * overwrites the default toString method and returns the name, phone and email concatanated separated by spaces
     */
    @Override
    public String toString(){
	return name + " " + phone + " " + email;
    }
}
