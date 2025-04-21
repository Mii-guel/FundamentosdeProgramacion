
package exp2_s6_miguel_castillo;

import java.util.Scanner;


public class Exp2_s6_Miguel_castillo {

    // Variables estáticas (estadísticas globales)
    static int totalEntradasVendidas = 0;
    static int totalReservas = 0;
    static int totalIngresos = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Variables de instancia (simuladas dentro del main como persistentes)
        String[][] asientos = new String[5][10]; // 5 filas, 10 columnas
        int[] precios = {30000, 15000, 10000, 12000}; // VIP, General, Alta, Baja
        String nombreTeatro = "Teatro Moro";
        int capacidadSala = 50; // 5x10
        boolean corriendo = true;

        // Inicializar todos los asientos como "LIBRE"
        for (int i = 0; i < asientos.length; i++) {
            for (int j = 0; j < asientos[i].length; j++) {
                asientos[i][j] = "LIBRE";
            }
        }

        while (corriendo) {
            System.out.println("Bienvenido al " + nombreTeatro);
            System.out.println("1. Reservar entrada");
            System.out.println("2. Comprar entrada");
            System.out.println("3. Modificar una venta");
            System.out.println("4. Imprimir boleta");
            System.out.println("5. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: // Reservar
                    System.out.println("\nSeleccione fila (0 a 4): ");
                    int filaReserva = scanner.nextInt();
                    System.out.println("Seleccione columna (0 a 9): ");
                    int colReserva = scanner.nextInt();

                    if (filaReserva >= 0 && filaReserva < 5 && colReserva >= 0 && colReserva < 10) {
                        if (asientos[filaReserva][colReserva].equals("LIBRE")) {
                            asientos[filaReserva][colReserva] = "RESERVADO";
                            totalReservas++;
                            System.out.println("Reserva realizada.");
                            // Punto de depuración 1: Verificar reserva
                            System.out.println("[DEBUG] Asiento " + filaReserva + "," + colReserva + " ahora está RESERVADO.");
                        } else {
                            System.out.println("El asiento no está disponible.");
                        }
                    } else {
                        System.out.println("Ubicación inválida.");
                    }
                    break;

                case 2: // Comprar
                    System.out.println("\nSeleccione fila (0 a 4): ");
                    int filaCompra = scanner.nextInt();
                    System.out.println("Seleccione columna (0 a 9): ");
                    int colCompra = scanner.nextInt();

                    if (filaCompra >= 0 && filaCompra < 5 && colCompra >= 0 && colCompra < 10) {
                        if (asientos[filaCompra][colCompra].equals("LIBRE") || asientos[filaCompra][colCompra].equals("RESERVADO")) {
                            System.out.println("Ingrese su edad:");
                            int edad = scanner.nextInt();
                            scanner.nextLine();

                            int tipo = filaCompra == 0 ? 0 : (filaCompra == 1 ? 1 : (filaCompra == 2 ? 2 : 3)); // según fila
                            int precioBase = precios[tipo];
                            int precioFinal = precioBase;

                            if (edad <= 23) {
                                precioFinal = (int)(precioBase * 0.9);
                            } else if (edad >= 65) {
                                precioFinal = (int)(precioBase * 0.85);
                            }

                            asientos[filaCompra][colCompra] = "VENDIDO";
                            totalIngresos += precioFinal;
                            totalEntradasVendidas++;
                            if (asientos[filaCompra][colCompra].equals("RESERVADO")) totalReservas--;

                            // Punto de depuración 2: Compra exitosa
                            System.out.println("[DEBUG] Compra realizada - Asiento " + filaCompra + "," + colCompra);
                            System.out.println("Compra realizada. Total a pagar: $" + precioFinal);
                        } else {
                            System.out.println("El asiento no está disponible para comprar.");
                        }
                    } else {
                        System.out.println("Ubicación inválida.");
                    }
                    break;

                case 3: // Modificar venta
                    System.out.println("\nIngrese fila del asiento a modificar:");
                    int filaMod = scanner.nextInt();
                    System.out.println("Ingrese columna del asiento a modificar:");
                    int colMod = scanner.nextInt();
                    scanner.nextLine();

                    if (filaMod >= 0 && filaMod < 5 && colMod >= 0 && colMod < 10) {
                        if (asientos[filaMod][colMod].equals("VENDIDO")) {
                            asientos[filaMod][colMod] = "LIBRE";
                            totalEntradasVendidas--;
                            System.out.println("Venta anulada.");
                            // Punto de depuración 3: Modificación exitosa
                            System.out.println("[DEBUG] Asiento " + filaMod + "," + colMod + " ahora está LIBRE.");
                        } else {
                            System.out.println("Ese asiento no ha sido comprado.");
                        }
                    } else {
                        System.out.println("Ubicación inválida.");
                    }
                    break;

                case 4: // Imprimir boleta
                    System.out.println("\n----- BOLETA -----");
                    System.out.println("Entradas vendidas: " + totalEntradasVendidas);
                    System.out.println("Reservas activas: " + totalReservas);
                    System.out.println("Total recaudado: $" + totalIngresos);
                    System.out.println("Capacidad total: " + capacidadSala);
                    System.out.println("------------------");
                    // Punto de depuración 4: Verificar impresión de boleta
                    System.out.println("[DEBUG] Boleta generada correctamente.");
                    break;

                case 5:
                    corriendo = false;
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }

        scanner.close();
        System.out.println("Gracias por usar el sistema del Teatro Moro.");
    }
    
}
