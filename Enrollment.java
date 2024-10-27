import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Comparator;

public class Enrollment {
	Course courseObj = new Course(0, null, null ,0, 0); //Initializes a Course Class - courseObj
	static Student studentObj = new Student(0, null, 0, null); //Initializes a Student Class - studentObj
	static Scanner scannerObj = new Scanner(System.in); //Initializes a Scanner for input - scannerObj
	static HashMap<Integer, Student> studentHashObj = new HashMap<>(); //Initializes a Hashmap - studentHashObj - that takes StuID as key and Student Obj as value
	static HashMap<Integer, Course> courseHashObj = new HashMap<>(); //Initializes a Hashmap - courseHashObj - that takes courseID as key and Course Obj as value
	static Comparator<Course> compareCourseObj = new Comparator<Course>() { // Initializes a Comparator class for comparing class priority for scheduling based on more students
		public int compare(Course one, Course two){
			return Integer.compare(two.enrolledStudents, one.enrolledStudents);
		}
	};

	static PriorityQueue<Course> pqScheduleObj = new PriorityQueue<>(compareCourseObj); //Initializes a PQ class that takes Course Class Objects - pqScheduleObj
	//Program needs to check if id is valid when entered for all cases

	//Function that adds a student 
	static void addStudent(){
		//Scanner that takes student ID
		System.out.println("Student ID (4 Digits and Zero can't be first digit):"); 
		int stuID = scannerObj.nextInt();

		//Function checks if stuID is in-use or within range
		stuID = checkStudentID_InUseAndRange(stuID); 

		scannerObj.nextLine();

		//Scanner that takes student name
		System.out.println("Student Name:"); 
		String stuName = scannerObj.nextLine();

		//Scanner that takes student age within 18-40
		System.out.println("Student Age (18-40 for Simplicity) :"); 
		int stuAge = scannerObj.nextInt();
		while(true){ //Condition statment checks if student age is within range
			if (stuAge >= 18 & stuAge <= 40){
				break;
			} else {
				System.out.println("Invalid Input. Student Age (18-40):");
				stuAge = scannerObj.nextInt();
			}
		}

		scannerObj.nextLine();

		//Scanner that takes student major
		System.out.println("Student Major:"); 
		String stuMajor = scannerObj.nextLine();

		//Creates the Student object with given info
		Student studentObj = new Student(stuID, stuName, stuAge, stuMajor);

		//Saves the Student into a Hashmap with ID as their key
		studentHashObj.put(stuID, studentObj);
	}

	//Function enrolls student into a course
	static void enrollStudent(){
		//Scanner that takes student ID
		System.out.println("Which Student? (Type ID)");
		int stuID = scannerObj.nextInt();

		//Checks if Student ID is in Hashmap
		stuID = checkStudentIDinHash(stuID);

		//Exit enrollStudent method and back to menu List
		if(stuID == -1){
			System.out.println("Exiting enrollStudent Method...");
			return;
		}

		//Scanner that takes Course ID
		System.out.println("Which Course to Enroll? (Type ID)");
		int courID = scannerObj.nextInt();

		//Checks if CourseID is in Hash
		courID = checkCourseIDinHash(courID);

		//Exit enrollStudent method and back to menu List
		if(courID == -1){ 
			System.out.println("Exiting enrollStudent Method...");
			return; 
		}

		//If everything passes, create a variable that takes the current student/course
		Student curStudent = studentHashObj.get(stuID);
		Course curCourse = courseHashObj.get(courID);

		//Check if student is already enrolled
		if(curStudent.enrolledCourses.contains(curCourse.courseName)){
			return; //If student is already enrolled in the course or similar course, do not enroll and exit the method
		}

		//Check if the course's capacity is available.
		if(curCourse.enrolledStudents == curCourse.capacity){ //If not, do not add student
			System.out.println("Enrollment Status is Closed");
		} else { //If available, increase the course count by 1 and add the student
			curCourse.increaseCount();
			System.out.println(curStudent.getName() + " has been enrolled into");
			System.out.println("Course ID: " + curCourse.getCourseID());
			System.out.println("Course Name: " + curCourse.getCourseName());
			curStudent.enrolledCourses.add(curCourse.courseName);
		}
	}

