package expression_calc_2;

public class Testes {
    public static void main(String[] args) {
        // PatternFinder p = new PatternFinder("+1.99009987+123.3",
        // "[+-]?[0-9]+\\.?[0-9]*");
        // System.out.println(p.patternGroup());

        // String s = "+1.032";
        // Double d = Double.parseDouble(s);

        // System.out.println(d);

        ExpressionCalc e = new ExpressionCalc();

        // System.out.println(e.calcularSomaSubtracao("1+10"));
        // e.calcularDivMult("2*2");
        // System.out.println("Teste 1 = " + e.calculadora("-10/-2*5"));
        // System.out.println("Teste 2 = " + e.calculadora("-3*4/3+5"));

        String teste = e.resolveParenteis("1/2*(3^2)+2^(2+2^2)");
        System.out.println(teste);

    }

}
