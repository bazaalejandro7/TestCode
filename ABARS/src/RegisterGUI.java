import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import sun.awt.RepaintArea;
import jxl.read.biff.BiffException;

/**
 * Course: SE 300- 01
 * Term: Fall 2014
 * Final Project
 * @author courtneyfennell
 * @created October 29, 2014
 */
@SuppressWarnings("serial")
public class RegisterGUI extends JPanel {

	Student currStudent;
	ArrayList<GradedCourse> gradedCourses;
	ArrayList<Course> allCourses;
	int i = 0;
	CourseDatabase cd;

	//this is where a student can add a class
	/** This constructor creates the panel that displays all the classes a student can choose to register for
	 * 
	 * @param student
	 * @throws BiffException
	 * @throws IOException
	 */
	public RegisterGUI(Student student) throws BiffException, IOException{
		currStudent = student;
		cd = new CourseDatabase();
		allCourses = cd.getCourseList();
		gradedCourses = currStudent.getCoursesTaken();
		i = gradedCourses.size();

		//add layout manager
		this.setLayout(new GridLayout((30), 2));
		//TODO figure out how to set the layout so that it works for all students

		this.add(new JLabel("Course Name:"));
		this.add(new JLabel("Credits:"));

		//continue from same spot for the rest of the courses without grades
		for(;i<allCourses.size();i++){

			Course currCourse = allCourses.get(i);
			JRadioButton currLabel = new JRadioButton(currCourse.getCourseNum());
			currLabel.addActionListener(new Listener());
			currLabel.setActionCommand(Integer.toString(i));



			this.add(currLabel);

			JLabel creditsLabel = new JLabel(Integer.toString(currCourse.getCredits()));
			this.add(creditsLabel);
		}
	}


	class Listener implements ActionListener { // Inner class
		public void actionPerformed(ActionEvent e) {

			int action = Integer.parseInt(e.getActionCommand());
			Course chosenCourse = allCourses.get(action);
		
			String text = String.format("You have %d points.\nEnter the number of points you want to bid:", currStudent.getNumPoints());
			String input = JOptionPane.showInputDialog(text);

					if (input !=null) {
						boolean success = currStudent.addCourse(chosenCourse,Integer.parseInt(input));
						if(success){
							ImageIcon image = new ImageIcon(getClass().getResource("zoidberg.jpg"));
							JOptionPane.showMessageDialog(null, 
									"You have successfully added your class!", 
									"Add Class Confimation", JOptionPane.PLAIN_MESSAGE , image);

						}
						else{
							ImageIcon image = new ImageIcon(getClass().getResource("127.gif"));
							JOptionPane.showMessageDialog(null, 
									"I'm sorry, you cannot add that class", 
									"Add Class Error", JOptionPane.PLAIN_MESSAGE , image);
						}
					
					}
		}

	}
}

