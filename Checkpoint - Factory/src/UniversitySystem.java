import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UniversitySystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CSVDataSource dataSource = new CSVDataSource(); // Asumiendo que esta clase ya está implementada adecuadamente

        // Carga de usuarios desde el archivo CSV
        List<Map<String, String>> usersData = dataSource.showData("usuarios");

        System.out.println("Ingrese su nombre:");
        String name = scanner.nextLine();
        System.out.println("Ingrese su contraseña:");
        String password = scanner.nextLine();

        Users userFactoryauthenticated = null;
        for (Map<String, String> userData : usersData) {
            if (userData.get("Nombre").equals(name) && userData.get("Contraseña").equals(password)) {
                // Autenticación exitosa, determinar el tipo de usuario
                String tipoUsuario = userData.get("TipoUsuario");
                int userType = getType(tipoUsuario);
                userFactoryauthenticated = UserFactory.createUser(userType, userData.get("ID"), name, password);
                break; // Salir del bucle una vez que se haya autenticado al usuario
            }
        }

        if (userFactoryauthenticated != null) {
            userFactoryauthenticated.showOptions(); // Mostrar el menú específico del tipo de usuario autenticado
        } else {
            System.out.println("Credenciales no válidas o tipo de usuario no reconocido.");
        }

        scanner.close();
    }

    private static int getType(String tipoUsuario) {
        switch (tipoUsuario) {
            case "Estudiante":
                return UserFactory.STUDENT_TYPE;
            case "Docente":
                return UserFactory.PROFESSOR_TYPE;
            case "PersonalAdministrativo":
                return UserFactory.ADMINISTRATION_TYPE;
            case "AuditorExterno":
                return UserFactory.AUDIT_TYPE;
            default:
                return -1; // Valor no válido
        }
    }
}