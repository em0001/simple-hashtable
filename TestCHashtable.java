import static org.junit.Assert.*;
import org.junit.Test;

public class TestCHashtable{
    
    @Test
    public void check_put()
    {
	/*
	 * check that put adds the items to the hashtable and that a minimum number of probes and put operations are performed and correct load is returned (i.e. correct number of items are added)
	 */
	CHashtable table = new CHashtable(11);
	CData d = new CData("persons", "name", "someone@hotmail.com", 1);
	CData d1 = new CData("human", "iAm", "human@outlook.com", 1);
	CData d2 = new CData("HUMAN", "PERSON", "humantheperson@emailsite.com", 1);	
	CData d3 = new CData("HUMAN", "PERSON", "humantheperson@emailsite.com", 1);	
	
	table.put(d.getKey(), d); table.put(d1.getKey(), d1); table.put(d2.getKey(), d2); table.put(d3.getKey(), d3); // put the 4 items into the hashtable
	
	boolean tableProbes = table.getProbes() >= 4;
	boolean tablePut = table.getPutOperations() >= 4;
	assertTrue("At least 4 probes should have occured", tableProbes);
	assertTrue("At least 4 put operations should have occured", tablePut);
	assertEquals(0.36363636363636365, table.getLoad(), 0.0); //check 4 things have been added to the hashtable
	
	/*System.out.println("CHECK PUT:");
	System.out.println("Number of probes performed: " + table.getProbes()); 
	System.out.println("Number of probes expected to have occurred (ideal hashtable): 4");
	System.out.println("Percentage of the hashtable used: " + table.getLoad());
	System.out.println("Number of put operations performed: " + table.getPutOperations());
	System.out.println();*/
    }
    
    @Test
    public void check_putAndGetNull()
    {
	/*
	 * check that put and get do not result in probes being performed but put will still be called
	 */
	CHashtable table = new CHashtable(11);
	//Create some null objects to attempt to put and get from the hashtable
	CData d = null;
	CKey k = null;	
	CData d1 = null;
	CKey k1 = null;	
	CKey k2 = null;
	table.put(k, d); table.put(k1, d1); //these should not be put into the hashtable
	 
	//check that attempting to obtain an object using a null key returns null
	assertNull(table.get(k));
	assertNull(table.get(k1));
	assertNull(table.get(k2));
	assertEquals(0.0, table.getLoad(), 0.0); //check nothing has been added to the hashtable
	assertEquals(2, table.getPutOperations()); //check nothing has been added to the hashtable but that put has been called

	/*System.out.println("CHECK PUT AND GET NULL:");
	System.out.println("Number of probes performed: " + table.getProbes()); 
	System.out.println("Number of probes expected to have occurred (ideal hashtable): 0");
	System.out.println("Percentage of the hashtable used: " + table.getLoad());
	System.out.println("Number of put operations performed: " + table.getPutOperations());
	System.out.println();*/
    }    
    
    @Test
    public void check_putNumItemsPutLargerThanHashtable()
    {
	/*
	 * check that put adds the items to the hashtable and that it stops when there is only one null space left
	 */	
	CHashtable table = new CHashtable(5);//create a hashtable
	//create some items to put into the hashtables  
	CData d = new CData("persons", "name", "someone@hotmail.com", 1);
	CData d1 = new CData("human", "iAm", "human@outlook.com", 1);
	CData d2 = new CData("HUMAN", "PERSON", "humantheperson@emailsite.com", 1);
	CData d3 = new CData("testing1", "check212", "testingcheck@anemail.com", 1);
	CData d4 = new CData("a", "name", "aname@email.com", 1);
	CData d5 = new CData("nobody", "absolutely", "iamnobody@someemail.com", 1);
	//add more items than can fit into table (it can hold 4)
	table.put(d.getKey(), d); table.put(d1.getKey(), d1); table.put(d2.getKey(), d2); table.put(d3.getKey(), d3); table.put(d4.getKey(), d4); table.put(d5.getKey(), d5);	
		
	//print out the performance of the tables
	/*System.out.println("CHECK PUTTING MORE ITEMS THAN CAN FIT INTO THE HASHTABLE:");
	System.out.println("Hashtable (size 5): ");
	System.out.println("Number of probes performed: " + table.getProbes());
	System.out.println("Number of probes expected to have occurred (ideal hashtable): 4");
	System.out.println("Percentage of the hashtable used: " + table.getLoad());
	System.out.println("Number of put operations performed: " + table.getPutOperations());	
	System.out.println();*/
	
	//flags to check if the correct number of probes has occurred in the size 5 hashtable that can accept entries
	boolean tableProbes = table.getProbes() >= 4;
	boolean tablePut = table.getPutOperations() >= 6; //put should have been called at least 6 times
	boolean tableLoad = table.getLoad() == 0.8;
	//check table has had the correct number of probes, puts and the right load size 
	assertTrue("At least 4 probes should have occured", tableProbes);
	assertTrue("At least 4 put operations should have occured", tablePut);
	assertTrue("The table load should be 0.8", tableLoad);	
    }

