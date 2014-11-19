
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.*;



/**
 * @author Matthew Alpert
 * @version 1.5
 * @created 16-Oct-2014 3:32:01 AM
 * This class creates a student object with various data fields.
 * It includes a personal information, transcript, courses currently
 * bid on, and a current schedule (after the auction has run).  
 */
public class Student {

	/**
	 * Array of objects, first bracket is the rows and where the course object is held,
	 * second bracket is the number of columns and the number of points bid on the
	 * course. Only two columns, second number should never exceed 1 (0 is first
	 * column, 1 is second).
	 */
	private ArrayList<BidCourse> bidCourses;
	private ArrayList<GradedCourse> coursesTaken;
	private ArrayList<Course> currentSchedule;
	private int numID;
	private int numPoints;
	private String password;
	private String username;
	private String name, address;
	private int dataRow;
	Workbook workbook;
	WritableWorkbook copy;
	WritableSheet writeSheet;
	WritableCell writeCell;
	
	/**
	 * @author Matthew Alpert
	 * @param coursesTaken - ArrayList of type GradedCourse which hold a list of courses graded and taken. Used in transcript
	 * @param numID - the student's ID number
	 * @param numPoints - total number of points the student can use to bid on classes
	 * @param password - student's login password
	 * @param username - student's login username
	 * @param name - student's personal name
	 * @param address - student's personal address
	 * @param dataRow - for database use, indicates the row the student is located in
	 */
	public Student(ArrayList<GradedCourse> coursesTaken, ArrayList<BidCourse> bidCourses, ArrayList<Course> currentSchedule,int numID, int numPoints,
			String password, String username, String name, String address, int dataRow) {
		this.coursesTaken = coursesTaken;
		this.numID = numID;
		this.numPoints = numPoints;
		this.password = password;
		this.username = username;
		this.name = name;
		this.address = address;
		this.bidCourses = bidCourses;
		this.currentSchedule = currentSchedule;
		this.dataRow = dataRow;
		
		//just to get it working for now
		try {
			workbook = Workbook.getWorkbook(new File("Student Database.xls"));
			copy = Workbook.createWorkbook(new File("Student Database Copy.xls"));
			writeSheet = copy.getSheet(0);
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @author Matthew Alpert
	 * @param course - the course the student is bidding on to take for the next semester, will add to the database
	 * @param bidPoints - number of points the student is using for the bid on the course, will add to the database
	 * @return boolean - whether or not the student has successfully added the class
	 * Edit 11/15/14 by Courtney Fennell - Change return value from void to boolean and fix a logical error.
	 */
	public boolean addCourse(Course course, int bidPoints){
		if(!coursesTaken.contains(course) && bidPoints <= numPoints){
			bidCourses.add(new BidCourse(course.getCourseNum(), course.getCredits(),
					course.getCorequisite(), course.getPrerequisites(),
					course.getCourseDescription(), course.getTimeSlot(), course.getDataColCourse(), bidPoints));
			numPoints-=bidPoints;
			System.out.println(bidPoints);
			System.out.println(bidCourses);
			return true;
//			write to database
			
		}else{
			return false;
		}
		
	}

	/**
	 * @author Matthew Alpert
	 * @param dropCourse - BidCourse object that the student wishes to drop their bid for
	 */
	public void dropCourse(BidCourse dropCourse){
		numPoints += dropCourse.getBid();
		bidCourses.remove(dropCourse);
//		write to database
		
	}

	/**
	 * @author Matthew Alpert
	 * @return the currentSchedule of the student
	 * currentSchedule will only have Course objects inside it after the auction has run
	 */
	public ArrayList<Course> getCurrentSchedule() {
		return currentSchedule;
	}

	/**
	 * @author Matthew Alpert
	 * @param currentSchedule - sets the schedule that the student has been placed into to
	 */
	public void setCurrentSchedule(ArrayList<Course> currentSchedule) {
		this.currentSchedule = currentSchedule;
//		write to database
	}

	/**
	 * @author Matthew Alpert
	 * @return current the number of points a student has to bid on courses
	 */
	public int getNumPoints() {
		return numPoints;
	}

	/**
	 * @author Matthew Alpert
	 * @param numPoints - current total number of points the student can use to bid on classes
	 */
	public void setNumPoints(int numPoints) {
		this.numPoints = numPoints;
//		write to database
	}

	/**
	 * @author Matthew Alpert
	 * @return the student's ID number
	 */
	public int getNumID() {
		return numID;
	}

	/**
	 * @author Matthew Alpert
	 * @return student's login password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @author Matthew Alpert
	 * @return student's login username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @author Matthew Alpert
	 * @return student's personal address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @author Matthew Alpert
	 * @param text - student's personal name
	 */
	public void setName(String text) {
		name = text;
//		write to database
	}

	/**
	 * @author Matthew Alpert
	 * @return student's personal name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @author Matthew Alpert
	 * @param text - student's personal address
	 */
	public void setAddress(String text) {
		address = text;
//		write to database
	}
	
	/**
	 * @author Matthew Alpert
	 * @return ArrayList of type GradedCourse which hold a list of courses graded and taken. Used in transcript
	 */
	public ArrayList<GradedCourse> getCoursesTaken(){
		return coursesTaken;
	}
	/**
	 * @author Courtney Fennell
	 * @return ArrayList of type BidCourse which hold a list of courses the student has bid on.
	 */
	public ArrayList<BidCourse> getBidCourses(){
		return bidCourses;
	}
	
	
//	finish up later
	private void writeDatabase(int col, String label){
		writeCell = writeSheet.getWritableCell(dataRow, col);
	}
	
	private void writeDatabase(int col, int num){
		
	}
	
}//end Student