package TestTask;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner concole = new Scanner(System.in);
        String input = concole.next();

        try {
            System.out.println(Test.calc(input));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Строка не является математической операцией");
        } catch (DifferentDataInException e) {
            System.err.println(e.getMessage());
        } catch (MoreOperandsException e1) {
            System.err.println(e1.getMessage());

        } catch (ZeroException e) {
            System.err.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Калькулятор принимает на вход числа от 1  до 10 включительно.");
        }
    }
}

class Test {
    static int a;
    static int b;
    static String result;
    static int acting;
    static String inputToSplit = "";
    static String[] split = new String[2];

    public static String calc(String input) throws MoreOperandsException, DifferentDataInException, ZeroException {
        input = input.replaceAll("\\s", "");
        for (MathOperators mathsOperator : MathOperators.values()) {
            if (input.contains(mathsOperator.getMathValue())) {
                int i = input.indexOf(mathsOperator.getMathValue());
                inputToSplit = input.substring(0, i).concat(" ").concat(mathsOperator.getMathValue()).concat(" ").concat(input.substring(i + 1));
            }
        }
        split = inputToSplit.split(" ");
        if (split.length > 3) {
            throw new MoreOperandsException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор( +, -, /, *)");
        }

        Pattern pattern1 = Pattern.compile("[^1-9]");
        Pattern pattern2 = Pattern.compile("^[0-9]");
        Matcher matcher1 = pattern1.matcher(split[0]);
        Matcher matcher2 = pattern1.matcher(split[2]);
        Matcher matcher3 = pattern2.matcher(split[0]);
        Matcher matcher4 = pattern2.matcher(split[2]);

        for (MathOperators mathOperator : MathOperators.values()) {
            if (mathOperator.getMathValue().equals(split[1])) {
                if (matcher1.find() && matcher2.find()) {
                    a = RomanArabicConverter.romanToArabic(split[0]);
                    b = RomanArabicConverter.romanToArabic(split[2]);
                    if (a > 0 && a < 11 && b > 0 && b < 11) {
                        if (choiceOperator(mathOperator.getMathValue(), a, b) <= 0) {
                            throw new ZeroException("В римской системе исчисления нет нуля  и отрицательных чисел.");
                        } else {
                            result = RomanArabicConverter.arabicToRoman(choiceOperator(split[1], a, b));
                        }
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else if (matcher3.find() && matcher4.find()) {
                    a = Integer.parseInt(split[0]);
                    b = Integer.parseInt(split[2]);
                    if (a > 0 && a < 11 && b > 0 && b < 11) {
                        result = String.valueOf(choiceOperator(mathOperator.getMathValue(), a, b));
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    throw new DifferentDataInException("Используются разные системы исчисления.");
                }
            }
        }
        return result;
    }

    static int choiceOperator(String split, int a, int b) {
        if (split.equals(MathOperators.PLUS.getMathValue())) {
            acting = a + b;
        }
        if (split.equals(MathOperators.MINUS.getMathValue())) {
            acting = a - b;
        }
        if (split.equals(MathOperators.TIMES.getMathValue())) {
            acting = a * b;
        }
        if (split.equals((MathOperators.DIVIDE.getMathValue()))) {
            acting = a / b;
        }
        return acting;
    }
}