    @Test
    public void check_getDuplicates()
    {
        /*
         * check that get retrieves the correct item when duplicates are added to the table
         */
	CHashtable table = new CHashtable(11);
        //create some CData objects and some invalid keys to test with
        CData d = new CData("persons", "name", "someone@hotmail.com", 1);
        CData d1 = new CData("human", "iAm", "human@outlook.com", 1);
        //add some duplicates to the table
        table.put(d.getKey(), d); table.put(d.getKey(), d); table.put(d.getKey(), d); table.put(d1.getKey(), d1); table.put(d1.getKey(), d1);
	//check that these are in the hashtable
        assertNotNull(table.get(d.getKey()));
        assertNotNull(table.get(d1.getKey()));
	assertEquals(0.45454545454545453, table.getLoad(), 0.0); //check 5 things have been added to the hashtable (5/11)
	
	/*System.out.println("CHECK GET DUPLICATES:");
	System.out.println("Number of probes performed: " + table.getProbes());
        System.out.println("Number of probes expected to have occurred (ideal hashtable): 7");
        System.out.println("Percentage of the hashtable used: " + table.getLoad());
	System.out.println("Number of put operations performed: " + table.getPutOperations());      
        System.out.println("Key being retrived: " + d.getKey().getKeystring() + " Key actually retrieved: " + table.get(d.getKey()).getKey().getKeystring());
        System.out.println("Key being retrived: " + d1.getKey().getKeystring() + " Key actually retrieved: " + table.get(d1.getKey()).getKey().getKeystring());
	System.out.println();*/
    }
    
    @Test
    public void check_putDuplicates()
    {
        /*
         * check that put allows duplicates to be added to the table
         */
	CHashtable table = new CHashtable(11); 
	//create some CData objects and some invalid keys to test with
	CData d = new CData("persons", "name", "someone@hotmail.com", 1);
	CData d1 = new CData("human", "iAm", "human@outlook.com", 1);
	//add some duplicates to the table
	table.put(d.getKey(), d); table.put(d.getKey(), d); table.put(d.getKey(), d); table.put(d1.getKey(), d1); table.put(d1.getKey(), d1); 
	//check the minimum number of put and probe operations have occured
	boolean tableProbes = table.getProbes() >= 5;
	boolean tablePut = table.getPutOperations() >= 5;
	assertTrue("At least 5 probes should have occured", tableProbes);
	assertTrue("At least 5 put operations should have occured", tablePut);
	assertEquals(0.45454545454545453, table.getLoad(), 0.0); //check 5 things have been added to the hashtable of size 11 (5/11 = 0.4545454545)
	
	/*System.out.println("CHECK PUT DUPLICATES:");
	System.out.println("Number of probes performed: " + table.getProbes());
        System.out.println("Number of probes expected to have occurred (ideal hashtable): 5");
        System.out.println("Percentage of the hashtable used: " + table.getLoad());
        System.out.println("Number of put operations performed: " + table.getPutOperations());
	System.out.println();*/
    }

    @Test
    public void check_emptyHashtable()
    {
        /*
         * check that put and get on an empty hashtable don't do anything (empty in that items cannot be inserted: size < 2)
         */
	CHashtable table = new CHashtable(0);
	CHashtable table1 = new CHashtable(1);
	//items to add to attempt to add to the hashtable
	CData d = new CData("persons", "name", "someone@hotmail.com", 1);
	CData d1 = new CData("human", "iAm", "human@outlook.com", 1);
	CData d2 = new CData("HUMAN", "PERSON", "humantheperson@emailsite.com", 1);
	//attempt to add the items to the hashtables
	table.put(d.getKey(), d); table.put(d1.getKey(), d1); table.put(d2.getKey(), d2);
	table1.put(d.getKey(), d); table1.put(d1.getKey(), d1); table1.put(d2.getKey(), d2);

	/*System.out.println("CHECK EMPTY HASHTABLE:");
	System.out.println("Hashtable (size 1): ");
	System.out.println("Number of probes performed: " + table1.getProbes());
	System.out.println("Number of probes expected to have occurred (ideal hashtable): 0");
	System.out.println("Percentage of the hashtable used: " + table1.getLoad());
	System.out.println("Number of put operations performed: " + table1.getPutOperations());
	System.out.println("Hashtable (size 0): ");
	System.out.println("Number of probes performed: " + table.getProbes());
	System.out.println("Number of probes expected to have occurred (ideal hashtable): 0");
	System.out.println("Percentage of the hashtable used: " + table.getLoad());
	System.out.println("Number of put operations performed: " + table.getPutOperations());
	System.out.println();*/

	//Check the tables have no items and no probes or put operations should have occurred
	assertEquals("No probes should have occured", 0, table1.getProbes());
	assertEquals("No put operations should have occured", 3, table1.getPutOperations()); //put should have been called 3 times
	assertEquals("The load should be 0", table1.getLoad(), 0.0, 0.0);
	assertEquals("No probes should have occured", 0, table.getProbes());
	assertEquals("No put operations should have occured", 3, table.getPutOperations()); //put should have been called 3 times
	assertEquals("The load should be 0", table.getLoad(), 0.0, 0.0);
    }

