import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Comparator;

public class Enrollment2 {
	static Course courseObj = new Course(0, null, null ,0, 0); //Initializes a Course Class - courseObj
	static Student2 studentObj = new Student2(0, null, 0, null); //Initializes a Student Class - studentObj
	static Scanner scannerObj = new Scanner(System.in); //Initializes a Scanner for input - scannerObj
	static HashMap<Integer, Student2> studentHashObj = new HashMap<>(); //Initializes a Hashmap - studentHashObj - that takes StuID as key and Student Obj as value
	static HashMap<Integer, Course> courseHashObj = new HashMap<>(); //Initializes a Hashmap - courseHashObj - that takes courseID as key and Course Obj as value
	static Comparator<Course> compareCourseObj = new Comparator<Course>() { // Initializes a Comparator class for comparing class priority for scheduling based on more students
		public int compare(Course one, Course two){
			return Integer.compare(two.enrolledStudents, one.enrolledStudents);
		}
	};

	static PriorityQueue<Course> pqScheduleObj = new PriorityQueue<>(compareCourseObj); //Initializes a PQ class that takes Course Class Objects - pqScheduleObj
	//Program needs to check if id is valid when entered for all cases

	//Function that adds a student 
	void addStudent(){

		//Scanner that takes student ID
		System.out.println("Student ID (4 Digits and Zero can't be first digit):"); 
		int stuID = scannerObj.nextInt();
		System.out.println();

		//Function checks if stuID is in-use or within range
		stuID = checkStudentID_InUseAndRange(stuID); 

		scannerObj.nextLine();

		//Scanner that takes student name
		System.out.println("Student Name:"); 
		String stuName = scannerObj.nextLine();
		System.out.println();

		//Scanner that takes student age within 18-40
		System.out.println("Student Age (18-40 for Simplicity) :"); 
		int stuAge = scannerObj.nextInt();
		System.out.println();

		while(true){ //Condition statment checks if student age is within range
			if (stuAge >= 18 & stuAge <= 40){
				break;
			} else {
				System.out.println("Input out of range. Student Age (18-40):");
				stuAge = scannerObj.nextInt();
				System.out.println();
			}
		}

		scannerObj.nextLine();

		//Scanner that takes student major
		System.out.println("Student Major:"); 
		String stuMajor = scannerObj.nextLine();
		System.out.println();

		//Creates the Student object with given info
		Student2 studentObj = new Student2(stuID, stuName, stuAge, stuMajor);

		//Saves the Student into a Hashmap with ID as their key
		studentHashObj.put(stuID, studentObj);

		System.out.println("\nStudent has been added");
	}

	//Function enrolls student into a course
	void enrollStudent(){

		//Scanner that takes student ID
		System.out.println("Which Student? (Type ID)");
		int stuID = scannerObj.nextInt();
		System.out.println();

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
		System.out.println();

		//Checks if CourseID is in Hash
		courID = checkCourseIDinHash(courID);

		//Exit enrollStudent method and back to menu List
		if(courID == -1){ 
			System.out.println("Exiting enrollStudent Method...");
			return; 
		}

		//If everything passes, create a variable that takes the current student/course
		Student2 curStudent = studentHashObj.get(stuID);
		Course curCourse = courseHashObj.get(courID);

		//Check if student is already enrolled
		if(curStudent.enrolledCoursesHash.containsKey(curCourse.courseID)){
			System.out.println("Student is already enrolled");
			System.out.println("Exiting enrollStudent method");
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
			curStudent.enrolledCoursesHash.put(curCourse.courseID, curCourse.courseName);
		}
	}

	//Function that drops student from course
	void dropStudent(){
		//Scanner takes student ID
		System.out.println("Which Student? (Type ID)");
		int stuID = scannerObj.nextInt();
		System.out.println();

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

		Student2 curStudent = studentHashObj.get(stuID);
		Course curCourse = courseHashObj.get(courID);

		//Checks if student is attending the course
		if (curStudent.enrolledCoursesHash.containsKey(curCourse.courseID)){
			curCourse.removeCount();
			System.out.println(curStudent.getName() + " has been dropped from");
			System.out.println("Course ID: " + curCourse.getCourseID());
			System.out.println("Course Name: " + curCourse.getCourseName());
			curStudent.enrolledCoursesHash.remove(curCourse.courseID);
		} else { //If not, notify user and do nothing
			System.out.println("Student is not Enrolled in " + curCourse.courseName);
			System.out.println("Returning to Menu...");
		}
	}

