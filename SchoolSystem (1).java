import java.io.*;
import java.util.*;

public class SchoolSystem {

    private List<Student> students = new ArrayList<>();
    private Map<Integer, Teacher> teachers = new HashMap<>();

    public SchoolSystem(String studentFile, String teacherFile) throws IOException {
        loadStudents(studentFile);
        loadTeachers(teacherFile);
    }

    private void loadStudents(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",\\s*");
            students.add(new Student(parts[0], parts[1], Integer.parseInt(parts[2]),
                                      Integer.parseInt(parts[3]), Integer.parseInt(parts[4])));
        }
        br.close();
    }

    private void loadTeachers(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",\\s*");
            teachers.put(Integer.parseInt(parts[2]),
                         new Teacher(parts[0], parts[1], Integer.parseInt(parts[2])));
        }
        br.close();
    }

    public void findAllInfoByLastName(String lastName) {
        for (Student student : students) {
            if (student.lastName.equalsIgnoreCase(lastName)) {
                Teacher teacher = teachers.get(student.classroom);
                System.out.println("Student Info: " + student.lastName + ", " + student.firstName +
                        ", Grade: " + student.grade + ", Classroom: " + student.classroom +
                        ", Bus: " + student.bus + ", Teacher: " +
                        (teacher != null ? teacher.firstName + " " + teacher.lastName : "Unknown"));
            }
        }
    }

    public void findStudentByName(String name) {
        for (Student student : students) {
            if (student.lastName.equalsIgnoreCase(name) || student.firstName.equalsIgnoreCase(name)) {
                Teacher teacher = teachers.get(student.classroom);
                System.out.println(student.firstName + " " + student.lastName +
                        " is in classroom " + student.classroom + ", Teacher: " +
                        (teacher != null ? teacher.firstName + " " + teacher.lastName : "Unknown"));
            }
        }
    }

    public void findBusByName(String name) {
        for (Student student : students) {
            if (student.lastName.equalsIgnoreCase(name) || student.firstName.equalsIgnoreCase(name)) {
                System.out.println(student.firstName + " " + student.lastName +
                        " takes bus " + student.bus);
            }
        }
    }

    public void findStudentsByTeacher(String teacherName) {
        for (Map.Entry<Integer, Teacher> entry : teachers.entrySet()) {
            Teacher teacher = entry.getValue();
            if (teacher.lastName.equalsIgnoreCase(teacherName) || teacher.firstName.equalsIgnoreCase(teacherName)) {
                for (Student student : students) {
                    if (student.classroom == teacher.classroom) {
                        System.out.println(student.firstName + " " + student.lastName);
                    }
                }
            }
        }
    }

    public void findStudentsByBusRoute(int busRoute) {
        for (Student student : students) {
            if (student.bus == busRoute) {
                System.out.println(student.firstName + " " + student.lastName +
                        " takes bus " + busRoute);
            }
        }
    }

    public void findStudentsByGrade(int grade) {
        for (Student student : students) {
            if (student.grade == grade) {
                Teacher teacher = teachers.get(student.classroom);
                System.out.println(student.firstName + " " + student.lastName + " is in grade " + grade +
                        ", Teacher: " +
                        (teacher != null ? teacher.firstName + " " + teacher.lastName : "Unknown"));
            }
        }
    }

    public void findStudentsByClassroom(int classroom) {
        for (Student student : students) {
            if (student.classroom == classroom) {
                System.out.println(student.firstName + " " + student.lastName +
                        " is in classroom " + classroom);
            }
        }
    }

    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n------------------MENU------------------");
            System.out.println("1. Find all info by student's last name");
            System.out.println("2. Search by name (search class and teacher)");
            System.out.println("3. Search by name (search bus)");
            System.out.println("4. Search students by teacher");
            System.out.println("5. Search for students by bus");
            System.out.println("6. Search students by grade");
            System.out.println("7. Search students by classroom");
            System.out.println("8. Exit the program");
            System.out.print("Choose an option (1-8): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter the student's last name: ");
                    String lastName = scanner.nextLine();
                    findAllInfoByLastName(lastName);
                    break;
                case 2:
                    System.out.print("Enter the student's last or first name: ");
                    String name = scanner.nextLine();
                    findStudentByName(name);
                    break;
                case 3:
                    System.out.print("Enter the student's last or first name: ");
                    name = scanner.nextLine();
                    findBusByName(name);
                    break;
                case 4:
                    System.out.print("Enter the teacher's last or first name: ");
                    String teacherName = scanner.nextLine();
                    findStudentsByTeacher(teacherName);
                    break;
                case 5:
                    System.out.print("Enter the bus number: ");
                    int busRoute = scanner.nextInt();
                    findStudentsByBusRoute(busRoute);
                    break;
                case 6:
                    System.out.print("Enter student's Grade Level (0 for Kindergarten): ");
                    int grade = scanner.nextInt();
                    findStudentsByGrade(grade);
                    break;
                case 7:
                    System.out.print("Enter the classroom number: ");
                    int classroom = scanner.nextInt();
                    findStudentsByClassroom(classroom);
                    break;
                case 8:
                    System.out.println("Exit...");
                    scanner.close();
                    return;
                default:
                    System.out.println("ERROR: Wrong choice, try again.");
            }
        }
    }

    static class Student {
        String lastName, firstName;
        int grade, classroom, bus;

        Student(String lastName, String firstName, int grade, int classroom, int bus) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.grade = grade;
            this.classroom = classroom;
            this.bus = bus;
        }
    }

    static class Teacher {
        String lastName, firstName;
        int classroom;

        Teacher(String lastName, String firstName, int classroom) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.classroom = classroom;
        }
    }

    public static void main(String[] args) {
        try {
            SchoolSystem school = new SchoolSystem("list.txt", "teacher.txt");
            school.startMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}