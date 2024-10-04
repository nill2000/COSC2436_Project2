import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Enrollment {
	Course courseObj = new Course(0, null, null ,0, 0);
	static Student studentObj = new Student(0, null, 0, null);
	static Scanner scannerObj = new Scanner(System.in);
	static HashMap<Integer, Student> studentHashObj = new HashMap<>();
	static HashMap<Integer, Course> courseHashObj = new HashMap<>();
	static PriorityQueue<Integer> priorityQueueObj = new PriorityQueue<>();

	//Program needs to check if id is valid when entered for all cases

	//COMPLETE
	static void addStudent(){
		System.out.println("Student ID (4 Digits and Zero can't be first digit):");
		int stuID = scannerObj.nextInt();
		while(true){ //Checks if input is within range
			if (stuID >= 1000 && stuID <= 9999){
				break;
			} else {
				System.out.println("Invalid Input. 4 Digits & Zero can't be first:");
				stuID = scannerObj.nextInt();
			}
		}

		scannerObj.nextLine();

		//Gets Student Name
		System.out.println("Student Name:");
		String stuName = scannerObj.nextLine();

		//Gets Student Age within 18-40
		System.out.println("Student Age (18-40 for Simplicity) :");
		int stuAge = scannerObj.nextInt();
		while(true){ //Checks if input is in range
			if (stuAge >= 18 & stuAge <= 40){
				break;
			} else {
				System.out.println("Invalid Input. Student Age (18-40):");
				stuAge = scannerObj.nextInt();
			}
		}

		scannerObj.nextLine();

		//Gets Student Major
		System.out.println("Student Major:");
		String stuMajor = scannerObj.nextLine();

		//Creates the Student object with given info
		Student studentObj = new Student(stuID, stuName, stuAge, stuMajor);

		//Saves the Student into a Hash with ID as their key
		studentHashObj.put(stuID, studentObj);
	}

	//COMPLETE - Needs to check if ID is available
	static void enrollStudent(){
		//Searches for student ID
		System.out.println("Which Student? (Type ID)");
		int stuID = scannerObj.nextInt();

		stuID = checkStudentID(stuID);

		if(stuID == -1){ //Exit Method and Back to Menu List
			System.out.println("Exiting enrollStudent Method...");
			return;
		}

		//Searches for Course ID
		System.out.println("Which Course to Enroll? (Type ID)");
		int courID = scannerObj.nextInt();

		courID = checkCourseID(courID);

		if(courID == -1){ //Exit Method and Back to Menu List
			System.out.println("Exiting enrollStudent Method...");
			return; 
		}

		Student curStudent = studentHashObj.get(stuID);
		Course curCourse = courseHashObj.get(courID);

		//Check if Capacity is available. If so, add student, and if not, dont add.
		//Increase enrollment by 1 count
		//Save the enrolled class into the students list somewhere
		if(curCourse.enrolledStudents == curCourse.capacity){
			System.out.println("Enrollment Status is Closed");
		} else {
			curCourse.increaseCount();
			System.out.println(curStudent.getName() + " has been enrolled into");
			System.out.println("Course ID: " + curCourse.getCourseID());
			System.out.println("Course Name: " + curCourse.getCourseName());
			curStudent.enrolledCourses.add(curCourse.courseName);
		}
	}

	//COMPLETE Needs to check if ID is available
	static void dropStudent(){
		System.out.println("Which Student? (Type ID)");
		int stuID = scannerObj.nextInt();

		stuID = checkStudentID(stuID);

		if(stuID == -1){ //Exit Method and Back to Menu List
			System.out.println("Exiting enrollStudent Method...");
			return;
		}

		System.out.println("Which Course to Drop? (Type ID)");
		int courID = scannerObj.nextInt();

		courID = checkCourseID(courID);

		if(courID == -1){ //Exit Method and Back to Menu List
			System.out.println("Exiting enrollStudent Method...");
			return; 
		}

		Student curStudent = studentHashObj.get(stuID);
		Course curCourse = courseHashObj.get(courID);

		//Check if there are any students. if none, give error, and if so, remove student and from their list.
		if(curCourse.enrolledStudents == 0){
			System.out.println("Course has 0 Students \nCannot drop");
		} else {
			curCourse.removeCount();
			System.out.println(curStudent.getName() + " has been dropped from");
			System.out.println("Course ID: " + curCourse.getCourseID());
			System.out.println("Course Name: " + curCourse.getCourseName());
			curStudent.enrolledCourses.remove(curCourse.courseName);
		}
	}

	//Gets the student ID from hash and display their details. COMPLETE
	static void displayStudent(){
		System.out.println("Which Student (Type ID)?");
		int stuID = scannerObj.nextInt();

		stuID = checkStudentID(stuID);

		if(stuID == -1){ //Exit Method and Back to Menu List
			System.out.println("Exiting displayStudent Method...");
			return;
		}

		System.out.println(studentHashObj.get(stuID).getStudentDetails());
	}

	//COMPLETE
	static void displayAllStudents(){
		if(studentHashObj.isEmpty()){
			System.out.println("No Students in System");
		} else {
			for(Student _students: studentHashObj.values()){
			System.out.println(_students.getStudentDetails());
			}
		}
	}

	//COMPLETE
	static void addCourse(){
		System.out.println("Course ID (Four Digits & Zero cant be first):");
		int courID = scannerObj.nextInt();

		while(true){ //Checks if input is within range
			if (courID >= 1000 && courID <= 9999){
				break;
			} else {
				System.out.println("Invalid Input. 4 Digits & Zero can't be first:");
				courID = scannerObj.nextInt();
			}
		}

		scannerObj.nextLine();

		System.out.println("Course Name:");
		String courName = scannerObj.nextLine();

		System.out.println("Instructor Name:");
		String courInstructor = scannerObj.nextLine();
	
		System.out.println("Course Capacity (5-10 For Simplicity)");
		int courCapacity = scannerObj.nextInt();

		System.out.println("");

		Course courseObj = new Course(courID, courName, courInstructor, courCapacity, 0);
		courseHashObj.put(courID, courseObj);
	}

	//COMPLETE
	static void deleteCourse(){
		System.out.println("Which Course to Delete? (Type ID)");
		int courID = scannerObj.nextInt();

		courID = checkCourseID(courID);

		if(courID == -1){ //Exit Method and Back to Menu List
			System.out.println("Exiting deleteCourse Method...");
			return; 
		}

		System.out.println(courseHashObj.remove(courID).getCourseDetails() + " has been removed");
	}

	//MAYBE COMPLETE
	//Check ID Availability and Range
	static void modifyCourse(){
		System.out.println("Which Course to Modify? (Type ID)");
		int courID = scannerObj.nextInt();
		Course curCourse = courseHashObj.get(courID);

		courID = checkCourseID(courID);

		if(courID == -1){ //Exit Method and Back to Menu List
			System.out.println("Exiting modifyCourse Method...");
			return; 
		}

		//Make sure courseIDs do not match one another
		//If change capacity, check enrolled students first
		System.out.println("If no change, type the same info");
		System.out.println("Course ID: ");
		int changeCourID = scannerObj.nextInt();

		while(true){ //Checks if input is within range
			if (changeCourID >= 1000 && changeCourID <= 9999){
				break;
			} else {
				System.out.println("Invalid Input. 4 Digits & Zero can't be first:");
				changeCourID = scannerObj.nextInt();
			}
		}

		System.out.println("Course Name: ");
		String changeCourName = scannerObj.nextLine();

		System.out.println("Course Instructor: ");
		String changeCourInstructor = scannerObj.nextLine();

		System.out.println("Course Capacity: ");
		int changeCourCapacity = scannerObj.nextInt();

		//Check ID Availability
		while(true){
			if(courseHashObj.containsKey(changeCourID)){
			System.out.println("ID is in use; Try another");
			System.out.println("Course ID: ");
			changeCourID = scannerObj.nextInt();	
		} else {
			System.out.println("ID has been Changed");
			courseHashObj.put(changeCourID, curCourse);
			curCourse.setCourseID(changeCourID);
			courseHashObj.remove(courID);
			break;
			}
		}

		//Check Course Capacity
		while(true){
			if(changeCourCapacity < curCourse.enrolledStudents){
			System.out.println("Cannot reduce capacity because of the amount of students already enrolled");
			System.out.println("Try Again. Course Capacity: ");
		    changeCourCapacity = scannerObj.nextInt();
		} else {
			System.out.println("Capacity has been changed");
			curCourse.setCapacity(changeCourCapacity);
			break;
			}
		}
		
		curCourse.setCourseName(changeCourName);
		curCourse.setInstructor(changeCourInstructor);
	}

	//COMPLETE
	static void displayCourse(){
		System.out.println("Which Course to Display? (Type ID)");
		int courID = scannerObj.nextInt();

		courID = checkCourseID(courID);

		if(courID == -1){ //Exit Method and Back to Menu List
			System.out.println("Exiting displayCourse Method...");
			return; 
		}

		System.out.println(courseHashObj.get(courID).getCourseDetails());

	}

	//COMPLETE
	static void displayEnrollStatus(){
		System.out.println("Which Course to Check Availability? (Type ID)");
		int courID = scannerObj.nextInt();

		courID = checkCourseID(courID);

		if(courID == -1){ //Exit Method and Back to Menu List
			System.out.println("Exiting enrollStatus Method...");
			return;
		}

		Course curCourse = courseHashObj.get(courID);
		System.out.println("Course Name: " + curCourse.courseName);
		System.out.println("Course Capacity: " + curCourse.capacity);
		System.out.println("Enrollment Amount: " + curCourse.enrolledStudents);

		//If enrolledStudents match courseCapacity, enrollment is closed
		if(curCourse.enrolledStudents == curCourse.capacity){
			System.out.println("Enrollment Status is Closed");
		} else {
			System.out.println("Enrollment Status is Open");
		}

	}

	//COMPLETE
	static void displayAllCourses(){
		if(courseHashObj.isEmpty()){
			System.out.println("No Courses in System");
		} else {
			for(Course _courses: courseHashObj.values()){
			System.out.println(_courses.getCourseDetails());
			}
		}
	}

	//Checks if studentID is in System
	static int checkStudentID(int stuIDParams){
		while(!studentHashObj.containsKey(stuIDParams)){
			if(stuIDParams == -1){
				return stuIDParams;
			}
			System.out.println("ID Doesn't Exist \nTry again or type -1 to Exit");
			stuIDParams = scannerObj.nextInt();
		}
		return stuIDParams;
	}

	//Checks if CourseID is in System
	static int checkCourseID(int courIDParams){
		while(!courseHashObj.containsKey(courIDParams)){
			if(courIDParams == -1){
				return courIDParams;
			}
			System.out.println("ID Doesn't Exist \nTry again or type -1 to Exit");
			courIDParams = scannerObj.nextInt();
		}
		return courIDParams;
	}

	//MAYBE COMPLETE
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
		System.out.println("-1. Exit Program");
		int menuChoice = scannerObj.nextInt();

		switch(menuChoice){
			case -1:
				System.out.println("Exiting Program");
				System.exit(0);
			case 1:
				addStudent();
				break;
			case 2:
				enrollStudent();
				break;
			case 3:
				dropStudent();
				break;
			case 4:
				displayStudent();
				break;
			case 5:
				addCourse();
				break;
			case 6:
				deleteCourse();
				break;
			case 7:
				displayCourse();
				break;
			case 8:
				displayEnrollStatus();
				break;
			default:
				System.out.println("Invalid Input \nExiting Program");
				System.exit(0);
		}
	}

	public static void main(String[] args){
		System.out.println("Enrollment Class Sucess");

		Boolean Running = true;
		while(Running){
			menuList();
		}

	}
}
