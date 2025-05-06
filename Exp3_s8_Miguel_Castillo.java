package exp3_s8_miguel_castillo;

import java.util.ArrayList;
import java.util.Scanner;

public class Exp3_s8_Miguel_Castillo {
    static ArrayList<String> ubicaciones = new ArrayList<>();
    static ArrayList<Double> preciosFinales = new ArrayList<>();
    static ArrayList<Double> descuentosAplicados = new ArrayList<>();
    static ArrayList<Integer> edadesClientes = new ArrayList<>();
    static double totalIngresos = 0;
    static int entradasVendidas = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int continuar = 1;

        while (continuar == 1) {
            System.out.println("\nBienvenido al Teatro Moro");
            System.out.println("1. Comprar entrada");
            System.out.println("2. Ver resumen de ventas");
            System.out.println("3. Generar boleta");
            System.out.println("4. Ver ingresos totales");
            System.out.println("5. Eliminar una venta");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion;
            try {
                opcion = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Por favor ingrese un número válido.");
                scanner.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    venderEntrada(scanner);
                    break;
                case 2:
                    verResumen();
                    break;
                case 3:
                    generarBoleta();
                    break;
                case 4:
                    verIngresosTotales();
                    break;
                case 5:
                    eliminarVenta(scanner);
                    break;
                case 6:
                    System.out.println("Gracias por visitar el Teatro Moro.");
                    continuar = 0;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }

        scanner.close();
    }

    public static void venderEntrada(Scanner scanner) {
        System.out.println("\nSeleccione la ubicación:");
        System.out.println("1. VIP ($20000)");
        System.out.println("2. Platea ($12000)");
        System.out.println("3. Balcón ($8000)");
        System.out.print("Ingrese número de opción: ");

        int tipo;
        try {
            tipo = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Entrada inválida.");
            scanner.nextLine();
            return;
        }

        String ubicacion = "";
        double precioBase = 0;

        switch (tipo) {
            case 1:
                ubicacion = "VIP";
                precioBase = 20000;
                break;
            case 2:
                ubicacion = "Platea";
                precioBase = 12000;
                break;
            case 3:
                ubicacion = "Balcón";
                precioBase = 8000;
                break;
            default:
                System.out.println("Opción inválida.");
                return;
        }

        System.out.print("Ingrese su edad: ");
        int edad;
        try {
            edad = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Edad inválida.");
            scanner.nextLine();
            return;
        }

        double descuento = 0;
        if (edad <= 23) {
            descuento = precioBase * 0.10;
            System.out.println("Descuento del 10% aplicado.");
        } else if (edad >= 60) {
            descuento = precioBase * 0.15;
            System.out.println("Descuento del 15% aplicado.");
        }

        double precioFinal = precioBase - descuento;

        ubicaciones.add(ubicacion);
        descuentosAplicados.add(descuento);
        preciosFinales.add(precioFinal);
        edadesClientes.add(edad);
        totalIngresos += precioFinal;
        entradasVendidas++;

        System.out.println("Entrada vendida con éxito.");
        System.out.println("Ubicación: " + ubicacion);
        System.out.println("Precio final: $" + precioFinal);
    }

    public static void verResumen() {
        System.out.println("\nResumen de ventas:");
        if (ubicaciones.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }

        for (int i = 0; i < ubicaciones.size(); i++) {
            System.out.println("Venta " + (i + 1) + ": Ubicación " + ubicaciones.get(i) + ", Precio final: $" + preciosFinales.get(i) + ", Descuento: $" + descuentosAplicados.get(i));
        }
    }

    public static void generarBoleta() {
        System.out.println("\nBoletas generadas:");
        if (ubicaciones.isEmpty()) {
            System.out.println("No hay boletas disponibles.");
            return;
        }

        for (int i = 0; i < ubicaciones.size(); i++) {
            System.out.println("-----------------------------");
            System.out.println("Teatro Moro - Boleta");
            System.out.println("Ubicación: " + ubicaciones.get(i));
            System.out.println("Edad cliente: " + edadesClientes.get(i));
            System.out.println("Descuento aplicado: $" + descuentosAplicados.get(i));
            System.out.println("Precio final: $" + preciosFinales.get(i));
            System.out.println("Gracias por su compra.");
            System.out.println("-----------------------------");
        }
    }

    public static void verIngresosTotales() {
        System.out.println("\nTotal de ingresos: $" + totalIngresos);
        System.out.println("Entradas vendidas: " + entradasVendidas);
    }

    public static void eliminarVenta(Scanner scanner) {
        if (ubicaciones.isEmpty()) {
            System.out.println("No hay ventas para eliminar.");
            return;
        }

        System.out.print("Ingrese el número de venta a eliminar (1 a " + ubicaciones.size() + "): ");
        int index;
        try {
            index = scanner.nextInt() - 1;
        } catch (Exception e) {
            System.out.println("Entrada inválida.");
            scanner.nextLine();
            return;
        }

        if (index < 0 || index >= ubicaciones.size()) {
            System.out.println("Índice fuera de rango.");
            return;
        }

        totalIngresos -= preciosFinales.get(index);
        entradasVendidas--;

        ubicaciones.remove(index);
        preciosFinales.remove(index);
        descuentosAplicados.remove(index);
        edadesClientes.remove(index);

        System.out.println("Venta eliminada exitosamente.");
    }
}

