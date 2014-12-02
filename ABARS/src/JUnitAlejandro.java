import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This  class is responsible for testing the AdministratorloginCheck method.
 * 
 * @version 1.0
 * @author Alejandro Baza
 */

public class JUnitAlejandro {

	@BeforeClass
	// This method is called only once at the beginning, each time JUnit is run.
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Preparing to run unit tests...\n");
	}

	@AfterClass
	// This method is called only once at the end, each time JUnit is run.
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Exiting unit tests...\n");
	}

	@Before
	// This method is called right before each individual @Test method,
	// but AFTER the class constructor is called.
	public void setUp() throws Exception {
		System.out.println("Starting a test\n");
	}

	@After
	// This method is called right after each individual @Test method.
	public void tearDown() throws Exception {
		System.out.println("Test complete\n");
	}

	@Test
	// This test checks the validation of the Admnistrator's username
	public void testAdministratorUsername() {
		AdministratorDatabase AdministratorDB = null;
		try {
			AdministratorDB = new AdministratorDatabase();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(AdministratorDB.AdministratorLoginCheck("Sherrinford", ""), null);
	}

	@Test
	// This test checks the validation of the Administrator's password
	public void testAdministratorPassword() {
		AdministratorDatabase AdministratorDB = null;
		try {
			AdministratorDB = new AdministratorDatabase();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(AdministratorDB.AdministratorLoginCheck("", "Hope"), null);
	}
}

