package expression_calc_2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionCalc {


    /**
     * @param expression
     * Calcula a soma e subtração de equações simples
     */

    public String calcularSomaSubtracao(String expression) { 
        Double result = 0.0;
        Pattern pattern = Pattern.compile("[+-][0-9]+\\.?[0-9]*");
        Matcher matcher = pattern.matcher(expression);

        //Encontra o padrão de uma expressão simples matemática
        //cada parte encotrada é adicionada na variável result
        while (matcher.find()) {
            result = result + Double.parseDouble(matcher.group());
            expression = expression.replace(matcher.group(), "");
        }

        return result.toString();

    }


    


}