	//Function that displays a specific student's details
	void displayStudent(){
		
		//Scanner takes student ID
		System.out.println("Which Student (Type ID)?");
		int stuID = scannerObj.nextInt();
		System.out.println();

		//Checks if Student ID is in Hash
		stuID = checkStudentIDinHash(stuID);

		//Exit displayStudent Method and Back to Menu List
		if(stuID == -1){ 
			System.out.println("Exiting displayStudent Method...");
			return;
		}

		System.out.println();
		System.out.println(studentHashObj.get(stuID).getStudentDetails());
		System.out.println("Student details are being displayed");
	}

	//Function that displays all the students and their details
	void displayAllStudents(){
		if(studentHashObj.isEmpty()){
			System.out.println("No Students in System");
		} else {
			for(Student2 _students: studentHashObj.values()){
				System.out.println(_students.getStudentDetails());
			}
		}
		System.out.println("\nAll student details are being displayed");
	}

	//Function to add course
	void addCourse(){
		//Scanner takes course ID
		System.out.println("Course ID (Four Digits & Zero cant be first):");
		int courID = scannerObj.nextInt();
		System.out.println();

		//CHecks if ID is already in use or out of range
		courID = checkCourseID_InUseAndRange(courID);

		scannerObj.nextLine();

		//Scanner takes course name
		System.out.println("Course Name:");
		String courName = scannerObj.nextLine();
		System.out.println();

		//Scanner takes instructor name
		System.out.println("Instructor Name:");
		String courInstructor = scannerObj.nextLine();
		System.out.println();
	
		//Scanner takes course capacity
		System.out.println("Course Capacity (5-10 For Simplicity)");
		int courCapacity = scannerObj.nextInt();
		System.out.println();

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

		System.out.println();

		//Creates a course object with the given values
		Course courseObj = new Course(courID, courName, courInstructor, courCapacity, 0);
		//Places the current course object into a hashmap with course ID as the key
		courseHashObj.put(courID, courseObj);

		System.out.println("\nCourse has been added");
	}

	//COMPLETE
	//Loop through every Student's enrolled course and remove from their list
	void deleteCourse(){
		System.out.println("Which Course to Delete? (Type ID)");
		int courID = scannerObj.nextInt();
		System.out.println();

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
		for(HashMap.Entry<Integer, Student2> eachCurStudent : studentHashObj.entrySet()){
			Student2 curStudent = eachCurStudent.getValue();
			curStudent.enrolledCoursesHash.remove(curCourse.courseID);
		}

		//In the courseHashObj, grab the removed course and its details to print into console, in addition to "actually" removing it. 
		System.out.println(courseHashObj.remove(courID).getCourseDetails() + " has been removed");
	}

