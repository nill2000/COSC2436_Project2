//Stephen Lin
//Nina Javaher
//Nov 15 2024
//COSC2436 Project 2

/*
 1. Update Courses class name in student's course after modifying course
 2. Do not delete course if student is enrolled
 3. Create msg if student is already enrolled
 4. Format CLI better
 */

import java.util.Scanner;
public class Main{
	static Enrollment enrollmentObj = new Enrollment();
	static Scanner scannerObj = new Scanner(System.in);

	public static void menuList(){
		System.out.println("Choose Option (Type Integer):");
		System.out.println("1. Add Student");
		System.out.println("2. Enroll Student");
		System.out.println("3. Drop Student");
		System.out.println("4. Display Student Details");
		System.out.println("5. Add Course");
		System.out.println("6. Delete Course");
		System.out.println("7. Display Course Details");
		System.out.println("8. Display Course Enrollment Status");
		System.out.println("9. Modify Course");
		System.out.println("10. Check Schedule");
		System.out.println("11. Display All Students");
		System.out.println("12. Display All Courses");
		System.out.println("-1. Exit Program");
		int menuChoice = scannerObj.nextInt();

		switch(menuChoice){
			case -1:
				System.out.println("Exiting Program");
				System.exit(0);
			case 1:
				enrollmentObj.addStudent();
				break;
			case 2:
				enrollmentObj.enrollStudent();
				break;
			case 3:
				enrollmentObj.dropStudent();
				break;
			case 4:
				enrollmentObj.displayStudent();
				break;
			case 5:
				enrollmentObj.addCourse();
				break;
			case 6:
				enrollmentObj.deleteCourse();
				break;
			case 7:
				enrollmentObj.displayCourse();
				break;
			case 8:
				enrollmentObj.displayEnrollStatus();
				break;
			case 9:
				enrollmentObj.modifyCourse();
				break;
			case 10:
				enrollmentObj.checkCurSchedule();
				break;
			case 11:
				enrollmentObj.displayAllStudents();
				break;
			case 12:
				enrollmentObj.displayAllCourses();
				break;
			default:
				System.out.println("Invalid Input \nExiting Program");
				System.exit(0);
		}
		System.out.println();
	}

	public static void main(String[] args){
		System.out.println("Main Class Success");

		Boolean Running = true;
		while(Running){
			menuList();
		}
	}
}