package exp2_s5_miguel_castillo;
import java.util.Scanner;

public class exp2_s5_miguel_castillo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int continuar = 1;

        // Precios de las entradas 
        int precioVIP = 30000;  
        int precioGeneral = 15000;  
        int precioAlta = 10000;  
        int precioBaja = 12000;  

        
        int entradasVIP = 0;
        int entradasGeneral = 0;
        int entradasPlateaAlta = 0;
        int entradasPlateaBaja = 0;
        int totalEntradasVendidas = 0;
        int totalIngresos = 0;

        while (continuar == 1) {
            
            System.out.println("Bienvenido al Teatro Moro");
            System.out.println("1. Comprar entrada");
            System.out.println("2. Revisar entradas compradas");
            System.out.println("3. Eliminar entrada");
            System.out.println("4. Mostrar promociones");
            System.out.println("5. Salir");

            if (scanner.hasNextInt()) {
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        
                        System.out.println("Elija la ubicación de su entrada:");
                        System.out.println("1. VIP");
                        System.out.println("2. General");
                        System.out.println("3. Platea Alta");
                        System.out.println("4. Platea Baja");

                        if (scanner.hasNextInt()) {
                            int tipoEntrada = scanner.nextInt();
                            int precioFinal = 0;
                            String ubicacion = "";

                            // Determinar el precio de la entrada según la ubicación
                            switch (tipoEntrada) {
                                case 1:
                                    precioFinal = precioVIP;
                                    ubicacion = "VIP";
                                    break;
                                case 2:
                                    precioFinal = precioGeneral;
                                    ubicacion = "General";
                                    break;
                                case 3:
                                    precioFinal = precioAlta;
                                    ubicacion = "Platea Alta";
                                    break;
                                case 4:
                                    precioFinal = precioBaja;
                                    ubicacion = "Platea Baja";
                                    break;
                                default:
                                    System.out.println("Opción no válida.");
                                    continue;
                            }

                            
                            System.out.println("Ingrese su edad para ver si opta a algún descuento");
                            if (scanner.hasNextInt()) {
                                int edad = scanner.nextInt();
                                if (edad <= 23) {
                                    System.out.println("Usted opta a un descuento del 10% para su entrada.");
                                    precioFinal = (int) (precioFinal * 0.90); // Descuento del 10%
                                } else if (edad >= 65) {
                                    System.out.println("Usted opta a un descuento del 15% para su entrada.");
                                    precioFinal = (int) (precioFinal * 0.85); // Descuento del 15%
                                }
                            } else {
                                System.out.println("Edad no válida.");
                                continue;
                            }

                            
                            totalEntradasVendidas++;
                            totalIngresos += precioFinal;

                            // Registrar la entrada vendida
                            switch (tipoEntrada) {
                                case 1:
                                    entradasVIP++;
                                    break;
                                case 2:
                                    entradasGeneral++;
                                    break;
                                case 3:
                                    entradasPlateaAlta++;
                                    break;
                                case 4:
                                    entradasPlateaBaja++;
                                    break;
                            }

                            System.out.println("Entrada comprada con éxito. El precio final es: $" + precioFinal);
                        }
                        break;
                    case 2:
                        // Revisar entradas compradas
                        System.out.println("Entradas compradas:");
                        System.out.println("VIP: " + entradasVIP);
                        System.out.println("General: " + entradasGeneral);
                        System.out.println("Platea Alta: " + entradasPlateaAlta);
                        System.out.println("Platea Baja: " + entradasPlateaBaja);
                        System.out.println("Total de entradas vendidas: " + totalEntradasVendidas);
                        System.out.println("Ingresos totales: $" + totalIngresos);
                        break;
                    case 3:
                        // Eliminar entrada
                        System.out.println("Ingrese el tipo de entrada que desea eliminar (VIP, General, Platea Alta, Platea Baja):");
                        scanner.nextLine(); 
                        String tipoEntradaEliminar = scanner.nextLine();

                        boolean eliminado = false;
                        switch (tipoEntradaEliminar.toLowerCase()) {
                            case "vip":
                                if (entradasVIP > 0) {
                                    entradasVIP--;
                                    eliminado = true;
                                }
                                break;
                            case "general":
                                if (entradasGeneral > 0) {
                                    entradasGeneral--;
                                    eliminado = true;
                                }
                                break;
                            case "platea alta":
                                if (entradasPlateaAlta > 0) {
                                    entradasPlateaAlta--;
                                    eliminado = true;
                                }
                                break;
                            case "platea baja":
                                if (entradasPlateaBaja > 0) {
                                    entradasPlateaBaja--;
                                    eliminado = true;
                                }
                                break;
                            default:
                                System.out.println("Tipo de entrada no válida.");
                                break;
                        }

                        if (eliminado) {
                            totalEntradasVendidas--;
                            System.out.println("Entrada eliminada con éxito.");
                        } else {
                            System.out.println("No se encontró entradas de ese tipo para eliminar.");
                        }
                        break;
                    case 4:
                        // Mostrar promociones
                        System.out.println("Promociones disponibles:");
                        System.out.println("1. Descuento del 10% para estudiantes.");
                        System.out.println("2. Descuento del 15% para personas de la tercera edad.");
                        break;
                    case 5:
                        // Salir
                        continuar = 2;
                        break;
                    default:
                        System.out.println("Opción inválida, intente de nuevo.");
                        break;
                }
            } else {
                System.out.println("Elija una opción válida");
                scanner.nextLine(); 
            }
        }

        scanner.close();
    }
}