    @Test
    public void check_get()
    {
        /*
         * check that get retrieves the correct item and that a minimum number of probes and put operations are performed
         */
	CHashtable table = new CHashtable(11); 
	//create some CData objects and some invalid keys to test with
	CData d = new CData("persons", "name", "someone@hotmail.com", 1);
	CData d1 = new CData("human", "iAm", "human@outlook.com", 1);
	CData d2 = new CData("HUMAN", "PERSON", "humantheperson@emailsite.com", 1);	
	CData d3 = new CData("HUMAN", "PERSON", "humantheperson@emailsite.com", 1);	
	CKey k = null; 
	CKey k1 = new CKey("hello");
	//add the items to the hash table
	table.put(d.getKey(), d); table.put(d1.getKey(), d1); table.put(d2.getKey(), d2); table.put(d3.getKey(), d3); // put the 4 items into the hashtable
	//check the minimum number of probes and put operations have occurred 
	boolean tableProbes = table.getProbes() >= 4;
	boolean tablePut = table.getPutOperations() >= 4;
	assertTrue("At least 4 probes should have occured", tableProbes);
	assertTrue("At least 4 put operations should have occured", tablePut); 
	assertEquals(0.36363636363636365, table.getLoad(), 0.0); //check 4 things have been added to the hashtable
	
	//check that these are in the hashtable
	assertNotNull(table.get(d.getKey()));
	assertNotNull(table.get(d1.getKey()));
	assertNotNull(table.get(d2.getKey()));
	assertNotNull(table.get(d3.getKey()));
	//check a key not in the hashtable returns null
	assertNull(table.get(k));
	assertNull(table.get(k1));

	/*System.out.println("CHECK GET:");
	System.out.println("Number of probes performed: " + table.getProbes()); 
	System.out.println("Number of probes expected to have occurred (ideal hashtable): 8");
	System.out.println("Percentage of the hashtable used: " + table.getLoad());
	System.out.println("Number of put operations performed: " + table.getPutOperations());
	System.out.println("Number of put operations expected to have occurred (ideal hashtable): 4");
	System.out.println("Key being retrived: " + d.getKey().getKeystring() + " Key actually retrieved: " + table.get(d.getKey()).getKey().getKeystring());
	System.out.println("Key being retrived: " + d1.getKey().getKeystring() + " Key actually retrieved: " + table.get(d1.getKey()).getKey().getKeystring());
	System.out.println("Key being retrived: " + d2.getKey().getKeystring() + " Key actually retrieved: " + table.get(d2.getKey()).getKey().getKeystring());
	System.out.println("Key being retrived: " + d3.getKey().getKeystring() + " Key actually retrieved: " + table.get(d3.getKey()).getKey().getKeystring());
	System.out.println("Key being retrived: (null) Key actually retrieved: ");	
	//print out the null key without causing a null pointer exception
	if(table.get(k) == null)
	    System.out.print("null"); //if it is null (as we expect print null)	
	else	    
	    System.out.print(table.get(k).getKey().getKeystring()); //print what is in the key
	System.out.println();
	System.out.println("Key being retrived (has a value but it isn't in the hashtable so we expect null): " + k1.getKeystring() + " Key actually retrieved: ");
	//print out the null returned CData without causing a null pointer exception
	if(table.get(k1) == null)
	    System.out.print("null"); //if it is null (as we expect print null)
	else
	    System.out.print(table.get(k1).getKey().getKeystring()); //print what is in the key
	System.out.println();*/
    }

}
