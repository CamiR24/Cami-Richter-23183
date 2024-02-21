package src;
import java.util.*;
import java.util.stream.Collectors;

public class Student extends Users implements IUserFactory{
    
    public Student(String id, String name, String password) {
        super(id, name, password);
    }

    @Override
    public void showOptions() {
        System.out.println("1. consultar nota en una asignatura");
        System.out.println("2. realizar pago");
        System.out.println("3. consultar pagos");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch(option){
            case 1:
                ConsultStudentGrades();
                break;
            case 2:
                pay();
                break;
            case 3:
                consultPayment();
                break;
            default:
                System.out.println("Opción no valida");
                break;
        }
        scanner.close();
    }
    
    public void ConsultStudentGrades(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del curso para consultar la nota:");
        String courseName = scanner.nextLine(); // Leer el código del curso ingresado por el usuario

        List<Map<String, String>> grades = CSVDataSource.showData("NOTA");
        if (grades.isEmpty()) {
            System.out.println("Aún no hay notas disponibles.");
        } else {
            // Filtrar primero por ID del estudiante y luego por código del curso
            List<Map<String, String>> filteredGrades = grades.stream()
                    //.filter(grade -> grade.get("NOMBRE").equals(this.getName()) && grade.get("CURSO").equals(courseName))
                    .collect(Collectors.toList());

            if (filteredGrades.isEmpty()) {
                System.out.println("No se encontraron notas para el curso con nombre: " + courseName);
            } else {
                filteredGrades.forEach(grade -> 
                    System.out.println("Clase: " + grade.get("CURSO") + ", Nota: " + grade.get("NOTA")));
            }
        } 
        scanner.close();   
    }

    public void pay(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el monto del pago:");
        String amount = scanner.nextLine();
        
        // Usar LinkedHashMap para mantener el orden de inserción
        Map<String, String> paymentData = new LinkedHashMap<>();
        paymentData.put("status", "completed"); // Asegurarse de agregar los elementos en el orden deseado
        paymentData.put("amount", amount);
        paymentData.put("studentId", this.getName());
        
        CSVDataSource.saveData("pagos", List.of(paymentData));
        System.out.println("Pago realizado correctamente.");
        scanner.close();
    }

    public void consultPayment(){
        List<Map<String, String>> payments = CSVDataSource.showData("pagos");
        if (payments.isEmpty()) {
            System.out.println("Aún no hay pagos realizados.");
        } else {
            payments.stream()
                    .filter(payment -> payment.get("studentName").equals(this.getName()))
                    .forEach(payment -> System.out.println("Monto: " + payment.get("amount") + ", Estado: " + payment.get("status")));
        }
    }
}
