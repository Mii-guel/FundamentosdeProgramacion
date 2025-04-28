
package exp3_s7_miguel_castillo;

import java.util.ArrayList;
import java.util.Scanner;

public class Exp3_s7_Miguel_Castillo {
    static ArrayList<String> ubicaciones = new ArrayList<>();
    static ArrayList<Double> preciosFinales = new ArrayList<>();
    static ArrayList<Double> descuentosAplicados = new ArrayList<>();
    static double totalIngresos = 0;
    static int entradasVendidas = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int continuar = 1;
        
        
        // menú
        while (continuar == 1) {
            int opcion = 0;
            System.out.println("Bienvenido al Teatro Moro");
            System.out.println("1. Comprar entradas");
            System.out.println("2. Ver resumen de ventas");
            System.out.println("3. Generar boleta");
            System.out.println("4. Ver ingresos totales");
            System.out.println("5. Salir");
            System.out.println("Seleccione una opción: ");
                        
            // Validación de entrada
            try {
                opcion = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Ingrese un número válido.");
                scanner.next(); 
                continue;
            }
            
            // opciones
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
                    System.out.println("Gracias por su compra.");
                    continuar = 0;
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }
        }
        scanner.close();
    }

    public static void venderEntrada(Scanner scanner) {
        // Menú de selección de ubicación
        System.out.println("Seleccione la ubicación:");
        System.out.println("1. VIP ($20000)");
        System.out.println("2. Platea ($12000)");
        System.out.println("3. Balcón ($8000)");
        System.out.println("Ingrese el número de la opción: ");

        int tipo = 0;

        // Validación de entrada
        try {
            tipo = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Ingreso inválido. Debe ser un número.");
            scanner.next(); 
            return;
        }

        String ubicacion = "";
        double precioBase = 0.0;

        // Guardar eleccion del tipo de entrada
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

        // edad del comprador
        System.out.println("Ingrese su edad: ");
        int edad = 0;

        // Validación de entrada
        try {
            edad = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Edad inválida.");
            scanner.next(); 
            return;
        }

        double descuento = 0.0;
        if (edad >= 0 && edad <= 23) {
            System.out.println("Usted opta a un descuento del 10% para su entrada.");
            descuento = precioBase * 0.10;
        } else if (edad >= 60 && edad <= 131) {
            System.out.println("Usted opta a un descuento del 15% para su entrada.");
            descuento = precioBase * 0.15;
        } else {
            System.out.println("No se le aplica descuento.");
        }

        double precioFinal = precioBase - descuento;

        // Guardar datos
        ubicaciones.add(ubicacion);
        descuentosAplicados.add(descuento);
        preciosFinales.add(precioFinal);
        totalIngresos += precioFinal;
        entradasVendidas++;

        System.out.println("Entrada vendida con éxito.");
        System.out.println("Ubicación: " + ubicacion);
        System.out.println("Edad: " + edad);
        System.out.println("Descuento: $" + descuento);
        System.out.println("Total a pagar: $" + precioFinal);
    }

    public static void verResumen() {
        System.out.println("Resumen de entradas:");
        for (int i = 0; i < ubicaciones.size(); i++) {
            System.out.println("Ubicación: " + ubicaciones.get(i) + " | Precio final: $" + preciosFinales.get(i) + " | Descuento: $" + descuentosAplicados.get(i));
        }
    }

    public static void generarBoleta() {
        System.out.println(" Boletas generadas:");
        for (int i = 0; i < ubicaciones.size(); i++) {
            System.out.println("-----------------------------");
            System.out.println("Teatro Moro -- Boleta");
            System.out.println("Ubicación: " + ubicaciones.get(i));
            System.out.println("Descuento aplicado: $" + descuentosAplicados.get(i));
            System.out.println("Precio final: $" + preciosFinales.get(i));
            System.out.println("¡Gracias por su compra!");
            System.out.println("-----------------------------");
        }
    }

    public static void verIngresosTotales() {
        System.out.println(" Ingresos totales: $" + totalIngresos);
        System.out.println(" Entradas vendidas: " + entradasVendidas);
    }
}