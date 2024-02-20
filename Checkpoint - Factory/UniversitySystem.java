import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.*;

public class UniversitySystem {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        CSVDataSource dataSource = new CSVDataSource(); // Asumiendo que esta clase ya está implementada adecuadamente

        // Carga de usuarios desde el archivo CSV
        List<Map<String, String>> usersData = dataSource.loadData("Informacion.csv");
        
        System.out.println("Ingrese su nombre:");
        String name = scanner.nextLine();
        System.out.println("Ingrese su contraseña:");
        String password = scanner.nextLine();

        User userFactoryauthenticated = null;
        for (Map<String, String> userData : usersData) {
            if (userData.get("Nombre").equals(name) && userData.get("Contraseña").equals(password)) {
                // Autenticación exitosa, determinar el tipo de usuario
                System.out.println("Seleccione el tipo de usuario:");
                System.out.println("1. Auditor Externo");
                System.out.println("2. Docente");
                System.out.println("3. Estudiante");
                System.out.println("4. Personal Administrativo");
                int option = scanner.nextInt();
        
                IUserFactory userFactory = UserFactory.getUserInstance(option);   
            }
        }

        if (userFactoryauthenticated != null) {
            userFactoryauthenticated.showOptions(); // Mostrar el menú específico del tipo de usuario
        } else {
            System.out.println("Credenciales no válidas o tipo de usuario no reconocido.");
        }

        scanner.close();
    }

    }
