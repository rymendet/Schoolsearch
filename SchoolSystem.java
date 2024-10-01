import java.io.*;
import java.util.*;

public class SchoolSystem {

    private List<String[]> students = new ArrayList<>();

    // конструктор класу, який зчитує файл students.txt і заповнює список students
    public SchoolSystem(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            students.add(line.split(",\\s*"));
        }
        br.close();
    }

    // Метод для пошуку і виведення всієї інформації про студента за прізвищем
    public void findAllInfoByLastName(String lastName) {
        for (String[] student : students) {
            if (student[0].equalsIgnoreCase(lastName)) {
                System.out.println("Student Info: " + student[0] + ", " + student[1] + ", Grade: " + student[2] + ", Classroom: " + student[3] + ", Bus: " + student[4] + ", Teacher: " + student[5] + ", " + student[6]);
            }
        }
    }

    // пошук студентів за прізвищем або ім'ям (виведення класу та вчителя)
    public void findStudentByName(String name) {
        for (String[] student : students) {
            if (student[0].equalsIgnoreCase(name) || student[1].equalsIgnoreCase(name)) {
                System.out.println(student[1] + " " + student[0] + " is in classroom " + student[3] + ", Teacher: " + student[6] + " " + student[5]);
            }
        }
    }

    // пошук маршруту автобуса за прізвищем або ім'ям студента
    public void findBusByName(String name) {
        for (String[] student : students) {
            if (student[0].equalsIgnoreCase(name) || student[1].equalsIgnoreCase(name)) {
                System.out.println(student[1] + " " + student[0] + " takes bus " + student[4]);
            }
        }
    }

    // пошук учнів цього викладача за прізвищем або ім'ям вчителя
    public void findStudentsByTeacher(String teacherName) {
        for (String[] student : students) {
            if (student[5].equalsIgnoreCase(teacherName) || student[6].equalsIgnoreCase(teacherName)) {
                System.out.println(student[1] + " " + student[0]);
            }
        }
    }

    // пошук учнів за номером автобуса
    public void findStudentsByBusRoute(int busRoute) {
        for (String[] student : students) {
            if (Integer.parseInt(student[4]) == busRoute) {
                System.out.println(student[1] + " " + student[0] + " takes bus " + busRoute);
            }
        }
    }

    // пошук учнів за класом
    public void findStudentsByGrade(int grade) {
        for (String[] student : students) {
            if (Integer.parseInt(student[2]) == grade) {
                System.out.println(student[1] + " " + student[0] + " is in grade " + grade);
            }
        }
    }

    // пошук учнів за номером класу
    public void findStudentsByClassroom(int classroom) {
        for (String[] student : students) {
            if (Integer.parseInt(student[3]) == classroom) {
                System.out.println(student[1] + " " + student[0] + " is in classroom " + classroom);
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
            scanner.nextLine(); // очищення буфера

            switch (choice) {
                case 1: // Пошук всієї інформації про студента за прізвищем
                    System.out.print("Enter the student's last name: ");
                    String lastName = scanner.nextLine(); // уникнення конфлікту змінних
                    findAllInfoByLastName(lastName);
                    break;
                case 2: // Пошук студента за прізвищем або ім'ям
                    System.out.print("Enter the student's last or first name: ");
                    String name = scanner.nextLine(); // виправлена змінна name
                    findStudentByName(name);
                    break;
                case 3: // Пошук автобуса за прізвищем або ім'ям
                    System.out.print("Enter the student's last or first name: ");
                    name = scanner.nextLine();
                    findBusByName(name);
                    break;
                case 4: // Пошук учнів за прізвищем або ім'ям вчителя
                    System.out.print("Enter the teacher's last or first name: ");
                    String teacherName = scanner.nextLine();
                    findStudentsByTeacher(teacherName);
                    break;
                case 5: // Пошук студентів за номером автобуса
                    System.out.print("Enter the bus number: ");
                    int busRoute = scanner.nextInt();
                    findStudentsByBusRoute(busRoute);
                    break;
                case 6: // Пошук студентів за рівнем класу
                    System.out.print("Enter student's Grade Level (0 for Kindergarten): ");
                    int grade = scanner.nextInt();
                    findStudentsByGrade(grade);
                    break;
                case 7: // Пошук студентів за номером класу
                    System.out.print("Enter the classroom number: ");
                    int classroom = scanner.nextInt();
                    findStudentsByClassroom(classroom);
                    break;
                case 8: // Вихід з програми
                    System.out.println("Exit...");
                    scanner.close();
                    return;
                default: // Неправильний вибір
                    System.out.println("ERROR: Wrong choice, try it again.");
            }
        }
    }

    public static void main(String[] args) {
        try {
            SchoolSystem school = new SchoolSystem("students.txt");
            school.startMenu();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
