public class Student {
	int studentID;
	String name;
	int age;
	String major;

	public Student(int studentID, String name, int age, String major) {
		this.studentID = studentID;
		this.name = name;
		this.age = age;
		this.major = major;
	};

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

	String getStudentDetails(){
		return String.format("ID: %s, Name: %s, Age: %d, Major: %s", this.studentID, this.name, this.age, this.major);
	}
	public static void main(String[] args){
		System.out.println("Student Class Sucess");
	}
}
