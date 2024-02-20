import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Student extends User implements IUserFactory{
    @Override
    public void showOptions() {
        System.out.println("1. consultar nota en una asignatura");
        System.out.println("2. realizar pago");
        System.out.println("3. consultar pagos");
    }

    @Override
    void action(int option) {
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
    }

    public void pay(){

    }

    public void consultPayment(){

    }

    @Override
    public User createUser(int userType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createUser'");
    }
}
