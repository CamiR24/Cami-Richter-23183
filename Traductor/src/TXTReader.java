import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TXTReader {

    private final static String paths = "src/";

    public static List<Map<String, String>> showData(String category) {
        List<Map<String, String>> data = new ArrayList<>();
        Path path = Paths.get(paths + category + ".txt");
        try (Stream<String> lines = Files.lines(path)) {
            List<String> allLines = lines.collect(Collectors.toList());
            if (!allLines.isEmpty()) {
                // Diagnóstico: Imprime la ruta absoluta del archivo
                System.out.println("Ruta absoluta del archivo CSV: " + path.toAbsolutePath());
                
                String[] headers = allLines.get(0).split(",");
                
                for (int i = 1; i < allLines.size(); i++) {
                    // Dividir cada línea en sus componentes y trimarlos para eliminar espacios adicionales
                    String[] values = allLines.get(i).split(",");
                    Map<String, String> row = new HashMap<>();
                    for (int j = 0; j < headers.length; j++) {
                        // Asegúrate de trimar tanto las claves como los valores
                        row.put(headers[j].trim(), j < values.length ? values[j].trim() : "");
                    }
                    // Diagnóstico: Imprime cada fila leída
                    data.add(row);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
