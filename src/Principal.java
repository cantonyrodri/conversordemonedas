import java.util.Scanner;

public class Principal {

    private GestorDeConsultas gestor;
    private HistorialDeConsultas historial;

    public Principal(){
        this.gestor = new GestorDeConsultas();
        this.historial = new HistorialDeConsultas();
    }

    public void mostrarMenuPrincipal(){
        Scanner lectura = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n**** Conversor de Monedas ****");
            System.out.println("1. USD a EUR");
            System.out.println("2. USD a GBP");
            System.out.println("3. USD a JPY");
            System.out.println("4. USD a AUD");
            System.out.println("5. USD a CAD");
            System.out.println("6. USD a MXN");
            System.out.println("7. Otras conversiones");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = lectura.nextInt();
            procesarOpcion(opcion);
        } while (opcion != 8);

        lectura.close();
    }

    public void procesarOpcion(int opcion){
        Scanner lectura = new Scanner(System.in);
        switch (opcion){
            case 1: realizarConversion("USD", "EUR"); break;
            case 2: realizarConversion("USD", "GBP"); break;
            case 3: realizarConversion("USD", "JPY"); break;
            case 4: realizarConversion("USD", "AUD"); break;
            case 5: realizarConversion("USD", "CAD"); break;
            case 6: realizarConversion("USD", "MXN"); break;
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
            historial.guardarConsulta(fechaActual, monedaBase + " a " + monedaObjetivo, montoConvertido);
        } catch (Exception e) {
            System.out.println("Error al realizar al conversion: " + e.getMessage());;
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