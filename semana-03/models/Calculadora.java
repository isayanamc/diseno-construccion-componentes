public class Calculadora {

    public double sumar(double a, double b) {
        return a + b;
    }

    public double restar(double a, double b) {
        return a - b;
    }

    public double multiplicar(double a, double b) {
        return a * b;
    }

    public double dividir(double a, double b) {
        if (b == 0) throw new ArithmeticException("No se puede dividir entre cero");
        return a / b;
    }

    public double raizCuadrada(double a) {
        return Math.sqrt(a);
    }

    public long factorial(int n) {
        if (n < 0) throw new ArithmeticException("No existe factorial de negativos");
        if (n == 0) return 1;
        long resultado = 1;
        for (int i = 1; i <= n; i++) resultado *= i;
        return resultado;
    }
}