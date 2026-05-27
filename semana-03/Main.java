import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        CalculadoraController controller = new CalculadoraController();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n=== Calculadora Basada en Componentes ===");
            System.out.println("1. Suma");
            System.out.println("2. Resta");
            System.out.println("3. Multiplicación");
            System.out.println("4. División");
            System.out.println("5. Raíz cuadrada");
            System.out.println("6. Factorial");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Primer número: ");
                    double a1 = scanner.nextDouble();
                    System.out.print("Segundo número: ");
                    double b1 = scanner.nextDouble();
                    System.out.println("Resultado: " + controller.sumar(a1, b1));
                    break;
                case 2:
                    System.out.print("Primer número: ");
                    double a2 = scanner.nextDouble();
                    System.out.print("Segundo número: ");
                    double b2 = scanner.nextDouble();
                    System.out.println("Resultado: " + controller.restar(a2, b2));
                    break;
                case 3:
                    System.out.print("Primer número: ");
                    double a3 = scanner.nextDouble();
                    System.out.print("Segundo número: ");
                    double b3 = scanner.nextDouble();
                    System.out.println("Resultado: " + controller.multiplicar(a3, b3));
                    break;
                case 4:
                    System.out.print("Primer número: ");
                    double a4 = scanner.nextDouble();
                    System.out.print("Segundo número: ");
                    double b4 = scanner.nextDouble();
                    System.out.println("Resultado: " + controller.dividir(a4, b4));
                    break;
                case 5:
                    System.out.print("Número: ");
                    double a5 = scanner.nextDouble();
                    System.out.println("Resultado: " + controller.raizCuadrada(a5));
                    break;
                case 6:
                    System.out.print("Número entero: ");
                    int n = scanner.nextInt();
                    System.out.println("Resultado: " + controller.factorial(n));
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}