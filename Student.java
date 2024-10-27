import java.util.ArrayList;

public class Student {
	int studentID; 
	String name;
	int age;
	String major;
	ArrayList<String> enrolledCourses = new ArrayList<>(); //enrollCourses take an ArrayList of Strings

	//Constructor
	public Student(int studentID, String name, int age, String major) {
		this.studentID = studentID;
		this.name = name;
		this.age = age;
		this.major = major;
	};

	//Getters
	int getStuID(){
		return this.studentID;
	}

	String getName(){
		return this.name;
	}

	int getAge(){
		return this.age;
	}

	String getMajor(){
		return this.major;
	}

	//Getter to display all details
	String getStudentDetails(){
		return String.format("ID: %s, Name: %s, Age: %d, Major: %s, Courses: %s", this.studentID, this.name, this.age, this.major, enrolledCourses.toString());
	}
	public static void main(String[] args){
		System.out.println("Student Class Sucess");
	}
}
