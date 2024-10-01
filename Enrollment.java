import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Enrollment {
	Course courseObj = new Course(0, null, null ,0, 0);
	static Student studentObj = new Student(0, null, 0, null);
	static Scanner scannerObj = new Scanner(System.in);
	static HashMap<Integer, Student> studentHashObj = new HashMap<>();
	static HashMap<Integer, Course> courseHashObj = new HashMap<>();
	PriorityQueue<Integer> priorityQueueObj = new PriorityQueue<>();

	//COMPLETE
	static void addStudent(){
		System.out.println("Student ID (4 Digits):");
		int stuID = scannerObj.nextInt();

		System.out.println("Student Name:");
		String stuName = scannerObj.next();

		System.out.println("Student Age (18-40):");
		int stuAge = scannerObj.nextInt();

		System.out.println("Student Major:");
		String stuMajor = scannerObj.nextLine();

		Student studentObj = new Student(stuID, stuName, stuAge, stuMajor);

		studentHashObj.put(stuID, studentObj);
	}

	//IN PROGRESS
	static void enrollStudent(){
		System.out.println("Which Student? (Type ID)");
		int stuID = scannerObj.nextInt();

		System.out.println("Which Course to Enroll? (Type ID)");
		int courID = scannerObj.nextInt();

		Student curStudent = studentHashObj.get(stuID);
		Course curCourse = courseHashObj.get(courID);

		//Check if Capacity is available. If so, add student, and if not, dont add
		//Increase enrollment by 1 count
		//Save the enrolled class into the students list somewhere
		if(curCourse.enrolledStudents == curCourse.capacity){
			System.out.println("Enrollment Status is Closed");
		} else {
			curCourse.increaseCount();
			System.out.println(curStudent.getName() + " has been enrolled into");
			System.out.println("Course ID: " + curCourse.getCourseID());
			System.out.println("Course Name: " + curCourse.getCourseName());
		}
	}

	//IN PROGRESS
	static void dropStudent(){
		System.out.println("Which Student? (Type ID)");
		int stuID = scannerObj.nextInt();

		System.out.println("Which Course to Drop? (Type ID)");
		int courID = scannerObj.nextInt();

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
		}
	}

	//Gets the student ID from hash and display their details. COMPLETE
	static void displayStudent(){
		System.out.println("Which Student (Type ID)?");
		int stuID = scannerObj.nextInt();
		System.out.println(studentHashObj.get(stuID).getStudentDetails());
	}

	static void displayAllStudents(){
		for(Student _students: studentHashObj.values()){
			System.out.println(_students.getStudentDetails());
		}
	}

	//COMPLETE
	static void addCourse(){
		System.out.println("Course ID:");
		int courID = scannerObj.nextInt();

		System.out.println("Course Name:");
		String courName = scannerObj.next();

		System.out.println("Instructor Name:");
		String courInstructor = scannerObj.next();
	
		System.out.println("Course Capacity (5-10 For the Scope of Program)");
		int courCapacity = scannerObj.nextInt();

		System.out.println("");

		Course courseObj = new Course(courID, courName, courInstructor, courCapacity, 0);
		courseHashObj.put(courID, courseObj);
	}

	//COMPLETE
	static void deleteCourse(){
		System.out.println("Which Course to Delete? (Type ID)");
		int courID = scannerObj.nextInt();
		System.out.println(courseHashObj.remove(courID).getCourseDetails() + " has been removed");
	}

	//MAYBE COMPLETE
	static void modifyCourse(){
		System.out.println("Which Course to Modify? (Type ID)");
		int courID = scannerObj.nextInt();
		Course curCourse = courseHashObj.get(courID);

		//Make sure courseIDs do not match one another
		//If change capacity, check enrolled students first
		System.out.println("If no change, type the same info");
		System.out.println("Course ID: ");
		int changeCourID = scannerObj.nextInt();

		System.out.println("Course Name: ");
		String changeCourName = scannerObj.next();

		System.out.println("Course Instructor: ");
		String changeCourInstructor = scannerObj.next();

		System.out.println("Course Capacity: ");
		int changeCourCapacity = scannerObj.nextInt();

		//Check ID Availability
		while(true){
			if(courseHashObj.containsKey(courID)){
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
			System.out.println("Course Capacity: ");
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
		System.out.println(courseHashObj.get(courID).getCourseDetails());

	}

	//COMPLETE
	static void displayEnrollStatus(){
		System.out.println("Which Course to Check Availability? (Type ID)");
		int courID = scannerObj.nextInt();
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
		for(Course _courses: courseHashObj.values()){
			System.out.println(_courses.getCourseDetails());
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
