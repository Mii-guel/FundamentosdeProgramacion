
package eft_s9_miguel_castillo;

import java.util.ArrayList;
import java.util.Scanner;

// Clase que representa una entrada de teatro
class Entrada {
    String nombre;
    int edad;
    String genero;
    String tipoCliente;
    String seccion;
    int asiento;
    double precioFinal;

    // Constructor que inicializa los datos de la entrada
    public Entrada(String nombre, int edad, String genero, String tipoCliente, String seccion, int asiento, double precioFinal) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.tipoCliente = tipoCliente;
        this.seccion = seccion;
        this.asiento = asiento;
        this.precioFinal = precioFinal;
    }

    // Método que imprime los detalles de la boleta
    public void imprimirBoleta() {
        System.out.println("------ BOLETA ------");
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("Género: " + genero);
        System.out.println("Tipo Cliente: " + tipoCliente);
        System.out.println("Sección: " + seccion);
        System.out.println("Asiento: " + asiento);
        System.out.println("Precio Final: $" + precioFinal);
        System.out.println("--------------------");
    }
}

// Clase principal que maneja el sistema de ventas del Teatro Moro
public class EFT_S9_Miguel_Castillo {
    static Scanner sc = new Scanner(System.in); 
    static ArrayList<Entrada> ventas = new ArrayList<>(); // Lista que guarda todas las ventas realizadas
    static ArrayList<Integer> asientosOcupados = new ArrayList<>(); // Lista para verificar los asientos ocupados
    static final int TOTAL_ASIENTOS = 50; 

    // Arreglos para las secciones y los precios base de cada sección
    static String[] secciones = {"VIP", "Palco", "Platea Baja", "Platea Alta", "Galería"};
    static double[] preciosBase = {20000, 18000, 15000, 12000, 8000};

