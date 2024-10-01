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

	//Setters for Modifying
	void setCourseID(int courseID){
		this.courseID = courseID;
	}
	void setCourseName(String courseName){
		this.courseName = courseName;
	}
	void setInstructor(String instructor){
		this.instructor = instructor;
	}
	void setCapacity(int capacity){
		this.capacity = capacity;
	}
	void setEnrolled(int enrolledStudents){
		this.enrolledStudents = enrolledStudents;
	}
	void increaseCount(){
		this.enrolledStudents += 1;
	}
	void removeCount(){
		this.enrolledStudents -= 1;
	}

	//Getters to Review Details
	int getCourseID(){
		return this.courseID;
	}
	String getCourseName(){
		return this.courseName;
	}
	String getInstructor(){
		return this.instructor;
	}
	int getCapacity(){
		return this.capacity;
	}
	int getEnrolled(){
		return this.enrolledStudents;
	}

	String getCourseDetails(){
		return String.format("CourseID: %s, Course Name: %s, Instructor: %s, Capacity: %d, Enrolled: %d", this.courseID, this.courseName, this.instructor, this.capacity, this.enrolledStudents);
	}

	public static void main(String[] args){
		System.out.println("Course Class Sucess");
	}
}
