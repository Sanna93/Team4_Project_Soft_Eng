package lab8in;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class DatabaseFileTest {

	String[] users = {"jsmith@uca.edu","msmith@uca.edu","tjones@yahoo.com","jjones@yahoo.com"};
	String[] passwords = {"hello123","pass123","123456","hello1234"};

	private DatabaseFile db; 
		
	private int rando;
	
	@Before
	public void setUp() throws Exception 
	{
	  db = new DatabaseFile(); 
	  db.setStream("src/lab8in/users.txt");
	  rando = ((int)Math.random()*users.length); 
	}


	@Test
	public void testSetStream() throws FileNotFoundException
	{
	db.setStream("src/lab8in/users.txt");

	FileInputStream set = db.getStream();

	  //3. make sure FileInputStream object returned by getStream is not null
			
//	try {  
	assertNotNull("Check setStream", set); //Place object here 
//	}catch{
		
//	}
			//fail("not yet implemented");
	}


	@Test(expected = FileNotFoundException.class)
	public void testStream() throws FileNotFoundException
	{
	  db.setStream("user.txt");
	  //1. Set the stream with user.txt (wrong name) – should throw FileNotFoundException

	}

		

	@Test
	public void testQuery() throws IOException 
	{
	  //Use Random # to extract username/ and expected password
			
	 String username = users[rando]; 
	 String expected = passwords[rando];
			
	 //get actual result (invoke query with username
			
	 String usr = db.query(username);
			
	//compare expected with actual using assertEquals
			
	assertEquals(usr, expected) ;
		
	}
		
	@Test  //Test for bad user name
	public void testQuery2() throws IOException
	{
			
	  //Set bad username to an invalid name
	String badUserName = "JosephJohnsonthe3rd";
	 //Extract actual name based on bad user name
	String badUsr = db.query(badUserName);	
	 //compare actual with assertNull 
			
	 assertNull("Check query", badUsr); 
	}



	

}
