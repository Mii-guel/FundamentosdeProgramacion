
package exp2_s4_miguel_castillo;

import java.util.Scanner;
        
public class Exp2_S4_Miguel_Castillo {

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int continuar = 0;

        while (continuar == 0) {
            System.out.println("Bienvenido al teatro Moro");
            System.out.println("(Seleccione un número según su opción)");
            System.out.println("1. Comprar entrada");
            System.out.println("2. Salir");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    int numAsiento = 1;
                    int asiento = 0;

                    // Selección de asiento
                    while (numAsiento == 1) {
                        System.out.println("Elija su número de asiento (del 1 al 20):");
                        asiento = scanner.nextInt();
                        if (asiento >= 1 && asiento <= 20) {
                            System.out.println("Ha elegido el asiento número: " + asiento);
                            numAsiento = 0;
                        } else {
                            System.out.println("Elija una opción válida.");
                        }
                    }

                    // Selección de zona
                    int zonas = 1;
                    int zona = 0;
                    int precioBase = 0;
                    while (zonas == 1) {
                        System.out.println("Elija la zona:");
                        System.out.println("1. Zona A");
                        System.out.println("2. Zona B");
                        System.out.println("3. Zona C");
                        zona = scanner.nextInt();

                        switch (zona) {
                            case 1:
                                System.out.println("Su asiento es el número: " + asiento + ", en la Zona A.");
                                precioBase = 5000; // Precio de la Zona A
                                zonas = 0;
                                break;
                            case 2:
                                System.out.println("Su asiento es el número: " + asiento + ", en la Zona B.");
                                precioBase = 3000; // Precio de la Zona B
                                zonas = 0;
                                break;
                            case 3:
                                System.out.println("Su asiento es el número: " + asiento + ", en la Zona C.");
                                precioBase = 1500; // Precio de la Zona C
                                zonas = 0;
                                break;
                            default:
                                System.out.println("Elija una opción válida.");
                                break;
                        }
                    }

                    // Verificación de edad para descuento
                    int repetir = 1;
                    double descuento = 0;
                    while (repetir == 1) {
                        System.out.println("Indique su edad para ver si opta a un descuento:");
                        int edad = scanner.nextInt();

                        if (edad <= 23) {
                            System.out.println("Usted opta a un descuento del 10% para su entrada.");
                            descuento = 0.1;
                            repetir = 0;
                        } else if (edad >= 60) {
                            System.out.println("Usted opta a un descuento del 15% para su entrada.");
                            descuento = 0.15;
                            repetir = 0;
                        } else if (edad > 23 && edad < 60) {
                            System.out.println("Usted no opta a ningún descuento.");
                            repetir = 0;
                        } else {
                            System.out.println("Edad inválida. Intente nuevamente.");
                        }
                    }

                    // Calcular precio final con descuento
                    double precioFinal = precioBase - (precioBase * descuento);

                    // Visualizar resumen de la compra
                    System.out.println("\nResumen de su compra:");
                    System.out.println("Ubicación del asiento: Zona " + (zona == 1 ? "A" : (zona == 2 ? "B" : "C")));
                    System.out.println("Precio base de la entrada: $" + precioBase);
                    System.out.println("Descuento aplicado: " + (descuento * 100) + "%");
                    System.out.println("Precio final a pagar: $" + precioFinal);

                    // Preguntar si desea realizar otra compra
                    System.out.println("¿Desea realizar otra compra?");
                    System.out.println("1. Sí");
                    System.out.println("2. No");
                    int opcionCompra = scanner.nextInt();
                    if (opcionCompra == 2) {
                        continuar = 1;
                        System.out.println("Gracias por usar nuestro sistema de ventas de entradas. ¡Esperamos verte pronto!");
                    }

                    break;

                case 2:
                    System.out.println("Gracias por usar nuestro sistema de ventas de entradas. ¡Esperamos verte pronto!");
                    continuar = 1;
                    break;

                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        }

        scanner.close();
    }
}

    