	//Complete
	void modifyCourse(){
		System.out.println("Which Course to Modify? (Type ID)");
		int courID = scannerObj.nextInt();
		Course curCourse = courseHashObj.get(courID);
		System.out.println();

		//Checks if courID - the ID to modify - is in HashMap
		courID = checkCourseIDinHash(courID); 

		//Exit Method and Back to Menu List
		if(courID == -1){ 
			System.out.println("Exiting modifyCourse Method...");
			return; 
		}

		//Make sure courseIDs do not match one another
		//If change capacity, check enrolled students first
		System.out.println("If no change, type the same info\n");
		System.out.println("Course ID: \n");
		int changeCourID = scannerObj.nextInt();

		//Check ID Availability
		// If HashMap contains the changedID or if input is out of range, ask again
		while(true){
			if(changeCourID == courID){ //This statement is an exception as it checks if the user wants to keep the same ID
			break;	
		} else if (courseHashObj.containsKey(changeCourID) || (changeCourID < 1000 || changeCourID > 9999)){ //This is statement that checks if ID is available if it's a dupe or within range
			System.out.println("ID is invalid. Either ID is in use or invalid input. Try Again");
			System.out.println("Course ID: ");
			changeCourID = scannerObj.nextInt();
		}
		else { //If this statement is reached, the ID will change
			System.out.println("ID has been Changed");
			courseHashObj.put(changeCourID, curCourse); //Create a hash with the same course but new ID
			curCourse.setCourseID(changeCourID); //Use a setter to change the course ID
			courseHashObj.remove(courID); //Remove the hash with the old ID
			break;
			}
		}

		scannerObj.nextLine();

		//Scanner takes the new course name
		System.out.println("New Course Name: ");
		String changeCourName = scannerObj.nextLine();
		System.out.println();

		//Scanner takes the new instructor
		System.out.println("New Course Instructor: ");
		String changeCourInstructor = scannerObj.nextLine();
		System.out.println();

		//Scanner takes the new course capacity
		System.out.println("New Course Capacity: ");
		int changeCourCapacity = scannerObj.nextInt();
		System.out.println();

		//Check Course Capacity
		while(true){
			if(changeCourCapacity < curCourse.enrolledStudents){ //This condition checks if the new capacity is less than the currently enrolled students. If so, the program can't change the capacity. 
			System.out.println("Cannot reduce capacity because of the amount of students already enrolled");
			System.out.println("Try Again. Course Capacity (5-10): ");
		    changeCourCapacity = scannerObj.nextInt();
		} else { //Changes the course capacity if the new capacity isnt less than enrolled students
			System.out.println("Capacity has been set");
			curCourse.setCapacity(changeCourCapacity);
			break;
			}
		}

		//Loop through every student's enrolledCourse and change the name
		for(HashMap.Entry<Integer, Student2> eachCurStudent : studentHashObj.entrySet()){
			Student2 curStudent = eachCurStudent.getValue(); //Grab each student and save it into a variable named curStudent
			if(curStudent.enrolledCoursesHash.containsKey(curCourse.courseID)){ //If student has the current course's name

				curStudent.enrolledCoursesHash.put(changeCourID, changeCourName);

				// int courseNameIndex = curStudent.enrolledCoursesHash.indexOf(curCourse.courseName); //Grab the index of the course name

				// curStudent.enrolledCoursesHash.set(courseNameIndex, changeCourName); //Change the course name to the new course name at the said index
			}
		}

		//The setters to change the new information if everything passes
		curCourse.setCourseName(changeCourName);
		curCourse.setInstructor(changeCourInstructor);

		System.out.println("\nCourse has been modified");
	}

	//COMPLETE
	void displayCourse(){
		//Scanner takes the course ID
		System.out.println("Which Course to Display? (Type ID)");
		int courID = scannerObj.nextInt();
		System.out.println();

		//Function checks if Course ID is in hashmap
		courID = checkCourseIDinHash(courID);

		//Exit the displayCourse Method and Back to Menu List
		if(courID == -1){ 
			System.out.println("Exiting displayCourse Method...");
			return; 
		}

		//If course ID is available, go into the courseHashObj and grab the Course with the associated value. Then, print the details form the Course Object because of its associated function from the class - a generalized getter.
		System.out.println(courseHashObj.get(courID).getCourseDetails());

		System.out.println("\nCourse details are being displayed");

	}

	//COMPLETE
	//Function displays a course's enrollment status
	void displayEnrollStatus(){
		//Scanner takes course ID
		System.out.println("Which Course to Check Availability? (Type ID)");
		int courID = scannerObj.nextInt();
		System.out.println();

		//Checks if the course ID is in the hashmap
		courID = checkCourseIDinHash(courID);

		//Exit the displayEnrollStatus Method and Back to Menu List
		if(courID == -1){ 
			System.out.println("Exiting enrollStatus Method...");
			return;
		}

		//Save the current course into a variable with the desired course after obtaining it from using the key and prints the needed getters.
		Course curCourse = courseHashObj.get(courID);
		System.out.println("Course Name: " + curCourse.courseName);
		System.out.println("Course Capacity: " + curCourse.capacity);
		System.out.println("Enrollment Amount: " + curCourse.enrolledStudents);

		//If enrolledStudents match courseCapacity, enrollment is closed
		if(curCourse.enrolledStudents == curCourse.capacity){ //This means that the enrolled students maxed the capacity
			System.out.println("Enrollment Status is Closed");
		} else { //If not, the enrollment is still open
			System.out.println("Enrollment Status is Open");
		}

		System.out.println("\nCourse enrollment status is being displayed");

	}

	//COMPLETE
	//Function that displays all courses
	void displayAllCourses(){
		if(courseHashObj.isEmpty()){ //If the course hashmap is empty, CLI notifies the user.
			System.out.println("No Courses in System");
		} else { //If not empty, the program will get each value in the course hashmap and use the generalized getters for the object and print its values
			for(Course _courses: courseHashObj.values()){
			System.out.println(_courses.getCourseDetails());
			}
		}
	}

