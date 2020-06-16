import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);
        System.out.print("Input number: ");
        String expression = in.nextLine();
        expression = deleteSpaces(expression);
        ExpressionData data = getSign(expression);
        expression = replaceRomanToArabic(expression, data);
        data = getSign(expression);
        String result = endResult(expression, data);
        System.out.println("Answer: " + result);
        in.close();
    }

    public static ExpressionData getSign(String inputLine) throws Exception {
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
        throw new Exception("Неправильный ввод");
    }

    public static String endResult(String expression, ExpressionData data) throws Exception {
        int romanSign = 0;
        if (expression.charAt(expression.length() - 1) == '!') {
            romanSign = 1;
        }
        expression = expression.replaceAll("!", "");
        String firstStr = expression.substring(0, data.signIndex);
        String secondStr = expression.substring(data.signIndex + 1);

        try {
            Integer.parseInt(firstStr);
            Integer.parseInt(secondStr);
        } catch (NumberFormatException e) {
            throw new Exception("Неправильный ввод");
        }
        if (Integer.parseInt(firstStr) > 10 || Integer.parseInt(firstStr) < 0 || Integer.parseInt(secondStr) > 10 || Integer.parseInt(secondStr) < 0) {
            throw new Exception("Неправильный ввод");
        }
        int result = 0;
        if (data.sign == '+') {
            result = Integer.parseInt(firstStr) + Integer.parseInt(secondStr);
        }
        if (data.sign == '-') {
            result = Integer.parseInt(firstStr) - Integer.parseInt(secondStr);
        }
        if (data.sign == '*') {
            result = Integer.parseInt(firstStr) * Integer.parseInt(secondStr);
        }
        if (data.sign == '/') {
            result = Integer.parseInt(firstStr) / Integer.parseInt(secondStr);
        }

        if (romanSign == 0) {
            return String.valueOf(result);
        } else {
            return RomanAnswer(result);
        }
    }

    public static String deleteSpaces(String inputLine) {
        return inputLine.replaceAll("\\s", "");
    }

    public static String replaceRomanToArabic(String expression, ExpressionData expData) throws Exception {
        String firstStr = expression.substring(0, expData.signIndex);
        String secondStr = expression.substring(expData.signIndex + 1);
        if (checkStringsParseInt(firstStr, secondStr) == 1) {
            throw new Exception("Неправильный ввод");
        }
        if (checkStringsParseInt(firstStr, secondStr) == 2) {
            firstStr = switchRoman(firstStr);
            secondStr = switchRoman(secondStr);
            return (firstStr + expData.sign + secondStr + "!");
        } else {
            firstStr = switchRoman(firstStr);
            secondStr = switchRoman(secondStr);
            return (firstStr + expData.sign + secondStr);
        }

    }

    public static String switchRoman(String expression) {
        switch (expression) {
            case "I":
                expression = "1";
                break;
            case "II":
                expression = "2";
                break;
            case "III":
                expression = "3";
                break;
            case "IV":
                expression = "4";
                break;
            case "V":
                expression = "5";
                break;
            case "VI":
                expression = "6";
                break;
            case "VII":
                expression = "7";
                break;
            case "VIII":
                expression = "8";
                break;
            case "IX":
                expression = "9";
                break;
            case "X":
                expression = "10";
        }
        return expression;
    }

    public static int checkStringsParseInt(String beforeSign, String afterSign) {
        int numOfNotInt = 0;
        try {
            Integer.parseInt(beforeSign);
        } catch (NumberFormatException e) {
            numOfNotInt++;
        }
        try {
            Integer.parseInt(afterSign);
        } catch (NumberFormatException e) {
            numOfNotInt++;
        }
        return numOfNotInt;
    }

    public static String RomanAnswer(int intExpr) {
        String result = "";
        if (intExpr == 100) {
            result = result + "C";
            intExpr = intExpr - 100;
        }
        if (intExpr >= 90) {
            result = result + "XC";
            intExpr = intExpr - 90;
        }
        if (intExpr >= 50) {
            result = result + "L";
            intExpr = intExpr - 50;
        }
        if (intExpr >= 40) {
            result = result + "XL";
            intExpr = intExpr - 40;
        }
        for (int i = 0; intExpr >= 10; i++) {
            result = result + "X";
            intExpr = intExpr - 10;
        }
        if (intExpr >= 9) {
            result = result + "IX";
            intExpr = intExpr - 9;
        }
        if (intExpr >= 5) {
            result = result + "V";
            intExpr = intExpr - 5;
        }
        if (intExpr >= 4) {
            result = result + "IV";
            intExpr = intExpr - 4;
        }
        for (int i = 0; intExpr > 0; i++) {
            result = result + "I";
            intExpr = intExpr - 1;
        }
        if (intExpr == 0){
            result = "none";
        }
        return result;
    }
}


