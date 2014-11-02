import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class TranscriptGUI extends JPanel{
	ArrayList<GradedCourse> gradedCourses;
	Student currStudent;
	
	public TranscriptGUI(Student student){
		currStudent = student;
		gradedCourses = currStudent.getCoursesTaken();
		
		this.setLayout(new GridLayout(gradedCourses.size()+1, 3));
		this.add(Box.createHorizontalStrut(5));
		this.add(new JLabel("Course Name:"));
		this.add(new JLabel("Grade:"));
		this.add(new JLabel("Credits:"));
		
		for(int i = 0; i<gradedCourses.size();i++){
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
		
	}
	
}
