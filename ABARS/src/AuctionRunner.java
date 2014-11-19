import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;


/**
* Author: Alejandro Baza
* Date: 2014-10-11
* Description: This file contains the methods that control the action of courses
*
* Change log:
*	<2014-10-11> Initial version created
*
* References:
* N/A
*/

public class AuctionRunner {
	
	/**
	 * 
	 * @author Alejandro Baza
	 * This method puts the students that bid for courses into an arraylist
	 *
	 */
	
	public void StudentBidList() {
		
	}
	
	/**
	 * @author Alejandro Baza
	 * This method sorts the list of students that bid for courses alphabetically
	 * 
	 */
	
	public void SortList() {
		Collator col = Collator.getInstance(new Locale("en", "EN"));
		String s = "AbaC";
		String[] s1= s.split("");
		Arrays.sort(s1, col);
		String sorted = "";
		for (int i = 0; i < s1.length; i++)
		{
		  sorted += s1[i];
		}
	}
	
	/**
	 * @author Alejandro Baza
	 * This method adds courses to the student's current schedule
	 * 
	 */
	
	public void AddCourseToSchedule() {
		
	}
}