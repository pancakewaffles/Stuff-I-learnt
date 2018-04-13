import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.caveofprogramming.designpatterns.demo1.model.DAOFactory;
import com.caveofprogramming.designpatterns.demo1.model.Database;
import com.caveofprogramming.designpatterns.demo1.model.Person;
import com.caveofprogramming.designpatterns.demo1.model.PersonDAO;

/*
 * The JUnit Test Case. 
 */
public class PersonDAOTests {

	@BeforeClass //Run once before all the Tests
	public static void setUpBeforeClass() throws Exception {
		System.out.println("set up before class");
		
		Database.getInstance().connect(); // Set up your Database.
	}

	@AfterClass //Run once after all the Tests are complete
	public static void tearDownAfterClass() throws Exception {
		System.out.println("tear down after class");
		
		Database.getInstance().disconnect(); // Disconnect from your Database.
	}

	@Before //runs before every Test
	public void setUp() throws Exception { 
		System.out.println("set up");
		
		//Make sure your Database is initialized in a known state before you run every test
		PersonDAO dao = DAOFactory.getPersonDAO();
		dao.deleteAll(); // I really should put in the raw code instead of the method, but I am lazy.
		                 // Basically using our method to delete the sql data.
	}

	@After //runs after every Test
	public void tearDown() throws Exception { 
		System.out.println("tear down");
	}
	
	//
	@Test
	public void testDemo() {
		//fail("Not yet implemented"); //fail is a method in junit.Assert It should be used as Assert.fail but since we import static, we can use fail directly.
		
		int value = 7;
		value += 2;
		
		System.out.println("Running demo test");
		
		assertEquals("Check that arithmetic works in Java!",9,value); // (expected -> what it should be, actual -> the variable that you are testing)
	}
	
	@Test
	public void testCreate() throws SQLException{
		PersonDAO dao = DAOFactory.getPersonDAO(); // Important because we need the connection.
		Person person1 = new Person("Bob","letmein");
		Person person2 = new Person("Sue","hello");
		
		dao.addPerson(person1);
		dao.addPerson(person2);
		
		List<Person> people = dao.getPeople();
		
		
		assertEquals("Should be two people in database.",2,people.size());
		assertEquals("These two people should be the same.",person1,people.get(0));
		assertEquals("These two people should be the same.",person2,people.get(1));
		// Since I am comparing between custom objects, I will need to specify the .equals method in the Person class.
	}

}