    public static void main(String[] args) {
        boolean salir = false;

        // Bucle principal del menú del programa
        while (!salir) {
            try {
                // Menú interactivo para el usuario
                System.out.println("\nBienvenido al Teatro Moro");
                System.out.println("1. Comprar entradas");
                System.out.println("2. Ver resumen de ventas");
                System.out.println("3. Generar boleta");
                System.out.println("4. Ver ingresos totales");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");
                int opcion = sc.nextInt();
                sc.nextLine(); // Limpiar el buffer

                // Llamar a diferentes métodos según la opción seleccionada
                switch (opcion) {
                    case 1:
                        comprarEntrada(); // Método para comprar entradas
                        break;
                    case 2:
                        mostrarVentas(); // Mostrar las ventas realizadas
                        break;
                    case 3:
                        generarBoleta(); // Generar las boletas
                        break;
                    case 4:
                        mostrarIngresos(); // Mostrar los ingresos totales
                        break;
                    case 5:
                        salir = true; // Salir del programa
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Entrada no válida. Intente nuevamente.");
                sc.nextLine(); // Limpiar el buffer
            }
        }
    }

    // Método para comprar una entrada
    static void comprarEntrada() {
        try {
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();

            // Captura de la edad con validación
            int edad = 0;
            boolean edadValida = false;
            while (!edadValida) {
                try {
                    System.out.print("Edad: ");
                    edad = sc.nextInt();
                    sc.nextLine(); // Limpiar el buffer
                    if (edad < 0) {
                        System.out.println("La edad no puede ser negativa. Intente nuevamente.");
                    } else {
                        edadValida = true;
                    }
                } catch (Exception e) {
                    System.out.println("Por favor, ingrese un número válido para la edad.");
                    sc.nextLine(); // Limpiar el buffer
                }
            }

            // Captura del género
            System.out.print("Género (M/F): ");
            String genero = sc.nextLine().toUpperCase();

            // Determinar tipo de cliente basado en edad y género
            String tipoCliente = obtenerTipoCliente(edad, genero);

            // Captura de la sección seleccionada con validación
            int opcionSeccion = 0;
            boolean seccionValida = false;
            while (!seccionValida) {
                try {
                    mostrarSecciones();
                    System.out.print("Seleccione sección (1-5): ");
                    opcionSeccion = sc.nextInt();
                    sc.nextLine(); // Limpiar el buffer
                    if (opcionSeccion < 1 || opcionSeccion > 5) {
                        System.out.println("Opción inválida. Intente nuevamente.");
                    } else {
                        seccionValida = true;
                    }
                } catch (Exception e) {
                    System.out.println("Entrada no válida. Intente nuevamente.");
                    sc.nextLine(); // Limpiar el buffer
                }
            }

            // Obtener precio base según la sección seleccionada
            String seccion = secciones[opcionSeccion - 1];
            double precioBase = preciosBase[opcionSeccion - 1];

            // Captura del asiento con validación
            int asiento = 0;
            boolean asientoValido = false;
            while (!asientoValido) {
                try {
                    System.out.print("Ingrese número de asiento (1-" + TOTAL_ASIENTOS + "): ");
                    asiento = sc.nextInt();
                    sc.nextLine(); // Limpiar el buffer
                    if (asiento < 1 || asiento > TOTAL_ASIENTOS) {
                        System.out.println("Asiento fuera de rango. Intente nuevamente.");
                    } else if (asientosOcupados.contains(asiento)) {
                        System.out.println("Este asiento ya está ocupado. Intente con otro.");
                    } else {
                        asientoValido = true;
                    }
                } catch (Exception e) {
                    System.out.println("Entrada no válida. Intente nuevamente.");
                    sc.nextLine(); // Limpiar el buffer
                }
            }

            // Aplicar el descuento según el tipo de cliente
            double precioFinal = aplicarDescuento(precioBase, tipoCliente);
            asientosOcupados.add(asiento); // Marcar el asiento como ocupado

            // Crear la entrada y agregarla a las ventas
            Entrada entrada = new Entrada(nombre, edad, genero, tipoCliente, seccion, asiento, precioFinal);
            ventas.add(entrada);
            entrada.imprimirBoleta(); // Imprimir la boleta

        } catch (Exception e) {
            System.out.println("Error al procesar la compra. Intente de nuevo.");
            sc.nextLine(); // Limpiar el buffer
        }
    }

    // Método para obtener el tipo de cliente basado en edad y género
    static String obtenerTipoCliente(int edad, String genero) {
        if (edad < 12) return "Niño";
        if (edad >= 60) return "Tercera Edad";
        if (genero.equals("F")) return "Mujer";
        System.out.print("¿Es estudiante? (S/N): ");
        String estudiante = sc.nextLine().toUpperCase();
        if (estudiante.equals("S")) return "Estudiante";
        return "Adulto (sin descuento)";
    }

    // Método para aplicar un descuento dependiendo del tipo de cliente
    static double aplicarDescuento(double precio, String tipoCliente) {
        switch (tipoCliente) {
            case "Niño":
                return precio * 0.90; // 10% de descuento
            case "Mujer":
                return precio * 0.80; // 20% de descuento
            case "Estudiante":
                return precio * 0.85; // 15% de descuento
            case "Tercera Edad":
                return precio * 0.75; // 25% de descuento
            default:
                return precio; // Sin descuento
        }
    }

    // Método para mostrar las secciones disponibles y sus precios
    static void mostrarSecciones() {
        System.out.println("Secciones disponibles:");
        for (int i = 0; i < secciones.length; i++) {
            System.out.println((i + 1) + ". " + secciones[i] + " - $" + preciosBase[i]);
        }
    }

    // Método para mostrar todas las ventas realizadas
    static void mostrarVentas() {
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
        } else {
            for (Entrada e : ventas) {
                e.imprimirBoleta();
            }
        }
    }

    // Método para generar todas las boletas de ventas
    static void generarBoleta() {
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas realizadas. No se puede generar boleta.");
        } else {
            for (Entrada e : ventas) {
                e.imprimirBoleta();
            }
        }
    }

    // Método para mostrar los ingresos totales del sistema
    static void mostrarIngresos() {
        double ingresosTotales = 0;
        for (Entrada e : ventas) {
            ingresosTotales += e.precioFinal;
        }
        System.out.println("Ingresos totales: $" + ingresosTotales);
    }
}



