public class Course {
	int courseID;
	String courseName;
	String instructor;
	int capacity;
	int enrolledStudents;

	public Course(int courseID, String courseName, String instructor, int capacity, int enrolledStudents){
		this.courseID = courseID;
		this.courseName = courseName;
		this.instructor = instructor;
		this.capacity = capacity;
		this.enrolledStudents = enrolledStudents;
	}
}
