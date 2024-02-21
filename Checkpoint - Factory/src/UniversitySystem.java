package src;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class UniversitySystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Carga de usuarios desde el archivo CSV
        List<Map<String, String>> usersData = CSVDataSource.showData("usuarios");

        System.out.println("¡Bienvenido!");
        System.out.println("Ingrese su nombre de usuario:");
        String name = scanner.nextLine();
        System.out.println("Ingrese su contraseña:");
        String password = scanner.nextLine();

        Users userFactoryauthenticated = null;
        for (Map<String, String> userData : usersData) {
            String nombre = userData.get("Nombre");
            String contraseña = userData.get("Contraseña");

            if (Objects.equals(nombre, name) && Objects.equals(contraseña, password)) {
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
            System.out.println("El usuario no se ha encontrado.");
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