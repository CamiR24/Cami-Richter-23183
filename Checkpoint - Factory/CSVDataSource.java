import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVDataSource {

    public void saveData(String category, List<Map<String, String>> data) {
        try {
            File file = new File("Informacion.csv");
            boolean isNewFile = file.createNewFile(); // Crea el archivo si no existe y verifica si es nuevo
            try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) {
                if (isNewFile) {
                    // Suponiendo que todos los mapas tienen las mismas claves, toma el primer elemento para los encabezados
                    pw.println("NOMBRE, CONTRASEÑA TIPO, PAGO MATRICULA, MONTO PAGO, COBRO, MONTO COBRO, CURSO ASIGNADO, CURSO 1, NOTA"); // Escribe los encabezados
                }
                for (Map<String, String> row : data) {
                    String line = String.join(",", row.values());
                    pw.println(line); // Escribe los datos
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Map<String, String>> loadData(String category) {
        List<Map<String, String>> data = new ArrayList<>();
        Path path = Paths.get("Informacion.csv");
        try (Stream<String> lines = Files.lines(path)) {
            List<String> allLines = lines.collect(Collectors.toList());
            if (!allLines.isEmpty()) {
                String[] headers = allLines.get(0).split(",");
                for (int i = 1; i < allLines.size(); i++) { // Empieza en 1 para saltar los encabezados
                    String[] values = allLines.get(i).split(",");
                    Map<String, String> row = new HashMap<>();
                    for (int j = 0; j < headers.length; j++) {
                        row.put(headers[j], values[j]);
                    }
                    data.add(row);
                }
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return data;
    }
}