import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GestorDeConsultas {
    public static final String API_URL = "https://v6.exchangerate-api.com/v6/c500b9f73ca66837fefbb436/pair/";

    public double consultarCambio(String monedaBase, String monedaObjetivo){
        try {
            String urlDirection = API_URL + monedaBase + "/" + monedaObjetivo;
            URI direccion = URI.create(urlDirection);

            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest pedido = HttpRequest.newBuilder()
                    .uri(direccion)
                    .build();
            HttpResponse<String> respuesta = cliente
                    .send(pedido, HttpResponse.BodyHandlers.ofString());

            var json = respuesta.body();
            JsonElement jsonElement = JsonParser.parseString(json);
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            return jsonObject.get("conversion_rate").getAsDouble();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public double convertirMoneda(double monto, double tasaCambio){
        return monto * tasaCambio;
    }
}
