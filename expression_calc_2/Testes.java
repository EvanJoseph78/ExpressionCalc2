package expression_calc_2;
public class Testes {
    public static void main(String[] args) {
        // PatternFinder p = new PatternFinder("+1.99009987+123.3", "[+-]?[0-9]+\\.?[0-9]*");
        // System.out.println(p.patternGroup());

        // String s = "+1.032";
        // Double d = Double.parseDouble(s);

        // System.out.println(d);

        ExpressionCalc e = new ExpressionCalc();
        
        System.out.println(e.calcularSomaSubtracao("100+200+122"));
    }
    
    
    
}




