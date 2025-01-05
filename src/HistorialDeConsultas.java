import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistorialDeConsultas {
    private static final String ARCHIVO_HISTORIAL = "historial_consultas.txt";

    public void guardarConsulta(String fecha, String detalle,double montoOriginal, double montoConvertido){
        try (BufferedWriter writer= new BufferedWriter(new FileWriter(ARCHIVO_HISTORIAL,true))){
            writer.write(fecha + "," + detalle + "," + montoOriginal + "," + montoConvertido);
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Error al guardar el historial, " + e.getMessage());;
        }
    }

    public String obtenerFechaActual(){
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return ahora.format(formatter);
    }

}
