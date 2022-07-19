# simple-hashtable
CHashtable.java - A hashtable for CData objects
CData.java - An object that consists of a name, phone, email and CKey (used to calculate the hashcode for the CData object)
CKey.java - An object that consists of a string which is then used to calculate a hashcode. Contains multiple methods that can be commented/uncommented to calculate a hashcode (the implementation from Java String's hashCode method, folding implementation of hashcode and rotation implementation of hashcode)
Report.txt is the experimentation results of experimenting with the different string hashing formulae in the CKey class. <br />

To run the program after compilation: <br />
java CProcess data.csv intSize intKeyChoice <br />
where data.csv is a csv containing data in the following format: name, phone and email (AU.csv and CA.csv are both in this format) intSize is an integer that determines the size of the hashtable that will be created, and int intKeyChoice determines which element (name, phone or email) will be used to create the hashcode for that line of the file (which is created into a CData object). If an invalid index is passed in (i.e. not between 0 and 2) 0 aka the name is used by default.

For each line in the proovided file a CData object is created and inserted into the hashtable, the CKey (used to calculate the hashcode of the CData object) is created based on the CData string field indexed by intKeyChoice (last provided command argument). After each CData object is inserted into the hashtable the expected and actual performance values (the number of put operations performed, the number or probes performed and the percentage of the hashable used) are displayed. 

