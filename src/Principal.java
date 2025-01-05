import java.util.Scanner;

public class Principal {

    private final GestorDeConsultas gestor;
    private final HistorialDeConsultas historial;

    public Principal(){
        this.gestor = new GestorDeConsultas();
        this.historial = new HistorialDeConsultas();
    }

    public void mostrarMenuPrincipal(){
        Scanner lectura = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n**** Conversor de Monedas ****");
            System.out.println("1. PEN a USD");
            System.out.println("2. PEN a EUR");
            System.out.println("3. PEN a JPY");
            System.out.println("4. PEN a AUD");
            System.out.println("5. PEN a CAD");
            System.out.println("6. PEN a MXN");
            System.out.println("7. Otras conversiones");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = lectura.nextInt();
            procesarOpcion(opcion);
        } while (opcion != 8);

        lectura.close();
    }

    public void procesarOpcion(int opcion){
        switch (opcion){
            case 1: realizarConversion("PEN", "USD"); break;
            case 2: realizarConversion("PEN", "EUR"); break;
            case 3: realizarConversion("PEN", "JPY"); break;
            case 4: realizarConversion("PEN", "AUD"); break;
            case 5: realizarConversion("PEN", "CAD"); break;
            case 6: realizarConversion("PEN", "MXN"); break;
            case 7: realizarConversionAdicional(); break;
            case 8:
                System.out.println("El conversor de monedas finalizó");
                break;
            default:
                System.out.println("Opción inválida. Intente de nuevo.");
        }
    }

    private void realizarConversion(String monedaBase, String monedaObjetivo){
        Scanner lectura = new Scanner(System.in);
        System.out.println("Ingrese el monto en " + monedaBase + ":");
        double monto = lectura.nextDouble();

        try {
            double tasaCambio = gestor.consultarCambio(monedaBase,monedaObjetivo);
            double montoConvertido = gestor.convertirMoneda(monto,tasaCambio);

            System.out.println("Monto convertido: " + montoConvertido + " " + monedaObjetivo);
            String fechaActual = historial.obtenerFechaActual();
            historial.guardarConsulta(fechaActual,
                    monedaBase + " a " + monedaObjetivo,
                    monto,
                    montoConvertido);
        } catch (Exception e) {
            System.out.println("Error al realizar la conversion: " + e.getMessage());
        }
    }

    private void realizarConversionAdicional(){
        Scanner lectura = new Scanner(System.in);
        System.out.println("Ingrese la moneda base (ej: USD): ");
        String monedaBase = lectura.next().toUpperCase();

        System.out.println("Ingrese la moneda base (ej: USD): ");
        String monedaObjetivo = lectura.next().toUpperCase();

        realizarConversion(monedaBase,monedaObjetivo);
    }

    public static void main(String[] args) {
        Principal app = new Principal();
        app.mostrarMenuPrincipal();
    }
}
