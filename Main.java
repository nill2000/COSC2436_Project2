//Stephen Lin
//Nina Javaher
//Nov 15 2024
//COSC2436 Project 2

/*
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
				System.out.println();
				enrollmentObj.addStudent();
				break;
			case 2:
				System.out.println();
				enrollmentObj.enrollStudent();
				break;
			case 3:
				System.out.println();
				enrollmentObj.dropStudent();
				break;
			case 4:
				System.out.println();
				enrollmentObj.displayStudent();
				break;
			case 5:
				System.out.println();
				enrollmentObj.addCourse();
				break;
			case 6:
				System.out.println();
				enrollmentObj.deleteCourse();
				break;
			case 7:
				System.out.println();
				enrollmentObj.displayCourse();
				break;
			case 8:
				System.out.println();
				enrollmentObj.displayEnrollStatus();
				break;
			case 9:
				System.out.println();
				enrollmentObj.modifyCourse();
				break;
			case 10:
				System.out.println();
				enrollmentObj.checkCurSchedule();
				break;
			case 11:
				System.out.println();
				enrollmentObj.displayAllStudents();
				break;
			case 12:
				System.out.println();
				enrollmentObj.displayAllCourses();
				break;
			default:
				System.out.println("Invalid Input \nExiting Program");
				System.exit(0);
		}
		System.out.println();
	}

	public static void main(String[] args){
		System.out.println("Main Class Success \n");

		Boolean Running = true;
		while(Running){
			System.out.println();
			menuList();
		}
	}
}