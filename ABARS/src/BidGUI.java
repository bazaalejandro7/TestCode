
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

import jxl.read.biff.BiffException;
/**
 * Course: SE 300- 01
 * Term: Fall 2014
 * Final Project
 * @author courtneyfennell
 * @created November 7, 2014
 */

@SuppressWarnings("serial")
public class BidGUI extends JPanel {
	Student currStudent;
	ArrayList<BidCourse> bidCourses;

	/**This panel displays all of the classes that the student has bid for before the auction runs
	 * 
	 * @param student
	 * @throws BiffException
	 * @throws IOException
	 */
	public BidGUI(Student student) throws BiffException, IOException{
		//this is where a student can drop a class
		currStudent = student;
		bidCourses = currStudent.getBidCourses();

		//add layout manager
		this.setLayout(new GridLayout((8), 2));
		//TODO figure out how to set the layout so that it works for all students

		this.add(new JLabel("Course Name:"));
		this.add(new JLabel("Credits:"));
		//display all courses
		for(int i = 0;i<bidCourses.size();i++){
			Course currCourse = bidCourses.get(i);
			JRadioButton currLabel = new JRadioButton(currCourse.getCourseNum());
			currLabel.addActionListener(new DropListener());
			currLabel.setActionCommand(Integer.toString(i));

			this.add(currLabel);

			JLabel creditsLabel = new JLabel(Integer.toString(currCourse.getCredits()));
			this.add(creditsLabel);
		}

	}
	public void setupPanel() throws BiffException, IOException{
		if(!currStudent.getBidCourses().isEmpty()){
			System.out.println(currStudent.getBidCourses());
			new BidGUI(currStudent);
		}
		else{
			ImageIcon image = new ImageIcon(getClass().getResource("fry.gif"));
			JOptionPane.showMessageDialog(null, 
					"I'm sorry there's nothing here. Try bidding on a class first.", 
					"Nothing is here", JOptionPane.PLAIN_MESSAGE , image);
		}
	}


	/**
	 * This action listener listens for the class to be chosen
	 * @author courtneyfennell
	 *
	 */
	class DropListener implements ActionListener { // Inner class
		public void actionPerformed(ActionEvent e) {

			int action = Integer.parseInt(e.getActionCommand());
			BidCourse chosenCourse = bidCourses.get(action);

			currStudent.dropCourse(chosenCourse);
			ImageIcon image = new ImageIcon(getClass().getResource("zoidbergescape.gif"));
			JOptionPane.showMessageDialog(null, 
					"You have successfully dropped your class!", 
					"Drop Class Confimation", JOptionPane.PLAIN_MESSAGE , image);


		}
	}
}




