import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jxl.read.biff.BiffException;

/*
 * Courtney Fennell
 * 
 */
@SuppressWarnings("serial")
public class DegreeAuditGUI extends JPanel {

	Student currStudent;
	ArrayList<GradedCourse> gradedCourses;
	ArrayList<Course> allCourses;
	CourseDatabase cd;

	int i;

	public DegreeAuditGUI(Student Student1) throws BiffException, IOException{
		cd = new CourseDatabase();
		currStudent = Student1;
		allCourses = cd.getCourseList();
		
		gradedCourses = currStudent.getCoursesTaken();



		this.setLayout(new GridLayout(allCourses.size()+1, 3));
		this.add(Box.createHorizontalStrut(5));
		this.add(new JLabel("Course Name:"));
		this.add(new JLabel("Grade:"));
		this.add(new JLabel("Credits:"));

		for(i = 0; i<gradedCourses.size();i++){
			this.add(Box.createHorizontalStrut(5));
			GradedCourse course =gradedCourses.get(i);

			String currCourse = course.getCourseNum();
			JLabel currLabel = new JLabel(currCourse);
			this.add(currLabel);

			JLabel currGradeLabel = new JLabel(course.getGrade());
			this.add(currGradeLabel);

			JLabel creditsLabel = new JLabel(Integer.toString(course.getCredits()));
			this.add(creditsLabel);
		}

		//continue from same spot for the rest of the courses without grades
		for(;i<allCourses.size();i++){
			
			this.add(Box.createHorizontalStrut(5));
			Course currCourse = allCourses.get(i);
			JLabel currLabel = new JLabel(currCourse.getCourseNum());


			this.add(currLabel);
			this.add(new JLabel("NA"));

			JLabel creditsLabel = new JLabel(Integer.toString(currCourse.getCredits()));
			this.add(creditsLabel);
		}

	}

}
