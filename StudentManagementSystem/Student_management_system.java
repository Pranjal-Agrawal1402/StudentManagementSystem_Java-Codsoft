import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private String Name;
    private int RollNumber;
    private String MobileNumber;
    private String Grade;
    private String Course; 

    public Student(String name, int rollNumber, String mobileNumber, String grade, String course) {
        this.Name = name;
        this.RollNumber = rollNumber;
        this.MobileNumber = mobileNumber;
        this.Grade = grade;
        this.Course = course; 
    }

    public int getRollNumber() {
        return RollNumber;
    }

    public String getName() {
        return Name;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public String getGrade() {
        return Grade;
    }

    public String getCourse() {
        return Course; 
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setRollNumber(int rollNumber) {
        this.RollNumber = rollNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.MobileNumber = mobileNumber;
    }

    public void setGrade(String grade) {
        this.Grade = grade;
    }

    public void setCourse(String course) { 
        this.Course = course;
    }

    @Override
    public String toString() {
        return Name + "," + RollNumber + "," + MobileNumber + "," + Grade + "," + Course;
    }
}

class StudentManagementSystem {
    private List<Student> students;

    public StudentManagementSystem() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public boolean removeStudent(int RollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == RollNumber) {
                students.remove(student);
                return true;
            }
        }
        return false;
    }

    public Student searchStudent(int RollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == RollNumber) {
                return student;
            }
        }
        return null;
    }

    public void editStudent(int RollNumber, String newName, int newRollNumber, String newMobile, String newGrade, String newCourse) {
        Student student = searchStudent(RollNumber);
        if (student != null) {
            if (isRollNumberUnique(newRollNumber)) {
                student.setName(newName);
                student.setRollNumber(newRollNumber);
                student.setMobileNumber(newMobile);
                student.setGrade(newGrade);
                student.setCourse(newCourse); 
            } else {
                System.out.println("Error: The Roll Number already exists. Please enter a unique Roll Number.");
            }
        } else {
            System.out.println("Student with Roll Number " + RollNumber + " not found.");
        }
    }


    public boolean isRollNumberUnique(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return false; 
            }
        }
        return true; 
    }

    public List<Student> getAllStudents() {
        return students;
    }
}

public class Student_management_system {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();

        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Edit Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scanner.nextLine(); 
                    System.out.print("Enter Student Name (mandatory): ");
                    String Name = scanner.nextLine();
                    if (Name.isEmpty()) {
                        System.out.println("Name is mandatory.");
                        break;
                    }
                    System.out.print("Enter Roll Number (mandatory): ");
                    int RollNumber = scanner.nextInt();
                    scanner.nextLine(); 
                    if (RollNumber <= 0) {
                        System.out.println("Roll Number is mandatory and must be positive.");
                        break;
                    }
                    System.out.print("Enter Mobile Number: ");
                    String MobileNumber = scanner.nextLine();
                    System.out.print("Enter Grade: ");
                    String Grade = scanner.nextLine();
                    System.out.print("Enter Course: "); 
                    String Course = scanner.nextLine();

                    Student newStudent = new Student(Name, RollNumber, MobileNumber, Grade, Course);
                    sms.addStudent(newStudent);
                    System.out.println("Student added.");
                    break;

                case 2:
                    System.out.print("Enter Roll Number of student to remove: ");
                    int rollToRemove = scanner.nextInt();
                    boolean removed = sms.removeStudent(rollToRemove);
                    if (removed) {
                        System.out.println("Student removed.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Roll Number of student to search: ");
                    int rollToSearch = scanner.nextInt();
                    Student searchedStudent = sms.searchStudent(rollToSearch);
                    if (searchedStudent != null) {
                        System.out.println("Student found:");
                        System.out.println("------------------------------------------------------");
                        System.out.println("No. | Name               | Roll No | Mobile Number | Grade | Course");
                        System.out.println("------------------------------------------------------");
                        System.out.printf("%-4d| %-18s| %-8d| %-15s| %-5s| %-10s%n", 
                                          1, 
                        searchedStudent.getName(), 
                        searchedStudent.getRollNumber(), 
                        searchedStudent.getMobileNumber(), 
                        searchedStudent.getGrade(),
                                          searchedStudent.getCourse()); 
                        System.out.println("------------------------------------------------------");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    List<Student> allStudents = sms.getAllStudents();
                    if (allStudents.isEmpty()) {
                        System.out.println("No students to display.");
                    } else {
                        System.out.println("All Students:");
                        System.out.println("------------------------------------------------------");
                        System.out.println("No. | Name               | Roll No | Mobile Number | Grade | Course");
                        System.out.println("------------------------------------------------------");

                        for (int i = 0; i < allStudents.size(); i++) {
                            Student student = allStudents.get(i);
                            System.out.printf("%-4d| %-18s| %-8d| %-15s| %-5s| %-10s%n", 
                            i + 1, 
                            student.getName(), 
                            student.getRollNumber(), 
                            student.getMobileNumber(), 
                            student.getGrade(),
                            student.getCourse()); 
                        }
                        System.out.println("------------------------------------------------------");
                    }
                    break;

                case 5:
                    System.out.print("Enter Roll Number of student to edit: ");
                    int rollToEdit = scanner.nextInt();
                    scanner.nextLine(); 
                    Student studentToEdit = sms.searchStudent(rollToEdit);
                    if (studentToEdit != null) {
                        System.out.print("Enter New Name (leave empty to keep existing): ");
                        String newName = scanner.nextLine();
                        if (!newName.isEmpty()) {
                            studentToEdit.setName(newName);
                        }

                        
                        System.out.print("Enter New Roll Number (leave empty to keep existing): ");
                        String newRollInput = scanner.nextLine();
                        if (!newRollInput.isEmpty()) {
                            int newRollNumber = Integer.parseInt(newRollInput);
                            studentToEdit.setRollNumber(newRollNumber);
                        }

                        System.out.print("Enter New Mobile Number (leave empty to keep existing): ");
                        String newMobile = scanner.nextLine();
                        if (!newMobile.isEmpty()) {
                        studentToEdit.setMobileNumber(newMobile);
                        }
                        System.out.print("Enter New Grade (leave empty to keep existing): ");
                        String newGrade = scanner.nextLine();
                        if (!newGrade.isEmpty()) {
                        studentToEdit.setGrade(newGrade);
                        }
                        
                        System.out.print("Enter New Course (leave empty to keep existing): ");
                        String newCourse = scanner.nextLine();
                        if (!newCourse.isEmpty()) {
                            studentToEdit.setCourse(newCourse);
                        }

                        System.out.println("Student information updated.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        }
    }
}
