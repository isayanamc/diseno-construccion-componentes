public class CalculadoraController {

    private Calculadora calculadora = new Calculadora();

    public double sumar(double a, double b) { return calculadora.sumar(a, b); }
    public double restar(double a, double b) { return calculadora.restar(a, b); }
    public double multiplicar(double a, double b) { return calculadora.multiplicar(a, b); }
    public double dividir(double a, double b) { return calculadora.dividir(a, b); }
    public double raizCuadrada(double a) { return calculadora.raizCuadrada(a); }
    public long factorial(int n) { return calculadora.factorial(n); }
}