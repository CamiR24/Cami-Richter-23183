import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class Professor extends Users implements IUserFactory{
    
    public Professor(String id, String name, String password){
        super(id, name, password);
    }

    @Override
    public void showOptions() {
        System.out.println("1. Ingresar notas del estudiante en una asignatura");
        System.out.println("2. cobrar pago");
        System.out.println("3. historial de pagos");
    }

    @Override
    void action(int option) {
        switch(option){
            case 1:
                AddGrades();
                break;
            case 2:
                charge();
                break;
            case 3:
                ConsultStudentPayment();
                break;
            default:
                System.out.println("Opción no valida");
                break;
        }
    }
    
    public void AddGrades(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre de la clase:");
        String courseName = scanner.nextLine();
        List<Map<String, String>> grades = new ArrayList<>();
        
        String studentName;
        do {
            System.out.println("Ingrese el nombre del estudiante (o 'salir' para finalizar):");
            studentName = scanner.nextLine();
            if (!studentName.equals("salir")) {
                System.out.println("Ingrese la nota:");
                String grade = scanner.nextLine();
                grades.add(Map.of("studentName", studentName, "class", courseName, "grade", grade));
            }
        } while (!studentName.equals("salir"));
        
        if (!grades.isEmpty()) {
            CSVDataSource.saveData("notas", grades);
            System.out.println("Notas ingresadas correctamente.");
        }
        scanner.close();
    }

    public void charge(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el monto del pago:");
        String amount = scanner.nextLine();

        // Usar LinkedHashMap para mantener el orden de inserción
        Map<String, String> paymentData = new LinkedHashMap<>();
        paymentData.put("amount", amount);
        paymentData.put("teacherName", this.getName());
        paymentData.put("status", "pending");

        CSVDataSource.saveData("pagosDocentes", List.of(paymentData));
        System.out.println("Pago cobrado correctamente.");
        scanner.close();
    }

    public void ConsultStudentPayment(){
        List<Map<String, String>> payments = CSVDataSource.showData("pagosDocentes");
        boolean Payments = false;
        
        for (Map<String, String> payment : payments) {
            if (payment.get("teacherName").equals(this.getName())) {
                System.out.println("Monto: " + payment.get("amount") + ", Estado: " + payment.get("status"));
                Payments = true;
            }
        }
        
        if (!Payments) {
            System.out.println("Aún no hay pagos registrados.");
        }
    }
}
