import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

       Dictionary dictionary = new Dictionary();

        // Pedir al usuario que seleccione un idioma
        System.out.print("Bienvenido al traductor");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione un idioma (english/spanish/french):");
        String language = scanner.nextLine();

        // Pedir al usuario que ingrese una palabra
        System.out.println("Ingrese una palabra:");
        String word = scanner.nextLine();

        // Traducir la palabra al idioma seleccionado y mostrar las traducciones en los otros idiomas
        String translation = dictionary.translate(word, language);
        if (translation != null) {
            System.out.println("Traducción:");
            System.out.println(translation);
        } else {
            System.out.println("Idioma no soportado");
        }

        scanner.close();
    }
}
