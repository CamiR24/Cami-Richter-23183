package src;
import java.util.*;
import java.util.List;

public class Audit extends Users implements IUserFactory{

    public Audit(String id, String name, String password) {
        super(id, name, password);
    }

    @Override
    
    public void showOptions() {
        System.out.println("1. consultar resumen de notas del estudiante");
        System.out.println("2. consultar resumen de pagos del estudiante");
        System.out.println("3. revisar pagos a docentes");
    }

    @Override
    void action(int option) {
        switch(option){
            case 1:
                ConsultStudentGrades();
                break;
            case 2:
                ConsultStudentPayment();
                break;
            case 3:
                AssignPayment();
                break;
            default:
                System.out.println("Opción no valida");
                break;
        }
    }

    public void AssignPayment(){

    }
    
    public void ConsultStudentGrades(){
         List<Map<String, String>> grades = CSVDataSource.showData("notas");
        if (grades.isEmpty()) {
            System.out.println("No hay notas disponibles para revisar.");
        } else {
            System.out.println("Notas de los estudiantes:");
            for (Map<String, String> grade : grades) {
                System.out.println("Nombre Estudiante: " + grade.get("studentName") + ", Curso: " + grade.get("courseName") + ", Nota: " + grade.get("grade"));
            }
        }
    }

    public void ConsultStudentPayment(){
        List<Map<String, String>> payments = CSVDataSource.showData("pagos");
        if (payments.isEmpty()) {
            System.out.println("No hay pagos de estudiantes");
        } else {
            System.out.println("Pagos realizados:");
            for (Map<String, String> payment : payments) {
                System.out.println("Nombre Estudiante: " + payment.get("studentName") + ", Monto: " + payment.get("amount") + ", Estado: " + payment.get("status"));
            }
        }
    }
}