	//Function that drops student from course
	static void dropStudent(){
		//Scanner takes student ID
		System.out.println("Which Student? (Type ID)");
		int stuID = scannerObj.nextInt();

		//Checks if Student ID is in Hash
		stuID = checkStudentIDinHash(stuID);
        
		//Exit dropStudent method and back to menu List
		if(stuID == -1){ 
			System.out.println("Exiting enrollStudent Method...");
			return;
		}

		//Scanner takes course ID
		System.out.println("Which Course to Drop? (Type ID)");
		int courID = scannerObj.nextInt();

		//Checks if courID is in HashMap
		courID = checkCourseIDinHash(courID);
        
		//Exit dropStudent method and back to menu List
		if(courID == -1){ 
			System.out.println("Exiting enrollStudent Method...");
			return; 
		}

		Student curStudent = studentHashObj.get(stuID);
		Course curCourse = courseHashObj.get(courID);

		//Checks if student is attending the course
		if (curStudent.enrolledCourses.contains(curCourse.courseName)){
			curCourse.removeCount();
			System.out.println(curStudent.getName() + " has been dropped from");
			System.out.println("Course ID: " + curCourse.getCourseID());
			System.out.println("Course Name: " + curCourse.getCourseName());
			curStudent.enrolledCourses.remove(curCourse.courseName);
		} else { //If not, notify user and do nothing
			System.out.println("Student is not Enrolled in " + curCourse.courseName);
			System.out.println("Returning to Menu...");
		}

		/* 
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
		*/
	}

	//Function that displays a specific student's details
	static void displayStudent(){
		//Scanner takes student ID
		System.out.println("Which Student (Type ID)?");
		int stuID = scannerObj.nextInt();

		//Checks if Student ID is in Hash
		stuID = checkStudentIDinHash(stuID);

		//Exit displayStudent Method and Back to Menu List
		if(stuID == -1){ 
			System.out.println("Exiting displayStudent Method...");
			return;
		}

		System.out.println(studentHashObj.get(stuID).getStudentDetails());
	}

	//Function that displays all the students and their details
	static void displayAllStudents(){
		if(studentHashObj.isEmpty()){
			System.out.println("No Students in System");
		} else {
			for(Student _students: studentHashObj.values()){
			System.out.println(_students.getStudentDetails());
			}
		}
	}

	//Function to add course
	static void addCourse(){
		//Scanner takes course ID
		System.out.println("Course ID (Four Digits & Zero cant be first):");
		int courID = scannerObj.nextInt();

		//CHecks if ID is already in use or out of range
		courID = checkCourseID_InUseAndRange(courID);

		scannerObj.nextLine();

		//Scanner takes course name
		System.out.println("Course Name:");
		String courName = scannerObj.nextLine();

		//Scanner takes instructor name
		System.out.println("Instructor Name:");
		String courInstructor = scannerObj.nextLine();
	
		//Scanner takes course capacity
		System.out.println("Course Capacity (5-10 For Simplicity)");
		int courCapacity = scannerObj.nextInt();

		//Condition to check course capacity
		while(true){
			if(courCapacity < 5 || courCapacity > 10){
				System.out.println("Invalid Input. Try Again.");
				System.out.println("Course Capacity (5-10 For Simplicity)");
				courCapacity = scannerObj.nextInt();
			} else {
				break;
			}
		}

		System.out.println("");

		//Creates a course object with the given values
		Course courseObj = new Course(courID, courName, courInstructor, courCapacity, 0);
		//Places the current course object into a hashmap with course ID as the key
		courseHashObj.put(courID, courseObj);
	}

	//COMPLETE
	//Loop through every Student's enrolled course and remove from their list
	static void deleteCourse(){
		System.out.println("Which Course to Delete? (Type ID)");
		int courID = scannerObj.nextInt();

		//Checks if courID is in HashMap
		courID = checkCourseIDinHash(courID);

		//Exit Method and Back to Menu List
		if(courID == -1){ 
			System.out.println("Exiting deleteCourse Method...");
			return; 
		}

		//After checking, grabs the current course
		Course curCourse = courseHashObj.get(courID);

		//Remove course from every student's list
		//For each studentObject, get the value, indicating the curStudent, grab the enrolledCourses ArrayList, and remove the course that matches the courseName
		for(HashMap.Entry<Integer, Student> eachCurStudent : studentHashObj.entrySet()){
			eachCurStudent.getValue().enrolledCourses.remove(curCourse.courseName);
		}

		//In the courseHashObj, grab the removed course and its details to print into console, in addition to "actually" removing it. 
		System.out.println(courseHashObj.remove(courID).getCourseDetails() + " has been removed");
	}

