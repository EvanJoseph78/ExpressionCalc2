package expression_calc_2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Math;

public class ExpressionCalc {

    /**
     * @param expression
     * @return expression
     *         Função de controle, que verifica os caracteres presentes na
     *         expressão e então escolhe qual operção deve ser feita.
     */

    public String calculadora(String expression) {
        if (expression.contains("^")) {
            expression = exponenciation(expression);
        }

        if (expression.contains("(")) {
            expression = resolveParenteis(expression);
        }

        if (expression.contains("/") || expression.contains("*")) {
            expression = calcularDivMult(expression);
        }

        if (expression.equals("Invalid Expression: Divisão por zero!") ||
                expression.equals("Infinity") ||
                expression.equals("Undefined")) {
            return expression;
        }

        expression = calcularSomaSubtracao(expression);

        return expression;
    }

    /**
     * @param expression
     *                   Calcula a soma e subtração de equações simples
     */

    public String calcularSomaSubtracao(String expression) {
        Double result = 0.0;
        Pattern pattern = Pattern.compile("[+-]?[0-9]+\\.?[0-9]*");
        Matcher matcher = pattern.matcher(expression);

        // Encontra o padrão de uma expressão simples matemática
        // cada parte encotrada é adicionada na variável result

        while (matcher.find()) {
            result = result + Double.parseDouble(matcher.group());
            expression = expression.replace(matcher.group(), "");
        }

        return result.toString();

    }

    /**
     * @param expression
     * @return resust
     *         calcula a divisão e multiplicação
     *         usando regex, encontra o resultado e subistitui
     *         como o replace pelo padrão encontrado.
     *         retorna o resutado em string para a função
     *         calculadora. Se ainda houver os caracteres que representem
     *         multiplicação ou divisão, então a função calculadora é chamada
     *         novamente recursivamente.
     */

    public String calcularDivMult(String expression) {
        Double result = 0.0;
        String resultString = "";
        Pattern pattern = Pattern.compile("[+-]?[0-9]+\\.?[0-9]*[\\/\\*]+[+-]?[0-9]+\\.?[0-9]*");
        Matcher matcher = pattern.matcher(expression);
        matcher.find();
        if (matcher.group().contains("/")) {

            String partes[] = matcher.group().split("/");

            result = Double.parseDouble(partes[0]) / Double.parseDouble(partes[1]);

            if (Double.isNaN(result)) {
                return "Invalid Expression: Divisão por zero!";
            }
            if (Double.isInfinite(result)) {
                return "Infinity";
            }
            // necessário esse bloco de if else pois o resultado pode dar positivo
            // e quando isso acontece a string não iria possuir o sinal.

            if (result >= 0) {
                resultString = "+" + Double.toString(result);
            } else {
                resultString = Double.toString(result);
            }
            expression = expression.replace(matcher.group(), resultString);
        } else {
            String partes[] = matcher.group().split("\\*");
            result = Double.parseDouble(partes[0]) * Double.parseDouble(partes[1]);

            // necessário esse bloco de if else pois o resultado pode dar positivo
            // e quando isso acontece a string não iria possuir o sinal.

            if (result >= 0) {
                resultString = "+" + Double.toString(result);
            } else {
                resultString = Double.toString(result);
            }
            expression = expression.replace(matcher.group(), resultString);
        }

        // Se ainda houver multiplicação ou divisão a fazer, a função é chamada
        // novamente recursivamente.

        if (expression.contains("/") || expression.contains("*")) {
            expression = calcularDivMult(expression);
        }

        return expression;

    }

    /**
     * @param expression
     * @return string
     *         resolve os parentesis como prioridade
     */

    public String resolveParenteis(String expression) {
        // encotra os parentesis mais internamente
        Pattern pattern = Pattern.compile("\\([^()]*\\)");
        Matcher matcher = pattern.matcher(expression);
        if (matcher.find()) {
            String parteExpression = matcher.group();

            parteExpression = parteExpression.replace("(", "");
            parteExpression = parteExpression.replace(")", "");

            expression = expression.replace(matcher.group(), calculadora(parteExpression));

            // se ainda houver parentesis a serem resolvidos a função é chamada novamente
            // recursivamente
            return resolveParenteis(expression);

        }

        expression = calculadora(expression);

        return expression;
    }

    public String exponenciation(String expression) {
        Double result = 0.0;
        String resultString = "";
        Pattern pattern = Pattern.compile("[+-]?[0-9]+\\.?[0-9]*[\\^]+[+-]?[0-9]+\\.?[0-9]*");
        Matcher matcher = pattern.matcher(expression);
        matcher.find();

        String partes[] = matcher.group().split("\\^");

        if (Double.parseDouble(partes[0]) == 0 && Double.parseDouble(partes[1]) == 0) {
            return "Undefined";
        }

        result = Math.pow(Double.parseDouble(partes[0]), Double.parseDouble(partes[1]));

        if (result >= 0) {
            resultString = "+" + Double.toString(result);
        } else {
            resultString = Double.toString(result);
        }

        expression = expression.replace(matcher.group(), resultString);

        if (expression.contains("^")) {
            expression = calcularDivMult(expression);
        }

        return expression;
    }

}
