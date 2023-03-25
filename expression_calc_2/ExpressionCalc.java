package expression_calc_2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionCalc {


    /**
     * @param expression
     * @return expression
     * Função de controle, que verifica os caracteres presentes na 
     * expressão e então escolhe qual operção deve ser feita.
     * utiliza recurisividade para resolver todos as operações.
     */
    public String calculadora(String expression) {
        if (expression.contains("/") || expression.contains("*")){
            expression = calcularDivMult(expression);
            expression = calculadora(expression);
        }

        expression = calcularSomaSubtracao(expression);
        
        return expression;
    }


    /**
     * @param expression
     * Calcula a soma e subtração de equações simples
     */

    public String calcularSomaSubtracao(String expression) { 
        Double result = 0.0;
        Pattern pattern = Pattern.compile("[+-]?[0-9]+\\.?[0-9]*");
        Matcher matcher = pattern.matcher(expression);

        //Encontra o padrão de uma expressão simples matemática
        //cada parte encotrada é adicionada na variável result
        
        while (matcher.find()) {
            result = result + Double.parseDouble(matcher.group());
            expression = expression.replace(matcher.group(), "");
        }

        return result.toString();

    }

    /**
     * @param expression
     * @return resust
     * calcula a divisão e multiplicação 
     * usando regex, encontra o resultado e subistitui
     * como o replace pelo padrão encontrado.
     * retorna o resutado em string para a função
     * calculadora. Se ainda houver os caracteres que representem
     * multiplicação ou divisão, então a função calculadora é chamada
     * novamente recursivamente.
     */

    public String calcularDivMult(String expression) {
        Double result = 0.0;
        Pattern pattern = Pattern.compile("[+-]?[0-9]+\\.?[0-9]*[\\/\\*]+[+-]?[0-9]+\\.?[0-9]*");
        Matcher matcher = pattern.matcher(expression);
        matcher.find();  
        if (matcher.group().contains("/")){
            String partes[] = matcher.group().split("/");
            result = Double.parseDouble(partes[0]) / Double.parseDouble(partes[1]);
            expression = expression.replace(matcher.group(), Double.toString(result));
        }else {
            String partes[] = matcher.group().split("\\*");
            result = Double.parseDouble(partes[0]) * Double.parseDouble(partes[1]);
            expression = expression.replace(matcher.group(), Double.toString(result));
        }
        return expression;
    }


    


}