	//MAYBE COMPLETE
	//Check ID Availability and Range
	static void modifyCourse(){
		System.out.println("Which Course to Modify? (Type ID)");
		int courID = scannerObj.nextInt();
		Course curCourse = courseHashObj.get(courID);

		//Checks if courID - the ID to modify - is in HashMap
		courID = checkCourseIDinHash(courID); 

		//Exit Method and Back to Menu List
		if(courID == -1){ 
			System.out.println("Exiting modifyCourse Method...");
			return; 
		}

		//Make sure courseIDs do not match one another
		//If change capacity, check enrolled students first
		System.out.println("If no change, type the same info");
		System.out.println("Course ID: ");
		int changeCourID = scannerObj.nextInt();

		//Check ID Availability
		// If HashMap contains the changedID or if input is out of range, ask again
		while(true){
			if(changeCourID == courID){
			break;	
		} else if (courseHashObj.containsKey(changeCourID) || (changeCourID < 1000 || changeCourID > 9999)){
			System.out.println("ID is invalid. Either ID is in use or invalid input. Try Again");
			System.out.println("Course ID: ");
			changeCourID = scannerObj.nextInt();
		}
		else {
			System.out.println("ID has been Changed");
			courseHashObj.put(changeCourID, curCourse); //Create a hash with the same course but new ID
			curCourse.setCourseID(changeCourID); //Use a setter to change the course ID
			courseHashObj.remove(courID); //Remove the hash with the old ID
			break;
			}
		}

		scannerObj.nextLine();

		System.out.println("Course Name: ");
		String changeCourName = scannerObj.nextLine();

		System.out.println("Course Instructor: ");
		String changeCourInstructor = scannerObj.nextLine();

		System.out.println("Course Capacity: ");
		int changeCourCapacity = scannerObj.nextInt();

		//Check Course Capacity
		while(true){
			if(changeCourCapacity < curCourse.enrolledStudents){
			System.out.println("Cannot reduce capacity because of the amount of students already enrolled");
			System.out.println("Try Again. Course Capacity (5-10): ");
		    changeCourCapacity = scannerObj.nextInt();
		} else {
			System.out.println("Capacity has been set");
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

		courID = checkCourseIDinHash(courID);

		//Exit Method and Back to Menu List
		if(courID == -1){ 
			System.out.println("Exiting displayCourse Method...");
			return; 
		}

		System.out.println(courseHashObj.get(courID).getCourseDetails());

	}

	//COMPLETE
	static void displayEnrollStatus(){
		System.out.println("Which Course to Check Availability? (Type ID)");
		int courID = scannerObj.nextInt();

		courID = checkCourseIDinHash(courID);

		//Exit Method and Back to Menu List
		if(courID == -1){ 
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

	//MAYBE COMPLETE
	static void checkCurSchedule(){
		System.out.println("Schedule with most students is prioritized and will begin sequentially with one hour apart after class ends. \nIf the course has the same amount of students, the schedule will be based on which is added onto the system first.");
		if(courseHashObj.isEmpty()){
			System.out.println("There are no courses. Cannot create schedule.");
		} else{
			for(Course _courses : courseHashObj.values()){
				pqScheduleObj.add(_courses);
			}

			System.out.println("The class will begin in the following order:");

			while(!pqScheduleObj.isEmpty()){
				Course polledCourse = pqScheduleObj.poll();
				System.out.print("Course ID: " + polledCourse.getCourseID() + "Course Name: " + polledCourse.getCourseName());
			}
		}

	}

	//Checks if studentID is in System
	static int checkStudentIDinHash(int stuIDParams){
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
	static int checkCourseIDinHash(int courIDParams){
		while(!courseHashObj.containsKey(courIDParams)){
			if(courIDParams == -1){
				return courIDParams;
			}
			System.out.println("ID Doesn't Exist \nTry again or type -1 to Exit");
			courIDParams = scannerObj.nextInt();
		}
		return courIDParams;
	}

	//Checks if student ID is already in use and if input is within range
	static int checkStudentID_InUseAndRange(int stuIDParams){
		while(true){
			if(studentHashObj.containsKey(stuIDParams)){
			System.out.println("ID is in use. Try Again");
			System.out.println("Student ID (Zero Can't be First and Only 4 Digits): ");
			stuIDParams = scannerObj.nextInt();	
		} else if (stuIDParams < 1000 || stuIDParams > 9999) {
			System.out.println("ID is an invalid input. Try Again");
			System.out.println("Student ID (Zero Can't be First and Only 4 Digits): ");
			stuIDParams = scannerObj.nextInt();	
		}
		else {
			return stuIDParams;
			}
		}
	}

	//Checks if course ID is already in use and if input is within range
	static int checkCourseID_InUseAndRange(int courIDParams){
		while(true){
			if(courseHashObj.containsKey(courIDParams)){
			System.out.println("ID is in use. Try Again");
			System.out.println("Course ID (Zero Can't be First and Only 4 Digits): ");
			courIDParams = scannerObj.nextInt();	
		} else if (courIDParams < 1000 || courIDParams > 9999){
			System.out.println("ID is an invalid input. Try Again");
			System.out.println("Course ID (Zero Can't be First and Only 4 Digits): ");
			courIDParams = scannerObj.nextInt();	
		}
		else {
			return courIDParams;
			}
		}
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
			case 9:
				modifyCourse();
				break;
			case 10:
				checkCurSchedule();
				break;
			case 11:
				displayAllStudents();
				break;
			case 12:
				displayAllCourses();
				break;
			default:
				System.out.println("Invalid Input \nExiting Program");
				System.exit(0);
		}
		System.out.println();
	}

	public static void main(String[] args){
		System.out.println("Enrollment Class Sucess");

		Boolean Running = true;
		while(Running){
			menuList();
		}

	}
}
