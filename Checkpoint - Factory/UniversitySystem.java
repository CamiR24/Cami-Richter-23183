import java.util.*;
import java.util.Scanner;

public class UniversitySystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione el tipo de usuario:");
        System.out.println("1. Auditor Externo");
        System.out.println("2. Docente");
        System.out.println("3. Estudiante");
        System.out.println("4. Personal Administrativo");
        int option = scanner.nextInt();

        IUserFactory userFactory = UserFactory.getUserInstance(option);
        userFactory.showOptions();
    }
}
