import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Enrollment {
	Course courseObj = new Course(0, null, null ,0, 0);
	Student studentObj = new Student(null, null, 0, null);
	Scanner scannerObj = new Scanner(System.in);
	HashMap<Integer, Integer> hashMapObj = new HashMap<>();
	PriorityQueue<Integer> priorityQueueObj = new PriorityQueue<>();

	static void addStudent(){

	}
	static void enrollStudent(){

	}
	static void dropStudent(){

	}
	static void displayStudent(){

	}
	static void addCourse(){

	}
	static void deleteCourse(){

	}
	static void modifyCourse(){

	}
	static void displayCourse(){

	}
	static void displayEnrollStatus(){
		
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