	//COMPLETE
	void checkCurSchedule(){
		System.out.println("Schedule with most students is prioritized and will begin sequentially with one hour apart after class ends. \nIf the course has the same amount of students, the schedule will be based on which is added onto the system first.\n");
		if(courseHashObj.isEmpty()){
			System.out.println("There are no courses. Cannot create schedule.");
		} else{
			pqScheduleObj.clear(); //Clear the pq first to prevent dupes
			for(Course _courses : courseHashObj.values()){ //Add the courses according to the comparator
				pqScheduleObj.add(_courses);
			}

			System.out.println("The class will begin in the following order:\n");

			//While the pq is not empty, continuously return the value and use the getters to print the Course ID and course Name
			while(!pqScheduleObj.isEmpty()){
				Course polledCourse = pqScheduleObj.poll();
				System.out.print("Course ID: " + polledCourse.getCourseID() + " Course Name: " + polledCourse.getCourseName());
				System.out.println();
			}
		}
		System.out.println("\nSchedule is being displayed");
	}

	/*
	///////////////////////////////////////////////////////////////////////
	
	From this line, the following functions are used to make checking cases

	////////////////////////////////////////////////////////////////////////
	 */

	//Checks if studentID is in System
	int checkStudentIDinHash(int stuIDParams){
		while(!studentHashObj.containsKey(stuIDParams)){ //Condition checks if the given student ID is in the student hashmap
			if(stuIDParams == -1){ //This condition allows the user to exit the current function and return back to the menu list
				return stuIDParams;
			}
			//Scanner asks for the student ID again
			System.out.println("ID Doesn't Exist \nTry again or type -1 to Exit");
			stuIDParams = scannerObj.nextInt();
			System.out.println();
		}
		return stuIDParams;
	}

	//Checks if CourseID is in System
	int checkCourseIDinHash(int courIDParams){
		while(!courseHashObj.containsKey(courIDParams)){ //Condition checks if the given course ID is in the course hashmap
			if(courIDParams == -1){ //This condition allows the user to exit the current function and return back to the menu list
				return courIDParams;
			}
			//Scanner asks the course ID again
			System.out.println("ID Doesn't Exist \nTry again or type -1 to Exit");
			courIDParams = scannerObj.nextInt();
			System.out.println();
		}
		return courIDParams;
	}

	//Checks if student ID is already in use and if input is within range
	int checkStudentID_InUseAndRange(int stuIDParams){
		while(true){
			if(studentHashObj.containsKey(stuIDParams)){ //If the student hashmap contains the key, ask for a new student ID
			System.out.println("ID is a dupe. Try Again");
			System.out.println("Student ID (Zero Can't be First and Only 4 Digits): ");
			stuIDParams = scannerObj.nextInt();	
			System.out.println();
		} else if (stuIDParams < 1000 || stuIDParams > 9999) { //If the student ID is not 4 digits, ask again
			System.out.println("ID is out of range. Try Again");
			System.out.println("Student ID (Zero Can't be First and Only 4 Digits): ");
			stuIDParams = scannerObj.nextInt();	
			System.out.println();
		}
		else { //If everything is successful, use the new ID for the student
			return stuIDParams;
			}
		}
	}

	//Checks if course ID is already in use and if input is within range
	int checkCourseID_InUseAndRange(int courIDParams){
		while(true){
			if(courseHashObj.containsKey(courIDParams)){ //If the course hashmap contains the key, ask for a new course ID
			System.out.println("ID is a dupe. Try Again");
			System.out.println("Course ID (Zero Can't be First and Only 4 Digits): ");
			courIDParams = scannerObj.nextInt();	
			System.out.println();
		} else if (courIDParams < 1000 || courIDParams > 9999){ //If the course hashmap contains the key, ask for a new course ID
			System.out.println("ID is out of range. Try Again");
			System.out.println("Course ID (Zero Can't be First and Only 4 Digits): ");
			courIDParams = scannerObj.nextInt();	
			System.out.println();
		}
		else { //If successful, use the new ID for the Course
			return courIDParams;
			}
		}
	}



	public static void main(String[] args){
		System.out.println("Enrollment Class Sucess");

	}
}
