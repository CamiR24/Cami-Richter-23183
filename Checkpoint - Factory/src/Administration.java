package src;
import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class Administration extends Users implements IUserFactory {
    
    public Administration(String id, String name, String password) {
        super(id, name, password);
    }
    
    @Override
    public void showOptions() {
        System.out.println("1. crear curso");
        System.out.println("2. crear docente");
        System.out.println("3. crear estudiante");
        System.out.println("4. asignar estudiante al curso");
        System.out.println("5. asignar docente al curso");
        System.out.println("6. asignar pago al catedrático");
        System.out.println("7. consultar resumen de notas del estudiante");
        System.out.println("8. consultar resumen de pagos del estudiante");
    }

    @Override
    void action(int option) {
        switch(option){
            case 1:
                createCourse();
                break;
            case 2:
                createProfessor();
                break;
            case 3:
                createStudent();
                break;
            case 4:
                AssignStudent();
                break;
            case 5:
                AssignProfessor();
                break;
            case 6:
                AssignPayment();
                break;
            case 7:
                ConsultStudentGrades();
                break;
            case 8:
                ConsultStudentPayment();
                break;
            default:
                System.out.println("Opción no valida");
                break;
        }
    }

    public void createCourse(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del curso nuevo:");
        String courseName = scanner.nextLine();

        List<Map<String, String>> courses = new ArrayList<>();
        Map<String, String> newCourse = new HashMap<>();
        newCourse.put("courseName", courseName);
        courses.add(newCourse);

        CSVDataSource.saveData("cursos", courses);
        System.out.println("Curso creado");
        scanner.close();
    }

    public void createProfessor(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del profesor nuevo:");
        String professorName = scanner.nextLine();
        System.out.println("Ingrese el ID del profesor:");
        String professorID = scanner.nextLine();

        List<Map<String, String>> professors = new ArrayList<>();
        Map<String, String> newProfessor = new HashMap<>();
        newProfessor.put("professorName", professorName);
        newProfessor.put("professorID", professorID);
        professors.add(newProfessor);

        CSVDataSource.saveData("profesores", professors);
        System.out.println("Profesor creado");
        scanner.close();
    }

    public void createStudent(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del estudiante nuevo:");
        String studentName = scanner.nextLine();
        System.out.println("Ingrese el ID del estudiante:");
        String studentID = scanner.nextLine();

        List<Map<String, String>> students = new ArrayList<>();
        Map<String, String> newStudent = new HashMap<>();
        newStudent.put("professorName", studentName);
        newStudent.put("professorID", studentID);
        students.add(newStudent);

        CSVDataSource.saveData("estudientes", students);
        System.out.println("Estudiante creado");
        scanner.close();
    }
    
    public void AssignStudent(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Asignar estudiante a curso:");
        System.out.println("Ingrese el nombre del estudiante:");
        String studentName = scanner.nextLine();
        System.out.println("Ingrese el nombre del curso:");
        String courseName = scanner.nextLine();
    
        List<Map<String, String>> assignments = new ArrayList<>();
        Map<String, String> newAssignment = new HashMap<>();
        newAssignment.put("studentName", studentName);
        newAssignment.put("courseName", courseName);
        assignments.add(newAssignment);
    
        CSVDataSource.saveData("asignaciones", assignments);
        System.out.println("Estudiante asignado al curso");
        scanner.close();
    }

    public void AssignProfessor(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Asignar profesor a curso:");
        System.out.println("Ingrese el nombre del profesor:");
        String professorName = scanner.nextLine();
        System.out.println("Ingrese el nombre del curso:");
        String courseName = scanner.nextLine();
    
        List<Map<String, String>> professorAssignments = new ArrayList<>();
        Map<String, String> newProfessorAssignment = new HashMap<>();
        newProfessorAssignment.put("professorName", professorName);
        newProfessorAssignment.put("courseName", courseName);
        professorAssignments.add(newProfessorAssignment);
    
        CSVDataSource.saveData("asignacionesDeProfesores", professorAssignments);
        System.out.println("Profesor asignado al curso");
        scanner.close();
    }
    
    public void AssignPayment(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Asignar pago:");
        System.out.println("Ingrese el nombre del catedrático:");
        String teacherName = scanner.nextLine();
        System.out.println("Ingrese el monto del pago:");
        String paymentAmount = scanner.nextLine();
    
        List<Map<String, String>> payments = new ArrayList<>();
        Map<String, String> newPayment = new HashMap<>();
        newPayment.put("teacherName", teacherName);
        newPayment.put("paymentAmount", paymentAmount);
        newPayment.put("status", "pending");
        payments.add(newPayment);
    
        CSVDataSource.saveData("pagosProfesores", payments);
        System.out.println("Pago asignado");
        scanner.close();
    }
    
    public void ConsultStudentGrades(){
        System.out.println("Resumen de Notas:");
        List<Map<String, String>> grades = CSVDataSource.showData("notas");
        grades.forEach(grade -> System.out.println("Nombre Estudiante: " + grade.get("studentName") + ", Curso: " + grade.get("courseName") + ", Nota: " + grade.get("grade")));
    }

    public void ConsultStudentPayment(){
        System.out.println("Resumen de Pagos:");
        List<Map<String, String>> payments = CSVDataSource.showData("pagos");
        payments.forEach(payment -> System.out.println("Nombre Estudiante: " + payment.get("studentId") + ", Monto: " + payment.get("amount") + ", Estatus: " + payment.get("status")));
    }
}
