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
		String stuMajor = scannerObj.next();

		Student studentObj = new Student(stuID, stuName, stuAge, stuMajor);

		studentHashObj.put(stuID, studentObj);
	}

	static void enrollStudent(){
		System.out.println("Which Student? (Type ID)");
		int stuID = scannerObj.nextInt();

		System.out.println("Which Course to Enroll? (Type ID)");
		int courID = scannerObj.nextInt();

		studentHashObj.get(stuID);
		courseHashObj.get(courID);

	}

	static void dropStudent(){
		System.out.println("Which Student? (Type ID)");
		int stuID = scannerObj.nextInt();

		System.out.println("Which Course to Drop? (Type ID)");
		int courID = scannerObj.nextInt();

		studentHashObj.get(stuID);
		courseHashObj.get(courID);
	}

	static void displayStudent(){
		System.out.println("Which Student (Type ID)?");
		int stuID = scannerObj.nextInt();
		studentHashObj.get(stuID);
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

	static void deleteCourse(){
		System.out.println("Which Course to Delete? (Type ID)");
		int courID = scannerObj.nextInt();
		courseHashObj.remove(courID);
	}

	static void modifyCourse(){
		System.out.println("Which Course to Modify? (Type ID)");
		int courID = scannerObj.nextInt();
		courseHashObj.get(courID);
	}

	static void displayCourse(){
		System.out.println("Which Course to Display? (Type ID)");
		int courID = scannerObj.nextInt();
		courseHashObj.get(courID);

	}

	static void displayEnrollStatus(){
		System.out.println("Which Course to Check Availability? (Type ID)");
		int courID = scannerObj.nextInt();
		Course curCourse = courseHashObj.get(courID);
		System.out.println("Course Name: " + curCourse.courseName);
		System.out.println("Course Capacity: " + curCourse.capacity);
		System.out.println("Enrollment Amount: " + curCourse.enrolledStudents);

		if(curCourse.enrolledStudents == curCourse.capacity){
			System.out.println("Enrollment Status is Closed");
		} else {
			System.out.println("Enrollment Status is Open");
		}

	}

	static void displayAllCourses(){
		for(Course _courses: courseHashObj.values()){
			System.out.println(_courses.getCourseDetails());
		}
	}

	public void menuList(){
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
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			default:
				System.out.println("Invalid Input \nExiting Program");
				System.exit(0);
		}
	}

	public static void main(String[] args){
		System.out.println("Enrollment Class Sucess");
	}
}
