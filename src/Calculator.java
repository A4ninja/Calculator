import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);
        System.out.print("Input number: ");
        String expression = in.nextLine();
        expression = deleteSpaces(expression);
        ExpressionData data = getSign(expression);
        expression = replaceRomanToArabic(expression,data);
        data = getSign(expression);
        int result = endResult(expression,data);
        System.out.println(result);
        in.close();
    }

    public static ExpressionData getSign(String inputLine) throws Exception  {
        String signs = "+-*/";
        ExpressionData data = new ExpressionData();
        for (int i = 0; i < inputLine.length(); i++) {
            char ch1 = inputLine.charAt(i);
            for (int j = 0; j < signs.length(); j++) {
                char ch2 = signs.charAt(j);
                if (ch1 == ch2) {
                    data.sign = ch1;
                    data.signIndex = i;
                    return data;
                }
            }
        }
        throw new Exception ("Неправильный ввод");
    }

    public static int endResult(String expression, ExpressionData data) throws Exception {
        String firstStr = expression.substring(0, data.signIndex);
        String secondStr = expression.substring(data.signIndex + 1);
        try{
            Integer.parseInt(firstStr);
            Integer.parseInt(secondStr);
        } catch (NumberFormatException e) {
            throw new Exception ("Неправильный ввод");
        }
        if (Integer.parseInt(firstStr) > 10 || Integer.parseInt(firstStr) < 0 || Integer.parseInt(secondStr) > 10 || Integer.parseInt(secondStr) < 0 ){
            throw new Exception ("Неправильный ввод");
        }
        int result = 0;
        if (data.sign == '+'){
            result = Integer.parseInt(firstStr) + Integer.parseInt(secondStr);
        }
        if (data.sign == '-'){
            result = Integer.parseInt(firstStr) - Integer.parseInt(secondStr);
        }
        if (data.sign == '*'){
            result = Integer.parseInt(firstStr) * Integer.parseInt(secondStr);
        }
        if (data.sign == '/'){
            result = Integer.parseInt(firstStr) / Integer.parseInt(secondStr);
        }
        return result;
    }

    public static String deleteSpaces(String inputLine) {
        return inputLine.replaceAll("\\s", "");
    }

    public static String replaceRomanToArabic(String expression, ExpressionData expData) throws Exception{
        String firstStr = expression.substring(0, expData.signIndex);
        String secondStr = expression.substring(expData.signIndex + 1);
        if (checkStringsParseInt(firstStr,secondStr) == 1){
            throw new Exception("Неправильный ввод");
        }
        firstStr = switchRoman(firstStr);
        secondStr = switchRoman(secondStr);
        return (firstStr + expData.sign + secondStr);
    }

    public static String switchRoman (String expression){
        switch (expression) {
            case "I": expression = "1";
                break;
            case "II": expression = "2";
                break;
            case "III": expression = "3";
                break;
            case "IV": expression = "4";
                break;
            case "V": expression = "5";
                break;
            case "VI": expression = "6";
                break;
            case "VII": expression = "7";
                break;
            case "VIII": expression = "8";
                break;
            case "IX": expression = "9";
                break;
            case "X": expression = "10";
        }
        return expression;
    }
    public static int checkStringsParseInt (String beforeSign, String afterSign){
        int numOfInt = 0;
        try {
            Integer.parseInt(beforeSign);
        }
        catch (NumberFormatException e) {
            numOfInt++;
        }
        try {
            Integer.parseInt(afterSign);
        }
        catch (NumberFormatException e) {
            numOfInt++;
        }
        return numOfInt;
        }
    }


